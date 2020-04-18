package SMTravelSimulation;
import java.awt.List;

import simulationModelling.ConditionalActivity;
/*
 * Conditional Activity Service 
 */
public class Service extends ConditionalActivity{

	   SMTravel model;
	   private Call icCall;
	   private int callType;
	   private int operatorType;
	   
	   //Constructor
	   public Service(SMTravel model, int callType, int operatorType){
	     this.model = model;
	     this.callType = callType;
	     this.operatorType = operatorType;
	   }
	   
	   protected static boolean precondition(SMTravel model)
	   {
	      boolean returnValue = false;
	      if(GetIDsToStartService()!= null)
	         returnValue = true;
	         
	        return(returnValue);
	   }
	   public void startingEvent(){
	      <queueID,operatorID> = GetIDsToStartService(); //what?
	      icCall = model.spRemoveQueue(model.qCallLine(queueID)); //what is queueID?
	      model.rgOperators[operatorType].numBusy++;
	   }
	   protected double duration(){
	        return (model.RVP.uSrvTm(operatorType));
	    }
	   protected void terminatingEvent(){
	        model.SP.Leave(icCall);
	        model.SP.StartSequel(AfterCall, operatorType, icCall.uTypeService);
	    }
	   
	public List GetIDsToStartService() {
		int queueID = -1;
		if( model.qCallLine[Constants.GOLD].n > 0)
			
			
		
		return null;
		
		
	}
}
