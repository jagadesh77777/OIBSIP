import javax.swing.*;          
import java.awt.*;              
import java.awt.event.*;       
import java.sql.*;              

public class LoginForm extends JFrame {

    private JTextField     txtLoginId;   
    private JPasswordField txtPassword;   
    private JButton        btnLogin;      
    private JButton        btnClear;      
    private JLabel         lblStatus;     
    private JCheckBox      chkShowPass;   

    public LoginForm() {
        setTitle("Railway Reservation System — Login");
        setSize(480, 400);                         
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocationRelativeTo(null);            
        setResizable(false);                       
        initComponents();
        setVisible(true);
    }

    private void initComponents() {

        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBackground(new Color(25, 42, 86));
      
        JPanel headerPanel = new JPanel(new GridLayout(3, 1)); 
        headerPanel.setBackground(new Color(25, 42, 86));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(25, 20, 15, 20)); 
       
        JLabel lblIcon = new JLabel("🚂  Indian Railway Reservation System", SwingConstants.CENTER);
        lblIcon.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblIcon.setForeground(Color.WHITE);

        JLabel lblSubtitle = new JLabel("Secure Staff Login Portal", SwingConstants.CENTER);
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitle.setForeground(new Color(180, 200, 230));

        JLabel lblSeparator = new JLabel("─────────────────────────────────", SwingConstants.CENTER);
        lblSeparator.setForeground(new Color(100, 130, 180));

        headerPanel.add(lblIcon);
        headerPanel.add(lblSubtitle);
        headerPanel.add(lblSeparator);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));
   
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.insets = new Insets(8, 5, 8, 5);  
        gbc.gridx = 0; gbc.gridy = 0;         
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;                         
        JLabel lblLoginId = new JLabel("Login ID:");
        lblLoginId.setFont(new Font("Segoe UI", Font.BOLD, 13));
        formPanel.add(lblLoginId, gbc);

        gbc.gridx = 1; gbc.gridy = 0;            
        gbc.weightx = 0.7;                        
        txtLoginId = new JTextField(20);           
        txtLoginId.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtLoginId.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 200, 230), 1),
            BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        formPanel.add(txtLoginId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 13));
        formPanel.add(lblPassword, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 0.7;
        txtPassword = new JPasswordField(20);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 200, 230), 1),
            BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        formPanel.add(txtPassword, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.weightx = 0.7;
        chkShowPass = new JCheckBox("Show Password");
        chkShowPass.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        chkShowPass.setBackground(Color.WHITE);
        
        chkShowPass.addActionListener(e -> {
            if (chkShowPass.isSelected()) {
               
                txtPassword.setEchoChar((char) 0);
            } else {
               
                txtPassword.setEchoChar('●');
            }
        });
        formPanel.add(chkShowPass, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;                        
        lblStatus = new JLabel(" ");            
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(lblStatus, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnPanel.setBackground(Color.WHITE);

        btnLogin = new JButton("  Login  ");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLogin.setBackground(new Color(25, 42, 86));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        btnClear = new JButton("  Clear  ");
        btnClear.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnClear.setBackground(new Color(220, 220, 220));
        btnClear.setForeground(new Color(50, 50, 50));
        btnClear.setFocusPainted(false);
        btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnClear.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        btnPanel.add(btnLogin);
        btnPanel.add(btnClear);
        formPanel.add(btnPanel, gbc);
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(240, 244, 248));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        JLabel lblFooter = new JLabel("Demo: Login ID = admin  |  Password = admin123");
        lblFooter.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblFooter.setForeground(new Color(100, 120, 160));
        footerPanel.add(lblFooter);
        outerPanel.add(headerPanel, BorderLayout.NORTH);
        outerPanel.add(formPanel,   BorderLayout.CENTER);
        outerPanel.add(footerPanel, BorderLayout.SOUTH);

        add(outerPanel);
        attachEvents();
    }
    private void attachEvents() {

        btnLogin.addActionListener(e -> performLogin());
        btnClear.addActionListener(e -> clearFields());
        txtPassword.addActionListener(e -> performLogin());
        txtLoginId.addActionListener(e -> txtPassword.requestFocus());
    }

    private void performLogin() {
        String loginId  = txtLoginId.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (loginId.isEmpty()) {
            showStatus("Please enter your Login ID.", Color.RED);
            txtLoginId.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            showStatus("Please enter your Password.", Color.RED);
            txtPassword.requestFocus();
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();

            if (conn == null) {
                showStatus("Database connection failed. Check MySQL setup.", Color.RED);
                return;
            }

            String sql = "SELECT full_name, role FROM users WHERE login_id = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, loginId); 
            pstmt.setString(2, password);  
            rs = pstmt.executeQuery();

            if (rs.next()) {
         
                String fullName = rs.getString("full_name"); 
                String role     = rs.getString("role");      

                showStatus("Login successful! Welcome, " + fullName, new Color(0, 128, 0));
                new MainMenu(fullName, role);

                dispose();

            } else {
              
                showStatus("Invalid Login ID or Password. Try again.", Color.RED);
                txtPassword.setText(""); // Clear password field
                txtPassword.requestFocus();
            }

        } catch (SQLException e) {
            showStatus("Database error: " + e.getMessage(), Color.RED);
            e.printStackTrace();

        } finally {
         
            try {
                if (rs    != null) rs.close();
                if (pstmt != null) pstmt.close();
                DBConnection.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void showStatus(String message, Color color) {
        lblStatus.setText(message);
        lblStatus.setForeground(color);
    }

    private void clearFields() {
        txtLoginId.setText("");
        txtPassword.setText("");
        lblStatus.setText(" ");
        txtLoginId.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm());
    }
}
