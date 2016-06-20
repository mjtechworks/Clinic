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
<h2>Welcome <s:property value="doctor.firstName"></s:property> <s:property value="doctor.lastName"></s:property></h2>
<br>
<h3>Your Patients</h3>
<table border="0" cellspacing="0" cellpadding="1">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Date of Birth</th>
        <th>Address</th>
        <th>Phone Number</th>
    </tr>
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