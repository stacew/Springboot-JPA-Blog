<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<div class="container">

	<div class="form-group">
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		<button id="btn-update" class="btn btn-warning">수정</button>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
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

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>