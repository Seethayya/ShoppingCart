<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div style="width:960px;">
  <span style="text-align:center;margin-left:350px;font-size:20px;font-weight:bold;color:blue;">Welcome to Online Shopping cart</span>
    <s:set var="customer" value="#session.customer"/>
    <s:if test="#customer != null">
        <div><a href="signOut.action" style="text-decoration:none">Logout</a> </div>
    </s:if>
</div>