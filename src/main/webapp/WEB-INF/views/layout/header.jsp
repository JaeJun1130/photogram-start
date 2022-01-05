<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<!-- 인증된 정보에 접근하는 방법 (session에 접근하는 방법) spring security -->
<!-- Model 없이 글로벌하게 사용가능 -->
<!-- property : principal 고정 UserDetails 접근하여에 인증된 정보를 가져온다 -->
<!-- var : session 정보를 담는 변수명 -->
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>

<!--ROLE_USER 권한을 갖는다면 이 글이 보임-->
<%--<h1 sec:authorize="hasRole('ADMIN')">Has admin Role</h1>--%>

<!--ROLE_ADMIN 권한을 갖는다면 이 글이 보임-->
<%--<h1 sec:authorize="hasRole('USER')">Has user Role</h1>--%>

<!--어떤 권한이건 상관없이 인증이 되었다면 이 글이 보임-->
<%--<div sec:authorize="isAuthenticated()">--%>
<%--	Only Authenticated user can see this Text--%>
<%--</div>--%>

<!--인증시 사용된 객체에 대한 정보-->
<%--<b>Authenticated DTO:</b>--%>
<%--<div sec:authentication="principal"></div>--%>

<!--인증시 사용된 객체의 Username (ID)-->
<%--<b>Authenticated username:</b>--%>
<%--<div sec:authentication="name"></div>--%>

<!--객체의 권한-->
<%--<b>Authenticated user role:</b>--%>
<%--<div sec:authentication="principal.authorities"></div>--%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Photogram</title>

	<!-- 제이쿼리 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<!-- Style -->
	<link rel="stylesheet" href="/css/style.css">
	<link rel="stylesheet" href="/css/story.css">
	<link rel="stylesheet" href="/css/popular.css">
	<link rel="stylesheet" href="/css/profile.css">
	<link rel="stylesheet" href="/css/upload.css">
	<link rel="stylesheet" href="/css/update.css">
	<link rel="shortcut icon" href="/images/insta.svg">
	<%--dd --%>
	<!-- Fontawesome -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
	<!-- Fonts -->
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700&display=swap"
		  rel="stylesheet">
</head>

<body>
<!-- principal 정보 담아두기 -->
<input type="hidden" id="principalId" value="${principal.user.id}"/>
<header class="header">
	<div class="container">
		<a href="/" class="logo">
			<img src="/images/logo.jpg" alt="">
		</a>
		<nav class="navi">
			<ul class="navi-list">
				<li class="navi-item"><a href="/">
					<i class="fas fa-home"></i>
				</a></li>
					<li class="navi-item"><a href="/image/popular">
							<i class="far fa-compass"></i>
						</a></li>
					<li class="navi-item"><a href="/user/${principal.user.id}">
							<i class="far fa-user"></i>
						</a></li>
				</ul>
			</nav>
		</div>
	</header>
