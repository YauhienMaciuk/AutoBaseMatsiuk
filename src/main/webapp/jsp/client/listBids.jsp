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
    <form action="controller" method="post">
        <input type="hidden" name="page" value="createBid">
        <input type="submit" value="<fmt:message key="start.create"/>" class="button9">
    </form>
    <table align="center">
        <tr>
            <td>
                <form action="controller" method="post">
                    <input type="hidden" name="page" value="listNewBids">
                    <input type="submit" value="<fmt:message key="listBids.sent"/> " class="button6">
                </form>
            </td>
            <td>
                <form action="controller" method="post">
                    <input type="hidden" name="page" value="listRuntimeBids">
                    <input type="submit" value="<fmt:message key="listBids.runtime"/> " class="button7">
                </form>
            </td>
            <td>
                <form action="controller" method="post">
                    <input type="hidden" name="page" value="listPerformedBids">
                    <input type="submit" value="<fmt:message key="listBids.performed"/> " class="button8">
                </form>
            </td>
        </tr>
    </table>
    <div class="title">
        <c:if test="${not empty changedBid}">
            <fmt:message key="addBid.changed"/>
        </c:if>
        <c:if test="${not empty emptyListSpentBidsClient}">
            <fmt:message key="listBids.emptySentBids"/>
        </c:if>
        <c:if test="${not empty emptyListPerformedBidsClient}">
            <fmt:message key="listBids.emptyPerformedBids"/>
        </c:if>
        <c:if test="${not empty emptyListRuntimeBidsClient}">
            <fmt:message key="listBids.emptyListRuntime"/>
        </c:if>
    </div>
    <c:if test="${not empty newBids}">
        <div class="title2"><fmt:message key="listBids.listSent"/><br><br></div>
        <table class="tb1" align="center">
            <%@include file="/jsp/elements/headerTable.jsp" %>
            <c:forEach var="bid" items="${newBids}">
                <tr class="tb6">
                    <form action="controller" method="post">
                        <input type="hidden" name="page" value="wantChangeBid">
                        <td align="center"><input type="hidden" name="idBid"
                                                  value="${bid.id}">${bid.id}</td>
                        <td align="center">${bid.fromCity.nameCity}</td>
                        <td align="center">${bid.toCity.nameCity}</td>
                        <td align="center">${bid.valueShipment}</td>
                        <td align="center">${bid.weightShipment}</td>
                        <td align="center">${bid.dateService}</td>
                        <td><input type="submit" value="<fmt:message key="addBid.change"/>" class="button"></td>
                    </form>
                    <form action="controller">
                        <input type="hidden" name="page" value="wantDeleteBid">
                        <input type="hidden" name="actionClient" value="cancelBid">
                        <input type="hidden" name="idBid" value="${bid.id}">
                        <td><input type="submit" value="<fmt:message key="addBid.cancel"/>" class="button"></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${not empty runtimeBids}">
        <div class="title2"><fmt:message key="listBids.listRuntime"/><br><br></div>
        <table class="tb1" align="center">
            <%@include file="/jsp/elements/headerTable.jsp" %>
            <c:forEach var="bid" items="${runtimeBids}">
                <tr class="tb6">
                    <td align="center">${bid.id}</td>
                    <td align="center">${bid.fromCity.nameCity}</td>
                    <td align="center">${bid.toCity.nameCity}</td>
                    <td align="center">${bid.valueShipment}</td>
                    <td align="center">${bid.weightShipment}</td>
                    <td align="center">${bid.dateService}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${not empty performedBids}">
        <td class="tb3" align="center">
            <div class="title2"><fmt:message key="listBids.listPerformed"/><br><br></div>
            <table class="tb1" align="center">
                <%@include file="/jsp/elements/headerTable.jsp" %>
                <c:forEach var="performedBid" items="${performedBids}">
                    <tr class="tb6">
                        <form action="controller" method="post">
                            <input type="hidden" name="page" value="wantDeleteBid">
                            <td align="center"><input type="hidden" name="idBid"
                                                      value="${performedBid.id}">${performedBid.id}</td>
                            <td align="center">${performedBid.fromCity.nameCity}</td>
                            <td align="center">${performedBid.toCity.nameCity}</td>
                            <td align="center">${performedBid.valueShipment}</td>
                            <td align="center">${performedBid.weightShipment}</td>
                            <td align="center">${performedBid.dateService}</td>
                            <input type="hidden" name="actionClient" value="deletePerformedBid">
                            <td><input type="submit" value="<fmt:message key="addBid.delete"/>" class="button"></td>
                        </form>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </c:if>
    <br><br><br><br><br><br><br>
</div>
<table class="tb10" align="center">
    <tr>
        <td><ctg:footer/></td>
    </tr>
</table>
</body>
</html>
