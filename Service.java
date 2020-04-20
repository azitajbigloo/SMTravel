package SMTravelSimulation;
import java.util.ArrayList;


import simulationModelling.ConditionalActivity;
/*
 * Conditional Activity Service 
 */
public class Service extends ConditionalActivity{

	   SMTravel model;
	   private Call icCall;
	   private int operatorType;
	   private int callType;
	   
	   //Constructor
	   public Service(SMTravel model, int operatorType, int callType){
	     this.model = model;
	     this.operatorType = operatorType;
	     this.callType = callType;
	   }
	   // IF THE CanService return True the service start.
	   protected static boolean precondition(SMTravel model)
	   {
	      boolean returnValue = false;
	      if(model.udp.GetOperatorToServe() != -1 && model.udp.GetCallToServe() != -1)
	         return true ;
	      return returnValue;
	   };
	   
	   public void startingEvent(){
		  model.udp.UpdateWaitCallsOutput(icCall);
		  operatorType = model.udp.GetOperatorToServe();
		  callType = model.udp.GetCallToServe();
		  icCall = model.qCallLine.get(callType).remove(0);
	      model.rgOperator[operatorType].insertGrp(icCall);
	      model.rgOperator[operatorType].numBusy++;
	   }
	   protected double duration(){
	        return (model.rvp.uSrvTm(icCall.uType,operatorType));//Call attrubute need to change based on the type
	    }
	   protected void terminatingEvent(){
	        model.rgTrunkLine.removeGrp(icCall);

	        //model.rgTrunkLine.numTrunkLineInUse--; no attribute for this in use trunklines.//
	        AfterCall afterCall = new AfterCall(model, operatorType, icCall);//
	        model.spStart(afterCall);
	   }}
