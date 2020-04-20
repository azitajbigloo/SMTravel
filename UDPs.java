package SMTravelSimulation;

public class UDPs {

	
		SMTravel model;  // for accessing the clock
		
		// Constructor
		protected UDPs(SMTravel model) { this.model = model; }
		
		protected int CanServiceCall(int uCuType) {
			//icCall.uCuType = this.icCall.uCuType;
			int operatorID = -1;
			if ((uCuType.uCuType == Constants.REGULAR) && (model.rgOperator[Constants.REGULAR].n < model.rgOperator[Constants.REGULAR].uNumOperator))
				 operatorID = Constants.REGULAR;
			
			//// azita - edit
			
			if (uCuType.uCuType == SILVER && rgOperator[SILVER].n < RG.Operator[SILVER].uNumOperators )
				return operatorID = SILVER;
			else if (uCuType.uCuType == SILVER && rgOperator[REGULAR].n < rgOperator[REGULAR].uNumOperators)
				return operatorID == REGULAR;
			else
				return null;
			
			if (uCuType.uCuType == GOLD && rgOperator[GOLD].n < rgOperator[GOLD].uNumOperators)
				return operatorID = GOLD;	
			else if (uCuType.uCuType = GOLD && rgOperator[SILVER].n < RG.Operator[SILVER].uNumOperators)
				return operatorID = SILVER;
			else if (uCuType.uCuType = GOLD && rgOperator[REGULAR].n < rgOperator[REGULAR].uNumOperators)
				return operatorID = REGULAR;
			else 
				return null;
		}
	        //Hang Gong edited for Get OperatorId
		protected int GetOperatorToServe(){
			int operatorId=-1;
			 for (int opType = 2; opType >= 0; opType--) { //Prioritize GOLD operators
		            if(model.rgOperator[opType].n < model.rgOperator[opType].uNumOperators) {
		                if (!model.qCallLine.get(Constants.GOLD).isEmpty()){
		                    operatorId = opType;
		                    break;
		                } else if ((opType == Constants.REGULAR || opType == Constants.SILVER)
		                        && !model.qCallLine.get(Constants.SILVER).isEmpty()) {
		                    operatorId = opType;
		                    break;
		                } else if (opType == Constants.REGULAR
		                        && !model.qCallLine.get(Constants.REGULAR).isEmpty()) {
		                    operatorId = opType;
		                    break;
		                }
		            }
		        }
			 return operatorId;
			
		}
		
		// Hang Gong edited for Get CallId//
		protected int GetCallToServe(){
			int CallId=-1;
			 for (int caType = 2; caType >= 0; caType--) { //Prioritize GOLD operators
		            if(model.rgOperator[caType].n < model.rgOperator[caType].uNumOperators) {
		                if (!model.qCallLine.get(Constants.GOLD).isEmpty()){
		                    CallId = caType;
		                    break;
		                } else if ((caType == Constants.REGULAR || caType == Constants.SILVER)
		                        && !model.qCallLine.get(Constants.SILVER).isEmpty()) {
		                    CallId = caType;
		                    break;
		                } else if (caType == Constants.REGULAR
		                        && !model.qCallLine.get(Constants.REGULAR).isEmpty()) {
		                    CallId = caType;
		                    break;
		                }
		            }
		        }
			 return CallId;
		}
                //Hang Gong edited for UPdateWaitCallsOutput Call for service//
		protected void UpdateWaitCallsOutput(Call icCall){
			 icCall.waitTime = model.getClock() - icCall.startWaitTime;
			 if(icCall.uType == Constants.REGULAR){
				 model.output.numRegularCalls ++;
				 if(icCall.waitTime > 900 )
					 model.output.num900SecRegularCalls ++;
				 model.output.perc900SecRegularCalls = model.output.num900SecRegularCalls/model.output.numRegularCalls;
			 }
			 else if(icCall.uType==Constants.SILVER){
				 model.output.numSilverCalls ++;
				 if(icCall.waitTime > 180)
					 model.output.num180SecSilverCalls ++;
				 model.output.perc180SecSilverCalls = model.output.num180SecSilverCalls/model.output.numSilverCalls;
			 }
			 else if(icCall.uType == Constants.GOLD){
				 model.output.numGoldCalls ++;
				 if(icCall.waitTime > 90)
					 model.output.num90SecGoldCalls ++;
				 model.output.perc90SecGoldCalls = model.output.num90SecGoldCalls/model.output.numGoldCalls;
			 }
		}
	
		protected void UpdateNumArrivalsOutput(Call icCall) {
			
			if(icCall.uCuType ==  Constants.REGULAR) { 
				model.output.numRegularArrivalCalls ++;
				model.output.percBusyRegularCalls = model.output.numBusyRegularCalls / model.output.numRegularArrivalCalls;
			}
			else {
				model.output.numCardHArrivalCalls ++;
				model.output.percBusyCrdHCalls = model.output.numBusyCrdHCalls / model.output.numCardHArrivalCalls;
			}
		}
		
		protected void UpdateNumBusyOutput(Call icCall) {
			if(icCall.uCuType ==  Constants.REGULAR) { 
				model.output.numBusyRegularCalls ++;
				model.output.percBusyRegularCalls = model.output.numBusyRegularCalls / model.output.numRegularArrivalCalls;
						
			}
			else {
				model.output.numBusyCrdHCalls ++;
				model.output.percBusyCrdHCalls = model.output.numBusyCrdHCalls / model.output.numCardHArrivalCalls;
			}
		}
}




