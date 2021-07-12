//맵이 계속 변하므로 vis배열 쓰지 않는다
import java.io.*;
import java.util.*;
public class Main {
	static char[][] map;
	static LinkedList<int[]> q;
	static boolean flag;
	static ArrayList<int[]> list;
	static int[][] dir= {{-1,0},{1,0},{0,-1},{0,1},{1,1},{-1,-1},{1,-1},{-1,1},{0,0}};
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		map=new char[8][8];
		list=new ArrayList<>();
		
		for(int i=0;i<8;++i) {
			String s=br.readLine();
			for(int j=0;j<8;++j) {
				map[i][j]=s.charAt(j);
				if(map[i][j]=='#')
				list.add(new int[] {i,j});
			}
		}
		q=new LinkedList<>();
		q.offer(new int[] {7,0});
		flag=false;
		bfs();
		if(flag)
			System.out.println(1);
		else
			System.out.println(0);
	}
	static void bfs() {
		while(!q.isEmpty()) {
			int temp=q.size();
			for(int i=0;i<temp;++i) {//한 턴
				int[] curr=q.poll();
				if(map[curr[0]][curr[1]]=='#') continue;
				//벽이 있는 칸으로 이동했으면 걔는 볼필요도 없다
				for(int j=0;j<9;++j) {
					int x=curr[0]+dir[j][0];
					int y=curr[1]+dir[j][1];
					if(x>=0 && y>=0 && x<8 && y<8 && map[x][y]=='.') {
						if(x==0 && y==7) {
							flag=true;
							return;
						}
						q.offer(new int[] {x,y});
					}
				}
			}
			
			for(int i=7;i>=0;--i) {
				for(int j=0;j<8;++j) {
					if(map[i][j]=='#') {
						if(i==7)
							map[i][j]='.';
						else {
							map[i][j]='.';
							map[i+1][j]='#';
						}
					}
				}
			}
			//쟁점 .. 밑으로 내려오게하는거 그냥무조건 전체배열 돌리면서
			//하기 list로 하면 위에서부터 하게되고, 위에서 내려온 밑에걸 못보게 된다. 그냥 단순리스트안에있는 것만
			//보게됨
			
			//for(int i=0;i<list.size();++i) {
				
			
				
				/*
				 * #        .  이때 list로하게되면 마지막것뺴고 다지워짐  .   이래됨
				 * #  --    #   							  .
				 * #		#								  .
				 * .		#								  #
				 * 
				 * 
				 */
//				if(list.get(i)[0]+1>=8) {  -->잘못구현한 코드
//					map[list.get(i)[0]][list.get(i)[1]]='.';
//					list.remove(i);
//					i--;
//				}
//				else {
//					map[list.get(i)[0]][list.get(i)[1]]='.';
//					map[list.get(i)[0]+1][list.get(i)[1]]='#';
//					list.set(i, new int[] {list.get(i)[0]+1,
//							list.get(i)[1]});
//				}
			//}
	
//			for (char[] is : map) {
//				System.out.println(Arrays.toString(is));
//			}
//			System.out.println();
		}
	}
}
