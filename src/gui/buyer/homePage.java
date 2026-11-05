package homePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class homePage extends JFrame {

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

    // ---------- filter sidebar
    private JPanel sidebar;
    private JLabel filterTitle;
    private JSeparator sep1, sep2, sep3, sep4, sep5;

    // ---------- category filter
    private JLabel catTitle;
    private JCheckBox cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8;

    // ---------- price filter
    private JLabel priceTitle, dashLabel;
    private JTextField minField, maxField;

    // ---------- color filter
    private JLabel colorTitle;
    private ColorPickerButton c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;

    // ---------- stock filter
    private JLabel stockTitle;
    private ToggleSwitch stockToggle;

    // ---------- rating filter
    private JLabel ratingTitle;
    private JRadioButton rate1, rate2, rate3;
    private ButtonGroup rateGroup;

    // ---------- product area
    private JPanel productContainer, gridContent;
    private JScrollPane scroll;
    private JComboBox<String> sortDropdown;

    // ---------- product cards (Array)
    private JPanel[] productCards = new JPanel[6];

    homePage() {

        setTitle("Shippidie");
        getContentPane().setPreferredSize(new Dimension(1280, 720));
        getContentPane().setBackground(bg);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack(); 
        setLocationRelativeTo(null); 

        // ==========================================
        // FILTER SIDEBAR INITIALIZATION (Added first so header layers on top)
        // ==========================================

        sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBackground(white);
        sidebar.setBounds(0, 0, 300, 720); 

        filterTitle = new JLabel("Filters");
        filterTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        filterTitle.setBounds(20, 25, 100, 30);
        sidebar.add(filterTitle);

        sep1 = new JSeparator();
        sep1.setBounds(20, 65, 260, 2);
        sidebar.add(sep1);

        // --- Category ---
        catTitle = new JLabel("CATEGORY");
        catTitle.setFont(new Font("SansSerif", Font.BOLD, 12));
        catTitle.setForeground(Color.DARK_GRAY);
        catTitle.setBounds(20, 78, 100, 20);
        sidebar.add(catTitle);

        String[] catNames = {"Women's Apparel", "Men's Apparel", "Home Decor", "Lorem Ipsum", "Lorem Ipsum", "Lorem Ipsum", "Lorem Ipsum", "Lorem Ipsum"};
        int catY = 105;
        
        cat1 = createRoundCheckBox(catNames[0], catY); sidebar.add(cat1); catY += 22;
        cat2 = createRoundCheckBox(catNames[1], catY); sidebar.add(cat2); catY += 22;
        cat3 = createRoundCheckBox(catNames[2], catY); sidebar.add(cat3); catY += 22;
        cat4 = createRoundCheckBox(catNames[3], catY); sidebar.add(cat4); catY += 22;
        cat5 = createRoundCheckBox(catNames[4], catY); sidebar.add(cat5); catY += 22;
        cat6 = createRoundCheckBox(catNames[5], catY); sidebar.add(cat6); catY += 22;
        cat7 = createRoundCheckBox(catNames[6], catY); sidebar.add(cat7); catY += 22;
        cat8 = createRoundCheckBox(catNames[7], catY); sidebar.add(cat8);

        sep2 = new JSeparator();
        sep2.setBounds(20, 290, 260, 2);
        sidebar.add(sep2);

        // --- Price ---
        priceTitle = new JLabel("PRICE");
        priceTitle.setFont(new Font("SansSerif", Font.BOLD, 12));
        priceTitle.setForeground(Color.DARK_GRAY);
        priceTitle.setBounds(20, 305, 100, 20);
        sidebar.add(priceTitle);

        minField = new JTextField("0");
        minField.setHorizontalAlignment(JTextField.CENTER);
        minField.setBounds(40, 335, 70, 25);
        sidebar.add(minField);

        dashLabel = new JLabel("—", SwingConstants.CENTER);
        dashLabel.setBounds(110, 335, 40, 25);
        sidebar.add(dashLabel);

        maxField = new JTextField("50000");
        maxField.setHorizontalAlignment(JTextField.CENTER);
        maxField.setBounds(150, 335, 70, 25);
        sidebar.add(maxField);

        minField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int minVal = Integer.parseInt(minField.getText());
                    int maxVal = Integer.parseInt(maxField.getText());
                    if (minVal > maxVal) {
                        minVal = maxVal;
                        minField.setText(String.valueOf(minVal));
                    }
                    if (minVal < 0) {
                        minVal = 0;
                        minField.setText("0");
                    }
                } catch (NumberFormatException ex) {
                    minField.setText("0");
                }
            }
        });

        maxField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int maxVal = Integer.parseInt(maxField.getText());
                    int minVal = Integer.parseInt(minField.getText());
                    if (maxVal < minVal) {
                        maxVal = minVal;
                        maxField.setText(String.valueOf(maxVal));
                    }
                    if (maxVal > 50000) {
                        maxVal = 50000;
                        maxField.setText("50000");
                    }
                } catch (NumberFormatException ex) {
                    maxField.setText("50000");
                }
            }
        });

        sep3 = new JSeparator();
        sep3.setBounds(20, 375, 260, 2);
        sidebar.add(sep3);

        // --- Color ---
        colorTitle = new JLabel("COLOR");
        colorTitle.setFont(new Font("SansSerif", Font.BOLD, 12));
        colorTitle.setForeground(Color.DARK_GRAY);
        colorTitle.setBounds(20, 390, 100, 20);
        sidebar.add(colorTitle);

        c1 = new ColorPickerButton(Color.BLACK); c1.setBounds(20, 418, 25, 25); sidebar.add(c1);
        c2 = new ColorPickerButton(Color.WHITE); c2.setBounds(45, 418, 25, 25); sidebar.add(c2);
        c3 = new ColorPickerButton(Color.RED); c3.setBounds(70, 418, 25, 25); sidebar.add(c3);
        c4 = new ColorPickerButton(Color.ORANGE); c4.setBounds(95, 418, 25, 25); sidebar.add(c4);
        c5 = new ColorPickerButton(Color.YELLOW); c5.setBounds(120, 418, 25, 25); sidebar.add(c5);
        c6 = new ColorPickerButton(Color.GREEN); c6.setBounds(145, 418, 25, 25); sidebar.add(c6);
        c7 = new ColorPickerButton(new Color(0, 153, 204)); c7.setBounds(170, 418, 25, 25); sidebar.add(c7);
        c8 = new ColorPickerButton(Color.decode("#a855f7")); c8.setBounds(195, 418, 25, 25); sidebar.add(c8); 
        c9 = new ColorPickerButton(Color.decode("#f472b6")); c9.setBounds(220, 418, 25, 25); sidebar.add(c9); 
        c10 = new ColorPickerButton("rainbow.png"); c10.setBounds(245, 418, 25, 25); sidebar.add(c10);

        sep4 = new JSeparator();
        sep4.setBounds(20, 460, 260, 2);
        sidebar.add(sep4);

        // --- In Stock ---
        stockTitle = new JLabel("IN STOCK ONLY");
        stockTitle.setFont(new Font("SansSerif", Font.BOLD, 12));
        stockTitle.setForeground(Color.DARK_GRAY);
        stockTitle.setBounds(20, 475, 150, 20);
        sidebar.add(stockTitle);

        stockToggle = new ToggleSwitch();
        stockToggle.setBounds(230, 475, 40, 20);
        sidebar.add(stockToggle);

        sep5 = new JSeparator();
        sep5.setBounds(20, 510, 260, 2);
        sidebar.add(sep5);

        // --- Rating ---
        ratingTitle = new JLabel("RATING");
        ratingTitle.setFont(new Font("SansSerif", Font.BOLD, 12));
        ratingTitle.setForeground(Color.DARK_GRAY);
        ratingTitle.setBounds(20, 525, 100, 20);
        sidebar.add(ratingTitle);

        rateGroup = new ButtonGroup();
        rate1 = createRadio("Any rating", 555); rateGroup.add(rate1); sidebar.add(rate1);
        rate2 = createRadio("★★★★☆ 4 stars & up", 580); rateGroup.add(rate2); sidebar.add(rate2);
        rate3 = createRadio("★★★☆☆ 3 stars & up", 605); rateGroup.add(rate3); sidebar.add(rate3);

        add(sidebar);

        // ==========================================
        // HEADER INITIALIZATION (Added after sidebar so it layers on top)
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
        // PRODUCT AREA INITIALIZATION
        // ==========================================
        productContainer = new JPanel();
        productContainer.setLayout(null);
        productContainer.setBackground(bg);
        productContainer.setBounds(300, 80, 980, 640);

        String[] sortOptions = {
            "Featured", 
            "Name: A to Z", 
            "Name: Z to A", 
            "Price: Low to High", 
            "Price: High to Low", 
            "Rating: High to Low", 
            "Rating: Low to High"
        };
        sortDropdown = new JComboBox<>(sortOptions);
        sortDropdown.setBackground(white);
        sortDropdown.setBounds(20, 15, 150, 30);
        productContainer.add(sortDropdown);

        gridContent = new JPanel();
        gridContent.setLayout(null);
        gridContent.setBackground(bg);
        gridContent.setPreferredSize(new Dimension(960, 850)); 

        int cardX = 40;
        int cardY = 20;
        
        for (int i = 0; i < 6; i++) {
            productCards[i] = buildProductCard();
            productCards[i].setBounds(cardX, cardY, 250, 350);
            gridContent.add(productCards[i]);

            cardX += 290;
            if (cardX > 620) {
                cardX = 40;
                cardY += 390;
            }
        }

        scroll = new JScrollPane(gridContent);
        scroll.setBounds(0, 60, 980, 580);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        productContainer.add(scroll);

        add(productContainer);
    }

    // ==========================================
    // HELPER METHODS
    // ==========================================
    private JCheckBox createRoundCheckBox(String text, int yPos) {
        JCheckBox cb = new JCheckBox(text);
        cb.setBackground(white);
        cb.setOpaque(false);
        cb.setFocusPainted(false);
        cb.setBounds(20, yPos, 200, 20);
        cb.setForeground(Color.DARK_GRAY);
        cb.setFont(new Font("SansSerif", Font.PLAIN, 12));

        Icon circleIcon = new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int size = 14;
                g2.setColor(white);
                g2.fillOval(x, y, size, size);
                g2.setColor(gray);
                g2.drawOval(x, y, size, size);
                if (c instanceof AbstractButton && ((AbstractButton) c).isSelected()) {
                    g2.setColor(maroon);
                    g2.fillOval(x + 3, y + 3, size - 6, size - 6);
                }
                g2.dispose();
            }
            @Override
            public int getIconWidth() { return 14; }
            @Override
            public int getIconHeight() { return 14; }
        };

        cb.setIcon(circleIcon);
        cb.setSelectedIcon(circleIcon);
        cb.setRolloverIcon(circleIcon);
        return cb;
    }

    private JRadioButton createRadio(String text, int yPos) {
        JRadioButton rb = new JRadioButton(text);
        rb.setBackground(white);
        rb.setBounds(20, yPos, 200, 20);
        return rb;
    }

    private JPanel buildProductCard() {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(cardBg);
        card.setBorder(BorderFactory.createLineBorder(gray, 1));

        JPanel imgPlace = new JPanel();
        imgPlace.setBackground(white);
        imgPlace.setBounds(10, 10, 230, 230);
        card.add(imgPlace);

        JLabel pName = new JLabel("PRODUCT NAME");
        pName.setFont(new Font("SansSerif", Font.BOLD, 12));
        pName.setBounds(15, 250, 150, 20);
        card.add(pName);

        JLabel pPrice = new JLabel("₱249");
        pPrice.setFont(new Font("SansSerif", Font.BOLD, 12));
        pPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        pPrice.setBounds(180, 250, 55, 20);
        card.add(pPrice);

        JLabel pColors = new JLabel("⚫ ⚪");
        pColors.setBounds(15, 275, 100, 20);
        card.add(pColors);

        JLabel pRating = new JLabel("☆☆☆☆☆ (0 stars)");
        pRating.setFont(new Font("SansSerif", Font.PLAIN, 11));
        pRating.setBounds(15, 300, 150, 20);
        card.add(pRating);

        return card;
    }

    // ==========================================
    // CUSTOM UI COMPONENT CLASSES
    // ==========================================

    private class ColorPickerButton extends JCheckBox {
        private Color buttonColor;
        private Image img;

        public ColorPickerButton(Color color) {
            this.buttonColor = color;
            init();
        }

        public ColorPickerButton(String imagePath) {
            try {
                java.net.URL imgURL = getClass().getResource(imagePath);
                if (imgURL != null) {
                    img = new ImageIcon(imgURL).getImage();
                } else {
                    System.err.println("Couldn't find file: " + imagePath);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            init();
        }

        private void init() {
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setPreferredSize(new Dimension(30, 30));
            setIcon(new javax.swing.Icon() {
                public void paintIcon(Component c, Graphics g, int x, int y) {}
                public int getIconWidth() { return 30; }
                public int getIconHeight() { return 30; }
            });
            setSelectedIcon(getIcon());
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int diam = 16;
            int x = (getWidth() - diam) / 2;
            int y = (getHeight() - diam) / 2;

            if (img != null) {
                g2.setClip(new Ellipse2D.Double(x, y, diam, diam));
                g2.drawImage(img, x, y, diam, diam, this);
                g2.setClip(null);
            } else {
                g2.setColor(buttonColor);
                g2.fill(new Ellipse2D.Double(x, y, diam, diam));
            }
            
            g2.setColor(gray);
            g2.draw(new Ellipse2D.Double(x, y, diam, diam));

            if (isSelected()) {
                g2.setColor(Color.BLUE);
                g2.setStroke(new BasicStroke(2));
                g2.draw(new Ellipse2D.Double(x - 2, y - 2, diam + 4, diam + 4));
            }
            g2.dispose();
        }
    }

    private class ToggleSwitch extends JPanel {
        private boolean isOn = false;

        public ToggleSwitch() {
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    isOn = !isOn;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            if (isOn) {
                g2.setColor(new Color(76, 217, 100));
            } else {
                g2.setColor(gray);
            }
            g2.fill(new RoundRectangle2D.Double(0, 0, width, height, height, height));

            int knobDiam = height - 4;
            int knobX = isOn ? width - knobDiam - 2 : 2;
            int knobY = 2;
            
            g2.setColor(white);
            g2.fill(new Ellipse2D.Double(knobX, knobY, knobDiam, knobDiam));

            g2.dispose();
        }
    }
}