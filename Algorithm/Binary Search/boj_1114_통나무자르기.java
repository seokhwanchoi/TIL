/*


*/


import java.io.*;
import java.util.*;
public class Main{
    static int L, K, C, ans, firstCutPoint;
    static int[] place;
    
    // 편의상 통나무의 양 끝(0, L)을 자를 수 있는 위치에 포함시켰습니다.
    public static void main(String args[]) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st=new StringTokenizer(br.readLine());
    	L =Integer.parseInt(st.nextToken());
    	K =Integer.parseInt(st.nextToken());
    	C =Integer.parseInt(st.nextToken());
    	
    	place = new int[K+2];
    	place[0] = 0;
    	st=new StringTokenizer(br.readLine());
    	for(int i = 1; i <= K; i++) 
    		place[i] = Integer.parseInt(st.nextToken());
    	place[K+1] = L;
    	
    	Arrays.sort(place);
    	
    	ans=Integer.MIN_VALUE; firstCutPoint=0;
    	binarySearch(1, L);
    	System.out.println(ans+" "+firstCutPoint);
   
    }
    
    // start = end, 즉 가장 긴 조각의 길이가 결정되면 아래와 같이 사이사이에 정답을 구하기 위한 작업
    static void binarySearch(int start, int end){
    	int left=0; int right=0; int cnt = 0, isFail = 1, mid = (start+end)/2;
        // 반대편 지점(위치 = right = place[K+1] = L)에서부터 나무를 자르기 시작합니다. 
    	right = place[K+1];
    	//System.out.println(start+" "+end+" "+mid);
    	for(int i = K; i >= 0; i--){
    		left = place[i];
    		if(right-left <= mid) continue;//자르려는 전체 구간이 mid보다는 커야
    		if(place[i+1]-place[i] > mid){
    			isFail = -1;
    			//붙어있는 cutpoint사이가 mid보다 크면  사이에 자르지 못하는 조각이 발생할 수 있으므로 더 갈것도 없이 잘라야
    			break;
    		}else{//붙어있는 cutpoint사이가 mid보다 작거나 같으면
    			if(start == end) ans = Math.max(ans, right-place[i+1]); // 답을 구하기 위한 마무리 작업1(최대한 통나무길이들을 작게 한 것 중에 가장 긴것)
    			right = place[i+1];//그 부분부터 다시 자르기 시작한다.
    			cnt++;
    		}
    	}
    //	System.out.println("left : "+left);
    //	System.out.println("right: "+right);
    //	System.out.println();
    	if(start == end){ // 답을 구하기 위한 마무리 작업2
    		ans = Math.max(ans, right);//최대한 통나무길이들을 작게 한 것 중에 가장 긴것
    		if(cnt == C) firstCutPoint = right;// 통나무를 자를 수 있는 횟수가 다 소진되면 마지막에 자른 위치가 정답
    		else firstCutPoint = place[1];// 횟수가 남아있다면 시작점에서 가장 가까운 위치가 답
    		return;
    	}
    	
    	
    	if(cnt > C || isFail == -1) binarySearch(mid+1, end);
    	else binarySearch(start, mid);
    }
    
}
