<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:form id="orderForm" name="orderForm" action="createOrder" theme="simple">
Enter new Order details : Order Id :<span style="color:green;"> <s:property value="orderId"/> </span> <br/>
<s:hidden name="orderId"/>
<s:iterator value="itemList" var="item" status="stat">
<s:property value="#item.name"/>
<s:property value="#item.price"/>
<s:hidden name="orderDetailForms[%{#stat.index}].itemId" value="%{#item.id}"/>
<s:textfield name="orderDetailForms[%{#stat.index}].quantity"/> <br/>
</s:iterator>
<a onclick="processAndExecuteScript('orderFormMainDiv', 'orderFormSuccessScriptDiv', 'orderForm','createOrder', true);" style="">Save
    order</a>
<s:submit value="Save Order"/>
<a onclick="submitForm(document.orderForm, 'findCustomerOrders.action');" style="text-decoration:none;">Cancel</a>
<s:actionmessage/>
</s:form>

<div id="orderFormSuccessScriptDiv">
    <script type="text/javascript">
        console.log("orderForm success script div");
        <s:if test = "hasActionMessages()" >
        setTimeout("submitForm(document.orderForm, 'findCustomerOrders.action')", 10000);
        </s:if>
    </script>
</div>