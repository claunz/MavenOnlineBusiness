package shop.control;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import shop.dao.IProductDao;
import shop.dao.ProductDao;
import shop.entity.Product;

@Named
@SessionScoped
public class ProductController implements Serializable {

    private FacesContext facesContext;
    private static final String browser_products = "browseProducts";
    private static final String add_product = "addProduct";
    private static final String view_product = "viewProduct";
    private Product product;
    private IProductDao productDao;
    private List<Product> productList;

    public ProductController() {
    }

    public String addProduct() {
        facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
        String productIdString = params.get("productId");
        if (productIdString != null) {
            int productId = Integer.valueOf(productIdString);
            product = productDao.loadProduct(productId);
        } else {
            product = new Product();
        }
        return add_product;
    }

    public String saveProduct() {
        productDao = new ProductDao();
        productDao.saveProduct(product);
        return view_product;
    }
    public String deleteProduct(){
        facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
        int productId = Integer.valueOf(params.get("productId"));
        if (productId > 0) {
            return browser_products;
        }
        return null;
    }
    public String cancel(){
        return browser_products;
    }
    public String viewProduct() {
        facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
        int productId = Integer.valueOf(params.get("productId"));
        if (productList == null) {
            productDao = new ProductDao();
            productList = (List<Product>) productDao.getProducts();
        }
        if (productId > 0) {
            product = productDao.loadProduct(productId);
            return view_product;
        }
        return null;
    }

    public String browseProducts() {
        productDao = new ProductDao();
        productList = (List<Product>) productDao.getProducts();
        System.out.println(" In get product list " + productList.size());
        return browser_products;
    }

    public List<Product> getProductList() {
        if (productList == null) {
            productDao = new ProductDao();
            productList = (List<Product>) productDao.getProducts();
        }
        return productList;
    }

    public Product getProduct() {
        return product;
    }

}
