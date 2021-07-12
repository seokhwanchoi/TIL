
// https://m.blog.naver.com/PostView.nhn?blogId=occidere&logNo=221059582892&proxyReferer=https%3A%2F%2Fwww.google.com%2F
//설명 참고



import java.io.*;
import java.util.*;
public class Main {
	static int n,m,k,pos[];
	static FenwickTree fenwickTree;
	static class FenwickTree{
		int n, tree[];
		
		FenwickTree(int n){
			this.n=n;
			tree=new int[n];
		}
		
		int sum(int pos) {
			int ret=0;
			while(pos>0) {
				ret+=tree[pos];
				pos &= (pos-1);//다음 구간을 찾기 위해 최종 비트를 지운다.
			}
			return ret;
		}
		void add(int pos, int val) {
			while(pos<n) {
				tree[pos]+=val;
        //맨 오른쪽에 있는 1인 비트를 스스로에게 더해주는 연산 
        //반복하면 해당위치를 포함하는 구간들을 모두 만날수 있음
        
				pos+=(pos & -pos);//마지막 비트 추출
			}
		}
	}
  
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		int T=Integer.parseInt(br.readLine().trim());
		StringTokenizer st;
		while(T--!=0) {
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			pos=new int[n+1];
			
			/* 펜윅 트리의 크기는 n + m + 1 */
			fenwickTree=new FenwickTree(n+m+1);
			
			/* m+i 위치부터 채워넣고 pos[i]에 m+i 위치 기록 */
			for(int i=1;i<=n;++i) {
				pos[i]=i+m;
				fenwickTree.add(pos[i], 1);// m + i 부터 채워놓음
			}
			
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<m;++i) {
				k=Integer.parseInt(st.nextToken());//뽑을 영화 번호
				
				/* 자기 자신을 포함한 합이 출력되므로 -1 */
				bw.write((fenwickTree.sum(pos[k])-1)+" ");
				
				/* 기존 위치 -1로 해서 부분합 값에 반영되도록 함 */
				fenwickTree.add(pos[k], -1);
				
				// 맨 위에 쌓음
				pos[k]=m-i;
				
				 /* 새로 쌓인 위치를 1로 바꿔서 부분합 값에 반영되도록 함 */
				fenwickTree.add(pos[k], 1);
			}
			bw.write('\n');
		}
		bw.flush();
	}

}
