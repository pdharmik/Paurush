package com.lexmark.service.impl.real.quartzjobs;

import java.io.File;
import java.io.FileFilter;

public class RegexFileFilter implements FileFilter{

	 private final String regex = "@";

		  public boolean accept(File file)
		  {
			  return file.getName().contains(regex) && file.getName().startsWith("1");

		  }
		  
}
