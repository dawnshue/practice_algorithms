package coursera.stanford.algorithms1.week4;

import java.util.TreeSet;

public class Vertex {

	private Integer name;
	private TreeSet<Integer> outgoing;
	private TreeSet<Integer> incoming;
	private boolean visited;
	private Integer root;

	public Vertex(Integer name) {
		this.name = name;
		outgoing = new TreeSet<>();
		incoming = new TreeSet<>();
		visited = false;
		root = null;
	}

	public Integer getRoot() {
		return root;
	}

	public void setRoot(Integer root) {
		this.root = root;
	}

	public Integer getName() {
		return name;
	}

	public void addOutgoing(Integer i) {
		outgoing.add(i);
	}

	public void addIncoming(Integer i) {
		incoming.add(i);
	}

	public TreeSet<Integer> getIncoming() {
		return incoming;
	}

	public boolean isVisited() {
		return visited;
	}

	public void visit() {
		visited = true;
	}

	public TreeSet<Integer> getOutgoing() {
		return outgoing;
	}

}
