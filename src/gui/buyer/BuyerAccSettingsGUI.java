/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buyerAccSettings;
/**
 *
 * @author Admin
 */
import javax.swing.*;
import java.awt.*;

public class BuyerAccSettingsGUI extends JFrame {

    private JPanel topNav, sideNav, contentPanel, profileCard, formCard;
    private JLabel lblLogo, lblTitle, lblSubtitle;
    private JLabel lblMenuProfile, lblMenuAddress, lblMenuPayment, lblMenuOrders, lblMenuVouchers, lblMenuSecurity;
    private JLabel lblNameCard, lblEmailCard, lblBadge;
    private JLabel lblFullName, lblUsername, lblEmail, lblPhone, lblGender, lblDob;
    private JTextField txtFullName, txtUsername, txtEmail, txtPhone, txtGender, txtDob;
    private JButton btnSave;

    BuyerAccSettingsGUI() {

        setTitle("Shippiedie - Buyer Account Settings");
        setSize(1280, 720);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color beige = new Color(224, 215, 192);
        Color gold = new Color(239, 190, 75);

        getContentPane().setBackground(beige);

        // Top Navigation Bar
        topNav = new JPanel();
        topNav.setLayout(null);
        topNav.setBounds(0, 0, 1280, 75);
        topNav.setBackground(gold);
        add(topNav);

        lblLogo = new JLabel("SHIPPIEDIE");
        lblLogo.setBounds(40, 20, 150, 30);
        lblLogo.setFont(new Font("Arial", Font.BOLD, 18));
        lblLogo.setForeground(Color.DARK_GRAY);
        topNav.add(lblLogo);

        // Sidebar Navigation
        sideNav = new JPanel();
        sideNav.setLayout(null);
        sideNav.setBounds(0, 75, 260, 645);
        sideNav.setBackground(beige);
        add(sideNav);

        JLabel lblMenuTitle = new JLabel("ACCOUNT SETTINGS");
        lblMenuTitle.setBounds(30, 25, 180, 20);
        lblMenuTitle.setFont(new Font("Arial", Font.BOLD, 11));
        lblMenuTitle.setForeground(Color.GRAY);
        sideNav.add(lblMenuTitle);

        lblMenuProfile = new JLabel("  My Profile");
        lblMenuProfile.setBounds(20, 55, 220, 40);
        lblMenuProfile.setFont(new Font("Arial", Font.BOLD, 13));
        lblMenuProfile.setOpaque(true);
        lblMenuProfile.setBackground(new Color(212, 200, 175));
        sideNav.add(lblMenuProfile);

        lblMenuAddress = new JLabel("  Addresses & Shipping");
        lblMenuAddress.setBounds(20, 105, 220, 30);
        lblMenuAddress.setFont(new Font("Arial", Font.PLAIN, 13));
        sideNav.add(lblMenuAddress);

        lblMenuPayment = new JLabel("  Payment Methods");
        lblMenuPayment.setBounds(20, 145, 220, 30);
        lblMenuPayment.setFont(new Font("Arial", Font.PLAIN, 13));
        sideNav.add(lblMenuPayment);

        lblMenuOrders = new JLabel("  My Orders");
        lblMenuOrders.setBounds(20, 185, 220, 30);
        lblMenuOrders.setFont(new Font("Arial", Font.PLAIN, 13));
        sideNav.add(lblMenuOrders);

        lblMenuVouchers = new JLabel("  My Vouchers");
        lblMenuVouchers.setBounds(20, 225, 220, 30);
        lblMenuVouchers.setFont(new Font("Arial", Font.PLAIN, 13));
        sideNav.add(lblMenuVouchers);

        lblMenuSecurity = new JLabel("  Privacy & Security");
        lblMenuSecurity.setBounds(20, 265, 220, 30);
        lblMenuSecurity.setFont(new Font("Arial", Font.PLAIN, 13));
        sideNav.add(lblMenuSecurity);

        // Main Content Area
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(260, 75, 1020, 645);
        contentPanel.setBackground(new Color(245, 242, 235));
        add(contentPanel);

        lblTitle = new JLabel("My Profile Settings");
        lblTitle.setBounds(50, 30, 300, 30);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(Color.DARK_GRAY);
        contentPanel.add(lblTitle);

        lblSubtitle = new JLabel("Manage your personal information and account preferences.");
        lblSubtitle.setBounds(50, 60, 500, 20);
        lblSubtitle.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSubtitle.setForeground(Color.GRAY);
        contentPanel.add(lblSubtitle);

        btnSave = new JButton("Save Changes");
        btnSave.setBounds(810, 40, 150, 45);
        btnSave.setBackground(gold);
        btnSave.setForeground(Color.DARK_GRAY);
        btnSave.setFont(new Font("Arial", Font.BOLD, 13));
        btnSave.setFocusPainted(false);
        btnSave.setEnabled(false);
        contentPanel.add(btnSave);

        // Left Profile Card
        profileCard = new JPanel();
        profileCard.setLayout(null);
        profileCard.setBounds(50, 110, 240, 480);
        profileCard.setBackground(Color.WHITE);
        contentPanel.add(profileCard);

        lblNameCard = new JLabel("Juan Dela Cruz", SwingConstants.CENTER);
        lblNameCard.setBounds(20, 160, 200, 25);
        lblNameCard.setFont(new Font("Arial", Font.BOLD, 15));
        profileCard.add(lblNameCard);

        lblEmailCard = new JLabel("juan.delacruz@example.com", SwingConstants.CENTER);
        lblEmailCard.setBounds(20, 185, 200, 20);
        lblEmailCard.setFont(new Font("Arial", Font.PLAIN, 11));
        lblEmailCard.setForeground(Color.GRAY);
        profileCard.add(lblEmailCard);

        lblBadge = new JLabel("Verified Buyer", SwingConstants.CENTER);
        lblBadge.setBounds(50, 230, 140, 30);
        lblBadge.setFont(new Font("Arial", Font.BOLD, 11));
        lblBadge.setForeground(new Color(40, 140, 40));
        lblBadge.setOpaque(true);
        lblBadge.setBackground(new Color(220, 245, 220));
        profileCard.add(lblBadge);

        // Right Form Fields Card
        formCard = new JPanel();
        formCard.setLayout(null);
        formCard.setBounds(310, 110, 650, 480);
        formCard.setBackground(Color.WHITE);
        contentPanel.add(formCard);

        lblFullName = new JLabel("Full Name");
        lblFullName.setBounds(40, 30, 200, 20);
        lblFullName.setFont(new Font("Arial", Font.BOLD, 12));
        formCard.add(lblFullName);

        txtFullName = new JTextField("Juan Dela Cruz");
        txtFullName.setBounds(40, 55, 260, 35);
        txtFullName.setEnabled(false);
        formCard.add(txtFullName);

        lblUsername = new JLabel("Username");
        lblUsername.setBounds(330, 30, 200, 20);
        lblUsername.setFont(new Font("Arial", Font.BOLD, 12));
        formCard.add(lblUsername);

        txtUsername = new JTextField("juandc_99");
        txtUsername.setBounds(330, 55, 260, 35);
        txtUsername.setEnabled(false);
        formCard.add(txtUsername);

        lblEmail = new JLabel("Email Address");
        lblEmail.setBounds(40, 120, 200, 20);
        lblEmail.setFont(new Font("Arial", Font.BOLD, 12));
        formCard.add(lblEmail);

        txtEmail = new JTextField("juan.delacruz@example.com");
        txtEmail.setBounds(40, 145, 260, 35);
        txtEmail.setEnabled(false);
        formCard.add(txtEmail);

        lblPhone = new JLabel("Phone Number");
        lblPhone.setBounds(330, 120, 200, 20);
        lblPhone.setFont(new Font("Arial", Font.BOLD, 12));
        formCard.add(lblPhone);

        txtPhone = new JTextField("+63 917 123 456");
        txtPhone.setBounds(330, 145, 260, 35);
        txtPhone.setEnabled(false);
        formCard.add(txtPhone);

        lblGender = new JLabel("Gender");
        lblGender.setBounds(40, 210, 200, 20);
        lblGender.setFont(new Font("Arial", Font.BOLD, 12));
        formCard.add(lblGender);

        txtGender = new JTextField("Male");
        txtGender.setBounds(40, 235, 260, 35);
        txtGender.setEnabled(false);
        formCard.add(txtGender);

        lblDob = new JLabel("Date of Birth");
        lblDob.setBounds(330, 210, 200, 20);
        lblDob.setFont(new Font("Arial", Font.BOLD, 12));
        formCard.add(lblDob);

        txtDob = new JTextField("06/15/1995");
        txtDob.setBounds(330, 235, 260, 35);
        txtDob.setEnabled(false);
        formCard.add(txtDob);

        setVisible(true);
    }

}