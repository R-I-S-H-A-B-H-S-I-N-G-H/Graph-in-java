import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Graph {

	Node[] nodes;
	int end;
	int start;

	Graph(int nodes) {
		this.end = nodes;
		this.start = 0;
		this.nodes = new Node[nodes];
	}

	void addNode(Node n) {
		if (this.start < this.end) {
			this.nodes[this.start] = n;
			this.start += 1;
		} else {
			System.out.println("Nodes Array full");
		}
	}

	void showNodes() {
		if (this.start != 0) {
			System.out.println("These are the nodes");
			for (int i = 0; i < this.nodes.length; i++) {
				System.out.println("Node: " + i);
				for (int j = 0; j < this.nodes[i].neighbours.length; j++) {
					String str = (j < this.nodes[i].neighbours.length - 1)
						? " <===> "
						: "";
					System.out.print(this.nodes[i].neighbours[j] + str);
				}
				System.out.println("");
				System.out.println("--------------------");
				System.out.println("");
			}
		} else {
			System.out.println("No Nodes to show");
		}
	}

	void bfs(int source, int destination) {
		Queue<Integer> q = new LinkedList<>();
		Queue<Integer> parent = new LinkedList<>();
		q.add(source);
		this.nodes[source].visited = true;
		while (!q.isEmpty()) {
			int node = q.poll();
			// System.out.println(node);
			if (node == destination) {
				System.out.println("Found");
				break;
			}
			int edges[] = this.nodes[node].neighbours;
			for (int i = 0; i < edges.length; i++) {
				if (!this.nodes[edges[i]].visited) {
					this.nodes[edges[i]].visited = true;
					this.nodes[edges[i]].parent = node;
					q.add(edges[i]);
				}
			}
		}

		System.out.println("all the parents areeee:::");
		System.out.println("");
		int lastp = this.nodes[destination].parent;
		parent.add(destination);
		String path = Integer.toString(destination);

		while (lastp != -1) {
			parent.add(lastp);
			path = Integer.toString(lastp) + "=>" + path;
			lastp = this.nodes[lastp].parent;
		}
		System.out.println(parent);
		System.out.println(path);
		// for (int i = 0; i < this.nodes.length; i++) {
		// 	System.out.println(this.nodes[i].parent);
		// }
	}

	void dfs(int source, int destination) {}

	public static void main(String[] args) {
		int[] nodeNeighbours;
		System.out.println("enter the number of nodes");
		Scanner kb = new Scanner(System.in);
		int Graphlen = kb.nextInt();
		Graph obj = new Graph(Graphlen);
		for (int i = 0; i < Graphlen; i++) {
			// System.out.println("Enter the neighbour count of node:" + i);

			//creating a new node
			Node node = new Node(kb.nextInt());

			//adding neighbous of the new node
			// System.out.println("Enter the neighbours for node" + i);
			nodeNeighbours = new int[node.neighbours.length];
			for (int j = 0; j < node.neighbours.length; j++) {
				nodeNeighbours[j] = kb.nextInt();
			}
			node.addNeighbours(nodeNeighbours);

			//adding the node to graph
			obj.addNode(node);
			// System.out.println("---------------------------------------");
		}
		// obj.showNodes();
		// System.out.println("Showing the parents");
		obj.bfs(0, 5);
		kb.close();
	}
}

class Node {

	boolean visited;
	int[] neighbours;
	int parent;

	Node(int n) {
		this.visited = false;
		this.neighbours = new int[n];
		this.parent = -1;
	}

	void addNeighbours(int[] neighbour) {
		if (neighbour.length == this.neighbours.length) {
			this.neighbours = neighbour;
		} else {
			System.out.println("your array " + neighbour.length);
			System.out.println("this array " + this.neighbours.length);

			System.out.print("Try endering " + this.neighbours.length + " elements");
		}
	}
}
