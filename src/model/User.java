package model;

import java.util.Map;

public class User {

    String username;
    int walletBalance;
    Portfolio portfolio;

    public User(String username, int walletBalance) {
        this.username = username;
        this.walletBalance = walletBalance;
        this.portfolio = new Portfolio(username);
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public int getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(int walletBalance) {
        this.walletBalance = walletBalance;
    }


    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public void updatePortfolio(String stockName, int quantity) {
        portfolio.listOfSocks.put(stockName, quantity);
    }

    public void sellStock(String stockName, int quantity) {
        portfolio.reduceStocks(stockName, quantity);
    }

}