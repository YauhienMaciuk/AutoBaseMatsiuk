<%@ include file="/jsp/common/head.jsp" %>
<html>
<head>
    <title><fmt:message key="title.createAutoFlight"/></title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="title5"><ctg:infoDate/></div>
<%@ include file="/jsp/elements/logOut.jsp" %>
<div class="block2">
    <div class="logo"></div>
    <form action="controller" method="post">
        <input type="hidden" name="page" value="listNewBids">
        <input type="submit" value="<fmt:message key="listAuto.back"/>" class="button4">
    </form>
    <div class="title2">
        <br>
        <fmt:message key="autoFlight.title"/>
        <br><br>
        <form action="controller" method="post">
            <input type="hidden" name="page" value="createAutoFlight">
            <input type="hidden" name="idBid" value="${idBid}">
            <table class="tb8" align="center">
                <tr>
                    <td align="center"><fmt:message key="listAuto.auto"/></td>
                    <td align="center"><fmt:message key="bids.driver"/></td>
                </tr>
                <tr>
                    <td>
                        <select name="idAuto">
                            <option disabled><fmt:message key="autoFlight.selectAuto"/></option>
                            <c:forEach var="auto" items="${listAuto}">
                                <option value="${auto.id}">${auto.mark}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select name="idDriver">
                            <option disabled><fmt:message key="autoFlight.selectDriver"/></option>
                            <c:forEach var="driver" items="${listDrivers}">
                                <option value="${driver.id}">${driver.firstName} ${driver.lastName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="submit" value="<fmt:message key="autoFlight.create"/>" class="button4">
                    </td>
                </tr>
            </table>
            <c:if test="${not empty failedCreateAutoFlight}"><fmt:message key="autoFlight.failed"/></c:if>
        </form>
    </div>
    <br><br><br><br><br><br><br><br><br>
</div>
<table class="tb10" align="center">
    <tr>
        <td><ctg:footer/></td>
    </tr>
</table>
</body>
</html>

