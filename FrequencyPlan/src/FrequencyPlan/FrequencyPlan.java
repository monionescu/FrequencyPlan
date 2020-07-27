package FrequencyPlan;
public class FrequencyPlan {
    final int T = 4; 
    int arfcn[]; 
  
    /* A utility function to check  
       if the current frequency assignment  
       is safe for transmitter t */
    boolean isSafe( 
        int t, int TransmitterMap[][], int arfcn[], 
        int f) 
    { 
        for (int i = 0; i < T; i++) 
            if ( 
            		TransmitterMap[t][i] == 1 && Math.abs(f - arfcn[i])<=1 
            	)
                return false; 
        return true; 
    } 
  
	/* A recursive utility function  
       to solve m FAllocation  problem */
    boolean TransmitterMapFrequencyUtil( 
        int TransmitterMap[][], int m, 
        int arfcn[], int t) 
    { 
        /* base case: If all transmitters are  
           assigned a frequency then return true */
        if (t == T) 
            return true; 
  
        /* Consider this transmitter t and try  
           different frequencies */
        for (int f = 1; f <= m; f++) { 
            /* Check if assignment of frequencies f to t 
               is fine*/
            if (isSafe(t, TransmitterMap, arfcn, f)) { 
            	arfcn[t] = f; 
  
                /* recur to assign frequencies to rest 
                   of the transmitters */
                if ( 
                    TransmitterMapFrequencyUtil( 
                    		TransmitterMap, m, 
                        arfcn, t + 1)) 
                    return true; 
  
                /* If assigning frequency f doesn't lead 
                   to a solution then remove it */
                arfcn[t] = 0; 
            } 
        } 
  
        /* If no frequency can be assigned to  
           this transmitter then return false */
        return false; 
    } 
  
    /* This function solves the m FAllocation problem using 
       Backtracking. It mainly uses TransmitterMapFrequencyUtil() 
       to solve the problem. It returns 	false if the m 
       frequencies cannot be assigned, otherwise return true 
       and  prints assignments of frequencies to all transmitters. 
       Please note that there  may be more than one 
       solutions, this function prints one of the 
       feasible solutions.*/
    boolean FrequencyPlan(int TransmitterMap[][], int m) 
    { 
        // Initialize all frequency values as 0. This 
        // initialization is needed correct 
        // functioning of isSafe() 
    	arfcn = new int[T]; 
        for (int i = 0; i < T; i++) 
        	arfcn[i] = 0; 
  
        // Call TransmitterMapFrequencyUtil() for vertex 0 
        if ( 
            !TransmitterMapFrequencyUtil( 
            		TransmitterMap, m, arfcn, 0)) { 
            System.out.println( 
                "Solution does not exist"); 
            return false; 
        } 
  
        // Print the solution 
        printSolution(arfcn); 
        return true; 
    } 
  
    /* A utility function to print solution */
    void printSolution(int arfcn[]) 
    { 
        System.out.println( 
            "Solution Exists: Following"
            + " are the assigned frequencies"); 
        for (int i = 0; i < T; i++) 
            System.out.print(" " + arfcn[i] + " "); 
        System.out.println(); 
    } 
  
    // driver program to test above function 
    public static void main(String args[]) 
    { 
    	FrequencyPlan FAllocation  
= new FrequencyPlan(); 
        /* Create following neighbors and  
           test whether it is 
           3 frequency assignable 
          (3)---(2) 
           |   / | 
           |  /  | 
           | /   | 
          (0)---(1) 
        */
        int TransmitterMap[][] = { 
            { 0, 1, 1, 0 }, 
            { 1, 0, 0, 0 }, 
            { 1, 0, 0, 1 }, 
            { 0, 0, 1, 0 }, 
           
        }; 
        int m = 4; // Number of frequency 
        FAllocation.FrequencyPlan(TransmitterMap, m); 
    } 


}

