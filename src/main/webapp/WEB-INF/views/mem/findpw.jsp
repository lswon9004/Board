<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<c:if test="${resultq ==false}">
<p>입력하신 아이디와 닉네임이 일치하지 않습니다.</p>
</c:if>
<form method="post" id="joinform">
<table>	
<tr><td>아이디 : <input name="id" required="required"></td></tr>
<tr><td>닉네임 : <input name="name" required="required"></td></tr>	
<tr><td><input type="submit" value="확인" ></td></tr>
</table>		
</form>
</body>
</html>