import javax.swing.*;
import java.awt.*;

public class SearchOptionForm extends JFrame {

    private JButton btnSearchBestCustomer;
    private JButton btnSearchOrder;
    private JButton btnSearchCustomer;
    private JButton btnExit;

    public SearchOptionForm() {
        setTitle("iHungry Burger Shop - Search Options");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2));

        // ----------- Left Panel (Image & Welcome Text) -----------
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

        // ----------- Right Panel (Header, Buttons & Exit) -----------
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(225, 225, 225));

        // Red Header Title
        JLabel lblHeader = new JLabel("Search Options", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        lblHeader.setOpaque(true);
        lblHeader.setBackground(new Color(217, 83, 79));
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setPreferredSize(new Dimension(350, 45));
        rightPanel.add(lblHeader, BorderLayout.NORTH);

        // Buttons Panel (Center)
        JPanel centerButtonsPanel = new JPanel(new GridBagLayout());
        centerButtonsPanel.setBackground(new Color(225, 225, 225));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        btnSearchBestCustomer = createMenuButton("Search Best Customer");
        btnSearchOrder = createMenuButton("Search Order");
        btnSearchCustomer = createMenuButton("Search Customer");

        gbc.gridy = 0; centerButtonsPanel.add(btnSearchBestCustomer, gbc);
        gbc.gridy = 1; centerButtonsPanel.add(btnSearchOrder, gbc);
        gbc.gridy = 2; centerButtonsPanel.add(btnSearchCustomer, gbc);

        rightPanel.add(centerButtonsPanel, BorderLayout.CENTER);

        // Exit / Back to Main Menu Button at Bottom Right
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
        btnSearchBestCustomer.addActionListener(e -> {
            new SearchBestCustomerForm().setVisible(true);
            this.dispose();
        });

        btnSearchOrder.addActionListener(e -> {
            new SearchOrderForm().setVisible(true);
            this.dispose();
        });

        btnSearchCustomer.addActionListener(e -> {
            new SearchCustomerForm().setVisible(true);
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
