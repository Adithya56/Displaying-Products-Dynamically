package ProductsController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ProductsDAO.CategoryDAO;
import ProductsModel.Product;
import ProductsService.CategoryService;
import ProductsService.ProductService;

@Controller
@ComponentScan(basePackages = "Products")
public class ProductController {

	private final ProductService productService;
	private final CategoryService categoryService;

	@Autowired
	public ProductController(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		CategoryDAO categoryDAO = new CategoryDAO(); // Instantiate the CategoryDAO class
		this.categoryService = new CategoryService(categoryDAO); // Pass CategoryDAO to CategoryService constructor
	}

	@GetMapping("/categories")
	public String showCategories(Model model) {
		return "prod";
	}

	@GetMapping("/CategoriesServlet")
	@ResponseBody
	public String displayCategories(Model model) {
		List<String> categories = categoryService.getAllCategories();

		StringBuilder htmlContent = new StringBuilder();
		htmlContent.append("<option disabled selected>Products</option>");
		for (String category : categories) {
			htmlContent.append("<option value='").append(category).append("'>").append(category).append("</option>");
		}

		return htmlContent.toString();
	}

	@GetMapping("/products")
	@ResponseBody
	public String showProducts(@RequestParam(value = "category", required = false) String category, Model model) {
		List<Product> products;
		if (category != null && !category.isEmpty()) {
			products = productService.getProductsByCategory(category);
		} else {
			products = productService.getAllProducts();
		}

		String htmlContent = generateProductCatalogHTML(products);

		return htmlContent;
	}

	private String generateProductCatalogHTML(List<Product> products) {
		StringBuilder htmlContent = new StringBuilder();
		htmlContent.append("<div class=\"container mt-5\">\n").append("<h2>Product Catalog</h2>\n")
				.append("<div class=\"row mt-4\">\n");

		for (Product product : products) {
			htmlContent.append("<div class=\"col-lg-4 col-md-6 mb-4\">\n").append("<div class=\"card h-100\">\n")
					.append("<img class=\"card-img-top\" src=\"").append(product.getImageUrl()).append("\" alt=\"")
					.append(product.getName()).append("\">\n").append("<div class=\"card-body\">\n")
					.append("<h5 class=\"card-title\">").append(product.getName()).append("</h5>\n")
					.append("<p class=\"card-text\">").append(product.getDescription()).append("</p>\n")
					.append("<p class=\"card-text\">").append(product.getPrice()).append("</p>\n")
					.append("<button class=\"btn btn-primary\">Add to Cart</button>\n")
					.append("<button class=\"btn btn-secondary\">Add to Wishlist</button>\n").append("</div>\n")
					.append("</div>\n").append("</div>\n");
		}

		htmlContent.append("</div>\n").append("</div>\n");
		return htmlContent.toString();
	}
}
