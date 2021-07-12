/*
 BFS안에서는, 시작점을 기준으로 모든 정점을 비교하면서, 시작점과 연결되어있으면서도 아직 방문하지 않은 점을

     계속해서 Queue에 넣어주면서 BFS를 반복시켜 주면 된다.

     여기서 생각을 하나 해야할 것이, 문제가 가장 적은 단계로 모든 사람들과 연결될 수 있는 사람을 구하는 것이다. 따라서,

     값을 계속해서 갱신할 수 있는 배열을 하나 사용하였다. Connect[] 라는 1차원 배열인데, 이 배열은 시작점으로 부터의

     거쳐지는 다리의 수를 계속해서 더해가는 것이다.

     예를 들어서, A와B가 친구, B와 C가 친구이면, Connect[B] = 1, Connect[C] = Connect[B] + 1 이런식으로, 

     물론 실제로는 저 배열 인덱스가 들어가는 부분에 영어가 들어갈 수는 없지만 편의상 이렇게 설명하겠다.

     이런식으로 A로부터의 모든 관계를 다 구한후에, A의 총 다리의 수를 더해주는 과정만 있으면 된다.

     자세한건, 코드를 보면 이해가 쉬울 것이다. 총 다리의 수를 저장하는 변수는 Result_BFS[]라는 배열을 사용하였다.
*/


import java.io.*;
import java.util.*;
public class Main {
	static int n,m,adj[][],a,b,resultBfs[],connect[];
	static boolean[] v;
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		adj=new int[n+1][n+1];
		for(int i=0;i<m;++i) {
			st=new StringTokenizer(br.readLine());
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			adj[a][b]=1;
			adj[b][a]=1;
		}
		
		
		resultBfs=new int[n+1];
		
		for(int i=1;i<=n;++i) {
			
			
			bfs(i);
			for(int j=1;j<=n;++j) {
				resultBfs[i]+=connect[j];
			}
		}
		
		int ans=1;
		int tempval=resultBfs[1];
		for(int i=2;i<=n;++i) {
			if(resultBfs[i]<=tempval) {
				if(resultBfs[i]==tempval) {
					continue;
				}
				else {
					ans=i;
					tempval=resultBfs[i];
				}
			}
				
		}
		
		System.out.println(ans);
	}
	static void bfs(int x) {
		LinkedList<Integer> q=new LinkedList<>();
		v=new boolean[n+1];
		connect=new int[n+1];
		
		q.offer(x);
		v[x]=true;
		
		while(!q.isEmpty()) {
			int curr=q.poll();
			
			for(int i=1;i<=n;++i) {
				if(adj[curr][i]==1 && !v[i]) {
					v[i]=true;
					q.offer(i);
					connect[i]=connect[curr]+1;
				}
			}
		}
	}
}
