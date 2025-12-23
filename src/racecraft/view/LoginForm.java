/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package racecraft.view;

/**
 *
 * @author susha
 */
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class LoginForm extends JFrame {

    JTextField txtUsername;
    JPasswordField txtPassword;
    JRadioButton rbtnAdmin, rbtnUser;
    JCheckBox chkRemember;
    JLabel lblMessage;

    public LoginForm() {
        setTitle("Racecraft Strategy Manager");
        setSize(520, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ====== HEADER ======
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(30, 30, 30));
        pnlHeader.setBorder(new EmptyBorder(20, 10, 20, 10));

        JLabel lblTitle = new JLabel("RACECRAFT STRATEGY MANAGER");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));

        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        // ====== LOGIN CARD ======
        JPanel pnlCard = new JPanel();
        pnlCard.setBackground(Color.WHITE);
        pnlCard.setBorder(new CompoundBorder(
                new EmptyBorder(30, 40, 30, 40),
                new LineBorder(new Color(220, 220, 220))
        ));
        pnlCard.setLayout(new BoxLayout(pnlCard, BoxLayout.Y_AXIS));

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);

        // Username
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(labelFont);
        txtUsername = new JTextField();
        styleField(txtUsername);

        // Password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(labelFont);
        txtPassword = new JPasswordField();
        styleField(txtPassword);

        // Role Panel
        JPanel pnlRole = new JPanel();
        pnlRole.setBorder(new TitledBorder("Login as"));
        pnlRole.setBackground(Color.WHITE);

        rbtnAdmin = new JRadioButton("Admin");
        rbtnUser = new JRadioButton("User", true);
        rbtnAdmin.setBackground(Color.WHITE);
        rbtnUser.setBackground(Color.WHITE);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbtnAdmin);
        bg.add(rbtnUser);

        pnlRole.add(rbtnAdmin);
        pnlRole.add(rbtnUser);

        // Remember Me
        chkRemember = new JCheckBox("Remember me");
        chkRemember.setBackground(Color.WHITE);
        chkRemember.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // Add components
        pnlCard.add(lblUsername);
        pnlCard.add(txtUsername);
        pnlCard.add(Box.createVerticalStrut(15));
        pnlCard.add(lblPassword);
        pnlCard.add(txtPassword);
        pnlCard.add(Box.createVerticalStrut(20));
        pnlCard.add(pnlRole);
        pnlCard.add(Box.createVerticalStrut(10));
        pnlCard.add(chkRemember);

        JPanel center = new JPanel();
        center.setBackground(new Color(245, 245, 245));
        center.add(pnlCard);
        add(center, BorderLayout.CENTER);

        // ====== FOOTER ======
        JButton btnLogin = new JButton("LOGIN");
        JButton btnExit = new JButton("EXIT");

        styleButton(btnLogin, new Color(0, 120, 215));
        styleButton(btnExit, new Color(180, 180, 180));

        JPanel pnlButtons = new JPanel();
        pnlButtons.setBackground(new Color(245, 245, 245));
        pnlButtons.add(btnLogin);
        pnlButtons.add(Box.createHorizontalStrut(15));
        pnlButtons.add(btnExit);

        lblMessage = new JLabel(" ");
        lblMessage.setForeground(Color.RED);
        lblMessage.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel south = new JPanel(new BorderLayout());
        south.setBackground(new Color(245, 245, 245));
        south.add(pnlButtons, BorderLayout.NORTH);
        south.add(lblMessage, BorderLayout.SOUTH);

        add(south, BorderLayout.SOUTH);

        // Actions
        btnLogin.addActionListener(e -> login());
        btnExit.addActionListener(e -> System.exit(0));
    }

    private void styleField(JTextField field) {
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(new CompoundBorder(
                new LineBorder(new Color(200, 200, 200)),
                new EmptyBorder(5, 10, 5, 10)
        ));
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setFocusPainted(false);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(120, 38));
        btn.setBorder(new EmptyBorder(5, 15, 5, 15));
    }

    private void login() {
        if (txtUsername.getText().isEmpty() ||
            txtPassword.getPassword().length == 0) {
            lblMessage.setText("Username and password are required.");
        } else {
            lblMessage.setForeground(new Color(0, 150, 0));
            lblMessage.setText("Login successful.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
