package SMTravelSimulation;
import simulationModelling.ScheduledAction;
public class RegularCallArrival extends ScheduledAction{
	
	    SMTravel model; 

	    RegularCallArrival(SMTravel model) { this.model = model; }

	    @Override
	    public double timeSequence()
	    {
	        return(model.rvp.duCallReg());
	    }

	    @Override
	    protected void actionEvent() {
	      
			// Arrival Action Sequence SCS
	    	Call icCall = model.spDerive(icCall);
	    	// Call icCall = new Call();
	    	icCall.uCuType = Constants.REGULAR;
	    	icCall.uCaType = model.rvp.uCaType();	 
	    	model.udp.UpdateNumArrivalsOtput(icCall.uCuType);
	    	if(TrunkLine.n < (Trunkline.numLines - TrunkLine.numReserved)) {
	    		model.spInsertGrp( rgTrunkLine, icCall);
	    		//model.rgTrunkLine.add(icCall);
	    		model.spStartSequel(EstimateWaitTime(icCall)); // deleted icCall from the parameters, but it should be in the CM
	    		
	    	}
	    	else {
	    		model.udp.UpdateNumBusySOutput(icCall.uCuType);
	    		model.spLeave(icCall);
	    	}
	    }

	}	

