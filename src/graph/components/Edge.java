package graph.components;

import com.google.common.base.Objects;

// Using a comparator instead of comparable since the natural ordering is inconsistent with equals.
public final class Edge 
{
   private int weight;
   private Vertex startVertex,endVertex;
   
   public static Edge createEdge(Vertex start, Vertex end, int weight)
   {
	   return new Edge(start,end,weight);
   }
   
   private Edge(Vertex start, Vertex end, int weight)
   {
	   this.startVertex=start;
	   this.endVertex=end;
	   this.weight=weight;
   }

   public int getWeight() 
   {
	return weight;
   }
   
   public Vertex getStartVertex() 
   {
	return startVertex;
   }
   
   public Vertex getEndVertex() 
   {
	return endVertex;
   }
   
   @Override
   public boolean equals(Object obj) 
    {
 		if (obj instanceof Edge) 
 		{
 			Edge other = (Edge) obj;
 			
 			// The edges (a,b,10) and (b,a,10) aren't the same same. 
 			// Also, removing the weight since (a,b,2) and (a,b,4) isn't permitted.
 			return (Objects.equal(startVertex, other.startVertex) && Objects.equal(endVertex, other.endVertex));
 		} 
 		else 
 		{
 			return false;
 		}
 	}
   
     @Override
 	public int hashCode() 
     {
 		return Objects.hashCode(startVertex,endVertex);
 	}
     
    public String toString()
    {
    	return "Edge from:" + startVertex + " to:" + endVertex + " having weight:" + weight;
    }
}
