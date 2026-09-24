import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OrdersListTableForm extends JFrame {

    private JTable tblOrders;
    private DefaultTableModel tableModel;
    private JButton btnBack;

    public OrdersListTableForm(String titleText, int orderStatus) {
        setTitle("iHungry Burger Shop - " + titleText);
        setSize(700, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header Title (Red Bar)
        JLabel lblHeader = new JLabel(titleText, SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        lblHeader.setOpaque(true);
        lblHeader.setBackground(new Color(217, 83, 79));
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setPreferredSize(new Dimension(700, 45));
        add(lblHeader, BorderLayout.NORTH);

        // Center Panel with JTable
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 20, 40));

        String[] columnNames = {"Order Id", "Customer Id", "Name", "Order QTY", "Total"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblOrders = new JTable(tableModel);
        tblOrders.setRowHeight(25);
        tblOrders.setFont(new Font("Arial", Font.PLAIN, 13));
        tblOrders.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tblOrders.getTableHeader().setBackground(new Color(220, 220, 220));

        // Center align table data
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columnNames.length; i++) {
            tblOrders.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tblOrders);
        scrollPane.getViewport().setBackground(Color.WHITE);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel (Back Button)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 40, 15));
        bottomPanel.setBackground(Color.WHITE);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(217, 83, 79));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setPreferredSize(new Dimension(100, 32));
        bottomPanel.add(btnBack);

        add(bottomPanel, BorderLayout.SOUTH);

        // Load Data based on status
        loadOrders(orderStatus);

        // Back Action -> View Orders Menu 
        btnBack.addActionListener(e -> {
            new ViewOrdersMenuForm().setVisible(true);
            this.dispose();
        });
    }

    private void loadOrders(int status) {
        Order[] orders = OrderController.getOrdersByStatus(status);
        tableModel.setRowCount(0);
        if (orders != null && orders.length > 0) {
            for (Order o : orders) {
                tableModel.addRow(new Object[]{
                        o.getOrderId(),
                        o.getCustomerId(),
                        o.getCustomerName(),
                        o.getBurgerQty(),
                        String.format("%.2f", o.getTotalValue())
                });
            }
        }
    }
}
