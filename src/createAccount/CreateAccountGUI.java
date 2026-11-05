import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountGUI extends JFrame implements ActionListener {

    JTextField email;
    JPasswordField password;
    JButton signupButton, loginButton;
    JLabel logo, title, emailLabel, passwordLabel, prompt;

    Color beige = new Color(224, 215, 192);
    Color maroon = new Color(125, 39, 55);
    Color gold = new Color(239, 190, 75);
    Color red = new Color(220, 45, 45);

    public CreateAccountGUI() {
        setTitle("Shippiedie - Create Account");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        logo = new JLabel("Shippiedie");
        logo.setFont(new Font("Serif", Font.BOLD, 46));
        logo.setForeground(maroon);
        logo.setBounds(510, 45, 300, 65);
        add(logo);
        
        JPanel panel = new JPanel(null);
        panel.setBackground(beige);
        panel.setBounds(355, 150, 545, 435);
        add(panel);

        title = new JLabel("Create Account");
        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setBounds(143, 35, 300, 45);
        panel.add(title);

        emailLabel = new JLabel("E-mail");
        emailLabel.setBounds(47, 120, 100, 20);
        panel.add(emailLabel);

        email = new JTextField();
        email.setBounds(47, 147, 445, 36);
        panel.add(email);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(47, 210, 100, 20);
        panel.add(passwordLabel);

        password = new JPasswordField();
        password.setBounds(47, 237, 445, 36);
        panel.add(password);

        signupButton = new JButton("Sign Up");
        signupButton.setBounds(47, 302, 445, 33);
        signupButton.setBackground(gold);
        signupButton.setFocusPainted(false);
        signupButton.addActionListener(this);
        panel.add(signupButton);
        
        prompt = new JLabel("Go Back to");
        prompt.setBounds(205, 365, 75, 25);
        panel.add(prompt);

        loginButton = new JButton("<html><u>Log In</u></html>");
        loginButton.setForeground(red);
        loginButton.setBounds(278, 365, 70, 25);
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(this);
        panel.add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            if (email.getText().trim().isEmpty()
                    || password.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Please enter your Email and Password first!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Sign Up button clicked. Saving accounts is not connected yet.");
            }
        } else if (e.getSource() == loginButton) {
            JOptionPane.showMessageDialog(this,
                    "Going to Log In...");
        }
    }
}

