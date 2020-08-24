package algorithm.graph;

import java.time.Duration;
import java.time.Instant;
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
		Instant begin = Instant.now();
		System.out.println(searchTree.solve(graphBuilder()));
		Instant end = Instant.now();
		System.out.println(Duration.between(begin, end).getNano()/1000000);
	}

	private boolean solve(Instance i) {
		if (i.k < 0) {
			return false;
		}
		if (i.graph.getEdgeCount() == 0) {
			return true;
		}

		Vertex u = new Vertex(i.graph.getVertices().iterator().next());
		Vertex v = new Vertex(i.graph.getNeighbors(u.getValue()).iterator().next());
		Graph copiedGraphWithoutU = i.graph.getCopy();
		copiedGraphWithoutU.deleteVertex(u.getValue());
		if(solve(new Instance(copiedGraphWithoutU, i.k-1))) {
			return true;
		}
		Graph copiedGraphWithoutV = i.graph.getCopy();
		copiedGraphWithoutV.deleteVertex(v.getValue());
		if(solve(new Instance(copiedGraphWithoutV, i.k-1))) {
			return true;
		}
		return false;
	}

	public int solve(Graph graph) {
		return coveredVertexes(graph, new HashSet<>()).size();
	}

	private Set<Vertex> coveredVertexes(Graph graph, Set<Vertex> vertexSet) {
		if (graph.getEdgeCount() == 0) {
			return vertexSet;
		}
		Vertex maxDegreeVertex = findMaxDegreeVertex(graph);
		vertexSet.add(maxDegreeVertex);
		graph.deleteVertex(maxDegreeVertex.getValue());
		coveredVertexes(graph, vertexSet);
		return vertexSet;
	}

	public Vertex findMaxDegreeVertex(Graph graph) {
		Map.Entry<Vertex, List<Vertex>> maxEntry = null;
		for (Map.Entry<Vertex, List<Vertex>> entry : graph.getAdjVertices().entrySet()) {
			if (maxEntry == null || entry.getValue().size() > maxEntry.getValue().size()) {
				maxEntry = entry;
			}
		}
		return maxEntry.getKey();
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

	private static MyGraph graphBuilder() {
		MyGraph myGraph = new MyGraph();
		myGraph.addVertex(1);
		myGraph.addVertex(2);
		myGraph.addVertex(3);
		myGraph.addVertex(4);
		myGraph.addVertex(5);
		myGraph.addVertex(6);
		myGraph.addEdge(1, 2);
		myGraph.addEdge(1, 3);
		myGraph.addEdge(1, 4);
		myGraph.addEdge(1, 5);
		myGraph.addEdge(1, 6);
		myGraph.addEdge(5, 6);
		myGraph.addEdge(4, 3);
		myGraph.addEdge(3, 5);
		myGraph.addEdge(4, 6);
		return myGraph;
	}
}
