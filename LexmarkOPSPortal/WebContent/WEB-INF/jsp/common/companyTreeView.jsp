<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--Added for (BRD 14-02-14) --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.lexmark.service.impl.real.jdbc.CustomerReportServiceImpl" %>
<%@page import="com.lexmark.domain.CompanyTreeBean" %>
<%@page import="java.util.*" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageWrapper" %>
<%@ page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %>
 <meta http-equiv="content-type" content="text/html; charset=iso-8859-1"/>
<%
CustomerReportServiceImpl ctdao = new CustomerReportServiceImpl();
%>

<%-- <div id="sidetreecontrol"><a href="#"  style="padding:0 0 0 5px;" >Collapse All</a> | <a href="#">Expand All</a></div> --%>
<div id="main" style="height:150px; padding:0 0 0 0; overflow: auto;" algin="left">
<ul id="tree">
    <%
        	int count = 0;
            ArrayList companyTreeList = new ArrayList();
            String listOfCompanies = (String) request.getAttribute("compName");
            String formname = "reportDefinition";
            companyTreeList = ctdao.getTreeForReportAdmin(listOfCompanies);
            String tempGno = "";
            String tempDno = "";
            boolean tempIsChildPresent = false;
            String gnum = "";
            String dno = "";
            String legalnum = "";
            if(companyTreeList.isEmpty() || companyTreeList == null){            	
            	out.println("<div style='font-weight:bold;padding:10px 0px 10px 10px;color:red'>No data found</div>");           	
            }
            else{
            	out.println("<div id='sidetreecontrol'><a href='#'  style='padding:0 0 0 5px;' >Collapse All</a> | <a href='#'>Expand All</a></div>");
            }
               for (int i = 0; i < companyTreeList.size(); i++) {
                CompanyTreeBean ctbean = (CompanyTreeBean) companyTreeList.get(i);
                boolean isChildPresent = ctbean.isChildPresent();
                String levelname = (String) ctbean.getLevelName();
                gnum = (String) ctbean.getCompany_global_ult_no();
                String gname = (String) ctbean.getCompany_global_ult_name();
                dno = (String) ctbean.getCompany_domestic_ult_no();
                String dname = (String) ctbean.getCompany_domestic_ult_name();
                legalnum = (String) ctbean.getCompany_legal_mdm_id();
                String legalname = (String) ctbean.getCompany_legal_mdm_name();
                char[] numArrayGlobal = gnum.toCharArray();
                char[] numArrayDomestic = dno.toCharArray();
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
              <%
              if (!gnum.equals(tempGno)){

                    if (!"".equals(tempGno)) {
                        if (tempIsChildPresent) {
                            out.println("</ul>"); //LEGAL UL closed
                            out.println("</li>"); //domestic LI closed
                            out.println("</ul>");//Domest UL closed
                        }
                        out.println("</li>");// Global LI Closed
                    }
                    tempDno = "";
                 %>
                <%--<li><strong> <input type="checkbox" name="chk" id="chk"  onclick="checkedCompanyLevel(<%=gnum%>,'Global');" value="<%=levelname%>_<%=gnum%>">&nbsp;<%=gnum%> - <%=gname%></strong> --%>
                <li><strong> <input type="checkbox" name="chk_L" id="chk_L<%=count%>"  onclick="if (this.checked) {changeCheckBox('chk_L<%=count%>');checkedCompanyLevel('','',<%=gnum%>,<%=noOfZerosDomestic%>,<%=noOfZerosGlobal%>); } else { javascript:RemoveTreeAssociation() }" value="<%=levelname%>_<%=gnum%>">&nbsp;<%=gnum%> - <%=gname%></strong>
                <%
                if(isChildPresent) {%>
                    <ul>
                <% }%>
              <% count++;
              }%>
              <%
              if (isChildPresent) {
                    if (!dno.equals(tempDno)) {
                        //if(tempDnochildNode) {
                        if (!"".equals(tempDno)) {
                            out.println("</ul>");//LEGAL UL closed
                            out.println("</li>");//Domest LI Closed
                            }
                       %>

				<%--<li>&nbsp;&nbsp;<input type="checkbox" name="chk_L" id="chk_L"  onclick="checkedCompanyLevel(<%=dno%>,'Domestic');" value="DOMESTIC_<%=dno%>">&nbsp;<%=dno%> - <%=dname%> --%>
				<li>&nbsp;&nbsp;<input type="checkbox" name="chk_L" id="chk_L<%=count%>"  onclick="if (this.checked) {changeCheckBox('chk_L<%=count%>');checkedCompanyLevel('',<%=dno%>,<%=gnum%>,<%=noOfZerosDomestic%>,<%=noOfZerosGlobal%>); } else { javascript:RemoveTreeAssociation() }" value="DOMESTIC_<%=dno%>">&nbsp;<%=dno%> - <%=dname%>
                <UL>
                <% count++;
                }%>

                    <%-- <li>&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk_L" id="chk_L"  onclick="checkedCompanyLevel(<%=legalnum%>,'Legal');" value="LEGAL_<%=legalnum%>">&nbsp;&nbsp;<%=legalnum%> - <%=legalname%></li> --%>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="chk_L" id="chk_L<%=count%>"  onclick="if (this.checked) {changeCheckBox('chk_L<%=count%>');checkedCompanyLevel(<%=legalnum%>,<%=dno%>,<%=gnum%>,<%=noOfZerosDomestic%>,<%=noOfZerosGlobal%>);javascript:l4Search(<%=legalnum%>); } else { javascript:RemoveTreeAssociation() }" value="LEGAL_<%=legalnum%>">&nbsp;&nbsp;<%=legalnum%> - <%=legalname%></li>
                <%
                count++;
                }%>


            <%
            tempGno = gnum;
                tempDno = dno;
                tempIsChildPresent = isChildPresent;

            }    %>


            <%
            if (tempIsChildPresent) {
                out.println("</ul>"); //LEGAL UL closed
                out.println("</li>"); //domestic LI closed
                out.println("</ul>");//Domest UL closed
            } else
                out.println("</li>");// Global LI Closed
           %>
            </ul>
            
</div>

<script type="text/javascript">

function changeCheckBox(id)
{
 	
	for(i=0;i<document.getElementsByName("chk_L").length;i++)
    {
        tmp_checkbox_id = "chk_L"+i;
                         
               if(id == tmp_checkbox_id ){
                document.getElementById(tmp_checkbox_id).checked = true;
				}
            else
            {    
               
                document.getElementById(tmp_checkbox_id).checked = false;
            }

    }
}

</script>