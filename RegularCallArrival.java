package SMTravelSimulation;
import simulationModelling.ScheduledAction;
/*
 * Scheduled Action RegularCallArrival 
 */
public class RegularCallArrival extends ScheduledAction{
	
	    SMTravel model;  // Access to the SMTravel model
	    
	    // Constructor 
	    RegularCallArrival(SMTravel model) { this.model = model; }

	    @Override
	    public double timeSequence(){
	        return(model.rvp.DuCallReg());
	    }

	    @Override
	    protected void actionEvent() {
			// Arrival Action Sequence SCS
	    	Call icCall = null; //    ?????
	    	icCall = model.spDerive(icCall); // ???
	    	// Call icCall = new Call();
	    	icCall.uCuType = Constants.REGULAR;   // Set Customer type to REGULAR
	    	icCall.uCaType = model.rvp.uCaType();	 
	    	model.udp.UpdateNumArrivalsOutput(icCall);
	    	//If there is an available trunk line add the Call
	    	if(model.rgTrunkLine.n < (model.rgTrunkLine.numLines - model.rgTrunkLine.numReserved)) {
	    		model.spInsertGrp(model.rgTrunkLine, icCall);
	    		//model.rgTrunkLine.add(icCall);
	    		EstimateWaitTime estWTime = new EstimateWaitTime(model, icCall); // Initiate next event
				model.spStartSequel(estWTime); 
	    	}
	    	else {
	    		// Call receives busy signal and leaves.
	    		model.udp.UpdateNumBusyOutput(icCall); 
	    		model.spLeave(icCall);
	    	}
	    }
}	

