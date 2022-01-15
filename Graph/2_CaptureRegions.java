/*
    Leet code problem link: https://leetcode.com/problems/surrounded-regions
*/

import java.util.*;
public class Solution {
    public static int[] dx = {-1 , 0 , 1 , 0};
    public static int[] dy = {0 , 1 , 0 , -1};
	public void solve(ArrayList<ArrayList<Character>> a) {
        int n = a.size();
        int m = a.get(0).size();
        for(int i=0 ; i<m ; i++)
        {
            if(a.get(0).get(i) == 'O')
            {
                bfs(a , 0 , i);
            }
            if(a.get(n-1).get(i) == 'O')
            {
                bfs(a , n-1 , i);
            }
        }
        for(int i=0 ; i<n ; i++)
        {
            if(a.get(i).get(0) == 'O')
            {
                bfs(a , i , 0);
            }
            if(a.get(i).get(m-1) == 'O')
            {
                bfs(a , i , m-1);
            }
        }
        for(int i=0 ; i<n ; i++)
        {
            for(int j=0 ; j<m ; j++)
            {
                if(a.get(i).get(j) == '#')
                {
                    a.get(i).set(j , 'O');
                }
                else if(a.get(i).get(j) == 'O')
                {
                    a.get(i).set(j , 'X');
                }
            }
        }
     }
     
    //  performing bfs traversal for the given node
     public static void bfs(ArrayList<ArrayList<Character>> a , int i , int j)
     {
        Queue<Node> queue = new LinkedList<Node>();
        // adding the initial node
        Node node = new Node(i , j);
        queue.add(node);
        while(!queue.isEmpty())
        {
            Node curr = queue.poll();
            // visiting the node
            a.get(curr.i).set(curr.j , '#');
            for(int k=0 ; k<4 ; k++)
            {
                int nI = curr.i + dx[k];
                int nJ = curr.j + dy[k];
                if(shouldVisit(a , nI , nJ))
                {
                    Node neighbor = new Node(nI , nJ);
                    queue.add(neighbor);
                    
                    // also mark it visited so as to avoid duplicate nodes in the queue
                    a.get(nI).set(nJ , '#');
                }
            }
        }
     }
     public static boolean shouldVisit(ArrayList<ArrayList<Character>> a , int i , int j  )
     {
         if(i<0 || j<0 || i>=a.size() || j>=a.get(0).size() || a.get(i).get(j) == '#' || a.get(i).get(j) != 'O')
         {
             return false;
         }
         return true;
     }
}
class Node{
    
    int i;
    int j;
    public Node(int i , int j)
    {
        this.i = i;
        this.j = j;
    }
}
