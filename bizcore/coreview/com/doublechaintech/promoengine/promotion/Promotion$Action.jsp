
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty promotion}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A Promotion" style="color: green">${userContext.localeMap['promotion']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['promotion.id']}</span> ${promotion.id}</li>
<li><span>${userContext.localeMap['promotion.name']}</span> ${promotion.name}</li>
<li><span>${userContext.localeMap['promotion.valid_after']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${promotion.validAfter}" /></li>
<li><span>${userContext.localeMap['promotion.expire_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${promotion.expireTime}" /></li>
<li><span>${userContext.localeMap['promotion.last_update_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${promotion.lastUpdateTime}" /></li>

	
	</ul>
</div>



</c:if>


