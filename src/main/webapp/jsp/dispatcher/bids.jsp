<%@ include file="/jsp/common/head.jsp" %>
<html>
<head>
    <title><fmt:message key="title.bids"/></title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>
<div class="title5"><ctg:infoDate/></div>
<%@ include file="/jsp/elements/logOut.jsp" %>
<div class="block2">
    <div class="logo"></div>
    <table align="center">
    <tr>
        <td>
            <form action="controller" method="post">
                <input type="hidden" name="page" value="listNewBids">
                <input type="submit" value="<fmt:message key="bids.newBids"/> " class="button6">
            </form>
        </td>
        <td>
            <form action="controller" method="post">
                <input type="hidden" name="page" value="toAutoFlight">
                <input type="submit" value="<fmt:message key="bids.acceptedBids"/> " class="button7">
            </form>
        </td>
        <td>
            <form action="controller" method="post">
                <input type="hidden" name="page" value="listPerformedBids">
                <input type="submit" value="<fmt:message key="bids.performed"/> " class="button8">
            </form>
        </td>
    </tr>
</table>
    <div class="title">
        <c:if test="${not empty successfullyCreated}">
            <fmt:message key="autoFlight.created"/>
        </c:if>
        <c:if test="${not empty impossibleCancelAutoFlight}">
            <fmt:message key="bids.impossibleCancelAutoFlight"/>
        </c:if>
        <c:if test="${not empty successfullyCancel}">
            <fmt:message key="bids.successfullyCancel"/>
        </c:if>
        <c:if test="${not empty emptyListNewBids}">
            <fmt:message key="bids.emptyListNewBids"/>
        </c:if>
        <c:if test="${not empty emptyListPerformedBids}">
            <fmt:message key="bids.emptyListPerformedBids"/>
        </c:if>
        <c:if test="${not empty emptyListAcceptedBids}">
            <fmt:message key="bids.emptyListAcceptedBids"/>
        </c:if>
    </div>
    <c:if test="${not empty newBids}">
        <div class="title2"><fmt:message key="bids.newBids"/><br></div>
        <table class="tb1" align="center">
            <%@include file="/jsp/elements/headerTable.jsp" %>
            <c:forEach var="bid" items="${newBids}">
                <form action="controller" method="post">
                    <input type="hidden" name="page" value="acceptBid">
                    <tr class="tb6">
                        <td align="center"><input type="hidden" name="idBid" value="${bid.id}">${bid.id}</td>
                        <td align="center">${bid.fromCity.nameCity}</td>
                        <td align="center">${bid.toCity.nameCity}</td>
                        <td align="center"><input type="hidden" name="value" value="${bid.valueShipment}">${bid.valueShipment}</td>
                        <td align="center"><input type="hidden" name="weight" value="${bid.weightShipment}">${bid.weightShipment}</td>
                        <td align="center">${bid.dateService}</td>
                        <td><input type="submit" value="<fmt:message key="bids.accept"/>" class="button"></td>
                    </tr>
                </form>
            </c:forEach>
            <c:if test="${not empty notAvailableAuto}">
                <div class="title">
                    <fmt:message key="bids.sorry"/></div>
            </c:if>
        </table>
    </c:if>
    <c:if test="${not empty listAcceptedBids}">
    <div class="title2"><fmt:message key="bids.acceptedBids"/><br></div>
        <table class="tb1" align="center">
            <tr>
                <td>
                    <table>
                        <tr class="tb4">
                            <td align="center"><fmt:message key="listAuto.idA"/></td>
                        </tr>
                        <c:forEach var="bid" items="${listAcceptedBids}">
                            <tr class="tb6">
                                <td align="center">${bid.id}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <td>
                    <table>
                        <tr class="tb4">
                            <td align="center"><fmt:message key="bids.driver"/></td>
                        </tr>
                        <c:forEach var="driver" items="${listDrivers}">
                            <tr class="tb6">
                                <td align="center">${driver.firstName} ${driver.lastName}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <td>
                    <table>
                        <tr class="tb4">
                            <td align="center"><fmt:message key="bids.auto"/></td>
                        </tr>
                        <c:forEach var="auto" items="${listAuto}">
                            <tr class="tb6">
                                <td align="center">${auto.mark}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <td>
                    <table>
                        <tr class="tb4">
                            <td align="center"><fmt:message key="bids.idAuto"/></td>
                        </tr>
                        <c:forEach var="auto" items="${listAuto}">
                            <tr class="tb6">
                                <td align="center">${auto.id}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <td>
                    <table>
                        <tr class="tb4">
                            <td align="center"><fmt:message key="bids.infoAboutAutoFlight"/></td>
                        </tr>
                        <c:forEach var="driver" items="${listDrivers}">
                            <form action="controller" method="post">
                                <tr class="tb6">
                                    <td>
                                        <input type="hidden" name="page" value="infoAboutAutoFlight">
                                        <input type="hidden" name="idDriver" value="${driver.id}">
                                        <input type="submit" value="<fmt:message key="bids.look"/>" class="button">
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </table>
                </td>
                <td>
                    <table>
                        <tr class="tb4">
                            <td align="center"><fmt:message key="bids.action"/></td>
                        </tr>
                        <c:forEach var="bid" items="${listAcceptedBids}">
                            <form action="controller" method="post">
                                <tr class="tb6">
                                    <td>
                                        <input type="hidden" name="page" value="wantDeleteBid">
                                        <input type="hidden" name="actionClient" value="cancelAutoFlight">
                                        <input type="hidden" name="idBid" value="${bid.id}">
                                        <input type="submit" value="<fmt:message key="bids.cancel"/>" class="button">
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </table>
                </td>
            </tr>
        </table>
    </c:if>
    <c:if test="${not empty performedBids}">
    <div class="title2"><fmt:message key="bids.performed"/><br></div>
        <table class="tb1" align="center">
            <%@include file="/jsp/elements/headerTable.jsp" %>
            <c:forEach var="bid" items="${performedBids}">
                <form action="controller" method="post">
                    <input type="hidden" name="page" value="wantDeleteBid">
                    <tr class="tb6">
                        <td align="center"><input type="hidden" name="idBid" value="${bid.id}">${bid.id}</td>
                        <td align="center">${bid.fromCity.nameCity}</td>
                        <td align="center">${bid.toCity.nameCity}</td>
                        <td align="center">${bid.valueShipment}</td>
                        <td align="center">${bid.weightShipment}</td>
                        <td align="center">${bid.dateService}</td>
                        <input type="hidden" name="actionClient" value="deletePerformedAutoFlight">
                        <td><input type="submit" value="<fmt:message key="addBid.delete"/>" class="button"></td>
                    </tr>
                </form>
            </c:forEach>
        </table>
    </c:if>
    </form>
    <br><br><br>
</div>
<table class="tb10" align="center">
    <tr>
        <td><ctg:footer/></td>
    </tr>
</table>
</body>
</html>
