/*
모든 '파티'의 멤버를 전부 disjoint-set을 거치게 한 뒤

마지막에 각 파티를 체크해야 하는 문제였습니다.

parents 배열의 초기값을 -1로 한 뒤,

진실을 알고 있는 사람의 parents 배열의 값을 해당 index로 바꾸는 작업을 통해

하나의 parents 배열로 모든 경우의 수를 체크할 수 있도록 구현했습니다.

따라서 parents[temp]의 값이 -1일 때와 temp 일 때 두 개를 합쳐서 체크했습니다.
*/

import java.io.*;
import java.util.*;

public class Main {

	static int N, M, result = 0;
	static int[] parents;
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		init();

		st = new StringTokenizer(br.readLine());
		int num = stoi(st.nextToken());
		for (int i = 0; i < num; i++) {
			int temp = stoi(st.nextToken());
			parents[temp] = temp;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			num = stoi(st.nextToken());
			for (int j = 0; j < num; j++) {
				list[i].add(stoi(st.nextToken()));
			}
			if (list[i].size() >= 2) {
				disjointSet(i);
			}
		}

		for (int i = 0; i < M; i++) {
			if (!checkLier(i)) {
				result++;
			}
		}
		System.out.println(result);
	}

	private static void disjointSet(int R) {
		int a = list[R].get(0);
		for (int i = 1; i < list[R].size(); i++) {
			union(a, list[R].get(i));
		}
	}

	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot != bRoot) {
			if (aRoot == parents[aRoot]) {
				parents[bRoot] = aRoot;
			} else {
				parents[aRoot] = bRoot;
			}
		}
	}

	private static int find(int a) {
		if (parents[a] < 0 || parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

	private static boolean checkLier(int R) {
		for (int i = 0; i < list[R].size(); i++) {
			int n = find(list[R].get(i));
			if (parents[n] == n) {
				return true;
			}
		}
		return false;
	}

	private static void init() {
		list = new ArrayList[M];
		parents = new int[N + 1];
		for (int i = 0; i < M; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			parents[i] = -1;
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}

