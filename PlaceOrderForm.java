import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlaceOrderForm extends JFrame {
	
	private JLabel lblOrderIdValue;
	private JTextField txtCustId;
	private JTextField txtCustName;
	private JTextField txtQty;
	private JLabel lblStatusValue;
	private JLabel lblNetTotal;
	private JButton btnPlaceOrder;
	private JButton btnBack;
	private JButton btnCancel;	
	
	public PlaceOrderForm() {
		setTitle("iHungry Burger Shop - Place Order");	
		setSize(700, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		// Header Title
		JLabel lblHeader = new JLabel("Place Order", SwingConstants.CENTER);
		lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        lblHeader.setOpaque(true);
        lblHeader.setBackground(new Color(217, 83, 79));
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setPreferredSize(new Dimension(700, 50));
        add(lblHeader, BorderLayout.NORTH);
        
        // Center Form Panel
        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(Color.WHITE);
        
        // Order ID
        JLabel lblOrderId = new JLabel("Order Id : ");
        lblOrderId.setFont(new Font("Arial", Font.BOLD, 14));
        lblOrderId.setBounds(50, 40, 120, 25);
        formPanel.add(lblOrderId);
        
        lblOrderIdValue = new JLabel(OrderController.generateOrderId());
        lblOrderIdValue.setFont(new Font("Arial", Font.BOLD, 14));
        lblOrderIdValue.setBounds(180, 40, 150, 25);
        formPanel.add(lblOrderIdValue);
        
        // Customer ID
        JLabel lblCustId = new JLabel("Customer Id : ");
        lblCustId.setFont(new Font("Arial", Font.BOLD, 14));
        lblCustId.setBounds(50, 80, 120, 25);
        formPanel.add(lblCustId);
        
        txtCustId = new JTextField(OrderController.generateCustomerId());
        txtCustId.setBounds(180, 80, 160, 28);
        formPanel.add(txtCustId);
        
        // Customer Name
        JLabel lblCustName = new JLabel("Customer Name : ");
        lblCustName.setFont(new Font("Arial", Font.BOLD, 14));
        lblCustName.setBounds(50, 120, 130, 25);
        formPanel.add(lblCustName);

        txtCustName = new JTextField();
        txtCustName.setBounds(180, 120, 160, 28);
        formPanel.add(txtCustName);
        
        // Divider
        JSeparator sep = new JSeparator();
        sep.setBounds(50, 165, 300, 5);
        formPanel.add(sep);

        // Burger QTY
        JLabel lblQty = new JLabel("Burger QTY : ");
        lblQty.setFont(new Font("Arial", Font.BOLD, 14));
        lblQty.setBounds(50, 185, 120, 25);
        formPanel.add(lblQty);

        txtQty = new JTextField();
        txtQty.setBounds(180, 185, 160, 28);
        formPanel.add(txtQty);

        // Status
        JLabel lblStatus = new JLabel("Order Status : ");
        lblStatus.setFont(new Font("Arial", Font.BOLD, 14));
        lblStatus.setBounds(50, 230, 120, 25);
        formPanel.add(lblStatus);

        lblStatusValue = new JLabel("PREPARING");
        lblStatusValue.setFont(new Font("Arial", Font.PLAIN, 14));
        lblStatusValue.setBounds(180, 230, 150, 25);
        formPanel.add(lblStatusValue);

        // Net Total
        JLabel lblNet = new JLabel("NET Total : ");
        lblNet.setFont(new Font("Arial", Font.BOLD, 15));
        lblNet.setBounds(400, 320, 100, 25);
        formPanel.add(lblNet);

        lblNetTotal = new JLabel("0.00");
        lblNetTotal.setFont(new Font("Arial", Font.BOLD, 15));
        lblNetTotal.setForeground(new Color(217, 83, 79));
        lblNetTotal.setBounds(500, 320, 150, 25);
        formPanel.add(lblNetTotal);
        
        // Buttons
        btnPlaceOrder = new JButton("Place Order");
        btnPlaceOrder.setBounds(480, 80, 160, 35);
        btnPlaceOrder.setBackground(new Color(92, 184, 92));
        btnPlaceOrder.setForeground(Color.WHITE);
        btnPlaceOrder.setFocusPainted(false);
        formPanel.add(btnPlaceOrder);

        btnBack = new JButton("Back to home Page");
        btnBack.setBounds(480, 130, 160, 35);
        btnBack.setBackground(new Color(217, 83, 79));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        formPanel.add(btnBack);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(480, 180, 160, 35);
        btnCancel.setBackground(new Color(217, 83, 79));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFocusPainted(false);
        formPanel.add(btnCancel);

        add(formPanel, BorderLayout.CENTER);
        
        // Auto-fetch Customer Name when entering Customer ID
        txtCustId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String name = OrderController.getCustomerNameById(txtCustId.getText().trim());
                if (name != null) {
                    txtCustName.setText(name);
                }
            }
        });
        
        // Real-time calculation of NET Total
        txtQty.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { calculate(); }
            public void removeUpdate(DocumentEvent e) { calculate(); }
            public void changedUpdate(DocumentEvent e) { calculate(); }

            private void calculate() {
                try {
                    int qty = Integer.parseInt(txtQty.getText().trim());
                    if (qty > 0) {
                        lblNetTotal.setText(String.format("%.2f", qty * Order.BURGER_PRICE));
                    } else {
                        lblNetTotal.setText("0.00");
                    }
                } catch (NumberFormatException ex) {
                    lblNetTotal.setText("0.00");
                }
            }
        });

        // Button Actions
        btnPlaceOrder.addActionListener(e -> {
            String oId = lblOrderIdValue.getText();
            String cId = txtCustId.getText().trim();
            String cName = txtCustName.getText().trim();
            String qtyText = txtQty.getText().trim();

            if (cId.isEmpty() || cName.isEmpty() || qtyText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int qty = Integer.parseInt(qtyText);
                if (qty <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantity must be greater than 0!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean isPlaced = OrderController.placeOrder(oId, cId, cName, qty);
                if (isPlaced) {
                    JOptionPane.showMessageDialog(this, "Order Placed Successfully!");
                    clearFields();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Quantity!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> clearFields());

        btnBack.addActionListener(e -> {
            new MainHomePage().setVisible(true);
            this.dispose();
        });
    }

    private void clearFields() {
        lblOrderIdValue.setText(OrderController.generateOrderId());
        txtCustId.setText(OrderController.generateCustomerId());
        txtCustName.setText("");
        txtQty.setText("");
        lblNetTotal.setText("0.00");
    }
}
        

