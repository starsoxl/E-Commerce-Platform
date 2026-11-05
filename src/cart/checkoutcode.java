package cart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.LinkedList;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author xyrell
 */
public class checkoutcode extends JFrame implements ActionListener {

    // ---------- colors (same as the design)
    private Color gold = new Color(205, 171, 91);
    private Color maroon = new Color(123, 45, 45);
    private Color bg = new Color(255, 253, 247);
    private Color beige = new Color(240, 234, 220);
    private Color addressbeige = new Color(230, 224, 201);
    private Color yellow = new Color(233, 185, 83);
    private Color red = new Color(224, 60, 60);
    private Color pinkborder = new Color(238, 150, 150);
    private String peso = "₱";

    // ---------- connection to the cart window
    private cartcode cart;

    // ---------- header
    private JPanel header;
    private JButton back, profile, gear;
    private JLabel title;

    // ---------- delivery address
    private JPanel addresspanel;
    private JLabel pin, hdraddress, custname, phone, address, home;
    private JButton change;

    // ---------- product (left side)
    private JPanel productpanel, content;
    private JScrollPane scroll;
    private JLabel truck, ships;

    // ---------- data from the cart (LinkedList of the selected items)
    private LinkedList<cartcode.CartItem> items;
    private int discount;

    // ---------- payment method (right side)
    private JPanel paymentpanel, totalpanel, line;
    private JLabel cardicon, paymentlbl, cashlbl;
    private JButton changepay;
    private JLabel totalpricelbl, totalpriceval, voucherico, voucherlbl, voucherval, totalpaylbl, totalpayval;

    // ---------- place order
    private JButton placeorder;
    private String paymentMethod = "Cash on Delivery";

    checkoutcode(cartcode cart, LinkedList<cartcode.CartItem> items, int discount) {

        this.cart = cart;
        this.items = items;
        this.discount = discount;

        setTitle("Checkout");
        getContentPane().setPreferredSize(new Dimension(1280, 720));
        getContentPane().setBackground(bg);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        // ---------- header
        header = new JPanel();
        header.setLayout(null);
        header.setBackground(gold);
        header.setBounds(0, 0, 1280, 81);
        add(header);

        back = new JButton(new DrawIcon(1, 35, 30));
        flat(back);
        back.setBounds(27, 24, 35, 30);
        header.add(back);

        title = new JLabel("Back to Cart");
        title.setFont(new Font("Serif", Font.BOLD, 38));
        title.setForeground(maroon);
        title.setBounds(84, 0, 500, 81);
        header.add(title);

        profile = new JButton(new DrawIcon(2, 37, 37));
        flat(profile);
        profile.setBounds(1157, 21, 37, 37);
        header.add(profile);

        gear = new JButton(new DrawIcon(3, 37, 37));
        flat(gear);
        gear.setBounds(1216, 21, 37, 37);
        header.add(gear);

        // ---------- delivery address
        addresspanel = new RoundPanel(addressbeige, 10);
        addresspanel.setBounds(46, 101, 1190, 124);
        add(addresspanel);

        pin = new JLabel(new DrawIcon(9, 22, 26));
        pin.setBounds(18, 14, 22, 26);
        addresspanel.add(pin);

        hdraddress = new JLabel("Delivery Address");
        hdraddress.setFont(new Font("SansSerif", Font.PLAIN, 17));
        hdraddress.setBounds(48, 14, 250, 26);
        addresspanel.add(hdraddress);

        custname = new JLabel("Juan Dela Cruz");
        custname.setFont(new Font("SansSerif", Font.BOLD, 18));
        custname.setBounds(30, 56, 300, 24);
        addresspanel.add(custname);

        phone = new JLabel("+63 969 222 4444");
        phone.setFont(new Font("SansSerif", Font.PLAIN, 13));
        phone.setBounds(31, 87, 200, 20);
        addresspanel.add(phone);

        address = new JLabel("123 Rizal Street, Barangay San Jose, Biñan City, Laguna, 4024");
        address.setFont(new Font("SansSerif", Font.PLAIN, 17));
        address.setBounds(270, 58, 700, 24);
        addresspanel.add(address);

        home = new JLabel("Home", SwingConstants.CENTER);
        home.setFont(new Font("SansSerif", Font.PLAIN, 9));
        home.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        home.setBounds(915, 57, 57, 16);
        addresspanel.add(home);

        change = new JButton("Change");
        change.setContentAreaFilled(false);
        change.setBorderPainted(false);
        change.setFocusPainted(false);
        change.setOpaque(false);
        change.setFont(new Font("SansSerif", Font.PLAIN, 18));
        change.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        change.setUI(new RoundButtonUI(Color.WHITE, pinkborder));
        change.setBounds(1049, 47, 114, 52);
        addresspanel.add(change);

        // ---------- product panel (scroll pane, one row for every item from the cart)
        productpanel = new RoundPanel(beige, 10);
        productpanel.setBounds(61, 274, 547, 156);
        add(productpanel);

        content = new JPanel();
        content.setLayout(null);
        content.setOpaque(false);

        scroll = new JScrollPane(content, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.getVerticalScrollBar().setUI(new RedBarUI());
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        scroll.getVerticalScrollBar().setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        scroll.setBounds(0, 0, 547, 156);
        productpanel.add(scroll);

        // ---------- ships in 1-4 days (under the product panel, same left edge)
        truck = new JLabel(new DrawIcon(11, 30, 22));
        truck.setBounds(61, 452, 30, 22);
        add(truck);

        ships = new JLabel("Ships in 1-4 days");
        ships.setFont(new Font("SansSerif", Font.PLAIN, 13));
        ships.setBounds(99, 452, 250, 22);
        add(ships);

        // ---------- payment method
        paymentpanel = new RoundPanel(beige, 10);
        paymentpanel.setBounds(653, 274, 575, 58);
        add(paymentpanel);

        cardicon = new JLabel(new DrawIcon(10, 29, 21));
        cardicon.setBounds(17, 19, 29, 21);
        paymentpanel.add(cardicon);

        paymentlbl = new JLabel("Payment Method");
        paymentlbl.setFont(new Font("SansSerif", Font.BOLD, 18));
        paymentlbl.setBounds(55, 17, 220, 24);
        paymentpanel.add(paymentlbl);

        cashlbl = new JLabel("Cash on Delivery");
        cashlbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
        cashlbl.setBounds(324, 18, 170, 22);
        paymentpanel.add(cashlbl);

        changepay = new JButton("CHANGE");
        changepay.setFont(new Font("SansSerif", Font.PLAIN, 13));
        changepay.setForeground(red);
        changepay.setContentAreaFilled(false);
        changepay.setBorderPainted(false);
        changepay.setFocusPainted(false);
        changepay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changepay.setBounds(486, 15, 80, 28);
        paymentpanel.add(changepay);

        // ---------- totals
        totalpanel = new RoundPanel(beige, 10);
        totalpanel.setBounds(653, 351, 575, 223);
        add(totalpanel);

        totalpricelbl = new JLabel("Total Price");
        totalpricelbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
        totalpricelbl.setBounds(35, 28, 200, 22);
        totalpanel.add(totalpricelbl);

        totalpriceval = new JLabel(peso + "249", SwingConstants.CENTER);
        totalpriceval.setFont(new Font("SansSerif", Font.PLAIN, 14));
        totalpriceval.setBounds(450, 28, 100, 22);
        totalpanel.add(totalpriceval);

        voucherico = new JLabel(new DrawIcon(6, 33, 24));
        voucherico.setBounds(34, 74, 33, 24);
        totalpanel.add(voucherico);

        voucherlbl = new JLabel("Vouchers");
        voucherlbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
        voucherlbl.setBounds(84, 75, 150, 22);
        totalpanel.add(voucherlbl);

        voucherval = new JLabel(peso + "10", SwingConstants.CENTER);
        voucherval.setFont(new Font("SansSerif", Font.PLAIN, 14));
        voucherval.setBounds(450, 75, 100, 22);
        totalpanel.add(voucherval);

        line = new JPanel();
        line.setBackground(new Color(120, 120, 120));
        line.setBounds(35, 157, 504, 1);
        totalpanel.add(line);

        totalpaylbl = new JLabel("Total Payment");
        totalpaylbl.setFont(new Font("SansSerif", Font.BOLD, 18));
        totalpaylbl.setBounds(35, 176, 250, 28);
        totalpanel.add(totalpaylbl);

        totalpayval = new JLabel(peso + "239", SwingConstants.RIGHT);
        totalpayval.setFont(new Font("SansSerif", Font.BOLD, 31));
        totalpayval.setBounds(389, 167, 150, 46);
        totalpanel.add(totalpayval);

        // ---------- place order
        placeorder = new JButton("Place Order");
        placeorder.setFont(new Font("SansSerif", Font.BOLD, 20));
        placeorder.setForeground(new Color(35, 28, 10));
        placeorder.setContentAreaFilled(false);
        placeorder.setBorderPainted(false);
        placeorder.setFocusPainted(false);
        placeorder.setOpaque(false);
        placeorder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        placeorder.setUI(new RoundButtonUI(yellow, yellow));
        placeorder.setBounds(814, 609, 253, 62);
        add(placeorder);

        showOrder();
        showTotals();

        // ---------- action listener (connection to the cart: Back to Cart only)
        back.addActionListener(this);
        change.addActionListener(this);
        changepay.addActionListener(this);
        placeorder.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (cart != null) {
                    cart.setVisible(true);
                }
                dispose();
            }
        });

        pack();
        if (cart != null) {
            setLocation(cart.getLocation());
        } else {
            setLocationRelativeTo(null);
        }
    }

    // =====================================================================
    // ACTION LISTENER
    // =====================================================================
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == back) {
            returnToCart();
        } else if (e.getSource() == change) {
            changeAddress();
        } else if (e.getSource() == changepay) {
            changePaymentMethod();
        } else if (e.getSource() == placeorder) {
            placeOrder();
        }
    }

    void returnToCart() {
        if (cart != null) {
            cart.setVisible(true);
        }
        dispose();
    }

    void changeAddress() {
        JTextField nameField = new JTextField(custname.getText());
        JTextField phoneField = new JTextField(phone.getText());
        JTextField addressField = new JTextField(address.getText());

        JPanel form = new JPanel(new GridLayout(0, 1, 5, 5));
        form.add(new JLabel("Recipient name:"));
        form.add(nameField);
        form.add(new JLabel("Phone number:"));
        form.add(phoneField);
        form.add(new JLabel("Delivery address:"));
        form.add(addressField);

        int result = JOptionPane.showConfirmDialog(this, form,
                "Change Delivery Address", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            if (nameField.getText().trim().isEmpty()
                    || phoneField.getText().trim().isEmpty()
                    || addressField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please complete all delivery details.",
                        "Invalid Address", JOptionPane.WARNING_MESSAGE);
                return;
            }

            custname.setText(nameField.getText().trim());
            phone.setText(phoneField.getText().trim());
            address.setText(addressField.getText().trim());
        }
    }

    void changePaymentMethod() {
        String[] methods = {
            "Cash on Delivery",
            "GCash (demo selection)",
            "Debit/Credit Card (demo selection)"
        };

        Object selected = JOptionPane.showInputDialog(this,
                "Choose a payment method:", "Payment Method",
                JOptionPane.PLAIN_MESSAGE, null, methods, paymentMethod);

        if (selected != null) {
            paymentMethod = selected.toString();
            cashlbl.setText(paymentMethod);
        }
    }

    void placeOrder() {
        if (items == null || items.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "There are no items to place an order.",
                    "Checkout", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Confirm your order?\nTotal: " + totalpayval.getText()
                + "\nPayment: " + paymentMethod,
                "Confirm Order", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this,
                    "Your order has been placed successfully!\n"
                    + "Payment method: " + paymentMethod,
                    "Order Confirmed", JOptionPane.INFORMATION_MESSAGE);

            if (cart != null) {
                cart.completeCheckout(items);
                cart.setVisible(true);
            }
            dispose();
        }
    }

    // =====================================================================
    // shows every item that was selected in the cart
    // =====================================================================
    void showOrder() {
        content.removeAll();

        int k = 0;
        for (cartcode.CartItem it : items) {
            int dy = 100 * k;

            JLabel qty = new JLabel(it.qty + "x");
            qty.setFont(new Font("SansSerif", Font.PLAIN, 11));
            qty.setBounds(32, 62 + dy, 25, 20);
            content.add(qty);

            JPanel picture = new RoundPanel(Color.WHITE, 6);
            picture.setBounds(55, 32 + dy, 78, 79);
            content.add(picture);

            JLabel prodname = new JLabel(it.name);
            prodname.setFont(new Font("SansSerif", Font.PLAIN, 16));
            prodname.setBounds(149, 50 + dy, 250, 22);
            content.add(prodname);

            JLabel variation = new JLabel("Variation: " + it.variation);
            variation.setFont(new Font("SansSerif", Font.PLAIN, 13));
            variation.setForeground(new Color(90, 90, 90));
            variation.setBounds(149, 77 + dy, 250, 18);
            content.add(variation);

            JLabel prodprice = new JLabel(peso + String.format("%,d", it.total()));
            prodprice.setFont(new Font("SansSerif", Font.BOLD, 15));
            prodprice.setBounds(459, 65 + dy, 80, 24);
            content.add(prodprice);

            k++;
        }

        int h = Math.max(156, 100 * k + 32);
        content.setPreferredSize(new Dimension(539, h));
        content.revalidate();
        content.repaint();
    }

    // =====================================================================
    // Total Price, Vouchers and Total Payment
    // =====================================================================
    void showTotals() {
        int sub = 0;
        if (items != null) {
            for (cartcode.CartItem it : items) {
                sub += it.total();
            }
        }
        int save = sub * discount / 100;

        totalpriceval.setText(peso + String.format("%,d", sub));
        voucherval.setText(peso + String.format("%,d", save));
        totalpayval.setText(peso + String.format("%,d", sub - save));
    }

    // makes a button look flat (no border, no background)
    void flat(JButton b) {
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setOpaque(false);
        b.setMargin(new Insets(0, 0, 0, 0));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // =====================================================================
    // SMALL DRAWING CLASSES (rounded panel, rounded button, icons)
    // =====================================================================
    class RoundPanel extends JPanel {
        Color c;
        int arc;

        RoundPanel(Color c, int arc) {
            this.c = c;
            this.arc = arc;
            setLayout(null);
            setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
            g2.dispose();
        }
    }

    // paints a button with a filled rounded background and a 1px border
    class RoundButtonUI extends javax.swing.plaf.basic.BasicButtonUI {
        Color fill, border;

        RoundButtonUI(Color fill, Color border) {
            this.fill = fill;
            this.border = border;
        }

        public void paint(Graphics g, JComponent c) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            AbstractButton b = (AbstractButton) c;
            Color f = b.getModel().isPressed() ? fill.darker() : fill;
            g2.setColor(f);
            g2.fillRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 6, 6);
            g2.setColor(border);
            g2.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 6, 6);
            g2.dispose();
            super.paint(g, c);
        }
    }

    // type: 1 arrow, 2 profile, 3 gear, 6 voucher, 9 pin, 10 card, 11 truck
    class DrawIcon implements Icon {
        int type, w, h;

        DrawIcon(int type, int w, int h) {
            this.type = type;
            this.w = w;
            this.h = h;
        }

        public int getIconWidth() {
            return w;
        }

        public int getIconHeight() {
            return h;
        }

        public void paintIcon(Component c, Graphics g0, int x, int y) {
            Graphics2D g = (Graphics2D) g0.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g.translate(x, y);
            g.setStroke(new BasicStroke(1.7f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.setColor(Color.BLACK);

            if (type == 1) { // arrow
                g.setColor(Color.WHITE);
                g.setStroke(new BasicStroke(2.4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g.drawLine(2, h / 2, w - 2, h / 2);
                g.drawLine(2, h / 2, w / 2 - 1, 2);
                g.drawLine(2, h / 2, w / 2 - 1, h - 2);
            } else if (type == 2) { // profile
                g.setColor(Color.WHITE);
                g.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                Ellipse2D.Double circle = new Ellipse2D.Double(1.5, 1.5, w - 3, h - 3);
                g.draw(circle);
                double hr = w * 0.16;
                g.draw(new Ellipse2D.Double(w / 2.0 - hr, h * 0.22, 2 * hr, 2 * hr));
                Shape old = g.getClip();
                g.setClip(circle);
                g.draw(new Arc2D.Double(w * 0.2, h * 0.62, w * 0.6, h * 0.6, 0, 180, Arc2D.OPEN));
                g.setClip(old);
            } else if (type == 3) { // gear
                double cx = w / 2.0, cy = h / 2.0, R = Math.min(w, h) / 2.0 - 1.5, r = R * 0.78;
                Path2D.Double p = new Path2D.Double();
                double[] da = { -13, -8, 8, 13 };
                double[] rr = { r, R, R, r };
                for (int i = 0; i < 8; i++) {
                    double a = Math.toRadians(i * 45);
                    for (int k = 0; k < 4; k++) {
                        double ang = a + Math.toRadians(da[k]);
                        double px = cx + rr[k] * Math.cos(ang), py = cy + rr[k] * Math.sin(ang);
                        if (i == 0 && k == 0) {
                            p.moveTo(px, py);
                        } else {
                            p.lineTo(px, py);
                        }
                    }
                }
                p.closePath();
                g.setColor(Color.WHITE);
                g.setStroke(new BasicStroke(2.1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g.draw(p);
                double ir = R * 0.34;
                g.draw(new Ellipse2D.Double(cx - ir, cy - ir, 2 * ir, 2 * ir));
            } else if (type == 6) { // voucher
                g.setStroke(new BasicStroke(1.5f));
                g.drawRoundRect(1, 1, w - 2, h - 2, 6, 6);
                g.setStroke(new BasicStroke(1.3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f,
                        new float[] { 2f, 2f }, 0f));
                g.drawLine(w / 3, 4, w / 3, h - 4);
                g.setStroke(new BasicStroke(1f));
                g.setFont(new Font("SansSerif", Font.BOLD, 13));
                g.drawString("%", w / 3 + 5, h / 2 + 5);
            } else if (type == 9) { // location pin
                double cx = w / 2.0, cy = h * 0.40, r = w * 0.44;
                Path2D.Double p = new Path2D.Double();
                p.append(new Arc2D.Double(cx - r, cy - r, 2 * r, 2 * r, -40, 260, Arc2D.OPEN), false);
                p.lineTo(cx, h - 1.5);
                p.closePath();
                g.setStroke(new BasicStroke(1.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g.draw(p);
                double ir = r * 0.38;
                g.draw(new Ellipse2D.Double(cx - ir, cy - ir, 2 * ir, 2 * ir));
            } else if (type == 10) { // credit card
                g.setStroke(new BasicStroke(1.7f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g.drawRoundRect(1, 1, w - 3, h - 3, 5, 5);
                g.setStroke(new BasicStroke(2.6f));
                g.drawLine(2, 7, w - 3, 7);
                g.setStroke(new BasicStroke(1.7f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g.drawLine(5, h - 6, 11, h - 6);
            } else if (type == 11) { // delivery truck
                g.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g.drawRect(7, 2, 12, 13);
                Path2D.Double cab = new Path2D.Double();
                cab.moveTo(19, 6);
                cab.lineTo(25, 6);
                cab.lineTo(29, 11);
                cab.lineTo(29, 16);
                cab.lineTo(19, 16);
                g.draw(cab);
                g.drawLine(0, 6, 4, 6);
                g.drawLine(1, 10, 4, 10);
                g.setColor(bg);
                g.fillOval(8, 15, 7, 7);
                g.fillOval(21, 15, 7, 7);
                g.setColor(Color.BLACK);
                g.drawOval(8, 15, 7, 7);
                g.drawOval(21, 15, 7, 7);
            }
            g.dispose();
        }
    }

    class RedBarUI extends BasicScrollBarUI {
        protected JButton createDecreaseButton(int o) {
            return zero();
        }

        protected JButton createIncreaseButton(int o) {
            return zero();
        }

        JButton zero() {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(0, 0));
            return b;
        }

        protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
        }

        protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(242, 107, 107));
            g2.fillRoundRect(r.x, r.y, r.width, r.height, 2, 2);
            g2.dispose();
        }
    }
}
