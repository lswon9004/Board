<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<form action="/sendw" method="post">
<input type="hidden" value="${id}" name="id" id="d">
<input name="emailAddress" id="email" required="required">
<input type="submit" value="전송" id="dda">
</form>
</body>
</html>