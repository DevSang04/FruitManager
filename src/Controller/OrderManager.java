
package Controller;

import Model.Fruit;
import Model.Order;
import View.Validation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderManager {
    private List<Order> listOrder = new ArrayList<>();
    //private Product pr = new Product();
    private Validation val = new Validation();

    public void Ordering(Order newCustom) {     //add oder vào list
        listOrder.add(newCustom);
    }
    
    public void showAllOrder() {
        System.out.println("=========================================================");
        if(listOrder.isEmpty()){
            System.out.println(">>We don't have any orders!!");
        } else {
            for (Order customer : listOrder) {
                double total = 0;
                System.out.println("Customer: "+ customer.getCustomer());
                System.out.println("|    Product    | Quantity |    Price    | Amount");
                for (Map.Entry<Fruit, Integer> entry : customer.getOrder().entrySet()) {
                    Fruit fruit = entry.getKey();
                    int quantity = entry.getValue();

                    total += quantity * fruit.getPrice();
                    System.out.printf("|%-15s| %-8d | %-11.2f | %.2f$ \n",
                         fruit.getName(), quantity, fruit.getPrice(), (quantity * fruit.getPrice()) );
                }
                System.out.printf("Total: %.2f$\n", total);
                System.out.println("<<<*****************************************>>>");
            }
        }
        System.out.println("=========================================================");
    }
    
}
