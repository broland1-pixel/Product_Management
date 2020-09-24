<%-- 
    Document   : confirmDelete
    Created on : Feb 4, 2018, 6:22:35 PM
    Author     : Bryson
--%>
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
        <section>
        User <a href="membership?action=logout">Logout</a>
        <h1>Are you sure that you want to delete this product?</h1>
        <p>
            Code: <c:out value="${delete.code}"/><br>
            Description: <c:out value="${delete.description}"/><br>
            Price: <c:out value="${delete.price}"/><br>
        </p>
        <form action="/Assignment1/productManagement?action=deleteProduct&productCode=${delete.code}" method="post">
            <input type="hidden" name ="delete1" value="Yes">
            <input type="submit" value ="Yes" />
        </form>
        <form action="/Assignment1/productManagement?action=displayProducts" name="displayProduct" method="post">
            <input type="submit" value="No" />
        </form>
        </section>
        
<c:import url="/footer.jsp" />
