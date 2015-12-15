<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.lexmark.domain.CompanyMdmAssociation" %>
<%@page import="java.util.*" %>

<div style="width: 100%; padding: 0px 0px 0px 6px; display: block;" id="association">
<% String expression = (String) request.getAttribute("expression");
%>
<% if(expression == "blank") {%>
					<script type="text/javascript">
						document.getElementById("accountIderror").style.display="block";
					</script>
					<%}%>
					<%if(expression != "blank"){
						ArrayList companyMdmAccountList = new ArrayList();
						companyMdmAccountList = (ArrayList)request.getAttribute("list"); %>					
					<table style="width:100%;display: block;" id="associationTable">
                    <tbody>
					<tr>
					<td nowrap="" style="padding:0 0 0 6px;"><b><input type="hidden" id="acc_companyType" name="acc_association">Company Type:</b></td>
					<td nowrap="" style="padding:0 0 0 6px;"><b><input type="hidden" id="acc_companyType" name="acc_association">Account Association:</b></td>					
					</tr>								
					<tr>					
					<td valign="top" nowrap="" style="padding:0 0 0 6px;">		
					<div style="width:200px;height:116px; border:gray 1px solid;padding:0 0 0 6px; overflow: auto;" id="companyType">
					<table style="width:100%;">
					<tbody>
					<tr></tr>
					<% for (int i = 0; i < companyMdmAccountList.size(); i++) {
							CompanyMdmAssociation cmbean = (CompanyMdmAssociation) companyMdmAccountList.get(i);
							String companyType = (String)cmbean.getCompanyType();
							String companyDAId = (String)cmbean.getCompanyDAId();
							String companyLegalNo = (String)cmbean.getCompanyLegalNo();
							String companyGlobalNo = (String)cmbean.getCompanyGlobalNo();
							String companyDomesticNo = (String)cmbean.getCompanyDomesticNo();
							char[] numArrayGlobal = companyGlobalNo.toCharArray();
			                char[] numArrayDomestic = companyDomesticNo.toCharArray();
			                int counter1=0;
			                int counter2=0;
			                for(int j=0;j<numArrayGlobal.length;j++) {
			                   if(numArrayGlobal[j]=='0') {
			                      counter1++; 
			                   }
			                   else{
			                	   break;
			                   }
			                }
			                for(int k=0;k<numArrayDomestic.length;k++) {
			                    if(numArrayDomestic[k]=='0') {
			                    	counter2++; 
			                    }
			                    else{
			                 	   break;
			                    }
			                }
			                String noOfZerosGlobal = Integer.toString(counter1);
			                String noOfZerosDomestic = Integer.toString(counter2);
			                %>
					<tr>					
					<td width:100%;'="" style="">
					<input type="hidden" value="LEGAL" name="levelName">
					<input id="acc_companyType" type="checkbox" value="<%=companyType%>" onclick="if (this.checked) { javascript:SelectcompanyType(<%=companyDAId%>,<%=companyLegalNo%>,<%=companyDomesticNo%>,<%=companyGlobalNo%>,<%=noOfZerosDomestic%>,<%=noOfZerosGlobal%>) } else { javascript:RemovecompanyTypeAssociation() }" name="acc_association"><%=companyType%>
					</td>													
					</tr>
					<%}%>
					</tbody>
					</table>
					</div>
					</td>
					<td valign="top" nowrap="" style="padding:0 0 0 6px;">
                    <div style="width:503px;height:116px; border:gray 1px solid; overflow: auto;" id="accDetail">					
					<select multiple="multiple" style="width:100%;" size="8" name="accountdetail" id="accountdetail">
					<% for (int i = 0; i < companyMdmAccountList.size(); i++) {
							CompanyMdmAssociation cmbean = (CompanyMdmAssociation) companyMdmAccountList.get(i);
							String companyDAId = (String)cmbean.getCompanyDAId();
							String companyLegalName = (String)cmbean.getCompanyLegalName();
							String companyType = (String)cmbean.getCompanyType();
							String companyRole = (String)cmbean.getCompanyRole();							
							String newExp = companyLegalName + "(" + companyDAId + ")" + " - " + companyType + " - " + companyRole; %>
					<option><%=newExp%></option>
					<%}%>
					</select>					
					</div>
					</td>					
					<%}%>
					</tr>					
					</tbody>
					</table>
					<table>
					<tr>
					<td class="labelBold" style="width:200px;">&nbsp;
	                    *Please check Company Type to configure Manual report for Level 4
	                </td>
					</tr>
					</table>										
</div>