/*
1 2
2 3
3 4
4 2
들어가면 최종적으로
adjList[1]=(2,null)
adjList[2]=(4,(3,(1,null)))
adjList[3]=(4,(2,null))
adjList[4]=(2,(3,null))



*/



import java.io.*;
import java.util.*;

public class Main {
	static int k, v, e;
	static Node[] adjList;
	static int[] visit;
	static boolean isBipartite;

	static class Node {
		int vertex;
		Node next;

		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			adjList = new Node[v + 1];
			visit = new int[v + 1];

			int from, to;
			for (int j = 0; j < e; j++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, adjList[from]);
				adjList[to] = new Node(from, adjList[to]);
			}

			isBipartite = true;
			for (int j = 1; j <= v && isBipartite; j++) {
				if (visit[j] == 0) {
					isBipartite = bfs(j);
				}
			}
			if (isBipartite)
				System.out.println("YES");
			else
				System.out.println("NO");
		}
		br.close();
	}

	static boolean bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		visit[start] = 1;
		q.offer(start);
		while (!q.isEmpty()) {
			int current = q.poll();
			Node temp = adjList[current];
			while (temp != null) {
				if (visit[temp.vertex] == 0) {
					visit[temp.vertex] = ~visit[current];
					q.offer(temp.vertex);
				} else {
					if (visit[temp.vertex] == visit[current])
						return false;
				}
				temp = temp.next;
			}
		}
		return true;
	}

}
