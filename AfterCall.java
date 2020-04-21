package SMTravelSimulation;
import simulationModelling.SequelActivity;
/*
 * Sequel Activity AfterCall, represents the operator doing the after call work.
 */
public class AfterCall extends SequelActivity{
		
	SMTravel model;		// Access to the SMTravel model
	private Call icCall;
	//private int operatorID;  //do not need operatorID here  - hossein
	private int operatorType;
	   
	// Constructor
	AfterCall(SMTravel model, Call icCall, int operatorType) {
		this.model = model;
		this.icCall = icCall;
		//this.operatorID = operatorID;  //do not need operatorID here
		this.operatorType = operatorType;  
	}


	public void startingEvent(){
	    //No starting event
	}
		   
	protected double duration(){
		return model.rvp.uAftCallWrkTm(icCall.uCaType, operatorType);
	}
		   
	protected void terminatingEvent(){
		model.rgOperator[operatorType].numBusy -= 1;	//operatorID changed to operatorType  - hossein
		
		        
	}
}


 

