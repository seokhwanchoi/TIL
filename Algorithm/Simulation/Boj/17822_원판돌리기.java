//1430~  
//mayora 코드 참고

import java.io.*;
import java.util.*;
public class Main {
	static int N,M,T,xi,di,ki;
	static int[][] list;
	static boolean flag, v[][];
	static void removeAdj(int j, int u, int xidx, int yidx ) {
		if(list[j][u]==list[xidx][yidx]) {
			v[j][u]=true;
			v[xidx][yidx]=true;
			flag=true;
		}	
	}
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		T=Integer.parseInt(st.nextToken());
		list=new int[N+1][M];
		for(int i=1;i<=N;++i) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<M;++j) {
				list[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<T;++i) {
			st=new StringTokenizer(br.readLine());
			xi=Integer.parseInt(st.nextToken());
			di=Integer.parseInt(st.nextToken());
			ki=Integer.parseInt(st.nextToken());
			if(di==1)//반시계일경우 시계로 바꿔준다
				ki=M-ki;
			ki=ki%M;//회전반복 최적화
			for(int j=1;j<=N;++j) {
				if(j%xi!=0) continue;
				for(int rotidx=0;rotidx<ki;++rotidx) {
					int temp=list[j][M-1];
					for(int u=M-1;u>0;--u) {
						list[j][u]=list[j][u-1];
					}
					list[j][0]=temp;
				}
			}
	
			//1번 회전완료
			flag=false;
			v=new boolean[N+1][M];
			for(int j=1;j<=N;++j) {
				for(int u=0;u<M;++u) {
					if(list[j][u]==0) continue;
					//맨처음껏하고 끝에꺼 빼고 4개씩 인접 그 두개는 세개씩 인접
					if(j==1) {
						//배열의 양끝쪽은 서로 인접한다 인접한거 한번에 없애는 기능
						if(u==0) {
							removeAdj(j,u,j,M-1);
							removeAdj(j,u,j,u+1);
							removeAdj(j,u,j+1,u);
						}
						else if(u==M-1) {
							removeAdj(j,u,j,0);
							removeAdj(j,u,j,u-1);
							removeAdj(j,u,j+1,u);
						}
						
						else {//배열의 가운데부분들
							removeAdj(j,u,j,u+1);
							removeAdj(j,u,j,u-1);
							removeAdj(j,u,j+1,u);
						}
					}
					
					
					else if(j==N) {
						//배열의 양끝쪽은 서로 인접한다
						if(u==0) {
							removeAdj(j,u,j,M-1);
							removeAdj(j,u,j,u+1);
							removeAdj(j,u,j-1,u);
						
						}
						else if(u==M-1) {
							removeAdj(j,u,j,0);
							removeAdj(j,u,j,u-1);
							removeAdj(j,u,j-1,u);
						}
						
						else {//배열의 가운데부분들
							removeAdj(j,u,j,u+1);
							removeAdj(j,u,j,u-1);
							removeAdj(j,u,j-1,u);
						}
					}
					
					
					//가운데부분들은 다 공통적으로 4개씩 인접
					else {
						//배열의 양끝쪽은 서로 인접한다
						if(u==0) {
							removeAdj(j,u,j,M-1);
							removeAdj(j,u,j,u+1);
							removeAdj(j,u,j-1,u);
							removeAdj(j,u,j+1,u);
						}
						else if(u==M-1) {
							removeAdj(j,u,j,0);
							removeAdj(j,u,j,u-1);
							removeAdj(j,u,j-1,u);
							removeAdj(j,u,j+1,u);
						}
						
						else {//배열의 가운데부분들
							removeAdj(j,u,j,u+1);
							removeAdj(j,u,j,u-1);
							removeAdj(j,u,j-1,u);
							removeAdj(j,u,j+1,u);
						}
					}
				}
			}
			//인접한거 찾기 완료
			
			
			for(int j=1;j<=N;++j) {
				for(int u=0;u<M;++u) {
					if(v[j][u]) {
						list[j][u]=0;
					}
				}
			}//인접한 거 일괄적으로 지워야 - ex)3개짜리중 2개만 미리 지우면 하나는 안지워진다
			//N*M*T이 최대 125000이면 굉장히 작은 횟수. 그냥 따로 모으지 않고 맵 순회하면서 지워준다.

			int sum=0; int cnt=0;
			if(flag) continue;
			else {
				for(int j=1;j<=N;++j) {
					for(int u=0;u<M;++u) {
						if(list[j][u]!=0) {
							sum+=list[j][u];
							cnt++;
						}
					}
				}
				double avg=(double)sum/(double)cnt;
				for(int j=1;j<=N;++j) {
					for(int u=0;u<M;++u) {
						if(list[j][u]!=0) {
							if(list[j][u]>avg)
								list[j][u]-=1;
							else if(list[j][u]<avg)
								list[j][u]+=1;
						}
					}
				}
	
			}
		
		}

		int ans=0;
		for(int j=1;j<=N;++j) {
			for(int u=0;u<M;++u) {
				if(list[j][u]!=0) {
					ans+=list[j][u];
				}
			}
		}	//지나친 최적화는 하지 않는다. a형이므로.. T-1번째에서 이것 처리 해줄수있지만 그냥 안전하게 이렇게 한다.
		System.out.println(ans);
	}
}
