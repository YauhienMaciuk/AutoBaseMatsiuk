<%@ include file="/jsp/common/head.jsp" %>
<html>
<head>
    <title><fmt:message key="title.addBid"/></title>
    <link rel="stylesheet" type="text/css" href="/css/tcal.css"/>
    <script type="text/javascript" src="/js/tcal_en.js"></script>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="title5"><ctg:infoDate/></div>
<%@ include file="/jsp/elements/logOut.jsp" %>
<div class="block2">
    <div class="logo"></div>
    <br>
    <div class="title2">
    <form action="controller" method="post">
        <input type="hidden" name="page" value="readRule">
        <fmt:message key="addBid.rule"/>
        <input type="submit" value="<fmt:message key="addBid.move"/>">
    </form>
    </div>
    <form action="controller" method="post">
        <input type="hidden" name="page" value="wantCreateBid">
        <table class="tb8" align="center">
            <tr>
                <td class="tb3" align="center">
                    <fmt:message key="addBid.title"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <fmt:message key="addBid.from"/>
                    <select name="fromCity">
                            <option disabled><fmt:message key="addBid.select"/></option>
                        <c:if test="${not empty nameFromCity}">
                            <option value="${nameFromCity}">${nameFromCity}</option>
                        </c:if>
                        <c:forEach var="city" items="${listCities}">
                            <option value="${city.nameCity}">${city.nameCity}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <fmt:message key="addBid.to"/>
                    <select name="toCity">
                            <option disabled><fmt:message key="addBid.select"/></option>
                        <c:if test="${not empty nameToCity}">
                            <option value="${nameToCity}">${nameToCity}</option>
                        </c:if>
                        <c:forEach var="city" items="${listCities}">
                            <option value="${city.nameCity}">${city.nameCity}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr align="right">
                <td>
                    <fmt:message key="addBid.value"/>
                    <input type="text" name="value" value="${value}" size="10"
                           pattern="^[1-9]|^[1-8][0-9]|^[9][0]" required>
                </td>
            </tr>
            <tr align="right">
                <td>
                    <fmt:message key="addBid.weight"/>
                    <input type="text" name="weight" value="${weight}" size="10"
                           pattern="^[1-9]|^[1][0-4]" required>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <fmt:message key="addBid.date"/>
                    <input type="text" name="date" value="${date}" size="8" class="tcal" required>
                </td>
            </tr>
            <tr>
                <td align="right"><br>
                    <input type="submit" value="<fmt:message key="addBid.create"/> ">
                </td>
            </tr>
        </table>
    </form>
        <div class="title">
        <c:if test="${not empty incorrectDate}">
                <fmt:message key="addBid.incorrectDate"/>
        </c:if>
        <c:if test="${not empty wrongCity}">
                <fmt:message key="addBid.wrong"/>
        </c:if>
        <c:if test="${not empty bidCreated}">
                <fmt:message key="addBid.created"/>
        </c:if>
        <c:if test="${not empty bidNotCreated}">
                <fmt:message key="addBid.notCreated"/>
        </c:if>
        <c:if test="${canNotDelete}">
                <fmt:message key="addBid.canNotDelete"/>
        </c:if>
            <c:if test="${not empty impossibleChange}">
                <fmt:message key="change.impossible"/>
            </c:if>
        </div>
    <br>
    <div align="center">
    <form action="controller" method="post">
        <input type="hidden" name="page" value="listNewBids">
        <input type="submit" value="<fmt:message key="start.look"/>" class="button9">
    </form>
    </div>
    <br><br><br>
</div>
<table class="tb10" align="center">
    <tr>
        <td><ctg:footer/></td>
    </tr>
</table>
</body>
</html>
