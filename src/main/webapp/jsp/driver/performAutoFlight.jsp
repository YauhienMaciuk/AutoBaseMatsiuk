<%@ include file="/jsp/common/head.jsp" %>
<html>
<head>
    <title><fmt:message key="title.performAutoFlight"/></title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="title5"><ctg:infoDate/></div>
<%@ include file="/jsp/elements/logOut.jsp" %>
<div class="block2">
    <div class="logo"></div>
    <br>
    <div class="title2"><fmt:message key="performAutoFlight.title"/><br><br></div>
    <table class="tb2" align="center">
        <%@include file="/jsp/elements/headerTable.jsp"%>
        <tr class="tb5">
            <td align="center">${bidForDriver.id}</td>
            <td align="center">${bidForDriver.fromCity.nameCity}</td>
            <td align="center">${bidForDriver.toCity.nameCity}</td>
            <td align="center">${bidForDriver.valueShipment}</td>
            <td align="center">${bidForDriver.weightShipment}</td>
            <td align="center">${bidForDriver.dateService}</td>
        </tr>
    </table><br>
    <table align="center">
        <tr>
            <td>
                <form action="controller" method="post">
                    <input type="hidden" name="page" value="performAutoFlight">
                    <input type="hidden" name="idBid" value="${bidForDriver.id}">
                    <input type="submit" value="<fmt:message key="confirmDelete.yes"/>" class="button5">
                </form>
            </td>
            <td>
                <form action="controller" method="post">
                    <input type="hidden" name="page" value="toBidsDriver">
                    <input type="submit" value="<fmt:message key="confirmDelete.not"/>" class="button5">
                </form>
            </td>
        </tr>
    </table>
    <br><br><br><br><br><br><br>
</div>
<table class="tb10" align="center">
    <tr>
        <td><ctg:footer/></td>
    </tr>
</table>
</body>
</html>
