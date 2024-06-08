import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class BankingSystem {
    private static Map<Integer, Account> accounts = new HashMap<>();
    private static JFrame frame;
    private static JTextField accountIdField;
    private static JTextField amountField;
    private static JTextArea textArea;

    public static void main(String[] args) {
        
        Account account = new Account(1, "Karan", .00);
        accounts.put(account.getId(), account);

        frame = new JFrame("Banking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Account ID:"));
        accountIdField = new JTextField(5);
        panel.add(accountIdField);

        panel.add(new JLabel("Amount:"));
        amountField = new JTextField(5);
        panel.add(amountField);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });
        panel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });
        panel.add(withdrawButton);

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);

        frame.getContentPane().add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static void deposit() {
        int id = Integer.parseInt(accountIdField.getText());
        double amount = Double.parseDouble(amountField.getText());

        Account account = accounts.get(id);

        if (account == null) {
            textArea.append("Account not found.\n");
            return;
        }

        account.deposit(amount);

        textArea.append("Deposit successful: " + account.toString() + "\n");
    }

    private static void withdraw() {
        int id = Integer.parseInt(accountIdField.getText());
        double amount = Double.parseDouble(amountField.getText());

        Account account = accounts.get(id);

        if (account == null) {
            textArea.append("Account not found.\n");
            return;
        }

        account.withdraw(amount);

        textArea.append("Withdrawal successful: " + account.toString() + "\n");
    }

    public static class Account {
        private final int id;
        private String name;
        private double balance;

        public Account(int id, String name, double balance) {
            this.id = id;
            this.name = name;
            this.balance = balance;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public void withdraw(double amount) {
            if (balance >= amount) {
                balance -= amount;
            } else {
                System.out.println("Insufficient balance.");
            }
        }

        @Override
        public String toString() {
            return "Account [id=" + id + ", name=" + name + ", balance=" + balance + "]";
        }
    }
}