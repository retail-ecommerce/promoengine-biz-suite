
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty targetAction}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A TargetAction" style="color: green">${userContext.localeMap['target_action']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['target_action.id']}</span> ${targetAction.id}</li>
<li><span>${userContext.localeMap['target_action.name']}</span> ${targetAction.name}</li>
<li><span>${userContext.localeMap['target_action.parameter']}</span> ${targetAction.parameter}</li>
<li><span>${userContext.localeMap['target_action.last_update_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${targetAction.lastUpdateTime}" /></li>

	
	</ul>
</div>



</c:if>


