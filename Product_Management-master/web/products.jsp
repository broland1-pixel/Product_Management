<%-- 
    Document   : products
    Created on : Feb 4, 2018, 11:46:28 AM
    Author     : Bryson
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:import url="/header.jsp" />
    <body>
        <header class="header">
            <div class ="container">
                <span class ="text-muted">The Store</span>
            </div>
        </header>
        User <a href="membership?action=logout">Logout</a>
        <h1>Products</h1>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">Code</th>
                    <th scope="col">Description</th>
                    <th scope="col">Price</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var= "current">
                <tr>
                    <td><c:out value="${current.code}"/></td>
                    <td><c:out value="${current.description}"/></td>
                    <td><c:out value="${current.price}"/></td>
                    <td><a href="/Assignment1/productManagement?action=addProduct&productCode=${current.code}">Edit</a></td>
                    <td><a href="/Assignment1/productManagement?action=deleteProduct&productCode=${current.code}">Delete</a></td>
                </tr>              
            </c:forEach>
            </tbody>
        </table>
        <form action="/Assignment1/productManagement?action=addProduct" name="addproduct" method="post">
            <input type="submit" value="Add Product" />
        </form>
<c:import url="/footer.jsp" />
