쟁점1. m=0일 때 더 입력받지 말고 바로 return
쟁점2. 기본 논리는 숫자먼저치고 +,-치는 것
쟁점3. 100근방의 숫자 혹은 고장난 숫자버튼이 너무 많으면 그냥 +,-로만 하는 것이 숫자치고 +,-하는 것보다 빠를수도 있다는 것 

import java.io.*;
import java.util.*;
public class Main {
	static int n,m,v[];
	public static void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine().trim());
		m=Integer.parseInt(br.readLine().trim());
		int ans=Integer.MAX_VALUE;
		
		if(Math.abs(n-100)<ans) {
			ans=Math.abs(n-100);
		}
		
		if(m==0) {
			if(ans>String.valueOf(n).length())
				ans=String.valueOf(n).length();
			
			System.out.println(ans);
			return;
		}
		
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		v=new int[10];//1이면 고장난것
		
		for(int i=0;i<m;++i) {
			int temp=Integer.parseInt(st.nextToken());
			v[temp]=1;
		}
		//먼저 숫자 다누르고 그다음에 +,-
		
		int len=0;
		loop:for(int i=0;i<=1000000;++i) {
			
			len=String.valueOf(i).length();
			if(ans<len) break;
			for(int j=0;j<len;++j) {
				if(v[String.valueOf(i).charAt(j)-'0']==1)
					continue loop;
			}
			int cnt=String.valueOf(i).length()+Math.abs(n-i);
			if(ans>cnt) ans=cnt;
		}
		System.out.println(ans);
		
		
	}
}
