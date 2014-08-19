
package shop.control;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import shop.dao.OrderDao;
import shop.entity.Order;
import shop.entity.OrderLine;

@Named
@SessionScoped
public class OrderController implements Serializable{
    
    private FacesContext facesContext;
    private static final String browse_orders = "browseOders";
    private static final String view_order = "viewOrder";
    @Inject
    private OrderDao orderDao;
    private Collection<Order> userOrders;
    private Collection<OrderLine> orderItems;
    
    public OrderController() {
    }
    public String browseOrders(){
        
        System.out.println("=======inside browse");
       // userOrders = orderDao.getUserOrders();
        return browse_orders;
    }
    public String viewOrder(){
        return view_order;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public List<Order> getOrderList(){
        System.out.println("==============Inside get order list========");
        userOrders = orderDao.getUserOrders();
        
        return (List<Order>) userOrders;
    }
    public List<OrderLine> getOrderItems(){
        facesContext = FacesContext.getCurrentInstance();
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
        String ordeIdString = params.get("orderId");
        if (ordeIdString != null) {
            int orderId = Integer.valueOf(ordeIdString);
            orderItems =  orderDao.getOrderItems(orderId);
        }
        return null;
    }

}

    