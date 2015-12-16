<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<style>
	#bundlesPagingArea > div {
	    width: 100% !important;
	}
	#pagingArea > div {
	    width: 100% !important;
	}
</style>

<c:if test="${fromAriba == 'true'}">
      	 <div id="breadcrum-cart-cntnr">
       	 	<div class="breadcrum-cntnr"></div>
       	 	<jsp:include page="/WEB-INF/jsp/shoppingCart/totalItems.jsp"/>
     	 </div>
      </c:if>
<%@ include file="/WEB-INF/jsp/requests/supplies/suppliesProduct.jsp"%>
</br></br></br>
<div id="layout-grid">
<div id="portlet-wrap" style="width:100%!important">
<div class="pageTitle" id="pageTitle_bundles">Bundles <spring:message code="suppliesProduct.pageTitle.searchResult"/></div>
<div class="error" id="errorMsgPopup" style="display:none"></div>
<div id="bundle">
<%@ include file="/WEB-INF/jsp/requests/product/bundle.jsp"%>
</div>
<c:if test="${fromAriba == 'true'}">
          <div class="price-btn-cntnr">
              <input name="Continue" title="" type="button" class="button" value="Continue" border="0" onclick="showShoppingCart('globalSearch')">
          </div>
</c:if>
</div>
</div>