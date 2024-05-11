
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ATM {

    private int balance = 5000;
    private String codePIN = "0000";
    private JFrame frame;
    private JLabel balanceLabel;

    public ATM() {
        frame = new JFrame("ATM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 215, 160));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = 400;
        int frameHeight = 400;
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
        frame.setLocation(x, y);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(255, 215, 160));
        JLabel welcomeLabel = new JLabel("Welcome to Banque Du Caire");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 18));
        topPanel.add(welcomeLabel);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        buttonPanel.setBackground(new Color(255, 215, 160));
        JButton withdrawbtn = new JButton("Withdraw");
        JButton depositbtn = new JButton("Deposit");
        JButton checkBalancebtn = new JButton("Check Balance");
        JButton miniStatmentbtn = new JButton("Mini Statement");
        JButton changePINbtn = new JButton("Change PIN");
        JButton exitbtn = new JButton("Exit");
        buttonPanel.add(withdrawbtn);
        buttonPanel.add(depositbtn);
        buttonPanel.add(checkBalancebtn);
        buttonPanel.add(miniStatmentbtn);
        buttonPanel.add(changePINbtn);
        buttonPanel.add(exitbtn);

        JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        balancePanel.setBackground(new Color(255, 215, 160));
        balanceLabel = new JLabel("Balance: " + balance);
        balanceLabel.setFont(new Font("Serif", Font.BOLD, 20));
        balancePanel.add(balanceLabel);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(balancePanel, BorderLayout.SOUTH);

        String enteredPIN = JOptionPane.showInputDialog(frame, "Enter your PIN:");
        if (enteredPIN == null || !enteredPIN.equals(codePIN)) {
            JOptionPane.showMessageDialog(frame, "Incorrect PIN");
            System.exit(0);
        }

        withdrawbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:");
                try {
                    int amount = Integer.parseInt(amountStr);
                    if (amount > balance) {
                        JOptionPane.showMessageDialog(frame, "Insufficient balance");
                    } else {
                        balance -= amount;
                        updateBalanceLabel();
                        JOptionPane.showMessageDialog(frame, "Please collect your cash");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount");
                }
            }
        });

        depositbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog(frame, "Enter amount to deposit:");
                try {
                    int amount = Integer.parseInt(amountStr);
                    balance += amount;
                    updateBalanceLabel();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount");
                }
            }
        });
        checkBalancebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBalanceLabel();
            }
        });
        miniStatmentbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder statement = new StringBuilder();
                statement.append("Mini Statement:\n");
                for (String transaction : getMiniStatement()) {
                    statement.append(transaction).append("\n");
                }
                JOptionPane.showMessageDialog(frame, statement.toString());
            }
        });

        changePINbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPIN = JOptionPane.showInputDialog(frame, "Enter new PIN:");
                if (newPIN != null && newPIN.length() == 4) {
                    codePIN = newPIN;
                    JOptionPane.showMessageDialog(frame, "PIN changed");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid PIN");
                }
            }
        });
        exitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    private List<String> getMiniStatement() {
        List<String> miniStatement = new ArrayList<>();
        miniStatement.add("Date: " + new java.util.Date());
        miniStatement.add("Balance: " + balance);
        return miniStatement;
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: " + balance);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATM());
    }
}
