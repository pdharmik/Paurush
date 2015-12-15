package com.lexmark.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import javax.portlet.PortletSession;

import org.apache.commons.logging.Log;

import java.util.logging.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CSV2AssetConverter {

	public static final boolean HAS_HEADER_LINE = true; 
	private static DateFormat destDateFormat = new SimpleDateFormat("yyyy/MM/dd");
	public static String ACTIVITY_HEADER_ONE = "#LXK UP Activity";
	public static String ACTIVITY_HEADER_TWO ="#Action.ActivityId,Action.ActionNarrative,Action.SRNum,Action.ActionSubStatus,Action.ActualEnd,Action.ActualFailureCode,Action.ActualStart,Action.AddlPaymentDesc1,Action.AddlPaymentDesc2,Action.AddlPaymentQty1,Action.AddlPaymentQty2,Action.AddlPaymentType1,Action.AddlPaymentType2,Action.AddlPaymentUnitPrice1,Action.AddlPaymentUnitPrice2,Action.AddlServiceReq,Action.Comments,Action.CustomerRequestResponse,Action.DeInstalledAssetTag,Action.DeInstalledPageCount,Action.EstTimeArrival,Action.InstalledAssetTag,Action.InstalledDeviceCondition,Action.InstalledIPAddress,Action.InstalledMacAddress,Action.InstalledPageCount,Action.NetworkConnected,Action.NewTechFirstName,Action.NewTechLastName,Action.NonLexmarkSuppliesUsed,Action.RecPartsDisp,Action.RecPartsErrCd1,Action.RecPartsErrCd2,Action.RecPartsNum,Action.RecPartsQty,Action.RecPartsRetCarrier,Action.RecPartsRetTrackNum,Action.RepairCompleteFlag,Action.ResolutionCode,Action.SPReferenceNum,Action.StatusAsOf,Action.SupplyManufacturer,Action.Technician,Action.TravelDistance,Action.TravelDistanceUM,Action.TravelDurationMin";
	public static String CLAIMS_HEADER_ONE = "#LXK UP Claims";
	public static String CLAIMS_HEADER_TWO = "#Claims.SRId,Claims.SRNum,Claims.ActivityId,Claims.AddlPaymentDesc1,Claims.AddlPaymentDesc2,Claims.AddlPaymentQty1,Claims.AddlPaymentQty2,Claims.AddlPaymentType1,Claims.AddlPaymentType2,Claims.AddlPaymentUnitPrice1,Claims.AddlPaymentUnitPrice2,Claims.AssetMTM,Claims.AssetProduct,Claims.AstSerialNumber,Claims.NewCustomerAccount,Claims.NewCustomerAddress,Claims.NewTechFirstName,Claims.NewTechLastName,Claims.PartnerAddress1,Claims.PartnerAddress2,Claims.PartnerAddress3,Claims.PartnerCity,Claims.PartnerCountry,Claims.PartnerName,Claims.PartnerOrg,Claims.PartnerPostal,Claims.PartnerProvince,Claims.PartnerRefNumber,Claims.PartnerSite,Claims.PartnerState,Claims.PrContactEmail,Claims.PrContactFN,Claims.PrContactLN,Claims.PrContactWorkPhone,Claims.PrinterCondition,Claims.ProblemCode,Claims.ProblemDetails,Claims.RecPartFlag1,Claims.RecPartFlag2,Claims.RecPartFlag3,Claims.RecPartFlag4,Claims.RecPartFlag5,Claims.RecPartNum1,Claims.RecPartNum2,Claims.RecPartNum3,Claims.RecPartNum4,Claims.RecPartNum5,Claims.RecPartsErrCd1Fo,Claims.RecPartsErrCd1Fr,Claims.RecPartsErrCd1Ft,Claims.RecPartsErrCd1Se,Claims.RecPartsErrCd1Th,Claims.RecPartsErrCd2Fo,Claims.RecPartsErrCd2Fr,Claims.RecPartsErrCd2Ft,Claims.RecPartsErrCd2Se,Claims.RecPartsErrCd2Th,Claims.RecPartsQty1,Claims.RecPartsQty2,Claims.RecPartsQty3,Claims.RecPartsQty4,Claims.RecPartsQty5,Claims.RecPartsRetCarrier1,Claims.RecPartsRetCarrier2,Claims.RecPartsRetCarrier3,Claims.RecPartsRetCarrier4,Claims.RecPartsRetCarrier5,Claims.RecPartsRetTrackNum1,Claims.RecPartsRetTrackNum2,Claims.RecPartsRetTrackNum3,Claims.RecPartsRetTrackNum4,Claims.RecPartsRetTrackNum5,Claims.RecPartsStatus1,Claims.RecPartsStatus2,Claims.RecPartsStatus3,Claims.RecPartsStatus4,Claims.RecPartsStatus5,Claims.RepairCompleteFlag,Claims.RepairDesc,Claims.ReqContactEmail,Claims.ReqContactFN,Claims.ReqContactLN,Claims.ReqContactWorkPhone,Claims.RequestReviewFlag,Claims.ResolutionCode,Claims.ReviewComments,Claims.ServiceAddress1,Claims.ServiceAddress2,Claims.ServiceAddress3,Claims.ServiceCity,Claims.ServiceCountry,Claims.ServicePostal,Claims.ServiceProvince,Claims.ServiceState,Claims.SrvcEndDate,Claims.SrvcStartDate,Claims.TechLoginName";
	public static String CLAIMS = "claims";
	public static String ACTIVITY = "activity";
	public static String UPLOAD_FILE_TYPE = "uploadFileType";
	public static final Logger logger = LogManager.getLogger("MyLogger");
	
	public static DateFormat getDestDateFormat() {
		return destDateFormat;
	}

	public static void setDestDateFormat(DateFormat _destDateFormat) {
		destDateFormat = _destDateFormat;
	}

	/**
	 *used for logic
	 * */
	// protected static final Log logger = LogFactory.getLog(AmindServiceTest.class);
	private CSVErrorHanlder errorHanlder = new CSVErrorHanlder();
	private BufferedReader reader;
	private Locale locale;
	private  ColumnInfo[] ActivityColumnInfos;
	private  ColumnInfo[] ClaimColumnInfos;
	private List<String[]> results = new ArrayList<String[]>();
	public List<String[]> getResults() {
		return results;
	}

	public CSV2AssetConverter(BufferedReader _reader, Locale _locale) {
		this.ActivityColumnInfos = ColumnInfo.getColumnInfosActivity();
		this.ClaimColumnInfos = ColumnInfo.getColumnInfosClaim();
		this.reader = _reader;
		locale = _locale;
	}

	public boolean processData(PortletSession session)  {
		String line;
		
			try {
			// Right now, we have to set HEADERLINE true,  the testers are using file with header 
			boolean headerLine = true;
			boolean firstHeaderLineValidation = false;
			boolean secondHeaderLineValidation = false;
			String fileType = "";
			int startIndex = 0;
			int endIndex = 0;
			int indexOfPartName1 = 0;;
			int indexOfPartName2 = 0;
			int indexOfPartName3 = 0;
			int indexOfPartName4 = 0;
			int indexOfPartName5 = 0;
			int indexOfQty1 = 0;
			int indexOfQty2 = 0;
			int indexOfQty3 = 0;
			int indexOfQty4 = 0;
			int indexOfQty5 = 0;
			int indeOfActivityId = 0;
			int indeOfPartName = 0;
			int indexOfQuantity = 0;
			int indexOfAstSerialNumber = 0;
			int indexOfAssetProduct = 0;
			int index = 0;
			int rowNum = 0;
			int emptyRows = 0;
			
			int claimRowCount = 0;
			int activityRowCount = 1;
			String[] cellArray = null;
			int cellArrayLength =0;
			int colLength=0;
			int indexOfSrvcEndDate = 0;
			int indexOfSrvcStartDate = 0;
			
			int indeOfActivityenddate = 0;
			int indeOfActivitystrdate = 0;
			int indexOfCustomerRequestResponse = 0;
			int indeOfEstTimeArrival = 0;
			int indeOfStatusAsOf = 0;
					
			
			String ClaimsRows = DownloadFileLocalizationUtil.getPropertyLocaleValue("Claim_Rows", locale);
			String activityRow = DownloadFileLocalizationUtil.getPropertyLocaleValue("Request_Rows", locale);
			int noOfClaimsRows = Integer.parseInt(ClaimsRows);
			logger.info("value of noOfClaimsRows::"+noOfClaimsRows);
			int noOfActivityRows = Integer.parseInt(activityRow);
			logger.info("value of noOfActivityRows::"+noOfActivityRows);
			String Name[] = new String[5];
			int Quantity[] = new int[5];
			String activityId[] = new String[100];
			String partName[] = new String[100];
			int quantity[] = new int[100];
			boolean partsClaimValidation = false;
	    	boolean partsActivityValidation = false;
	    	logger.info("locale"+locale);
	    	
	    		    	
			while ((line=reader.readLine()) != null) {
				
					
					rowNum++;
					logger.info("firstHeaderLineValidation:"+firstHeaderLineValidation);
						if(!firstHeaderLineValidation){
						logger.info("Program should come here only during header first line validation");
						//this is first line
						logger.info("line :::: "+line);
						if(line.indexOf(ColumnInfo.activityHeader)!=-1)
						{
							firstHeaderLineValidation = true;
							fileType = "activity";
							logger.info("File Type Is:"+fileType);
							//this is activity header
							//set the flag that this csv file is for activity
					
						}else if(line.indexOf(ColumnInfo.claimsHeader)!=-1){

							firstHeaderLineValidation = true;
							fileType = "claims";
							logger.info("File Type Is:"+fileType);
							//So this is claim header
							//set the flag that this csv file is for claim
						}else{
							
							addError(CSVErrorHanlder.MSG_KEY_INVALID_FILE_TYPE);
							logger.info("File Header is Invalid");
							return false;
						}
						session.removeAttribute(CSV2AssetConverter.UPLOAD_FILE_TYPE);
                        session.setAttribute(CSV2AssetConverter.UPLOAD_FILE_TYPE, fileType);
                        logger.info("Setting value in the session is "+fileType);
						
							//give flag that first header line validation completed.
						if(firstHeaderLineValidation)
							continue;
						}
				
										
				 logger.info("secondHeaderLineValidation value is "+secondHeaderLineValidation);
				 logger.info("Filetype value is "+fileType);
				 String headerInfo=line;
				 StringTokenizer stokenizer_1 = new StringTokenizer(headerInfo, "," );
				 logger.info("next line::"+line);
				 stokenizer_1.countTokens();
				 
				 if(!secondHeaderLineValidation){
					 
				 while(stokenizer_1.hasMoreTokens()){
					 stokenizer_1.nextToken();
					 colLength++;
					 
					 }
				 
				 logger.info("colLength"+colLength);
				 
				 if(fileType.equals("claims")){
						 if(colLength==this.ClaimColumnInfos.length){
							 cellArrayLength= this.ClaimColumnInfos.length;
						 }else if(colLength<this.ClaimColumnInfos.length){
							 addError(CSVErrorHanlder.MSG_KEY_TOO_FEW_COLUMNS);
								return false;
						 }
						 else if(colLength>this.ClaimColumnInfos.length){
							 addError(CSVErrorHanlder.MSG_KEY_TOO_MUCH_COLUMNS);
								return false;
							 
						 }
				 }
				 else{
					 if(colLength==this.ActivityColumnInfos.length){
						 cellArrayLength= this.ActivityColumnInfos.length;
					 }else if(colLength<this.ActivityColumnInfos.length){
						 addError(CSVErrorHanlder.MSG_KEY_TOO_FEW_COLUMNS);
							return false;
					 }
					 else if(colLength>this.ActivityColumnInfos.length){
						 addError(CSVErrorHanlder.MSG_KEY_TOO_MUCH_COLUMNS);
							return false;
						 
					 }
			 
				 }
			
				 }
				 
				  cellArray = new String[colLength];
				 
				  Pattern pat = Pattern.compile(",(\".+?\")");
					 Matcher matcher = pat.matcher(line);
				        StringBuffer sb = new StringBuffer();
				        while (matcher.find()){
				            matcher.appendReplacement(sb,","+matcher.group(1).replaceAll(",","&comm"));
				        }
				        matcher.appendTail(sb);
				        cellArray = sb.toString().split(",");
				        String cellArray2[] = new String[cellArrayLength];
				        
				    for(int i=0;i<cellArray.length;i++){
				    	//String out = cellArray[i]!=null ? cellArray[i].replaceAll("&comm",",") : "";
				    	if(cellArray[i]!=null&&cellArray[i].length()>0)
				    		cellArray2[i]  = cellArray[i]; 
				    	
				    	else
				    	cellArray2[i] ="";
				    					    	
				    }
				    logger.info("Length of cellArray2 is::"+cellArray2.length);
				  
				if(!secondHeaderLineValidation){
					
						for(int columnindex = 0;columnindex<cellArray.length;columnindex++)
						{
							if(cellArray[columnindex]==null){
								logger.info("cellArray[columnindex] is null:"+cellArray[columnindex]);
								logger.info("columnindex"+columnindex);
								addError(CSVErrorHanlder.MSG_KEY_Claims_HEADER_NULL);
		    					return false;
							}
								
						}
					//Find the index of Part name columns in Claims file
						if(fileType.equals("claims")){
							
							for(int l1=0;l1<cellArray.length;l1++){
								if(cellArray[l1].equals("Claims.SrvcEndDate"))
									indexOfSrvcEndDate = l1;
							}
							for(int l2=0;l2<cellArray.length;l2++){
								if(cellArray[l2].equals("Claims.SrvcStartDate"))
									indexOfSrvcStartDate = l2;
							}
							
						for(int k1=0;k1<cellArray.length;k1++){
							if(cellArray[k1].equals("Claims.AstSerialNumber"))
								indexOfAstSerialNumber = k1;
						}
											
						for(int k2=0;k2<cellArray.length;k2++){
							if(cellArray[k2].equals("Claims.AssetProduct"))
								indexOfAssetProduct = k2;
						}
						
						
						for(int i1=0;i1<cellArray.length;i1++){
							if(cellArray[i1].equals("Claims.RecPartNum1"))
								indexOfPartName1 = i1;
							
						}
						logger.info("indexOfPartName1++"+indexOfPartName1);
						
										
						for(int i2=0;i2<cellArray.length;i2++){
							
							if(cellArray[i2].equals("Claims.RecPartNum2"))
								indexOfPartName2 = i2;			
						}
						logger.info("indexOfPartName2++"+indexOfPartName2);
												
						for(int i3=0;i3<cellArray.length;i3++){
							if(cellArray[i3].equals("Claims.RecPartNum3"))
							indexOfPartName3 = i3;
						}
						logger.info("indexOfPartName3++"+indexOfPartName3);
												
						for(int i4=0;i4<cellArray.length;i4++){
						if(cellArray[i4].equals("Claims.RecPartNum4"))
								indexOfPartName4 = i4;
						}
															
						logger.info("indexOfPartName4++"+indexOfPartName4);
						for(int i5=0;i5<cellArray.length;i5++){
							 if(cellArray[i5].equals("Claims.RecPartNum5"))
								indexOfPartName5 = i5;
						}
						logger.info("indexOfPartName5++"+indexOfPartName5);
												
						for(int j1=0;j1<cellArray.length;j1++){
							if(cellArray[j1].equals("Claims.RecPartsQty1"))
								indexOfQty1 = j1;
								
							}
																		
							
						for(int j2=0;j2<cellArray.length;j2++){
							
							if(cellArray[j2].equals("Claims.RecPartsQty2"))
								indexOfQty2 = j2;
						}
												
						for(int j3=0;j3<cellArray.length;j3++){
							
							if(cellArray[j3].equals("Claims.RecPartsQty3"))
								indexOfQty3 = j3;
						
						}
						
						
						for(int j4=0;j4<cellArray.length;j4++){
							
							if(cellArray[j4].equals("Claims.RecPartsQty4"))
								indexOfQty4 = j4;
						}
						
						
						for(int j5=0;j5<cellArray.length;j5++){
							
							if(cellArray[j5].equals("Claims.RecPartsQty5"))
								indexOfQty5 = j5;
						}
						
					}
						//Finds the index of Activity Id,part name and quantity column
						else if(fileType.equals("activity")){
							

							for(int t1=0;t1<cellArray.length;t1++){
								if(cellArray[t1].equals("Action.CustomerRequestResponse")){
									indexOfCustomerRequestResponse = t1;
								}
							}

							for(int t2=0;t2<cellArray.length;t2++){
								if(cellArray[t2].equals("Action.EstTimeArrival")){
									indeOfEstTimeArrival = t2;
								}
							}

							for(int t3=0;t3<cellArray.length;t3++){
								if(cellArray[t3].equals("Action.StatusAsOf")){
									indeOfStatusAsOf = t3;
								}
							}
							
							for(int l1=0;l1<cellArray.length;l1++){
								if(cellArray[l1].equals("Action.ActualEnd")){
									indeOfActivityenddate = l1;
								}
							}
							
							for(int m1=0;m1<cellArray.length;m1++){
								if(cellArray[m1].equals("Action.ActualStart")){
									indeOfActivitystrdate = m1;
								}
							}
							logger.info("indeOfActivityId"+indeOfActivityId);
							for(int j1=0;j1<cellArray.length;j1++){
								if(cellArray[j1].equals("Action.RecPartsNum"))
									indeOfPartName = j1;
							}
							for(int k1=0;k1<cellArray.length;k1++){
								if(cellArray[k1].equals("Action.RecPartsQty"))
									indexOfQuantity = k1;
							}
						}
						
						boolean columnvalidation = processColumn(cellArray,fileType);//Here we are validating the name of the column header
						logger.info("columnvalidation value returning is "+columnvalidation);
						if(!columnvalidation){
							    return false;
						}
						else {
					    		secondHeaderLineValidation=true;
							    firstHeaderLineValidation=true;
							    continue;
							    }
					}
					logger.info("firstHeaderLineValidation+"+firstHeaderLineValidation );
					logger.info("secondHeaderLineValidation+"+secondHeaderLineValidation);
					
					
						
					 if(secondHeaderLineValidation && firstHeaderLineValidation ){
						 
						 
						 if(isArrayIsBlank(cellArray2)){
							 emptyRows++;
						 
                             continue;
						 }
						 
						 if(fileType.equals("claims")){
							 
														 
							    claimRowCount++;
								logger.info("No of Records::"+claimRowCount);
															 
															 
					 		 if(claimRowCount<=noOfClaimsRows){
					 			if((cellArray2[indexOfSrvcEndDate]!=null)&&(cellArray2[indexOfSrvcEndDate].length()>0)){
					 			  					 			 
					 			  if(!processDate(cellArray2, indexOfSrvcEndDate)){
					 				 addError(CSVErrorHanlder.MSG_KEY_DATE_FORMAT);
				    				 return false;
					 			  }
					 			}
					 			if((cellArray2[indexOfSrvcStartDate]!=null)&&(cellArray2[indexOfSrvcStartDate].length()>0)){
					 			
					 			  if(!processDate(cellArray2, indexOfSrvcStartDate)){
					 				 addError(CSVErrorHanlder.MSG_KEY_DATE_FORMAT);
				    				 return false;
					 			  }
					 			}
					 			 
					 			  if(cellArray2[indexOfPartName1]!=null){
										  Name[0] = cellArray2[indexOfPartName1];
										  	}
							    		else{
							    			Name[0] = null;
							    		}
							    							    		
							      if(cellArray2[indexOfPartName2]!=null){
										  Name[1] = cellArray2[indexOfPartName2];
										  
							    		}
							    		else{
							    			Name[1] = null;
							    			
							    		}
							    		
							    	if(cellArray2[indexOfPartName3]!=null){
										  Name[2] = cellArray2[indexOfPartName3];
										  
							    		}
							    		else{
							    			Name[2] = null;
							    			
							    		}
							    		
							    	if(cellArray2[indexOfPartName4]!=null){
										  Name[3] = cellArray2[indexOfPartName4];
										  
							    		}
							    		else{
							    			Name[3] = null;
							    			
							    		}
							    		
							    		if(cellArray2[indexOfPartName5]!=null){
										  Name[4] = cellArray2[indexOfPartName5];
										  
							    		}
							    		else{
							    			Name[4] = null;
							    			
							    		}
							    	      
										 										  
										  if(cellArray2[indexOfQty1]!=null){
										  if(cellArray2[indexOfQty1].toString().trim().length()==0){
											    Quantity[0] =0;
										  }
											    else{
											    	 Quantity[0] = Integer.parseInt(cellArray2[indexOfQty1]);
											    }
										  }
										  else
											  Quantity[0] = 0;
										  if(cellArray2[indexOfQty2]!=null){
										  if(cellArray2[indexOfQty2].toString().trim().length()==0){
											   Quantity[1] =0;
										  }
										  else{
										    	 Quantity[1] = Integer.parseInt(cellArray2[indexOfQty2]);
										    }
										  }
										  else
											  Quantity[1] = 0;
									  
										  if(cellArray2[indexOfQty3]!=null){
										  if(cellArray2[indexOfQty3].toString().trim().length()==0){
											   Quantity[2]=0;
										  }
										  else{
										    	 Quantity[2] = Integer.parseInt(cellArray2[indexOfQty3]);
										    }
									  
											  
										  }
										  else
											  Quantity[2] = 0;
											  
										  if(cellArray2[indexOfQty4]!=null){
										  if(cellArray2[indexOfQty4].toString().trim().length()==0){
											   Quantity[3] =0;
										  }
										  else{
										    	 Quantity[3] = Integer.parseInt(cellArray2[indexOfQty4]);
										    }
										  }
										  else
											  Quantity[3] = 0;
									  
										  if(cellArray2[indexOfQty5]!=null){
										  if(cellArray2[indexOfQty5].toString().trim().length()==0){
											   Quantity[4] =0;
										  }
										  else{
										    	 Quantity[4] = Integer.parseInt(cellArray2[indexOfQty5]);
										    }
										  }
										  else
											  Quantity[4] = 0;
									  
										
																					
										
										if(cellArray2[indexOfAstSerialNumber]==null || cellArray2[indexOfAstSerialNumber].trim().length()==0 ) 
										{	
											logger.info("Claims.AstSerialNumber column value is null in record number "+claimRowCount);
											addError(CSVErrorHanlder.MSG_KEY_Claims_AstSerialNumber_NULL);
					    					return false;
										}
										if(cellArray2[indexOfAssetProduct]==null || cellArray2[indexOfAssetProduct].trim().length()==0 ) 
										{	
											logger.info("Claims.AssetProduct column value is null in record number "+claimRowCount);
											addError(CSVErrorHanlder.MSG_KEY_Claims_AssetProduct_NULL);
					    					return false;
										}
										  partsClaimValidation = processPartsClaims(Name,Quantity,claimRowCount);
										  if (partsClaimValidation){
									    		secondHeaderLineValidation=true;
												 firstHeaderLineValidation=true;
												 //continue;
												 												 
									    		continue;
										 
										  }
										  else{
											  logger.info("No of parts is more than five for record no:: "+claimRowCount);
											 	addError(CSVErrorHanlder.MSG_KEY_PARTS_MORE_THAN_FIVE);
						    					return false;
											   }
							    	}
							    	else{
							    		logger.info("There are more than 50 records in the CSV file");
										addError(CSVErrorHanlder.MSG_KEY_TOO_MANY_ROWS);
										return false;
							    	}
					 		 }
							    	else if(fileType.equals("activity")){
							    		
							    		if((cellArray2[indexOfCustomerRequestResponse]!=null)&&(cellArray2[indexOfCustomerRequestResponse].length()>0)){
								    		
								 			  if(!processDate(cellArray2, indexOfCustomerRequestResponse)){
								 				  addError(CSVErrorHanlder.MSG_KEY_DATE_FORMAT);
							    				 return false;
								 			  }
								    		}
							    		if((cellArray2[indeOfEstTimeArrival]!=null)&&(cellArray2[indeOfEstTimeArrival].length()>0)){
								    		
								 			  if(!processDate(cellArray2, indeOfEstTimeArrival)){
								 				 addError(CSVErrorHanlder.MSG_KEY_DATE_FORMAT);
							    				 return false;
								 			  }
								    		}
							    		if((cellArray2[indeOfActivitystrdate]!=null)&&(cellArray2[indeOfActivitystrdate].length()>0)){
								    		
								 			  if(!processDate(cellArray2, indeOfActivitystrdate)){
								 				 addError(CSVErrorHanlder.MSG_KEY_DATE_FORMAT);
							    				 return false;
								 			  }
								    		}
							    		if((cellArray2[indeOfStatusAsOf]!=null)&&(cellArray2[indeOfStatusAsOf].length()>0)){
							    		
							 			  if(!processDate(cellArray2, indeOfStatusAsOf)){
							 				 addError(CSVErrorHanlder.MSG_KEY_DATE_FORMAT);
						    				 return false;
							 			  }
							    		}
							    		if((cellArray2[indeOfActivityenddate]!=null)&&(cellArray2[indeOfActivityenddate].length()>0)){ 
							 			 
							 			  if(!processDate(cellArray2, indeOfActivityenddate)){
							 				 addError(CSVErrorHanlder.MSG_KEY_DATE_FORMAT);
						    				 return false;
							 			  }
							    		}
							 			  
							    		
							    		
							    		
							    				
							    				logger.info("Value of cellArray2[indeOfActivityId]::"+cellArray2[indeOfActivityId]);
							    				
							    				if(cellArray2[indeOfActivityId]!=null&&cellArray2[indeOfActivityId].trim().length()>0){
							    					activityId[index] = cellArray2[indeOfActivityId];
							    				}
							    				else{
							    					logger.info("--Activity ID in row number  "+rowNum + " is null--");
							    											    					
							    					addError(CSVErrorHanlder.MSG_KEY_ACTIVITYID_NULL);
							    					return false;							    			
							    					}
							    				
							    					
							    				
							    				if(cellArray2[indeOfPartName]!=null &&cellArray2[indeOfPartName].trim().length()>0){
							    				partName[index] = cellArray2[indeOfPartName];
							    				}
							    				else
							    					partName[index] = null;
							    				 if(cellArray2[indexOfQuantity]!=null){
							    				if(cellArray2[indexOfQuantity].trim().length()==0){
							    					quantity[index]=0;	
							    				}else{
							    					quantity[index] = Integer.parseInt(cellArray2[indexOfQuantity]);
							    				}
							    				 }
							    				 else
							    					 quantity[index] = 0;
							    				 
							    				if(quantity[index]>5)
							    				{
							    					logger.info("No of Parts is more than 5 in Row number ::"+rowNum);
							    					addError(CSVErrorHanlder.MSG_KEY_PARTS_MORE_THAN_FIVE);
							    					return false;
							    				}
							    				secondHeaderLineValidation=true;
												 firstHeaderLineValidation=true;
												 index++;
												
												 logger.info("value of index"+index);
												 }
							    	
							    	continue;
							    			}
			}
											    				
			logger.info("rowNum"+rowNum);
			logger.info("emptyRows"+emptyRows);
			if(emptyRows==(rowNum-2)){
				addError(CSVErrorHanlder.MSG_KEY_EMPTY_FILE);
				return false;
			}
				
			if(rowNum<3){
						
				addError(CSVErrorHanlder.MSG_KEY_EMPTY_FILE);
				return false;
			}
			logger.info("file type:"+fileType);
			 if(fileType.equals("activity")){
				 logger.info("file type:"+fileType);
				 int rowCount = 0;
				for(int i=0;i<index-1;i++){
					
    				String st1 = activityId[i];
    				String st2 = activityId[i+1];
    				    				
    				if((st1.equals(st2))){
    					rowCount++;
    					int ct = i+1;
    					   					
    					if(ct==(index-1)){
    						
    						endIndex= index;
    						partsActivityValidation = processPartsActivity(quantity,partName,startIndex,endIndex);
    						if(!partsActivityValidation){
    							logger.info("No of parts for the Activity Id " +st1 + " is more than 5");
    							addError(CSVErrorHanlder.MSG_KEY_PARTS_MORE_THAN_FIVE);
    	    					return false;
    						}
    							
    					}
    					continue;
    				}
    				
    				
    				else{
    					activityRowCount++;
    					
    					endIndex = i+1;
    					logger.info("value of activityRowCount::"+activityRowCount);
    					if(activityRowCount<=noOfActivityRows){
    					partsActivityValidation = processPartsActivity(quantity,partName,startIndex,endIndex);
    					if(partsActivityValidation){
    						startIndex = i+1;
    						continue;
    					}
    					else{
    						logger.info("No Of parts for Activity Id "+activityId[i]+ "is more than 5");
						addError(CSVErrorHanlder.MSG_KEY_PARTS_MORE_THAN_FIVE);
						return false;
    						
    				}
    					}
    					
    					else{
    						logger.info("No of records for request File is ::"+activityRowCount);
    						logger.info("No Of records for this CSV file is more than 50");
    						addError(CSVErrorHanlder.MSG_KEY_TOO_MANY_ROWS);
    					return false;
    					}
    					
				}	
    				
				
				}
			}
			
				
    		
			
			
			}
				
			
				catch (IOException e) {
			logger.debug("In IOException");
			return false;
		}
					
		return true;
	}
	
	
				
	//Method for validating no of parts for Claims File
	
	private boolean processPartsClaims(String nameParts[],int quantityParts[],int claimRowCount){
		
	int totalParts = 0;
	int index = 0;
	int add;
	for(int i=0;i<5;i++){
		if(quantityParts[i]>5){
			addError(CSVErrorHanlder.MSG_KEY_PARTS_MORE_THAN_FIVE);
			return false;
		}
	}
	
	for(int i = 0;i<5;i++){
		
		String st = nameParts[i];
		totalParts = 0;
		if(nameParts[i]!=null){
		for(int j=0;j<5;j++){
		
			if(nameParts[j]!=null){
			if(st.equals(nameParts[j])){
				index = j;
				add = quantityParts[index];
				totalParts = totalParts + add;
								
			if(totalParts<=5){
				continue;
			}
			
			else if(totalParts>5) {
				logger.info("---No of parts for the product num "+nameParts[j]+" is "+totalParts+" in record no "+claimRowCount);
				addError(CSVErrorHanlder.MSG_KEY_PARTS_MORE_THAN_FIVE);
				return false;
			}
			}
		
		}
		else
			continue;
	}
		}
	else
		continue;
	
	}
	
	return true;
	}
	
	private boolean processPartsActivity(int quantity[],String partName[],int startIndex,int endIndex){
		
		int totalParts = 0;
		int index = 0;
		int add = 0;
				
		for(int i=startIndex;i<endIndex;i++){
			String st = partName[i];
			totalParts = 0;
			if(partName[i]!=null){
			for(int j=startIndex;j<endIndex;j++){
				if(partName[j]!=null){
				if(st.equals(partName[j])){
					index = j;
					add = quantity[index];
					totalParts = totalParts + add;
																	
				if((totalParts<=5)){
					continue;
				}
			
				else {
					logger.info("No of parts for part num "+partName[i]+ " is"+totalParts);
					return false;
					}
				}
			}
				else 
					continue;
			}
			}
			
			else {
						
				continue;
			}
			logger.info("No of parts for part num "+partName[i]+ " is"+totalParts);
			}
			
		
		
		return true;
		
		
		
	}
		
	//Method for calculating no of columns 
	
	
	// This method checks for each column existance
	private boolean processColumn(String[] cellArray,String fileType) {
		
		String st;
		boolean validate = false;
		String[] ActivityColumn = ACTIVITY_HEADER_TWO.split("[,]");
		String[] ClaimColumn = CLAIMS_HEADER_TWO.split("[,]");
			if(fileType.equals("activity")){
				int columnInfos = ActivityColumnInfos.length;
				boolean columnCheck = false;
			for(int i=0;i<columnInfos;i++){
			st = cellArray[i];
				logger.info("header column:"+st);
				
				for(int j=0;j<columnInfos;j++){
				 columnCheck = ActivityColumn[j].equals(st);
					if(columnCheck){
						validate=columnCheck;
						break;
					}
				}
					
				if(columnCheck)
					continue;
				else{
					addError(CSVErrorHanlder.MSG_KEY_MISSING_COLUMNS);
					logger.info("The Invalid column name is: "+st);
					logger.info("The column no is: "+i);
					logger.info("Header column validation not successful");
					validate = false;
					break;
				}
				
			}
			}
			if(fileType.equals("claims")){
				int columnInfos = ClaimColumnInfos.length;
				boolean columnCheck = false;
				int clumnNo = 0;
			for(int i=0;i<columnInfos;i++){
			st = cellArray[i];
				//logger.info("header column:"+st);
				
				for(int j=0;j<columnInfos;j++){
					clumnNo = j;
				 columnCheck = ClaimColumn[j].equals(st);
				if(columnCheck){
						validate=columnCheck;
						break;
					}
				}
					
				if(columnCheck)
					continue;
				else{
					addError(CSVErrorHanlder.MSG_KEY_MISSING_COLUMNS);
					logger.info("The column no is: "+(i+1));
					logger.info("The Invalid column name is: "+st);
					//Rlogger.info("The Valid column name Should be: "+ClaimColumn[clumnNo]);
					logger.info("Header column validation not successful");
					validate = false;
					break;
				}
				
			}
			}
		
		return validate;
		
	}
	
	
	 private boolean processDate(String[] cellArray,int indeOfdate)  {
				
		 
		if(cellArray[indeOfdate]!=null && cellArray[indeOfdate].trim().length()>0){
			 try{
			String strDate=cellArray[indeOfdate];
			 SimpleDateFormat dateFormat3 = new SimpleDateFormat(strDate);	
			 		Date testDate = new Date();
					testDate = dateFormat3.parse(strDate);
					if(dateFormat3.format(testDate).equals(strDate)){
						return true;
					}else
						return false;
				}
				catch(Exception e){
					logger.info("Exception ::"+e);
					return false;
				}
			
			

		}else{
			return true;
		}
		
	 }
	 public boolean isArrayIsBlank(String[] inputArray){
         boolean isArrayIsBlank=false;
         
         for(int i=0;i<inputArray.length;i++){
                 if(inputArray[i]==null){
                         isArrayIsBlank=true;
                         
                 }else if(inputArray[i].trim().length()==0){
                         isArrayIsBlank=true;
                 }        
                 else{
                         isArrayIsBlank=false;
                         break;
                 }
         }
         
         return isArrayIsBlank;
 }

			

		
	public void addError(String code) {
		errorHanlder.addError(code);
	}
	public ArrayList<CSVError> getErrors(){
		return errorHanlder.getErrors();
	}
}
