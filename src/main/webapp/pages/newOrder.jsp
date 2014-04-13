<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
<div style="width:960px;">
    <s:form action="createOrder" theme="simple">
        Enter new Order details : Order Id :<span style="color:green;"> <s:property value="orderId"/> </span> <br/>
        <s:hidden name="orderId"/>
        <s:iterator value="itemList" var="item" status="stat">
            <s:property value="#item.name"/>
            <s:property value="#item.price"/>
            <s:hidden name="orderDetailForms[%{#stat.index}].itemId" value="%{#item.id}"/>
            <s:textfield name="orderDetailForms[%{#stat.index}].quantity"/> <br/>
        </s:iterator>
        <s:submit value="Save Order"/>
         <a href="findCustomerOrders.action" style="text-decoration:none;">Cancel</a>
    </s:form>
</div>
</body>
</html>