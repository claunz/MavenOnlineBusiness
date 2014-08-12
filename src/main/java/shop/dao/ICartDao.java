
package shop.dao;

import java.util.Collection;
import shop.entity.OrderLine;
import shop.entity.Product;


public interface ICartDao {
        public void addOderLine(OrderLine orderLine);
	public void removeOrderLine(int productId);
	public Collection<OrderLine> getCartOrderLines();
}
