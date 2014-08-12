
package shop.control;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import shop.dao.IProductDao;
import shop.dao.ProductDao;


@ManagedBean(name="navcontrol")
@ApplicationScoped
public class NavigationContoller  implements Serializable{
    private static final String login = "login";
    private static final String browser_products = "browseProducts";
    private IProductDao productDao;
    public NavigationContoller() {
    }
    public String checkLogin(){
        return login;
    }
    public String browseProducts(){
        productDao = new ProductDao();
        productDao.getProducts();
        return browser_products;
    }
    public String addProduct(){
        return null;
    }
    public String viewProduct(){
        return null;
    }
    public String browseOrders(){
        return null;
    }
    public String viewOrders(){
        return null;
    }
    public String viewCart(){
        return null;
    }
}
