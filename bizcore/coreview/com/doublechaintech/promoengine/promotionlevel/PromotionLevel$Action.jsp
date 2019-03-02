
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty promotionLevel}">

<div class="col-xs-12 col-ms-4 col-md-3 action-section" >
	<b title="A PromotionLevel" style="color: green">${userContext.localeMap['promotion_level']}</b>
	<hr/>
	<ul>
	
	
	<li><span>${userContext.localeMap['promotion_level.id']}</span> ${promotionLevel.id}</li>
<li><span>${userContext.localeMap['promotion_level.name']}</span> ${promotionLevel.name}</li>
<li><span>${userContext.localeMap['promotion_level.last_update_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${promotionLevel.lastUpdateTime}" /></li>

	
	</ul>
</div>



</c:if>


