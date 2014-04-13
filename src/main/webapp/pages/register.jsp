<%--
  Created by IntelliJ IDEA.
  User: Seethayya
  Date: 4/11/14
  Time: 12:50 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head><title>Simple jsp page</title></head>
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
    Password : <s:password name="customer.password"/>                           <br/>
     <s:submit />
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