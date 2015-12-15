<%@ include file="/WEB-INF/jsp/include.jsp"%>
<div class="main-wrap">
	<div class="content">
		<table id="contactContentTable">
			<tr>
				<td id="left">
					<table>
						<c:forEach items="${contactInformationForm.contactInformations}" var="contactInformation" varStatus = "status" >
						<tr>
							<td>${contactInformation.contactData}</td>
						</tr>
						<c:if test="${not empty contactInformation.emailAddress}">
							<tr>
								<td><a href="mailto:${contactInformation.emailAddress}">${contactInformation.emailAddress}</a></td>
							</tr>
						</c:if>
						<tr>
							<td><br></td>
						</tr>
    					</c:forEach>
   					</table>
				</td>
				<td id="right" valign="top">
					<table>
						<tr>
							<td valign="top">
							<div align="left">
						    	<img src="<html:imagesPath/>support.gif" height="30px" width="28px"/> &nbsp;
								<span class="labelBold"><spring:message code='contactInformation.label.contactTechnicalsupport'/></span><br>
							</div>
							<div align="left">
								<spring:message code='contactInformation.label.for'/><br>
								<spring:message code='contactInformation.label.please'/> <a id="linkToProduct" href="#" target="_blank"><spring:message code='contactInformation.label.selectaproduct'/></a> <spring:message code='contactInformation.label.togetstarted'/>
							</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	<div class="clear"></div><!-- clear -->
	</div><!-- content -->
</div><!-- main-wrap -->
<script type="text/javascript">
	var hostName = "<spring:message code='contactTechnicalSupportHostName'/>";

	document.getElementById("linkToProduct").href = "http:\/\/"+hostName+"\/"+"index?page=productSelection&locale="+"${language}"+"&userlocale="+"${language}_${country}";
</script>
<script type="text/javascript">
//---- Ominture script 
     portletName = "Contact Admin";
     addPortlet(portletName);
     pageTitle="Contact display";
</script>