Name: Bipul Karki

Programming language used: Java

How the code is structured?
    A. find_route.java contains only the main function 
	1. main function
       	 -in the main function the command line input are stored in different variables.
         -the contents of input file is read and stored in the ArrayList called "route".
         -the fringe uses a priority queue, the priority queue uses comparator-interface to sort the nodes following UCS algorithm.
    B. Input_File.java contains:
	1. get_city1() to return the origin city
	2. get_city2() to return the destination city
	3. get_distance() return the route distance
    C. Nodes.java contains:
	1. A constructor Nodes to initialize the required objects for the program
	2. get_total_depth() to return the node depth
	3. get_total_distance to return the total distance of the route
	4. get_city() to return the city name
	5. get_child() method to store and return the Childs 
	6. set_child() to determine the child node
	7. get_parent() to determine the parent node
    D. nodeComparator.java is created which uses the comparator-interface to sort the nodes following UCS algorithm
	1. compare() function to sort the nodes according to their route cost. 
	
        
How to run the code?
  All the .java files and the .txt files should be inside the same folder.
  
  Compilation Instruction:

    Step1:- % javac find_route.java Input_File.java Nodes.java nodeComparator.java

    Step2:- % java find_route input1.txt Bremen Kassel
      Output:
	nodes expanded: 12
	nodes generated:20
	distance : 297.0km
	route:
	Hannover to Kassel,165.0 km
	Bremen to Hannover,132.0 km

      % java find_route input1.txt London Kassel
	nodes expanded: 7
	nodes generated:7
	distance: infinity
	route:
	none

External source used:
	1. An external source is used to implement the comparator interface to sort the fringe. https://www.geeksforgeeks.org/implement-priorityqueue-comparator-java/
