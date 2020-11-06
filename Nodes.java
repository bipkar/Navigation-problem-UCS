/*
Name : Bipul Karki
ID : 1001661413
Date : 09/22/2020
*/
import java.io.*;
import java.util.*;
public class Nodes
    {
        int depth=0;
        double distance=0.0;
        String city_name;
        ArrayList<Nodes> child = new ArrayList<Nodes>();
        Nodes parent;
        //a constructor to initialize the required objects for a node
        public Nodes(String city_name, int depth, double distance, Nodes parent)
        {
            this.city_name = city_name;
            this.depth = depth;
            this.distance = distance;
            this.parent = parent;          
        }
        int get_total_depth()
        {
            return this.depth;
        }
        double get_total_distance()
        {
            return this.distance;
        }
        String get_city()
        {
            return this.city_name;
        }
        ArrayList<Nodes> get_child()
        {
            return this.child;
        }
        void set_child(ArrayList<Nodes>child)
        {
            this.child = child;
        }
        Nodes get_parent()
        {
            return this.parent;
        }
        
    }