<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Administrator - RigBuilder</title>
        <link rel="icon" href="${pageContext.request.contextPath}/images/RigBuilder_site_watermark.png" type="image/x-icon">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nav.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addForm.css" type="text/css">
        <script src="https://kit.fontawesome.com/8488ba2065.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body id="top">
        <%@include file="/WEB-INF/results/modules/isAdminLogged.jsp"%>
        <%@include file="/WEB-INF/results/modules/navbar.jsp"%>
        <%@include file="/WEB-INF/results/modules/backtotopbutton.jsp"%>

        <%if (request.getAttribute("alreadyExists")!=null) {%>
        <div class="messageContainer failure">
            <h3>The email you tried to add already exists.</h3>
        </div>
        <%}%>
        <%if (request.getAttribute("formError")!=null){%>
        <div class="messageContainer failure">
            <h3>There was an error with the parameters of the form. Please, try again.</h3>
        </div>
        <%}%>
        <%if (request.getAttribute("emailError")!=null) {%>
        <div class="messageContainer failure">
            <h3>Email and email confirmation are not corresponding. Try again.</h3>
        </div>
        <%}%>
        <%if (request.getAttribute("passwordError")!=null){%>
        <div class="messageContainer failure">
            <h3>Password and password confirmation are not corresponding. Try again.</h3>
        </div>
        <%}%>

        <form action="addAdmin" method="post" class="form-container">
            <div class="input-container">
                <label for="email">Email</label>
                <input type="email" name="email" id="email" pattern="^(?=.{7,})[a-zA-Z0-9._%]+@[a-zA-Z0-9.]+\.[a-zA-Z]{2,5}$" title="after @ at least one character, followed by a dot and at least two characters" minlength="7" maxlength="134" placeholder="Email" required>
            </div>

            <div class="input-container">
                <label for="confirm-email">Confirm Email</label>
                <input type="email" name="confirm-email" id="confirm-email" pattern="^(?=.{7,})[a-zA-Z0-9._%]+@[a-zA-Z0-9.]+\.[a-zA-Z]{2,5}$" title="after @ at least one character, followed by a dot and at least two characters" minlength="7" maxlength="134" placeholder="Email" required>
            </div>

            <div class="input-container">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" minlength="8" maxlength="30" title="8 to 30 characters, including a number, a uppercase letter, a lowercase letter and a special character," pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@#£$%^&_?*])[A-Za-z\d!@#£$%^&_?*]{8,30}$" placeholder="Password" required>
            </div>

            <div class="input-container">
                <label for="confirm-password">Confirm Password</label>
                <input type="password" id="confirm-password" name="confirm-password" minlength="8" maxlength="30" title="8 to 30 characters, including a number, a uppercase letter, a lowercase letter and a special character," pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@#£$%^&_?*])[A-Za-z\d!@#£$%^&_?*]{8,30}$" placeholder="Password" required>
            </div>

            <div class="input-container">
                <button type="submit"><i class="fa fa-user-plus" aria-hidden="true"></i> Add</button>
            </div>
        </form>

        <%@include file="/WEB-INF/results/modules/footer.jsp"%>
    </body>
</html>
