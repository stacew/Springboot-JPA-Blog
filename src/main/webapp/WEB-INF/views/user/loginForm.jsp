<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<div class="container">

	<form action="/auth/loginProc" method="post">

		<div class="form-group">
			<label for="username">User Name</label> <input type="text" name="username" class="form-control" placeholder="Enter User Name" id="username">
		</div>

		<div class="form-group">
			<label for="password">Password</label> <input type="password" name="password" class="form-control" placeholder="Enter Password" id="password">
		</div>

		<div class="form-group">
			<button id="btn-login" class="btn btn-primary">로그인</button>
		</div>
	</form>


</div>

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>