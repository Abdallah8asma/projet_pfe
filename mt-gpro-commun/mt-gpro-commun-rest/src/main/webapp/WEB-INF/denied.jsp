<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <body>
     
        <h1 id="banner">Unauthorized Access !!</h1>
     
        <hr />
     
        <c:if test="${not empty error}">
            <div style="color:red">
                Your fake login attempt was bursted, dare again !!<br /> 
                Caused : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </c:if>
     
        <p class="message">Access denied!</p>
        <a href="//howtodoinjava.com/spring/spring-security/login-form-based-spring-3-security-example/">Go back to login page</a> 
    </body>
</html>