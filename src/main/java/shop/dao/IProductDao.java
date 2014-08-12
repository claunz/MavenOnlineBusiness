
package shop.dao;

import java.util.Collection;
import shop.entity.Product;


public interface IProductDao {
    public void saveProduct(Product product);
	public void updateProduct(Product product);
	public Product loadProduct(int productId);
	public Collection<Product> getProducts();
}
