
package simModel;
import cern.jet.random.Exponential;
import cern.jet.random.engine.MersenneTwister;
import cern.jet.random.Uniform;
import dataModelling.TriangularVariate;

class RVPs
{
    SMTravel model; // for accessing the clock
    // Data Models - i.e. random variate generators for distributions
    // are created using Colt classes, define
    // reference variables here and create the objects in the
    // constructor with seeds
    /* Internal Data Models */
    
    private Exponential[] dmCallReg;
    private Exponential[] dmCallCrd;
    private MersenneTwister dmCuType;
    private MersenneTwister dmCaType;
    private TriangularVariate[] dmSrvTm;
    private Uniform[] dmAftCallWrkTm;
    private Uniform[] dmWaitTmTolerated;
    private Uniform[] dmIMNDuration;
    
    // Constructor
    RVPs(SMTravel model, Seeds sd)
    {
       this.model=model;
       dmCallReg = new Exponential[12];
       dmCallCrd = new Exponential[12];
       for (int i = 0; i < 12; i++){
            dmCallReg[i] = new Exponential(REGULAR_ARRIVAL_RATE[i],
                    new MersenneTwister(sd.arrRegular[i]));
            dmCallCrd[i] = new Exponential(CARDHOLDER_ARRIVAL_RATE[i],
                    new MersenneTwister(sd.arrCardholder[i]));
        }
       
        dmCaType = new MersenneTwister(sd.cardholderType)
        dmCuType = new MersenneTwister(sd.customerType)
        dmSrvTm = new TraingularVariate[3];
        dmAftCallWrkTm = new Uniform[3];
        for (int i = 0; i < 3; i++){
            dmSrvTm[i] = new TriangularVariate(
                    SERVICE_TIME[i][MIN],
                    SERVICE_TIME[i][MEAN],
                    SERVICE_TIME[i][MAX],
                    new MersenneTwister(sd.serviceTime[i])
            );
           }
            dmAftCallWrkTm[i] = new Uniform(
                    AFTER_CALL_TIME[i][MIN],
                    AFTER_CALL_TIME[i][MAX],
                    new MersenneTwister(sd.afterCallTime[i])
            );
          }
        dmWaitTmTolerated = new Uniform[2];
        for (int i = 0; i < 2; i++) {
            dmToleratedWaitTime[i] = new Uniform(
                    TOLERATED_WAIT_TIME[i][MIN],
                    TOLERATED_WAIT_TIME[i][MAX],
                    new MersenneTwister(sd.toleratedWaitTime[i])
            );
        }
        dmIMNDuration = new Uniform(
                TYPING_TIME[MIN],
                TYPING_TIME[MAX],
                new MersenneTwister(sd.typingTime));
    }
         
    // data models
    protected final static double[] REGULAR_ARRIVAL_RATE = {
            87.0  / 60.0, // 7 AM -  8 AM
            165.0 / 60.0, // 8 AM -  9 AM
            236.0 / 60.0, // 9 AM - 10 AM
            323.0 / 60.0, //10 AM - 11 AM
            277.0 / 60.0, //11 AM - 12 PM
            440.0 / 60.0, //12 PM -  1 PM
            269.0 / 60.0, // 1 PM -  2 PM
            342.0 / 60.0, // 2 PM -  3 PM
            175.0 / 60.0, // 3 PM -  4 PM
            273.0 / 60.0, // 4 PM -  5 PM
            115.0 / 60.0, // 5 PM -  6 PM
            56.0  / 60.0, // 6 PM -  7 PM
    };
       protected final static double[] CARDHOLDER_ARRIVAL_RATE = {
            89.0  / 60.0, // 7 AM -  8 AM
            243.0 / 60.0, // 8 AM -  9 AM
            221.0 / 60.0, // 9 AM - 10 AM
            180.0 / 60.0, //10 AM - 11 AM
            301.0 / 60.0, //11 AM - 12 PM
            490.0 / 60.0, //12 PM -  1 PM
            394.0 / 60.0, // 1 PM -  2 PM
            347.0 / 60.0, // 2 PM -  3 PM
            240.0 / 60.0, // 3 PM -  4 PM
            269.0 / 60.0, // 4 PM -  5 PM
            145.0 / 60.0, // 5 PM -  6 PM
            69.0  / 60.0, // 6 PM -  7 PM
    };
        protected final static double[][] SERVICE_TIME = {
            {1.2,  3.75, 2.05}, //INFORMATION
            {2.25, 8.6,  2.95}, //RESERVATION
            {1.2,  5.8,  1.9 }  //CHANGE
    };
        protected final static int MIN = 0;
        protected final static int MAX = 1;
        protected final static int MEAN = 2;
        protected final static double SILVER_OPERATOR_REDUCTION = 0.95; //95% of the time needed by a REGULAR operator
        protected final static double GOLD_OPERATOR_REDUCTION = 0.88;   //88% of the time needed by a REGULAR operator
        protected final static double[][] AFTER_CALL_TIME = {
            {0.05, 0.10}, //INFORMATION
            {0.5,  0.8 }, //RESERVATION
            {0.4,  0.6 }  //CHANGE
    };
        protected final static double[][] TOLERATED_WAIT_TIME = {
            {12.0, 30.0}, //REGULAR
            {8.0,  17.0}  //CARDHOLDER
    };
        protected final static double PROPORTION_SILVER_CARDHOLDER = 0.68;
                      //PROPORTION_GOLD_CARDHOLDER = 0.32
        protected final static double[] TYPING_TIME = {7.0 / 60.0, 16.0 / 60.0};
        
        //Random Variate Procedures//
        
        double DuCallCrd()
    {
        int time = (int)model.getClock() / 60;
        return model.getClock() + dmCallCrd[time].nextDouble();
    }


    double DuCallReg()
    {
        int time = (int) model.getClock() / 60;
        return model.getClock() + dmCallReg[time].nextDouble();
    }

    int uCuType() {
        double randNum = dmCuType.nextDouble();
        int type;
        if(randNum < PROPORTION_SILVER_CARDHOLDER){
            type = Constants.SILVER;
        } else {
            type = Constants.GOLD;
        }
        return type;
    }
    int uCaType()
    {
        double randNum = dmCaType.nextDouble();
        int callType;
        if(randNum < Constants.PROPORTION_SUBJECT[Constants.INFORMATION]){
            callSubType = Constants.INFORMATION;
        } else if (randNum < Constants.PROPORTION_SUBJECT[Constants.INFORMATION] +
                Constants.PROPORTION_SUBJECT[Constants.RESERVATION]) {
            callType = Constants.RESERVATION;
        } else {
            callType = Constants.CHANGE;
        }
        return callType;
    }
}

    double uSrvTm(int callTypeService, int operatorType){
        double serviceTime = dmSrvTm[callTypeService].next();
        if (operatorType == Constants.SILVER){
            serviceTime *= SILVER_OPERATOR_REDUCTION;
        } else if (operatorType == Constants.GOLD) {
            serviceTime *= GOLD_OPERATOR_REDUCTION;
        }
        return serviceTime;
    }

    double uAftCallWrkTm(int callSubject, int operatorType) {
        double afterCallTime = dmAftCallWrkTm[callSubject].nextDouble();
        if (operatorType == Constants.SILVER){
            afterCallTime *= Constants.SILVER_OPERATOR_REDUCTION;
        } else if (operatorType == Constants.GOLD) {
            afterCallTime *= Constants.GOLD_OPERATOR_REDUCTION;
        }
        return afterCallTime;
    }

    double uWaitTmTolerance(int callType) {
        if(callType == Constants.REGULAR) {
            return dmToleratedWaitTime[Constants.REGULAR].nextDouble();
        } else { //CARDHOLDER
            return dmToleratedWaitTime[Constants.CARDHOLDER].nextDouble();
        }
    }

    double uIMNDuration() {
        return dmIMNDuration.nextDouble();
    }
}