package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import graph.Graph;
import graph.components.Edge;
import graph.components.Vertex;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import djikstra.DjikstraAlgorithm;

public final class DjikstraOperationsTest 
{
	private DjikstraAlgorithm djikstraAlgorithm=new DjikstraAlgorithm();
	 private Graph graph;
	 private Vertex source;
	 private Map<Vertex,Vertex> parentByVertex;
	
	 @Before
	  public void setUp()
	  {
		 Vertex s = Vertex.createVertex('s');
		 Vertex t = Vertex.createVertex('t');
		 Vertex x = Vertex.createVertex('x');
		 Vertex y = Vertex.createVertex('y');
		 Vertex z = Vertex.createVertex('z');
		 source = s;
		 
		 Edge st = Edge.createEdge(s, t, 10);
		 Edge sy = Edge.createEdge(s, y, 5);
		 Edge yt = Edge.createEdge(y, t, 3);
		 Edge yx = Edge.createEdge(y, x, 9);
		 Edge yz = Edge.createEdge(y, z, 2);
		 Edge ty = Edge.createEdge(t, y, 2);
		 Edge tx = Edge.createEdge(t, x, 1);
		 Edge xz = Edge.createEdge(x, z, 4);
		 Edge zs = Edge.createEdge(z, s, 7);
		 Edge zx = Edge.createEdge(z, x, 6);
		 
		 s.setAdjacencyList(Lists.newArrayList(t,y));
		 t.setAdjacencyList(Lists.newArrayList(x,y));
		 y.setAdjacencyList(Lists.newArrayList(t,x,z));
		 z.setAdjacencyList(Lists.newArrayList(s,x));
		 
		  graph = Graph.createGraph(Sets.newHashSet(s,t,x,y,z), Sets.newHashSet(st,sy,yt,yx,yz,ty,tx,xz,zs,zx));
		  
		  parentByVertex =  new HashMap<Vertex,Vertex>();
		  parentByVertex.put(y, s);
		  parentByVertex.put(t, y);
		  parentByVertex.put(z, y);
		  parentByVertex.put(x, t);
		  
	  }
	 
	 @Test
	  public void testPrimsAlgorithm()
	  {
		Set<Vertex> vertices = djikstraAlgorithm.findShortestPath(graph, source);
		for(Vertex vertex : vertices)
		{
			if(vertex==source)
			{
				continue;
			}

			System.out.println("There exists and edge between:" + vertex.getParent().getName() + " and:" + vertex.getName());
			
			Vertex parent = parentByVertex.get(vertex);
			assertEquals("Expected parent vertex not the same as actual parent vertex",parent,vertex.getParent());
		}
	  }
}
