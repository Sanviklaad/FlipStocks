package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Portfolio {
    String userName;
    Map<String,Integer> listOfSocks;
    int portfolioValue;
    int finalRate;

    public Portfolio(String userName) {
        this.userName = userName;
        this.listOfSocks = new HashMap<>();
        this.portfolioValue = 0;
        this.finalRate = 0;
    }

    public Map<String, Integer> getListOfSocks() {
        return listOfSocks;
    }

    public void reduceStocks(String stockName, int quantity) {
        if (listOfSocks.containsKey(stockName)) {
            int currentQuantity = listOfSocks.get(stockName);
            if (currentQuantity - quantity > 0) {
                listOfSocks.put(stockName, currentQuantity - quantity);
            } else {
                listOfSocks.remove(stockName);
            }
        }
    }
    public void setPortfolioValue(int portfolioValue) {
        this.portfolioValue = portfolioValue;
    }

    public int getPortfolioValue() {
        return portfolioValue;
    }

}
