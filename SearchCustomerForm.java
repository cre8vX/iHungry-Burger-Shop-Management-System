import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchCustomerForm extends JFrame {

    private JTextField txtCustId;
    private JLabel lblNameVal;
    private JTable tblOrderDetails;
    private DefaultTableModel tableModel;
    private JButton btnBack;

    public SearchCustomerForm() {
        setTitle("iHungry Burger Shop - Search Customer");
        setSize(650, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header Title
        JLabel lblHeader = new JLabel("Search Customer", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        lblHeader.setOpaque(true);
        lblHeader.setBackground(new Color(217, 83, 79));
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setPreferredSize(new Dimension(650, 45));
        add(lblHeader, BorderLayout.NORTH);

        // Center Panel (Contains Input Section + Sub Header + Table)
        JPanel mainCenterPanel = new JPanel(null);
        mainCenterPanel.setBackground(Color.WHITE);

        // 1. Enter Customer ID Input
        JLabel lblCustId = new JLabel("Enter Customer Id : ");
        lblCustId.setFont(new Font("Arial", Font.BOLD, 14));
        lblCustId.setBounds(80, 20, 160, 25);
        mainCenterPanel.add(lblCustId);

        txtCustId = new JTextField();
        txtCustId.setBounds(240, 20, 150, 28);
        mainCenterPanel.add(txtCustId);
        
        // 2. Name Display
        JLabel lblName = new JLabel("Name : ");
        lblName.setFont(new Font("Arial", Font.BOLD, 14));
        lblName.setBounds(80, 55, 100, 25);
        mainCenterPanel.add(lblName);

        lblNameVal = new JLabel("");
        lblNameVal.setFont(new Font("Arial", Font.BOLD, 14));
        lblNameVal.setBounds(180, 55, 300, 25);
        mainCenterPanel.add(lblNameVal);

        // 3. Sub Header "Order Details"
        JLabel lblSubHeader = new JLabel("Order Details", SwingConstants.CENTER);
        lblSubHeader.setFont(new Font("Arial", Font.BOLD, 16));
        lblSubHeader.setOpaque(true);
        lblSubHeader.setBackground(new Color(217, 83, 79));
        lblSubHeader.setForeground(Color.WHITE);
        lblSubHeader.setBounds(0, 95, 650, 32);
        mainCenterPanel.add(lblSubHeader);

        // 4. JTable for Orders
        String[] columnNames = {"Order Id", "Order QTY", "Total"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblOrderDetails = new JTable(tableModel);
        tblOrderDetails.setRowHeight(25);
        tblOrderDetails.setFont(new Font("Arial", Font.PLAIN, 13));
        tblOrderDetails.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tblOrderDetails.getTableHeader().setBackground(new Color(220, 220, 220));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columnNames.length; i++) {
            tblOrderDetails.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tblOrderDetails);
        scrollPane.setBounds(70, 145, 500, 180);
        mainCenterPanel.add(scrollPane);

        // 5. Back Button (Bottom Right)
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(217, 83, 79));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setBounds(470, 390, 100, 32);
        mainCenterPanel.add(btnBack);

        add(mainCenterPanel, BorderLayout.CENTER);
		
		// Enter key/Text change weddi Orders load wenna
        txtCustId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchCustomerOrders();
            }
        });
        
        // Back Action -> Search Options Menu 
        btnBack.addActionListener(e -> {
            new SearchOptionForm().setVisible(true);
            this.dispose();
        });
    }  
    private void searchCustomerOrders() {
        String cId = txtCustId.getText().trim();
        tableModel.setRowCount(0); //Erase old rows
        
        if (cId.isEmpty()) {
            lblNameVal.setText("");
            return;
        }

        String name = OrderController.getCustomerNameById(cId);
        if (name != null) {
            lblNameVal.setText(name);
            Order[] orders = OrderController.getOrderByCustomer(cId);
            if (orders != null && orders.length > 0) {
                for (Order o : orders) {
                    tableModel.addRow(new Object[]{
                        o.getOrderId(),
                        o.getBurgerQty(),
                        String.format("%.2f", o.getTotalValue())
                    });
                }
            }
        } else {
            lblNameVal.setText("Customer not found!");
        }
    }
}
    
    
