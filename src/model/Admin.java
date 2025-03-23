package model;

import java.util.ArrayList;
import java.util.List;


public class Admin {
    String userName;


    List<Stocks> listOfStocks =new ArrayList<>();

     public Admin(String userName) {
         this.userName = userName;
     }

  public void addStock(Stocks stocks) {
   listOfStocks.add(stocks);
  }
    public List<Stocks> getListOfStocks() {
        return listOfStocks;
    }

}
