<%@ include file="/jsp/common/head.jsp" %>
<html>
<head>
    <title><fmt:message key="title.confirmBid"/></title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="title5"><ctg:infoDate/></div><br>
<%@ include file="/jsp/elements/logOut.jsp" %>
<div class="block2">
    <div class="logo"></div>
    <br>
    <div class="title2">
        <c:if test="${not empty deletePerformedBid}">
            <fmt:message key="confirmDelete.matter"/>
            <br><br>
            <%@include file="/jsp/elements/confirmDeleteBid.jsp" %>
        </c:if>
        <c:if test="${not empty cancelBid}">
            <fmt:message key="confirmDelete.cancelBid"/>
            <br><br>
            <table align="center">
                <tr>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="page" value="deleteBid">
                            <input type="hidden" name="actionClient" value="cancelBid">
                            <input type="submit" value="<fmt:message key="confirmDelete.yes"/> " class="button5">
                        </form>
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="page" value="notDelete">
                            <input type="hidden" name="actionClient" value="cancelBid">
                            <input type="submit" value="<fmt:message key="confirmDelete.not"/>" class="button5">
                        </form>
                    </td>
                </tr>
            </table>
        </c:if>
        <c:if test="${not empty deletePerformedAutoFlight}">
            <fmt:message key="confirmDelete.performedAutoFlight"/>
            <br><br>
            <%@include file="/jsp/elements/confirmDeleteBid.jsp" %>
        </c:if>
        <c:if test="${not empty cancelAutoFlight}">
            <fmt:message key="confirmDelete.cancelAutoFlight"/>
            <br><br>
            <%@include file="/jsp/elements/confirmDeleteAutoFlight.jsp"%>
        </c:if>
    </div>
    <br><br><br><br><br><br><br>
</div>
<table class="tb10" align="center">
    <tr>
        <td><ctg:footer/></td>
    </tr>
</table>
</body>
</html>
