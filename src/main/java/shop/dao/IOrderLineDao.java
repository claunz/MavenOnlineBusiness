/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shop.dao;

import shop.entity.OrderLine;

/**
 *
 * @author coco
 */
public interface IOrderLineDao {
    public void saveOrderLine(OrderLine orderLine);
    
}
