package SMTravelSimulation;
import java.util.ArrayList;
import java.util.HashSet;
/* 
 *  RG.TrunkLine Entity Category    
 */
public class TrunkLines {	
	// Attributes
	int n;    //The number of call entities, n
	//ArrayList<Call> list = new ArrayList<Call>();  	// List of call entities that are in the group
	// For implementing the group, use a HashSet object.
	protected HashSet<Call> group = new HashSet<Call>();  // maintains set of call objects, that is RG.TrunkLines.list
	protected int numLines; 	//The  total number of trunk lines
	protected int numReserved;  //The number of reserved lines

	// Required methods to manipulate the group
	protected void insertGrp(Call icCall) {	group.add(icCall); }
	protected boolean removeGrp(Call icCall) { return(group.remove(icCall)); }
	protected int getN() { return group.size(); }  // Attribute n

}
