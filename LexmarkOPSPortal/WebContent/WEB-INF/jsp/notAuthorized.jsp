<%--
 * Copyright 2005-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
--%>

<%@ include file="/WEB-INF/jsp/include.jsp" %>

<div class="portlet-section-header"></div>
<div class="portlet-section-body">
	<div class="main-wrap">
		<div class="content">
			<table width="100%">
				<tr>
					<td width="60px" height="30px">
						<p><img src="<html:imagesPath/>/error.png"  width="32px" id = "errImg" /></p>
					</td>
					<td>
						<p><spring:message code="exception.notAuthorized.title"/></p>
						<p><spring:message code="exception.notAuthorized.message"/></p>
					</td>
				</tr>
				<tr>
					<td width="60px" height="30px">&nbsp;</td>
					<td><spring:message code="exception.contactAdmin"/></td>
				</tr>
				<tr>
					<td width="60px" height="30px">
						<a href="<portlet:renderURL portletMode="view"/>"><spring:message code="button.home"/></a>
					</td>
					<td></td>
				</tr>
			</table>	
		</div><!-- content -->
	</div><!-- main-wrap -->
</div>
<div class="portlet-section-footer"></div>
<script type="text/javascript">
//---- Ominture script 
     pageTypeDisplayed="errorPage";
</script>
