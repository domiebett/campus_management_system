package campus;

public class App { 
	
	public static void main(String[] args) {
		
		try {
			
			GUI.createFrame();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("We have no idea what happened");
		}
		
	}
}
