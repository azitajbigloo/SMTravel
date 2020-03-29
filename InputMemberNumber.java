package SMTravelSimulation;
import simulationModelling.SequelActivity;
public class InputMemberNumber extends SequelActivity{
//This activity represents the Call entity entering his member number.
	
	    SMTravel model;
	    private Call icCall;
	    
	    InputMemberNumber(SMTravel model, Call icCall) {
	        this.model = model;
	        this.icCall = icCall;
	    }

	   
	    public void startingEvent(){
	        icCall.uCuType = model.rvp.uCuType();
	    }
	   
	    protected double duration(){
	        return model.rvp.uIMNDuration();
	    }
	   
	    protected void terminatingEvent(){
	        model.spStartSequel(EstimateWaitTime(icCall));
	    }
	}


