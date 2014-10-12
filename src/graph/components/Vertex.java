package graph.components;


import java.util.Collections;
import java.util.List;

import com.google.common.base.Objects;

//TODO: Is this class even needed? Would it be better to just represent the vertex with a char (or whatever better data type) instead?
public final class Vertex 
{
  // TODO: Think of a better way to represent the name. String will consume a lot of memory!
 // But this is too small.
  private char name;
  private Vertex parent;
  private int key;
  private List<Vertex> adjacencyList;
  
  public static Vertex createVertex(char name)
  {
	Vertex v = new Vertex(name);
	return v;
  }
  
  /**
   * Constructor for Vertex. The parent of each new vertex is null and it's key is max int value.
   * @param name The name of the vertex being constructed.
   */
  private Vertex(char name)
  {
	  this.name=name; 
	  this.parent = null;
	  this.key = Integer.MAX_VALUE;
	  this.adjacencyList = Collections.emptyList();
  }

  public char getName() 
  {
	return name;
  }
  
  public int getKey()
  {
	  return key;
  }
  
  public Vertex getParent()
  {
	  return parent;
  }
  
  public List<Vertex> getAdjacencyList()
  {
	  return adjacencyList;
  }
  
  public void setParent(Vertex parent)
  {
	  this.parent = parent;
  }
  
  public void setKey(int key)
  {
	  this.key = key;
  }
  
  public void setAdjacencyList(List<Vertex> adjacencyList)
  {
	  this.adjacencyList = adjacencyList;
  }
  
  @Override
  public boolean equals(Object obj) 
   {
		if (obj instanceof Vertex) 
		{
			Vertex other = (Vertex) obj;
			
			return Objects.equal(name, other.name);
		} 
		else 
		{
			return false;
		}
	}
  
    @Override
	public int hashCode() 
    {
		return Objects.hashCode(name);
	}
}
