import model.Admin;
import service.FlipStocksService;

public class DriverClass {
    public static  void main(String[] args) {
        FlipStocksService service = new FlipStocksService(new Admin("admin"));
        service.adminLogin("admin");
        service.addStocks("stock1", 100);
        service.addStocks("stock2", 200);
        service.addStocks("stock3", 300);
        service.addUser("user", 1000);
        service.addUser("user2",500);
//        service.adminLogout();
        service.listAllStockPrices();

        service.moveTimeStamp();
        service.listAllStockPrices();
        service.addUser("user3", 1000);
        service.buyStock("user3", "stock1", 2);
        service.viewPortfolio("user3");
        service.moveTimeStamp();
        service.sellStock("user3", "stock1", 1);
        service.viewPortfolio("user3");
        service.sellStock("user3", "stock1", 2);
//
//        service.buyStock("user", "stock1", 2);
//        service.viewPortfolio("user");
//        service.sellStock("user", "stock1", 1);
//        service.viewPortfolio("user");
//        service.listAllStockPrices();
//        service.getStockPrice("stock1");
//        service.adminLogin("admin");
//        service.moveTimeStamp();
//        service.adminLogout();
//        service.listAllStockPrices();
//        service.getUserDetails("user");
//        service.viewPortfolio("user");

    }
}
