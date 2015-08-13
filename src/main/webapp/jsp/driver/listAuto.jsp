<%@ include file="/jsp/common/head.jsp" %>
<html>
<head>
    <title><fmt:message key="title.listAuto"/></title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="title5"><ctg:infoDate/></div>
<%@ include file="/jsp/elements/logOut.jsp"%>
<div class="block2">
    <div class="logo"></div>
    <form action="controller" method="post">
        <input type="hidden" name="page" value="toBidsDriver">
        <input type="submit" value="<fmt:message key="listAuto.back"/>" class="button4">
    </form>
    <table width="950" align="center">
        <tr>
            <td>
                <c:if test="${not empty listAuto}">
                    <table class="tb1">
                        <tr>
                            <td class="title5" colspan="3" align="center">
                                    <fmt:message key="listAuto.goodCondition"/>
                            </td>
                        </tr>
                        <tr class="tb4">
                            <td align="center"><fmt:message key="listAuto.idA"/></td>
                            <td align="center"><fmt:message key="listAuto.mark"/></td>
                            <c:forEach var="auto" items="${listAuto}">
                                <form action="controller" method="post">
                                    <input type="hidden" name="page" value="repair">
                                    <tr class="tb5">
                                        <td align="center"><input type="hidden" name="idAuto" value="${auto.id}">${auto.id}</td>
                                        <td align="center"><input type="hidden" name="condition" value="toRepair">${auto.mark}</td>
                                        <td align="center" width="140"><input type="submit" value="<fmt:message key="listAuto.repair"/>"></td>
                                    </tr>
                                </form>
                            </c:forEach>
                        </tr>
                    </table>
                </c:if>
            </td>
            <td>
                <c:if test="${not empty listAutoForRepair}">
                    <table class="tb1" align="right">
                        <tr>
                        <td  class="title5" colspan="3" align="center">
                                <fmt:message key="listAuto.forRepairs"/>
                        </td>
                        </tr>
                        <tr class="tb4">
                            <td align="center"><fmt:message key="listAuto.idA"/></td>
                            <td align="center"><fmt:message key="listAuto.mark"/></td>
                            <c:forEach var="auto" items="${listAutoForRepair}">
                                <form action="controller" method="post">
                                    <input type="hidden" name="page" value="repair">
                                    <tr class="tb5">
                                        <td align="center"><input type="hidden" name="idAuto" value="${auto.id}">${auto.id}</td>
                                        <td align="center"><input type="hidden" name="condition" value="fromRepair">${auto.mark}</td>
                                        <td align="center" width="140"><input type="submit" value="<fmt:message key="listAuto.fromRepair"/>"></td>
                                    </tr>
                                </form>
                            </c:forEach>
                        </tr>
                    </table>
                </c:if>
            </td>
        </tr>
    </table>
    <br><br><br><br><br>
</div>
<table class="tb10" align="center">
    <tr>
        <td><ctg:footer/></td>
    </tr>
</table>
</body>
</html>