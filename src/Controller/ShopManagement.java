package Controller;

import Model.Fruit;
import Model.Order;
import View.Validation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopManagement {
    private Validation val = new Validation();
    private List<Fruit> listOfFruit  = new ArrayList<>();
    
    public Order getChoiceFruit() {
        Order customer = new Order();
        do {
            showFruitManage();   //hiển thị all fruit                           ///////
            System.out.println("=====================================");
            String choice = val.getString(">Your choice: ");
            if(searchFruit(choice) == null) {                           ///////
                System.out.println(">>That option doesn't exist!!");
                // cho lặp lại vòng lặp
            } else {
                Fruit frChoice = searchFruit(choice);                       ////////
                int quantityBuy = val.checkQuatityFruit(">Please input quantity: ", frChoice);
                
                if(customer.getOrder().containsKey(frChoice)) {
                    customer.getOrder().put( frChoice, (quantityBuy + customer.getOrder().get(frChoice)) );
                } else {
                    customer.getOrder().put(frChoice, quantityBuy);
                }

                boolean stopLoop = val.checkInputYN( "Do you want to order now(Y/N): ");    //stopLoop == true >>> stop
                if(stopLoop == true) {
                    System.out.println("===============================================");
                    return customer;
                }
            }
        } while (true);
    }
    
    public Order printAllProductWillBuy(Order customer) {
        double total = 0;
        System.out.println("|  Product  | Quantity |   Price   | Amount");
            for (Map.Entry<Fruit, Integer> entry : customer.getOrder().entrySet()) {
                Fruit fruit = entry.getKey();
                int quantity = entry.getValue();
                
                total += quantity * fruit.getPrice();
                System.out.printf("|%-11s| %-10d | %-11.3f | %.3f$ |\n",
                     fruit.getName(), quantity, fruit.getPrice(), (quantity * fruit.getPrice()) );
            }
            System.out.println("======================================");
            System.out.printf("Total: %.3f$\n", total);
            String name = val.getString(">Enter your name: ");
            customer.setCustomer(name);
            return customer;
    }
    
    ///////////////////////////////////////////////////
    
    public boolean isEmptyProduct() {
        return (listOfFruit == null);
    }
    
    public void addFruit() {
        String name = val.getAndValidValue(">Enter fruit name: ", "[\\pL ]+", "Fruit name just contain letter");
        for (Fruit fruit : listOfFruit) {
            if(fruit.getName().equals(name)) { System.out.println("This fruit is available!");   return; }
        }
        
        double price = val.getAndValidMoney(">Enter price of fruit: ");
        int quality = val.getInt(">Enter quantity: ");
        String origin = val.getAndValidValue(">Enter fruit origin: ", "[\\pL ]+", "Origin just contain letter");
        listOfFruit.add(new Fruit(name, price, quality, origin));
    }
    
    public void showFruitManage() {
        System.out.println("|+Iteam+| +++Fruit Name+++ | +++Origin+++ | +++Price+++ | +++Quantity+++ |");
        for (Fruit fruit : listOfFruit) {
            System.out.printf("|%-7d| %-16s | %-12s | %-11.3f | %-14d |\n",
                    fruit.getID(), fruit.getName(), fruit.getOrigin(), fruit.getPrice(), fruit.getQuantity() );
        }
    }
    
    public Fruit searchFruit(String nameOrId){
        for (Fruit fruit : listOfFruit) {
            String idFruit = String.valueOf(fruit.getID());
            if( (fruit.getName().equalsIgnoreCase(nameOrId))
                    || (idFruit.equalsIgnoreCase(nameOrId)) ) {return fruit;}
        }
        return null;
    }
    
    public void updateQuantity(String nameOrId, int newQuantity) {
        Fruit temp = searchFruit(nameOrId);
        if(temp == null) {
            System.out.println("Can't find that fruit!");
        } else {        //nếu nó ko đổi thì chạy search trực tiếp trong hàm này
            temp.setQuantity(newQuantity);
        }
    }
}      
