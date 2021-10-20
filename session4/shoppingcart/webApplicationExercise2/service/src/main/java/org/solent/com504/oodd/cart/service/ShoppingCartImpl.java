/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;

/**
 *
 * @author cgallen
 */
public class ShoppingCartImpl implements ShoppingCart {

    private HashMap<String, ShoppingItem> itemMap = new HashMap<String, ShoppingItem>();

    @Override
    public List<ShoppingItem> getShoppingCartItems() {
        List<ShoppingItem> itemlist = new ArrayList();
        for (String itemUUID : itemMap.keySet()) {
            ShoppingItem shoppingCartItem = itemMap.get(itemUUID);
            itemlist.add(shoppingCartItem);
        }
        return itemlist;
    }

    @Override
    public void addItemToCart(ShoppingItem shoppingItem) {
        ShoppingItem item = itemMap.get(shoppingItem.getUuid());
        if (item != null) {
            Integer q = item.getQuantity();
            item.setQuantity(q+1);
        } else {
            itemMap.put(shoppingItem.getUuid(), shoppingItem);
        }        
    }

    @Override
    public String removeItemFromCart(String itemUuid) {
        ShoppingItem cartItem = itemMap.get(itemUuid);
        itemMap.remove(itemUuid);
        return cartItem.getName();
    }

    @Override
    public double getTotal() {
        Double total = 0.0;
        List<ShoppingItem> cartContents = this.getShoppingCartItems();
        for (ShoppingItem item : cartContents) {
            total += item.getPrice();
        }
        
        return total;
    }

}
