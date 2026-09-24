import javax.swing.SwingUtilities;

public class Demo {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
				new MainHomePage().setVisible(true);
		});
	}	
}

//public class Demo{
//	public static void main(String[] args){
//		new PlaceOrderForm().setVisible(true);	
//	}	
//}
