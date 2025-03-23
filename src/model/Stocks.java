package model;

public class Stocks {


    String name;



    int quantity;
    int initialPrice;
    int currentPrice;

    public Stocks(String name, int quantity, int initialPrice) {
        this.name = name;
        this.quantity = quantity;
        this.initialPrice = initialPrice;
        this.currentPrice = initialPrice;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getName() {
        return name;
    }
    public int getCurrentPrice() {
        return currentPrice;
    }

    public void updateCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getInitialPrice() {
        return initialPrice;
    }


}
