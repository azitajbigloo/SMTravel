package smTravel;

import smTravel.Operator.*;
import simulationModelling.ScheduledAction;

public class Initialise {
	smTeravel model;
	
	// Constructor
	protected Initialise(smTeravel model) { this.model = model; }
	
	double [] ts = { 0.0, -1.0 }; // -1.0 ends scheduling
	int tsix = 0;  // set index to first entry.
	protected double timeSequence() {
		return ts[tsix++];  // only invoked at t=0
	}
	
	public void actionEvent() {
		// System Initialisation
		int qCallLine[GOLD] = 0;
		int qCallLine[SILVER] = 0;
		int qCallLine[REGULAR] = 0;
	
	
		int rgOperator[GOLD] = 0;
		int rgOperator[SILVER] = 0;
		int rgOperator[REGULAR] = 0;
	
		int rgTrunkLine.n = 0;
		
		// Initialise the output variables
		int numGoldCalls = 0;
		int numSilverCalls = 0;
		int numRegularCalls  = 0;
		int num90SecGoldCalls = 0;
		int num180SecSilverCalls = 0;
		int num900SecRegularCalls = 0;
		int numBusyCrdHCalls = 0;
		int numBusyRegualarCalls = 0;
		
		
	}
	
	
	
}