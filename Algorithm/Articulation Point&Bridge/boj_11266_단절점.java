/*
 * 참고 : https://bowbowbow.tistory.com/3
 * 
 * 
 */


import java.io.*;
import java.util.*;
public class Main {
	static int v,e,discovered[],a,b,cnt;
	static boolean isCutVertex[];
	static ArrayList<Integer> graph[];
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		v=Integer.parseInt(st.nextToken());
		e=Integer.parseInt(st.nextToken());
		
		discovered=new int[v+1];
		isCutVertex=new boolean[v+1];
		graph=new ArrayList[v+1];

		for(int i=0;i<=v;++i) {
			graph[i]=new ArrayList<>();
		}
		
		for(int i=1;i<=e;++i) {
			st=new StringTokenizer(br.readLine());
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		cnt=0;
		for(int i=1;i<=v;++i) 
			if(discovered[i]==0)
				dfs(i,true);
		
		cnt=0;
		for(int i=1;i<=v;++i)
			if(isCutVertex[i])
				cnt++;
		
		bw.write(cnt+""+'\n');
		for(int i=1;i<=v;++i)
			if(isCutVertex[i])
				bw.write(i+" ");
		
		bw.flush();
	}
	static int dfs(int vertex, boolean isRoot) {
		/* 자기보다 앞에 탐색할수 있는 경우가 있으면 단절점이 되지 않는다. */
        /* DFS스패닝트리를 만들면서 기존 트리는 그대로 사용됨 없어지는 것이 아님*/
        /* DFS스패닝 트리의 역할은 순서를 지정해 주는 것과 
         * DFS스패닝 트리에서 루트가 자식을 2개 가지는지 체크 */
		
		discovered[vertex]= ++cnt;
		int temp=discovered[vertex];
		  // 자기랑 인접노드 중에서 가장 빨리 방문되는 노드의 순서를 저장하는 변수
		
		int child=0;// 루트 노드일 경우 스패닝트리에서 자식수
		
		for(int i=0;i<graph[vertex].size();++i) {
			int next=graph[vertex].get(i).intValue();
			
			if(discovered[next]==0) {
				child++;
				int fastest=dfs(next,false);
				 // 자식 노드가 갈수 있는 노드중 가장 일찍 방문한 노드
                // 중간에 dfs 한다는 것은 정점의 끝까지 간다는 것을 의미
				
				
				if(!isRoot && fastest>=discovered[vertex]) {
					isCutVertex[vertex]=true;
					// fastest가 자기의 순서보다 늦거나 같은 경우는 
	                //즉 자기보다 앞에 있는 경로는 자기를 통해서 밖에 못간다.
					//단절점!
	 
				}
				temp=Math.min(temp, fastest);
			}	// 이미 방문한 정점과 temp값 비교 최소값 저장
			else {
				temp=Math.min(temp, discovered[next]);
			}
		}
		if(isRoot && child>=2)
			isCutVertex[vertex]=true;
		// 루트는 위의 방법으로 확인할 수가 없기 때문에 스패닝 트리에서
        // 자식이 두개 있다는 것은 단절점이다.
		
		return temp;
		// dfs에서 다른 노드 값을 얻고 싶을때 return 아래에 사용
	}
}
