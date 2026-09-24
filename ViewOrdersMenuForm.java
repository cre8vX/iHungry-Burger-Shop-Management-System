import javax.swing.*;
import java.awt.*;

public class ViewOrdersMenuForm extends JFrame {

    private JButton btnDelivered;
    private JButton btnProcessing;
    private JButton btnCanceled;
    private JButton btnExit;
    
    public ViewOrdersMenuForm() {
        setTitle("iHungry Burger Shop - View Orders");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2));

        // ----------- Left Panel -----------
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Welcome to Burgers", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(new Color(180, 130, 0));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        leftPanel.add(lblTitle, BorderLayout.NORTH);

        ImageIcon originalIcon = new ImageIcon("imgClipArt.jpg");
		Image img = originalIcon.getImage().getScaledInstance(270, 340, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(img);
		JLabel lblBurgerImage = new JLabel(scaledIcon, SwingConstants.CENTER);
		leftPanel.add(lblBurgerImage, BorderLayout.CENTER);

        add(leftPanel);

        // ----------- Right Panel -----------
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(225, 225, 225));

        // Red Header Title
        JLabel lblHeader = new JLabel("View Orders", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        lblHeader.setOpaque(true);
        lblHeader.setBackground(new Color(217, 83, 79));
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setPreferredSize(new Dimension(350, 45));
        rightPanel.add(lblHeader, BorderLayout.NORTH);

        // Buttons Panel
        JPanel centerButtonsPanel = new JPanel(new GridBagLayout());
        centerButtonsPanel.setBackground(new Color(225, 225, 225));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        btnDelivered = createMenuButton("Delivered Orders");
        btnProcessing = createMenuButton("Processing Orders");
        btnCanceled = createMenuButton("Canceled Orders");

        gbc.gridy = 0; centerButtonsPanel.add(btnDelivered, gbc);
        gbc.gridy = 1; centerButtonsPanel.add(btnProcessing, gbc);
        gbc.gridy = 2; centerButtonsPanel.add(btnCanceled, gbc);

        rightPanel.add(centerButtonsPanel, BorderLayout.CENTER);

        // Exit Button at Bottom Right
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        bottomPanel.setBackground(new Color(225, 225, 225));

        btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Arial", Font.BOLD, 13));
        btnExit.setBackground(new Color(217, 83, 79));
        btnExit.setForeground(Color.WHITE);
        btnExit.setFocusPainted(false);
        btnExit.setPreferredSize(new Dimension(80, 32));
        bottomPanel.add(btnExit);

        rightPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(rightPanel);

        // ----------- Action Listeners -----------
        btnDelivered.addActionListener(e -> {
            new OrdersListTableForm("Delivered Orders", Order.DELIVERED).setVisible(true);
            this.dispose();
        });

        btnProcessing.addActionListener(e -> {
            new OrdersListTableForm("Processing Orders", Order.PREPARING).setVisible(true);
            this.dispose();
        });

        btnCanceled.addActionListener(e -> {
            new OrdersListTableForm("Canceled Orders", Order.CANCELLED).setVisible(true);
            this.dispose();
        });

        btnExit.addActionListener(e -> {
            new MainHomePage().setVisible(true);
            this.dispose();
        });
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(217, 83, 79));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(210, 38));
        return button;
    }
}
