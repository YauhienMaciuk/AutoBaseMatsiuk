<%@ include file="/jsp/common/head.jsp" %>
<html>
<head>
    <title><fmt:message key="title.bidsDriver"/></title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="title5"><ctg:infoDate/></div>
<%@ include file="/jsp/elements/logOut.jsp"%>
<div class="block2">
    <div class="logo"></div>
    <div class="title2">
        <c:if test="${not empty notAutoFlight}"><fmt:message key="bidsDriver.notAutoFlight"/></c:if>
    </div>
    <c:if test="${not empty bidForDriver}"><br>
        <div class="title2"><fmt:message key="bidsDriver.title"/><br><br></div>
    <table class="tb2" align="center">
            <%@include file="/jsp/elements/headerTable.jsp"%>
            <form action="controller" method="post">
                <input type="hidden" name="page" value="wantPerformAutoFlight">
                <tr class="tb5">
                    <td align="center">${bidForDriver.id}</td>
                    <td align="center">${bidForDriver.fromCity.nameCity}</td>
                    <td align="center">${bidForDriver.toCity.nameCity}</td>
                    <td align="center">${bidForDriver.valueShipment}</td>
                    <td align="center">${bidForDriver.weightShipment}</td>
                    <td align="center">${bidForDriver.dateService}</td>
                    <td align="center"><input type="submit" value="<fmt:message key="bidsDriver.perform"/>" class="button"></td>
                </tr>
            </form>
    </table>
    </c:if>
    <c:if test="${not empty impossiblePerformAutoFlight}">
        <div class="title"><br>
            <fmt:message key="bidsDriver.impossible"/></div>
    </c:if>
    <c:if test="${not empty changedConditionAuto}">
        <div class="title"><br>
            <fmt:message key="bidsDriver.changed"/></div>
    </c:if>
    <div class="title3">
        <form action="controller" method="post">
            <input type="hidden" name="page" value="lookListAuto"><br>
            <fmt:message key="bidsDriver.look"/>
            <input type="submit" value="<fmt:message key="bidsDriver.press"/>" class="button5">
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
