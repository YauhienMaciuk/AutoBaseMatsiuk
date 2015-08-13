<%@ include file="head.jsp" %>
<html>
<head>
    <title><fmt:message key="title.login"/></title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="title5"><ctg:infoDate/></div>
<div class="block2">
    <div class="logo"></div>
    <br>
    <table align="center">
        <tr>
            <td align="right">
                <form action="controller" method="post">
                    <input type="hidden" name="page" value="login">
                    <fmt:message key="login.nick"/>
                    <input type="text" name="login" pattern="[A-Za-z0-9]{7,25}$"
                           maxlength="25" size="25" required placeholder="**********">
            </td>
        </tr>
        <tr>
            <td align="right">
                <fmt:message key="login.pass"/>
                <input type="password" name="password" pattern="[A-Za-z0-9]{7,25}$"
                       maxlength="25" size="25" required placeholder="**********">
            </td>
        </tr>
        <tr>
            <td align="right">
                <input type="submit" value="<fmt:message key="option.enter"/>" class="button">
                </form>
            </td>
        </tr>
        <tr>
            <td align="right">
                <form action="controller" method="post">
                    <input type="hidden" name="page" value="wantRegistration"><br>
                    <fmt:message key="option.registration"/>
                    <input type="submit" value="<fmt:message key="registration.sing"/> ">
                </form>
            </td>
        </tr>
    </table>
    <c:if test="${not empty incorrectLogPass}">
        <div class="title">
            <fmt:message key="login.incorrect"/></div>
    </c:if>
    <br><br><br><br><br><br><br><br><br><br><br>
</div>
<table class="tb10" align="center">
    <tr>
        <td><ctg:footer/></td>
    </tr>
</table>
</body>
</html>
