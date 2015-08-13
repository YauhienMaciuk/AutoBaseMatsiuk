<%@ include file="/jsp/common/head.jsp" %>
<html>
<head>
    <title><fmt:message key="title.registration"/></title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="title5"><ctg:infoDate/></div>
<div class="block2">
    <div class="logo"></div>
    <form action="controller" method="post">
        <input type="hidden" name="page" value="startPage">
        <input type="submit" value="<fmt:message key="listAuto.back"/>" class="button4">
    </form>
    <table class="tb8" align="center">
        <form action="controller" method="post">
            <tr>
                <td class="tb3" align="center">
                    <fmt:message key="registration.please"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <input type="hidden" name="page" value="registration">
                    <fmt:message key="registration.first.name"/>
                    <input type="text" name="firstName" value="${firstName}" pattern="[A-Za-z\u0410-\u042f\u0430-\u044f]{2,25}$"
                           maxlength="25" size="15" required placeholder="*************************">
                </td>
            </tr>
            <tr>
                <td align="right">
                    <fmt:message key="registration.last.name"/>
                    <input type="text" name="lastName" value="${lastName}" pattern="[A-Za-z\u0410-\u042f\u0430-\u044f]{2,25}$"
                           maxlength="25" size="15" required placeholder="*************************">
                </td>
            </tr>
            <tr>
                <td align="right">
                    <fmt:message key="option.nick"/>
                    <input type="text" name="login" value="${login}" pattern="[A-Za-z0-9]{7,25}$" min="7" maxlength="25" size="15"
                           required placeholder="*************************">
                </td>
            </tr>
            <tr>
                <td align="right">
                    <fmt:message key="option.pass"/>
                    <input type="password" name="password" pattern="[A-Za-z0-9]{7,25}$" min="7" maxlength="25"
                           size="15" required placeholder="*************************">
                </td>
            </tr>
            <tr>
                <td align="right">
                    <fmt:message key="option.confirmPass"/>
                    <input type="password" name="confirmPassword" pattern="[A-Za-z0-9]{7,25}$" min="7" maxlength="25"
                           size="15" required placeholder="*************************">
                </td>
            </tr>
            <tr>
                <td align="right"><br>
                    <input type="submit" value="<fmt:message key="registration.sing"/>">
                </td>
            </tr>
        </form>
        <c:if test="${not empty incorrectConfirmPass}">
            <div class="title">
                <fmt:message key="option.incorrectPass"/></div>
            </c:if>
        <c:if test="${not empty errorRegistration}">
            <div class="title">
                <fmt:message key="registration.error"/></div>
        </c:if>
        <c:if test="${not empty userAlreadyExists}">
            <div class="title">
                <fmt:message key="registration.userAlreadyExists"/></div>
        </c:if>
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
