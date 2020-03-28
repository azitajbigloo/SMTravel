package smTravel;

public class UDPs {
	class UDPs {
		ModelName model;  // for accessing the clock
		
		// Constructor
		protected UDPs(ModelName model) { this.model = model; }
		
		protected Operator CanServiceCall(icCall.uCuType) {
			if (icCall.uCuType == REGULAR && rgOperator[REGULAR].n < rgOperator[REGULAR].uNumOperators)
				return operatorID = REGULAR;
			else
				return null;
			
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
