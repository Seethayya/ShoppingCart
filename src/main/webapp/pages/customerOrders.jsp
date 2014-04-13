
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
            <s:form action="existingOrder" theme="simple">
                <s:iterator value="orderForms" var="orderForm" status="stat">
                    <div style="width:800px;clear:both;">
                        <div style="margin-left:0px;">
                        <input type="radio" name="orderId" value="<s:property value="%{#orderForm.orderId}"/>"/>
                        Order Id : <s:property value="#orderForm.orderId"/>
                        Order Date : <s:property value="#orderForm.orderDate"/>
                        Total price : <s:property value="#orderForm.totalPrice"/>
                            <a href="deleteOrder?orderForm.id=<s:property value="%{#orderForm.id}"/>"
                               style="text-decoration:none;color:red;"> Delete order</a>
                        </div>
                    </div>
                </s:iterator>
                <div style="color:red;">
                    <s:actionerror/>
                </div>
                <s:submit value="Edit Order"/>
            </s:form>
        </s:else>
        <br/>
        <s:form theme="simple" action="newOrder.action">
             <s:submit value="New order"/>
        </s:form>
    </div>
</body>
</html>