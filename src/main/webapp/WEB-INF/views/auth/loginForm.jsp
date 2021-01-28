<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<!-- 1. spring env -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- 2. spring env -->
<spring:eval expression="@environment.getProperty('doksam.auth.kakaoRest.reqLogin')" var="kakaoLoginPath" />

<div class="container">

	<%-- "/auth/login" WebSecurityConfigurerAdapter에서 정해줌. --%>
	<form action="/auth/login" method="post">

		<div class="form-group">
			<label for="username">User Name</label> <input type="text" name="username" class="form-control" placeholder="Enter User Name" id="username" autocomplete="off">
		</div>

		<div class="form-group">
			<label for="password">Password</label> <input type="password" name="password" class="form-control" placeholder="Enter Password" id="password" autocomplete="off">
		</div>

		<div class="form-group">
			<button class="btn btn-primary">로그인</button>
			<a href=${kakaoLoginPath}><img height=38 src="/image/kakao_login_btn.png" /></a>
		</div>
	</form>

</div>

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>