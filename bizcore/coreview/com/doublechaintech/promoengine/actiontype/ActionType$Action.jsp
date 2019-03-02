
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty actionType}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A ActionType" style="color: green">${userContext.localeMap['action_type']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['action_type.id']}</span> ${actionType.id}</li>
<li><span>${userContext.localeMap['action_type.name']}</span> ${actionType.name}</li>
<li><span>${userContext.localeMap['action_type.last_update_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${actionType.lastUpdateTime}" /></li>

	
	</ul>
</div>



</c:if>


