package djikstra;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import graph.Graph;
import graph.components.Edge;
import graph.components.Vertex;

// Djikstra's algorithm to find shortest paths from a single source in a non-negative cycle. 
// This uses min priority queue and a technique called relaxation which changes the distance key of a vertex if the newly discovered
// distance is smaller. A Set of vertices (including the source) is returned along with all their parents which can be used to construct the path.
public final class DjikstraAlgorithm 
{
	private Set<Vertex> vertices;
	private Set<Edge> edges;
	private Vertex source;
	private MinPriorityHeap minPriorityHeap;

	/**
	 * Finds the shortest path in the graph from source to every other vertex.
	 * @param graph
	 * @param source
	 * @return
	 */
	public Set<Vertex> findShortestPath(Graph graph, Vertex source)
	{
		this.vertices = graph.getVertices();
		this.edges=graph.getEdges();
		this.source = source;
		
		initializeVertices();
		Set<Vertex> verticesWithFinalShortestPaths = new HashSet<Vertex>(vertices.size());
		
		minPriorityHeap = MinPriorityHeap.createMinPriorityHeap(vertices);
		while(!minPriorityHeap.getConstructedHeap().isEmpty())
		{
			Vertex minVertex = minPriorityHeap.extractMinimumVertex();
			
			verticesWithFinalShortestPaths.add(minVertex);
			
			for(Vertex v : minVertex.getAdjacencyList())
			{
				relax(v,minVertex);
			}
		}
		
		return verticesWithFinalShortestPaths;
	}
	
	/**
	 * The vertex initializes parents to NULL and their keys to INTEGER.MAX_VALUE (the vertex constructor takes care of this).
	 * This simply sets the source key to 0.
	 */
	private void initializeVertices()
	{
		for(Vertex vertex:vertices)
		{
			if(vertex==source)
			{
				vertex.setKey(0);
				break;
			}
		}
	}
	
	private void relax(Vertex v,Vertex u)
	{
		// We only consider the edge from u->v since this is a directed graph.
		Set<Edge> currentEdge = edges.stream().filter(e->e.getStartVertex().equals(u) && e.getEndVertex().equals(v)).collect(Collectors.toSet());
		
		if(currentEdge==null || currentEdge.isEmpty())
		{
			throw new IllegalStateException("Couldn't find an edge between the root:" + u.getName() + " and the current adjacent vertex:" + v.getName());
		}
		
		if(currentEdge.size()>1)
		{
			throw new IllegalStateException("There are more than 1 edges between the root:" + u.getName() + " and the current adjacent vertex:" + v.getName());
		}
		
		int edgeWeight = currentEdge.iterator().next().getWeight();
		if(v.getKey() > u.getKey() + edgeWeight)
		{
			// Using decrease key and update parent to update the vertex on the min priority heap.
			minPriorityHeap.decreaseKeyAndUpdateParent(v, u.getKey() + edgeWeight, u);
		}
	}
}
