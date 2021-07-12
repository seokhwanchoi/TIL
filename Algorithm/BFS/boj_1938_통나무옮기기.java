//이동 일단 시킨 후 check하는 게 편하다..
//그냥 그렇다.. 이동시키기 전에 미리 판별하는건 구현량이 더많아진다 -> 실수가능성 높아진다 ->틀릴 가능성 더 높아진다


import java.io.*;
import java.util.*;
public class Main{
	static int n,map[][],b[][],e[][];
	static LinkedList<int[]> q;
	static boolean[][][] v;
	static int ans;
	static boolean flag;
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine().trim());
		map=new int[n][n];
		int bidx=0; int eidx=0;
		b=new int[3][2];
		e=new int[3][2];
		for(int i=0;i<n;++i) {
			String s=br.readLine();
			for(int j=0;j<n;++j) {
				if(s.charAt(j)=='B') {
					b[bidx][0]=i;
					b[bidx++][1]=j;
				}
				else if(s.charAt(j)=='E') {
					e[eidx][0]=i;
					e[eidx++][1]=j;
				}
				else if(s.charAt(j)=='1')
					map[i][j]=1;
					
			}
		}
		q=new LinkedList<>();
		v=new boolean[n][n][2];
		q.offer(new int[] {b[1][0], b[1][1], b[0][0]==b[1][0]?0:1 });//중앙좌표와 방향
		//가로면 0 세로면 1
		v[b[1][0]][b[1][1]][b[0][0]==b[1][0]?0:1]=true;
		ans=0; flag=false;
		
		bfs();
		System.out.println(flag?ans:0);
		
	}
	static void bfs() {
		while(!q.isEmpty()) {
			int temp=q.size();
			ans++;
			for(int i=0;i<temp;++i) {
				int[] curr=q.poll();

//				if(x<0 || y<0 || x>=n || y>=n || 
//						map[x][y]==1 ||
//						v[x][y][d]) continue;
				for(int j=0;j<5;++j) {//U D L R T
					int x=curr[0];
					int y=curr[1];
					int d=curr[2];
					switch (j) {
					case 0:
						x=x-1;
						break;
					case 1:
						x=x+1;
						break;
					case 2:
						y=y-1;
						break;
					case 3:
						y=y+1;
						break;
					case 4:
						if(d==0) d=1;
						else if(d==1) d=0;
						
						break;
					}
					if(j==4) {
						if(checkNine(x,y,d)==false)
							continue;
					}
					else {
						if(check(x,y,d)==false)
							continue;
					}
					if(x==e[1][0] && y==e[1][1] && d==(e[0][0]==e[1][0]?0:1)) {
						flag=true;
						return;
					}
					q.offer(new int[] {x,y,d});
					v[x][y][d]=true;
				}
			}
		}
	}
	static boolean check(int x, int y, int d) {
		if(d==0) {//가로면
			int ly=y-1;
			int ry=y+1;
			
			//이미 옮긴후다
			if(x>=0 && x<n && ly>=0 && ly<n && 
					ry>=0 && ry<n && y>=0 && y<n &&
					map[x][ly]==0 && map[x][ry]==0 && map[x][y]==0
					&& !v[x][y][d])
				return true;
			else
				return false;
				
			
		}
		else {//세로면
			int upx=x-1;
			int downx=x+1;
			
			//이미 옮긴후다
			if(x>=0 && x<n && upx>=0 && upx<n && 
					downx>=0 && downx<n && y>=0 && y<n &&
					map[upx][y]==0 && map[downx][y]==0 && map[x][y]==0
					&& !v[x][y][d])
				return true;
			else
				return false;
		}
	}
	static boolean checkNine(int x, int y, int d) {
		if(d==0) {//가로면
			int ly=y-1;
			int ry=y+1;
			
			//이미 옮긴후다
			if(x>=0 && x<n && ly>=0 && ly<n && 
					ry>=0 && ry<n && y>=0 && y<n &&
			   !v[x][y][d] && isNineZero(x,y)
							 )
				return true;
			else
				return false;
				
			
		}
		else {//세로면
			int upx=x-1;
			int downx=x+1;
			
			//이미 옮긴후다
			if(x>=0 && x<n && upx>=0 && upx<n && 
					downx>=0 && downx<n && y>=0 && y<n &&
					!v[x][y][d]&& isNineZero(x,y))
				return true;
			else
				return false;
		}
	}
	static boolean isNineZero(int x, int y) {
		for(int i=x-1;i<=x+1;++i) {
			for(int j=y-1;j<=y+1;++j) {
				if(map[i][j]==1)
					return false;
			}
		}
		return true;
	}

}
