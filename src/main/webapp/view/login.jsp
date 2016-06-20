<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Clinical-Management</title>
</head>
<body>
<div class="welcome">
    <h3>Welcome Doctor, please login below</h3>
</div>
<div class="login">
    <s:form action="login">
        <s:textfield name="username" label="User Name"></s:textfield>
        <s:textfield name="password" label="Password" type="password"></s:textfield>
        <s:submit value="Login" class="btn btn-default"></s:submit>
    </s:form>
</div>
<s:include value="master.jsp"></s:include>
</body>
</html>