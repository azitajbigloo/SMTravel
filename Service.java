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
	   // added for the initialise action and activities in SMTravel Class
	   public Service(SMTravel model) {
		// TODO Auto-generated constructor stub
		   this.model = model;
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
	     // model.rgOperator[operatorType].insertGrp(icCall);
	      model.rgOperator[operatorType].numBusy++;
	   }
	   protected double duration(){
	        return (model.rvp.uSrvTm(icCall.uCaType, operatorType));//Call attribute need to change based on the type
	    }
	   protected void terminatingEvent(){
	        model.rgTrunkLine.removeGrp(icCall);

	        //model.rgTrunkLine.numTrunkLineInUse--; no attribute for this in use trunk lines.//
	        AfterCall afterCall = new AfterCall(model, icCall, operatorType);//
	        model.spStart(afterCall);
	   }}
