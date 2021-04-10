import javax.swing.JOptionPane;

/**
 * This Class contains the main method as it runs the whole program
 */
public class runner {
	
	public static void main(String [] args) throws OverFlowException, UnderFlowException
	{
		
		 DequeCyclic d = new DequeCyclic(5);
		 d.pushRight(2);
		 //d.print();
		 d.pushRight(2);
		 //d.print();
		 d.pushLeft(1);
		 //d.print();
		 //d.popRight();
		 //d.pushLeft(1);
		 d.popRight();
		 //d.print();
		 //d.pushLeft(1);
		 //d.pushLeft(1);
		 //d.popRight();
		 d.popLeft();
		 //d.print();
		 d.popLeft();
		 //d.print();
		 //d.popLeft();
		 d.pushLeft(5);
		 //d.print();
		 d.pushLeft(5);
		 //d.peekRight();
		 //d.pushRight(2);
		 d.popRight();
		 d.pushRight(5);
		 d.pushRight(5);
		 d.pushLeft(8);
		 d.popRight();
		 		
		
	}
}
