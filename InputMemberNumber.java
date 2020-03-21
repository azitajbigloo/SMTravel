import simulationModelling.SequelActivity;
public class InputMemberNumber extends SequelActivity{
//This activity represents the Call entity entering his member number.
	
	    SMTravel model;
	    private Call call;
	    EnterCardNumber(SMTravel model, Call call) {
	        this.model = model;
	        this.call = call;
	    }

	   
	    public void startingEvent(){
	        icCall.uCuType = model.rvp.uCuType();
	    }
	   
	    protected double duration(){
	        return model.rvp.uIMNDuration();
	    }
	   
	    protected void terminatingEvent(){
	        model.spStartSequel(EstimateWaitTime);
	    }
	}


