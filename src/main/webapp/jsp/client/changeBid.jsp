<%@ include file="/jsp/common/head.jsp" %>
<html>
<head>
    <title><fmt:message key="change.title"/></title>
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
    <form action="controller" method="post">
        <input type="hidden" name="page" value="listNewBids">
        <input type="submit" value="<fmt:message key="listAuto.back"/>" class="button4">
    </form>
    <form action="controller" method="post">
        <input type="hidden" name="page" value="changeBid">
        <table class="tb8" align="center">
            <tr>
                <td class="tb3" align="center">
                    <fmt:message key="change.header"/>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <fmt:message key="addBid.from"/>
                    <select name="fromCity">
                            <option disabled><fmt:message key="addBid.select"/></option>
                        <c:if test="${not empty bid}">
                            <option value="${bid.fromCity.nameCity}">${bid.fromCity.nameCity}</option>
                        </c:if>
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
                        <c:if test="${not empty bid}">
                            <option value="${bid.toCity.nameCity}">${bid.toCity.nameCity}</option>
                        </c:if>
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
                    <c:if test="${not empty bid}">
                        <input type="text" name="value" value="${bid.valueShipment}" size="10"
                               pattern="^[1-9]|^[1-8][0-9]|^[9][0]" required>
                    </c:if>
                    <c:if test="${not empty nameFromCity}">
                        <input type="text" name="value" value="${value}" size="10"
                               pattern="^[1-9]|^[1-8][0-9]|^[9][0]" required>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <fmt:message key="addBid.weight"/>
                    <c:if test="${not empty bid}">
                        <input type="text" name="weight" value="${bid.weightShipment}" size="10"
                               pattern="[1-9]|^[1][0-4]" required>
                    </c:if>
                    <c:if test="${not empty weight}">
                        <input type="text" name="weight" value="${weight}" size="10"
                               pattern="[1-9]|^[1][0-4]" required>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <fmt:message key="addBid.date"/>
                    <c:if test="${not empty bid}">
                        <input type="text" name="date" value="${bid.dateService}" size="8" class="tcal" required>
                    </c:if>
                    <c:if test="${not empty date}">
                        <input type="text" name="date" value="${date}" size="8" class="tcal" required>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td align="right"><br>
                    <c:if test="${not empty bid}">
                        <input type="hidden" name="idBid" value="${bid.id}">
                    </c:if>
                    <c:if test="${not empty idBid}">
                        <input type="hidden" name="idBid" value="${idBid}">
                    </c:if>
                    <input type="submit" value="<fmt:message key="addBid.change"/>" class="button">
                </td>
            </tr>
        </table>
    </form>
    </table>
    <div class="title">
        <c:if test="${not empty incorrectDate}">
            <fmt:message key="addBid.incorrectDate"/>
        </c:if>
        <c:if test="${not empty wrongCity}">
            <fmt:message key="addBid.wrong"/>
        </c:if>
    </div>
    <br><br><br><br><br>
</div>
<table class="tb10" align="center">
    <tr>
        <td><ctg:footer/></td>
    </tr>
</table>
</body>
</html>
