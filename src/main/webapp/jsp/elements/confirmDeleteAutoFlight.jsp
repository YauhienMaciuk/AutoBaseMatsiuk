<%@ include file="/jsp/common/head.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<table align="center">
    <tr>
        <td>
            <form action="controller" method="post">
                <input type="hidden" name="page" value="cancelAutoFlight">
                <input type="submit" value="<fmt:message key="confirmDelete.yes"/> " class="button5">
            </form>
        </td>
        <td>
            <form action="controller" method="post">
                <input type="hidden" name="page" value="toAutoFlight">
                <input type="submit" value="<fmt:message key="confirmDelete.not"/>" class="button5">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
