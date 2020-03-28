
package simModel;

import simulationModelling.ConditionalActivity;

public class Service extends ConditionalActivity
{
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
      if(model.UDP.GetIDsToStartService()!= null)
         returnValue = true;
         
        return(returnValue);
   }
   public void startingEvent(){
      <queueID,operatorID>=model.UDP.GetIDsToStartService();
      icCall = model.SP.RemoveQue(QCallLine(queueID));
      model.rgOperators[operatorType].numBusy++;
   }
   protected double duration(){
        return (model.RVP.uSrvTm(operatorType));
    }
   protected void terminatingEvent(){
        model.SP.Leave(icCall);
        model.SP.StartSequel(AfterCall, operatorType, icCall.uTypeService);
    }
   
}