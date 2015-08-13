<%@ include file="/jsp/common/head.jsp" %>
<html>
<head>
    <title><fmt:message key="title.rule"/></title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="title5"><ctg:infoDate/></div>
<%@ include file="/jsp/elements/logOut.jsp"%>
<div class="block2">
    <div class="logo"></div>
    <br>
    <br>
    <table class="tb11" align="center">
        <tr>
            <td>
                <div class="title2">
                    <fmt:message key="rule.title"/><br>
                </div>
            </td>
        </tr>
        <tr>
            <td>    <fmt:message key="rule.sentence1"/></td>
        </tr>
        <tr>
            <td><fmt:message key="rule.sentence2"/></td>
        </tr>
        <tr>
            <td><fmt:message key="rule.sentence3"/></td>
        </tr>
        <tr>
            <td><fmt:message key="rule.sentence4"/></td>
        </tr>
        <tr>
            <td><fmt:message key="rule.sentence5"/></td>
        </tr>
        <tr>
            <td><fmt:message key="rule.sentence6"/></td>
        </tr>
        <tr>
            <td>    <fmt:message key="rule.sentence7"/></td>
        </tr>
    </table>
    <br>
    <table align="center">
        <tr>
            <td>
                <form action="controller" method="post">
                <input type="hidden" name="page" value="backToAddBid">
                <input type="submit" value="<fmt:message key="listAuto.back"/>" class="button4">
                </form>
            </td>
        </tr>
    </table>
    <br><br><br><br>
    </div>
<table class="tb10" align="center">
    <tr>
        <td><ctg:footer/></td>
    </tr>
</table>
</body>
</html>
