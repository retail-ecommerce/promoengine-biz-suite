<%@ page import='java.util.*,com.doublechaintech.promoengine.*'%>
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${ empty targetActionList}" >
	<div class="row" style="font-size: 30px;">
		<div class="col-xs-12 col-md-12" style="padding-left:20px">
		 ${userContext.localeMap['@not_found']}${userContext.localeMap['target_action']}! 
		 <a href="./${ownerBeanName}Manager/addTargetAction/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 
		 
		 </div>
	</div>

</c:if>




	

 <c:if test="${not empty targetActionList}" >
    
    
<%

 	SmartList list=(SmartList)request.getAttribute("targetActionList"); 
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
		<i class='fa fa-table'></i> ${userContext.localeMap['target_action']}(${totalCount})
		<a href="./${ownerBeanName}Manager/addTargetAction/${result.id}/"><i class="fa fa-plus-square" aria-hidden="true"></i></a>
		 
		 		 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">		
	<c:forEach var="action" items="${result.actionList}">
		<c:if test="${'targetActionList' eq action.actionGroup}">
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
		<a href='#${ownerBeanName}Manager/load${ownerClassName}/${result.id}/${targetActionListName};${targetActionListName}CurrentPage=${page.pageNumber};${targetActionListName}RowsPerPage=${rowsPerPage}/' 
			class='page-link page-action '>${page.title}</a>
	</li>
</c:forEach>
 </ul>
</nav>


   


	<table class="table table-striped" pageToken='targetActionListCurrentPage=${currentPageNumber}'>
		<thead><tr>
		<c:if test="${param.referName ne 'id'}">
	<th>${userContext.localeMap['target_action.id']}</th>
</c:if>
<c:if test="${param.referName ne 'name'}">
	<th>${userContext.localeMap['target_action.name']}</th>
</c:if>
<c:if test="${param.referName ne 'action'}">
	<th>${userContext.localeMap['target_action.action']}</th>
</c:if>
<c:if test="${param.referName ne 'parameter'}">
	<th>${userContext.localeMap['target_action.parameter']}</th>
</c:if>
<c:if test="${param.referName ne 'lastUpdateTime'}">
	<th>${userContext.localeMap['target_action.last_update_time']}</th>
</c:if>
<c:if test="${param.referName ne 'promotion'}">
	<th>${userContext.localeMap['target_action.promotion']}</th>
</c:if>
<th>${userContext.localeMap['@action']}</th>
		</tr></thead>
		<tbody>
			
			<c:forEach var="item" items="${targetActionList}">
				<tr currentVersion='${item.version}' id="targetAction-${item.id}" ><td><a class="link-action-removed" href="./targetActionManager/view/${item.id}/"> ${item.id}</a></td>
<c:if test="${param.referName ne 'name'}">	<td contenteditable='true' class='edit-value'  propertyToChange='name' storedCellValue='${item.name}' prefix='${ownerBeanName}Manager/updateTargetAction/${result.id}/${item.id}/'>${item.name}</td>
</c:if><c:if test="${param.referName ne 'action'}">
	<td class="select_candidate_td"
			data-candidate-method="./targetActionManager/requestCandidateAction/${ownerBeanName}/${item.id}/"
			data-switch-method="./targetActionManager/transferToAnotherAction/${item.id}/"
			data-link-template="./actionTypeManager/view/${'$'}{ID}/">
		<span class="display_span">
			<c:if test="${not empty  item.action}">
			<a href='./actionTypeManager/view/${item.action.id}/'>${item.action.displayName}</a>
			</c:if>
			<c:if test="${empty  item.action}">
			<a href='#'></a>
			</c:if>
			<button class="btn btn-link candidate-action">...</button>
		</span>
		<div class="candidate_span" style="display:none;">
			<input type="text" data-provide="typeahead" class="input-sm form-control candidate-filter-input" autocomplete="off" />
		</div>
	</td>
</c:if>
<c:if test="${param.referName ne 'parameter'}">	<td contenteditable='true' class='edit-value'  propertyToChange='parameter' storedCellValue='${item.parameter}' prefix='${ownerBeanName}Manager/updateTargetAction/${result.id}/${item.id}/'>${item.parameter}</td>
</c:if><c:if test="${param.referName ne 'lastUpdateTime'}">	<td contenteditable='true' class='edit-value'  propertyToChange='lastUpdateTime' storedCellValue='${item.lastUpdateTime}' prefix='${ownerBeanName}Manager/updateTargetAction/${result.id}/${item.id}/'><fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ss" value="${item.lastUpdateTime}" /></td>
</c:if><c:if test="${param.referName ne 'promotion'}">
	<td class="select_candidate_td"
			data-candidate-method="./targetActionManager/requestCandidatePromotion/${ownerBeanName}/${item.id}/"
			data-switch-method="./targetActionManager/transferToAnotherPromotion/${item.id}/"
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

				<a href='#${ownerBeanName}Manager/removeTargetAction/${result.id}/${item.id}/' class='delete-action btn btn-danger btn-xs'><i class="fa fa-trash-o fa-lg"></i> ${userContext.localeMap['@delete']}</a>
				<a href='#${ownerBeanName}Manager/copyTargetActionFrom/${result.id}/${item.id}/' class='copy-action btn btn-success btn-xs'><i class="fa fa-files-o fa-lg"></i> ${userContext.localeMap['@copy']} </a>

				</td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>	
	

</div></c:if>


