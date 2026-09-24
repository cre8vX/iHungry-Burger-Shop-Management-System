import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UpdateOrderDetailsForm extends JFrame {

    private JComboBox<String> cmbStatus;
    private JLabel lblWarningMsg;
    private JTextField txtOrderId;
    private JTextField txtCustId;
    private JTextField txtCustName;
    private JTextField txtQty;
    private JLabel lblTotalVal;
    private JButton btnUpdate;
    private JButton btnBack;

    private Order currentOrder = null;

    public UpdateOrderDetailsForm() {
        setTitle("iHungry Burger Shop - Update Order");
        setSize(650, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header Title
        JLabel lblHeader = new JLabel("Update Order", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        lblHeader.setOpaque(true);
        lblHeader.setBackground(new Color(217, 83, 79));
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setPreferredSize(new Dimension(650, 48));
        add(lblHeader, BorderLayout.NORTH);

        // Center Form Panel
        JPanel centerPanel = new JPanel(null);
        centerPanel.setBackground(Color.WHITE);

        // 1. Order Status (Row 1)
        JLabel lblStatus = new JLabel("Order Status");
        lblStatus.setFont(new Font("Arial", Font.BOLD, 13));
        lblStatus.setBounds(80, 40, 110, 25);
        centerPanel.add(lblStatus);

        String[] statuses = {"Preparing..", "Delivered..", "Cancelled.."};
        cmbStatus = new JComboBox<>(statuses);
        cmbStatus.setFont(new Font("Arial", Font.PLAIN, 13));
        cmbStatus.setBounds(200, 40, 140, 28);
        cmbStatus.setEnabled(false);
        centerPanel.add(cmbStatus);

        // Warning Label (Right side of status)
        lblWarningMsg = new JLabel("");
        lblWarningMsg.setFont(new Font("Arial", Font.BOLD, 10));
        lblWarningMsg.setForeground(new Color(217, 83, 79));
        lblWarningMsg.setBounds(360, 32, 260, 40);
        centerPanel.add(lblWarningMsg);

        // 2. Order ID (Row 2)
        JLabel lblOrderId = new JLabel("Order Id");
        lblOrderId.setFont(new Font("Arial", Font.BOLD, 13));
        lblOrderId.setBounds(80, 85, 110, 25);
        centerPanel.add(lblOrderId);

        txtOrderId = new JTextField();
        txtOrderId.setFont(new Font("Arial", Font.PLAIN, 13));
        txtOrderId.setBounds(200, 85, 140, 28);
        centerPanel.add(txtOrderId);

        // 3. Customer ID (Row 3)
        JLabel lblCustId = new JLabel("Customer Id");
        lblCustId.setFont(new Font("Arial", Font.BOLD, 13));
        lblCustId.setBounds(80, 130, 110, 25);
        centerPanel.add(lblCustId);

        txtCustId = createDisabledTextField();
        txtCustId.setBounds(200, 130, 140, 28);
        centerPanel.add(txtCustId);

        // 4. Name (Row 4)
        JLabel lblCustName = new JLabel("Name");
        lblCustName.setFont(new Font("Arial", Font.BOLD, 13));
        lblCustName.setBounds(80, 175, 110, 25);
        centerPanel.add(lblCustName);

        txtCustName = createDisabledTextField();
        txtCustName.setBounds(200, 175, 140, 28);
        centerPanel.add(txtCustName);

        // 5. Order QTY (Row 5)
        JLabel lblQty = new JLabel("Order QTY");
        lblQty.setFont(new Font("Arial", Font.BOLD, 13));
        lblQty.setBounds(80, 220, 110, 25);
        centerPanel.add(lblQty);

        txtQty = new JTextField();
        txtQty.setFont(new Font("Arial", Font.PLAIN, 13));
        txtQty.setBounds(200, 220, 140, 28);
        txtQty.setBackground(new Color(225, 225, 225));
        txtQty.setEditable(false);
        centerPanel.add(txtQty);

        // 6. Total (Row 6)
        JLabel lblTotal = new JLabel("Total");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 13));
        lblTotal.setBounds(80, 265, 110, 25);
        centerPanel.add(lblTotal);

        lblTotalVal = new JLabel("");
        lblTotalVal.setFont(new Font("Arial", Font.BOLD, 14));
        lblTotalVal.setForeground(new Color(217, 83, 79));
        lblTotalVal.setBounds(200, 265, 140, 25);
        centerPanel.add(lblTotalVal);

        // Bottom Buttons (Update Order & Back)
        btnUpdate = new JButton("Update Order");
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 13));
        btnUpdate.setBackground(new Color(40, 167, 69));
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setBounds(390, 390, 125, 35);
        btnUpdate.setEnabled(false);
        centerPanel.add(btnUpdate);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 13));
        btnBack.setBackground(new Color(217, 83, 79));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setBounds(530, 390, 85, 35);
        centerPanel.add(btnBack);

        add(centerPanel, BorderLayout.CENTER);

        // ----------- Action Events -----------

        // Order ID එක Type කරද්දී Load වීම
        txtOrderId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                loadOrderData();
            }
        });

        // Update Action
        btnUpdate.addActionListener(e -> updateOrder());

        // Back Action
        btnBack.addActionListener(e -> {
            new MainHomePage().setVisible(true);
            this.dispose();
        });
    }

    private JTextField createDisabledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.BOLD, 12));
        field.setBackground(new Color(205, 205, 205));
        field.setEditable(false);
        return field;
    }

    private void loadOrderData() {
        String oId = txtOrderId.getText().trim();
        if (oId.isEmpty()) {
            clearFields();
            return;
        }

        currentOrder = OrderController.searchOrder(oId);
        if (currentOrder != null) {
            txtCustId.setText(currentOrder.getCustomerId());
            txtCustName.setText(currentOrder.getCustomerName());
            txtQty.setText(String.valueOf(currentOrder.getBurgerQty()));
            lblTotalVal.setText(String.format("%.2f", currentOrder.getTotalValue()));

            int status = currentOrder.getOrderStatus();

            if (status == Order.PREPARING) {
                cmbStatus.setSelectedIndex(0); // Preparing..
                cmbStatus.setEnabled(true);
                lblWarningMsg.setText("");
                btnUpdate.setEnabled(true);
            } else if (status == Order.DELIVERED) {
                cmbStatus.setSelectedIndex(1); // Delivered..
                cmbStatus.setEnabled(false);
                lblWarningMsg.setText("<html>This order has been Delivered<br>Sorry, you can not update this order</html>");
                btnUpdate.setEnabled(false);
            } else if (status == Order.CANCELLED) {
                cmbStatus.setSelectedIndex(2); // Cancelled..
                cmbStatus.setEnabled(false);
                lblWarningMsg.setText("<html>This order has been Canceled<br>Sorry, you can not update this order</html>");
                btnUpdate.setEnabled(false);
            }
        } else {
            clearFields();
        }
    }

    private void updateOrder() {
        if (currentOrder == null) return;

        int selectedIndex = cmbStatus.getSelectedIndex();
        int newStatus = Order.PREPARING;
        if (selectedIndex == 1) {
            newStatus = Order.DELIVERED;
        } else if (selectedIndex == 2) {
            newStatus = Order.CANCELLED;
        }

        boolean updated = OrderController.updateOrderStatus(currentOrder.getOrderId(), newStatus);
        if (updated) {
            JOptionPane.showMessageDialog(this, "Order Status Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadOrderData(); // reload details with warning message
        }
    }

    private void clearFields() {
        txtCustId.setText("");
        txtCustName.setText("");
        txtQty.setText("");
        lblTotalVal.setText("");
        lblWarningMsg.setText("");
        cmbStatus.setEnabled(false);
        btnUpdate.setEnabled(false);
        currentOrder = null;
    }
}
