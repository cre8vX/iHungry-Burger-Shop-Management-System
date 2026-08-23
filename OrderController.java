public class OrderController {
	private static OrderList orderList = new OrderList();
	
	public static String generateOrderId() {
		return String.format("O%03d", orderList.size() + 1);	
	}	

	public static boolean placeOrder(String orderId, String customerId, String name, int qty){
		if (qty <= 0){
			return false;	
		}
		Order newOrder = new Order(orderId, customerId, name, qty, Order.PREPARING);
		orderList.add(newOrder);
		return true;
	}

	public static String generateCustomerId() {
		int maxId = 0;
		for (int i = 0; i < orderList.size(); i++) {
			String cid = orderList.get(i).getCustomerId();
			if(cid.startsWith("C") && cid.length() > 1){
				try {
					int num = Integer.parseInt(cid.substring(1));
					if(num > maxId) {
					maxId = num;		
					}	
				}catch (NumberFormatException ignored) {}	
			}		
		}	
		return String.format("C%03d", maxId + 1);
	}
	
	//Search Order by Order ID
	public static Order searchOrder(String orderId) { 
		for (int i=0; i < orderList.size(); i++) {
			if(orderList.get(i).getOrderId().equalsIgnoreCase(orderId)){
				return orderList.get(i);	
			}	
		}	
		return null;
	} 
	
	//Get Customer Name by Customer ID
	public static String getCustomerNameById(String customerId) {
		for(int i = 0; i < orderList.size(); i++) {
			if(orderList.get(i).getCustomerId().equalsIgnoreCase(customerId)) {
				return orderList.get(i).getCustomerName();	
			}	
		}	
		return null;
	}
	
	//Update Burger QTY  
	public static boolean upadateOrderQty(String orderId, int newQty) {
		Order order = searchOrder(orderId);
		if(order != null && order.getOrderStatus() == order.PREPARING) {
			order.setBurgerQty(newQty);	
			return true;
		}	
		return false;
	}
	
	//Update Order Status  
	public static boolean upadateOrderStatus(String orderId, int newStatus) {
		Order order = searchOrder(orderId);
		if (order != null && order.getOrderStatus() == Order.PREPARING) {
			order.setOrderStatus(newStatus);
			return true;	
		}	
		return false;
	}
	
	//Customer ID ekakata adala siyaluma Orders labaganeema
	public static Order[] getOrderByCustomer(String customerId) {
		int count = 0;
		for(int i = 0; i < orderList.size(); i++) {
			if(orderList.get(i).getCustomerId().equalsIgnoreCase(customerId)){
				count++;	
			}	
		}	
		Order[] customerOrders = new Order[count];
		int index = 0;
		for(int i = 0; i < orderList.size(); i++) {
			if(orderList.get(i).getCustomerId().equalsIgnoreCase(customerId)){
				customerOrders[index++] = orderList.get(i);	
			}	
		}
		return customerOrders;
	}
	
	//Status eka anuwa Orders Labaganeema
	public static Order[] getOrdersByStatus(int status) {
		int count = 0;
		for (int i=0; i < orderList.size(); i++) {
			if (orderList.get(i).getOrderStatus() == status) {
				count++;	
			}	
		}	
		Order[] filtered = new Order[count];
		int index = 0;
		for (int i = 0; i < orderList.size(); i++) {
			if (orderList.get(i).getOrderStatus() == status) {
				filtered[index++] = orderList.get(i);
			}
		}
		return filtered;
	}
	
	//Best Customers Data(Table ekata 2D String Array ekak vidiyata)
	public static String[][] getBestCustomerData() {
		int maxCust = orderList.size();
		String[] cIds = new String[maxCust];
		String[] cNames = new String[maxCust];
		double[] cTotals = new double [maxCust];	
		int uniqueCount = 0;
		
		for(int i = 0; i < orderList.size(); i++) {
			Order o = orderList.get(i);
			if(o.getOrderStatus() == Order.CANCELLED) continue;
			
			int found = -1;
			for (int j = 0; j < uniqueCount; j++) {
				if (cIds[j].equalsIgnoreCase(o.getCustomerId())) {
					found = j;
					break;	
				}	
			}
			if (found != -1) {
				cTotals[found] = cTotals[found] + o.getTotalValue();
			} else {
				cIds[uniqueCount] = o.getCustomerId();
				cNames[uniqueCount] = o.getCustomerName();
				cTotals[uniqueCount] = o.getTotalValue();
				uniqueCount++;	
			}	
		}
		
		//Bubble Sort Descending
		for (int i = 0; i < uniqueCount - 1; i++) {
			for(int j = 0; j < uniqueCount - i - 1; j++) {
				if(cTotals[j] < cTotals[j + 1]) {
					double tTotal = cTotals[j];
					cTotals[j] = cTotals[j + 1];
					cTotals[j + 1] = tTotal;

					String tId = cIds[j];
					cIds[j] = cIds[j + 1];
					cIds[j + 1] = tId;

					String tName = cNames[j];
					cNames[j] = cNames[j + 1];
					cNames[j + 1] = tName;	
				}	
			}	
		}
		
		String[][] tableData = new String[uniqueCount][3];
		for (int i = 0; i < uniqueCount; i++) {
			tableData[i][0] = cIds[i];
			tableData[i][1] = cNames[i];
			tableData[i][2] = String.format("%.2f", cTotals[i]);	
		}
		return tableData;
	}
}
