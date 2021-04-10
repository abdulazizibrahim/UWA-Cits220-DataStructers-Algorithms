
import CITS2200.*;
/**
 * @author arslan aziz
 * Lab 8 implementation of Path interface
 *
 */

public class PathImp implements Path {
	/**
	 *  Finds the minimum weight of a spanning tree for the given graph.
	 */
 public int getMinSpanningTree(Graph g) {
    int p1[] = new int[g.getNumberOfVertices()];
    int key[] = new int [g.getNumberOfVertices()];
    Boolean mstSet[] = new Boolean[g.getNumberOfVertices()];
    for (int i = 0; i < g.getNumberOfVertices(); i++)
    {
        key[i] = Integer.MAX_VALUE;
        mstSet[i] = false;
    }
    key[0] = 0;     
    p1[0] = -1;
    for (int count = 0; count < g.getNumberOfVertices(); count++)
    {
        int u = minKey(key, mstSet);
        mstSet[u] = true;
        for (int v = 0; v < g.getNumberOfVertices(); v++){
            if (g.getWeight(u,v)!=0 && mstSet[v] == false &&
                g.getWeight(u,v) <  key[v])
            {
                p1[v]  = u;
                key[v] = g.getWeight(u,v);
            }
         }
    }
    
    return getsum(p1, mstSet, g);
}
 private int getsum(int[] p1, Boolean[] mstSet, Graph g) {
		// TODO Auto-generated method stub
	 int sum = 0;
	    for (int v = 0; v < mstSet.length; v++)
	    {
	        if (mstSet[v] && p1[v] >= 0)
	        {
	            sum += g.getWeight(p1[v], v);
	        }
	    }
		return sum;
	}
/**
 * returns the minimum value in the key array.
 */
public int minKey(int key[], Boolean mstSet[])
{
    int min = Integer.MAX_VALUE, min_index=-1;
    for (int v = 0; v < key.length; v++)
        if (mstSet[v] == false && key[v] < min)
        {
            min = key[v];
            min_index = v;
        }
    return min_index;
}
/**
* returns the index of the minimum distance
*/
 public int minDistance(int dist[], Boolean sptSet[])
    {
        int min = Integer.MAX_VALUE, min_index=-1;
        for (int v = 0; v < dist.length; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }
 /**
 *   Runs Dijkstra's algorithm on a given undirected, 
 *   non-negative weighted graph to find the distances to all vertices from the specified source vertex
 */
 public int[] getShortestPaths(Graph g, int src)
{
    int dist[] = new int[g.getNumberOfVertices()];                       
    Boolean sptSet[] = new Boolean[g.getNumberOfVertices()];
    for (int i = 0; i < g.getNumberOfVertices(); i++)
    {
        dist[i] = Integer.MAX_VALUE;
        sptSet[i] = false;
    }
    
    return getdist(g, src, dist, sptSet);
}
private int[] getdist(Graph g, int src, int[] dist, Boolean[] sptSet) {
	// TODO Auto-generated method stub
	dist[src] = 0;
	int graph[][] = g.getEdgeMatrix();   
    for (int count = 0; count < g.getNumberOfVertices()-1; count++)
    {
        int u = minDistance(dist, sptSet);
        sptSet[u] = true;
        for (int v = 0; v < g.getNumberOfVertices(); v++)
            if (!sptSet[v] && graph[u][v]!=0 &&
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u]+graph[u][v] < dist[v])
                dist[v] = dist[u] + graph[u][v];
    }
	return dist;
}
}