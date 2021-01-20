<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<div class="container">

	<div class="form-group">
		<label for="username">User Name</label> <input type="text" class="form-control" placeholder="Enter User Name" id="username">
	</div>

	<div class="form-group">
		<label for="password">Password</label> <input type="password" class="form-control" placeholder="Enter Password" id="password">
	</div>

	<div class="form-group">
		<label for="email">Email</label> <input type="email" class="form-control" placeholder="Enter Email" id="email">
	</div>

	<div class="form-group">
		<button id="btn-create" class="btn btn-primary">회원 가입</button>
	</div>

</div>

<script src="/js/auth/createForm.js"></script>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>