package algorithm.graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MyGraph implements Graph {

	private Map<Vertex, List<Vertex>> adjVertices = new HashMap<Vertex, List<Vertex>>();

	@Override
	public void addVertex(Integer v) {
		adjVertices.putIfAbsent(new Vertex(v), new ArrayList<>());
	}

	@Override
	public void addEdge(Integer v, Integer w) {
		Vertex v1 = new Vertex(v);
		Vertex v2 = new Vertex(w);
		adjVertices.get(v1).add(v2);
		adjVertices.get(v2).add(v1);

	}

	@Override
	public void deleteVertex(Integer v) {
		Vertex vertex = new Vertex(v);
		adjVertices.values().stream().forEach(e -> e.remove(vertex));
		adjVertices.remove(new Vertex(v));

	}

	@Override
	public void deleteEdge(Integer u, Integer v) {
		Vertex v1 = new Vertex(u);
		Vertex v2 = new Vertex(v);
		List<Vertex> eV1 = adjVertices.get(v1);
		List<Vertex> eV2 = adjVertices.get(v2);
		if (eV1 != null)
			eV1.remove(v2);
		if (eV2 != null)
			eV2.remove(v1);

	}

	@Override
	public boolean contains(Integer v) {
		return adjVertices.containsKey(new Vertex(v));
	}

	@Override
	public int degree(Integer v) {
		return adjVertices.get(new Vertex(v)).size();
	}

	@Override
	public boolean adjacent(Integer v, Integer w) {
		return adjVertices.get(new Vertex(v)).contains(new Vertex(w));
	}

	@Override
	public Graph getCopy() {
		MyGraph copiedGraph = new MyGraph();
		for (Map.Entry<Vertex, List<Vertex>> entry : adjVertices.entrySet()) {
			copiedGraph.addVertex(entry.getKey().getValue());
			for (Vertex vertex : entry.getValue()) {
				copiedGraph.addVertex(vertex.getValue());
				copiedGraph.addEdge(entry.getKey().getValue(), vertex.getValue());
			}
		}
		return copiedGraph;
	}

	@Override
	public Set<Integer> getNeighbors(Integer v) {
		return adjVertices.get(new Vertex(v)).stream().map(Vertex::getValue).collect(Collectors.toSet());
	}

	@Override
	public int size() {
		return adjVertices.keySet().size();
	}

	@Override
	public int getEdgeCount() {
		int edgedCount = 0;
		for (Vertex vertex : adjVertices.keySet()) {
			edgedCount += adjVertices.get(vertex).size();
		}
		return edgedCount;
	}

	@Override
	public Set<Integer> getVertices() {
		return adjVertices.keySet().stream().map(vertex -> vertex.getValue()).collect(Collectors.toSet());
	}

	public MyGraph() {
	}

	public MyGraph(String filename) throws IOException {
		adjVertices = new HashMap<Vertex, List<Vertex>>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			String line;
			while ((line = reader.readLine()) != null) {
				Integer v = Integer.parseInt(line.substring(0, line.indexOf("	")));
				Integer w = Integer.parseInt(line.substring(line.indexOf("	") + 1));
				addVertex(v);
				addVertex(w);
				addEdge(v, w);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void printGraph() {
		for (Map.Entry<Vertex, List<Vertex>> entry : adjVertices.entrySet()) {
			System.out.println(entry.getKey().getValue() + " -->");
			for (Vertex vertex : entry.getValue()) {
				System.out.printf("  %d  ", vertex.getValue());
			}
			System.out.println();
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		MyGraph graph = new MyGraph("./docs/test.sec");
		graph.printGraph();
	}

	public Map<Vertex, List<Vertex>> getAdjVertices() {
		return adjVertices;
	}

	public void setAdjVertices(Map<Vertex, List<Vertex>> adjVertices) {
		this.adjVertices = adjVertices;
	}

}
