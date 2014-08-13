/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shop.entity;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
=======
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
>>>>>>> Benyam

/**
 *
 * @author Benybifa
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String firstName;
    public String lastName;
    public String password;
    public String confPassword;
    public String userType;
<<<<<<< HEAD
    
    @OneToMany(mappedBy = "user")
    private Collection<Order> orders;
    
    @OneToMany(mappedBy = "user")
    private Collection<Cart> carts;
    
=======
    @OneToOne
    @PrimaryKeyJoinColumn
    public ShippingAddress shippingAddress;
    @OneToOne
    @PrimaryKeyJoinColumn
    public BillingAddress billingAddress;
>>>>>>> Benyam
    public User(){
        
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

   

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    public Collection<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Collection<Cart> carts) {
        this.carts = carts;
    }

    
    
    
}