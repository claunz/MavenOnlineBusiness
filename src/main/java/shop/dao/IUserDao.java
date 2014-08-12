/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shop.dao;

import shop.entity.User;

/**
 *
 * @author Benybifa
 */
public interface IUserDao {
   public void saveUser(User user);
   public User loadUser(int userId);
}
