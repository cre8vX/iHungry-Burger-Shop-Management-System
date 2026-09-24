import javax.swing.*;
import java.awt.*;

public class SearchOrderForm extends JFrame {

	private JTextField txtSearchOrderId;
	private JButton btnSearch;
	private JLabel lblCustomerIdVal;
	private JLabel lblCustomerNameVal;
	private JLabel lblQtyVal;
    private JLabel lblTotalVal;
    private JLabel lblStatusVal;
    private JButton btnBack;
    
    public SearchOrderForm() {
		setTitle("iHungry Burger Shop - Search Order");	
		setSize(650, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		//Header Title
		JLabel lblHeader = new JLabel("Search Order Details", SwingConstants.CENTER);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
		lblHeader.setOpaque(true);
		lblHeader.setBackground(new Color(217, 83, 79));
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setPreferredSize(new Dimension(650, 50));
        add(lblHeader, BorderLayout.NORTH);
        
        //Center Form Panel
        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(Color.WHITE);
        
        //Search Input Section
        JLabel lblEnterId = new JLabel("Enter Order ID :");
        lblEnterId.setFont(new Font("Arial", Font.BOLD, 14));
        lblEnterId.setBounds(50, 30, 150, 25);
        formPanel.add(lblEnterId);
        
        txtSearchOrderId = new JTextField();
        txtSearchOrderId.setBounds(180, 30, 150, 28);
        formPanel.add(txtSearchOrderId); 
        
        btnSearch = new JButton("Search");
        btnSearch.setBounds(350, 30, 100, 28);
        btnSearch.setBackground(new Color(217, 83, 79));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);
        formPanel.add(btnSearch);
        
        //Divider
        JSeparator sep = new JSeparator();
        sep.setBounds(50, 75, 530, 5);
        formPanel.add(sep);
        
        // Details Labels
        // 1. Customer ID
        JLabel lblCustId = new JLabel("Customer ID :");
        lblCustId.setFont(new Font("Arial", Font.BOLD, 14));
        lblCustId.setBounds(50, 100, 150, 25);
        formPanel.add(lblCustId);
        
        lblCustomerIdVal = new JLabel("---");
        lblCustomerIdVal.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCustomerIdVal.setBounds(200, 100, 200, 25);
        formPanel.add(lblCustomerIdVal);
        
        // 2. Customer Name
        JLabel lblCustName = new JLabel("Customer Name : ");
        lblCustName.setFont(new Font("Arial", Font.BOLD, 14));
        lblCustName.setBounds(50, 140, 150, 25);
        formPanel.add(lblCustName);
        
        lblCustomerNameVal = new JLabel("---");
        lblCustomerNameVal.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCustomerNameVal.setBounds(200, 140, 200, 25);
        formPanel.add(lblCustomerNameVal);
        
        // 3. Burger QTY
        JLabel lblQty = new JLabel("Burger QTY : ");
        lblQty.setFont(new Font("Arial", Font.BOLD, 14));
        lblQty.setBounds(50, 180, 150, 25);
        formPanel.add(lblQty);

        lblQtyVal = new JLabel("---");
        lblQtyVal.setFont(new Font("Arial", Font.PLAIN, 14));
        lblQtyVal.setBounds(200, 180, 200, 25);
        formPanel.add(lblQtyVal);
        
        // 4. Total Value
        JLabel lblTotal = new JLabel("Total Value : ");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
        lblTotal.setBounds(50, 220, 150, 25);
        formPanel.add(lblTotal);

        lblTotalVal = new JLabel("---");
        lblTotalVal.setFont(new Font("Arial", Font.BOLD, 14));
        lblTotalVal.setForeground(new Color(217, 83, 79));
        lblTotalVal.setBounds(200, 220, 200, 25);
        formPanel.add(lblTotalVal);
        
        // 5. Order Status
        JLabel lblStatus = new JLabel("Order Status : ");
        lblStatus.setFont(new Font("Arial", Font.BOLD, 14));
        lblStatus.setBounds(50, 260, 150, 25);
        formPanel.add(lblStatus); 
        
        lblStatusVal = new JLabel("---");
        lblStatusVal.setFont(new Font("Arial", Font.BOLD, 14));
        lblStatusVal.setBounds(200, 260, 200, 25);
        formPanel.add(lblStatusVal);
        
        //Back Button
        btnBack = new JButton("Back to Home");
        btnBack.setBounds(420, 340, 160, 35);
        btnBack.setBackground(new Color(217, 83, 79));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        formPanel.add(btnBack);
        
        add(formPanel, BorderLayout.CENTER);
        
        // Search Action
        btnSearch.addActionListener(e -> {
			String searchId = txtSearchOrderId.getText().trim();
			if(searchId.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please enter an Order ID!", "Warning", JOptionPane.WARNING_MESSAGE);	
				return;
			}
			
			Order order = OrderController.searchOrder(searchId);
			if (order != null) {
				lblCustomerIdVal.setText(order.getCustomerId());
                lblCustomerNameVal.setText(order.getCustomerName());
                lblQtyVal.setText(String.valueOf(order.getBurgerQty()));
                lblTotalVal.setText(String.format("%.2f", order.getTotalValue()));
                lblStatusVal.setText(order.getOrderStatusText());    	
			}else{
				JOptionPane.showMessageDialog(this, "Order ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
				clearDetails();	
			}
		});
			
		// Back Action
		btnBack.addActionListener(e -> {
			new MainHomePage().setVisible(true);
			this.dispose();	
		});
	}
		
	private void clearDetails() {
		lblCustomerIdVal.setText("---");
		lblCustomerNameVal.setText("---");
		lblQtyVal.setText("---");
		lblTotalVal.setText("---");
		lblStatusVal.setText("---");
    }			
}
