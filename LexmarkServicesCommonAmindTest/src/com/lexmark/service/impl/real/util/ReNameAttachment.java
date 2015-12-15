package com.lexmark.service.impl.real.util;

import java.io.File;

import org.junit.Test;

public class ReNameAttachment {

	@Test
	public void renameFileonFTP()
	{
		StringBuilder filePathBuilder = new StringBuilder();
		filePathBuilder.append("\\\\uslexna2t1p\\siebelftp\\Swportal\\MRIMPORT\\");
		//filePathBuilder.append("Meter_Read 4_1a.csv");
		File destPath = new File(filePathBuilder.toString());
		 File[] listOfFiles = destPath.listFiles();
		 for(File file: listOfFiles){
			 System.out.println(file.getName());
		 }
	//	System.out.println("number of files" + count);
		/*File file2 = new File("\\\\uslexna2t1p\\siebelftp\\Swportal\\MRIMPORT\\RenameTest.csv");
		destPath.renameTo(file2);
	}*/
}
}
