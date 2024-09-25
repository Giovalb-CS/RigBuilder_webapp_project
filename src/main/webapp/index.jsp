<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login - RigBuilder</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index-login.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css" type="text/css">
        <script src="https://kit.fontawesome.com/8488ba2065.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <div class="top-container">
            <div class="image-container">
                <img src="${pageContext.request.contextPath}/images/RigBuilder_Logo.png" alt="Logo">
                <p>Administrator Dashboard</p>
            </div>

            <div class="form-container">
                <form action="login" method="post">
                    <label for="email">Email</label><br>
                    <input type="text" id="email" name="email" pattern="^(?=.{7,})[a-zA-Z0-9._%]+@[a-zA-Z0-9.]+\.[a-zA-Z]{2,5}$" title="after @ at least one character, followed by a dot and at least two characters" minlength="7" maxlength="134" placeholder="Email" required>
                    <br>

                    <label for="pass">Password</label><br>
                    <input type="password" id="pass" name="pass" minlength="8" maxlength="30" title="8 to 30 characters, including a number, a uppercase letter, a lowercase letter and a special character," pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@#£$%^&_?*])[A-Za-z\d!@#£$%^&_?*]{8,30}$" placeholder="Password" required>
                    <br>

                    <div class="button-container">
                        <button type="submit"><i class="fa fa-sign-in" aria-hidden="true"></i> Login</button>
                    </div>
                </form>
            </div>
        </div>

        <%@include file="WEB-INF/results/modules/footer.jsp"%>
    
    </body>
</html>