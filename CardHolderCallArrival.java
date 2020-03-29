package SMTravelSimulation;
import simulationModelling.ScheduledAction;
public class CardHolderCallArrival extends ScheduledAction {
	
	    SMTravel model; 
	    
	    // constructor 
	    CardHolderCallArrival(SMTravel model) {
	        this.model = model;
	    }

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
	    	if(TrunkLine.n < TrunkLine.numLines) {
	    		model.spInsertGrp(rgTrunkLine, icCall);
	    		//model.rgTrunkLine.add(icCall);
	    		model.spStartSequel(InputMemberNumber(icCall));// deleted icCall from the parameters, but it should be in the CM
	    		
	    	}
	    	else {
	    		model.udp.UpdateNumBusySOutput(icCall.uCuType);
	    		model.spLeave(icCall);
	    		
	    	}
	    }
	}

