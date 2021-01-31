<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<div class="container">

	<div class="form-group">
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		<%-- id 외에 권한.. 필요 --%>
		<c:if test="${board.user.id==principal.user.id}">
			<a href="/board/updateForm/${board.id}" class="btn btn-warning">수정</a>
			<button id="btn-delete" class="btn btn-danger">삭제</button>
		</c:if>
	</div>
	<div class="form-group">
		글 번호 : <span id="board_id"><i>${board.id} </i></span> 작성자 : <span><i>${board.user.username} </i></span>
	</div>

	<div class="form-group">
		<h3>${board.title}</h3>
		<hr>
	</div>

	<div class="form-group">
		${board.content}
		<hr>
	</div>

	<div class="card">
		<form>
			<input type="hidden"  id="boardId"  value="${board.id}"/>
			<div class="card-body">
				<textarea id="reply-content" class="form-control" rows="2"></textarea>
			</div>
			<div class="card-footer">
				<!-- form 안에 button은 summit 타입이 돼서 반응 없는 button type을 세팅 -->
				<button type="button" id="btn-reply-create" class="btn btn-primary">등록</button>
			</div>
		</form>
	</div>

	<div class="card">
		<div class="card-header">댓글 리스트</div>
		<ul id="reply--box" class="list-group">
			<c:forEach var="reply" items="${board.replys}">
				<li id="reply--1" class="list-group-item d-flex justify-content-between">
					<div>${reply.content}</div>
					<div class="d-flex justify-content-right">
						<div class="font-italic">작성자 : ${reply.user.username}&nbsp;</div>
						<button class="badge">삭제</button>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>

</div>

<script src="/js/board/detail.js"></script>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>