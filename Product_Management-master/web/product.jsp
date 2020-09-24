<%-- 
    Document   : product
    Created on : Feb 4, 2018, 6:21:46 PM
    Author     : Bryson
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/header.jsp" />
    <body>
        <header class="header">
            <div class ="container">
                <span class ="text-muted">The Store</span>
            </div>
        </header>
        <div class ="container">
            User <a href="membership?action=logout">Logout</a>
            <h1>Product</h1>
            <form action="/Assignment1/productManagement?action=addProduct" name="addproduct" method="post">  
                <div class="form-group">
                    <label for="code" class="labels">Code</label>
                    <input type="text" class="form-control" name="code" value="<c:out value = '${editproduct.code}'/>" required/> <br />
                </div>
                <div class="form-group">
                    <label for="description" class="labels">Description</label>
                    <input type="text" class="form-control" name="description" value="<c:out value ='${editproduct.description}'/>" required/> <br />
                </div>
                <div class="form-group">
                    <label class="labels">Price</label>
                    <input type="text" class="form-control" name="price" value="<c:out value ='${editproduct.price}'/>" required/> <br />
                </div>
                <input type="submit" class="btn btn-default" name="addProduct" value="Update Product" />       
            </form>
                
            <form action="/Assignment1/productManagement?action=displayProducts" name="displayProduct" method="post">
                <div class="form-group">
                <input type="submit" class="btn btn-default" value="View Products" />
                </div>
            </form>
        </div>
<c:import url="/footer.jsp" />
