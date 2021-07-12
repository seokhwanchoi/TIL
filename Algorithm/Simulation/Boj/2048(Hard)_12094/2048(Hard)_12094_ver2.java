import java.io.*;
import java.util.*;
//3차원 배열 메모리초과 가능성 왠만하면 지양 -- 상황마다 다름.. 인덱스별로. __7562 나이트의 이동 참고
public class Main{
	static int n,ans;
	static int[][] map;
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		ans=Integer.MIN_VALUE;
		StringTokenizer st;
		for(int i=0;i<map.length;++i) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;++j) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(ans<map[i][j])
					ans=map[i][j];
			}
		}
		solve(0);
		
		System.out.println(ans);
		
	}
	static void solve(int depth) {
		if(depth==10) {
			//코드 너무 복잡하면 여기서 한번에 tempmax뽑아도됨. 밑에서 그때그때 안뽑고
			for(int i=0;i<n;++i) {
				for(int j=0;j<n;++j) {
					if(ans<map[i][j])
						ans=map[i][j];
				}
				
			}
			return;
		}
    
    //@@@@@@@@@@@@@2
		int[][] tempmap=new int[n][n];
    
    //인자로 넘겨주지 않는한
		//depth 1에서의 tempmap과 depth 2에서의 tempmap을 다르게 할 수 있다.
		//즉 인자로 넘겨주지 않으면 call by reference와 유사한 현상이 일어나지 않는다.
		//다만 이 tempmap을 static변수로 선언하지 않도록 주의!
		//그때그때 만들어야 하는 비효율성이 있지만 이걸 static변수로 만들거나 인자로 넘겨주면 틀리니 명심
		
    for(int j=0;j<tempmap.length;++j) {
			tempmap[j]=map[j].clone();
		}
    //@@@@@@@@@@@@@2
    
		for(int i=0;i<4;++i) {
			if(nextState(depth+1,i,tempmap)) {
				solve(depth+1);
				for(int j=0;j<map.length;++j) {
					map[j]=tempmap[j].clone();
				}
			}
		}
	}
	static boolean nextState(int depth, int dir,int[][] tempmap) {
		boolean update=false;
		switch (dir) {
		case 0://상
			for(int j = 0; j < n; j++) {
				int cursor = 0;
				int value = 0;
				for(int k = 0; k < n; k++) {
					if(map[k][j] == 0)
						continue;
					if(value == 0) {
						value = map[k][j];
						map[k][j] = 0;
						continue;
					}
					if(value == map[k][j]) {
						map[cursor][j] = value*2;
						ans = Math.max(map[cursor][j], ans);
						update=true;
						map[k][j] = value = 0;
						cursor++;
						
					}else {
						if(tempmap[cursor][j]!=map[cursor][j]) update=true;
						map[cursor][j] = value;
						cursor++;
						value = map[k][j];
						map[k][j] = 0;
					}
				}
				map[cursor][j] = value;
			}
			break;
      
		case 1://하
			for(int j = 0; j < n; j++) {
				int cursor = n-1;
				int value = 0;
				for(int k = n-1; k >= 0; k--) {
					if(map[k][j] == 0)
						continue;
					if(value == 0) {
						value = map[k][j];
						map[k][j] = 0;
						continue;
					}
					if(value == map[k][j]) {
						map[cursor][j] = value*2;
						ans = Math.max(map[cursor][j], ans);
						update=true;
						map[k][j] = value = 0;
						cursor--;
					}else {
						if(tempmap[cursor][j]!=map[cursor][j]) update=true;
						map[cursor][j] = value;
						cursor--;
						value = map[k][j];
						map[k][j] = 0;
					}
				}
				map[cursor][j] = value;
			}
			break;
      
		case 2://좌
			for(int j = 0; j < n; j++) {
				int cursor = 0;
				int value = 0;
				for(int k = 0; k < n; k++) {
					if(map[j][k] == 0)
						continue;
					if(value == 0) {
						value = map[j][k];
						map[j][k] = 0;
						continue;
					}
					if(value == map[j][k]) {
						map[j][cursor] = value*2;
						ans = Math.max(map[j][cursor], ans);
						update=true;
						map[j][k] = value = 0;
						cursor++;
					}else {
						if(tempmap[j][cursor]!=map[j][cursor]) update=true;
						map[j][cursor] = value;
						cursor++;
						value = map[j][k];
						map[j][k] = 0;
					}
				}
				map[j][cursor] = value;
			}
			break;
      
		case 3://우
			for(int j = 0; j < n; j++) {
				int cursor = n-1;
				int value = 0;
				for(int k = n-1; k >= 0; k--) {
					if(map[j][k] == 0)
						continue;
					if(value == 0) {
						value = map[j][k];
						map[j][k] = 0;
						continue;
					}
					if(value == map[j][k]) {
						map[j][cursor] = value*2;
						ans = Math.max(map[j][cursor], ans);
						update=true;
						map[j][k] = value = 0;
						cursor--;
					}else {
						if(tempmap[j][cursor]!=map[j][cursor]) update=true;
						map[j][cursor] = value;
						cursor--;
						value = map[j][k];
						map[j][k] = 0;
						
					}
				}
				map[j][cursor] = value;
			}
			break;

		}

		return update;
	}

}
