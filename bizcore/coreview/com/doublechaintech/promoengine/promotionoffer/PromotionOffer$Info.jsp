
<%@ page language="java" contentType="text/plain; charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sky" tagdir="/tags"%>
<fmt:setLocale value="zh_CN"/>
<c:set var="ignoreListAccessControl" value="${true}"/>


<c:if test="${not empty promotionOffer}">
<div class="col-xs-12 col-ms-6 col-md-4 section">
	
	<div class="inner-section">
	
	<b title="A PromotionOffer">${userContext.localeMap['promotion_offer']} </b><a href="#"><i class="fa fa-refresh" aria-hidden="true"></i></a>
	<hr/>
	<ul>
	
	
	<li><span>ID</span><a class="link-action-removed" href="./promotionOfferManager/view/${promotionOffer.id}/"> ${promotionOffer.id}</a></li>
<li><span>${userContext.localeMap['promotion_offer.name']}</span> ${promotionOffer.name}</li>
<li><span>${userContext.localeMap['promotion_offer.parameter']}</span> ${promotionOffer.parameter}</li>

	
	</ul>
	
	</div><!-- end of inner-section -->
	
</div>

</c:if>




