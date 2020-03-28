package smTravel;

import java.util.ArrayList;
import java.util.HashSet;

public class Operator {
	
	protected enum operatorType {REGULAR, SILVER, GOLD}; //type of operators
	protected operatorType uType;
	
	private int uShift; //indicates the number of operators starting at each shift time
	private ArrayList<Operator> list = new ArrayList<Operator>();
	private int n; //The number of entities in the list (maximum values is numOperators)
	private int numOperator; //total number of operators within a day
	private int numBusy; //The assigned value represents the number of operators that are busy
	
	
}
	