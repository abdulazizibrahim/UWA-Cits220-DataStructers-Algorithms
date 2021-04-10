/**
* Assignment Lab 6
* Graph traversals BFS and DFS
*/
import java.util.LinkedList;
import java.util.Queue;
import CITS2200.Graph;
import CITS2200.Search;
public class SearchImp implements Search {
	 /**
	 * Colors scheme used:
	 * Black = 0
	 * White = 1;
	 * Grey = 2
	 */
	static int  time = 0;
	 /**
	 * This method returns an array of vertices 
	 * which are adjacent to it separating from 
	 * the adjacency matrix
	 */
	public int[] getAdjacents(int [] e)
	{
		int[] edge = new int[e.length];
		int count = 0;
		for(int i=0; i<e.length;i++)
		{
			if(e[i] != 0)
			{
				edge[count] = i;
				count = count + 1;
			}
		}
		int [] edgex = new int[count];
		for(int i=0; i<count; i++)
		{
			edgex[i] = edge[i];
		}
		return edgex;
	}
	@Override
	 /**
	 * Runs a BFS on a given directed, un-weighted graph.
	 * returns an array listing the parent of each vertex in the spanning tree, 
	 * or -1 is the vertex is not reachable from the start vertex.
	 */
	public int[] getConnectedTree(Graph g, int vertex) {
		// TODO Auto-generated method stub
		int [] color = new int[g.getNumberOfVertices()];
		int [] parent= new int [g.getNumberOfVertices()];
		int[][] adjacent  = g.getEdgeMatrix();
		Queue<Integer> que = new LinkedList<>();
		for(int i=0; i<g.getNumberOfVertices();i++)
		{
			parent[i] = -1;
			color[i] = 1; 
		}
		que.add(vertex);
		while(!que.isEmpty())
		{
			int w = que.remove();
			int [] edge = adjacent[w];
			edge = getAdjacents(edge);
			for(int i=0; i<edge.length;i++)
			{
				if(color[edge[i]] == 1)
				{
					parent[edge[i]] = w;
					color[edge[i]] = 2;
					que.add(edge[i]);
				}
			}
			color[w] = 0;
		}
		return parent;
	}

	@Override
	/**
	 * Runs a BFS on a given directed, un-weighted graph to find the distances of vertices from the start vertex.
	 * returns an array listing the distance of each vertex from the start vertex of each, 
	 * or -1 is the vertex is not reachable from the start vertex.
	 */
	public int[] getDistances(Graph g, int vertex) {
		// TODO Auto-generated method stub
		int [] color = new int[g.getNumberOfVertices()];
		int [] distance= new int[g.getNumberOfVertices()];
		int [] parent= new int [g.getNumberOfVertices()];
		int[][] adjacent  = g.getEdgeMatrix();
		Queue<Integer> que = new LinkedList<>();
		for(int i=0; i<g.getNumberOfVertices();i++)
		{
			parent[i] = -1;
			color[i] = 1; 
			distance[i] = -1;
		}
		color[vertex] = 2;
		distance[vertex] = 0;
		que.add(vertex);
		while(!que.isEmpty())
		{
			int w = que.remove();
			int [] edge = adjacent[w];
			edge = getAdjacents(edge);
			for(int i=0; i<edge.length;i++)
			{
				if(color[edge[i]] == 1)
				{
					distance[edge[i]] = distance[w] + 1;
					parent[edge[i]] = w;
					color[edge[i]] = 2;
					que.add(edge[i]);
				}
			}
			color[w] = 0;
		}
		
		return distance;
	}

	@Override
	/**
	 * Runs a DFS on a given directed, un-weighted graph to find the start time and finish time for each vertex
	 * returns a 2-dimensional array, where each sub-array has two elements: 
	 * the first is the start time, 
	 * the second is the end time.
	 */
	public int[][] getTimes(Graph g, int vertex) {
		// TODO Auto-generated method stub
		int [] color = new int[g.getNumberOfVertices()];
		int [] parent= new int [g.getNumberOfVertices()];
		int[][] adjacent  = g.getEdgeMatrix();
		int [] [] dftime= new int[g.getNumberOfVertices()][2];
		for(int i=0; i<g.getNumberOfVertices();i++)
		{
			parent[i] = -1;
			color[i] = 1; 
		}
		color[vertex] = 2;
		dftime[vertex][0] = time;
		time = time + 1;
		int [] edge = adjacent[vertex];
		edge = getAdjacents(edge);
		for(int i=0; i<edge.length;i++)
		{
			if(color[edge[i]] == 1)
			{
				parent[edge[i]] = vertex;
				getTimes( g,  vertex);
			}
		}
		color[vertex] = 0;
		dftime[vertex][1] = time;
		time = time + 1;
		return dftime;
	}

}
