import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JPasswordField passcodeField;
    private JLabel statusMessage;
    private String storedPassword = null;

    public Main() {

        setTitle("Cool Secure Locker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(45, 45, 45));


        JPanel buttonPanel = new JPanel(new GridLayout(4, 3, 10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, new Color(60, 60, 60), getWidth(), getHeight(), new Color(20, 20, 20)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 1; i <= 9; i++) {
            JButton button = createCustomButton(String.valueOf(i));
            buttonPanel.add(button);
        }
        JButton zeroButton = createCustomButton("0");
        buttonPanel.add(new JLabel());
        buttonPanel.add(zeroButton);

        // Panel for passcode input and actions
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, new Color(80, 80, 80), getWidth(), getHeight(), new Color(40, 40, 40)));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        passcodeField = new JPasswordField(12);
        passcodeField.setFont(new Font("Arial", Font.PLAIN, 18));
        passcodeField.setForeground(Color.WHITE);
        passcodeField.setBackground(new Color(30, 30, 30));
        inputPanel.add(passcodeField, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JButton enterButton = createCustomButton("Enter");
        enterButton.addActionListener(new EnterButtonListener());
        actionPanel.add(enterButton);

        JButton clearButton = createCustomButton("Clear");
        clearButton.addActionListener(new ClearButtonListener());
        actionPanel.add(clearButton);
        inputPanel.add(actionPanel, BorderLayout.SOUTH);


        JPanel statusPanel = new JPanel();
        statusMessage = new JLabel(" ");
        statusMessage.setFont(new Font("Arial", Font.BOLD, 14));
        statusMessage.setForeground(Color.WHITE);
        statusPanel.setBackground(new Color(45, 45, 45));
        statusPanel.add(statusMessage);


        add(buttonPanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(statusPanel, BorderLayout.SOUTH);

        setVisible(true);
    }


    private JButton createCustomButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 70, 70));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        button.addActionListener(new NumericButtonListener());
        return button;
    }


    private class NumericButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String num = e.getActionCommand();
            passcodeField.setText(passcodeField.getText() + num);
        }
    }


    private class EnterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredPassword = new String(passcodeField.getPassword());
            if (storedPassword == null) {
                storedPassword = enteredPassword;
                statusMessage.setText("Password Set");
            } else {
                if (enteredPassword.equals(storedPassword)) {
                    statusMessage.setText("Correct Password");
                } else {
                    statusMessage.setText("Incorrect Password");
                }
            }
        }
    }


    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            passcodeField.setText("");
            statusMessage.setText(" "); 
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new Main());
    }
}
