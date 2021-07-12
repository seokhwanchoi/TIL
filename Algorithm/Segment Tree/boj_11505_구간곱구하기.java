import java.io.*;
import java.util.*;
public class Main {
	static long n,m,k,temp,a,b,c;
	static long seg[];
	static long mod=1000000007;
	public static void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Long.parseLong(st.nextToken());
		m=Long.parseLong(st.nextToken());
		k=Long.parseLong(st.nextToken());
		
		seg=new long[4000000];
		for(long i=1;i<=n;++i) {
			temp=Long.parseLong(br.readLine().trim());
			update(i,temp,1,1,n);
		}
		for(long i=0;i<m+k;++i) {
			st=new StringTokenizer(br.readLine());
			a=Long.parseLong(st.nextToken());
			b=Long.parseLong(st.nextToken());
			c=Long.parseLong(st.nextToken());
			if(a==1)
				update(b,c,1,1,n);
			else
				bw.write(query(b,c,1,1,n)+""+'\n');
		}
		
		bw.flush();
	}
	static long update(long index, long newValue, long node, 
			long nodeLeft, long nodeRight) {
		//index가 노드가 표현하는 구간과 상관없는 경우는 무시
		if(index<nodeLeft || nodeRight<index)
			return seg[(int)node];
		
		//트리의 leaf까지 내려온 경우
		if(nodeLeft==nodeRight) return seg[(int)node]=newValue;
		
		long mid=(nodeLeft+nodeRight)/2;
		
		return seg[(int)node]=((update(index,newValue,node*2,nodeLeft,mid))
				*update(index,newValue,node*2+1,mid+1,nodeRight))%mod;
		
	}
	
	static long query(long left, long right, long node, long nodeLeft, long nodeRight) {
		//두 구간이 겹치지 않으면 무시 - 구간 곱이기에 1을 return
		if(right<nodeLeft || nodeRight<left) return 1;
		
		//node가 표현하는 범위가 [left,right]에 완전히 포함되는 경우
		
		if(left<=nodeLeft && nodeRight<=right) return seg[(int)node];
		
		//양쪽 구간을 나눠서 푼되 결과를 합친다.
		long mid=(nodeLeft+nodeRight)/2;
		
		return ((query(left,right,node*2,nodeLeft,mid))*(query(left,right,node*2+1,mid+1,nodeRight)))%mod;
		
		
	}
}
