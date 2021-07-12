import java.io.*;
import java.util.*;
 
public class Main {
	static class Network{
		int start;
		int end;
		int cost;
		public Network(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}
	static int n, m, ans, cnt;
	static char[][] map;
	static int[] parent;
	
	static int[][] node;
	static Network[] edge;
	
	static int[][] count;
	static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        String s;
        int nodeCnt=0;//정점의 개수
        map=new char[n+1][n+1];
        node=new int[m+1][3];
        for(int i=0; i<n; i++){
        	s = br.readLine();
        	for(int j=0; j<n; j++){
        		map[i][j]= s.charAt(j);
        		if(map[i][j]!='0' && map[i][j]!='1'){
        			node[nodeCnt][0]=i; 
        			node[nodeCnt][1]=j; 
        			node[nodeCnt][2]=nodeCnt;
        			nodeCnt++;
        		}
        	}
        }
        
        count=new int[n+1][n+1];
        edge=new Network[(m+1)*m/2];//정점끼리 만들수있는 총 간선의 개수
        //여기서 +1, -1해줘도 바로 nullpointerException
        //그냥 크게 40000정도로 주려면(최대범위 251*250/2 하면 대략 31000얼마이므로)
        //arrays.sort할때 (edge, 0, edgeCnt ~ )이런식으로
        //딱 그 간선만큼만 sort해줘야한다.(왜냐면 전 범위 다 new Network한게 아니므로)
        
        
        int edgeCnt = 0;//로봇 혹은 열쇠끼리의 간선의 개수
        for(int f=0; f<m; f++){//마지막 열쇠 제외하고 출발시켜보기
        	bfs(node[f]);
        	int temp = edgeCnt;
        	
        	for(int t=f+1; t<=m; t++){
        		if(count[node[t][0]][node[t][1]]!=0) {//그 노드까지 갈 수 있으면
        			edge[edgeCnt++] = 
                			new Network(node[f][2],
                					node[t][2], 
                					count[node[t][0]][node[t][1]]-1);
        		}
        	}
        	
        	if(temp==edgeCnt) {//못찾는 열쇠가 하나라도 있으면
        		System.out.println("-1"); 
        		return;
        	}
        }
        
        if(edgeCnt<m) {//못찾는 열쇠가 하나라도 있으면
        	System.out.println("-1"); 
        	return;
        }
        
        //모든 열쇠를 찾을 수 있다면~

        parent = new int[nodeCnt];
        
        for(int i=0; i<nodeCnt; i++) 
        	parent[i]=i;
        
        //cnt=0;
        
        Arrays.sort(edge, new Comparator<Network>() {//거리 오름차순으로 sort
			public int compare(Network o1, Network o2) {
				if(o1.cost>o2.cost)
					return 1;
				else if(o1.cost==o2.cost)
					return 0;
				else
					return -1;
			}
		});
        
        for(int i=0; i<edgeCnt; i++){
        	//if(cnt>=m) break;
        	
        	//부모 찾아서 연결시키는 과정에서 ans구하는건데
        	//한 정점 하나 부모 찾아서 연결될때마다 cnt++해주는데
        	//cnt가 정점개수보다 같거나 클수는 없다.(마지막 정점은
        	//이미 부모가 자기 자신이므로 cnt++안됨)
        	//괜히 헷갈리니까 없애자 없애도 돌아간다.
        	
        	if(union(edge[i].start, edge[i].end)){
        		ans+=edge[i].cost;
        	}
        }
        if(ans==0) ans=-1;
        System.out.println(ans);
    }
    
    static void bfs(int[] from){
    	LinkedList<int[]> q = new LinkedList<>();
    	count=new int[n+1][n+1];
    	
    	q.offer(new int[] {from[0], from[1]});
    	count[from[0]][from[1]]=1;
    	
    	while(!q.isEmpty()){
    		int curr[] = q.poll();
    		for(int i=0; i<4; i++){
    			int x = curr[0]+dir[i][0];
    			int y = curr[1]+dir[i][1];
    			if(x>=0 && x<n && y>=0 && y<n
    					&& map[x][y]!='1' && count[x][y]==0){
    				q.offer(new int[] {x,y});
    				count[x][y]=count[curr[0]][curr[1]]+1;
    			}
    		}
    	}
    }

    public static int find(int x){
    	if(x==parent[x])
    		return x;
    	else
    		return parent[x]=find(parent[x]);
    }
    public static boolean union(int x, int y){
    	int a = find(x);
    	int b = find(y);
    	if(a==b) 
    		return false; //같은 그룹일 떄
    	
    	cnt++;//각 정점들을 부모찾아서 연결시켜준다.
    	parent[a]=b;
    	return true;
    }
}


