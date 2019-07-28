<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css"
          href="css/style.css" />
</head>

<body>
<div id="wrap">
    <div id="top_content">
        <%@ include file="header.jsp"%>
        <div id="content">
            <p id="whereami">
            </p>
            <h1>
                用户登录
            </h1>
            <form action="login.do" method="post">
                <table cellpadding="0" cellspacing="0" border="0"
                       class="form_table">
                    <tr>
                        <td valign="middle" align="right">
                            用户名:
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="username" />
                            <span style="color:red">
                                ${login_fail}
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td valign="middle" align="right">
                            密码:
                        </td>
                        <td valign="middle" align="left">
                            <input type="password" class="inputgri" name="pwd" />
                        </td>
                    </tr>
                </table>
                <p>
                    <input type="submit" class="button" value="确认 &raquo;" />
                </p>
            </form>
        </div>
    </div>
    <%@ include file="footer.jsp"%>
</div>
</body>
</html>

