
package shop.control;

import java.util.Collection;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import shop.dao.OrderDao;
import shop.entity.Order;

@Named
@RequestScoped
public class OrderController {
    private static final String browse_orders = "browseOders";
    @Inject
    private OrderDao orderDao;
    private Collection<Order> userOrders;
    
    public OrderController() {
    }
    public String browseOrders(){
        userOrders = orderDao.getUserOrders();
        return browse_orders;
    }
    public String viewOrderr(){
        return null;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public List<Order> getOrderList(){
        System.out.println("==============Inside get order list========");
        userOrders = orderDao.getUserOrders();
        
        return (List<Order>) userOrders;
    }
    
}

    