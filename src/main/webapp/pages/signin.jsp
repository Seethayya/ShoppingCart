
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head><title>Simple jsp page</title></head>
<body>
     <s:form action="signIn.action" theme="simple">
     <div style="clear:both;">
       New User Sign in : <br/>
         UserName : <s:textfield name="customer.emailId"/><br/>
         Password : <s:password name="customer.password"/>
         <div style="color:red;">
             <s:actionerror/>
         </div>
         <s:submit value="signIn"/>
   </div>
    </s:form>
</body>
</html>