package productView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class ProductView extends JFrame {

    // ---------- colors
    private Color gold = new Color(208, 175, 102);
    private Color maroon = new Color(102, 34, 34);
    private Color bg = new Color(249, 245, 237);
    private Color cardBg = new Color(228, 219, 196);
    private Color white = Color.WHITE;
    private Color gray = Color.LIGHT_GRAY;

    // ---------- header
    private JPanel header;
    private JLabel title, cartIcon, userIcon, gearIcon;

    // ---------- product view components
    private JPanel mainContainer;
    private JLabel backButton, productName, productPrice, productRating, availableLabel, quantityLabel;
    private JTextArea productDescription;
    private JPanel mainImagePlace;
    private JPanel[] thumbImages = new JPanel[3];
    private JPanel colorBlackDot, colorWhiteDot;
    private JLabel colorBlackText, colorWhiteText;
    private JPanel qtyBox;
    private JLabel minusBtn, qtyValue, plusBtn;
    private JButton addToCartBtn;

    // ---------- quantity tracking variable
    private int quantity = 1;

    ProductView() {

        setTitle("Shippidie - Product View");
        getContentPane().setPreferredSize(new Dimension(1280, 720));
        getContentPane().setBackground(bg);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        // ==========================================
        // HEADER INITIALIZATION
        // ==========================================
        header = new JPanel();
        header.setLayout(null);
        header.setBackground(gold);
        header.setBounds(0, 0, 1280, 80);

        title = new JLabel("Shippidie", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 36));
        title.setForeground(maroon);
        title.setBounds(540, 20, 200, 40);

        cartIcon = new JLabel("🛒");
        cartIcon.setFont(new Font("SansSerif", Font.PLAIN, 24));
        cartIcon.setForeground(white);
        cartIcon.setBounds(1120, 25, 30, 30);

        userIcon = new JLabel("👤");
        userIcon.setFont(new Font("SansSerif", Font.PLAIN, 24));
        userIcon.setForeground(white);
        userIcon.setBounds(1170, 25, 30, 30);

        gearIcon = new JLabel("⚙");
        gearIcon.setFont(new Font("SansSerif", Font.PLAIN, 24));
        gearIcon.setForeground(white);
        gearIcon.setBounds(1220, 25, 30, 30);

        header.add(title);
        header.add(cartIcon);
        header.add(userIcon);
        header.add(gearIcon);
        add(header);

        // ==========================================
        // MAIN CONTENT AREA
        // ==========================================
        mainContainer = new JPanel();
        mainContainer.setLayout(null);
        mainContainer.setBackground(bg);
        mainContainer.setBounds(0, 80, 1280, 640);

        // Back Button
        backButton = new JLabel("←");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 28));
        backButton.setForeground(Color.DARK_GRAY);
        backButton.setBounds(40, 30, 40, 30);
        mainContainer.add(backButton);

        // Large Main Image Placeholder
        mainImagePlace = new JPanel();
        mainImagePlace.setBackground(gray);
        mainImagePlace.setBounds(75, 90, 250, 250);
        mainContainer.add(mainImagePlace);

        // 3 Thumbnail Placeholders underneath
        int thumbX = 75;
        int thumbY = 360;
        for (int i = 0; i < 3; i++) {
            thumbImages[i] = new JPanel();
            thumbImages[i].setBackground(gray);
            thumbImages[i].setBounds(thumbX, thumbY, 70, 70);
            mainContainer.add(thumbImages[i]);
            thumbX += 90;
        }

        // Product Name & Price
        productName = new JLabel("PRODUCT NAME");
        productName.setFont(new Font("SansSerif", Font.BOLD, 24));
        productName.setForeground(Color.DARK_GRAY);
        productName.setBounds(370, 95, 300, 30);
        mainContainer.add(productName);

        productPrice = new JLabel("₱249");
        productPrice.setFont(new Font("SansSerif", Font.BOLD, 32));
        productPrice.setForeground(Color.DARK_GRAY);
        productPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        productPrice.setBounds(1040, 90, 160, 40);
        mainContainer.add(productPrice);

        // Rating
        productRating = new JLabel("☆☆☆☆☆ (0 stars)");
        productRating.setFont(new Font("SansSerif", Font.PLAIN, 12));
        productRating.setForeground(Color.GRAY);
        productRating.setBounds(370, 132, 200, 20);
        mainContainer.add(productRating);

        // Description text area (to be improvved)
        productDescription = new JTextArea(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce in mauris feugiat, malesuada lacus eget, " +
            "sodales justo. Duis venenatis magna eu est cursus, facilisis aliquet elit sodales. Maecenas vehicula enim id " +
            "felis sodales, in fringilla mauris convallis. Phasellus at mollis lorem, sit amet semper libero. Suspendisse " +
            "consequat dolor eu arcu bibendum lacinia. Suspendisse justo enim, auctor ac urna vel, vulputate tempus " +
            "dolor. Fusce accumsan nisi et nisi aliquet, vitae condimentum mauris lobortis. Donec arcu ante, tempus et " +
            "ultricies quis, luctus in nunc."
        );
        productDescription.setFont(new Font("SansSerif", Font.PLAIN, 12));
        productDescription.setForeground(Color.DARK_GRAY);
        productDescription.setBackground(bg);
        productDescription.setLineWrap(true);
        productDescription.setWrapStyleWord(true);
        productDescription.setEditable(false);
        productDescription.setBounds(370, 168, 830, 95);
        mainContainer.add(productDescription);

        // Separator Line
        JSeparator sep = new JSeparator();
        sep.setBounds(370, 280, 830, 2);
        mainContainer.add(sep);

        // Available in Section
        availableLabel = new JLabel("Available in:");
        availableLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        availableLabel.setForeground(Color.DARK_GRAY);
        availableLabel.setBounds(370, 305, 100, 20);
        mainContainer.add(availableLabel);

        colorBlackDot = createColorDot(Color.BLACK);
        colorBlackDot.setBounds(370, 335, 20, 20);
        mainContainer.add(colorBlackDot);

        colorBlackText = new JLabel("Black");
        colorBlackText.setFont(new Font("SansSerif", Font.PLAIN, 11));
        colorBlackText.setBounds(365, 358, 40, 15);
        mainContainer.add(colorBlackText);

        colorWhiteDot = createColorDot(Color.WHITE);
        colorWhiteDot.setBounds(420, 335, 20, 20);
        mainContainer.add(colorWhiteDot);

        colorWhiteText = new JLabel("White");
        colorWhiteText.setFont(new Font("SansSerif", Font.PLAIN, 11));
        colorWhiteText.setBounds(413, 358, 40, 15);
        mainContainer.add(colorWhiteText);

        // Quantity Section
        quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        quantityLabel.setForeground(Color.DARK_GRAY);
        quantityLabel.setBounds(960, 305, 70, 20);
        mainContainer.add(quantityLabel);

        qtyBox = new JPanel();
        qtyBox.setLayout(null);
        qtyBox.setBackground(white);
        qtyBox.setBorder(BorderFactory.createLineBorder(gray, 1));
        qtyBox.setBounds(960, 330, 110, 35);

        minusBtn = new JLabel("-", SwingConstants.CENTER);
        minusBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        minusBtn.setBounds(10, 5, 25, 25);
        minusBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        qtyBox.add(minusBtn);

        qtyValue = new JLabel("1", SwingConstants.CENTER);
        qtyValue.setFont(new Font("SansSerif", Font.BOLD, 14));
        qtyValue.setBounds(40, 5, 30, 25);
        qtyBox.add(qtyValue);

        plusBtn = new JLabel("+", SwingConstants.CENTER);
        plusBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        plusBtn.setBounds(75, 5, 25, 25);
        plusBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        qtyBox.add(plusBtn);

        mainContainer.add(qtyBox);

        // --- Quantity Logic ---
        minusBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (quantity > 1) {
                    quantity--;
                    qtyValue.setText(String.valueOf(quantity));
                }
            }
        });

        plusBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (quantity < 99) {
                    quantity++;
                    qtyValue.setText(String.valueOf(quantity));
                }
            }
        });

        // Add to Cart Button
        addToCartBtn = new JButton("Add to Cart");
        addToCartBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        addToCartBtn.setForeground(Color.DARK_GRAY);
        addToCartBtn.setBackground(cardBg);
        addToCartBtn.setFocusPainted(false);
        addToCartBtn.setBounds(500, 440, 280, 50);
        mainContainer.add(addToCartBtn);

        add(mainContainer);
    }

    // ==========================================
    // HELPER METHODS
    // ==========================================
    private JPanel createColorDot(Color c) {
        JPanel dot = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(c);
                g2.fill(new Ellipse2D.Double(0, 0, getWidth() - 1, getHeight() - 1));
                g2.setColor(gray);
                g2.draw(new Ellipse2D.Double(0, 0, getWidth() - 1, getHeight() - 1));
                g2.dispose();
            }
        };
        dot.setOpaque(false);
        return dot;
    }

}