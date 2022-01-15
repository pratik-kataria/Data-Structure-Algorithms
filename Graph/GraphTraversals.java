import java.util.*;
public class GraphTraversals{

     public static void main(String []args){
        // vertices
        char[] vertices = {'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G'};
        // edges
        char[] s = {'A' , 'A' , 'B' ,'B', 'D' , 'C' , 'E'};
        char[] d = {'B', 'C' , 'E' , 'D' , 'F' , 'G' , 'A'};
        // create a graph
        Map<Character , HashSet<Character>> graph = buildGraph(s , d);
         System.out.println(graph);
        
        startDFS(graph , vertices , new HashSet<Character>() , false);
        System.out.println();
        startBFS(graph , vertices , new HashSet<Character>() );
       
     }
     public static void startDFS(Map<Character , HashSet<Character>> graph , char[] vertices , Set<Character> visited , boolean recursive)
     {
        for(int i=0 ; i<vertices.length ; i++)
        {
            char vertex = vertices[i];
            if(!visited.contains(vertex))
            {   
                if(recursive)
                    graphDFS(graph , vertex , visited);
                else
                    graphDFSUsingStack(graph , vertex , visited);
            }
        }
     }
     public static void startBFS(Map<Character , HashSet<Character>> graph , char[] vertices , Set<Character> visited)
     {
        for(int i=0 ; i<vertices.length ; i++)
        {
            char vertex = vertices[i];
            if(!visited.contains(vertex))
            {
                graphBFS(graph , vertex , visited);
            }
        }
     }
     
    //  Recursive implementation of the DFS for a graph
     public static void graphDFS(Map<Character , HashSet<Character>> graph , char vertex , Set<Character> visited)
     {
        // process the current node
        System.out.print( vertex +" ");
        visited.add(vertex);
        Set<Character> neighbors = graph.getOrDefault(vertex , new HashSet<Character>());
        for(Character n : neighbors)
        {
            if(!visited.contains(n))
            {
                // visit the neighbor if not already visited
                graphDFS(graph , n , visited);
            }
        }
     }
     
    //  Stack implementation of the DFS for a graph
     public static void graphDFSUsingStack(Map<Character , HashSet<Character>> graph , char vertex , Set<Character> visited)
     {
        Stack<Character> stack = new Stack<Character>();
        stack.push(vertex);
        while(!stack.isEmpty())
        {
            char top = stack.pop();
            System.out.print(top + " ");
            // visiting the top vertex
            visited.add(top);
            for(Character neighbor : graph.getOrDefault(top , new HashSet<Character>()))
            {
                if(!visited.contains(neighbor))
                {
                    stack.push(neighbor);
                }
            }
            
        }
     }
     public static void graphBFS(Map<Character , HashSet<Character>> graph , char vertex , Set<Character> visited)
     {
        Queue<Character> queue = new LinkedList<Character>();
        queue.add(vertex);
        while(!queue.isEmpty())
        {
            char v = queue.poll();
            // visit the vertex
            System.out.print(v + " ");
            visited.add(v);
            for(char neighbor : graph.getOrDefault(v , new HashSet<Character>()))
            {
                if(!visited.contains(neighbor))
                {
                    queue.add(neighbor);
                }
            }
        }
     }
     public static Map<Character , HashSet<Character>> buildGraph(char[] source , char[] dest)
     {
        Map<Character , HashSet<Character>> graph = new HashMap<Character , HashSet<Character>>();
        for(int i=0 ; i<source.length ; i++)
        {
            char from = source[i];
            char to = dest[i];
            HashSet<Character> neighbors = graph.getOrDefault(from , new HashSet<Character>());
            neighbors.add(to);
            graph.put(from , neighbors);
        }
        return graph;
     }
}