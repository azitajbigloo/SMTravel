import simulationModelling.ScheduledAction;
public class RegularCallArrival extends ScheduledAction{
	
	    SMTravel model; 

	    RegularCallArrival(SMTravel model) { this.model = model; }

	   
	    public double timeSequence()
	    {
	        return(model.rvp.duCallReg());
	    }

	  
	    protected void actionEvent() {
	        // Arrival Action Sequence SCS
	    	icCall = model.spDerive(Call);
	    	icCall.uCuType = Constants.REGULAR;
	    	icCall.uCaType = model.rvp.uCaType();	 
	    	model.udp.UpdateNumArrivalsOtput(icCall.uCuType);
	    	if(TrunkLine.n < (Trunkline.numLines - TrunkLine.numReserved)) {
	    		model.spInsertGrp( rgTrunkLine, icCall);
	    		model.spStartSequel(EstimateWaitTime, icCall);
	    	}
	    	else {
	    		model.udp.UpdateNumBusySOutput(icCall.uCuType);
	    		model.spLeave(icCall);
	    	}
	    }

	}	

