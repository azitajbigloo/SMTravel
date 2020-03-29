package SMTravelSimulation;
import simulationModelling.ScheduledAction;
/*
 * Scheduled Action CardHolderCallArrival 
 */
public class CardHolderCallArrival extends ScheduledAction {
	
	    SMTravel model; 	// Access to the SMTravel model
	    
	    // Constructor 
	    CardHolderCallArrival(SMTravel model) { this.model = model;}

	    @Override
	    public double timeSequence() {
	        return (model.rvp.duCallCrd());
	    }

	    @Override
	    protected void actionEvent() {
	    	// Arrival Action Sequence SCS
	    	Call icCall = model.spDerive(icCall);
	    	icCall.uCaType = model.rvp.uCaType();
	    	model.udp.UpdateNumArrivalsOutput(icCall.uCuType);
	    	//If there is an available trunkline add the Call
	    	if(model.rgTrunkLine.n < model.rgTrunkLine.numLines) {
	    		model.spInsertGrp(model.rgTrunkLine, icCall);
	    		//model.rgTrunkLine.add(icCall);
	    		InputMemberNumber inputMNum = new InputMemberNumber(model, icCall);
	    		model.spStartSequel(inputMNum);
	    	}
	    	else {
	    		// Call receives busy signal and leaves.
	    		model.udp.UpdateNumBusyOutput(icCall.uCuType);
	    		model.spLeave(icCall);
	    	}
	    }	
}

