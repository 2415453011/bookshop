<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加图书</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp"%>


    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color:red;
        }

        input {
            text-align: center;
        }
    </style>
    <script type="text/javascript">
        // 给删除的a标签绑定单击事件，用于删除的确认提示操作
        $(function () {
            $("#btn").click(function () {
                /**
                 * confirm时确认提示框函数
                 * 参数是它的提示内容
                 * 它有两个按钮，一个确认，一个取消
                 * 返回true表示确认，返回false表示点击取消
                 */
                <%--alert(${books.id})--%>
                return confirm("你确定要添加图书吗?");
            })
        });
    </script>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.png" >
    <span class="wel_word">编辑图书</span>

    <%-- 静态包含 manager管理模块的菜单  --%>
    <%@include file="/pages/common/manager_menu.jsp"%>


</div>


<div id="main">
    <form action="book/manager/add.do" method="post">
        <input type="hidden" name="pageNo" value="${param.pageNo}">
        <input type="hidden" name="id" value="${requestScope.book.id}" />
        <table>
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>
            <tr>
                <td><input name="name" type="text" value=""/></td>
                <td><input name="price" type="text" value=""/></td>
                <td><input name="author" type="text" value=""/></td>
                <td><input name="sales" type="text" value=""/></td>
                <td><input name="stock" type="text" value=""/></td>
                <td><input id="btn" type="submit" value="添加"/></td>
            </tr>
        </table>
    </form>
</div>


<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>


</body>
</html>