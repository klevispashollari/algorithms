package algorithm.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchTree {

	class Instance {
		private Graph graph;
		private Integer k;

		public Instance(Graph graph, Integer k) {
			super();
			this.graph = graph;
			this.k = k;
		}

	}

	public static void main(String[] args) {
		SearchTree searchTree = new SearchTree();


		Set<Vertex> set = searchTree.coveredVertexes(graphBuilder2(),new HashSet<>());
		System.out.println(set);
	}
	private static MyGraph graphBuilder2(){
		MyGraph myGraph = new MyGraph();
		myGraph.addVertex(1);
		myGraph.addVertex(2);
		myGraph.addVertex(3);
		myGraph.addVertex(4);
		myGraph.addVertex(5);
		myGraph.addVertex(6);
		myGraph.addEdge(1,2);
		myGraph.addEdge(1,3);
		myGraph.addEdge(1,4);
		myGraph.addEdge(1,5);
		myGraph.addEdge(1,6);
		myGraph.addEdge(5,6);
		return myGraph;
	}

	private static MyGraph graphBuilder1(){
		MyGraph myGraph = new MyGraph();
		myGraph.addVertex(1);
		myGraph.addVertex(2);
		myGraph.addVertex(3);
		myGraph.addVertex(4);
		myGraph.addVertex(5);
		myGraph.addVertex(6);
		myGraph.addVertex(7);
		myGraph.addVertex(8);
		myGraph.addEdge(1,4);
		myGraph.addEdge(1,2);
		myGraph.addEdge(2,3);
		myGraph.addEdge(2,5);
		myGraph.addEdge(5,6);
		myGraph.addEdge(3,6);
		myGraph.addEdge(3,7);
		myGraph.addEdge(3,8);
		return myGraph;
	}

	public Vertex findMaxDegreeVertex(Graph graph){
		Map.Entry<Vertex, List<Vertex>> maxEntry = null;
		for (Map.Entry<Vertex, List<Vertex>> entry : graph.getAdjVertices().entrySet())
		{
			if (maxEntry == null || entry.getValue().size() > maxEntry.getValue().size())
			{
				maxEntry = entry;
			}
		}
		return maxEntry.getKey();
	}

	private Set<Vertex> coveredVertexes(Graph graph,Set<Vertex> vertexSet) {
		if (graph.getEdgeCount() == 0) {
			return vertexSet;
		}
		Vertex maxDegreeVertex = findMaxDegreeVertex(graph);
		vertexSet.add(maxDegreeVertex);
		graph.deleteVertex(maxDegreeVertex);
		coveredVertexes(graph,vertexSet);
		return vertexSet;
	}

	public void removeSingletons(Instance i) {
		for (Map.Entry<Vertex, List<Vertex>> entry : i.graph.getAdjVertices().entrySet()) {
			if (i.graph.degree(entry.getKey().getValue()) == 0) {
				i.graph.deleteVertex(entry.getKey().getValue());
			}
		}
	}

	public void removeDegOne(Instance i) {
		for (Map.Entry<Vertex, List<Vertex>> entry : i.graph.getAdjVertices().entrySet()) {
			if (i.graph.degree(entry.getKey().getValue()) == 1) {
				Vertex neighbor = new Vertex(i.graph.getNeighbors(entry.getKey().getValue()).iterator().next());
				i.graph.deleteVertex(neighbor.getValue());
				i.k--;
			}
		}
	}
	
	public void removeHighDeg(Instance i) {
		for (Map.Entry<Vertex, List<Vertex>> entry : i.graph.getAdjVertices().entrySet()) {
			if (i.graph.degree(entry.getKey().getValue()) > i.k) {
				i.graph.deleteVertex(entry.getKey().getValue());
				i.k--;
			}
		}
	}
}
