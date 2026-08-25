import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainHomePage extends JFrame {
	private JButton btnPlaceOrder;
	private JButton btnSearch;
	private JButton btnViewOrders;
	private JButton btnUpdateOrder;
	private JButton btnExit;
	
	public MainHomePage() {
		setTitle("iHungry Burger Shop - Home");
		setSize(700, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(1, 2)); //Screen eka dekata bedeema(Left, Right)
		
		//-----------Left Panel-----------
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setLayout(new BorderLayout());
		
		JLabel lblTitle = new JLabel("Welcome to Burgers", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
		lblTitle.setForeground(new Color(180, 130, 0));
		lblTitle.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		leftPanel.add(lblTitle, BorderLayout.NORTH);
		
		//Image ekak add karanna
		JLabel lblImagePlaceholder = new JLabel("\uD83C\uDF54", SwingConstants.CENTER);
		lblImagePlaceholder.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 100));
		leftPanel.add(lblImagePlaceholder, BorderLayout.CENTER);
		
		add(leftPanel);
		
		//-----------Right Panel-----------
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(225, 225, 225));
		rightPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        
        // Create Custom Buttons
        btnPlaceOrder = createMenuButton("Place Order");
        btnSearch = createMenuButton("Search");
        btnViewOrders = createMenuButton("View Orders");
        btnUpdateOrder = createMenuButton("Update Order Details");
        btnExit = createMenuButton("Exit");
        
        // Buttons GridBagLayout ekata ekathu kireema
        gbc.gridy = 0; rightPanel.add(btnPlaceOrder, gbc);
        gbc.gridy = 1; rightPanel.add(btnSearch, gbc);
        gbc.gridy = 2; rightPanel.add(btnViewOrders, gbc);
        gbc.gridy = 3; rightPanel.add(btnUpdateOrder, gbc);
        
        // Exit Button eka wenama pahalin thabeema
        gbc.gridy = 4;
        gbc.insets = new Insets(25, 10, 10, 10);
        rightPanel.add(btnExit, gbc);
        
        add(rightPanel);
        
        //-----------Action Listeners (Click Events)-----------
        btnExit.addActionListener(new ActionListener() { 
			@Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }   
        });
		
		// Other Forms link 
		btnPlaceOrder.addActionListener(e -> {
            new PlaceOrderForm().setVisible(true);
            this.dispose();
            //JOptionPane.showMessageDialog(this, "Place Order window will open.");
        });
	}	
	
	private JButton createMenuButton(String text) {
		JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(217, 83, 79)); // Burger Red/Coral color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 38));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return button;	
	}
}
