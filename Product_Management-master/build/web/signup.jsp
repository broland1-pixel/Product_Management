<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no">
        <title>Login</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <header class="header">
            <div class ="container">
                <span class ="text-muted">The Store</span>
            </div>
        </header>
        <div class ="container">
            <h1>Sign-up Form</h1>  
            <main>
                <form action="/Assignment1/membership?action=signup" name="registration" method="post">  
                    <div class="form-group">
                        <label for="firstName">First Name:</label>  
                        <input type="text" class="form-control" name="firstName"/>  <br />
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last Name: </label>
                        <input type ="text" class="form-control" name="lastName"/> <br />
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>  
                        <input type="text" class="form-control" name="email"  />  <br />
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>  
                        <input type="password" class="form-control" name="password"/>  <br />
                    </div>
                    <input type="submit" name="signup" value="Submit" />  
                </form>  
            </main>
        </div>
    </body>
<c:import url="/footer.jsp" />
