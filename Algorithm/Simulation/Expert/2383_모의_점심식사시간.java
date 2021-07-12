/*
 * 한 공간에 여러 원소들이 있으면 무조건 일단 arrayList[][] 생각
 
  차량정비소랑 비슷하나 그건 단계가 더 많아서 그렇게 순서를 해주었던거고(처리를) 이거는 단계가 많지 않아
  원래 순서대로 구현해도 
 */
import java.io.*;
import java.util.*;
public class Solution {
	static int n,peoplecount,s1x,s1y,s1v,s2x,s2y,s2v,tempcount,tcount,ans;
	static int[] ord;
	static ArrayList<int[]> peo,waitStair1,waitStair2;
	static boolean[] v;
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine().trim());
		int temp;
		for(int tc=1;tc<=T;++tc) {
			s1v=0; s2v=0;
			peo=new ArrayList<>();
			peoplecount=0;
			n=Integer.parseInt(br.readLine().trim());
			for(int i=0;i<n;++i) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;++j) {
					temp=Integer.parseInt(st.nextToken());
					if(temp==1) {
						peo.add(new int[] {i,j,0,0});//
						//계단까지의 거리, 계단 까지 남은거리(초기화 다시 안해줘도 되는 걸로)
						peoplecount++;
					}
					if(temp>=2) {
						if(s1v==0) {
							s1x=i; s1y=j; s1v=temp;
						}
						else {
							s2x=i; s2y=j; s2v=temp;
						}
					}
				}
			}
			ord=new int[peoplecount];
			v=new boolean[peoplecount];
			ans=Integer.MAX_VALUE;
			dfs(0,0);
			sb.append("#").append(tc).append(" ").append(ans).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
	}
	static void dfs(int count,int index) {
		if(count==peoplecount) {
			solve();
			return;
		}
		for(int i=index;i<peoplecount;++i) {
			//0과 1를 뽑고 그걸 배열한다. 조합 후 순열-->조합자체는 중복 허용하지 않음
			// 순열은 중복 허용
			for(int j=0;j<2;++j) {
				ord[count]=j;
				dfs(count+1,i+1);//중복허용x조합 ->i+1
					//순열 중복 허용 -->v 쓰지 않음
			}
			
		}
	}
	static void solve() {
		tempcount=0;
		for(int i=0;i<peoplecount;++i) {
			if(ord[i]==0) {
				peo.get(i)[2]=Math.abs(s1x-peo.get(i)[0])
						+Math.abs(s1y-peo.get(i)[1]);
				peo.get(i)[3]=peo.get(i)[2];
			}
			else {
				peo.get(i)[2]=Math.abs(s2x-peo.get(i)[0])
						+Math.abs(s2y-peo.get(i)[1]);
				peo.get(i)[3]=peo.get(i)[2];
			}
		}
		waitStair1=new ArrayList<>();
		waitStair2=new ArrayList<>();
		loop:while(true) {
			tempcount++;
			for(int i=0;i<peoplecount;++i) {
				if(peo.get(i)[3]>0) peo.get(i)[3]--;
				else if(peo.get(i)[3]==0) {
					if(ord[i]==0) {
						waitStair1.add(new int[] {s1v});
						peo.get(i)[3]=-1;//계단 속으로!
					}
					else {
						waitStair2.add(new int[] {s2v});
						peo.get(i)[3]=-1;//계단 속으로!
					}
				}
				
			}
			//계단은 따로 처리
			for(int j=0;j<waitStair1.size();++j) {
				if(j>=3) break;
				if(waitStair1.get(j)[0]>0)waitStair1.get(j)[0]--;
				else if(waitStair1.get(j)[0]==0) {
					waitStair1.remove(j);
					j--;
				}
			}
			for(int j=0;j<waitStair2.size();++j) {
				if(j>=3) break;
				if(waitStair2.get(j)[0]>0)waitStair2.get(j)[0]--;
				else if(waitStair2.get(j)[0]==0) {
					waitStair2.remove(j);
					j--;
				}
			}
			for(int i=0;i<peoplecount;++i) {
				if(peo.get(i)[3]!=-1) continue loop;
			}
			if(waitStair1.size()!=0 ||waitStair2.size()!=0) continue loop;
			break;
		}
		if(ans>tempcount) ans=tempcount;
	}
}
