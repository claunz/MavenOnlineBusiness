

package shop.control;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import shop.dao.ICartDao;
import shop.entity.Cart;
import shop.entity.OrderLine;
import shop.entity.User;

@Named
@RequestScoped
public class CartController implements Serializable{
    private FacesContext facesContext;
    private static final String view_order = "viewOrder";
    private Cart cart;
    private ICartDao cartDao;
    private User user;
    private List<OrderLine> orderLines;
    
    public CartController() {
    }
    public String addOrderLine(){
        return null;
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
