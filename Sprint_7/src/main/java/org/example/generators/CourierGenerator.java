package org.example.generators;

import org.example.model.Courier;

public class CourierGenerator {

    public static Courier getDefault(){
        return new Courier("login090909090909","password","");
    }

    public static Courier getWithoutField(){
        return new Courier(null,"password","");
    }

    public static Courier getNotExist(){
        return new Courier("login09090909091","password","");
    }
}
