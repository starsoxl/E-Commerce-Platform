package cart;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author xyrell
 */
public class cartcode extends JFrame implements ActionListener {

    // ---------- colors (same as the design)
    private Color gold = new Color(205, 171, 91);
    private Color maroon = new Color(123, 45, 45);
    private Color bg = new Color(255, 253, 247);
    private Color beige = new Color(240, 234, 220);
    private Color yellow = new Color(233, 185, 83);
    private Color redbar = new Color(242, 107, 107);
    private Color delred = new Color(224, 60, 60);
    private Color linegray = new Color(214, 209, 198);
    private String peso = "₱";

    // ---------- header
    private JPanel header, colbar, listpanel, content, bottombar;
    private JButton back, profile, gear;
    private JLabel title;

    // ---------- column header
    private JLabel colproduct, colunit, colqty, coltotal;

    // ---------- list
    private JScrollPane scroll;
    private JScrollBar scrollbar;

    // ---------- bottom bar
    private JCheckBox selectall;
    private JButton delete, vouchers, checkout;
    private JLabel totalcount, totalamount, saved, savedamount;

    // ---------- data (LinkedList)
    private LinkedList<Seller> sellers = new LinkedList<Seller>();

    // ---------- components of each row (LinkedList, same order as the items)
    private LinkedList<CartItem> allitems = new LinkedList<CartItem>();
    private LinkedList<JCheckBox> checks = new LinkedList<JCheckBox>();
    private LinkedList<JButton> variations = new LinkedList<JButton>();
    private LinkedList<JButton> minuses = new LinkedList<JButton>();
    private LinkedList<JButton> pluses = new LinkedList<JButton>();
    private LinkedList<JButton> trashes = new LinkedList<JButton>();
    private LinkedList<JLabel> varlabels = new LinkedList<JLabel>();
    private LinkedList<JLabel> qtylabels = new LinkedList<JLabel>();
    private LinkedList<JLabel> totallabels = new LinkedList<JLabel>();

    private int discount = 0;
    private boolean untouched = false;

    cartcode() {

        setTitle("Cart");
        getContentPane().setPreferredSize(new Dimension(1280, 720));
        getContentPane().setBackground(bg);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // ---------- sample data
        Seller s1 = new Seller("Seller");
        s1.items.add(new CartItem("PRODUCT NAME", "Black", 249));
        s1.items.add(new CartItem("PRODUCT NAME", "Black", 249));
        Seller s2 = new Seller("Seller");
        s2.items.add(new CartItem("PRODUCT NAME", "Black", 249));
        s2.items.add(new CartItem("PRODUCT NAME", "Black", 249));
        sellers.add(s1);
        sellers.add(s2);

        // ---------- bottom bar (added first so it is drawn on top of the list)
        bottombar = new RoundPanel(bg, 8, true);
        bottombar.setBounds(47, 608, 1185, 102);
        add(bottombar);

        selectall = new JCheckBox("Select All");
        selectall.setIcon(new BoxIcon(false));
        selectall.setSelectedIcon(new BoxIcon(true));
        selectall.setFont(new Font("SansSerif", Font.PLAIN, 16));
        selectall.setIconTextGap(10);
        selectall.setOpaque(false);
        selectall.setFocusPainted(false);
        selectall.setBorder(null);
        selectall.setMargin(new Insets(0, 0, 0, 0));
        selectall.setBounds(26, 40, 130, 24);
        bottombar.add(selectall);

        delete = new JButton("Delete");
        flat(delete);
        delete.setFont(new Font("SansSerif", Font.PLAIN, 16));
        delete.setForeground(delred);
        delete.setHorizontalAlignment(SwingConstants.LEFT);
        delete.setBounds(164, 39, 65, 24);
        bottombar.add(delete);

        vouchers = new JButton("Vouchers", new DrawIcon(6, 34, 24));
        flat(vouchers);
        vouchers.setFont(new Font("SansSerif", Font.PLAIN, 17));
        vouchers.setIconTextGap(10);
        vouchers.setHorizontalAlignment(SwingConstants.LEFT);
        vouchers.setBounds(338, 31, 130, 39);
        bottombar.add(vouchers);

        totalcount = new JLabel("Total (1):");
        totalcount.setFont(new Font("SansSerif", Font.PLAIN, 14));
        totalcount.setBounds(739, 31, 91, 21);
        bottombar.add(totalcount);

        totalamount = new JLabel(peso + "249");
        totalamount.setFont(new Font("SansSerif", Font.BOLD, 31));
        totalamount.setBounds(821, 18, 150, 47);
        bottombar.add(totalamount);

        saved = new JLabel("Saved");
        saved.setFont(new Font("SansSerif", Font.BOLD, 12));
        saved.setBounds(803, 65, 52, 18);
        bottombar.add(saved);

        savedamount = new JLabel(peso + "249");
        savedamount.setFont(new Font("SansSerif", Font.PLAIN, 13));
        savedamount.setBounds(872, 65, 91, 18);
        bottombar.add(savedamount);

        checkout = new JButton("Check Out");
        flat(checkout);
        checkout.setOpaque(true);
        checkout.setContentAreaFilled(true);
        checkout.setBackground(yellow);
        checkout.setForeground(new Color(35, 28, 10));
        checkout.setFont(new Font("SansSerif", Font.PLAIN, 18));
        checkout.setBounds(985, 27, 178, 46);
        bottombar.add(checkout);

        // ---------- list
        listpanel = new RoundPanel(beige, 10, false);
        listpanel.setBounds(51, 181, 1177, 524);
        add(listpanel);

        content = new JPanel();
        content.setLayout(null);
        content.setOpaque(false);

        scroll = new JScrollPane(content, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setWheelScrollingEnabled(false);
        scroll.setBounds(0, 0, 1177, 524);
        listpanel.add(scroll);

        // red scroll bar on the right
        scrollbar = new JScrollBar(JScrollBar.VERTICAL, 0, 171, 0, 340);
        scrollbar.setUI(new RedBarUI());
        scrollbar.setOpaque(false);
        scrollbar.setBounds(1169, 79, 8, 445);
        listpanel.add(scrollbar, 0);

        scrollbar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int max = scrollbar.getMaximum() - scrollbar.getVisibleAmount();
                int contentheight = content.getPreferredSize().height;
                int viewheight = scroll.getViewport().getHeight();
                int offset = (int) ((scrollbar.getValue() / (double) max) * Math.max(0, contentheight - viewheight));
                scroll.getViewport().setViewPosition(new Point(0, offset));
            }
        });
        MouseWheelListener wheel = new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                scrollbar.setValue(scrollbar.getValue() + e.getWheelRotation() * 12);
            }
        };
        scroll.addMouseWheelListener(wheel);
        scrollbar.addMouseWheelListener(wheel);

        // ---------- column header
        colbar = new RoundPanel(beige, 10, false);
        colbar.setBounds(51, 103, 1177, 56);
        add(colbar);

        colproduct = new JLabel("Product");
        colproduct.setFont(new Font("SansSerif", Font.PLAIN, 14));
        colproduct.setBounds(78, 16, 131, 21);
        colbar.add(colproduct);

        colunit = new JLabel("Unit Price", SwingConstants.CENTER);
        colunit.setFont(new Font("SansSerif", Font.PLAIN, 14));
        colunit.setBounds(562, 16, 131, 21);
        colbar.add(colunit);

        colqty = new JLabel("Quantity", SwingConstants.CENTER);
        colqty.setFont(new Font("SansSerif", Font.PLAIN, 14));
        colqty.setBounds(741, 16, 131, 21);
        colbar.add(colqty);

        coltotal = new JLabel("Total Price", SwingConstants.CENTER);
        coltotal.setFont(new Font("SansSerif", Font.PLAIN, 14));
        coltotal.setBounds(922, 16, 131, 21);
        colbar.add(coltotal);

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

        title = new JLabel("Cart (0)");
        title.setFont(new Font("Serif", Font.BOLD, 38));
        title.setForeground(maroon);
        title.setBounds(93, 0, 392, 81);
        header.add(title);

        profile = new JButton(new DrawIcon(2, 37, 37));
        flat(profile);
        profile.setBounds(1157, 21, 37, 37);
        header.add(profile);

        gear = new JButton(new DrawIcon(3, 37, 37));
        flat(gear);
        gear.setBounds(1216, 21, 37, 37);
        header.add(gear);

        // ---------- action listeners
        back.addActionListener(this);
        profile.addActionListener(this);
        gear.addActionListener(this);
        selectall.addActionListener(this);
        delete.addActionListener(this);
        vouchers.addActionListener(this);
        checkout.addActionListener(this);

        showItems();
        updateTotals();

        pack();
        setLocationRelativeTo(null);
    }

    // =====================================================================
    // builds every seller and product row from the LinkedLists
    // =====================================================================
    void showItems() {
        content.removeAll();
        allitems.clear();
        checks.clear();
        variations.clear();
        minuses.clear();
        pluses.clear();
        trashes.clear();
        varlabels.clear();
        qtylabels.clear();
        totallabels.clear();

        int y0 = 0;
        for (Seller s : sellers) {

            JLabel store = new JLabel(new DrawIcon(4, 29, 26));
            store.setBounds(51, y0 + 26, 29, 26);
            content.add(store);

            JLabel sname = new JLabel(s.name);
            sname.setFont(new Font("SansSerif", Font.BOLD, 21));
            sname.setBounds(90, y0 + 24, 196, 31);
            content.add(sname);

            JPanel line = new JPanel();
            line.setBackground(linegray);
            line.setBounds(48, y0 + 77, 1041, 1);
            content.add(line);

            int k = 0;
            for (CartItem it : s.items) {
                int cy = y0 + 140 + 102 * k;

                JCheckBox cb = new JCheckBox();
                cb.setIcon(new BoxIcon(false));
                cb.setSelectedIcon(new BoxIcon(true));
                cb.setSelected(it.selected);
                cb.setOpaque(false);
                cb.setFocusPainted(false);
                cb.setBorder(null);
                cb.setMargin(new Insets(0, 0, 0, 0));
                cb.setBounds(29, cy - 9, 17, 17);
                cb.addActionListener(this);
                content.add(cb);

                JPanel img = new RoundPanel(Color.WHITE, 8, false);
                img.setBounds(67, cy - 38, 77, 77);
                content.add(img);

                JLabel name = new JLabel(it.name);
                name.setFont(new Font("SansSerif", Font.PLAIN, 16));
                name.setBounds(157, cy - 39, 235, 26);
                content.add(name);

                JButton var = new JButton("Variation ▼");
                flat(var);
                var.setFont(new Font("SansSerif", Font.PLAIN, 14));
                var.setForeground(new Color(60, 60, 60));
                var.setBounds(400, cy - 26, 84, 21);
                var.addActionListener(this);
                content.add(var);

                JLabel varlbl = new JLabel(it.variation, SwingConstants.CENTER);
                varlbl.setFont(new Font("SansSerif", Font.PLAIN, 13));
                varlbl.setForeground(new Color(125, 125, 125));
                varlbl.setBounds(393, cy - 8, 78, 18);
                content.add(varlbl);

                JLabel unit = new JLabel(peso + it.price, SwingConstants.CENTER);
                unit.setFont(new Font("SansSerif", Font.PLAIN, 16));
                unit.setBounds(575, cy - 12, 104, 24);
                content.add(unit);

                // quantity box  [ - | 1 | + ]
                JPanel qp = new QtyPanel();
                qp.setBounds(743, cy - 14, 124, 30);
                content.add(qp);

                JButton minus = new JButton(new DrawIcon(7, 34, 30));
                flat(minus);
                minus.setBounds(0, 0, 34, 30);
                minus.addActionListener(this);
                qp.add(minus);

                JLabel qty = new JLabel("" + it.qty, SwingConstants.CENTER);
                qty.setFont(new Font("SansSerif", Font.PLAIN, 16));
                qty.setBounds(34, 0, 55, 30);
                qp.add(qty);

                JButton plus = new JButton(new DrawIcon(8, 34, 30));
                flat(plus);
                plus.setBounds(90, 0, 34, 30);
                plus.addActionListener(this);
                qp.add(plus);

                JLabel tot = new JLabel(peso + it.total(), SwingConstants.CENTER);
                tot.setFont(new Font("SansSerif", Font.PLAIN, 16));
                tot.setBounds(935, cy - 12, 104, 24);
                content.add(tot);

                JButton trash = new JButton(new DrawIcon(5, 21, 24));
                flat(trash);
                trash.setBounds(1078, cy - 12, 21, 24);
                trash.addActionListener(this);
                content.add(trash);

                // remember the components, same order as the item
                allitems.add(it);
                checks.add(cb);
                variations.add(var);
                varlabels.add(varlbl);
                minuses.add(minus);
                pluses.add(plus);
                qtylabels.add(qty);
                totallabels.add(tot);
                trashes.add(trash);
                k++;
            }
            y0 += 94 + 102 * s.items.size();
        }

        int ch = y0 + 118;
        content.setPreferredSize(new Dimension(1177, ch));
        content.revalidate();
        content.repaint();
        int maxy = Math.max(0, ch - 524);
        scroll.getViewport().setViewPosition(new Point(0, Math.min(scroll.getViewport().getViewPosition().y, maxy)));
    }

    // =====================================================================
    // ACTION LISTENER  (dito dumadaan ang lahat ng click, tapos ipapasa sa method nila)
    // =====================================================================
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        // ----- header
        if (src == back) {
            backClicked();
            return;
        }
        if (src == profile) {
            profileClicked();
            return;
        }
        if (src == gear) {
            gearClicked();
            return;
        }

        // ----- bottom bar
        if (src == selectall) {
            selectAllClicked();
            return;
        }
        if (src == delete) {
            deleteClicked();
            return;
        }
        if (src == vouchers) {
            vouchersClicked();
            return;
        }
        if (src == checkout) {
            checkoutClicked();
            return;
        }

        // ----- inside the product rows (scroll pane)
        int i = checks.indexOf(src);
        if (i != -1) {
            checkboxClicked(i);
            return;
        }
        i = variations.indexOf(src);
        if (i != -1) {
            variationClicked(i);
            return;
        }
        i = minuses.indexOf(src);
        if (i != -1) {
            minusClicked(i);
            return;
        }
        i = pluses.indexOf(src);
        if (i != -1) {
            plusClicked(i);
            return;
        }
        i = trashes.indexOf(src);
        if (i != -1) {
            trashClicked(i);
            return;
        }
    }

    // =====================================================================
    // HEADER ACTIONS
    // =====================================================================
    void backClicked() {
        int r = JOptionPane.showConfirmDialog(this, "Go back and close the cart?", "Back",
                JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    void profileClicked() {
        JOptionPane.showMessageDialog(this, "Profile", "Account", JOptionPane.INFORMATION_MESSAGE);
    }

    void gearClicked() {
        JOptionPane.showMessageDialog(this, "Settings", "Settings", JOptionPane.INFORMATION_MESSAGE);
    }

    // =====================================================================
    // BOTTOM BAR ACTIONS
    // =====================================================================
    void selectAllClicked() {
        for (CartItem it : allitems) {
            it.selected = selectall.isSelected();
        }
        untouched = false;
        showItems();
        updateTotals();
    }

    void deleteClicked() {
        untouched = false;
        int removed = 0;
        Iterator<Seller> si = sellers.iterator();
        while (si.hasNext()) {
            Seller s = si.next();
            Iterator<CartItem> ii = s.items.iterator();
            while (ii.hasNext()) {
                if (ii.next().selected) {
                    ii.remove();
                    removed++;
                }
            }
            if (s.items.isEmpty()) {
                si.remove();
            }
        }
        if (removed == 0) {
            JOptionPane.showMessageDialog(this, "No items selected.", "Delete", JOptionPane.INFORMATION_MESSAGE);
        }
        showItems();
        updateTotals();
    }

    void vouchersClicked() {
        String[] opt = { "10% OFF  (SAVE10)", "5% OFF  (WELCOME5)", "Remove voucher" };
        int c = JOptionPane.showOptionDialog(this, "Choose a voucher", "Vouchers", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, opt, opt[0]);
        if (c < 0) {
            return;
        }
        if (c == 0) {
            discount = 10;
        } else if (c == 1) {
            discount = 5;
        } else {
            discount = 0;
        }
        untouched = false;
        updateTotals();
    }

    void checkoutClicked() {
        // Copy the selected item references into a LinkedList for checkout.
        LinkedList<CartItem> picked = new LinkedList<CartItem>();

        for (CartItem it : allitems) {
            if (it.selected) {
                picked.add(it);
            }
        }

        if (picked.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select at least one item to check out.",
                    "Check Out", JOptionPane.WARNING_MESSAGE);
            return;
        }

        checkoutcode co = new checkoutcode(this, picked, discount);
        co.setVisible(true);
        setVisible(false);
    }

    // Called by Checkout only after the customer confirms the order.
    void completeCheckout(LinkedList<CartItem> purchasedItems) {
        Iterator<Seller> sellerIterator = sellers.iterator();

        while (sellerIterator.hasNext()) {
            Seller seller = sellerIterator.next();
            Iterator<CartItem> itemIterator = seller.items.iterator();

            while (itemIterator.hasNext()) {
                CartItem current = itemIterator.next();

                if (purchasedItems.contains(current)) {
                    itemIterator.remove();
                }
            }

            if (seller.items.isEmpty()) {
                sellerIterator.remove();
            }
        }

        discount = 0;
        untouched = false;
        showItems();
        updateTotals();
    }

    // =====================================================================
    // PRODUCT ROW ACTIONS (i = position of the item in the LinkedList)
    // =====================================================================
    void checkboxClicked(int i) {
        allitems.get(i).selected = checks.get(i).isSelected();
        untouched = false;
        updateTotals();
    }

    void variationClicked(int i) {
        String[] choices = { "Black", "White", "Navy Blue", "Red" };
        Object pick = JOptionPane.showInputDialog(this, "Choose variation", "Variation",
                JOptionPane.PLAIN_MESSAGE, null, choices, allitems.get(i).variation);
        if (pick != null) {
            allitems.get(i).variation = pick.toString();
            varlabels.get(i).setText(pick.toString());
            untouched = false;
        }
    }

    void minusClicked(int i) {
        CartItem it = allitems.get(i);
        if (it.qty > 1) {
            it.qty--;
        }
        qtylabels.get(i).setText("" + it.qty);
        totallabels.get(i).setText(peso + it.total());
        untouched = false;
        updateTotals();
    }

    void plusClicked(int i) {
        CartItem it = allitems.get(i);
        it.qty++;
        qtylabels.get(i).setText("" + it.qty);
        totallabels.get(i).setText(peso + it.total());
        untouched = false;
        updateTotals();
    }

    void trashClicked(int i) {
        final CartItem target = allitems.get(i);
        // delayed a little so the row is not rebuilt while its own button is still being clicked
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                deleteItem(target);
            }
        });
    }

    void deleteItem(CartItem target) {
        untouched = false;
        Iterator<Seller> si = sellers.iterator();
        while (si.hasNext()) {
            Seller s = si.next();
            if (s.items.remove(target)) {
                if (s.items.isEmpty()) {
                    si.remove();
                }
                break;
            }
        }
        showItems();
        updateTotals();
    }

    // =====================================================================
    // TOTALS (Cart count, Total, Saved, Select All)
    // =====================================================================
    void updateTotals() {
        int count = allitems.size();
        int n = 0;
        int sub = 0;
        boolean all = count > 0;

        for (CartItem it : allitems) {
            if (it.selected) {
                n++;
                sub += it.total();
            } else {
                all = false;
            }
        }
        int save = sub * discount / 100;
        title.setText("Cart (" + count + ")");
        totalcount.setText("Total (" + n + "):");
        totalamount.setText(peso + String.format("%,d", sub - save));
        savedamount.setText(peso + String.format("%,d", save));
        selectall.setSelected(all && count > 0);
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
    // DATA CLASSES
    // =====================================================================
    static class CartItem {
        String name, variation;
        int price, qty = 1;
        boolean selected;

        CartItem(String n, String v, int p) {
            name = n;
            variation = v;
            price = p;
        }

        int total() {
            return price * qty;
        }
    }

    static class Seller {
        String name;
        LinkedList<CartItem> items = new LinkedList<CartItem>();

        Seller(String n) {
            name = n;
        }
    }

    // =====================================================================
    // SMALL DRAWING CLASSES (rounded panels, check box, icons, scroll bar)
    // =====================================================================
    class RoundPanel extends JPanel {
        Color c;
        int arc;
        boolean shadow;

        RoundPanel(Color c, int arc, boolean shadow) {
            this.c = c;
            this.arc = arc;
            this.shadow = shadow;
            setLayout(null);
            setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (shadow) {
                for (int i = 5; i >= 1; i--) {
                    g2.setColor(new Color(0, 0, 0, 9));
                    g2.fillRoundRect(5 - i, 5 - i + 3, getWidth() - 10 + 2 * i, getHeight() - 10 + 2 * i, arc + i, arc + i);
                }
                g2.setColor(c);
                g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, arc, arc);
            } else {
                g2.setColor(c);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
            }
            g2.dispose();
        }
    }

    class QtyPanel extends JPanel {
        QtyPanel() {
            setLayout(null);
            setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
            g2.setColor(new Color(228, 224, 214));
            g2.drawLine(34, 0, 34, getHeight());
            g2.drawLine(89, 0, 89, getHeight());
            g2.dispose();
        }
    }

    class BoxIcon implements Icon {
        boolean on;

        BoxIcon(boolean on) {
            this.on = on;
        }

        public int getIconWidth() {
            return 17;
        }

        public int getIconHeight() {
            return 17;
        }

        public void paintIcon(Component c, Graphics g0, int x, int y) {
            Graphics2D g = (Graphics2D) g0.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.WHITE);
            g.fillRoundRect(x, y, 16, 16, 3, 3);
            g.setColor(new Color(120, 120, 120));
            g.drawRoundRect(x, y, 16, 16, 3, 3);
            if (on) {
                g.setColor(new Color(40, 40, 40));
                g.setStroke(new BasicStroke(1.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g.drawLine(x + 3, y + 9, x + 7, y + 13);
                g.drawLine(x + 7, y + 13, x + 13, y + 3);
            }
            g.dispose();
        }
    }

    // type: 1 arrow, 2 profile, 3 gear, 4 store, 5 trash, 6 voucher, 7 minus, 8 plus
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
            } else if (type == 4) { // store
                int ab = (int) (h * 0.40);
                g.drawRoundRect(1, 1, w - 3, ab, 4, 4);
                g.drawLine(w / 3, 1, w / 3, ab + 1);
                g.drawLine(2 * w / 3, 1, 2 * w / 3, ab + 1);
                g.drawLine(3, ab + 1, 3, h - 2);
                g.drawLine(w - 4, ab + 1, w - 4, h - 2);
                g.drawLine(3, h - 2, w - 4, h - 2);
                int dh = (int) (h * 0.30);
                g.drawRect(w / 2 - 3, h - 2 - dh, 6, dh);
            } else if (type == 5) { // trash
                g.setStroke(new BasicStroke(1.4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g.drawLine(0, 3, w, 3);
                g.drawRect((int) (w * 0.33), 0, (int) (w * 0.34), 3);
                g.drawLine(2, 5, 3, h - 1);
                g.drawLine(w - 2, 5, w - 3, h - 1);
                g.drawLine(3, h - 1, w - 3, h - 1);
                g.drawLine((int) (w * 0.33), 8, (int) (w * 0.33), h - 4);
                g.drawLine((int) (w * 0.5), 8, (int) (w * 0.5), h - 4);
                g.drawLine((int) (w * 0.67), 8, (int) (w * 0.67), h - 4);
            } else if (type == 6) { // voucher
                g.setStroke(new BasicStroke(1.5f));
                g.drawRoundRect(1, 1, w - 2, h - 2, 6, 6);
                g.setStroke(new BasicStroke(1.3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f,
                        new float[] { 2f, 2f }, 0f));
                g.drawLine(w / 3, 4, w / 3, h - 4);
                g.setStroke(new BasicStroke(1f));
                g.setFont(new Font("SansSerif", Font.BOLD, 13));
                g.drawString("%", w / 3 + 5, h / 2 + 5);
            } else if (type == 7) { // minus
                g.setStroke(new BasicStroke(1.4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g.drawLine(w / 2 - 6, h / 2, w / 2 + 6, h / 2);
            } else if (type == 8) { // plus
                g.setStroke(new BasicStroke(1.4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g.drawLine(w / 2 - 6, h / 2, w / 2 + 6, h / 2);
                g.drawLine(w / 2, h / 2 - 6, w / 2, h / 2 + 6);
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
            g2.setColor(redbar);
            g2.fillRoundRect(r.x, r.y, r.width, r.height, 2, 2);
            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                cartcode f = new cartcode();
                f.setVisible(true);
            }
        });
    }
}
