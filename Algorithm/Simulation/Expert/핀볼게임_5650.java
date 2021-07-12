/*
<엑스퍼트 핀볼게임 5650>
쟁점1. 한step씩 하는것의 중요성(설령 비효율적이어보일지라도)
쟁점2. 변수 따로 빼는 것의 중요성 x=map[x][y]해버리면 다음에 y바꿀때 다망가진다!
tmpx=map[x][y] 한후에 x=tmpx하자!!! 매우중요!
*/

import java.io.*;
import java.util.*;
public class 핀볼게임_5650 {
	static ArrayList<int[]> list[];
	static int map[][],n,ans;
						//상 하 좌 우
	static int[][] dir= {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		int T=Integer.parseInt(br.readLine().trim());
		StringTokenizer st;
		map=new int[100][100];
		
		for(int tc=1;tc<=T;++tc) {
			list=new ArrayList[5];
			for(int i=0;i<5;++i) list[i]=new ArrayList<>();
			n=Integer.parseInt(br.readLine().trim());
			for(int i=0;i<n;++i) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;++j) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]>=6 && map[i][j]<=10) {
						list[map[i][j]-6].add(new int[] {i,j});
					}
				}
			}
			ans=-1;
			for(int i=0;i<n;++i) {
				for(int j=0;j<n;++j) {
					if(map[i][j]==0) {
						for(int k=0;k<4;++k)
							solve(i,j,k);
					}
				}
			}
			bw.write("#"+tc+" "+ans+'\n');
		}
		bw.flush();
		
	}
	static void solve(int sx, int sy,int d) {
		int x=sx;
		int y=sy;
		int score=0;
		
		loop:while(true) {//무조건 한 step씩 해주는 게 이문제의 핵심!이동하고 방향 바꾸고 또 이동하는순간
			// 다 망가진다! 무조건 한스텝에 최대 이동한번 방향 한번이다!방향바꾼후에 또 이동 금물!
			x += dir[d][0];
			y += dir[d][1];
			if(x==sx && y==sy) break;
			if(x<0 || y<0 || x>=n || y>=n) {
				score ++;
				if(d==0) d=1;
				else if(d==1) d=0;
				else if(d==2) d=3;
				else if(d==3) d=2;
				continue;//continue꼭 해주기(지금 인덱스가 벗어나있는 상태)
			}
			
			switch (map[x][y]) {
			case -1:{
				break loop;
			}
			case 0:
				break;
				
			case 1: //상 하 좌 우
				if(d==0) d=1;
				else if(d==1) d=3;
				else if(d==2) d=0;
				else if(d==3) d=2;
				score++;
				break;
			case 2:
				if(d==0) d=3;
				else if(d==1) d=0;
				else if(d==2) d=1;
				else if(d==3) d=2;
				score++;
				break;
			case 3:
				if(d==0) d=2;
				else if(d==1) d=0;
				else if(d==2) d=3;
				else if(d==3) d=1;
				score++;
				break;
			case 4:
				if(d==0) d=1;
				else if(d==1) d=2;
				else if(d==2) d=3;
				else if(d==3) d=0;
				score++;
				break;
			case 5:
				if(d==0) d=1;
				else if(d==1) d=0;
				else if(d==2) d=3;
				else if(d==3) d=2;
				score++;
				break;
			default://여기서 6-10 처리해준다 그냥 그 웜홀로만 이동하고 한칸 더가지 않는다
				//바로 x y 대입하면 x값 바뀐상태로 y가 계산된다 매우주의!(map[x][y]이므로)
				int tmpx=0;
				int tmpy=0;
				if(x==list[map[x][y]-6].get(0)[0]
						&& y==list[map[x][y]-6].get(0)[1]) {
					tmpx=list[map[x][y]-6].get(1)[0];
					tmpy=list[map[x][y]-6].get(1)[1];
				}
				else {
					tmpx=list[map[x][y]-6].get(0)[0];
					tmpy=list[map[x][y]-6].get(0)[1];
				}
				x=tmpx;
				y=tmpy;
				
				break;
			}
		}
		if(ans<score) ans=score;
	}
}

