
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty promotion}">
<div class="col-xs-12 col-ms-6 col-md-4 section">
	
	<div class="inner-section">
	
	<b title="A Promotion">${userContext.localeMap['promotion']} </b><a href="#"><i class="fa fa-refresh" aria-hidden="true"></i></a>
	<hr/>
	<ul>
	
	
	<li><span>ID</span><a class="link-action-removed" href="./promotionManager/view/${promotion.id}/"> ${promotion.id}</a></li>
<li><span>${userContext.localeMap['promotion.name']}</span> ${promotion.name}</li>
<li><span>${userContext.localeMap['promotion.valid_after']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${promotion.validAfter}" /></li>
<li><span>${userContext.localeMap['promotion.expire_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${promotion.expireTime}" /></li>
<li><span>${userContext.localeMap['promotion.last_update_time']}</span> <fmt:formatDate pattern="yyyy-MM-dd" value="${promotion.lastUpdateTime}" /></li>

	
	</ul>
	
	</div><!-- end of inner-section -->
	
</div>

</c:if>




