<%@ taglib prefix="html" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="util" uri="/WEB-INF/tld/lexmark-util.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="<html:rootPath/>css/in-styles.css?version=<html:fileCacheVersion/>" />
<div class="infoBox columnInner rounded shadow">

              <h4><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.heading"/></h4>
              <p class="inlineTitle inlineTitle2"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.headingMsg"/></p>
			  <div>
					<table class="displayGrid2 rounded shadow" id="contactTable" class="width-100per">
						<thead class="">
							<tr>
								<th width="150"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.contactType"/></th>
								<th width="300"><spring:message code="requestInfo.heading.serviceOrder.primaryContact"/></th>
								
								<th width="300"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.address"/></th>
								<th width="30"></th>
							</tr>
						</thead>
						<tbody>
						<c:choose>
							<c:when test="${fn:length(hardwareDebriefForm.userEnteredActivity.serviceRequest.contactInfoForDevice)>0}">
						
							
						
						<c:forEach var="contactInformation" items="${hardwareDebriefForm.userEnteredActivity.serviceRequest.contactInfoForDevice}" varStatus="status">
							
							<c:choose>
									<c:when test="${status.index%2==0}">
													<tr id="tableContactAddressRow[${status.index}]">
												</c:when>
												<c:otherwise>
													<tr class="altRow" id="tableContactAddressRow[${status.index}]">
												</c:otherwise>
							</c:choose>
						
							<!-- <tr id="tableContactAddressRow[${status.index}]"> -->
								<td class="middle">
										<select name="userEnteredActivity.serviceRequest.contactInfoForDevice[${status.index}].deviceContactType" onchange="validateContactType(this)">
												<option value="">---<spring:message code="generateAssetList.label.select"/>---</option>
												<c:forEach items="${hardwareDebriefForm.contactTypeMap}" var="contactTypeMap">
												<c:choose>
													<c:when test="${contactInformation.deviceContactType eq contactTypeMap.key }">
														<option value="${contactTypeMap.key}"  selected="selected">${contactTypeMap.value}</option>
													</c:when>
													<c:otherwise>
														<option value="${contactTypeMap.key}">${contactTypeMap.value}</option>
													</c:otherwise>
												</c:choose>
												
												</c:forEach>
															
										</select>
								</td>
								<td class="middle"><div>
									<ul class="form">
							                <li>
							                  <label><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.name"/></label>
							                  <span  id="serviceContact[${status.index}]_firstName">${contactInformation.firstName}</span><span id="serviceContact[${status.index}]_lastName">${contactInformation.lastName}</span></li>
							                <li>
							                  <label><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.ph"/></label>
							                  <span id="serviceContact[${status.index}]_workPhone">${contactInformation.workPhone}</span></li>
							                <li>
							                  <label><spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.email"/></label>
							                  <span class="word-break-break-all" id="serviceContact[${status.index}]_emailAddress">${contactInformation.emailAddress}</span></li>
							              </ul></div>
							              
										  <div><a style="cursor:pointer;" id="selectContactLink[${status.index}]" title="<spring:message code="contact.selectDiffContact"/>" class="floatR" onClick="popUpContact(id,'[${status.index}]',setContactValuesFromPopup)">
										  		
										  		<spring:message code="contact.selectDiffContact"/>
										  </a></div>
										 
										  <div style="display:none">
										   <%-- This div contains hidden inputs for contacts --%>
										  <input type="text" name="userEnteredActivity.serviceRequest.contactInfoForDevice[${status.index}].firstName" value="${contactInformation.firstName}"/>
										  <input type="text" name="userEnteredActivity.serviceRequest.contactInfoForDevice[${status.index}].lastName" value="${contactInformation.lastName}"/>
										  <input type="text" name="userEnteredActivity.serviceRequest.contactInfoForDevice[${status.index}].workPhone" value="${contactInformation.workPhone}"/>
										  <input type="text" name="userEnteredActivity.serviceRequest.contactInfoForDevice[${status.index}].alternatePhone" value="${contactInformation.alternatePhone}"/>
										  <input type="text" name="userEnteredActivity.serviceRequest.contactInfoForDevice[${status.index}].emailAddress" value="${contactInformation.emailAddress}" />
										  <input type="text" name="userEnteredActivity.serviceRequest.contactInfoForDevice[${status.index}].contactId" value="${contactInformation.contactId}" />
										  </div>
								</td>
								
								<td class="middle">
								
										<ul class="roDisplay">
										<li id="showAddress_userEnteredActivity.serviceRequest.contactInfoForDevice[${status.index}].address">
								                <%-- <li><span>
								                  Store-front Name<br/>
								                  Address Line 1<br />
								                  Address Line 2<br />
								                  City, State / Province &lt;Zip/Postal Code&gt;<br />
								                  Country</span> </li>
								                  --%>
								                  <util:addressOutput value="${contactInformation.address}" displayInDivs="true"></util:addressOutput>
								         </li>
								         
								              </ul>
								              <ul class="form division">
								                <li>
								                  <label for="building"><spring:message code="claimCreate.addressInfo.label.building"/></label>
								                  <span>
								                  <input type="text" id="building"  name="userEnteredActivity.serviceRequest.contactInfoForDevice[${status.index}].address.physicalLocation1" class="w100" value="${contactInformation.address.physicalLocation1}"/>
								                  </span></li>
								                <li>
								                  <label for="floor"><spring:message code="claimCreate.addressInfo.label.floor"/></label>
								                  <span>
								                  <input type="text" id="floor"  name="userEnteredActivity.serviceRequest.contactInfoForDevice[${status.index}].address.physicalLocation2" class="w100" value="${contactInformation.address.physicalLocation2}" />
								                  </span></li>
								                <li>
								                  <label for="office"><spring:message code="claimCreate.addressInfo.label.office"/></label>
								                  <span>
								                  <input type="text" id="office"  name="userEnteredActivity.serviceRequest.contactInfoForDevice[${status.index}].address.physicalLocation3" class="w100" value="${contactInformation.address.physicalLocation3}"/>
								                  </span></li>
								              </ul>
								              <div>
								              <c:choose>
								              <c:when test="${not empty contactInformation.address.addressLine1}">
								              
								              	<a style="cursor: pointer;" id="selectAddressLink[${status.index}]" title="<spring:message code="address.selectDiffAddress" />" class="floatR" onClick="popUpAddress(id,'userEnteredActivity.serviceRequest.contactInfoForDevice[${status.index}].address',setAddressValuesFromPopup,false)">
								              				<spring:message code="address.selectDiffAddress"/>
								              	</a>
								              </c:when>
								              <c:otherwise>
								              		<a style="cursor: pointer;" id="selectAddressLink[${status.index}]" title="<spring:message code="address.selectAddress" />" class="floatR" onClick="popUpAddress(id,'userEnteredActivity.serviceRequest.contactInfoForDevice[${status.index}].address',setAddressValuesFromPopup,false)">
								              				<spring:message code="address.selectAddress"/>
								              		</a>
								              </c:otherwise>
								              </c:choose>
								              </div>
								               <div style="display:none">
								               <util:generateAddressInputFields value="${contactInformation.address}" index="[${status.index}]" preName="userEnteredActivity.serviceRequest.contactInfoForDevice" postName=".address."></util:generateAddressInputFields>
												  
												  
												  
										  		</div>
								</td>
								<td class="middle"><img src="<html:imagesPath/>transparent.png"  alt="Remove" title="Remove" class="edit ui-icon ui-icon-closethick cursor-pointer" onclick="removeContactAddressTableRow('tableContactAddressRow[${status.index}]')" ></td>
							</tr>
							</c:forEach>	
								</c:when>
							<c:otherwise>
								<tr><td colspan="4"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.noRec"/></td></tr>
							</c:otherwise>
						</c:choose>
							
						</tbody>
					</table>
            </div>
			
			<div class="buttonContainer border-none" >
        
          <a class="button button21" id="addAnotherRowButton" onclick="addAnotherContactRow()">
          
          <c:choose>
				<c:when test="${fn:length(hardwareDebriefForm.userEnteredActivity.serviceRequest.contactInfoForDevice)>0}">
					
				   	<spring:message code="contact.addAnotherContact" />
				 </c:when>
				<c:otherwise>
					<spring:message code="contact.addContact" />
				</c:otherwise>	 
			</c:choose>      
          </a>
        </div>
          </div>
          
          
          
          
         <div style="display:none;" id="templateContactDiv">
         <%-- THIS DIV IS USED TO CREATE A NEW TABLE ROW FOR ADD NEW CONTACT --%>
         	<table id="defaultTableTemplate">
         	<tbody>
         		<tr class="altRow" id="tableContactAddressRow[-1]">
								<td class="middle">
								<select name="userEnteredActivity.serviceRequest.contactInfoForDevice[-1].deviceContactType" onchange="validateContactType(this)">
								<option value="">---<spring:message code="generateAssetList.label.select"/>---</option>
								<c:forEach items="${hardwareDebriefForm.contactTypeMap}" var="contactTypeMap">
												<option value="${contactTypeMap.key}">${contactTypeMap.value}</option>
								</c:forEach>
								</select>
								</td>
								<td class="middle"><div>
									<ul class="form">
							                <li>
							                  <label><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.name"/></label>
							                
							                  <span id="serviceContact[-1]_firstName"></span><span id="serviceContact[-1]_lastName"></span></li>
							                <li>
							                  <label><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.ph"/></label>
							                  <span id="serviceContact[-1]_workPhone"></span></li>
							                <li>
							                  <label><spring:message code="requestInfo.contactInfo.label.emailAddress"/></label>
							                  <span class="word-break-break-all" id="serviceContact[-1]_emailAddress"></span></li>
							              </ul></div>
										  <div><a class="cursor-pointer" id="selectContactLink[-1]" title="<spring:message code="contact.selectContact" />" class="floatR" onClick="popUpContact(id,'[-1]',setContactValuesFromPopup)">
										  		<spring:message code="contact.selectContact" />	
										  		</a></div>
										 
										  <div style="display:none">
										   <%-- This div contains hidden inputs for contacts --%>
										  <input type="text" name="userEnteredActivity.serviceRequest.contactInfoForDevice[-1].firstName" />
										  <input type="text" name="userEnteredActivity.serviceRequest.contactInfoForDevice[-1].lastName" />
										  <input type="text" name="userEnteredActivity.serviceRequest.contactInfoForDevice[-1].workPhone" />
										  <input type="text" name="userEnteredActivity.serviceRequest.contactInfoForDevice[-1].alternatePhone" />
										  <input type="text" name="userEnteredActivity.serviceRequest.contactInfoForDevice[-1].emailAddress" />
										  <input type="text" name="userEnteredActivity.serviceRequest.contactInfoForDevice[-1].contactId"  />
										  </div>
								</td>
								
								<td class="middle">
								
										<ul class="roDisplay">
										<li id="showAddress_userEnteredActivity.serviceRequest.contactInfoForDevice[-1].address"></li>
								                <%--<li><span>
								                  Store-front Name<br/>
								                  Address Line 1<br />
								                  Address Line 2<br />
								                  City, State / Province &lt;Zip/Postal Code&gt;<br />
								                  Country</span> </li>
								                   --%>
								              </ul>
								              <ul class="form division">
								                <li>
								                  <label for="building"><spring:message code="claimCreate.addressInfo.label.building" /></label>
								                  <span>
								                  <input type="text" id="building" name="userEnteredActivity.serviceRequest.contactInfoForDevice[-1].address.physicalLocation1" class="w100" />
								                  </span></li>
								                <li>
								                  <label for="floor"><spring:message code="claimCreate.addressInfo.label.floor" /></label>
								                  <span>
								                  <input type="text" id="floor" name="userEnteredActivity.serviceRequest.contactInfoForDevice[-1].address.physicalLocation2" class="w100" />
								                  </span></li>
								                <li>
								                  <label for="office"><spring:message code="claimCreate.addressInfo.label.office" /></label>
								                  <span>
								                  <input type="text" id="office" name="userEnteredActivity.serviceRequest.contactInfoForDevice[-1].address.physicalLocation3" class="w100" />
								                  </span></li>
								              </ul><div><a style="cursor: pointer;" id="selectAddressLink[-1]" title="<spring:message code="address.selectAddress" />" class="floatR" onClick="popUpAddress(id,'userEnteredActivity.serviceRequest.contactInfoForDevice[-1].address',setAddressValuesFromPopup,false)">
								              			<spring:message code="address.selectAddress" />
								              			</a></div>
								               <div style="display:none">
								               	<util:generateAddressInputFields index="[-1]" preName="userEnteredActivity.serviceRequest.contactInfoForDevice" postName=".address."></util:generateAddressInputFields>
												  
												  
												  
										  		</div>
								</td>
								<td class="middle"><img src="<html:imagesPath/>transparent.png" width="12" height="12" alt="Remove" title="Remove" class="edit ui-icon ui-icon-closethick cursor-pointer" onclick="removeContactAddressTableRow('tableContactAddressRow[-1]')" ></td>
							</tr>
					</tbody>
         	</table>
         </div> 
         
         <script>
        
        <%--
          alert(jQuery('#showAddress_activity\\.serviceRequest\\.contactInfoForDevice\\[-1\\]\\.address\\.').html());  
			alert(jQuery("[name='"+"userEnteredActivity.serviceRequest.contactInfoForDevice[-1].firstName"+"']").val());
         alert(jQuery('#'+"serviceContact\\[-1\\]_firstName").text());
         var spanHTMLId="serviceContact\\[-1\\]_firstName";
         spanHTMLId=spanHTMLId.replace('[','\\\\[');
		spanHTMLId=spanHTMLId.replace(']','\\\\]');		
			alert(spanHTMLId);	
             	alert(jQuery('#'+spanHTMLId).html());

         --%>
         	var contactListLength=-1;
         	if(" ${fn:length(hardwareDebriefForm.userEnteredActivity.serviceRequest.contactInfoForDevice)}"!=0){
         		contactListLength=parseInt(" ${fn:length(hardwareDebriefForm.userEnteredActivity.serviceRequest.contactInfoForDevice)}");
         		contactListLength--;
             }
         	//alert('contactListLength='+contactListLength);
         	function setContactValuesFromPopup(contactRowObj){
         		var selectContactLink=contactRowObj.clickFromId;
         		selectContactLink=selectContactLink.replace('[','\\[');
         		selectContactLink=selectContactLink.replace(']','\\]');
     			jQuery('#'+selectContactLink).html(contact_AddressMessages.diffContactMsg);
     			jQuery('#'+selectContactLink).attr('title',contact_AddressMessages.diffContactMsg);
             		for(i=0;i<contactRowObj.keys.length;i++){
             			//serviceContact[${status.index}]_firstName
             			var spanHTMLId="serviceContact"+contactRowObj.inputIds+'_'+contactRowObj.keys[i];
		     			spanHTMLId=spanHTMLId.replace('[','\\[');
		     			spanHTMLId=spanHTMLId.replace(']','\\]');
		     			
		     			var inputHTMLId="userEnteredActivity.serviceRequest.contactInfoForDevice"+contactRowObj.inputIds+"."+contactRowObj.keys[i];

						<%--
		   		     	alert('contactRowObj.inputIds'+contactRowObj.inputIds);
		     			alert('spanHTMLId='+spanHTMLId);
		     			//alert('inputHTMLId='+inputHTMLId);
             			
             			
             			//alert('value='+contactRowObj[contactRowObj.keys[i]]);
             			alert(jQuery('#'+spanHTMLId).html());
						--%>
             			
                 			jQuery('#'+spanHTMLId).html(contactRowObj[contactRowObj.keys[i]]);
                 			jQuery("[name='"+inputHTMLId+"']").val(contactRowObj[contactRowObj.keys[i]]);
                 			<%--alert(jQuery("[name='"+inputHTMLId+"']").val());--%>
                 		}
             		closeDialog();
             	}
         	

			
         	
         	function addAnotherContactRow(){
             	if(contactListLength==-1){
                 		<%-- This is for removing the first row which state table is empty --%>
                 		jQuery('#contactTable tbody tr:first').remove();
                 		jQuery('#addAnotherRowButton').html("<spring:message code="contact.addAnotherContact" javaScriptEscape="true"/>");
                 		
                 	}
             	//alert('default template='+jQuery('#defaultTableTemplate tbody').html());
             	var trHtml=jQuery('#defaultTableTemplate tbody').html();
             	contactListLength++;
             	formatValidation[10][3]=contactListLength;
             	formatValidation[11][3]=contactListLength;
                 	
             	trHtml=replaceAll(trHtml,'[-1]','['+contactListLength+']');
             	if(contactListLength%2==0){
             		trHtml=replaceAll(trHtml,'altRow','');
             	}
             	jQuery(trHtml).appendTo(jQuery('#contactTable tbody'));
             	//jQuery('#defaultTableTemplate tr').clone().appendTo(jQuery('#contactTable'));
             		//jQuery('#contactTable')
             
             	
             	}

         function removeContactAddressTableRow(tableRowId){
        	 tableRowId=tableRowId.replace('[','\\[');
        	 tableRowId=tableRowId.replace(']','\\]');
             	jQuery('#'+tableRowId).remove();
             	
             	//contactListLength--;
             	
             	formatValidation[10][3]=contactListLength;
             	formatValidation[11][3]=contactListLength;
             	//addressArray[contactListLength]=null;
             	if(contactListLength==-1){
             		<%-- This is for adding the first row which state table is empty --%>
             		jQuery('#contactTable tbody').append("<tr><td colspan=\"4\"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.noRec" javaScriptEscape="true"/></td></tr>");
             		jQuery('#addAnotherRowButton').html("<spring:message code="contact.addContact" javaScriptEscape="true"/>");
             	}
           
             }	
         	
         function validateContactType(selectObj){
       	  var count=0;
       		jQuery(selectObj).removeClass('errorColor');
       	  jQuery('#contactTable tbody tr').each(function(){
       		  jQuery(this).find('td:first').each(function(){
       			  var selObj=jQuery(this).find("select");
       			  
       			  if(jQuery(selectObj).val()==jQuery(selObj).val()){
       				  count++;
       			  }
       		  });
       	  }); 
       	  if(count>1){
       		  jAlert("Contact Type already selected");
       		  jQuery(selectObj).val('');
       	  }
         }
         	
         </script>