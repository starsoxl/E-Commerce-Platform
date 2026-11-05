import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginGUI extends JFrame implements ActionListener {

    JTextField email;
    JPasswordField password;
    JButton loginButton, signupButton;
    JLabel logo, title, emailLabel, prompt, passwordLabel;

    Color beige = new Color(224, 215, 192);
    Color maroon = new Color(125, 39, 55);
    Color gold = new Color(239, 190, 75);
    Color red = new Color(220, 45, 45);

    public LoginGUI() {
        setTitle("Shippiedie - Log In");
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

        title = new JLabel("Log In");
        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setBounds(205, 35, 150, 45);
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
        
        loginButton = new JButton("Log In");
        loginButton.setBounds(47, 302, 445, 33);
        loginButton.setBackground(gold);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        prompt = new JLabel("Don't have an account?");
        prompt.setBounds(165, 365, 155, 25);
        panel.add(prompt);

        signupButton = new JButton("<html><u>Sign Up</u></html>");
        signupButton.setForeground(red);
        signupButton.setBounds(320, 365, 80, 25);
        signupButton.setBorderPainted(false);
        signupButton.setContentAreaFilled(false);
        signupButton.setFocusPainted(false);
        signupButton.addActionListener(this);
        panel.add(signupButton);
    }
    
    /**
     *
     * @param 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (email.getText().trim().isEmpty()
                    || password.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Please enter your Email and Password!", "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Log In button clicked. Account verification is not connected yet.");
            }
        } else if (e.getSource() == signupButton) {
            JOptionPane.showMessageDialog(this, "Create Account transporting...");
        }
    }
}


