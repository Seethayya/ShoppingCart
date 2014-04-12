
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
    <div style="width:960px;">
        <s:if test="orderForms == null || orderForms.isEmpty()">
            You do not have orders. Create new order.
        </s:if>
        <s:else>
            <s:form theme="simple">
                <s:iterator value="orderForms" var="orderForm" status="stat">
                    Order Id : <s:property value="#orderForm.orderId"/>
                    Order Date : <s:property value="#orderForm.orderDate"/>
                    Total price : <s:property value="#orderForm.totalPrice"/> <br/>
                </s:iterator>
            </s:form>
        </s:else>
        <s:form theme="simple" action="newOrder.action">
            Create New order <s:submit/>
        </s:form>
    </div>
</body>
</html>