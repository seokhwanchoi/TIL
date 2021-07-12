/*
상당히 애를 먹었던 문제..
쟁점 1. 주기 끝까지 다하면 0이 아니고 1이다 그리고 주기를 여러번 도는 경우가있다
쟁점 2. 나누어 떨어지면 0이면 1이아니라 가장 max값을해줘야한다.. 이것때문에 틀린것

이런 쟁점들 다른 문제에서도 중요하다
*/

import java.io.*;
import java.util.*;
public class 카잉달력_6064_20200325 {
	static int m,n,x,y,ans,tmpy,tmpans;
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine().trim());
		while(T--!=0) {
			st=new StringTokenizer(br.readLine());
			m=Integer.parseInt(st.nextToken());
			n=Integer.parseInt(st.nextToken());
			x=Integer.parseInt(st.nextToken());
			y=Integer.parseInt(st.nextToken());
			ans=-1;
			
			
			if(m==1 && n==1) {
				ans=1;//처음에 바로 맞으면..
			}
			else {
				tmpans=x;//주기 다하면 0이 아니고 1이다 그리고 주기를 여러번 도는 경우가있다
				tmpy=(x%n==0?n:x%n);
				//나누어 떨어지면 0이면 1이아니라 가장 max값을해줘야한다.. 이것때문에 틀린것
				while(true) {
					if(tmpy==y) {
						ans=tmpans;
						break;
					}
					tmpans += m;
					tmpy=(tmpy+m)%n==0?n:(tmpy+m)%n;
					if(tmpy==(x%n==0?n:x%n)) break;//주기 한바퀴돌았는데도
					//답 없으면..
				}
			}
			
			bw.write(String.valueOf(ans)+'\n');
		}
		bw.flush();
	}
}
