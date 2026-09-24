import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SearchBestCustomerForm extends JFrame {
	private JTable tblBestCustomers;
    private DefaultTableModel tableModel;
    private JButton btnBack;	
    
    public SearchBestCustomerForm() {
		setTitle("iHungry Burger Shop - Search Best Customer");
        setSize(650, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Header Title
        JLabel lblHeader = new JLabel("Search Best Customer", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 20));
        lblHeader.setOpaque(true);
        lblHeader.setBackground(new Color(217, 83, 79));
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setPreferredSize(new Dimension(650, 50));
        add(lblHeader, BorderLayout.NORTH);
        
        // Center Panel with Table
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        centerPanel.setBackground(Color.WHITE);
        
        // Table Columns
        String[] columnNames = {"Customer ID", "Name", "Total"};
        
        // Non-editable Table Model
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        	
        tblBestCustomers = new JTable(tableModel);
        tblBestCustomers.setRowHeight(28);
        tblBestCustomers.setFont(new Font("Arial", Font.PLAIN, 14));
        tblBestCustomers.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tblBestCustomers.getTableHeader().setBackground(new Color(240, 240, 240));
        
        // Center Align Data in Table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columnNames.length; i++) {
            tblBestCustomers.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tblBestCustomers);
        scrollPane.getViewport().setBackground(Color.WHITE);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
        
        // Bottom Panel (Back Button)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 15));
        bottomPanel.setBackground(Color.WHITE);

        btnBack = new JButton("Back to Home");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(217, 83, 79));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setPreferredSize(new Dimension(150, 35));
        bottomPanel.add(btnBack);

        add(bottomPanel, BorderLayout.SOUTH);

        // Load Data from Controller
        loadBestCustomerData();

        // Back Action
        btnBack.addActionListener(e -> {
            new MainHomePage().setVisible(true);
            this.dispose();
        });
    }
    private void loadBestCustomerData() {
        String[][] data = OrderController.getBestCustomerData();
        tableModel.setRowCount(0); //Clear Table
        
        if (data != null && data.length > 0) {
            for (String[] row : data) {
                tableModel.addRow(row);
            }
        }     
	}
}


