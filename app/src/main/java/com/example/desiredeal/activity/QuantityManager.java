package com.example.desiredeal.activity;

import java.util.HashMap;

public class QuantityManager {
    private static QuantityManager instance;
    private HashMap<Integer, Integer> quantityMap;

    private QuantityManager() {
        quantityMap = new HashMap<>();
    }

    public static QuantityManager getInstance() {
        if (instance == null) {
            instance = new QuantityManager();
        }
        return instance;
    }

    public void setQuantity(int productId, int quantity) {
        quantityMap.put(productId, quantity);
    }

    public int getQuantity(int productId) {
        if (quantityMap.containsKey(productId)) {
            return quantityMap.get(productId);
        }
        return 0; // Default quantity is 0
    }
}

