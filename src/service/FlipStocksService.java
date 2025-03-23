package service;

import model.Admin;
import model.Portfolio;
import model.Stocks;
import model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FlipStocksService {

    private Map<String, User> users;
    private Admin admin;
    private Map<String, Stocks> stocks;
    private int timeStampCount;

    public FlipStocksService(Admin admin) {
        this.users = new HashMap<>();
        this.stocks = new HashMap<>();
        this.admin = admin;
        this.timeStampCount = 0;
    }

    public void adminLogin(String username) {
        admin = new Admin(username);
    }

    public void adminLogout() {
        admin = null;
    }


    public void addStocks(String stockName, int initialPrice) {
        if (admin != null) {
            if (!stocks.containsKey(stockName)) {
                Stocks stock = new Stocks(stockName, 0, initialPrice);
                stocks.put(stockName, stock);
                admin.addStock(stock);
                System.out.println("Stock added successfully");
            } else {
                System.err.println("Stock already exists");
            }
        } else {
            System.err.println("Not Enough Privileges");
        }
    }

    public void addUser(String username, int walletBalance) {
        if (admin != null) {
            if (!users.containsKey(username)) {
                User user = new User(username, walletBalance);
                users.put(username, user);
                System.out.println("User added successfully");
            } else {
                System.err.println("User already exists");
            }
        } else {
            System.err.println("Not Enough Privileges");
        }
    }

    public void moveTimeStamp() {

        if (timeStampCount <= 10) {
            timeStampCount++;
            Random random = new Random();
            for (Map.Entry<String,Stocks>stocksEntry:stocks.entrySet()) {
                Stocks stock = stocksEntry.getValue();
                int previousPrice = stock.getCurrentPrice();
                double minPrice = previousPrice * 0.5;
                double maxPrice = previousPrice * 1.5;
                int newPrice = (int) (minPrice + (maxPrice - minPrice) * random.nextDouble());
                stock.updateCurrentPrice(newPrice);
            }
            System.out.println("Time stamp moved to t" + timeStampCount);
        }
    else
           System.err.println("Time stamp limit reached");
    }

    public void userLogin(String username) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            user.setPortfolio(new Portfolio(username));
        } else {
            System.err.println("User does not exist");
        }
    }

    public void buyStock(String username, String stockName, int quantity) {
        if (users.containsKey(username) && stocks.containsKey(stockName)) {
            User user = users.get(username);
            Stocks stock = stocks.get(stockName);

            if(user!=null && stock!=null && quantity>0) {
                int price = stock.getCurrentPrice();
                int totalCost = price * quantity;
                user.setPortfolio(new Portfolio(username));
                if (user.getWalletBalance() >= totalCost) {
                    int updateBalance = user.getWalletBalance() - totalCost;
                    user.setWalletBalance(updateBalance);
                    user.updatePortfolio(stockName, quantity);
                    System.out.println("Stocks bought successfully");
                } else {
                    System.err.println("Not possible");
                }

            }else
                System.err.println("User or can not be null");
        }else
        {
            System.err.println("User or Stock does not exist");
        }
    }

    public void sellStock(String username, String stockName, int quantity) {
        if (users.containsKey(username) && stocks.containsKey(stockName)) {
            User user = users.get(username);
            Stocks stock = stocks.get(stockName);

            if(user!=null && stock!=null ){
                if(quantity<=user.getPortfolio().getListOfSocks().get(stockName)) {

                int price = stock.getCurrentPrice();
                int totalCost = price * quantity;
                int updateBalance = user.getWalletBalance() + totalCost;
                user.setWalletBalance(updateBalance);
                user.sellStock(stockName, quantity);
                System.out.println("Stocks sold successfully");
            }else
                {
                    System.err.println("Not enough stocks to sell");
                }
            }else
                System.err.println("User or can not be null");
        }else
        {
            System.err.println("User or Stock does not exist");
        }
    }
public void viewPortfolio(String userName) {
    User user = users.get(userName);
    if (user != null) {
        Portfolio portfolio = user.getPortfolio();
        if (portfolio != null) {
            Map<String, Integer> stockslist = portfolio.getListOfSocks();
            for (Map.Entry<String, Integer> entry : stockslist.entrySet()) {
                Stocks stock = stocks.get(entry.getKey());
                if (stock != null) {
                    System.out.println("Portfolio details " + stock.getName() + ": " + stock.getCurrentPrice() + " Quantity " + entry.getValue());
                } else {
                    System.out.println("Stock " + entry.getKey() + " does not exist in the stocks map.");
                }
            }
            portfolio.setPortfolioValue(calculateReturnRate(user));
        } else {
            System.out.println("Portfolio does not exist.");
        }
    } else {
        System.out.println("User does not exist.");
    }
}
    public void listAllStockPrices() {
        System.out.println("Stock Prices at t" + timeStampCount + ":");
        for (Map.Entry<String,Stocks>stocksEntry:stocks.entrySet()) {
            Stocks stock = stocksEntry.getValue();
            System.out.println(stock.getName() + ": " + stock.getCurrentPrice());
        }
    }

    public double getStockPrice(String stockName) {
        if(stocks.containsKey(stockName)) {
            return stocks.get(stockName).getCurrentPrice();
        }else {
            System.err.println("Stock does not exist");
            return -1;
        }
    }
    public int calculateReturnRate(User user) {
        double initialInvestment = 0;
        double finalInvestment = 0;
        Portfolio portfolio = user.getPortfolio();
        if (portfolio != null && portfolio.getListOfSocks() != null) {
            for (Map.Entry<String, Integer> entry : portfolio.getListOfSocks().entrySet()) {
                String stockName = entry.getKey();
                int quantity = entry.getValue();
                for (Map.Entry<String,Stocks>stocksEntry:stocks.entrySet()) {
                    Stocks stock = stocksEntry.getValue();
                    if (stock.getName().equals(stockName)) {
                        initialInvestment += stock.getInitialPrice() * quantity;
                        finalInvestment += stock.getCurrentPrice() * quantity;
                        break;
                    }
                }
            }
        }
        if (initialInvestment == 0) {
            return 0;
        }
        return (int) ((int)(finalInvestment - initialInvestment) / initialInvestment * 100);
    }
}


