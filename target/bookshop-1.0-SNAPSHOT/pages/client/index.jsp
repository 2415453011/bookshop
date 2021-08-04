<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>书城首页</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>
	<script type="application/javascript">
		$(function () {
			//	给加入购物车绑定单击事件
			$("button.addToCart").click(function () {
				var id = $(this).attr("bookId");
				// $.getJSON("cart/addCart.do","id=" + id,function (data) {
				// 	$("#cartTotalCount").text("您的购物车中有 " + data.totalCount + " 件商品");
				// 	$("#lastName").text(data.lastName);
				// })
				// location.href = "cartServlet?action=addItem&id=" + bookId;
				$.ajax({
					type:"post",
					url:"cart/addCart.do",
					data:"id=" + id,
					dataType:"json",
					success:function (resp) {
						$("#cartTotalCount").text("您的购物车中有 " + resp.totalCount + " 件商品");
						$("#lastName").text(resp.lastName);
					}
				})

			})

		})
	</script>
</head>
<body>

<div id="header">
	<img class="logo_img" alt="" src="static/img/logo.png" >
	<span class="wel_word">网上书城</span>
	<%@ include file="/pages/common/login_success_menu.jsp"%>

</div>

<div id="main">
	<div id="book">
		<div class="book_cond">
			<form action="book/user/pageByPrice.do" method="get">
				价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
				<input id="max" type="text" name="max" value="${param.max}"> 元
				<input type="submit" value="查询" />
			</form>
		</div>
		<div style="text-align: center">

			<c:if test="${not empty sessionScope.cart.items}">
				<%--购物车非空的输出--%>
				<span id="cartTotalCount">您的购物车中有 ${sessionScope.cart.totalCount} 件商品</span>
				<div>
					您刚刚将<span style="color: red" id="lastName">${sessionScope.lastName}</span>加入到了购物车中
				</div>
			</c:if>
			<c:if test="${empty sessionScope.cart.items}">
				<%--购物车为空的输出--%>
				<span id="cartTotalCount"> </span>
				<div>
					<span style="color: red" id="lastName">当前购物车为空</span>
				</div>
			</c:if>

		</div>

	<c:forEach items="${page.item}" var="book">
		<div class="b_list">
			<div class="img_div">
				<img class="book_img" alt="" src="${book.img_path}" />
			</div>
			<div class="book_info">
				<div class="book_name">
					<span class="sp1">书名:</span>
					<span class="sp2">${book.name}</span>
				</div>
				<div class="book_author">
					<span class="sp1">作者:</span>
					<span class="sp2">${book.author}</span>
				</div>
				<div class="book_price">
					<span class="sp1">价格:</span>
					<span class="sp2">￥${book.price}</span>
				</div>
				<div class="book_sales">
					<span class="sp1">销量:</span>
					<span class="sp2">${book.sales}</span>
				</div>
				<div class="book_amount">
					<span class="sp1">库存:</span>
					<span class="sp2">${book.stock}</span>
				</div>
				<div class="book_add">
					<button bookId="${book.id}" class="addToCart">加入购物车</button>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<%@include file="/pages/common/page_nav.jsp"%>

</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>

</body>
</html>