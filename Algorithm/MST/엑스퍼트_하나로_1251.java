package 알고리즘_엑스퍼트_주석많이단거;
import java.io.*;
import java.util.*;
public class 하나로_1251 {
	static StringBuilder sb;
	static ArrayList<double[]> tree;
	static int n,x,y,cnt;
	static double e,ans,tmp[];
	static int dat[][],parent[];
	
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		int T=Integer.parseInt(br.readLine().trim());
		StringTokenizer st;
		sb=new StringBuilder();
		dat=new int[2][1000];
		parent=new int[1000];
		for(int tc=1;tc<=T;++tc) {
			n=Integer.parseInt(br.readLine().trim());
			for(int i=0;i<2;++i) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;++j) {
					dat[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			e=Double.parseDouble(br.readLine().trim());
			cnt=0;
			tree=new ArrayList<>();
			for(int i=0;i<n;++i) {
				parent[i]=i;
				for(int j=i+1;j<n;++j) {
					tree.add(new double[] {
									e*(Math.pow(dat[0][i]-dat[0][j],2)+
									Math.pow(dat[1][i]-dat[1][j],2)),i,j
					});
					cnt++;
				}
			}
			Collections.sort(tree,new Comparator<double[]>() {
				public int compare(double[] o1, double[] o2) {
					if(o1[0]>o2[0])
						return 1;
					else if(o1[0]==o2[0])
						return 0;
					else
						return -1;
				}
				//compare함수 return o1[0]-o2[0]같은 식으로
				//하면 원소가 int가 아니기 때문에 부정확한경우가 생긴다
				//따라서 어떠한 경우에도 정확하기 위해서는 이런식으로 세분화하는 것이필요
				//백준 소수의 곱에서도 마찬가지
				
			});
			ans=0;
			for(int i=0;i<cnt;++i) {
				tmp=tree.get(i);
				if(merge((int)tmp[1],(int)tmp[2])) ans += tmp[0];
				//merge의 반환형을 boolean으로 할 경우 더 빠르다.
			}
			sb.append("#").append(tc).append(" ").append(Math.round(ans)).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		
	}
	static int find(int x) {
		if(x==parent[x])
			return x;
		else
			return parent[x]=find(parent[x]);
	}
	static boolean merge(int x, int y) {
		x=find(x);
		y=find(y);
		if(x!=y) {
			parent[y]=x;
			return true;
		}
		return false;
	}
}
