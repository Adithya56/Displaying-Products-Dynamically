package ProductsController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ProductsDAO.CategoryDAO;
import ProductsService.CategoryService;

@WebServlet("/CategoriesServlet")
public class CategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CategoryService categoryService;

	public CategoriesServlet() {
		CategoryDAO categoryDAO = new CategoryDAO(); // Instantiate the CategoryDAO class
		this.categoryService = new CategoryService(categoryDAO); // Pass CategoryDAO to CategoryService constructor
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> categories = categoryService.getAllCategories();

		StringBuilder htmlContent = new StringBuilder();
		htmlContent.append("<option disabled selected>Products</option>");
		for (String category : categories) {
			htmlContent.append("<option value='").append(category).append("'>").append(category).append("</option>");
		}
		System.out.println("hello categoryServlet");
		response.setContentType("text/html");
		response.getWriter().write(htmlContent.toString());
	}
}
