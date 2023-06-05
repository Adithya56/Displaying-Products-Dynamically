<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% @ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
    // Retrieve the cart from the request
    List<Integer> cart = (List<Integer>) request.getSession().getAttribute("cart");

    // Iterate over the product IDs in the cart and display the product details
    if (cart != null) {
        for (int productId : cart) {
            // Retrieve the product details from the database using the product ID
            // Replace `productName`, `productDescription`, and `productPrice` with the actual product information
%>
            <div>
                <h4>productName</h4>
                <p>productDescription</p>
                <p>productPrice</p>
                <form action="cart" method="get">
                    <input type="hidden" name="productId" value="<%=productId%>">
                    <button type="submit" class="btn btn-danger">Remove from Cart</button>
                </form>
            </div>
<%
        }
    } else {
%>
        <p>Your cart is empty.</p>
<%
    }
%>

</body>
</html>