<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="/css/base.css" />
</head>
<body>
<div id="wrap">
	<!-- header -->
	<div id="header">
		<h1>게시판 상세보기</h1>
	</div><!-- // header -->
	
	<!-- container -->
	<div id="container">
		
			<div class="table_grp">
				<div class="table_type_01">
					<table>
						<colgroup>
							<col style="width:120px;" />
							<col />
							<col style="width:120px;" />
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">글번호</th>
								<td>${article.no}</td>
								<th scope="row">조회수</th>
								<td>${article.readcount}</td>
							</tr>
							<tr>
								<th scope="row">작성자</th>
								<td>${article.id}</td>
								<th scope="row">작성일</th>
								<td>${article.regdate}</td>
							</tr>
							<tr>
								<th scope="row">글제목</th>
								<td colspan="3">${article.subject}</td>
							</tr>
							<tr>
								<th scope="row">글내용</th>
								<td colspan="3"><pre>${article.content}</pre></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div style="margin: 5px auto; ">
					<button style="background-color: black; color: white; margin: 5px auto;">좋아요</button>
				</div>
				<div id="comm" style="margin: 5px auto;">
					<table>
					<colgroup>
						<col style="width:10%;" />
						<col />
						<col style="width:12%;" />
					</colgroup>
					<c:forEach items="${clist}" var="comm">
						<tr>
							<td>${comm.id }</td>
							<td>
							<c:if test="${comm.re_level > 0 }">
										<!-- 답변글이있으면 -->
										<span class="icon">
										<img src="/img/level.gif" width="${comm.re_level * 10}">	
										<img src="/img/re.gif" alt="답변" /></span>
										</c:if>${comm.content}</td>
							<td><fmt:formatDate value="${comm.regdate }" dateStyle="short"/></td>
						</tr>
						<tr>
							<td>
								<input style="border: solid 1px; " name="content" id="content">
								<button class="add">등록</button>
							<td>
						</tr>
					</c:forEach>
					</table>
					
				</div>
				<div class="btn_align_02">
	
					<span class="btn btnF_03 btnC_02 mr5">
						<input type="button" value="답글쓰기" onclick="location.href='/ref/write?no=${article.no}&ref=${article.ref}&re_step=${article.re_step}&re_level=${article.re_level}'" />
					</span>
					<span class="btn btnF_03 btnC_02 mr5">
						<input type="button" value="목록" onclick="location.href='../list'" />
					</span>
					
					<c:if test="${user.id == article.id}">
					
					<span class="btn btnF_03 btnC_02 mr5">
						<input type="button" value="삭제" onclick="location.href='/ref/delete/'+${article.no}" />
					</span>	
					<span class="btn btnF_03 btnC_02 mr5">
						<input type="button" value="수정" onclick="location.href='/ref/update/'+${article.no}" />
					</span>
					</c:if>	
				</div>
			
		</div>
	</div><!-- // container -->
</div><!-- // wrap -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#comm").on("click",".add",function(){
		
	})
})
</script>
</body>
</html>