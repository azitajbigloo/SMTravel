package simModel;

class Output 
{
//	SMTravel model;
//	
//	protected Output(ModelName md) {model = md; }
    // Use OutputSequence class to define Trajectory and Sample Sequences
    // Trajectory Sequences

    // Sample Sequences

    // DSOVs available in the OutputSequence objects
    // If seperate methods required to process Trajectory or Sample
    // Sequences - add them here

    // SSOVs
	int numGoldCalls;
	int num90SecGoldCalls;
	int numSilverCalls;
	int num180SecSilverCalls;
	int numRegularCalls;
	int num900SecRegularCalls;
	
	int numBusyCrdHCalls;
	int numCardHArrivalCalls;
	int numBusyRegularCalls;
	int numRegularArrivalCalls;
	
	double perc900SecRegularCalls=numRegularCalls/num900SecRegularCalls;
	double perc180SecSilverCalls=num180SecSilverCalls/numSilverCalls;
	double perc90SecGoldCalls=num90SecGoldCalls/numGoldCalls;
	double percBusyCrdHCalls=numBusyCrdHCalls/(numGoldCalls+numSilverCalls);
	double percBusyRegularCalls=numBusyRegularCalls/numRegularArrivalCalls;
	
	//remeber to design a print method in simulation part
}
