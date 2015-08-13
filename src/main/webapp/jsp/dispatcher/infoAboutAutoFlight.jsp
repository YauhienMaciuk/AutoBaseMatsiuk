<%@ include file="/jsp/common/head.jsp" %>
<html>
<head>
    <title></title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="title5"><ctg:infoDate/></div>
<%@ include file="/jsp/elements/logOut.jsp"%>
<div class="block2">
    <div class="logo"></div>
    <br>
        <div class="title2"><fmt:message key="infoAboutAutoFlight.title"/></div>
    <br>
        <table class="tb2" align="center">
                <%@include file="/jsp/elements/headerTable.jsp"%>
            <tr class="tb6">
                <td align="center">${bid.id}</td>
                <td align="center">${bid.fromCity.nameCity}</td>
                <td align="center">${bid.toCity.nameCity}</td>
                <td align="center">${bid.valueShipment}</td>
                <td align="center">${bid.weightShipment}</td>
                <td align="center">${bid.dateService}</td>
            </tr>
        </table><br>
    <div align="center">
        <form action="controller" method="post">
            <input type="hidden" name="page" value="toAutoFlight">
            <input type="submit" value="<fmt:message key="listAuto.back"/>" class="button4">
        </form>
    </div>
    <br><br><br><br><br><br>
    </div>
<table class="tb10" align="center">
    <tr>
        <td><ctg:footer/></td>
    </tr>
</table>
</body>
</html>
