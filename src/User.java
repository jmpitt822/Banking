/**
 * Created by jeremypitt on 9/1/16.
 */

import java.util.HashMap;


 class User {

     User() {
        accountInfo.put("Jeremy", 100.00);
        accountInfo.put("John", 117.00);
        accountInfo.put("Linda", 58.00);
    }

    String name;
    String nameEntry;
    HashMap<String, Double> accountInfo = new HashMap<>();


    public void generateUser() {
        Double balance = new Double(0);
        String balanceAmt = new String();
        boolean a = true;

        while (balance <= 0) {
            System.out.println("How much would you like to deposit?");
            balanceAmt = Main.scanner.nextLine();
            balance = validatePosDouble(balanceAmt);
        }
        accountInfo.put(name, balance);
    }


    public boolean chooseUser() throws Exception {
        boolean a = true;
        boolean b = true;
        String nameEntry = new String();

        while (b) {
            System.out.println("Hello! Enter your name to log in.");
            name = Main.scanner.nextLine();
            if (accountInfo.containsKey(name)) {
                System.out.println("Hello, " + name + "!");
                a = false;
                b = false;
            } else if (name.length() == 0) {
                System.out.println("Name field can not be left blank!");
            } else {
                System.out.println("Would you like to create an account? [y/n]");
                String acctChoice = Main.scanner.nextLine();
                if (acctChoice.equalsIgnoreCase("y")) {
                    generateUser();
                    b = false;
                } else if (acctChoice.equalsIgnoreCase("n")) {
                    System.out.println("Goodbye!");
                } else {
                    System.out.println("You must choose y or n!");
                }
            }
        }
        return a;
    }

    public boolean chooseAction() throws Exception {
        boolean b = false;
        boolean c = true;

        do {
            String choice = new String();
            System.out.println("What would you like to do? [Check Balance, Withdraw, Delete Account, Cancel]");
            choice = Main.scanner.nextLine();
            if (choice.equalsIgnoreCase("Check Balance")) {
                checkBalance();
                b = true;
            } else if (choice.equalsIgnoreCase("Withdraw")) {
                withdraw();
                b = true;
            } else if (choice.equalsIgnoreCase("Cancel")) {
                System.out.println("Thank you, and please come again.");
                b = false;
                c = false;
            } else if (choice.equalsIgnoreCase("Delete Account")) {
                b = deleteAccount();
                c = false;
            } else {
                System.out.println("Invalid choice: " + choice);
            }
        } while (b);
        return c;
    }

    public void checkBalance() throws Exception {
        Double balance = accountInfo.get(name);
        System.out.println("Your balance is $" + balance);
    }

    public void withdraw() throws Exception {
        String amount;

        Double balance = (accountInfo.get(name));
        boolean a = true;
        while (a) {
            Double withdrawal = new Double(0);

            while (withdrawal <= 0) {
                System.out.println("How much would you like to withdraw?");
                amount = Main.scanner.nextLine();
                withdrawal = validatePosDouble(amount);
            }


            if (withdrawal <= balance) {
                balance -= withdrawal;
                System.out.println("Please take your money. Your new balance is $" + balance);
                accountInfo.put(name, balance);
                a = false;

            } else {
                System.out.println("Error: Insufficient funds");
            }
        }

    }

    public boolean deleteAccount() throws Exception {
        boolean a = true;
        boolean b = true;
        String deleteChoice = new String();
        while (b) {
            System.out.println("Are you sure you want to delete your account? [y/n]");
            deleteChoice = Main.scanner.nextLine();
            if (deleteChoice.equalsIgnoreCase("y")) {
                accountInfo.remove(name);
                System.out.println("Account deleted.");
                a = false;
                b = false;
            } else if (deleteChoice.equalsIgnoreCase("n")) {
                b = false;
            } else {
                System.out.println("Error: must choose y or n");
            }
        }
        return a;
    }


    public Double validatePosDouble(String s) {
        Double b = new Double(0);
        try {
            b = Double.parseDouble(s);
        } catch (Exception e) {
            System.out.println("Please enter a valid number");
        }
        return b;
    }

}
