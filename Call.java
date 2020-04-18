package SMTravelSimulation;
/*
 * Call Entities Class 
 */
public class Call {
	
	//protected enum CustomerType {REGULAR, SILVER, GOLD};   //Type of customers
	//protected CustomerType uCuType;
	//protected enum CallType {RSRVN, INFO, CHNGN};   //Type of calls
	//protected CallType uCaType;
    double startWaitTime;  // Time the Call arrives 
    double waitTime; // Total wait Time of the Call
	 int uCuType;        // Type of Customer 
	 int uCaType;        // Type of Call
	
    //Constructor     
    /***** I don't think this class needs a constructor??? ***/
	public Call() {
		//int time, String cutype, String catype){
		//startWaitTime = time;
		//uCuType = cutype;
		//uCaType = catype;
	}
	/*
	 * public String toString() { return ("Call: uType = " +
	 * Constants.CALLTYPES[uCuType] + ", uSubject = " +
	 * Constants.CALL_SUBJECTS[uCaType] + ", uToleratedWaitTime = " +
	 * uToleratedWaitTime + ", startWaitTime = " + startWaitTime);
	}*/
}
