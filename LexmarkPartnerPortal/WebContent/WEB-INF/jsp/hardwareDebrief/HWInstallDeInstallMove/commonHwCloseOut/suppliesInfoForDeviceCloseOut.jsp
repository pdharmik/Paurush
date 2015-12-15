<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="portletBlock infoBox rounded shadow">
          <div class="columnsOne">
            <div class="columnInner">
              <h4><spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.suppliesInfo.header"/></h4>
				<div class="columnsTwo">
					<div class="infoBox columnInner rounded shadow">
					<h4><a href="javascript:void();" class="" id="PrimarySuppliesContactLink" title="Select a Contact" onClick="popUpContact(id,'primary_device');"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.selectDifferentContact"/></a><spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.suppliesInfo.primarySuppliesContact"/></h4>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
							<tr>
								<td width="100" class="label"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.name"/></td>
								<td>FirstName LastName</td>
							</tr>
							<tr>
								<td width="150" class="label"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.ph"/></td>
								<td>1234567890</td>
							</tr>
							<tr>
								<td class="label"><spring:message code="requestInfo.contactInfo.label.emailAddress"/></td>
								<td>abc@gmail.com</td>
							</tr>               
						</table>
					</div>
					<div class="infoBox columnInner rounded shadow">
					  <h4><a href="javascript:void();" class="" id="PrimaryContactAddressLink" title="Select an Address" onClick="popupAddress(id);"><spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.addressLink"/></a><spring:message code="requestInfo.hardwareDebreief.common.closeOut.serviceInfoForDevice.suppliesInfo.primaryContactAddr"/></h4>
					  <ul class="roDisplay">
						<li><span>
						  Store-front Name<br/>
						  Address Line 1<br />
						  Address Line 2<br />
						  City, State / Province &lt;Zip/Postal Code&gt;<br />
						  Country</span> </li>
					  </ul>
					  <ul class="form division">
						<li>
						  <label for="building"><spring:message code="claimCreate.addressInfo.label.building"/></label>
						  <span>
						  <input type="text" id="building" class="w100" />
						  </span>
						<li>
						  <label for="floor"><spring:message code="claimCreate.addressInfo.label.floor"/></label>
						  <span>
						  <input type="text" id="floor" class="w100" />
						  </span>
						<li>
						  <label for="office"><spring:message code="claimCreate.addressInfo.label.office"/></label>
						  <span>
						  <input type="text" id="office" class="w100" />
						  </span></li>
					  </ul>
					</div>
				</div>
				<div class="columnsTwo">
					<div class="infoBox columnInner rounded shadow">
					<h4><a href="javascript:void();" class="" id="SecondarySuppliesContactLink" title="Select a Contact" onClick="popUpContact(id);">Select a different contact</a>Secondary Supplies Contact</h4>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="dataGrid">
							<tr>
								<td width="100" class="label"><spring:message code="requestInfo.hardwareDebrief.commonContactInfo.name"/></td>
								<td><span id=""></span>&nbsp;<span></span> </td>
							</tr>
							<tr>
								<td width="150" class="label"><spring:message code="claim.label.phone"/></td>
								<td>1234567890</td>
							</tr>
							<tr>
								<td class="label"><spring:message code="requestInfo.contactInfo.label.emailAddress"/></td>
								<td>abc@gmail.com</td>
							</tr>               
						</table>
					</div>
					<div class="infoBox columnInner rounded shadow">
					  <h4><a href="javascript:void();" class="" title="Select an Address" id="SecondaryContactAddressLink" onClick="return popupAddress(id);">Select a different address</a>Secondary Contact Address</h4>
					  <ul class="roDisplay">
						<li><span>
						  Store-front Name<br/>
						  Address Line 1<br />
						  Address Line 2<br />
						  City, State / Province &lt;Zip/Postal Code&gt;<br />
						  Country</span> </li>
					  </ul>
					  <ul class="form division">
						<li>
						  <label for="building"><spring:message code="claimCreate.addressInfo.label.building"/></label>
						  <span>
						  <input type="text" id="building" class="w100" />
						  </span>
						<li>
						  <label for="floor"><spring:message code="claimCreate.addressInfo.label.floor"/></label>
						  <span>
						  <input type="text" id="floor" class="w100" />
						  </span>
						<li>
						  <label for="office"><spring:message code="claimCreate.addressInfo.label.office"/></label>
						  <span>
						  <input type="text" id="office" class="w100" />
						  </span></li>
					  </ul>
					</div>
				</div>

            </div>
          </div>
        </div>
        <script>
        	function setPopupValues(contactRowObj){
            		
            	}
        </script>