// File: Experiment.java
// Description:

import SMTravelSimulation.SMTravel;
import cern.jet.random.engine.*;

// Main Method: Experiments
// 
class Experiment
{
   static int [] [] schedule =
   {
	{ ？, ？, ？, ？}, 
	{ ？, ？, ？, ？}, 
	{ ？, ？, ？, ？},    
   };
   public static void main(String[] args)
   {
       int i, NUMRUNS = 20; 
       double startTime=0.0, endTime=43200.0;
       Seeds[] sds = new Seeds[NUMRUNS];
       SMTravel SMT1;  // Simulation object

       // Lets get a set of uncorrelated seeds
       RandomSeedGenerator rsg = new RandomSeedGenerator();
       for(i=0 ; i<NUMRUNS ; i++) sds[i] = new Seeds(rsg);
       
       // Loop for NUMRUN simulation runs for each case
       // Case 1
       System.out.println(" Case 1");
       for(i=0 ; i < NUMRUNS ; i++)
       {
          SMT1 = new SMTravel(startTime,endTime,schedule,55,15,sds[i],ture);
          SMT1.runSimulation();
          System.out.println("Perc900SecRegularCalls:"+SMT1.getPerc900SecRegularCalls())
          System.out.println("Perc180SecSilverCalls:"+SMT1.getPerc180SecSilverCalls())
          System.out.println("Perc90SecGoldCalls:"+SMT1.getPerc90SecGoldCalls())
          System.out.println("PercBusyCrdHCalls:"+SMT1.getPercBusyCrdHCalls())
          System.out.println("PercBusyRegularCalls:"+SMT1.getPercBusyRegularCalls())
          // See examples for hints on collecting output
          // and developping code for analysis
       }
   }
}
