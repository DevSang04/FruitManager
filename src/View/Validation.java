package View;

import Model.Fruit;
import java.util.Scanner;

public class Validation {

    public String getValue(String msg) {
        Scanner sc = new Scanner(System.in);
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    public String getAndValidValue(String msg, String regex, String cause) {
        String value;
        while (true) {
            try {
                value = getValue(msg);
                if (value.isEmpty()) {
                    throw new Exception("Input cannot empty");
                }
                if (!value.matches(regex)) {
                    throw new Exception(cause);
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return value;
    }

    public double getAndValidMoney(String msg) {
        String money;
        
        while (true) {            
            try {
                money = getAndValidValue(msg, "[\\d.]+", "Invalid amount of money");
                
                if(Double.parseDouble(money) <= 0){
                    throw new Exception("Amounr of money must a postive number");
                }
                return Double.parseDouble(money);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid amout of money!");
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    //////////////////////////////////////
    
    public int getInt(String inputMsg) {
        Scanner sc = new Scanner(System.in);
        int result;
        
        do{
            try {
                System.out.print(inputMsg);
                result = Integer.parseInt(sc.nextLine());
                if(result >= 0) { 
                    return result;
                } else { System.err.println("Value must be >= 0"); }
            } catch (NumberFormatException e) {
                System.err.println("Pls enter integer number!! Try again!");
            }
        } while (true);
    }
    
    public String getString(String inputMsg) {
        Scanner sc = new Scanner(System.in);
        String result;
        do{
            try {
                System.out.print(inputMsg);
                result = sc.nextLine().trim();
                if(result.isEmpty() == true) {
                    System.out.println(">>You input nothing?");
                } else { return result; }
            } catch (Exception e) {
                System.err.println("Pls enter again!");
            }
        } while (true);
    }
    
    public int checkQuatityFruit(String msg, Fruit fr) {
        do {            
            int quantityBuy = getInt(msg);
            if(fr.getQuantity() < quantityBuy) {
                System.out.println("The quantity is too large, currently available " + fr.getQuantity());
            } else {
                fr.setQuantity(fr.getQuantity() - quantityBuy);
                return quantityBuy;
            } 
        } while (true);
    }
    
    public boolean checkInputYN(String inputMsg) {
        while (true) {
            try {
                String result = getString(inputMsg);
                if (result.equalsIgnoreCase("Y")) {
                    return true;
                } else if (result.equalsIgnoreCase("N")) {
                    return false;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Please input y/Y or n/N.");
            }
        }
    }
}
