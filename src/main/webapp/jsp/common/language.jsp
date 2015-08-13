<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="./css/css.css" type="text/css"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="pagecontent_en"/>
<html>
<head>
    <title><fmt:message key="title.language"/></title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="block1">
    <div class="logo"></div>
    <br>

    <div id="form_A">
        <div class="title"><fmt:message key="lang.select"/></div>
        <br>
        <%--<table align="center">--%>
            <%--<tr>--%>
                <%--<form action="controller" method="post">--%>
                    <%--<input type="hidden" name="page" value="lang">--%>
                    <%--<td>--%>
                        <%--<input type="submit" name="language" value="en"--%>
                               <%--style="background-image: url(/images/GB.jpg);" class="button2">--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--<input type="submit" name="language" value="ru"--%>
                               <%--style="background-image: url(/images/rus.jpg);" class="button3">--%>
                    <%--</td>--%>
                <%--</form>--%>
            <%--</tr>--%>
        <%--</table>--%>


        <table align="center">
            <tr>
                <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="page" value="lang">
                        <input type="hidden" name="language" value="en">
                        <input type="submit" value="" style="background-image: url(/images/GB.jpg);" class="button2">
                    </form>
                </td>
                <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="page" value="lang">
                        <input type="hidden" name="language" value="ru">
                        <input type="submit" value="" style="background-image: url(/images/rus.jpg);" class="button3">
                    </form>
                </td>
            </tr>
        </table>


    </div>
</div>
</body>
</html>
