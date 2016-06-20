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
<s:include value="login.jsp"></s:include>
<s:include value="master.jsp"></s:include>
<div class="alert alert-danger danger-message">
    <h4>User Name or Password is wrong</h4>
</div>
</body>
</html>