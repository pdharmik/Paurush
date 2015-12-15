package com.lexmark.service.impl.real.attachmentService;

import static com.lexmark.service.impl.real.attachmentService.AmindAttachmentServiceUtil.composeFilePath;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;

import com.lexmark.service.api.SiebelCrmServiceException;

import org.apache.logging.log4j.Logger;


public class RenameAttachmentUtil {

	//private static final Log logger = LogFactory.getLog(RenameAttachmentUtil.class);
	private static final Logger logger = LogManager.getLogger(RenameAttachmentUtil.class);
	
	public static boolean exceuteRenameFile(String fileDestination, String fileName, String renameFileName){
		
		File destPath = new File(composeFilePath(fileDestination, fileName));
		if(destPath.exists()) {
			File renameFilePath = new File(composeFilePath(fileDestination, renameFileName));
			if (destPath.renameTo(renameFilePath)) {
				return true;
			} else {
				logger.error("attachment file has not renamed!");
				throw new SiebelCrmServiceException("attachment file has not been renamed!");
			}
		} else {
			logger.error("File:" + destPath + " does not exist");
			throw new SiebelCrmServiceException("File not Found");
		}
	}
}
