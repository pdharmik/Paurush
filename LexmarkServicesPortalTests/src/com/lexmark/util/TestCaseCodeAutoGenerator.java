package com.lexmark.util;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.FileReader;
import java.io.BufferedReader;

import javax.swing.text.AbstractDocument.Content;

public class TestCaseCodeAutoGenerator {

	private static BufferedReader reader = null;
	private static String[] elements = new String[30];
	private static String _sourcePath ="C:/LexMark/Workspace/LexmarkServicesCommon/src/com/lexmark/domain";
	private static String _outputPath ="C:/LSP/"; 
	//private static String _sourcePath = "C:/Lexmark_Claims/liferay-plugins-sdk-5.2.3/Workspace/LexmarkCommon/src/com/lexmark/contract/";
	//private static String _outputPath = "C:/Lexmark_Claims/liferay-plugins-sdk-5.2.3/Workspace/LexmarkServiceTests/src/com/lexmark/tests/contract/";
	private static String fileName = "";
	private static String className = "";
	private final String _space = "    ";
	
	@BeforeClass
	public static void setUp() throws Exception{
		
		//fileName = "AgreementDetailsContract.java";
		//className = fileName.substring(0,fileName.indexOf("."));
		
	}
	
 
	public File[] getFileList() throws Exception{
		   File directory = new File(_sourcePath);
	       File[] files = directory.listFiles();
	      
	       for (int i = 0; i < files.length; i++) {
	          
	       }
	       return files;
	}
	
	public String getClassName(String fileName){
		
		return className = fileName.substring(0,fileName.indexOf("."));
	}
	
	@Test
	public void createTestFiles()throws Exception{
		File[] files = getFileList();
		for(File file:files){
			if(file.getName().indexOf(".java") > 0 && file.isFile()){
				
				
				className = getClassName(file.getName());
				readFile(file);
			    createFile(_outputPath+getClassName(file.getName())+"TestCase.java");
			}
		}
	}
	
	public void readFile(File file) throws Exception{
		StringBuffer str = new StringBuffer();
		String tempLine = "";
		for(int i = 0; i < elements.length; i ++){
			elements[i] = null;
		}
		//file = new File(path + fileName);
        reader = new BufferedReader(new FileReader(file));
       
        String tempString = null;
        int line = 1;
        int elementIndex = 0;
        while ((tempString = reader.readLine()) != null) {
        	if(tempString.indexOf("private") > 0 
        			&& tempString.indexOf("serialVersionUID")<0
        			&& tempString.indexOf("(")<0){
        		tempString = tempString.trim();
        		//str.append(tempString);
        		
        		String tmp = tempString.substring(tempString.lastIndexOf(" "),tempString.indexOf(";"));
        		//str.append(tmp);
        		//str.append(",");
        		
        		elements[elementIndex] = tmp.trim();
        		elementIndex ++;
        	}
            line++;
        }
        reader.close();
        
	}
 
	
	public void createFile(String filePathAndName)throws Exception{
		//String filePathAndName = testPath+className+"TestCase.java";
		
		try {  
		      String filePath = filePathAndName;  
		      filePath = filePath.toString();  
		      File myFilePath = new File(filePath);  
		      if (!myFilePath.exists()) {  
		        myFilePath.createNewFile();  
		      }  
		      FileWriter resultFile = new FileWriter(myFilePath);  
		      PrintWriter myFile = new PrintWriter(resultFile);  
		      StringBuffer strContent = populateFileContent("TestCase");  
		      myFile.println(strContent);  
		      resultFile.close();  
		      
		    }  
		    catch (Exception e) {  
		        
		      e.printStackTrace();  
		  
		    }  
	}
	
	public StringBuffer populateFileContent(String fileName)throws Exception{
	
		//readFile();
		//className = getClassName(fileName);
		StringBuffer content = new StringBuffer();
		content.append("package com.lexmark.tests.domain;");
		content.append("\r\n\r\n");
		content.append("import org.junit.Test;\r\n");
		content.append("import org.junit.Before;\r\n");
		content.append("import java.util.Locale;\r\n");
		content.append("import static org.junit.Assert.assertEquals;\r\n");
		content.append("import static org.junit.Assert.assertTrue;\r\n");
		content.append("import static org.junit.Assert.assertNotNull;\r\n\r\n");
		content.append("import com.lexmark.contract."+className+";\r\n\r\n");
		//start of test cases body
		content.append("/**\r\n");
		content.append("*\r\n");
		content.append("* Auto generated Test Codes \r\n");
		content.append("*\r\n");
		content.append("**/\r\n\r\n");
		content.append("public class "+className+"TestCase {\r\n\r\n");
		// define Contract
		content.append("   private " + className + " "+ className.toLowerCase() + " = new "+className+"();\r\n\r\n");
		content.append("   Locale locale = Locale.US;\r\n");
		// Test Case
		content.append("   @Test\r\n");
		content.append("   public void testContract(){\r\n\r\n");
		
		content.append(populateSetMethods());
		
		content.append("\r\n\r\n");

		content.append(populateGetMethods());

		content.append("   }\r\n");
		content.append("}\r\n");
		return content;
	}
	
	public StringBuffer populateSetMethods(){
		//className = getClassName(fileName);
		StringBuffer setMethods = new StringBuffer();
		for(int i = 0; i < elements.length; i ++){
			if(elements[i] != null){
				if(elements[i].trim().equals("locale")){
					setMethods.append(_space+className.toLowerCase()
							+".set"+getElementName(elements[i].trim())+"("
							+ elements[i].trim() 
							+ ");\r\n");
				}else{
					setMethods.append(_space+className.toLowerCase()
						+".set"+getElementName(elements[i].trim())+"(\""
						+ elements[i].trim() 
						+ "\");\r\n");
				}
			}
		}
		return setMethods;
	}
	
	public StringBuffer populateGetMethods(){
		
		StringBuffer getMethods = new StringBuffer();
		for(int i = 0; i < elements.length; i ++){
			if(elements[i] != null){
				if(elements[i].trim().equals("locale")){
					getMethods.append(_space+"assertEquals(");
					getMethods.append(elements[i].trim()+".US,");
					getMethods.append(className.toLowerCase()+".get"+getElementName(elements[i].trim()));
					getMethods.append("());\r\n");
				}else{
					getMethods.append(_space+"assertEquals(\"");
					getMethods.append(elements[i].trim()+"\",");
					getMethods.append(className.toLowerCase()+".get"+getElementName(elements[i].trim()));
					getMethods.append("());\r\n");
				}
			}
		}
		return getMethods;
	}
	
	public String getElementName(String element){
		String _firstCharacter = "";
		String _elementName = "";
		if(element != null){
			_firstCharacter = element.substring(0,1).toUpperCase();
			_elementName = _firstCharacter+element.substring(1,element.length());
		}
		return _elementName;
	}
}
