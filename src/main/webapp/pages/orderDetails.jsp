<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
<div style="width:960px;">
    <s:form action="updateOrder" theme="simple">
        Update existing Order details : Order Id : <s:property value="orderId"/>
        <s:hidden name="orderId"/>
        <s:set var="orderDetailFormsSize" value="%{orderForm.orderDetailForms.size()}"/>
        Size1:<s:property value="#orderDetailFormsSize"/>:
        <s:iterator value="itemList" var="item" status="stat">
            <s:set var="isItemFound" value="false"/>
            <s:set var="formIndex" value="%{#stat.count+#orderDetailFormsSize}"/>
            <s:iterator value="orderForm.orderDetailForms" var="orderDetailForm" status="innerStat">
                <s:if test="#orderDetailForm.itemId == #item.id">
                    <s:property value="#item.name"/>
                    <s:property value="#item.price"/>
                    <s:set var="isItemFound" value="true"/>
                    <s:hidden name="orderDetailForms[%{#innerStat.index}].itemId" value="%{#item.id}"/>
                    <s:hidden name="orderDetailForms[%{#innerStat.index}].id" value="%{#orderDetailForm.id}"/>
                    <s:textfield name="orderDetailForms[%{#innerStat.index}].quantity" value="%{#orderDetailForm.quantity}"/> <br/>
                </s:if>
            </s:iterator>
            <s:if test="!#isItemFound">
                <s:property value="#item.name"/>
                <s:property value="#item.price"/>
                <s:hidden name="orderDetailForms[%{#formIndex}].itemId" value="%{#item.id}"/>
                <s:textfield name="orderDetailForms[%{#formIndex}].quantity"/> <br/>
            </s:if>
        </s:iterator>
        <s:submit value="save"/>
        <br/>
        <a href="findCustomerOrders.action" style="text-decoration:none;">Cancel</a>
    </s:form>
</div>
</body>
</html>