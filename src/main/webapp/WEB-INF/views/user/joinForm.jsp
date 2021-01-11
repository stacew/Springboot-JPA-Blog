<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<!-- <form action="/user/join" method="POST"> -->
		<div class="form-group">
			<label for="username">UserName</label> <input type="text" class="form-control" placeholder="Enter UserName" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password</label> <input type="password" class="form-control" placeholder="Enter Password" id="password">
		</div>
		<div class="form-group">
			<label for="email">Email</label> <input type="email" class="form-control" placeholder="Enter Email" id="email">
		</div>
	</form>
	<button id="btn-save" class="btn btn-primary">회원 가입</button>
	
</div>
<script src="/blog/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>