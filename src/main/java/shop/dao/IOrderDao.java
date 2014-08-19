/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shop.dao;

import java.util.Collection;
import shop.entity.Order;
import shop.entity.OrderLine;

/**
 *
 * @author coco
 */
public interface IOrderDao {
    public Collection<OrderLine> getOrderItems(int orderId);
    public Collection<Order> getUserOrders();
}
