class OrderList {
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
		if (size == capacity) {
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
	
	//size eka private nisa apita ekapara ganna ba, A nisa getter method ekak oana wenawa 
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
