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
    <br><br><br><br>
        <table align="center">
            <tr>
                <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="page" value="createBid">
                        <input type="submit" value="<fmt:message key="start.create"/>" class="button9">
                    </form>
                </td>
                <td>
                    <form action="controller" method="post">
                    <input type="hidden" name="page" value="listNewBids">
                    <input type="submit" value="<fmt:message key="start.look"/>" class="button9">
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
