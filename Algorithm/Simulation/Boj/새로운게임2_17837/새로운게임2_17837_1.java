import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	static class Node {
		int row;
		int col;
		int dir;

		Node(int r, int c, int d) {
			this.row = r;
			this.col = c;
			this.dir = d;
		}
	}

	static int N, K, color[][], turn;
	static int dir[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	static Node list[];
	static ArrayList<Node> map[][];

	// 0흰색 1빨간색 2파란색
	// 0오른쪽 1왼쪽 2위 3아래
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		color = new int[N + 2][N + 2];
		map = new ArrayList[N + 2][N + 2];
		list = new Node[K];

		for (int i = 0; i <= N + 1; ++i) {
			if (i != 0 && i != N + 1)
				st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= N + 1; ++j) {
				if (i == 0 || i == N + 1 || j == 0 || j == N + 1) {
					color[i][j] = 2;  //바깥으로 나가는 것과 파란색 체스판은 같은 처리를 해주므로
          //맵을 위 아래 왼 오른 각각 1칸씩 늘리고 각 경계를 파란색으로 본 구현
          //이렇게하면 구현량을 상당히 줄일 수 있다. @@@Good Idea
				} else {
					color[i][j] = Integer.parseInt(st.nextToken());
					map[i][j] = new ArrayList<Node>();
				}
			}
		}

		for (int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());
			Node node = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()) - 1);
			list[i] = node;
			map[node.row][node.col].add(node);
		}

		turn = 0;

		loop: while (++turn <= 1000) {
			for (int i = 0; i < K; ++i) {
				int nowRow = list[i].row;
				int nowCol = list[i].col;
				int nextRow = nowRow + dir[list[i].dir][0];
				int nextCol = nowCol + dir[list[i].dir][1];

				if (color[nextRow][nextCol] == 2) {
					if (list[i].dir == 0)
						list[i].dir = 1;
					else if (list[i].dir == 1)
						list[i].dir = 0;
					else if (list[i].dir == 2)
						list[i].dir = 3;
					else if (list[i].dir == 3)
						list[i].dir = 2;
					nextRow = nowRow + dir[list[i].dir][0];
					nextCol = nowCol + dir[list[i].dir][1];
				}
				int idx = 0;

				switch (color[nextRow][nextCol]) {
				case 0:
					idx = map[nowRow][nowCol].indexOf(list[i]);
					for (int j = idx; j < map[nowRow][nowCol].size(); ++j) {
						Node tmp = map[nowRow][nowCol].get(j);
						tmp.row = nextRow;
						tmp.col = nextCol;
						map[nextRow][nextCol].add(tmp);
					}
					for (int j = map[nowRow][nowCol].size() - 1; j >= idx; --j) {
						map[nowRow][nowCol].remove(j);
					}
					if (map[nextRow][nextCol].size() >= 4)
						break loop;
					break;
				case 1:
					idx = map[nowRow][nowCol].indexOf(list[i]);
					for (int j = map[nowRow][nowCol].size() - 1; j >= idx; --j) {
						Node tmp = map[nowRow][nowCol].get(j);
						tmp.row = nextRow;
						tmp.col = nextCol;
						map[nextRow][nextCol].add(tmp);
					}
					for (int j = map[nowRow][nowCol].size() - 1; j >= idx; --j) {
						map[nowRow][nowCol].remove(j);
					}
					if (map[nextRow][nextCol].size() >= 4)
						break loop;
					break;
				case 2:

					break;
				}
			}
		}
		System.out.println(turn <= 1000 ? turn : -1);

	}
}
