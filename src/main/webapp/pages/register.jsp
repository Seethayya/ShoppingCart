<%--
  Created by IntelliJ IDEA.
  User: Seethayya
  Date: 4/11/14
  Time: 12:50 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head><title>Simple jsp page</title>
    <link rel="stylesheet" href="css/jquery-ui-1.10.4.css" type="text/css">
    <script type="text/javascript" src="js/jQuery-1.11.0.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.10.4.js"></script>
    <script type="text/javascript" src="js/jQuery-autocompletor.js"></script>
    <script type="text/javascript">
    </script>
    <style type="text/css">
        .ui-menu-item {
    font-size: 11px;}
    </style>
</head>
<body>
<div style="width:960px;">
 Please enter following details : <br/>
 <s:form action="register" theme="simple">
     <s:hidden name="customer.id"/>
    First Name : <s:textfield name="customer.firstName"/>   <br/>
    Middle Name : <s:textfield name="customer.middleName"/> <br/>
    last Name : <s:textfield name="customer.lastName"/>          <br/>
    Phone no : <s:textfield name="customer.phoneNo"/>                 <br/>
    Email Id : <s:textfield name="customer.emailId"/>                      <br/>
    Password : <s:password name="customer.password"/>
    Country : <s:textfield name="customer.country" id="countryName"/>
     <br/>
     <s:submit value="register"/>
     <br/>
     <div style="color:red;">
     <s:actionerror/>
         </div>
     <div style="color:blue;">
     <s:actionmessage/>
         </div>
</s:form>
</div>
</body>
</html>