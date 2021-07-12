//너무 좋은아이디어 코드 문제

import java.io.*;
import java.util.*;
public class Main{
	static int target,m,n,a[],b[],ans,sum,cnta[],cntb[];
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		target=Integer.parseInt(br.readLine().trim());
		StringTokenizer st=new StringTokenizer(br.readLine());
		m=Integer.parseInt(st.nextToken());
		n=Integer.parseInt(st.nextToken());
		a=new int[m];
		b=new int[n];
		sum=0;
		cnta=new int[2000001];
		cntb=new int[2000001];
		for(int i=0;i<m;++i) {
			a[i]=Integer.parseInt(br.readLine().trim());
			sum+=a[i];
		}
		cnta[sum]=1;
		sum=0;
		for(int i=0;i<n;++i) {
			b[i]=Integer.parseInt(br.readLine().trim());
			sum+=b[i];
		}
		cntb[sum]=1;
		
		for(int i=0;i<m;++i) {
			sum=a[i];
			cnta[sum]++;
			for(int j=1;j<m-1;++j) {
				sum+=a[(i+j)%m];
				if(sum>target) break;
				else cnta[sum]++;
			}
		}
	
		for(int i=0;i<n;++i) {
			sum=b[i];
			cntb[sum]++;
			for(int j=1;j<n-1;++j) {
				sum+=b[(i+j)%n];
				if(sum>target) break;
				else cntb[sum]++;
			}
		}
		ans+=cnta[target]+cntb[target];
		for(int i=1;i<target;++i) {
			ans+=cnta[i]*cntb[target-i];
		}
	
		System.out.println(ans);
	}

	
}
