package coursera.stanford.algorithms2.week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Union-Find DS node
 * 
 */
class UFNode {
	int parent; // parent of Vertex at i in the nodeHolder
	int rank; // Number of object present in the tree/cluster

	UFNode(int parent, int rank) {
		this.parent = parent;
		this.rank = rank;
	}
}

/**
 * Union-Find Data structure
 * 
 */
public class UnionFind {
	// Node Holder having UFNode
	private UFNode[] nodeHolder;
	private final int numVertices;
	// number of node (Cluster Count)
	private int count;

	public UnionFind(int size) {
		if (size < 0)
			throw new IllegalArgumentException();

		count = size;
		numVertices = size;
		nodeHolder = new UFNode[size];
		for (int i = 0; i < size; i++) {
			nodeHolder[i] = new UFNode(i, 1); // default values, node points to
			// itself and rank is 1
		}
	}

	/**
	 * Finds the parent of a given vertex, using recursion
	 * 
	 * @param vertex
	 * @return
	 */
	public int Find(int vertex) {
		if (vertex < 0 || vertex >= nodeHolder.length)
			throw new IndexOutOfBoundsException();

		if (nodeHolder[vertex].parent != vertex)
			nodeHolder[vertex].parent = Find(nodeHolder[vertex].parent);

		return nodeHolder[vertex].parent;
	}

	/**
	 * @return the cluster count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param v1
	 *            : vertex 1 of some cluster
	 * @param v2
	 *            : vertex 2 of some cluster
	 * @return true if both vertex have same parent
	 */
	public boolean isConnected(int v1, int v2) {
		return Find(v1) == Find(v2);
	}

	/**
	 * unions two cluster of two vertices
	 * 
	 * @param v1
	 * @param v2
	 */
	public void Union(int v1, int v2) {
		int i = Find(v1);
		int j = Find(v2);

		if (i == j)
			return;

		if (nodeHolder[i].rank < nodeHolder[j].rank) {
			nodeHolder[i].parent = j;
			nodeHolder[j].rank = nodeHolder[j].rank + nodeHolder[i].rank;
		} else {
			nodeHolder[j].parent = i;
			nodeHolder[i].rank = nodeHolder[i].rank + nodeHolder[j].rank;
		}
		count--;
	}

	public void printCluster1() {
		Map<Integer, ArrayList<Integer>> leaders = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < nodeHolder.length; i++) {
			leaders.put(nodeHolder[i].parent, null);
		}

		for (int i = 0; i < numVertices; i++) {
			int leader = Find(i);
			ArrayList<Integer> leaderList = leaders.get(Find(i));
			if (leaderList == null) {
				leaderList = new ArrayList<Integer>();
			}
			leaderList.add(i);
			leaders.put(leader, leaderList);

		}

		Set<Map.Entry<Integer, ArrayList<Integer>>> set = leaders.entrySet();

		printCluster2(set);

	}

	private void printCluster2(Set<Entry<Integer, ArrayList<Integer>>> set) {
		System.out.println("Following is the cluster");
		System.out.println(" { ");
		for (Map.Entry<Integer, ArrayList<Integer>> entry : set) {

			int key = entry.getKey();
			System.out.print(key + " :[ ");
			ArrayList<Integer> val = entry.getValue();
			for (int i : val) {
				System.out.print(i + " ,");
			}
			System.out.println(" ] ");
		}
		System.out.println(" } ");
	}

}