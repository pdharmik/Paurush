package com.lexmark.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.portlet.ResourceResponse;

public class ResourceResponseUtil {
	public static PrintWriter getUTF8PrintWrtierWithBOM(ResourceResponse response) throws IOException {
		OutputStream outputStream = response.getPortletOutputStream();
		outputStream.write(0xEF);   // 1st byte of BOM
		outputStream.write(0xBB);
		outputStream.write(0xBF);   // last byte of BOM
		// now get a PrintWriter to stream the chars.
		PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream,"UTF-8"));
		
		return out;
	}
}
