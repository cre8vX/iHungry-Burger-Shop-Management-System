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
			case DELIVERED: return "DELIVERED";	
			case CANCELLED: return "CANCELLED";
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
