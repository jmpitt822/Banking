/**
 * Created by jeremypitt on 9/1/16.
 */

import java.util.HashMap;


public class User {

    public User() {
        accountInfo.put("Jeremy", 100.00);
        accountInfo.put("John", 117.00);
        accountInfo.put("Linda", 58.00);
    }

    String name;
    String choice;
    HashMap<String, Double> accountInfo = new HashMap<>();
    Double balance;


    public void chooseName() throws Exception {
        System.out.println("Hello! What is your name?");
        name = Main.scanner.nextLine();
        if(name.length() > 0) {
            boolean accountExists = accountInfo.get(name) != null;
            if (accountExists) {
                System.out.println("Hello, " + name +"!");
                chooseAction();
            } else {
                System.out.println("Would you like to create an account? [y/n]");
                String acctChoice = Main.scanner.nextLine();
                Boolean createAccount = acctChoice.equals("y");
                if(createAccount){
                    System.out.println("How much would you like to deposit?");
                    String newDeposit = Main.scanner.nextLine();
                    Double newDepositNum = Double.parseDouble(newDeposit);
                    accountInfo.put(name, newDepositNum);
                    //prints hash map, for testing purposes
//                    for (String key: accountInfo.keySet()){
//                        System.out.println("key: " + key + ", value: " + accountInfo.get(key));
//                    }
                    chooseAction();
                }
                else{
                    System.out.println("Goodbye!");
                    chooseName();
                }
            }
        }
        else {
            throw new Exception("Name field can not be left blank!");
        }
    }
    public void chooseAction() throws Exception {
        System.out.println("What would you like to do? [Check Balance, Withdraw, Delete Account, Cancel]");
        choice = Main.scanner.nextLine();

        if(choice.equalsIgnoreCase("Check Balance")){
            checkBalance();
        }
        else if(choice.equalsIgnoreCase("Withdraw")){
            withdraw();

        }
        else if(choice.equalsIgnoreCase("Cancel")){
            cancel();
        }
        else if (choice.equalsIgnoreCase("Delete Account")){
            deleteAccount();
        }
        else{
            throw new Exception("Invalid choice: " + choice);
        }
    }
    public void checkBalance() throws Exception {
        balance = accountInfo.get(name);
        System.out.println("Your balance is $" + balance);
        chooseAction();
    }

    public void withdraw() throws Exception{
        String amount;
        System.out.println("How much would you like to withdraw?");
        amount = Main.scanner.nextLine();
        Double numAmount = Double.parseDouble(amount);
        if(numAmount <= accountInfo.get(name)){
            balance = (accountInfo.get(name) - numAmount);
            if(balance >= 0){
                System.out.println("Please take your money. Your new balance is $" + balance);
                accountInfo.put(name, balance);
                chooseAction();
            }
            else {
                throw new Exception("Error: Insufficient funds");
            }
        }
        else{
            throw new Exception("Error: Insufficient funds");
        }

    }
    public void deleteAccount() throws Exception {
        System.out.println("Are you sure you want to delete your account? [y/n]");
        String deleteChoice = Main.scanner.nextLine();
        boolean confirmDelete = deleteChoice.equals("y");
        if(confirmDelete){
            accountInfo.remove(name);
            System.out.println("Account deleted.");
            chooseName();
//            for (String key: accountInfo.keySet()){
//                System.out.println("key: " + key + ", value: " + accountInfo.get(key));
//            }
        }
        else{
            chooseAction();
        }
    }
    public void cancel() throws Exception {
        System.out.println("Thank you, and please come again.");
        chooseName();
    }

}
