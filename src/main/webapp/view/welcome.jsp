<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Clinical-Management</title>
</head>
<body>
<div class="welcome">
    <h3>Doctor <s:property value="doctor.firstName"></s:property> <s:property value="doctor.lastName"></s:property></h3>
    <spa class="logout">
        <s:form action="logout">
            <s:submit value="Logout" cssClass="logout-button"></s:submit>
        </s:form>
    </spa>
</div>
<h4 class="table-name">Your Patients</h4>
<div class="create">
    <s:form action="create">
        <s:submit value="Add New Patient" cssClass="btn btn-primary"></s:submit>
    </s:form>
</div>
<div class="search">
    <s:form action="login" method="GET">
        <s:textfield name="patientName" label="Patient Name"></s:textfield>
        <s:submit value="Search" cssClass="btn btn-default"></s:submit>
    </s:form>
</div>
<table class="table table-hover patient-table">
    <thead>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Date of Birth</th>
        <th>Address</th>
        <th>Phone Number</th>
    </tr>
    </thead>
    <s:iterator value="patients">
        <tr>
            <td><s:property value="firstName"/></td>
            <td><s:property value="lastName"/></td>
            <td><s:property value="dob"/></td>
            <td><s:property value="address"/></td>
            <td><s:property value="phoneNumber"/></td>
        </tr>
    </s:iterator>
</table>
<s:include value="master.jsp"></s:include>
</body>
</html>