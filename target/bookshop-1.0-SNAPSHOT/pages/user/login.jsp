<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>小明同学书城会员登录页面</title>
    <%--    静态包含 base标签、css样式、jQuery--%>
    <%@ include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $("#code_img").click(function () {
            // alert("213");
            //在事件响应的function函数中有一个this对象.这个this对象,是当前正在响应事件的dom对象
            //src属表示验证码img标签的图片路径.可读可写
            this.src = "${basePath}kaptcha.jpg?d=" + new Date();
            <%--this.src = "${basePath}kaptcha.jpg"--%>
        });
        $("#sub_btn").click(function () {
            var code = $("#code").val();
            if (code == ""){
                $(".errorMsg").text("验证码输入有误！");
                return false;
            }
            return true;
        })
    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.png" >
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>小明书城会员</h1>
                    <a href="pages/user/regist.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
<%--									<%=request.getAttribute("msg")==null?"请输入用户名和密码" : request.getAttribute("msg")%>--%>
									${ empty requestScope.msg ? "请输入用户名和密码":requestScope.msg}
								</span>
                </div>
                <div class="form">
                    <form action="user/login.do" method="post">
                        <input type="hidden" name="action" value="login"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1" name="username"
                               value=""/>
                        <br />
                        <br />
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password"/>
                        <br />
                        <br />
                        <label>&nbsp;&nbsp;&nbsp;验证码：</label>
                        <input class="itxt" name="code" type="text" style="width: 100px;"
                               id="code"/>
                        <img  id="code_img" alt="点击更换" src="kaptcha.jpg" style="float: right; margin-right: 40px;width: 120px;height: 40px">
                        <br />
                        <br />
                        <input type="submit" value="登录" id="sub_btn" style="cursor: pointer"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>