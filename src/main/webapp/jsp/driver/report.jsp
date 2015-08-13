<%@ include file="/jsp/common/head.jsp" %>
<html>
<head>
    <title><fmt:message key="title.report"/></title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="title5"><ctg:infoDate/></div>
<%@ include file="/jsp/elements/logOut.jsp"%>
<div class="block2">
    <div class="logo"></div>
    <div class="title4">
        <br>
        <fmt:message key="report.title1"/>
        <fmt:message key="report.title2"/>
        <br><br>
        <table class="tb8" align="center">
            <form action="controller" method="post">
                <tr>
                    <td align="right">
                        <input type="hidden" name="page" value="changeConditionAuto">
                        <fmt:message key="report.good"/>
                        <input type="radio" name="condition" value="good" checked="checked"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <fmt:message key="report.repair"/>
                        <input type="radio" name="condition" value="repair"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <input type="submit" value="<fmt:message key="report.send"/> " class="button5">
                    </td>
                </tr>
            </form>
        </table>
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
