
package shop.dao;

import java.util.Collection;
import shop.entity.Cart;
import shop.entity.OrderLine;


public interface ICartDao {
        public void saveCart(Cart cart);
        public void updateCart(Cart cart);
        public Cart loadCart();
        public void deleteCart(Cart cart);
        public void addOderLine(OrderLine orderLine);
	public void removeOrderLine(int orderLineId);
	public Collection<OrderLine> getCartOrderLines();
}
