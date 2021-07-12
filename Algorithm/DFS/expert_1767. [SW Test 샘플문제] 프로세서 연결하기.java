/*
쟁점1. 조합만들어놓고 solve하는 것보다 조합돌리면서 그때그때 solve해야 시간초과 안나는 문제

쟁점2. dfs -- visted바로 앞뒤에 해주는거

쟁점3. list같은데다가 바꿀 것 모았다가 for문으로 true false앞뒤로 해주면 확실히 틀릴확률은 적지만
시간손해는 본다.확실히

쟁점4. ArrayList 로 true false 앞뒤로 모아주는 거 할때 초기화 위치
그 밖에 dfs에 필요한 초기화 위치 꼼꼼히 이것때문에 계속 
쌓여서 미묘하게 틀릴수있다. 답이일단 계속 테케마다 증가하면 의심해보기
특히 arraylist 주의 계속 쌓이는데 눈치채기힘들다
변수는 아예쌩뚱맞은거라 답이 터무니없는데
arraylist는 디버깅 직접해보기전까지는 찾기도 힘들다
한개 2개 쌓여있으면

쟁점5. 어떤 타켓숫자에 맞는게 나왔으면 그다음은 안하는
백트래킹기법 필수--엄청난 시간차이

쟁점6.if else dfs다해주려면 선택하고 안하고 다가본다는 건데
이왕이면 if에서 선택한것만 가볼수있게(시간 및 로직 어려움)

쟁점7.맵이왕이면 테케마다 갱신안하게(크면 클수록)

쟁점 8.마지막 답 도출시 >=를 한꺼번에 처리하지말고 >와 = 나눠서 처리

*/

import java.io.*;
import java.util.*;
public class Solution {
	static int n,map[][],maxCore, ans;
	static ArrayList<int[]> list;
	static boolean isExisted;
	static int[][] dir= {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine().trim());
		map=new int[12][12];
		for(int tc=1;tc<=T;++tc) {
			list=new ArrayList<>();
			n=Integer.parseInt(br.readLine().trim());
			for(int i=0;i<n;++i) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;++j) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]==1) {
						if(i!=0 && i!=n-1 && j!=0 && j!=n-1)  list.add(new int[] {i,j});
					}
				//가장자리는 제외
					//1개의 cell은 한개전선+한개cell 이렇게는 못온다
					//즉 core가 있는자리 전선 못지나감
				}
			}
			maxCore=-1;
			isExisted=false;
			//조합
			for(int i=list.size();i>=0;--i) {
				if(isExisted)break;
				dfs(0,0,0,i);
			}
			
			
			bw.write("#"+tc+" "+ans+'\n');
		}
		bw.flush();
	}
	static void dfs(int idx,int len, int cnt, int target) {
		if(maxCore>target) return;
		if(cnt==target) {
			//최대의 전원에 최소의 전선
			if(maxCore<cnt) {//같은 전원이어도 전선이 적을수있으므로
				isExisted=true;
				maxCore=cnt;
				ans=len;
			}
			else if(maxCore==cnt&& ans>len) {
				ans=len;
			}
			
			return;
		}
		for(int i=idx;i<list.size();++i) {
				
				int[] curr=list.get(i);
				//놓을수있는지 한칸씩 가봐야 한다.(그냥단순 떨어져있는 거리로 하면 안됨)
				int x=0; int y=0;
				for(int j=0;j<4;++j) {
					ArrayList<int[]> temp=new ArrayList<>();//전선되돌릴 것들
					x=curr[0]+dir[j][0];
					y=curr[1]+dir[j][1];
					
					
					//일단 한칸 가보고 그다음 while로 쭉 가보자
					while(x>=0 && y>=0 && x<n && y<n && map[x][y]==0) {
						temp.add(new int[] {x,y});
						x+=dir[j][0];
						y+=dir[j][1];
						
					}
					
					//갈수있는데까지 갔는데 가장자리고 거기도 빈칸이면
					if(x<0 || y<0 || x>=n || y>=n) {
						//전선놔주자
						int tempcnt=0;
						for(int k=0;k<temp.size();++k) {
							map[temp.get(k)[0]][temp.get(k)[1]]=2;
							tempcnt++;
						}
						
						//dfs
						dfs(i+1,len+tempcnt,cnt+1,target);
						
						for(int k=0;k<temp.size();++k) {
							map[temp.get(k)[0]][temp.get(k)[1]]=0;
						}
						
					}
//					else {
//						//전선을 못놓는 상황이어도 dfs는 해야함. 단 전선개수,연결된 전원개수는 그대로
//						dfs(i+1,len,cnt,target);
//						
//					}
				}
			
		}
	}

}
