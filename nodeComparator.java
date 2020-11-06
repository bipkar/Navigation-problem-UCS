/*
Name : Bipul Karki
ID : 1001661413
Date : 09/22/2020
*/
import java.io.*;
import java.util.*;
public class nodeComparator implements Comparator<Nodes>{
    
    public int compare(Nodes A, Nodes B)
    {

        if (A.get_total_distance() == B.get_total_distance()) {
            return 0;
        }
        else if (A.get_total_distance() > B.get_total_distance()){
            return 1;
        }
        else{
            return -1;
            
        }
        
    }

}
