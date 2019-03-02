<%@ page import='java.util.*,com.doublechaintech.promoengine.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty promotionOfferList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['promotion_offer']}! 
		 <a href="./${ownerBeanName}Manager/addPromotionOffer/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty promotionOfferList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("promotionOfferList"); 
 	int totalCount = list.getTotalCount();
 	List pages = list.getPages();
 	pageContext.setAttribute("rowsPerPage",list.getRowsPerPage()); 
 	
 	pageContext.setAttribute("pages",pages); 
 	pageContext.setAttribute("totalCount",totalCount); 
 	//pageContext.setAttribute("accessible",list.isAccessible()); 
 	//the reason using scriptlet here is the el express is currently not able resolv common property from a subclass of list
%>
    
 	   
    <div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		<i class='fa fa-table'></i> ${userContext.localeMap['promotion_offer']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addPromotionOffer/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'promotionOfferList' eq action.actionGroup}">
		<a class="btn btn-${action.actionLevel} btn-sm" href="${action.managerBeanName}/${action.actionPath}">${userContext.localeMap[action.localeKey]}</a>
		</c:if>
	</c:forEach>
	</div><!--end of div class="btn-group" -->
	
		 
		 
		 
		 </div>
 </div>
    
    
<div class="table-responsive">


<c:set var="currentPageNumber" value="1"/>	



<nav aria-label="Page navigation example">
  <ul class="pagination">
<c:forEach var="page" items="${pages}"> 
<c:set var="classType" value=""/>
<c:if test='${page.selected}' >
<c:set var="classType" value="active"/>
<c:set var="currentPageNumber" value="${page.pageNumber}"/>
</c:if>


	<li class="page-item ${classType}">
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${promotionOfferListName};${promotionOfferListName}CurrentPage=${page.pageNumber};${promotionOfferListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='promotionOfferListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['promotion_offer.id']}</th>
</c:if>
<c:if test="${param.referName ne 'name'}">
	<th>${userContext.localeMap['promotion_offer.name']}</th>
</c:if>
<c:if test="${param.referName ne 'promotionLevel'}">
	<th>${userContext.localeMap['promotion_offer.promotion_level']}</th>
</c:if>
<c:if test="${param.referName ne 'parameter'}">
	<th>${userContext.localeMap['promotion_offer.parameter']}</th>
</c:if>
<c:if test="${param.referName ne 'promotion'}">
	<th>${userContext.localeMap['promotion_offer.promotion']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${promotionOfferList}">
				<tr currentVersion='${item.version}' id="promotionOffer-${item.id}" ><td><a class="link-action-removed" href="./promotionOfferManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'name'}">	<td contenteditable='true' class='edit-value'  propertyToChange='name' storedCellValue='${item.name}' prefix='${ownerBeanName}Manager/updatePromotionOffer/${result.id}/${item.id}/'>${item.name}</td>
</c:if><c:if test="${param.referName ne 'promotionLevel'}">
	<td class="select_candidate_td"
			data-candidate-method="./promotionOfferManager/requestCandidatePromotionLevel/${ownerBeanName}/${item.id}/"
			data-switch-method="./promotionOfferManager/transferToAnotherPromotionLevel/${item.id}/"
			data-link-template="./promotionLevelManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.promotionLevel}">
			<a href='./promotionLevelManager/view/${item.promotionLevel.id}/'>${item.promotionLevel.displayName}</a>
			</c:if>
			<c:if test="${empty  item.promotionLevel}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'parameter'}">	<td contenteditable='true' class='edit-value'  propertyToChange='parameter' storedCellValue='${item.parameter}' prefix='${ownerBeanName}Manager/updatePromotionOffer/${result.id}/${item.id}/'>${item.parameter}</td>
</c:if><c:if test="${param.referName ne 'promotion'}">
	<td class="select_candidate_td"
			data-candidate-method="./promotionOfferManager/requestCandidatePromotion/${ownerBeanName}/${item.id}/"
			data-switch-method="./promotionOfferManager/transferToAnotherPromotion/${item.id}/"
			data-link-template="./promotionManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.promotion}">
			<a href='./promotionManager/view/${item.promotion.id}/'>${item.promotion.displayName}</a>
			</c:if>
			<c:if test="${empty  item.promotion}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>

				<td>

				<a href='#${ownerBeanName}Manager/removePromotionOffer/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyPromotionOfferFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


