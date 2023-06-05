package ProductsController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get the product ID from the request
		int productId = Integer.parseInt(request.getParameter("productId"));

		// Get the session
		HttpSession session = request.getSession();

		// Get the cart from the session or create a new one if it doesn't exist
		List<Integer> cart = (List<Integer>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<>();
		}

		// Add the product ID to the cart
		cart.add(productId);

		// Update the cart in the session
		session.setAttribute("cart", cart);

		// Redirect the user to the product listing page or any other desired page
		response.sendRedirect("/WEB-INF/views/products.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get the product ID to remove from the cart
		int productId = Integer.parseInt(request.getParameter("productId"));

		// Get the session
		HttpSession session = request.getSession();

		// Get the cart from the session
		List<Integer> cart = (List<Integer>) session.getAttribute("cart");

		// Remove the product ID from the cart
		if (cart != null) {
			cart.remove(Integer.valueOf(productId));
		}

		// Update the cart in the session
		session.setAttribute("cart", cart);

		// Redirect the user to the cart page or any other desired page
		response.sendRedirect("/WEB-INF/views/-cart.jsp");
	}
}
