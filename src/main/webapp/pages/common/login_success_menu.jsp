<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%--如果已经登录，则显示 登录 成功之后的用户信息。--%>
<c:if test="${not empty sessionScope.user}">
    <div>
        <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="order/client/listOrder.do">我的订单</a>
        <a href="user/logout.do">注销</a>&nbsp;&nbsp;&nbsp;
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</c:if>
<%--如果用户还没有登录，显示     【登录 和注册的菜单】 --%>
<c:if test="${empty sessionScope.user}">
    <div>
        <a href="pages/user/login.jsp">登录</a> |
        <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        <a href="pages/cart/cart.jsp">购物车</a>
    </div>
</c:if>
