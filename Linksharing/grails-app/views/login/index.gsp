<%--
  Created by IntelliJ IDEA.
  User: prachi
  Date: 28/3/18
  Time: 8:40 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
hello
%{--<g:if test="${flash.error}">--}%
${keyFail}
<g:message message="${flash.error}"></g:message>
<g:message message="${flash.message}"></g:message>
%{--</g:if>--}%


</body>
</html>