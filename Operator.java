package SMTravelSimulation;
import java.util.ArrayList;
import java.util.HashSet;
/* 
 *  RG.Operator[3] Entity Category    
 */
public class Operator {
	
		int[] uShift; 	//The number of operators starting at each shift time
		Operator(int[] uShift){
			this.uShift = uShift; 
		}
	/** shouldn't this be in Constants class??*/
		protected enum operatorType {REGULAR, SILVER, GOLD};   //Type of operators
		protected operatorType uType;
	
		//private int uShift; 	//The number of operators starting at each shift time
		ArrayList<Operator> list = new ArrayList<Operator>();  	// List of operator objects that can service a call
		int n; 	//The number of entities in the list (maximum values is uNumOperators)
		int uNumOperator; 	//The total number of operators within a day
		int numBusy; 	//The assigned value represents the number of operators that are busy

		
		

		    
}
