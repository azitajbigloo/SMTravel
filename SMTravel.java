package SMTravelSimulation;
import simulationModelling.AOSimulationModel;
import simulationModelling.Behaviour;
import simulationModelling.SequelActivity;
import java.util.*;

// The Simulation Model Class
public class SMTravel extends AOSimulationModel{
	
	private boolean traceFlag;
	// Constants available from Constants class
		/* Parameter */
	        

/*----------------------Entity Data Structures--------------------------*/
	/* Group and Queue Entities */
		Operator[] rgOperator = new Operator[3];
		Queue[] qCallLine = new Queue[3];
	         //HANG GONG comments: CHANGE aboeve to ArrayList<ArrayList<Call>> qCallLine = new ArrayList<ArrayList<Call>>(3);
	         // and then we use the methods like qcallline.get(id) to define the gold, silver, regular. and get the call like
	         // icCALL=qcallline.get(CONSTANTS.GOLD).remove(0): the first call to be served in each queue
		//protected ArrayList<Call> qCallLine = new ArrayList<Call>(); // Line
		TrunkLines rgTrunkLine;
		
		// Define the reference variables to the various 
		// entities with scope Set and Unary
		// Objects can be created here or in the Initialise Action

	/* Input Variables */
		// Define any Independent Input Variables here
		
		// References to RVP and UDP objects
		protected RVPs rvp;  // Reference to rvp object - object created in constructor
		protected UDPs udp = new UDPs(this);

		// Output object
		protected Output output = new Output(this);
		// Output values - define the public methods that return values
		// required for experimentation.
		
		//Constructor + Initializes the model
		public SMTravel(double t0time, double tftime, int[][] schedule, int numTrunkLine,
           int numReservedLine, Seeds sd, boolean traceFlag) {
		
			// Tracing
			 this.traceFlag = traceFlag;
			 
				
			// Initialize Parameters
			qCallLine[0] = new LinkedList<Call>();
			qCallLine[1] = new LinkedList<Call>();
			qCallLine[2] = new LinkedList<Call>();
                        // HANG GONG COMMENTS: ArrayList<ArrayList<Call>> qCallLine = new ArrayList<ArrayList<Call>>(3);
			rgTrunkLine = new TrunkLines(numTrunkLine, numReservedLine);
       
			rgOperator[Constants.REGULAR] = new Operator(schedule[Constants.REGULAR]);
			rgOperator[Constants.SILVER] = new Operator(schedule[Constants.SILVER]);
			rgOperator[Constants.GOLD] = new Operator(schedule[Constants.GOLD]);
		
			// Initialize classes
			// Create RVP object with given seed
			rvp = new RVPs(this, sd);
	
		
// rgCounter and qCustLine objects created in Initalise Action
		

			// Initialize the Simulation Model
			initAOSimulModel(t0time,tftime);
		
			// Schedule the first arrivals and employee scheduling
			Initialise init = new Initialise(this);
			scheduleAction(init);  // Should always be first one scheduled.
			StaffChange staffChange = new StaffChange(this);
			scheduleAction(staffChange);
			RegularCallArrival regularArrival = new RegularCallArrival(this);
			scheduleAction(regularArrival);
			CardHolderCallArrival cardholderArrival = new CardHolderCallArrival(this);
			scheduleAction(cardholderArrival);
	      
			// Schedule other scheduled actions and activities here

		}

		public SMTravel(double t0time, double tftime, int[][] schedule, int numTrunkLine, int numReservedLine, Seeds sd) {
			this(t0time, tftime, schedule, numTrunkLine, numReservedLine, sd, false);
		}
	
	 
	 
	//*** dont know this part ***********************************//
/***************** Implementation of Data Modules ************/
	/*
	* Testing preconditions
	*/
	protected void testPreconditions(Behaviour behObj) {
		// Reschedule scheduled actions
		reschedule(behObj);
		//boolean statusChanged = false;
		
		// Check preconditions of Conditional Activities
		if (Service.precondition(this) == true) {
			Service act = new Service(this);
			act.startingEvent();
			scheduleActivity(act);
			//statuChange = true;
		}
		// Check preconditions of Interruptions in Extended Activities
		if (EstimateWaitTime.interruptionPreCond(this) == 1) {
			EstimateWaitTime act = new EstimateWaitTime(this);
			act.startingEvent();
			scheduleActivity(act);
			//statuChange = true;
		}
		
	//	return(statuChange);

	
	}
	
	public void eventOccured()
	{
//******************************************		
		//this.showSBL();
		// Can add other debug code to monitor the status of the system
		// See examples for suggestions on setup logging

		// Setup an updateTrjSequences() method in the Output class
		// and call here if you have Trajectory Sets
		// updateTrjSequences() 
//*********************************************
		if(traceFlag) {
            System.out.printf("Clock: %-9.3f RG.TrunkLines.n: %d\n",
                    getClock(), rgTrunkLine.n);
            System.out.printf("Q.CallLine[REGULAR].n: %d Q.CallLine[SILVER].n: %d Q.CallLine[GOLD].n %d\n",
                    qCallLine[Constants.REGULAR].size(),
                    qCallLine[Constants.SILVER].size(),
                    qCallLine[Constants.GOLD].size());
            System.out.printf("RG.Operator[REGULAR].n: %d\n",
                    rgOperator[Constants.REGULAR].n);
            System.out.printf("RG.Operator[SILVER].n:  %d\n",
                    rgOperator[Constants.SILVER].n);
            System.out.printf("RG.Operator[GOLD].n:    %d\n",
                    rgOperator[Constants.GOLD].n);
            this.showSBL();
		}
	}
	
	//The percentage of regular calls that exceeded the 900 second wait time.
	public double getPerc900SecRegularCalls(){
		 return output.perc900SecRegularCalls;
	}
	//The  percentage of silver calls that exceeded the 180 second wait time.
	public double getPerc180SecSilverCalls(){
		 return output.perc180SecSilverCalls;
	}
	//The percentage of gold calls that exceeded the 90 second wait time.
	public double getPerc90SecGoldCalls(){
		return output.perc90SecGoldCalls;
	}
	//The percentage of card hold calls that receive busy signal.
	public double getPercBusyCrdHCalls(){
		return output.percBusyCrdHCalls;
	}
	//The percentage of regular calls that receive a busy signal.
	public double getPercBusyRegularCalls(){
		return output.percBusyRegularCalls;
	}
	
	
	protected double closingTime; // closing time of the call center
	
	//Termination explicit
	public boolean implicitStopCondition() {
		boolean retVal = false;
		//System.out.println("ClosingTime = " + closingTime + "currentTime = "
		//		+ getClock() + "RG.TrunkLine.n = " + rgTrunkLine.n);
		if (getClock() >= closingTime && rgTrunkLine.n == 0)
			retVal = true;
		//System.out.println("implicit stop condition returns " + retVal);

		return (retVal);
	}
	
	
	    
//************** not sure about SPs *************************************************************//
	// For implementing the group, use a HashSet object.
		//protected HashSet<TrunkLines> group = new HashSet<TrunkLines>();

	// Standard Procedure to start Sequel Activities with no parameters
	//start the sequel activity
	public void spStart(SequelActivity seqAct){
		seqAct.startingEvent();
		scheduleActivity(seqAct);
	}
		
	public Call spDerive(Call icCall) {
		 return (icCall = new Call());
	}
	
	// Insert the call into the RG.TrunkLine
	public void spInsertGrp(TrunkLines rgTrunkLine, Call icCall) {
		//rgTrunkLine.add(icCall);
		rgTrunkLine.insertGrp(icCall);	
	}
	
	// remove object from group
	public void spRemoveGrp(TrunkLines rgTrunkLine, Call icCall) {
		//rgTrunkLine.remove(icCall);
		rgTrunkLine.removeGrp(icCall);
		
	}
	// Insert object to queue
	public void spInsertQue(Queue qCallLine, Call icCall) {
	//	 qCallLine[icCall.uCuType].add(icCall);
		 qCallLine[icCall.uCuType].spInsertQue(icCall);
	}
	
	// Leave
	public void spLeave(Call icCall) {
	//	rgTrunkline = this.rgTrunkLine;
	//remove(icCall);
		rgTrunkLine.removeGrp(icCall);
		qCallLine[icCall.uCuType].spRemoveQue(icCall);
		// how??
		}
	
	// Terminate
	public void spTerminate() {
		// dont know
	}

}











