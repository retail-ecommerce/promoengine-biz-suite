<%@ page import='java.util.*,com.doublechaintech.promoengine.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty targetUserRuleList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['target_user_rule']}! 
		 <a href="./${ownerBeanName}Manager/addTargetUserRule/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty targetUserRuleList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("targetUserRuleList"); 
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
		<i class='fa fa-table'></i> ${userContext.localeMap['target_user_rule']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addTargetUserRule/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'targetUserRuleList' eq action.actionGroup}">
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
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${targetUserRuleListName};${targetUserRuleListName}CurrentPage=${page.pageNumber};${targetUserRuleListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='targetUserRuleListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['target_user_rule.id']}</th>
</c:if>
<c:if test="${param.referName ne 'name'}">
	<th>${userContext.localeMap['target_user_rule.name']}</th>
</c:if>
<c:if test="${param.referName ne 'applyCondition'}">
	<th>${userContext.localeMap['target_user_rule.apply_condition']}</th>
</c:if>
<c:if test="${param.referName ne 'parameter'}">
	<th>${userContext.localeMap['target_user_rule.parameter']}</th>
</c:if>
<c:if test="${param.referName ne 'lastUpdateTime'}">
	<th>${userContext.localeMap['target_user_rule.last_update_time']}</th>
</c:if>
<c:if test="${param.referName ne 'promotion'}">
	<th>${userContext.localeMap['target_user_rule.promotion']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${targetUserRuleList}">
				<tr currentVersion='${item.version}' id="targetUserRule-${item.id}" ><td><a class="link-action-removed" href="./targetUserRuleManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'name'}">	<td contenteditable='true' class='edit-value'  propertyToChange='name' storedCellValue='${item.name}' prefix='${ownerBeanName}Manager/updateTargetUserRule/${result.id}/${item.id}/'>${item.name}</td>
</c:if><c:if test="${param.referName ne 'applyCondition'}">
	<td class="select_candidate_td"
			data-candidate-method="./targetUserRuleManager/requestCandidateApplyCondition/${ownerBeanName}/${item.id}/"
			data-switch-method="./targetUserRuleManager/transferToAnotherApplyCondition/${item.id}/"
			data-link-template="./applyConditionManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.applyCondition}">
			<a href='./applyConditionManager/view/${item.applyCondition.id}/'>${item.applyCondition.displayName}</a>
			</c:if>
			<c:if test="${empty  item.applyCondition}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'parameter'}">	<td contenteditable='true' class='edit-value'  propertyToChange='parameter' storedCellValue='${item.parameter}' prefix='${ownerBeanName}Manager/updateTargetUserRule/${result.id}/${item.id}/'>${item.parameter}</td>
</c:if><c:if test="${param.referName ne 'lastUpdateTime'}">	<td contenteditable='true' class='edit-value'  propertyToChange='lastUpdateTime' storedCellValue='${item.lastUpdateTime}' prefix='${ownerBeanName}Manager/updateTargetUserRule/${result.id}/${item.id}/'><fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.lastUpdateTime}" /></td>
</c:if><c:if test="${param.referName ne 'promotion'}">
	<td class="select_candidate_td"
			data-candidate-method="./targetUserRuleManager/requestCandidatePromotion/${ownerBeanName}/${item.id}/"
			data-switch-method="./targetUserRuleManager/transferToAnotherPromotion/${item.id}/"
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

				<a href='#${ownerBeanName}Manager/removeTargetUserRule/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyTargetUserRuleFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>

