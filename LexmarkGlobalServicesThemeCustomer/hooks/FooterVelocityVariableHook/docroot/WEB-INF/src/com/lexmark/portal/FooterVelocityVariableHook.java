package com.lexmark.portal;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;

public class FooterVelocityVariableHook extends Action {
	private static File path = null;
	private static Logger LOG = Logger.getLogger(FooterVelocityVariableHook.class.getName());
	private static File getPath(){
		if(path!=null) return path;
		try {
			InitialContext ctx = new InitialContext();
			String p = ctx.lookup("java:comp/env/footerpath").toString();
			File f = new File(p);
			if(f.canRead() && f.isDirectory()) {
				path = f;
				return path;
			} else {
				LOG.log(Level.SEVERE,"Can't read footer path or is not directory.");
				return null;
			}
		} catch (Exception e) {
			LOG.log(Level.SEVERE,"Unable to get footer path!",e);
			return null;
		}
	}
	
	@Override
	public void run(HttpServletRequest req, HttpServletResponse resp) throws ActionException { 
		Map<String, Object> vmVariables = (Map) req.getAttribute("VM_VARIABLES");
		if(vmVariables==null) {
			HttpSession session = req.getSession();
			synchronized (session) {
				vmVariables = (Map)session.getAttribute("VM_VARIABLES");
				if(vmVariables==null) {
					vmVariables = new HashMap<String, Object>();
					session.setAttribute("VM_VARIABLES", vmVariables);
				}
			}
			req.setAttribute("VM_VARIABLES", vmVariables);
		}
		
		vmVariables.put("footer", this);
	}
	
	public String get(String locale) {
		File path = getPath();
		if(path==null) return "";
		String[] loc = locale.split("_");
		String ll = loc[0].toLowerCase(),cc=loc[1].toLowerCase();
		File file = new File(path,"footer_"+cc+"_"+ll+".html");
		try {
			return read(file);
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Unable to read "+file.getAbsolutePath(), e);
			if("en_US".equals(locale)) return "";
			else return get("en_US");
		}
	}
	
	private static final Pattern extractBodyPattern = Pattern.compile(".*<BODY[^>]*>(.*)</BODY>.*", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	private static String read(File f) throws IOException {
		FileReader r = new FileReader(f);
		char[] buf = new char[1024];
		StringWriter sw = new StringWriter();
		while(true){
			int read = r.read(buf);
			if(read<0) break;
			sw.write(buf, 0, read);
		}
		sw.close(); 
		r.close();
		StringBuffer sb = sw.getBuffer();
		Matcher m = extractBodyPattern.matcher(sb);
		if(m.matches()) return m.group(1);
		return sb.toString();
	}
}
