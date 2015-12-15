package com.lexmark.form.source.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.print.DocFlavor.STRING;

import org.apache.commons.discovery.tools.SPInterface;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.form.source.ErrorContainer;
import com.lexmark.form.source.OrderDetailForm; 
import com.lexmark.form.source.ProcessedPartGridError;


/**
 * This class is used to validate the common fields
 * 
 * @author Wipro Offshore Team
 */
@SuppressWarnings(value = { "unchecked" })
// @SessionAttributes(value={"errorMap"})
public class ServiceOrderValidator implements Validator {

private List<ErrorContainer> errorList;
private List<String> quantity;
private List<String> partNumber;
private List<String> shipQuantity;
private List<String> carrier;
private List<String> tracking;
private List<String> actualShippedDate;
private List<String> backOrderedQty;
private List<String> actualBackOrderedQtyList;
private List<String> ETA;
private List<ProcessedPartGridError> processedGridError;
private List<String> status;
private List<String> deliveryDate;
private List<String> processedPart;
private List<String> processed_TrackingList;
private List<String> repeatedPartNumber;
private static final String NUMBERPATTERN="^[0-9]{1,20}$";
private static final String DATEPATTERN="(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)";
	private static final Logger LOGGER = LogManager
			.getLogger(ServiceOrderValidator.class);

	public boolean supports(Class<?> type) {

		if ("orderDetailForm".equalsIgnoreCase(type.getSimpleName()))
			return true;
		return OrderDetailForm.class.equals(type);

		
	}
		private void putDataInList(List<String> list,String data)
		{
			StringTokenizer st=new StringTokenizer(data, ",");
			while(st.hasMoreTokens())
				{
					list.add(st.nextToken());
				}
		}
		public List<ErrorContainer> getErrorList()
		{
			return errorList;
		}
		public List<ProcessedPartGridError> processedGridError()
		{
			return processedGridError;
		}
		public void validate(Object obj, Errors errors) {
			
			if(obj instanceof OrderDetailForm)
			{
				
				OrderDetailForm orderDetailForm=(OrderDetailForm) obj;
				processedGridError=new ArrayList<ProcessedPartGridError>();
				quantity=new ArrayList<String>();
				partNumber=new ArrayList<String>();
				processed_TrackingList=new ArrayList<String>();
				shipQuantity=new ArrayList<String>();
				carrier=new ArrayList<String>();
				tracking=new ArrayList<String>();
				actualShippedDate=new ArrayList<String>();
				backOrderedQty=new ArrayList<String>();
				actualBackOrderedQtyList=new ArrayList<String>();
				ETA=new ArrayList<String>();
				errorList=new ArrayList<ErrorContainer>();
				status=new ArrayList<String>();
				deliveryDate=new ArrayList<String>();
				processedPart=new ArrayList<String>();
				
				putDataInList(quantity,orderDetailForm.getQuantityList());
				putDataInList(partNumber,orderDetailForm.getPartNumberList());
				putDataInList(shipQuantity,orderDetailForm.getShipQuantityList());
				putDataInList(carrier,orderDetailForm.getCarrierList());
				putDataInList(tracking,orderDetailForm.getTrackingList());
				putDataInList(actualShippedDate,orderDetailForm.getActualShippedDateList());				
				putDataInList(backOrderedQty,orderDetailForm.getBackOrderedQtyList());
				putDataInList(actualBackOrderedQtyList,orderDetailForm.getActualBackOrderedQtyList());
				putDataInList(ETA,orderDetailForm.getETAList());
				
				
				putDataInList(processed_TrackingList, orderDetailForm.getProcessedTrackingList());
				putDataInList(status,orderDetailForm.getStatusList());
				putDataInList(deliveryDate,orderDetailForm.getDeliveryDateList());
				putDataInList(processedPart,orderDetailForm.getProcessedPartList());
				//validation for processed grid
				for(int i=0;i<deliveryDate.size();i++)
				{
					if(!deliveryDate.get(i).equals("empty"))
					{
						/*if(!deliveryDate.get(i).trim().matches(DATEPATTERN))
						{
							processedGridError.add(new ProcessedPartGridError(processed_TrackingList.get(i), "requestInfo.validation.source.deliveredDate.format.errorMsg"));
							//errors.reject(processed_TrackingList.get(i)+"validation.source.deliveredDate.format.errorMsg");
						}*/
					}
				}
				//end
				
				//getting  repeated part number
				repeatedPartNumber=new ArrayList<String>();
				String checked_partNumber="";
				for(int i=0;i<partNumber.size();i++)
				{
					for(int j=i+1;j<partNumber.size();j++)
					{
						if(partNumber.get(i) == null || partNumber.get(i) == "")
						{
							break;
						}else{
								if(!partNumber.get(i).equals(checked_partNumber))
								{
									if(partNumber.get(i).equals(partNumber.get(j)))
									{
										repeatedPartNumber.add(partNumber.get(j));
										checked_partNumber=partNumber.get(j);
										break;
									}
								}
						}
					}
				}
				
				//end		
				//validations for repeated part Number
				
				if(repeatedPartNumber.size()>0)
				{
					List<Integer>rowId;
					for(int i=0;i<repeatedPartNumber.size();i++)
					{
						rowId=new ArrayList<Integer>();
						for(int j=0;j<partNumber.size();j++)
						{
							if(repeatedPartNumber.get(i).equals(partNumber.get(j)))
							{
								rowId.add(j);
							}
						}
						long totalShippedQty=0;
						long totalQty=0;
						long backQty=0;
						long actualBackQty=0;
						
						if((!backOrderedQty.get(rowId.get(i)).equals("empty")&&!backOrderedQty.get(rowId.get(i)).equals("doesnotExist"))&&backOrderedQty.get(rowId.get(i)).trim().matches(NUMBERPATTERN))
						{
							backQty=Long.parseLong(backOrderedQty.get(rowId.get(i)).trim());
						}
						
						if((!actualBackOrderedQtyList.get(rowId.get(i)).equals("empty")&&!actualBackOrderedQtyList.get(rowId.get(i)).equals("doesnotExist"))&&actualBackOrderedQtyList.get(rowId.get(i)).trim().matches(NUMBERPATTERN))
						{
							actualBackQty=Long.parseLong(actualBackOrderedQtyList.get(rowId.get(i)).trim());
						}
						
						for(int k=0;k<rowId.size();k++)
						{
							
							//checking format error for ship qty
							if(!shipQuantity.get(rowId.get(k)).equals("empty")&&!shipQuantity.get(rowId.get(k)).trim().matches(NUMBERPATTERN))
							{
								errorList.add(new ErrorContainer(repeatedPartNumber.get(i),"requestInfo.validation.source.shipQty.format.errorMsg", k+1));
							}
							/*if(!actualShippedDate.get(rowId.get(k)).equals("empty")&&!actualShippedDate.get(rowId.get(k)).trim().matches(DATEPATTERN))
							{
								errorList.add(new ErrorContainer(repeatedPartNumber.get(i),"requestInfo.validation.source.actualShippeddate.format.errorMsg", k+1));
							}*/
							//end
							
							//checking mandetory fields
							if((!shipQuantity.get(rowId.get(k)).equals("empty")&&shipQuantity.get(k).trim().matches(NUMBERPATTERN))&& Long.parseLong(shipQuantity.get(rowId.get(k)))>0)
							{
								
								if(carrier.get(rowId.get(k)).equals("empty"))
								{
									errorList.add(new ErrorContainer(repeatedPartNumber.get(i),"requestInfo.validation.source.carrierEmpty.errorMsg", k+1));
								}
								if(tracking.get(rowId.get(k)).equals("empty"))
								{
									errorList.add(new ErrorContainer(repeatedPartNumber.get(i),"requestInfo.validation.source.trackingNumberEmpty.errorMsg", k+1));
								}
								if(actualShippedDate.get(rowId.get(k)).equals("empty"))
								{
									errorList.add(new ErrorContainer(repeatedPartNumber.get(i),"requestInfo.validation.source.actualShippedDateEmpty.errorMsg", k+1));
								}
								//end
							
							}
							// tracking number repetation validation
							if(!tracking.get(rowId.get(k)).equals("empty"))
							{
								for(int m=k+1;m<rowId.size();m++)
								{
									if(tracking.get(rowId.get(k)).equals(tracking.get(rowId.get(m))))
									{
										errors.reject(repeatedPartNumber.get(i),"requestInfo.validation.source.trackingNum.errorMsg");
										break;
									}
								}
							}
							//end
							// counting total qty
							try{
								long shipQty=0;
								
								if(!shipQuantity.get(rowId.get(k)).equals("empty")&&shipQuantity.get(rowId.get(k)).trim().matches(NUMBERPATTERN))
								{
									shipQty=Long.parseLong(shipQuantity.get(rowId.get(k)).trim());
								}
								
								totalShippedQty=totalShippedQty+shipQty;	
							}
							catch(Exception exception){
								LOGGER.debug("inside catch");
							}	
							
						}
						
						totalQty = Long.parseLong(quantity.get(rowId.get(0))) + (actualBackQty - backQty);
						
						if(totalShippedQty>totalQty)
						{
							errors.reject(repeatedPartNumber.get(i),"requestInfo.validation.source.shipQty.errorMsg");
						}
						
						
					}
				}
				//end 	
				
				//validations single part number
				for(int i=0;i<partNumber.size();i++)
				{
					boolean repeatedPartNumberFlag=false;
					long shipQty=0;
					long backQty=0;
					long actualBackQty=0;
					long totalQuantity=0;
					
					//checking if total ship qty is more than qty
					for(int j=0;j<repeatedPartNumber.size();j++)
					{
						if(partNumber.get(i).equals(repeatedPartNumber.get(j)))
						{
							repeatedPartNumberFlag=true;
							break;
						}
					}
					if(!repeatedPartNumberFlag){
						if(!shipQuantity.get(i).equals("empty")&&shipQuantity.get(i).trim().matches(NUMBERPATTERN))
						{
							shipQty=Long.parseLong(shipQuantity.get(i));
						}
						
						if((!backOrderedQty.get(i).equals("empty")&&!backOrderedQty.get(i).equals("doesnotExist"))&&backOrderedQty.get(i).trim().matches(NUMBERPATTERN))
						{
							backQty=Long.parseLong(backOrderedQty.get(i));
						}
						
						if((!actualBackOrderedQtyList.get(i).equals("empty")&&!actualBackOrderedQtyList.get(i).equals("doesnotExist"))&&actualBackOrderedQtyList.get(i).trim().matches(NUMBERPATTERN))
						{
							actualBackQty=Long.parseLong(actualBackOrderedQtyList.get(i));
						}
						
						totalQuantity=Long.parseLong(quantity.get(i))+(actualBackQty - backQty);
						
						if(shipQty>totalQuantity)
						{					
								errors.reject(partNumber.get(i),"requestInfo.validation.source.shipQty.errorMsg");							
						}
					}
					//end
					
					
					if(!repeatedPartNumberFlag)
					{
						if(!shipQuantity.get(i).equals("empty"))
						{
							if(!shipQuantity.get(i).trim().matches(NUMBERPATTERN))
								errors.reject(partNumber.get(i),"requestInfo.validation.source.shipQty.format.errorMsg");
						}
						/*if(!actualShippedDate.get(i).equals("empty"))
						{
							if(!actualShippedDate.get(i).trim().matches(DATEPATTERN))
								errors.reject(partNumber.get(i),"requestInfo.validation.source.actualShippeddate.format.errorMsg");
						}*/
					}
				
					if(!backOrderedQty.get(i).equals("empty")&&!backOrderedQty.get(i).equals("doesnotExist"))
					{
						if(!backOrderedQty.get(i).trim().matches(NUMBERPATTERN))
							errors.reject(partNumber.get(i),"requestInfo.validation.source.backOrderQty.format.errorMsg");
					}
					if(!ETA.get(i).equals("empty")&&!ETA.get(i).equals("doesnotExist"))
					{
						/*if(!ETA.get(i).trim().matches(DATEPATTERN))
						{
							
							errors.reject(partNumber.get(i),"requestInfo.validation.source.etadate.format.errorMsg");
						}*/
					}
					
					//	Checking mandatory fields for ship qty					
					
					if(!repeatedPartNumberFlag)
					{
						if((!shipQuantity.get(i).equals("empty")&&shipQuantity.get(i).trim().matches(NUMBERPATTERN))&&Long.parseLong(shipQuantity.get(i))>0)
						{
							if(carrier.get(i).equals("empty"))
								errors.reject(partNumber.get(i),"requestInfo.validation.source.carrierEmpty.errorMsg");
							if(tracking.get(i).equals("empty"))
								errors.reject(partNumber.get(i),"requestInfo.validation.source.trackingNumberEmpty.errorMsg");
							if(actualShippedDate.get(i).equals("empty"))
								errors.reject(partNumber.get(i),"requestInfo.validation.source.actualShippedDateEmpty.errorMsg");
							
								
						}
					}
					//end
					
					//checking mandatory fields for back order qty
					if(((!backOrderedQty.get(i).equals("empty")&&!backOrderedQty.get(i).equals("doesnotExist"))&&backOrderedQty.get(i).trim().matches(NUMBERPATTERN))&&Long.parseLong(backOrderedQty.get(i))>0)
						{
							/*if(ETA.get(i).equals("empty"))
							{
								errors.reject(partNumber.get(i),"validation.source.etaEmpty.errorMsg");
								
							}*/
						}
					//end
				}

				
	
				
			}
		}
		
	}