//ArrayList에 쏠리는 거 모아두는 거, 상하 좌우 한꺼번에 처리하는 코드(그때 구글링했던 C++로 되었던??)처럼도 짜보기

import java.io.*;
import java.util.*;
public class Main {
	static int n,m,rx,ry,bx,by,ans;
	static char[][] map;
	static int[][] dir= {{-1,0},{1,0},{0,-1},{0,1}};
	// 상 하 좌 우
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		map=new char[n][m];
		for(int i=0;i<n;++i) {
			String s=br.readLine();
			for(int j=0;j<m;++j) {
				map[i][j]=s.charAt(j);
				switch (map[i][j]) {
				case 'R':
					rx=i; ry=j;
					break;
				case 'B':
					bx=i; by=j;
					break;
				}
			}
		}
		ans=Integer.MAX_VALUE;
		//돌리면서 하기 굳이 맵 바꿀필요도 없다.
		dfs(0,rx,ry,bx,by,1);
		dfs(1,rx,ry,bx,by,1);
		dfs(2,rx,ry,bx,by,1);
		dfs(3,rx,ry,bx,by,1);
		
		//원래 갔던 방향만 다시 안가봐도 충분
		//반대방향은 1 2 3 1;우 2:좌 면 3은 1과 다를 수있다
		
		if(ans==Integer.MAX_VALUE) ans=-1;
		bw.write(ans+"");
		bw.flush();
	}
	static void dfs(int dnum,int Rx, int Ry, int Bx, int By, int cnt) {
		//가지치기
		if(cnt>=ans || cnt>10) return;
		
		
		//한칸씩이동 -- 'O'만날수있으므로
		
		//파란구슬먼저 겹치는 건 나중에 한꺼번에 생각
		
		int tempBx=Bx;
		int tempBy=By;
		int tempRx=Rx;
		int tempRy=Ry;
		
		Bx+=dir[dnum][0];
		By+=dir[dnum][1];
		if(map[Bx][By]=='O') {
			return;
		}
		while(map[Bx][By]=='.' || map[Bx][By]=='B' || map[Bx][By]=='R') {
			Bx+=dir[dnum][0];
			By+=dir[dnum][1];
			if(map[Bx][By]=='O') {
				return;
			}
		}
		Bx-=dir[dnum][0];
		By-=dir[dnum][1];
		
		
		Rx+=dir[dnum][0];
		Ry+=dir[dnum][1];
		if(map[Rx][Ry]=='O') {
			if(ans>cnt)
				ans=cnt;
			return;//결과와 상관없이 return
		}
		while(map[Rx][Ry]=='.'  || map[Rx][Ry]=='B' || map[Rx][Ry]=='R') {
			Rx+=dir[dnum][0];
			Ry+=dir[dnum][1];
			if(map[Rx][Ry]=='O') {
				if(ans>cnt)
					ans=cnt;
				return;//결과와 상관없이 return
			}
		}
		Rx-=dir[dnum][0];
		Ry-=dir[dnum][1];
		
		
		
		//겹치는 부분처리  	// 상 하 좌 우
		if(Bx==Rx && By==Ry) {
			switch (dnum) {
			case 0:
				//x값이 작은게 먼저 도착
				if(tempBx<tempRx) {
					Rx-=dir[dnum][0];
					Ry-=dir[dnum][1];
				}
				else if(tempRx<tempBx) {
					Bx-=dir[dnum][0];
					By-=dir[dnum][1];
				}
				break;
			case 1:
				//x값이 큰게 먼저 도착
			
				if(tempBx<tempRx) {
					Bx-=dir[dnum][0];
					By-=dir[dnum][1];
				}
				else if(tempRx<tempBx) {
					Rx-=dir[dnum][0];
					Ry-=dir[dnum][1];
				}
				break;
			case 2:
				//y값이 작은게 먼저 도착
				if(tempBy<tempRy) {
					Rx-=dir[dnum][0];
					Ry-=dir[dnum][1];
				}
				else if(tempBy>tempRy) {
					Bx-=dir[dnum][0];
					By-=dir[dnum][1];
				}
				break;
			case 3:
				//y값이 큰게 먼저 도착
				if(tempBy<tempRy) {
					Bx-=dir[dnum][0];
					By-=dir[dnum][1];
				}
				else if(tempBy>tempRy) {
					Rx-=dir[dnum][0];
					Ry-=dir[dnum][1];
				}
				break;

			}
		}
		for(int i=0;i<4;++i) {
			if(dnum==i) continue;
			dfs(i,Rx,Ry,Bx,By,cnt+1);
		}
	}
}

