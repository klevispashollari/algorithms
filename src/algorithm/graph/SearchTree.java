package algorithm.graph;

import java.util.List;
import java.util.Map;

public class SearchTree {

	class Instance {
		private MyGraph graph;
		private Integer k;

		public Instance(MyGraph graph, Integer k) {
			super();
			this.graph = graph;
			this.k = k;
		}

	}

	private boolean solve(Instance i) {
		if (i.k < 0) {
			return false;
		}
		if (i.graph.getEdgeCount() == 0) {
			return true;
		}
		return false;
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
