package org.example.generators;

import org.example.model.Order;

public class OrderGenerator {

    public static Order getOrderBlack(){
        return new Order("fname","lname","address","Station","+711111111",5,"2020-06-06","hello", new String[]{"BLACK"});
    }

    public static Order getDefault(){
        return new Order("fname","lname","address","Station","+711111111",5,"2020-06-06","hello");
    }


    public static Object getOrderBlackGrey() {
        return new Order("fname","lname","address","Station","+711111111",5,"2020-06-06","hello", new String[]{"BLACK","GREY"});
    }
}
