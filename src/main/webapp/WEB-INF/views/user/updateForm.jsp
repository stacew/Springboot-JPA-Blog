<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<div class="container">

	<div class="form-group">
		<label for="username">User Name</label>		
		<input type="text" value="${principal.user.username}" class="form-control"
			placeholder="Enter User Name" id="username" readonly>
	</div>
	<div class="form-group">
		<label for="originalPassword">Original Password</label> 
		<input type="password" class="form-control" placeholder="Enter Original Password" id="originalPassword">
	</div>

	<div class="form-group">
		<label for="newPassword">New Password</label> 
		<input type="password" class="form-control" placeholder="Enter New Password" id="newPassword">
	</div>

	<div class="form-group">
		<label for="email">Email</label> 
		<input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter Email" id="email">
	</div>

	<div class="form-group">
		<button id="btn-update" class="btn btn-primary">회원 수정 완료</button>
	</div>

</div>

<script src="/js/user/updateForm.js"></script>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>