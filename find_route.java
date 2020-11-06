/*
Name : Bipul Karki
ID : 1001661413
Date : 09/23/2020
*/
import java.io.*;
import java.util.*;
public class find_route {
   
    public static void main(String[] args) {
        //declaring the command line input file to input_filename
        String input_filename= args[0]; 
        //declaring the user input origin city to origin_city variable
        String origin_city=args[1]; 
        //declaring the user input destination to destination_city variable
        String destination_city=args[2]; 
        //using an ArrayList here to store the routes from input file
        ArrayList<Input_File> route=new ArrayList<Input_File>();
        //token array will be used to split the inputs line by line and store it 
        String token[]; 
        //using built in BufferedReader class to read the input text line by line
        BufferedReader br1 = null;
        try
        {
            br1 = new BufferedReader(new FileReader(input_filename));
            //a string to store input text from the file line by line
            String line;

            while((line = br1.readLine()) != null) 
            {
                if(line.equals("END OF INPUT"))
                {
                    break;
                }

                token = line.split(" ");
                
                Input_File fp= new Input_File();

                fp.city1=token[0]; 

                fp.city2=token[1];

                fp.route_distance=(int) Double.parseDouble(token[2]);

                route.add(fp);
            } 
        }
        catch(IOException excep)
        {
            System.out.println("Error! File not found");
        }
        
        finally
        {
            try
            {
                if(br1 != null)
                {
                    br1.close();
                }
            }
            catch(IOException excep)
            {
                System.out.println("Error closing the BufferedReader!");
            }
        }
        //creating a first node and initializing the nodeGenerated and nodeExpanded
        Nodes start_node=new Nodes(origin_city,0,0,null);
        int nodeGenerated = 1;
        int nodeExpanded = 0;
        double routeDistance = 0.0;

        //an ArrayList to store the closed states
        ArrayList<String> closed=new ArrayList<String>();
        //using comparator-interface to sort the nodes following UCS algorithm
        Comparator<Nodes> comparator = new nodeComparator();
        //creating a priority queue to store the nodes in the fringe
        PriorityQueue<Nodes> queueFringe;
        queueFringe = new PriorityQueue<Nodes>(100, comparator);
        //adding the first node in the fringe
        queueFringe.add(start_node);
        //starting the loop to determine the nodes expanded, nodes generaterd, total_distance, and route.
        while(!queueFringe.isEmpty())
        {
            //poping out the first node and storing it in insert_city node for later use
            //and expanding the node count by 1
            Nodes insert_city = queueFringe.poll();
            nodeExpanded = nodeExpanded + 1;
            //creating a child_node to store the nodes that are generated while expanding
            ArrayList<Nodes> child_node = new ArrayList<Nodes>();
            //if the node is the goal state, then it will print out the required output
            if(insert_city.get_city().equals(destination_city))
            {
                System.out.println("nodes expanded: "+nodeExpanded);
                System.out.println("nodes generated:"+nodeGenerated);
                System.out.println("distance : "+ insert_city.get_total_distance()+"km");
                System.out.println("route:");
                //A stack to store and print the parent noeds from start to goal state.
                Stack<String> temp = new Stack<String>();
                
                while(insert_city.get_parent() != null)
                {
                    for(int i=0; i<route.size(); i++)
                    {
                        if(insert_city.get_city().equals(route.get(i).city1) || insert_city.get_city().equals(route.get(i).city2))
                        {
                            if(insert_city.get_parent().get_city().equals(route.get(i).city1) || insert_city.get_parent().get_city().equals(route.get(i).city2))
                            {
                                routeDistance = route.get(i).route_distance;
                            }
                        }
                    }
                   
                    temp.push(insert_city.get_parent().get_city());
                    temp.push(insert_city.get_city());
                    String distance = Double.toString(routeDistance);
                    temp.push(distance);
                    
                    insert_city = insert_city.get_parent();
                    
                }
                while(!temp.isEmpty())
                {
                    String distance = temp.pop();
                    String destination = temp.pop();
                    String origin = temp.pop();
                    System.out.println(origin+" to "+destination+","+distance+"km");
                }
                break;
            }
            //else traverse through the child nodes and store the depth value, g(n),total route cost, and parent node. 
            else if(!closed.contains(insert_city.get_city()))
            {
                closed.add(insert_city.get_city());
                
                for(Input_File inputs:route)
                {
                    if(inputs.get_city1().equals(insert_city.get_city())){
                        Nodes childNode = new Nodes(inputs.get_city2(),insert_city.get_total_depth()+1,insert_city.get_total_distance()+inputs.get_distance(),insert_city);
                        child_node.add(childNode);
                    }
                    else if(inputs.get_city2().equals(insert_city.get_city())){
                        Nodes childNode = new Nodes(inputs.get_city1(),insert_city.get_total_depth()+1,insert_city.get_total_distance()+inputs.get_distance(),insert_city);
                        child_node.add(childNode);
                    }
                }
                insert_city.set_child(child_node);
                for(int i = 0; i< insert_city.get_child().size();i++)
                {
                    Nodes Child_Node;
                    Child_Node = insert_city.get_child().get(i);
                    queueFringe.add(Child_Node);
                    nodeGenerated++;
                }
            }
            //if the fringe is empty i.e. the route is not available to reach the goal, print out the required results  
            else if(queueFringe.isEmpty())
            {
                System.out.println("nodes expanded: "+nodeExpanded);
                System.out.println("nodes generated:"+nodeGenerated);
                System.out.println("distance: infinity");
                System.out.println("route:");
                System.out.println("none");
            }
        }
   }
}