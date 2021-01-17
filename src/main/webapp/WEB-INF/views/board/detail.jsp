<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<div class="container">

	<div class="form-group">
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		<%-- id 외에 권한.. 필요 --%>
		<c:if test="${board.user.id==principal.user.id}">
			<button id="btn-update" class="btn btn-warning">수정</button>
			<button id="btn-delete" class="btn btn-danger">삭제</button>
		</c:if>
	</div>
	<div class="form-group">
		글 번호 : <span id="board_id"><i>${board.id} </i></span>
		작성자 : <span><i>${board.user.username} </i></span>
	</div>

	<div class="form-group">
		<h3>${board.title}</h3>
		<hr>
	</div>

	<div class="form-group">
		${board.content}
		<hr>
	</div>
</div>

<script src="/js/detail.js"></script>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>