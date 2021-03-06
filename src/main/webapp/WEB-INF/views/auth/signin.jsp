<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Photogram</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
        integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />

    <!-- 제이쿼리 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>
    <div class="container">
        <main class="loginMain">
        <!--로그인섹션-->
            <section class="login">
               <!--로그인박스-->
                <article class="login__form__container">
                   <!--로그인 폼-->
                   <div class="login__form">
                        <h1><img src="/images/logo.jpg" alt=""></h1>
                        
                        <!--로그인 인풋-->
                        <form class="login__input">
                            <input type="text" name="username" placeholder="유저네임" required="required" />
                            <input type="password" name="password" placeholder="비밀번호" required="required" />
                            <button onclick="formSubmit('s');">로그인</button>
                        </form>
                        <!--로그인 인풋end-->

                       <!-- 또는 -->
                       <div class="login__horizon">
                           <div class="br"></div>
                           <div class="or">또는</div>
                           <div class="br"></div>
                       </div>
                       <!-- 또는end -->

                       <!-- Oauth 소셜로그인 -->
                       <div class="login__facebook">
                           <%--                            oauth2/authorization/ 까지는 고정 그뒤에는 yml에 설정해놓은 로그인방식--%>
                           <button onclick="facebookLogin()">
                               <i class="fab fa-facebook-square"></i>
                               <span>Facebook으로 로그인</span>
                           </button>
                       </div>
                       <!-- Oauth 소셜로그인end -->
                   </div>

                    <!--계정이 없으신가요?-->
                    <div class="login__register">
                        <span>계정이 없으신가요?</span>
                        <a href="/auth/signup">가입하기</a>
                    </div>
                    <!--계정이 없으신가요?end-->
                </article>
            </section>
        </main>

    </div>
</body>
<script>
    function formSubmit(type) {
        if (type === 's') {
            $('.login__input').attr("method", "POST").attr("action", "/auth/signin").submit();
        }
    }

    function facebookLogin() {
        location.href = "/oauth2/authorization/facebook";
    }
</script>
</html>