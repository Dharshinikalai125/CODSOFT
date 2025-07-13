import java.util.Scanner;

// Class to represent the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return false;
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return false;
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful.");
            return true;
        }
    }
}

// Class to represent the ATM
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // User interface
            System.out.println("\n=== ATM MENU ===");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);

        scanner.close();
    }

    // Methods connected to bank account
    private void checkBalance() {
        System.out.printf("Current Balance: ₹%.2f\n", account.getBalance());
    }

    private void deposit(double amount) {
        account.deposit(amount);
    }

    private void withdraw(double amount) {
        account.withdraw(amount);
    }
}

// Main class to run the ATM simulation
public class Task3 {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.00);  // initial balance
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}

/*OUTPUT:

=== ATM MENU ===
1. Check Balance
2. Deposit
3. Withdraw
4. Exit
Enter your choice: 1
Current Balance: ?1000.00

=== ATM MENU ===
1. Check Balance
2. Deposit
3. Withdraw
4. Exit
Enter your choice: 2
Enter amount to deposit: 2000
Amount deposited successfully.

=== ATM MENU ===
1. Check Balance
2. Deposit
3. Withdraw
4. Exit
Enter your choice: 1
Current Balance: ?3000.00

=== ATM MENU ===
1. Check Balance
2. Deposit
3. Withdraw
4. Exit */
