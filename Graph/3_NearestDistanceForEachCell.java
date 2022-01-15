/*
    GFG problem link: https://www.geeksforgeeks.org/distance-nearest-cell-1-binary-matrix

*/


import java.util.*;
public class NearestDistanceForEachCell {
    public static int[] dx = {-1 , 0 , 1 , 0};
    public static int[] dy = {0 , 1 , 0 , -1};
   public int[][] solve(int[][] A)
    {
        int[][] distance = new int[A.length][A[0].length];
        // -1 indicates that the node is being touched..
        for(int[] row : distance)
        {
            Arrays.fill(row , -1);
        }
        bfs(A , distance);
        return distance;
    }
    public static void populateQueue(Queue<Node> queue , int[][] arr , int[][] dist)
    {
        // Adding hash node in the queue for distinguishing between the level
        Node node = new Node(-1 , -1);
        queue.add(node);
        for(int i=0 ; i<arr.length ; i++)
        {
            for(int j=0 ; j<arr[0].length ; j++)
            {
                if(arr[i][j] == 1)
                {
                    node = new Node(i , j);
                    queue.add(node);
                    // we are also using it to check whether the node is visited or not
                    dist[i][j] = 0;
                }
            }
        }
    }
    public static void bfs(int[][] A , int[][] dist)
    {
        Queue<Node> queue = new LinkedList<Node>();
        // put all the 1's in the queue
        populateQueue(queue , A , dist);
        int level = 0;
        while(!queue.isEmpty())
        {
            Node curr = queue.poll();
            if(curr.isHashNode() && !queue.isEmpty())
            {
                level++;
                queue.add(curr);
                continue;
            }
            for(int i=0 ; i<4 ; i++)
            {
                int nI = curr.i + dx[i];
                int nJ = curr.j + dy[i];
                if(shouldVisit(dist , nI , nJ))
                {
                    Node node = new Node(nI , nJ);
                    queue.add(node);
                    dist[nI][nJ] = level;
                }
            }
        }
    }
    public static boolean shouldVisit(int[][] dist , int i , int j)
    {
        if(i<0 || j<0 || i>=dist.length || j>=dist[0].length || dist[i][j] >= 0)
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
    public boolean isHashNode()
    {
        if(this.i == -1 && this.j == -1)
        {
            return true;
        }
        return false;
    }
    @Override
    public String toString()
    {
        return "("+this.i+","+this.j+")";
    }
    
}