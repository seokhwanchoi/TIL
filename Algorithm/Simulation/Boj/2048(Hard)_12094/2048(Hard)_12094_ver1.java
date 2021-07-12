//가장 짧은시간 코드 1등(900ms) 이거,,,  2등은 (1800ms) 2배가량 빠른 코드
//입맞에 맞게 수정후 
//이차원 배열 int[][] map일 때 세로길이는 map.length로 하면 되는 모양..
//더 알아보기 (가로길이는 map[0].length)


import java.util.*;
import java.io.*;

public class Main {
	static int N, ans;
	static int[][][] board;
	
	public static void solve(int depth) {
		if (depth == 10) {
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (nextState(depth + 1, i)) {
				int max = getMax(board[depth + 1]);
				ans = Math.max(ans, max);
				
				//지금나온 ans가 현상황에서 도출된 max값에서 
				//깊이만큼 2배*2배...된값보다 같거나 크면
				//더이상 진행해볼필요가 없다는 뜻
				//문제조건 잘 생각해보기??
				//이 밑에 3줄이 가장 핵심. 다른 코드보다 1000ms빠른 비결
				//@@@@@@@@@@@@
				if (ans < max << (9 - depth)) {
					solve(depth + 1);
				}
				//@@@@@@@@
				
				
			}
		}
	}
	
	public static boolean nextState(int depth, int direction) {
		for (int i = 0; i < N; i++) {
			Arrays.fill(board[depth][i], 0);
		}
		boolean update = false;
		switch (direction) {
		case 0:
			for (int i = 0; i < N; i++) {
				int idx = 0;
				boolean flag = false;
				for (int j = 0; j < N; j++) {
					if (board[depth - 1][i][j] == 0) {
						continue;
					}
					if (!flag && idx > 0 && board[depth][i][idx - 1] == board[depth - 1][i][j]) {
						board[depth][i][idx - 1] <<= 1;
						update = true;
						flag = true;
					} else {
						board[depth][i][idx] = board[depth - 1][i][j];
						update |= idx++ != j;
						flag = false;
					}
				}
			}
			break;
		case 1:
			for (int i = 0; i < N; i++) {
				int idx = N - 1;
				boolean flag = false;
				for (int j = N - 1; j >= 0; j--) {
					if (board[depth - 1][i][j] == 0) {
						continue;
					}
					if (!flag && idx < N - 1 && board[depth][i][idx + 1] == board[depth - 1][i][j]) {
						board[depth][i][idx + 1] <<= 1;
						update = true;
						flag = true;
					} else {
						board[depth][i][idx] = board[depth - 1][i][j];
						update |= idx-- != j;
						flag = false;
					}
				}
			}
			break;
		case 2:
			for (int i = 0; i < N; i++) {
				int idx = 0;
				boolean flag = false;
				for (int j = 0; j < N; j++) {
					if (board[depth - 1][j][i] == 0) {
						continue;
					}
					if (!flag && idx > 0 && board[depth][idx - 1][i] == board[depth - 1][j][i]) {
						board[depth][idx - 1][i] <<= 1;
						update = true;
						flag = true;
					} else {
						board[depth][idx][i] = board[depth - 1][j][i];
						update |= idx++ != j;
						flag = false;
					}
				}
			}
			break;
		case 3:
			for (int i = 0; i < N; i++) {
				int idx = N - 1;
				boolean flag = false;
				for (int j = N - 1; j >= 0; j--) {
					if (board[depth - 1][j][i] == 0) {
						continue;
					}
					if (!flag && idx < N - 1 && board[depth][idx + 1][i] == board[depth - 1][j][i]) {
						board[depth][idx + 1][i] <<= 1;
						update = true;
						flag = true;
					} else {
						board[depth][idx][i] = board[depth - 1][j][i];
						update |= idx-- != j;
						flag = false;
					}
				}
			}
			break;
		}
		return update;
	}
	
	public static int getMax(int[][] board) {
		int max = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (max < board[i][j]) {
					max = board[i][j];
				}
			}
		}
		return max;
	}
	
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[11][N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[0][i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = getMax(board[0]);
		solve(0);
		
		System.out.println(ans);
		
	}
}
