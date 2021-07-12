/*
소트 + for문 1개가 불완전하다는 걸 보여주는 좋은 문제...

Comparator이용해서 소트해봤자.. 두 요소 모두 다 커야 순위가 성립되기 때문에... 뭐 더 방법이 있을수도 있다만..

그냥 이중 for문으로 돌리자 n이 작을땐

쟁점1. 계속 정렬에 집착하지 말자. 정렬하려고 하면할수록 더 안풀리는 문제...

그냥  각각 나보다 덩치큰사람이 몇명있는지 세서 한명한명 등수 매기면 되는문제..

본질에 집중해야한다는 것을 다시한번 느끼게 한문제... 정렬에 너무 집착 말자..

개인적으로 좋은문제다. 초심으로
*/

import java.io.*;
import java.util.*;
class Main{
    static int n,weight[],height[];
    public static void main(String[] args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n=Integer.parseInt(br.readLine());
        weight=new int[n];
        height=new int[n];
        for(int i=0;i<n;++i){
            st=new StringTokenizer(br.readLine());
            weight[i]=Integer.parseInt(st.nextToken());
            height[i]=Integer.parseInt(st.nextToken());
        }
        
        for(int i=0;i<n;++i) {
        	int count=1;
        	for(int j=0;j<n;++j) {
        		if(weight[i]<weight[j] && height[i]<height[j])
        			count++;
        	}
        	System.out.print(count+" ");
        }
        
    }
}

