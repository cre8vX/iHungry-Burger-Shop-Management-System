public class Order {
	public static final double BURGER_PRICE = 500.00;
	
	public static final int PREPARING = 0;
	public static final int DELIVERED = 1;
	public static final int CANCELLED = 2;
	
	private String orderId;
	private String customerId;		
	private String customerName;		
	private int burgerQty;		
	private double totalValue;		
	private int orderStatus;	
	
	public Order(String orderId, String customerId, String customerName, int burgerQty, int orderStatus) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.burgerQty = burgerQty;
		this.totalValue = burgerQty * BURGER_PRICE;
		this.orderStatus = orderStatus;	
	}
	
	public String getOrderId() {return orderId;}
	public String getCustomerId() {return customerId;}
	public String getCustomerName() {return customerName;}
	public int getBurgerQty() {return burgerQty;}
	public double getTotalValue() {return totalValue;}
	public int getOrderStatus() {return orderStatus;} 
	
	public String getOrderStatusText() {
		switch (orderStatus) {
			case PREPARING: return "PREPARING";	
			case DELIVERED: return "PREPARING";	
			case CANCELLED: return "PREPARING";
			default: return "UNKNOWN";		
		}	
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;	
	}
	
	public void setBurgerQty(int burgerQty) {
		this.burgerQty = burgerQty;
		this.totalValue = burgerQty * BURGER_PRICE;		
	}
	
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;	
	}
}

//02--------------------------------------------
public class OrderList {
	private Order[] orderArray;
	private int size;
	private int capacity;
	
	public OrderList(int capacity) {
		this.capacity = capacity;
		this.orderArray = new Order[capacity];
		this.size = 0;	
	}	
	
	public OrderList() {
		this(10);	
	}
	
	private void extendArray() {
		Order[] temp = new Order[capacity * 2];	
		for(int i = 0; i < size; i++) {
			temp[i] = orderArray[i];	
		}
		orderArray = temp;
		capacity = capacity * 2;
	}
	
	public void add(Order order) {
		if (size == capacity {
			extendArray();	
		}
		orderArray[size++] = order;
	}
	
	public Order get(int index) {
		if (index >= 0 && index < size) {
			return orderArray[index];	
		}	
		return null;
	}
	
	public int size() {
		return size;	
	}
	
	public Order[] toArray() {
		Order[] temp = new Order[size];
		for (int i = 0; i < size; i++) {
			temp[i] = orderArray[i];	
		}	
		return temp;
	}	
}

//03-------------------
public class OrderController {
	private static OrderList orderList = new OrderList();
	
	public static String generateOrderId() {
		return String.format("0%03d", orderList.size() + 1);	
	}	
	
	public static String generateCustomerId() {
		int maxId = 0;
		for (int i = 0; i < orderList.size(); i++) {
			String cid = orederList.get(i).getCustomerId();
			if(cid.startsWith("C") && cid.length() > 1) {
				try {
					int num = Integer.parseInt(cid.substring(1));
					if (num > maxId) {
						maxId = num;	
					}	
				}catch (NumberFormatException ignored) {}	
			}	
		}
		return String.format("C%03d", maxId + 1);
	} 
}
