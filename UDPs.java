package SMTravelSimulation;
//edited
public class UDPs {

	
		SMTravel model;  // for accessing the clock
		
		// Constructor
		protected UDPs(SMTravel model) { this.model = model; }
		
		protected int CanServiceCall(Call icCall) {
			//icCall.uCuType = this.icCall.uCuType;
			int operatorID = -1;
			if ((icCall.uCuType == Constants.REGULAR) && (model.rgOperator[Constants.REGULAR].n < model.rgOperator[Constants.REGULAR].uNumOperator))
				 operatorID = Constants.REGULAR;
			
			//// azita - edit
			
			if (icCall.uCuType == SILVER && rgOperator[SILVER].n < RG.Operator[SILVER].uNumOperators )
				return operatorID = SILVER;
			else if (icCall.uCuType == SILVER && rgOperator[REGULAR].n < rgOperator[REGULAR].uNumOperators)
				return operatorID == REGULAR;
			else
				return null;
			
			if (icCall.uCuType == GOLD && rgOperator[GOLD].n < rgOperator[GOLD].uNumOperators)
				return operatorID = GOLD;	
			else if (icCall.uCuType = GOLD && rgOperator[SILVER].n < RG.Operator[SILVER].uNumOperators)
				return operatorID = SILVER;
			else if (icCall.uCuType = GOLD && rgOperator[REGULAR].n < rgOperator[REGULAR].uNumOperators)
				return operatorID = REGULAR;
			else 
				return null;
		}

			
}
