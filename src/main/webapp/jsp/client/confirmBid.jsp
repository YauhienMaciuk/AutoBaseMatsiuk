<%@ include file="/jsp/common/head.jsp" %>
<html>
<head>
    <title><fmt:message key="title.confirmBid"/></title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="title5"><ctg:infoDate/></div>
<%@ include file="/jsp/elements/logOut.jsp" %>
<div class="block2">
    <div class="logo"></div>
    <br>
    <div class="title2">
        <fmt:message key="confirm.title"/>
        <br><br>
        <table class="tb2" align="center">
            <tr class="tb4">
                <td align="center"><fmt:message key="addBid.fromCity"/></td>
                <td align="center"><fmt:message key="addBid.toCity"/></td>
                <td align="center"><fmt:message key="addBid.valueShipment"/></td>
                <td align="center"><fmt:message key="addBid.weightShipment"/></td>
                <td align="center"><fmt:message key="addBid.date"/></td>
            </tr>
            <tr class="tb5">
                <td align="center">${nameFromCity}</td>
                <td align="center">${nameToCity}</td>
                <td align="center">${value}</td>
                <td align="center">${weight}</td>
                <td align="center">${date}</td>
            </tr>
        </table>
        <br>
        <table align="center">
            <tr>
                <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="page" value="yesAddBid">
                        <input type="hidden" name="nameFromCity" value="${nameFromCity}">
                        <input type="hidden" name="nameToCity" value="${nameToCity}">
                        <input type="hidden" name="value" value="${value}">
                        <input type="hidden" name="weight" value="${weight}">
                        <input type="hidden" name="date" value="${date}">
                        <input type="submit" value="<fmt:message key="confirmDelete.yes"/>" class="button5">
                    </form>
                </td>
                <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="page" value="backToAddBid">
                        <input type="submit" value="<fmt:message key="confirmDelete.not"/>" class="button5">
                    </form>
                </td>
            </tr>
        </table>
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
