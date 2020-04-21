// File: Experiment.java
// Description:

import SMTravelSimulation.SMTravel;
import cern.jet.random.engine.*;

// Main Method: Experiments
// 
class Experiment
{
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
          SMT1 = new SMTravel(startTime,endTime,schedule[0],55,15,sds[i]);
          mname.runSimulation();
          // See examples for hints on collecting output
          // and developping code for analysis
       }
   }
}
