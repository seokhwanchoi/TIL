import java.io.*;
import java.util.*;

class Solution {
    public int solution(String s) {
        int answer=Integer.MAX_VALUE;
        int len=s.length();
        //자르면서 답을 구할수도 있겠다..다음엔 그렇게 해보기
        for(int i=1;i<=len/2;++i){
            ArrayList<String> list=new ArrayList<>();
            for(int j=0;j<len;j+=i){
                if(j>=len-i) {
                    list.add(s.substring(j,len));
                    break;
                }
                list.add(s.substring(j,j+i));
            }
            
            StringBuilder sb=new StringBuilder();
            int cnt=1;
            for(int j=0;j<list.size()-1;++j){
                String curr=list.get(j);
                String next=list.get(j+1);
                if(curr.equals(next)){
                    cnt++;
                    if(j==list.size()-2){
                        sb.append(cnt).append(curr);
                    }
                }
                else{
                    if(cnt==1){
                        sb.append(curr);  
                    }
                    else{
                        sb.append(cnt).append(curr);
                        cnt=1;
                    }
                   
                    if(j==list.size()-2){
                        sb.append(next);
                     }
                }
                
            }
            if(answer>sb.length())
                    answer=sb.length();
            
        }
        
        return answer==Integer.MAX_VALUE?1:answer;
    }
}
