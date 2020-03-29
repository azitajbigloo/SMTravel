package SMTravelSimulation;
import simulationModelling.AOSimulationModel;
import simulationModelling.Behaviour;
import simulationModelling.SequelActivity;
import java.util.*;

import java.util.HashSet;


// The Simulation Model Class
public class SMTravel extends AOSimulationModel{
	// Constants available from Constants class
		/* Parameter */
	        // Define the parameters

		/*-------------Entity Data Structures-------------------*/
		/* Group and Queue Entities */
	//Trunkline rgTrunkLine = new TrunkLine;
	Operator[] rgOperator = new Operator[3];
	Queue[] qCallLine = new Queue[3];
	TrunkLines rgTrunkLine;
		// Define the reference variables to the various 
		// entities with scope Set and Unary
		// Objects can be created here or in the Initialise Action

		/* Input Variables */
		// Define any Independent Input Varaibles here
		
		// References to RVP and DVP objects
		protected RVPs rvp;  // Reference to rvp object - object created in constructor
		protected DVPs dvp = new DVPs(this);  // Reference to dvp object
		protected UDPs udp = new UDPs(this);

		// Output object
		protected Output output = new Output(this);
		// Output values - define the public methods that return values
		// required for experimentation.
		
	//Constructor + Initializes the model
	public SMTravel(double t0time, double tftime, int[][] uShift, int numTrunkLine,
            int numReservedLine, Seeds sd, boolean traceFlag)) {
		
		// Tracing

		
		// Initialize Parameters
		
		
         qCallLine[0] = new LinkedList<Call>();
         qCallLine[1] = new LinkedList<Call>();
         qCallLine[2] = new LinkedList<Call>();

         rgTrunkLines = new TrunkLines(numTrunkLine, numReservedLine);

                
		 rgOperator[Constants.REGULAR] = new Operator(uShift[Constants.REGULAR]);
         rgOperator[Constants.SILVER] = new Operator(uShift[Constants.SILVER]);
         rgOperator[Constants.GOLD] = new Operator(uShift[Constants.GOLD]);
		
     // Initialize classes
		// Create RVP object with given seed
		rvp = new RVPs(sd);
	
		
	// rgCounter and qCustLine objects created in Initalise Action
		

	// Initialize the Simulation Model
		initAOSimulModel(t0time,tftime);
		
	// Schedule the first arrivals and employee scheduling
	    Initialise init = new Initialise(this);
	    scheduleAction(init);  // Should always be first one scheduled.
	    RegularCallArrival regularArrival = new RegularCallArrival(this);
	    scheduleAction(regularArrival);
	    CardHolderCallArrival cardholderArrival = new CardHolderCallArrival(this);
	    scheduleAction(cardholderArrival);
	      
	   // Schedule other scheduled actions and acitvities here

	        this.traceFlag = traceFlag;
	}
	
	// Initialize static components of model classes
	void initializeClasses (Seeds sd) {
		// Add reference to standard classes
		Initialise.model = this;
		Output.model = this;
		RVps.model = this;
		
		// Add reference to activity and action classes
		
		// Initialize RVPs in the classes
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
		// Check preconditions of Conditional Activities

		// Check preconditions of Interruptions in Extended Activities
	}
	public void eventOccured()
	{
		//this.showSBL();
		// Can add other debug code to monitor the status of the system
		// See examples for suggestions on setup logging

		// Setup an updateTrjSequences() method in the Output class
		// and call here if you have Trajectory Sets
		// updateTrjSequences() 
	}
	
	// Standard Procedure to start Sequel Activities with no parameters
		protected void spStart(SequelActivity seqAct)
		{
			seqAct.startingEvent();
			scheduleActivity(seqAct);
		}	
	protected double closingTime; // closing time of the call center
	
	
	
	//Termination explicit
	public boolean implicitStopCondition() {
		// ?????
	}
	
	
	// Flag for controlling tracing
	//....

	
    // Standard Procedure to start Sequel Activities with no parameters
	
	//start the sequel activity
	void spStartSequel(SequelActivity seqAct){
	        seqAct.startingEvent();
	        scheduleActivity(seqAct);
	        }
	    
	//*** not sure about SPs ***********************************//
	// For implementing the group, use a HashSet object.
		protected HashSet<TrunkLines> group = new HashSet<TrunkLines>();
	public Call spDerive(Call icCall) {
		 return (icCall = new Call());
		
	}
	// Insert the call into the RG.TrunkLine
	void spInsertGrp(TrunkLines rgTrunkLine, Call icCall) {
		rgTrunkLine.add(icCall);
		
	}
	
	// remove 
	void spRemoveGrp(TrunkLines rgTrunkLine, Call icCall) {
		rgTrunkLine.remove(icCall);
		
	}
	void spInsertQue(Queue qCallLine, Call icCall) {
		 qCallLine[icCall.uCuType].add(icCall);
	}
	
	void spLeave(Call icCall) {
		remove(icCall);
		// how??
	}
	void spTerminate() {
		// dont know
	}

	
	
	
	
}











