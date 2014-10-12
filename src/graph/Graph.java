package graph;

import graph.components.Edge;
import graph.components.Vertex;

import java.util.Set;

//TODO : Do we need equals/hashcode?
public final class Graph 
{
   private Set<Vertex> vertices;
   private Set<Edge> edges;
   
   public static Graph createGraph(Set<Vertex> vertices,Set<Edge> edges)
   {
	   Graph graph = new Graph(vertices,edges);
	   return graph;
   }
   
   private Graph(Set<Vertex> vertices,Set<Edge> edges)
   {
	   this.vertices=vertices;
	   this.edges=edges;
   }
   
   // May be null.
   public Set<Vertex> getVertices()
   {
	   return vertices;
   }
   
   // May be null.
   public Set<Edge> getEdges()
   {
	   return edges;
   }
}
