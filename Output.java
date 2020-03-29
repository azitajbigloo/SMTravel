package SMTravelSimulation;
/*
 * Outputs
 */
public class Output {
	
		SMTravel model;

//	      ZIBO MENG	
		
		protected Output(SMTravel md) {model = md; }
	    // Use OutputSequence class to define Trajectory and Sample Sequences
	    // Trajectory Sequences

	    // Sample Sequences

	    // DSOVs available in the OutputSequence objects
	    // If separate methods required to process Trajectory or Sample
	    // Sequences - add them here

	    // SSOVs
		int numGoldCalls; 			//The number of gold calls that call the SM Travel center.
		int num90SecGoldCalls; 		//The number of gold calls that exceeded the 90 second wait time.
		int numSilverCalls; 		//The number of silver calls that enter the SM Travel system.
		int num180SecSilverCalls; 	//The number of silver calls that exceeded the 180 second wait time.
		int numRegularCalls; 		//The number of regular calls that enter the SM Travel system.
		int num900SecRegularCalls;  //The number of regular calls that exceeded the 900 second wait time.
		
		int numBusyCrdHCalls;  		//The number of gold/silver calls that receive a busy signal.
		int numCardHArrivalCalls;  	//The number of card hold calls that arrived at the very start of the system.
		int numBusyRegularCalls; 	//The number of regular calls that receive a busy signal.
		int numRegularArrivalCalls; //The number of regular calls that arrived at the very start of the system.
		
		double perc900SecRegularCalls = numRegularCalls / num900SecRegularCalls;  	//The percentage of regular calls that exceeded the 900 second wait time.
		double perc180SecSilverCalls = num180SecSilverCalls / numSilverCalls; 		//The percentage of silver calls that exceeded the 180 second wait time.
		double perc90SecGoldCalls = num90SecGoldCalls / numGoldCalls; 				//The percentage of gold calls that exceeded the 90 second wait time.
		double percBusyCrdHCalls = numBusyCrdHCalls / (numGoldCalls+numSilverCalls);//The percentage of card hold calls that receive busy signal.
		double percBusyRegularCalls = numBusyRegularCalls / numRegularArrivalCalls; //The percentage of regular calls that receive a busy signal.
		
		//remember to design a print method in simulation part

}
