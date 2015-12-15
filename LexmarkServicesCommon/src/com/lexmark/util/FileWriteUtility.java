package com.lexmark.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ResourceBundle;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.ServiceRequestDTO;
import com.lexmark.service.impl.real.util.AttachmentProperties;

public class FileWriteUtility {

	private static Logger LOGGER = LogManager.getLogger(FileWriteUtility.class);
	private final static String CSVSEPERATOR = ",";
	private final static String LINESEPERATOR = "\n";
	private final static String APPENDCHAR="_";
	private final static String FILEXTENSION=".csv";

	private final static String ATTACHMENT_REQUEST_TYPE="Service Request";
	private final static String CSVFAILURE = "failure";
	private final static String DECOMMASSET = "DecommAsset";
	
	private static FileWriteUtility fileWriteInstance = null;

	// private static Properties props;

	private FileWriteUtility() {
	};

	// Creating a singleton instance of
	public static synchronized FileWriteUtility getInstance() {
		if (fileWriteInstance == null) {
			fileWriteInstance = new FileWriteUtility();
		}
		return fileWriteInstance;
	}

	/*
	 * CSV creation goes here for Manage Asset, Manage Address & Manage Contact
	 */

	public String createCSVFile(final ServiceRequestDTO serviceReqDTO,
			final String workSheetName){
		LOGGER.debug("Inside create CSV file ");
		LOGGER.info("Inside create CSV file ");
		// props =
		// PropsFileLoadUtility.getConfigurationFile("attachment.properties");
		//ResourceBundle bundle = ResourceBundle.getBundle("attachment");

		AttachmentProperties fileProperties = new AttachmentProperties(ATTACHMENT_REQUEST_TYPE);
		LOGGER.debug("File Upload dest is " + fileProperties.getFileUploadDestination());
		
		final String fileUploadDest= fileProperties.getFileUploadDestination();
		
		final String fileName = fileUploadDest + workSheetName + APPENDCHAR + System.currentTimeMillis()+ FILEXTENSION;
		
		LOGGER.debug("File name is " + fileName);
		final StringBuffer stringBuff = new StringBuffer();

		try {
			//final BufferedWriter bufferedWriter = new BufferedWriter(
				//	new FileWriter(fileName));
			
			OutputStream outputStream=new FileOutputStream((fileName));
			 outputStream.write(0xEF);   // 1st byte of BOM
				outputStream.write(0xBB);
				outputStream.write(0xBF);
			 OutputStreamWriter outputWriter=new OutputStreamWriter(outputStream, "UTF-8");
			 final BufferedWriter bufferedWriter = new BufferedWriter(outputWriter);
			

			for (int rowNum = 0; rowNum < 2; rowNum++) {
				LOGGER.debug("RowNum " + rowNum + "is for Headers ");

				writeAssetInfo(serviceReqDTO, rowNum, stringBuff);
				writeAddress(serviceReqDTO, rowNum, stringBuff);
				
				writeConsumablesAddressAndContact(serviceReqDTO, rowNum,stringBuff);
				
				if(serviceReqDTO.getContact()!=null){
					writeContact(serviceReqDTO, rowNum, stringBuff);
				}
				
				writePrimarySiteContact(serviceReqDTO, rowNum, stringBuff);

				stringBuff.append(LINESEPERATOR);
			}
			bufferedWriter.write(stringBuff.toString());
			bufferedWriter.flush();
			bufferedWriter.close();
			LOGGER.info("**** CSV Creation Done ****");
			// No errors go for the ftp
			/*
			 * if(filePath!=null) { doFtp(filePath); }
			 */

		} catch (Exception e) {
			e.printStackTrace();//This is for the time being- to be removed later
			LOGGER.info("**** CSV Creation Error ****",e);
			return CSVFAILURE;//This string is mandatory because checking done in the controller
		}

		return fileName;
	}

	private void writeInstalledAddrForAsset(ServiceRequestDTO serviceReqDTO,
			int rowNum, StringBuffer stringBuff, String assetReqType) {
		
		LOGGER.debug("This is a manage asset request, writing address");
		LOGGER.debug("------------ASSET REQUEST TYPE iS---------->>"+assetReqType);
		
	if(!DECOMMASSET.equals(assetReqType)){//For Decommission asset, I will not write installed address name
		if (rowNum == 0) {
			stringBuff.append("Asset Installed Address Name").append(CSVSEPERATOR);			
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getAddressName() + "\"")
					.append(CSVSEPERATOR);
		}
	}
	
		if (rowNum == 0) {
			
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up Address StoreFront Name").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed Address StoreFront Name").append(CSVSEPERATOR);
			}
		} else {
			LOGGER.info("Store Front Name ---------> "+serviceReqDTO.getAddress().getStoreFrontName());
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getStoreFrontName() + "\"")
					.append(CSVSEPERATOR);
		}
	
		if (rowNum == 0) {
			
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up Address Line1").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed Address Line1").append(CSVSEPERATOR);
			}
		} else {
			
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getAddressLine1() + "\"")
					.append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up Address Line2").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed Address Line2").append(CSVSEPERATOR);
			}
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getAddressLine2() + "\"")
					.append(CSVSEPERATOR);

		}
		
		if (rowNum == 0) {
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up City").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed City").append(CSVSEPERATOR);
			}
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getCity() + "\"").append(
					CSVSEPERATOR);
		}

		if (rowNum == 0) {
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up State").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed State").append(CSVSEPERATOR);
			}
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getState() + "\"")
					.append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up Province").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed Province").append(CSVSEPERATOR);
			}
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getProvince() + "\"")
					.append(CSVSEPERATOR);
		}
		// stringBuff.append(CSVSEPERATOR);

		if (rowNum == 0) {
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up Country").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed Country").append(CSVSEPERATOR);
			}
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getCountry() + "\"")
					.append(CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			if(DECOMMASSET.equals(assetReqType)){
				stringBuff.append("Pick Up Postal Code").append(CSVSEPERATOR);
			}else{
			stringBuff.append("Asset Installed Postal Code").append(CSVSEPERATOR);
			}
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getPostalCode() + "\"")
					.append(CSVSEPERATOR);
		}

		/*********Building, Floor and Office is not present for Decommission asset********/
	if(!DECOMMASSET.equals(assetReqType)){
		
		LOGGER.debug("In here I am writing the move type to the CSV file");
			
		if (rowNum == 0) {
			stringBuff.append("Building").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getPhysicalLocation1()
							+ "\"").append(CSVSEPERATOR);
		}	
		
		if (rowNum == 0) {
			stringBuff.append("Office").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getPhysicalLocation3()
							+ "\"").append(CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			stringBuff.append("Floor").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getPhysicalLocation2()
							+ "\"").append(CSVSEPERATOR);
		}
			
		
		
		if (serviceReqDTO.getInstallAssetFlag() != null) {
			if (rowNum == 0) {
				stringBuff.append("Install Asset Flag").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
					"\"" + serviceReqDTO.getInstallAssetFlag()
							+ "\"").append(CSVSEPERATOR);
			}	
		}
		
		//Done by Gaurav--START
		
		if (serviceReqDTO.getMoveType() != null) {
			if (rowNum == 0) {
				stringBuff.append("Move Type").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
					"\"" + serviceReqDTO.getMoveType()
							+ "\"").append(CSVSEPERATOR);
			}	
		}
		
		//Done by Gaurav--END
	}
}

	private void writeAddress(ServiceRequestDTO serviceReqDTO,
			int rowNum, StringBuffer stringBuff) {
		try{
		if (serviceReqDTO.getAddress() != null) {
			if (serviceReqDTO.getOldAddress() != null) {
				writeChangeAddressInfo(serviceReqDTO, rowNum,
						stringBuff);
			} else {
				if(serviceReqDTO.getAssetDetail()==null && serviceReqDTO.getAssetDetailForChange()==null){
				LOGGER.debug("This is a manage address req");
				writeCommonAddress(serviceReqDTO, rowNum, stringBuff);
				}
			}
		}
		/***************Must be removed later ************/
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private void writePrimarySiteContact(ServiceRequestDTO serviceReqDTO,
			final int rowNum, StringBuffer stringBuff) {
		if (serviceReqDTO.getPrimarySiteContact() != null) {
			if (rowNum == 0) {
				stringBuff.append("Site Contact Name").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getPrimarySiteContact()
										.getFirstName()).										
				append(" "+serviceReqDTO.getPrimarySiteContact()
						.getLastName()).append("\"").append(CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);
			/*if (rowNum == 0) {
				stringBuff.append("Site Contact Last Name").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getPrimarySiteContact()
										.getLastName() + "\"").append(
						CSVSEPERATOR);
			}*/
			// stringBuff.append(CSVSEPERATOR);
			if (rowNum == 0) {
				stringBuff.append("Site Contact Work Phone").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getPrimarySiteContact()
										.getWorkPhone() + "\"").append(
						CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);
			if (rowNum == 0) {
				stringBuff.append("Site Contact Email Address").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getPrimarySiteContact()
										.getEmailAddress() + "\"").append(
						CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);
			if (rowNum == 0) {
				stringBuff.append("Additional Information").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getAddtnDockDetails() + "\"").append(
						CSVSEPERATOR);
			}
		}
		
		/*if (serviceReqDTO.getSecondarySiteContact() != null) {
			if (rowNum == 0) {
				stringBuff.append("Secondary Site Contact First Name").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getSecondarySiteContact()
										.getFirstName() + "\"").append(
						CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);

			if (rowNum == 0) {
				stringBuff.append("Secondary Site Contact Last Name").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getSecondarySiteContact()
										.getLastName() + "\"").append(
						CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);

			if (rowNum == 0) {
				stringBuff.append("Secondary Site Contact Work Phone").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getSecondarySiteContact()
										.getWorkPhone() + "\"").append(
						CSVSEPERATOR);
			}
			stringBuff.append(CSVSEPERATOR).append(CSVSEPERATOR);

			if (rowNum == 0) {
				stringBuff.append("Secondary Site Contact Email Address")
						.append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getSecondarySiteContact()
										.getEmailAddress() + "\"").append(
						CSVSEPERATOR);
			}
		}*/

	}

	private void writeContact(ServiceRequestDTO serviceReqDTO, int rowNum,
			StringBuffer stringBuff) {
		try {
			if (serviceReqDTO.getOldContactData() != null) {
				if (rowNum == 0) {
					stringBuff.append("Selected Contact First Name").append(
							CSVSEPERATOR);
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getOldContactData()
											.getFirstName() + "\"").append(
							CSVSEPERATOR);
				}
				
				if(rowNum==0) {
					stringBuff.append("Selected Contact Middle Name").append(
							CSVSEPERATOR); 
				} else {
					stringBuff.append(
							"\""+serviceReqDTO
							.getOldContactData().getMiddleName()+"\"").append(
							CSVSEPERATOR); 
				}
				 
				if (rowNum == 0) {
					stringBuff.append("Selected Contact Last Name").append(
							CSVSEPERATOR);
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getOldContactData()
											.getLastName() + "\"").append(
							CSVSEPERATOR);
				}
				if (rowNum == 0) {
					stringBuff.append("Selected Contact Work Phone").append(
							CSVSEPERATOR);
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getOldContactData()
											.getWorkPhone() + "\"").append(
							CSVSEPERATOR);
				}

				if (rowNum == 0) {
					stringBuff.append("Selected Contact Alternate Phone")
							.append(CSVSEPERATOR);
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getOldContactData()
											.getAlternatePhone() + "\"")
							.append(CSVSEPERATOR);
				}

				if (rowNum == 0) {
					stringBuff.append("Selected Contact Email Address").append(
							CSVSEPERATOR);
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getOldContactData()
											.getEmailAddress() + "\"").append(
							CSVSEPERATOR);
				}

				if (serviceReqDTO.getContact() != null) {
					if (rowNum == 0) {
						stringBuff.append("New Contact Work Phone").append(
								CSVSEPERATOR);
					} else {
						stringBuff.append(
								"\""
										+ serviceReqDTO.getContact()
												.getWorkPhone() + "\"").append(
								CSVSEPERATOR);
					}

					if (rowNum == 0) {
						stringBuff.append("New Contact Alternate Phone")
								.append(CSVSEPERATOR);
					} else {
						stringBuff.append(
								"\""
										+ serviceReqDTO.getContact()
												.getAlternatePhone() + "\"")
								.append(CSVSEPERATOR);
					}

					if (rowNum == 0) {
						stringBuff.append("New Contact Email Address").append(
								CSVSEPERATOR);
					} else {
						stringBuff.append(
								"\""
										+ serviceReqDTO.getContact()
												.getEmailAddress() + "\"")
								.append(CSVSEPERATOR);
					}
				}
			}

			else {
				if (serviceReqDTO.getContact() != null) {
					// LOGGER.debug("inside if contact");
					if (rowNum == 0) {
						if(serviceReqDTO.getPageNameForContact().equals("removeContact")){
							
							stringBuff.append("Selected Contact First Name").append(
									CSVSEPERATOR);
							}
							else{
						stringBuff.append("Contact First Name").append(
								CSVSEPERATOR);
							}
					} else {
						stringBuff.append(
								"\""
										+ serviceReqDTO.getContact()
												.getFirstName() + "\"").append(
								CSVSEPERATOR);
					}

					if (rowNum == 0) {
						if(serviceReqDTO.getPageNameForContact().equals("removeContact")){
							
							stringBuff.append("Selected Contact Middle Name").append(
									CSVSEPERATOR);
							}
							else{
						stringBuff.append("Contact Middle Name").append(
								CSVSEPERATOR);
							}
					} else {
						stringBuff.append(
								"\""
										+ serviceReqDTO.getContact()
												.getMiddleName() + "\"")
								.append(CSVSEPERATOR);
					}

					if (rowNum == 0) {
						if(serviceReqDTO.getPageNameForContact().equals("removeContact")){
							
							stringBuff.append("Selected Contact Last Name").append(
									CSVSEPERATOR);
							}
							else{
						stringBuff.append("Contact Last Name").append(
								CSVSEPERATOR);
							}
					} else {
						stringBuff.append(
								"\"" + serviceReqDTO.getContact().getLastName()
										+ "\"").append(CSVSEPERATOR);
					}

					if (rowNum == 0) {
						if(serviceReqDTO.getPageNameForContact().equals("removeContact")){
							
							stringBuff.append("Selected Contact Work Phone").append(
									CSVSEPERATOR);
							}
							else{
						stringBuff.append("Contact Work Phone").append(
								CSVSEPERATOR);
							}
					} else {
						stringBuff.append(
								"\"'"
										+ serviceReqDTO.getContact()
												.getWorkPhone() + "'\"").append(
								CSVSEPERATOR);
					}

					if (rowNum == 0) {
						if(serviceReqDTO.getPageNameForContact().equals("removeContact")){
							
							stringBuff.append("Selected Contact Alternate Phone").append(
									CSVSEPERATOR);
							}
							else{
						stringBuff.append("Contact Alternate Phone").append(
								CSVSEPERATOR);
							}
					} else {
						stringBuff.append(
								"\"'"
										+ serviceReqDTO.getContact()
												.getAlternatePhone() + "'\"")
								.append(CSVSEPERATOR);
					}

					if (rowNum == 0) {
						if(serviceReqDTO.getPageNameForContact().equals("removeContact")){
							
							stringBuff.append("Selected Contact Email Address").append(
									CSVSEPERATOR);
							}
							else{
						stringBuff.append("Contact Email Address").append(
								CSVSEPERATOR);
							}
					} else {
						stringBuff.append(
								"\""
										+ serviceReqDTO.getContact()
												.getEmailAddress() + "\"")
								.append(CSVSEPERATOR);
					}
				}
			}
			
			if (serviceReqDTO.getContact().getAddress() != null) {
				
				if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
				if (rowNum == 0) {
					//if(serviceReqDTO.getPageNameForContact().equals("changeContact")){						
					stringBuff.append("New Contact Address StoreFront Name").append(
							CSVSEPERATOR);
					//}
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getContact().getAddress()
											.getStoreFrontName() + "\"").append(
							CSVSEPERATOR);
				}
				}
				
				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
						
					stringBuff.append("New Contact Address Line1").append(
							CSVSEPERATOR);
					}
					else{
						stringBuff.append("Contact Address Line1").append(
								CSVSEPERATOR);
					}
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getContact().getAddress()
											.getAddressLine1() + "\"").append(
							CSVSEPERATOR);
				}

				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
						
						stringBuff.append("New Contact Address Line2").append(
								CSVSEPERATOR);
						}
						else{
					stringBuff.append("Contact Address Line2").append(
							CSVSEPERATOR);
						}
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getContact().getAddress()
											.getAddressLine2() + "\"").append(
							CSVSEPERATOR);
				}

//				if (rowNum == 0) {
//					stringBuff.append("Contact Address Line3").append(
//							CSVSEPERATOR);
//				} else {
//					stringBuff.append(
//							"\""
//									+ serviceReqDTO.getContact().getAddress()
//											.getAddressLine3() + "\"").append(
//							CSVSEPERATOR);
//				}

				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
						
						stringBuff.append("New Contact City").append(
								CSVSEPERATOR);
						}
						else{
					stringBuff.append("Contact City").append(
							CSVSEPERATOR);
						}
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getContact().getAddress()
											.getCity() + "\"").append(
							CSVSEPERATOR);
				}

				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
						
						stringBuff.append("New Contact State").append(
								CSVSEPERATOR);
						}
						else{
					stringBuff.append("Contact State").append(
							CSVSEPERATOR);
						}
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getContact().getAddress()
											.getState() + "\"").append(
							CSVSEPERATOR);
				}

				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
						
						stringBuff.append("New Contact Province").append(
								CSVSEPERATOR);
						}
						else{
					stringBuff.append("Contact Province").append(
							CSVSEPERATOR);
						}
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getContact().getAddress()
											.getProvince() + "\"").append(
							CSVSEPERATOR);
				}
				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
						
						stringBuff.append("New Contact Country").append(
								CSVSEPERATOR);
						}
						else{
					stringBuff.append("Contact Country").append(
							CSVSEPERATOR);
						}
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getContact().getAddress()
									.getCountry() + "\"").append(
											CSVSEPERATOR);
				}

				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
						
						stringBuff.append("New Contact Postal Code").append(
								CSVSEPERATOR);
						}
						else{
					stringBuff.append("Contact Postal Code").append(
							CSVSEPERATOR);
						}
				} else {
					stringBuff.append(
							"\"'"
									+ serviceReqDTO.getContact().getAddress()
											.getPostalCode() + "'\"").append(
							CSVSEPERATOR);
				}


				if (rowNum == 0) {
						if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
						
						stringBuff.append("New Contact Building").append(
								CSVSEPERATOR);
						}
						else{
					stringBuff.append("Contact Building").append(
							CSVSEPERATOR);
						}
				} else {
					stringBuff.append(
							"\"'"
									+ serviceReqDTO.getContact().getAddress()
											.getPhysicalLocation1() + "'\"")
							.append(CSVSEPERATOR);
				}

				if (rowNum == 0) {
						if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
						
						stringBuff.append("New Contact Office").append(
								CSVSEPERATOR);
						}
						else{
					stringBuff.append("Contact Office").append(
							CSVSEPERATOR);
						}
				} else {
					stringBuff.append(
							"\"'"
									+ serviceReqDTO.getContact().getAddress()
									.getPhysicalLocation3() + "'\"")
									.append(CSVSEPERATOR);
				}
				
				if (rowNum == 0) {
						if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
						
						stringBuff.append("New Contact Floor").append(
								CSVSEPERATOR);
						}
						else{
					stringBuff.append("Contact Floor").append(
							CSVSEPERATOR);
						}
				} else {
					stringBuff.append(
							"\"'"
									+ serviceReqDTO.getContact().getAddress()
											.getPhysicalLocation2() + "'\"")
							.append(CSVSEPERATOR);
				}
				
				
				
				/* Changes MPS 2.1*/
				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
					
					stringBuff.append("New County").append(
							CSVSEPERATOR);
					}
					else{
				stringBuff.append("County").append(
						CSVSEPERATOR);
					}
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getContact().getAddress()
										.getCounty() + "\"")
						.append(CSVSEPERATOR);
			}
				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
					
					stringBuff.append("New Office Number").append(
							CSVSEPERATOR);
					}
					else{
				stringBuff.append("Office Number").append(
						CSVSEPERATOR);
					}
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getContact().getAddress()
										.getOfficeNumber() + "\"")
						.append(CSVSEPERATOR);
			}
				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
					
					stringBuff.append("New State Code").append(
							CSVSEPERATOR);
					}
					else{
				stringBuff.append("State Code").append(
						CSVSEPERATOR);
					}
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getContact().getAddress()
										.getStateCode() + "\"")
						.append(CSVSEPERATOR);
			}
				
				
				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
					
					stringBuff.append("New State Full Name").append(
							CSVSEPERATOR);
					}
					else{
				stringBuff.append("State Full Name").append(
						CSVSEPERATOR);
					}
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getContact().getAddress()
										.getStateFullName() + "\"")
						.append(CSVSEPERATOR);
			}
				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
					
					stringBuff.append("New District").append(
							CSVSEPERATOR);
					}
					else{
				stringBuff.append("District").append(
						CSVSEPERATOR);
					}
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getContact().getAddress()
										.getDistrict() + "\"")
						.append(CSVSEPERATOR);
			}
				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
					
					stringBuff.append("New Region").append(
							CSVSEPERATOR);
					}
					else{
				stringBuff.append("Region").append(
						CSVSEPERATOR);
					}
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getContact().getAddress()
										.getRegion() + "\"")
						.append(CSVSEPERATOR);
			}
				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
					
					stringBuff.append("New Latitude").append(
							CSVSEPERATOR);
					}
					else{
				stringBuff.append("Latitude").append(
						CSVSEPERATOR);
					}
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getContact().getAddress()
										.getLatitude() + "\"")
						.append(CSVSEPERATOR);
			}
				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
					
					stringBuff.append("New Longitude").append(
							CSVSEPERATOR);
					}
					else{
				stringBuff.append("Longitude").append(
						CSVSEPERATOR);
					}
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getContact().getAddress()
										.getLongitude() + "\"")
						.append(CSVSEPERATOR);
			}
				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
					
					stringBuff.append("New Country ISO Code").append(
							CSVSEPERATOR);
					}
					else{
				stringBuff.append("Country ISO Code").append(
						CSVSEPERATOR);
					}
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getContact().getAddress()
										.getCountryISOCode() + "\"")
						.append(CSVSEPERATOR);
			}
				if (rowNum == 0) {
					if(serviceReqDTO.getPageNameForContact().equals("changeContact")){
					
					stringBuff.append("New Saved Error Message").append(
							CSVSEPERATOR);
					}
					else{
				stringBuff.append("Saved Error Message").append(
						CSVSEPERATOR);
					}
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getContact().getAddress()
										.getSavedErrorMessage() + "\"")
						.append(CSVSEPERATOR);
			}
				
				
			/** Ends changes mps 2.1*/	
				
				

			}
		} catch (Exception ex) {//Must be removed
			ex.printStackTrace();
		}

	}

	private void writeConsumablesAddressAndContact(
			ServiceRequestDTO serviceReqDTO, int rowNum, StringBuffer stringBuff) {
		if (serviceReqDTO.getConsumablesAddress() != null) {
			
			/*Consumable contact does not have address name
			if (rowNum == 0) {
				stringBuff.append("Consumables Address Name").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getConsumablesAddress()
										.getAddressName() + "\"").append(
						CSVSEPERATOR);
			}*/
			// stringBuff.append(CSVSEPERATOR);

			if (serviceReqDTO.getConsumablesContact() != null) {
				
				if (rowNum == 0) {
					stringBuff.append("Consumables Contact First Name").append(
							CSVSEPERATOR);
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getConsumablesContact()
									.getFirstName() + "\"").append(
											CSVSEPERATOR);
				}
				// stringBuff.append(CSVSEPERATOR);
				if (rowNum == 0) {
					stringBuff.append("Consumables Contact Middle Name")
					.append(CSVSEPERATOR);
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getConsumablesContact()
									.getMiddleName() + "\"").append(
											CSVSEPERATOR);
				}
				// stringBuff.append(CSVSEPERATOR);
				if (rowNum == 0) {
					stringBuff.append("Consumables Contact Last Name").append(
							CSVSEPERATOR);
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getConsumablesContact()
									.getLastName() + "\"").append(
											CSVSEPERATOR);
				}
				// stringBuff.append(CSVSEPERATOR);
				if (rowNum == 0) {
					stringBuff.append("Consumables Contact Work Phone").append(
							CSVSEPERATOR);
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getConsumablesContact()
									.getWorkPhone() + "\"").append(
											CSVSEPERATOR);
				}
				// stringBuff.append(CSVSEPERATOR);
				if (rowNum == 0) {
					stringBuff.append("Consumables Contact Alternate Phone")
					.append(CSVSEPERATOR);
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getConsumablesContact()
									.getAlternatePhone() + "\"")
									.append(CSVSEPERATOR);
				}
				
				// stringBuff.append(CSVSEPERATOR);
				if (rowNum == 0) {
					stringBuff.append("Consumables Address Email Address")
					.append(CSVSEPERATOR);
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getConsumablesContact()
									.getEmailAddress() + "\"").append(
											CSVSEPERATOR);
				}
				// stringBuff.append(CSVSEPERATOR);
			}
			
			if (rowNum == 0) {
				stringBuff.append("Consumables Contact Address StoreFront Name").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getConsumablesAddress()
										.getStoreFrontName() + "\"").append(
						CSVSEPERATOR);
			}
			
			if (rowNum == 0) {
				stringBuff.append("Consumables Contact Address Line1").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getConsumablesAddress()
										.getAddressLine1() + "\"").append(
						CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);

			if (rowNum == 0) {
				stringBuff.append("Consumables Contact Address Line2").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getConsumablesAddress()
										.getAddressLine2() + "\"").append(
						CSVSEPERATOR);
			}
			
			if (rowNum == 0) {
				stringBuff.append("Consumables Contact City").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getConsumablesAddress().getCity()
								+ "\"").append(CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);

			if (rowNum == 0) {
				stringBuff.append("Consumables Contact State").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getConsumablesAddress().getState()
								+ "\"").append(CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);

			if (rowNum == 0) {
				stringBuff.append("Consumables Contact Province").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getConsumablesAddress()
										.getProvince() + "\"").append(
						CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);

			if (rowNum == 0) {
				stringBuff.append("Consumables Contact Country").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getConsumablesAddress()
								.getCountry() + "\"").append(
										CSVSEPERATOR);
			}
			
			if (rowNum == 0) {
				stringBuff.append("Consumables Contact Postal Code").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getConsumablesAddress()
										.getPostalCode() + "\"").append(
						CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);

			// stringBuff.append(CSVSEPERATOR);

			if (rowNum == 0) {
				stringBuff.append("Consumables Contact Building").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getConsumablesAddress()
										.getPhysicalLocation1() + "\"").append(
						CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);
			if (rowNum == 0) {
				stringBuff.append("Consumables Contact Office").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getConsumablesAddress()
								.getPhysicalLocation3() + "\"").append(
										CSVSEPERATOR);
			}

			if (rowNum == 0) {
				stringBuff.append("Consumables Contact Floor").append(
						CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getConsumablesAddress()
										.getPhysicalLocation2() + "\"").append(
						CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);

			// stringBuff.append(CSVSEPERATOR);


		}
		// return stringBuff;
	}

	private void writeAssetInfo(ServiceRequestDTO serviceReqDTO, int rowNum,
			StringBuffer stringBuff) {
		String assetReqType;
		
		/***********Below condition is for writing add asset information*********/
		if (serviceReqDTO.getAssetDetail() != null && serviceReqDTO.getAssetDetailForChange()==null
				&& serviceReqDTO.getAssetDetail().getPickupAddress()==null) {
			if (rowNum == 0) {
				//stringBuff.append("Serial No").append(CSVSEPERATOR);
				stringBuff.append("Serial Number").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getAssetDetail().getSerialNumber()
								+ "\"").append(CSVSEPERATOR);
			}

			// stringBuff.append(CSVSEPERATOR);

			if (rowNum == 0) {
				//stringBuff.append("Product Name").append(CSVSEPERATOR);
				stringBuff.append("Product").append(CSVSEPERATOR);
				
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getAssetDetail().getProductLine()
								+ "\"").append(CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);

			if (rowNum == 0) {
				stringBuff.append("Install Date").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + DateUtil.convertDateToGMTString(serviceReqDTO.getAssetDetail().getInstallDate())
								+ "\"").append(CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);

			if (rowNum == 0) {
				stringBuff.append("IP Address").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getAssetDetail().getIpAddress()
								+ "\"").append(CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);

			if (rowNum == 0) {
				stringBuff.append("Host Name").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getAssetDetail().getHostName()
								+ "\"").append(CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);

			if (rowNum == 0) {
				//stringBuff.append("Asset tag").append(CSVSEPERATOR);
				stringBuff.append("Customer Asset tag").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getAssetDetail().getDeviceTag() + "\"").append(CSVSEPERATOR);
			}	
			/* Commented out because customerAssetTag and deviceTag both are same
			 * + serviceReqDTO.getAssetDetail().getCustomerAssetTag() + "\"").append(CSVSEPERATOR);*/

			
			//This is done for change asset as no ltpc is involved, but for add its there
			if(serviceReqDTO.getAssetDetail().getLastPageCount()!=null)
			{			
			
				if (rowNum == 0) {
					//stringBuff.append("Last Page Count").append(CSVSEPERATOR);
					stringBuff.append("LTPC Count").append(CSVSEPERATOR);
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getAssetDetail()
											.getLastPageCount() + "\"").append(
							CSVSEPERATOR);
				}
			}
			
			//This is done for change asset as no ltpc is involved, but for add its there
			if(serviceReqDTO.getAssetDetail().getLastPageReadDate()!=null)
			{
				if (rowNum == 0) {
					//stringBuff.append("Last Page Date").append(CSVSEPERATOR);
					stringBuff.append("LTPC Date Time").append(CSVSEPERATOR);
				} else {
					stringBuff.append(
							"\""
									+ DateUtil.convertDateToGMTString(serviceReqDTO.getAssetDetail()
											.getLastPageReadDate()) + "\"").append(
													CSVSEPERATOR);
				}
			}
			
			//This is done for change asset as no ltpc is involved, but for add its there
			if(serviceReqDTO.getAssetDetail().getLastColorPageCount()!=null)
			{
				if (rowNum == 0) {
					//stringBuff.append("Last Color Page Count").append(CSVSEPERATOR);
					stringBuff.append("Color Page Count").append(CSVSEPERATOR);
				} else {
					stringBuff.append(
							"\""
									+ serviceReqDTO.getAssetDetail()
									.getLastColorPageCount() + "\"")
									.append(CSVSEPERATOR);
				}
			}
			
			//This is done for change asset as no ltpc is involved, but for add its there
			if(serviceReqDTO.getAssetDetail().getLastColorPageReadDate()!=null)
			{
				if (rowNum == 0) {
					//stringBuff.append("Last Color Page Read Date").append(CSVSEPERATOR);
					stringBuff.append("Color Date Time").append(CSVSEPERATOR);
					
				} else {
					stringBuff.append(
							"\""
									+ DateUtil.convertDateToGMTString(serviceReqDTO.getAssetDetail()
											.getLastColorPageReadDate()) + "\"")
											.append(CSVSEPERATOR);
				}
			}

			if (rowNum == 0) {
				stringBuff.append("Asset Cost Center").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getAssetDetail()
										.getAssetCostCenter() + "\"").append(
						CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);

			if (rowNum == 0) {
				//stringBuff.append("Chl Node Value").append(CSVSEPERATOR);
				stringBuff.append("Selected CHL Node").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getAssetDetail().getChlNodeValue()
								+ "\"").append(CSVSEPERATOR);
			}
			
			LOGGER.debug("This is an add asset request, so setting the type of req to add asset");
			assetReqType="AddAsset";
			writeInstalledAddrForAsset(serviceReqDTO, rowNum,stringBuff,assetReqType);
		}
		
		/**********Below condition is either for change asset or decommission asset**********/
		else if(serviceReqDTO.getAssetDetailForChange()!=null || serviceReqDTO.getAssetDetail()!=null)
		{
			//Old identifiers for change asset and decommission asset
			if (rowNum == 0) {
				//stringBuff.append("Serial No").append(CSVSEPERATOR);
				stringBuff.append("Selected Serial Number").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getAssetDetail().getSerialNumber()
								+ "\"").append(CSVSEPERATOR);
			}			

			if (rowNum == 0) {
				//stringBuff.append("Product Name").append(CSVSEPERATOR);
				stringBuff.append("Selected Product").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getAssetDetail().getProductTLI()
								+ "\"").append(CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);

			if (rowNum == 0) {
				//stringBuff.append("Install Date").append(CSVSEPERATOR);
				stringBuff.append("Selected Install Date").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getAssetDetail().getInstallDate()
								+ "\"").append(CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);
			if (rowNum == 0) {
				//stringBuff.append("Install Date").append(CSVSEPERATOR);
				stringBuff.append("Selected IP Address").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getAssetDetail().getIpAddress()
								+ "\"").append(CSVSEPERATOR);
			}
			if (rowNum == 0) {
				//stringBuff.append("Install Date").append(CSVSEPERATOR);
				stringBuff.append("Selected Host Name").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getAssetDetail().getHostName()
								+ "\"").append(CSVSEPERATOR);
			}
			
			if (rowNum == 0) {
				//stringBuff.append("Install Date").append(CSVSEPERATOR);
				stringBuff.append("Selected Customer Asset Tag").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getAssetDetail().getDeviceTag()
								+ "\"").append(CSVSEPERATOR);
			}
			/*********This is for decommission asset flag********/
			if (serviceReqDTO.getDecommAssetFlag() != null) {
				if (rowNum == 0) {
					stringBuff.append("Collect Asset Flag").append(CSVSEPERATOR);
				} else {
					stringBuff.append(
							"\"" + serviceReqDTO.getDecommAssetFlag()
							+ "\"").append(CSVSEPERATOR);
				}
				
				LOGGER.debug("This is an decommission asset request, so setting the type of req to add asset");
				assetReqType=DECOMMASSET;
				writeInstalledAddrForAsset(serviceReqDTO, rowNum,stringBuff,assetReqType);
			}
			
			
			/*********New Identifiers begin only for change asset **********/
			if(serviceReqDTO.getAssetDetailForChange()!=null){
			if (rowNum == 0) {
				stringBuff.append("New IP Address").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getAssetDetailForChange().getIpAddress()
								+ "\"").append(CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);

			if (rowNum == 0) {
				stringBuff.append("New Host Name").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getAssetDetailForChange().getHostName()
								+ "\"").append(CSVSEPERATOR);
			}
			// stringBuff.append(CSVSEPERATOR);

			if (rowNum == 0) {
				stringBuff.append("New Customer Asset tag").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getAssetDetailForChange().getDeviceTag() + "\"").append(CSVSEPERATOR);
			}
			
			if (rowNum == 0) {
				stringBuff.append("Selected Asset Cost Center").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getAssetDetailForChange()
										.getAssetCostCenter() + "\"").append(
						CSVSEPERATOR);
			}
			
			if (rowNum == 0) {
				stringBuff.append("Selected CHL Node").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getAssetDetailForChange()
										.getAssetCostCenter() + "\"").append(
						CSVSEPERATOR);
			}
			
			if (rowNum == 0) {
				stringBuff.append("New Asset Cost Center").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\""
								+ serviceReqDTO.getAssetDetailForChange()
										.getAssetCostCenter() + "\"").append(
						CSVSEPERATOR);
			}

			if (rowNum == 0) {
				stringBuff.append("New CHL Node").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getAssetDetailForChange().getChlNodeValue()
								+ "\"").append(CSVSEPERATOR);
			}
			
			LOGGER.debug("This is an change asset request, so setting the type of req to change asset");
			assetReqType="ChangeAsset";
			writeInstalledAddrForAsset(serviceReqDTO, rowNum,stringBuff,assetReqType);
		  }	
		}
	}

	/*private void writeAddress(ServiceRequestDTO serviceReqDTO, int rowNum,
			StringBuffer stringBuff) {
		try {

			if (serviceReqDTO.getAddress() != null) {
				if (serviceReqDTO.getOldAddress() != null) {
					writeChangeAddressInfo(serviceReqDTO, rowNum,
							stringBuff);
				} else {
					writeCommonAddress(serviceReqDTO, rowNum, stringBuff);
				}
			}
			
			 * else if(serviceReqDTO.getOldAddress()!=null) {
			 * writeAllAddressInfoForAddress(serviceReqDTO,rowNum, stringBuff);
			 * }
			 
			// else writeAddressForContact(serviceReqDTO,rowNum, stringBuff);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// return stringBuff;
	}*/

	private void writeChangeAddressInfo(ServiceRequestDTO serviceReqDTO,
			int rowNum, StringBuffer stringBuff) {

		// Writing the current Identifiers for Address
		if (rowNum == 0) {
			stringBuff.append("Selected Address Name").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getOldAddress().getAddressName()
							+ "\"").append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Selected StoreFront Name").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getOldAddress().getStoreFrontName()
							+ "\"").append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Selected Address Line1").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getOldAddress().getAddressLine1()
							+ "\"").append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Selected Address Line2").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getOldAddress().getAddressLine2()
							+ "\"").append(CSVSEPERATOR);

		}

//		if (rowNum == 0) {
//			stringBuff.append("Selected Address Line3").append(CSVSEPERATOR);
//		} else {
//			stringBuff.append(
//					"\"" + serviceReqDTO.getOldAddress().getAddressLine3()
//							+ "\"").append(CSVSEPERATOR);
//		}

		if (rowNum == 0) {
			stringBuff.append("Selected City").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getOldAddress().getCity() + "\"")
					.append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Selected State").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getOldAddress().getState()
							+ "\"").append(CSVSEPERATOR);
		}
		if (rowNum == 0) {
			stringBuff.append("Selected Province").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getOldAddress().getProvince()
							+ "\"").append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Selected Country").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getOldAddress().getCountry() + "\"")
					.append(CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			stringBuff.append("Selected Postal Code").append(CSVSEPERATOR);
		} else {
			stringBuff
					.append("\""
							+ serviceReqDTO.getOldAddress().getPostalCode()
							+ "\"").append(CSVSEPERATOR);
		}
		
		
		if (rowNum == 0) {
			stringBuff.append("Selected County").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getCounty()
							+ "\"").append(CSVSEPERATOR);
				}

		
		if (rowNum == 0) {
			stringBuff.append("Selected Office Number").append(CSVSEPERATOR);
		}else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getOfficeNumber()
							+ "\"").append(CSVSEPERATOR);
				}
	
		
		if (rowNum == 0) {
			stringBuff.append("Selected State Code").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getStateCode()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Selected State Full Name").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getStateFullName()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Selected District").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getDistrict()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Selected Region").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getRegion()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Selected Latitude").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getLatitude()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Selected Longitude").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getLongitude()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Selected Country ISO Code").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getCountryISOCode()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Selected Saved Error Message").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getSavedErrorMessage()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
				stringBuff.append("Selected Is Address Cleansed").append(CSVSEPERATOR);
		} else {
				stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getIsAddressCleansed()
							+ "\"").append(CSVSEPERATOR);
		}
		
		
		
		
		
		
		
		
		
		
		
		// Writing the new Identifiers for Address

		if (rowNum == 0) {
			stringBuff.append("Updated Address Name").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getAddressName() + "\"")
					.append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Updated StoreFront Name").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getStoreFrontName()
							+ "\"").append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Updated Address Line1").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getAddressLine1() + "\"")
					.append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Updated Address Line2").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getAddressLine2() + "\"")
					.append(CSVSEPERATOR);

		}

//		if (rowNum == 0) {
//			stringBuff.append("Updated Address Line3").append(CSVSEPERATOR);
//		} else {
//			stringBuff.append(
//					"\"" + serviceReqDTO.getAddress().getAddressLine3() + "\"")
//					.append(CSVSEPERATOR);
//		}

		if (rowNum == 0) {
			stringBuff.append("Updated City").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getCity() + "\"").append(
					CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Updated State").append(CSVSEPERATOR);
		} else {
			stringBuff
					.append("\""
							+ serviceReqDTO.getAddress().getState()
							+ "\"").append(CSVSEPERATOR);
		}
		if (rowNum == 0) {
			stringBuff.append("Updated Province").append(CSVSEPERATOR);
		} else {
			stringBuff
					.append("\""
							+ serviceReqDTO.getAddress().getProvince()
							+ "\"").append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Updated Country").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getCountry() + "\"")
					.append(CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			stringBuff.append("Updated Postal Code").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getPostalCode() + "\"")
					.append(CSVSEPERATOR);
		}
		
		
		if (rowNum == 0) {
			stringBuff.append("Updated County").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getCounty()
							+ "\"").append(CSVSEPERATOR);
				}

		
		if (rowNum == 0) {
			stringBuff.append("Updated Office Number").append(CSVSEPERATOR);
		}else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getOfficeNumber()
							+ "\"").append(CSVSEPERATOR);
				}
	
		
		if (rowNum == 0) {
			stringBuff.append("Updated State Code").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getStateCode()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Updated State Full Name").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getStateFullName()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Updated District").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getDistrict()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Updated Region").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getRegion()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Updated Latitude").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getLatitude()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Updated Longitude").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getLongitude()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Updated Country ISO Code").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getCountryISOCode()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Updated Saved Error Message").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getSavedErrorMessage()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
				stringBuff.append("Updated Is Address Cleansed").append(CSVSEPERATOR);
		} else {
				stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getIsAddressCleansed()
							+ "\"").append(CSVSEPERATOR);
		}

	}

	private void writeCommonAddress(ServiceRequestDTO serviceReqDTO,
			int rowNum, StringBuffer stringBuff) {
		if (rowNum == 0) {
			stringBuff.append("Address Name").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getAddressName() + "\"")
					.append(CSVSEPERATOR);
		}

		// This applies only for Add/Remove address
		if (serviceReqDTO.getAddress().getStoreFrontName() != null) {
			if (rowNum == 0) {
				stringBuff.append("StoreFront Name").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getAddress().getStoreFrontName()
								+ "\"").append(CSVSEPERATOR);
			}
		}

		if (rowNum == 0) {
			stringBuff.append("Address Line1").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getAddressLine1() + "\"")
					.append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Address Line2").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getAddressLine2() + "\"")
					.append(CSVSEPERATOR);

		}

//		if (rowNum == 0) {
//			stringBuff.append("Address Line3").append(CSVSEPERATOR);
//		} else {
//			stringBuff.append(
//					"\"" + serviceReqDTO.getAddress().getAddressLine3() + "\"")
//					.append(CSVSEPERATOR);
//		}

		if (rowNum == 0) {
			stringBuff.append("City").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getCity() + "\"").append(
					CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("State").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getState() + "\"")
					.append(CSVSEPERATOR);
		}

		if (rowNum == 0) {
			stringBuff.append("Province").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getProvince() + "\"")
					.append(CSVSEPERATOR);
		}
		// stringBuff.append(CSVSEPERATOR);

		if (rowNum == 0) {
			stringBuff.append("Country").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getCountry() + "\"")
					.append(CSVSEPERATOR);
		}
		
		if (rowNum == 0) {
			stringBuff.append("Postal Code").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"'" + serviceReqDTO.getAddress().getPostalCode() + "'\"")
					.append(CSVSEPERATOR);
		}

		//Building, Floor and Office won't be there for add address
		if(serviceReqDTO.getAddress().getPhysicalLocation1()!=null){
			if (rowNum == 0) {
			stringBuff.append("Building").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getPhysicalLocation1()
							+ "\"").append(CSVSEPERATOR);
				}
		}
		if(serviceReqDTO.getAddress().getPhysicalLocation3()!=null){
			if (rowNum == 0) {
				stringBuff.append("Office").append(CSVSEPERATOR);
			} else {
				stringBuff.append(
						"\"" + serviceReqDTO.getAddress().getPhysicalLocation3()
						+ "\"").append(CSVSEPERATOR);
			}
		}
		
		if(serviceReqDTO.getAddress().getPhysicalLocation2()!=null){
			if (rowNum == 0) {
			stringBuff.append("Floor").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getPhysicalLocation2()
							+ "\"").append(CSVSEPERATOR);
				}
		}
		/* Changes MPS 2.1*/
		if (rowNum == 0) {
			stringBuff.append("County").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getCounty()
							+ "\"").append(CSVSEPERATOR);
				}

		
		if (rowNum == 0) {
			//Changed for CI Defect # 8351
			stringBuff.append("House Number").append(CSVSEPERATOR);
		}else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getOfficeNumber()
							+ "\"").append(CSVSEPERATOR);
				}
	
		
		if (rowNum == 0) {
			stringBuff.append("State Code").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getStateCode()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("State Full Name").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getStateFullName()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("District").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getDistrict()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Region").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getRegion()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Latitude").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getLatitude()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Longitude").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getLongitude()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Country ISO Code").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getCountryISOCode()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
			stringBuff.append("Saved Error Message").append(CSVSEPERATOR);
		} else {
			stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getSavedErrorMessage()
							+ "\"").append(CSVSEPERATOR);
				}
		
		
		if (rowNum == 0) {
				stringBuff.append("Is Address Cleansed").append(CSVSEPERATOR);
		} else {
				stringBuff.append(
					"\"" + serviceReqDTO.getAddress().getIsAddressCleansed()
							+ "\"").append(CSVSEPERATOR);
		}
	
		/* Ends changes MPS 2.1*/
		
	}
}
