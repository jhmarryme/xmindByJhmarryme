<%--
  Created by IntelliJ IDEA.
  User: jhmarryme
  Date: 2019/1/3
  Time: 8:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login.jsp</title>
</head>
<body>
    <%
        Cookie[] cookies = request.getCookies();
        String username = "";
        for (int i = 0; cookies != null && i < cookies.length; i++){
            if ("username".equals(cookies[i].getName())){
                username = cookies[i].getValue();
            }
        }

    %>

    <form action="/handleLogin" method="post">
        <input type="text" name="username" value="<%=username%>"/>
        <input type="password" name="userpass" value=""/>
        <input type="checkbox" name="remember" value="iscookie"/>
        <input type="submit" value="登陆"/>
    </form>

</body>
</html>
