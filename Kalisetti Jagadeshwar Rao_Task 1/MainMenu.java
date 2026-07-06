import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;

public class MainMenu extends JFrame {

    private String loggedInUser;
    private String userRole;

    private JLabel lblTotalBookings;
    private JLabel lblTotalCancelled;
    private JLabel lblTodayBookings;

    public MainMenu(String fullName, String role) {
        this.loggedInUser = fullName;
        this.userRole     = role;

        setTitle("Railway Reservation System — Main Menu");
        setSize(620, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);   // Center on screen
        setResizable(false);

        initComponents();

        loadStatistics();

        setVisible(true);
    }

    private void initComponents() {

        JPanel outerPanel = new JPanel(new BorderLayout(0, 0));
        outerPanel.setBackground(new Color(240, 244, 250));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(25, 42, 86));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(18, 25, 18, 25));

        JPanel headerLeft = new JPanel(new GridLayout(2, 1));
        headerLeft.setBackground(new Color(25, 42, 86));

        JLabel lblTitle = new JLabel("🚂  Indian Railway Reservation System");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblSubtitle = new JLabel("Main Dashboard");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitle.setForeground(new Color(180, 200, 230));

        headerLeft.add(lblTitle);
        headerLeft.add(lblSubtitle);

        JPanel headerRight = new JPanel(new GridLayout(3, 1, 0, 2));
        headerRight.setBackground(new Color(25, 42, 86));

        JLabel lblUser = new JLabel("👤 " + loggedInUser, SwingConstants.RIGHT);
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUser.setForeground(Color.WHITE);

        JLabel lblRole = new JLabel(userRole, SwingConstants.RIGHT);
        lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblRole.setForeground(new Color(180, 200, 230));

        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnLogout.setBackground(new Color(180, 30, 30));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.setBorder(BorderFactory.createEmptyBorder(4, 14, 4, 14));
        btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogout.setAlignmentX(Component.RIGHT_ALIGNMENT);
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();             
                new LoginForm();       
            }
        });
        
        headerRight.add(lblUser);
        headerRight.add(lblRole);
        headerRight.add(btnLogout);

        headerPanel.add(headerLeft,  BorderLayout.WEST);
        headerPanel.add(headerRight, BorderLayout.EAST);

        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 14, 0));
        statsPanel.setBackground(new Color(240, 244, 250));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(18, 25, 10, 25));

        lblTotalBookings  = new JLabel("...", SwingConstants.CENTER);
        lblTotalCancelled = new JLabel("...", SwingConstants.CENTER);
        lblTodayBookings  = new JLabel("...", SwingConstants.CENTER);

        statsPanel.add(createStatCard("Total Bookings",   lblTotalBookings,  new Color(21, 101, 192)));
        statsPanel.add(createStatCard("Cancelled",         lblTotalCancelled, new Color(185, 28, 28)));
        statsPanel.add(createStatCard("Today's Bookings", lblTodayBookings,  new Color(21, 128, 61)));

        JPanel navPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        navPanel.setBackground(new Color(240, 244, 250));
        navPanel.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        navPanel.add(createNavButton(
            "🎫",
            "Reservation Form",
            "Book a new train ticket",
            new Color(21, 101, 192),
            e -> openReservationForm()
        ));

        navPanel.add(createNavButton(
            "❌",
            "Cancellation Form",
            "Cancel an existing ticket",
            new Color(185, 28, 28),
            e -> openCancellationForm()
        ));

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(new Color(240, 244, 250));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(6, 25, 10, 25));

        JTextArea txtInfo = new JTextArea(
            "  ℹ️  How to use:\n" +
            "  • Click 'Reservation Form' to book a new ticket.\n" +
            "  • Click 'Cancellation Form' to cancel using a PNR number.\n" +
            "  • Statistics above are fetched live from the database."
        );
        txtInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtInfo.setForeground(new Color(70, 90, 130));
        txtInfo.setBackground(new Color(225, 235, 250));
        txtInfo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 200, 230)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        txtInfo.setEditable(false);  // Read-only text area
        infoPanel.add(txtInfo, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(25, 42, 86));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        JLabel lblFooter = new JLabel(
            "Online Railway Reservation System  |  Java + MySQL  |  Internship Project",
            SwingConstants.CENTER
        );
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblFooter.setForeground(new Color(160, 180, 220));
        footerPanel.add(lblFooter, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 0, 0));
        centerPanel.setBackground(new Color(240, 244, 250));
        centerPanel.add(statsPanel);
        centerPanel.add(navPanel);
        centerPanel.add(infoPanel);

        outerPanel.add(headerPanel, BorderLayout.NORTH);
        outerPanel.add(centerPanel, BorderLayout.CENTER);
        outerPanel.add(footerPanel, BorderLayout.SOUTH);

        add(outerPanel);
    }

    private JPanel createStatCard(String title, JLabel valueLabel, Color color) {

        JPanel card = new JPanel(new GridLayout(2, 1));
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(14, 10, 14, 10));

        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        titleLabel.setForeground(new Color(220, 230, 255));

        card.add(valueLabel);
        card.add(titleLabel);

        return card;
    }

    private JPanel createNavButton(String icon, String title, String subtitle,
                                    Color bgColor, ActionListener listener) {

        JPanel card = new JPanel(new GridLayout(3, 1, 0, 4));
        card.setBackground(bgColor);
        card.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel lblIcon = new JLabel(icon, SwingConstants.CENTER);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));

        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblSub = new JLabel(subtitle, SwingConstants.CENTER);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblSub.setForeground(new Color(200, 220, 255));

        card.add(lblIcon);
        card.add(lblTitle);
        card.add(lblSub);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.actionPerformed(new ActionEvent(card, ActionEvent.ACTION_PERFORMED, "click"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(bgColor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(bgColor);
            }
        });

        return card;
    }

    private void loadStatistics() {

        Connection conn = null;
        Statement  stmt = null;
        ResultSet  rs   = null;

        try {
            conn = DBConnection.getConnection();
            if (conn == null) return;

            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT COUNT(*) FROM reservations");
            if (rs.next()) lblTotalBookings.setText(String.valueOf(rs.getInt(1)));
            rs.close();

            rs = stmt.executeQuery("SELECT COUNT(*) FROM reservations WHERE status = 'Cancelled'");
            if (rs.next()) lblTotalCancelled.setText(String.valueOf(rs.getInt(1)));
            rs.close();

            rs = stmt.executeQuery("SELECT COUNT(*) FROM reservations WHERE booking_date = CURDATE()");
            if (rs.next()) lblTodayBookings.setText(String.valueOf(rs.getInt(1)));

        } catch (SQLException e) {
            lblTotalBookings.setText("—");
            lblTotalCancelled.setText("—");
            lblTodayBookings.setText("—");
            e.printStackTrace();

        } finally {
            try {
                if (rs   != null) rs.close();
                if (stmt != null) stmt.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    private void openReservationForm() {
        new ReservationForm(this);  
    }

    private void openCancellationForm() {
        new CancellationForm(this);
    }

    public void refreshStats() {
        loadStatistics();
    }
}

