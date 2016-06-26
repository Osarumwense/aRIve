<%--<!DOCTYPE html>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<HTML>

<TITLE>aRIve</TITLE>
<spring:url value="/resources" var="resourcesPath" />
<script src="${resourcesPath}/resources/index.js"></script>

<form>
    BUS ID: <br><input id="busID" type="text" name="busID"><br>
    DESTINATION:  <br><input id="dest"type="text" name="dest"><br>
    BUS Number:  <br><input id="busNumber"type="text" name="busNumber"><br>

    <input id="submit" type="submit" value="Submit" >
</form>

</HTML>
