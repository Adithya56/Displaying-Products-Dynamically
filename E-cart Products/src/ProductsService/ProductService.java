package ProductsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProductsDAO.ProductDAO;
import ProductsModel.Product;

@Service
public class ProductService {

	private final ProductDAO productDAO;

	@Autowired
	public ProductService(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public List<Product> getAllProductCategories() {
		return productDAO.getAllProducts();
	}

	public List<Product> getProductsByCategory(String category) {
		return productDAO.getProductsByCategory(category);
	}

	public List<Product> getAllProducts() {
		return productDAO.getAllProducts();
	}
}
