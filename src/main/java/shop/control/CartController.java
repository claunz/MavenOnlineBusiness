

package shop.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Hibernate;
import shop.dao.CartDao;
import shop.dao.ICartDao;
import shop.dao.ProductDao;
import shop.dao.UserDao;
import shop.entity.Cart;
import shop.entity.OrderLine;
import shop.entity.Product;
import shop.entity.User;

@Named
@SessionScoped
public class CartController implements Serializable{
    private FacesContext facesContext;
    private static final String view_order = "viewOrder";
    private Cart cart;
    @Inject
    private CartDao cartDao;
    private User user;
    @Inject
    private UserController userController;
    private List<OrderLine> orderLines;
    
    static{
        
    }
    
    public CartController() {
    }
    public String addOrderLine(){
        UserDao userDao = new UserDao();
        user = userDao.loadUser(1);
        facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
        String productIdString = params.get("productId");
        
        if (productIdString != null) {
            int productId = Integer.valueOf(productIdString);
            ProductDao productDao = new ProductDao();
            Product product = productDao.loadProduct(productId);
            OrderLine orderLine = new OrderLine(1, product); //generate orderline from product 
            
            if(user.getCart() != null){
                cart = user.getCart();
            }
            else{
                cart = new Cart();
                cart.setUser(user);
            }
            cartDao.setCart(cart);
            cartDao.addOderLine(orderLine);
        } 
              
        return null;
    }
    public void removeOrderLine(){
        facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
        int orderLineId = Integer.valueOf(params.get("orderLineId"));
        if (orderLineId > 0) {
            cartDao.removeOrderLine(orderLineId);
        }
    }
    public String viewCart(){
        return null;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
    
    
}
