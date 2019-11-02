/*
Assignment on : Vending Machine
Author: Obaydullah
ID: 19511013
Year: 1st, Semester: 2nd
Bangladesh University of Professionals
 */
package com.machine;

import java.util.*;

import static java.lang.System.out;

class CashRegister{
    private int cashOnHand;
    CashRegister(){
        cashOnHand=500;
    }
    CashRegister(int cashOnHand){
        this.cashOnHand=cashOnHand;
    }
    int getCurrentBalance(){
        return cashOnHand;
    }
    void acceptAmount(int amount){
        cashOnHand+=amount;
    }
}
class DispenserType{
    private String Type;
    private int numberOfItems;
    private int cost;
    DispenserType(){
        Type="Candy";
        cost=50;
        numberOfItems=50;
    }
    DispenserType(String Type,int numberOfItems,int cost){
        this.Type=Type;
        this.numberOfItems=numberOfItems;
        this.cost=cost;
    }
    String getType(){
        return Type;
    }
    int getNumberOfItems(){
        return numberOfItems;
    }
    int getCost(){
        return cost;
    }
    void makeSale(){
        numberOfItems--;
    }
}
class Controller{
    Vector<DispenserType> dispenserTypes;
    Controller(){
        dispenserTypes=new Vector<DispenserType>();
    }
    DispenserType getDispenserType(int selection){
        return dispenserTypes.get(selection);
    }
    void AddItems() {
        Random r = new Random();
        int items = r.nextInt(50);
        int cost=r.nextInt(20)+5;
        dispenserTypes.add(new DispenserType("Candy",items,cost));
        items = r.nextInt(50);
        cost=r.nextInt(20)+5;
        dispenserTypes.add(new DispenserType("Chips",items,cost));
        items = r.nextInt(50);
        cost=r.nextInt(20)+5;
        dispenserTypes.add(new DispenserType("Gum",items,cost));
        items = r.nextInt(50);
        cost=r.nextInt(20)+5;
        dispenserTypes.add(new DispenserType("Cookie",items,cost));
    }
    void Show(){
        int x=0;
        for(DispenserType obj:dispenserTypes){
            out.println(++x +". Type: "+obj.getType()+"\tAvailable: "+obj.getNumberOfItems()+"\tCostPP: "+obj.getCost());
        }
        out.println("5. Balance");
        out.println("6. Add Balance");
        out.println("7. Withdraw Balance");
        out.println("8. Exit");
    }
}
public class program {
    public static int showSelection(Controller controller){
        controller.Show();
        Scanner in=new Scanner(System.in);
        out.println("Your Selection is: ");
        int selection=in.nextInt();
        return selection;
    }
    public static void sellProduct(Controller controller,CashRegister cashRegister,int selection){
        DispenserType dispenserType=controller.getDispenserType(selection);
        if(dispenserType.getNumberOfItems()>0){
            if(dispenserType.getCost()<=cashRegister.getCurrentBalance()) {
                out.println("\u001B[34m"+"Your attempt to buy "+ dispenserType.getType()+" is successful!! :)");
                out.println("Here is your Item! Woooo!!!"+"\u001B[0m");
                dispenserType.makeSale();
                cashRegister.acceptAmount(-dispenserType.getCost());
                Scanner in=new Scanner(System.in);
                out.println("\u001B[33m"+"Wanna Buy more? Press 1");
                out.println("If not Press any number"+"\u001B[0m");
                int select=in.nextInt();
                if(select==1) sellProduct(controller, cashRegister, selection);
            }else{
                out.println( "\u001B[31m"+"You have insufficient Balance to buy this item,Try Another :("+"\u001B[0m");

            }
        }else {
            out.println( "\u001B[31m"+"Item is Null,Try Another!!"+"\u001B[0m");
        }
    }
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
	    Controller controller=new Controller();
	    controller.AddItems();
	    CashRegister cashRegister=new CashRegister(new Random().nextInt(10000)+20);
	    boolean ok=true;
	    while(ok){
            int selection=showSelection(controller);
            switch (selection) {
                case 1:
                case 2:
                case 3:
                case 4:
                    sellProduct(controller, cashRegister, selection-1);
                    break;
                case 5:
                    out.println("\u001B[32m"+"Your Credit is: "+cashRegister.getCurrentBalance()+"\u001B[0m");
                    break;
                case 6:
                    out.println("Enter Your Amount: ");
                    int amount=in.nextInt();
                    cashRegister.acceptAmount(amount);
                    out.println("\u001B[32m"+"Taka "+amount+" Successfully Added"+"\u001B[0m");
                    break;
                case 7:
                    out.println("How much you want to withdraw? :");
                    int amounts=in.nextInt();
                        if(amounts<=cashRegister.getCurrentBalance()) {
                            cashRegister.acceptAmount(-amounts);
                            out.println("\u001B[32m"+"Taka "+amounts+" Successfully Withdrawn!"+"\u001B[0m");
                            break;
                        }
                        else{
                            out.println( "\u001B[31m"+"Sorry!! You have insufficient Balance!"+"\u001B[0m");
                            break;
                        }
                case 8:
                    out.println( "\u001B[31m"+"Exiting,Be Fine!!"+"\u001B[0m");
                    ok=false;
                    break;
                default:
                    out.println( "\u001B[31m"+"Wrong Input"+"\u001B[0m");
                    break;
            }
        }
    }
}
