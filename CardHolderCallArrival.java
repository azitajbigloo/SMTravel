import simulationModelling.ScheduledAction;
public class CardHolderCallArrival extends ScheduledAction {
	
	    SMTravel model; 
	    
	    // constructor 
	    CardHolderCallArrival(SMTravel model) {
	        this.model = model;
	    }

	  
	    public double timeSequence() {
	        return (model.rvp.duCallCrd());
	    }

	   
	    protected void actionEvent() {
	    	 // Arrival Action Sequence SCS
	    	icCall = model.spDerive(Call);
	    	icCall.uCaType = model.rvp.uCaType();
	    	model.udp.UpdateNumArrivalsOutput(Constants.SILVER);
	    	if(TrunkLine.n < TrunkLine.numLines) {
	    		model.spInsertGrp(rgTrunkLine, icCall);
	    		model.spStartSequel(InputMemberNumber, icCall);
	    		
	    	}
	    	else {
	    		model.udp.UpdateNumBusySOutput(icCall.uCuType);
	    		model.spLeave(icCall);
	    	}
	    }
	}

