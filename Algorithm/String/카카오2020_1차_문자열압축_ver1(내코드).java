import java.io.*;
import java.util.*;

class Solution {
    public int solution(String s) {
        int len=s.length();
        int answer = Integer.MAX_VALUE;
        for(int i=1;i<=len/2;++i){
            int tempans=0;
            ArrayList<String> list=new ArrayList<>();
            for(int j=0;j<=len;j+=i){
                if(j>=len-i){
                    list.add(s.substring(j,len));
                    break;//substring딱 맞춰서 해줘야 동작
                }
                list.add(s.substring(j,j+i));
            }
            
            StringBuilder sb=new StringBuilder();
            int cnt=1;
            for(int j=0;j<list.size()-1;++j){
                if(list.get(j).equals(list.get(j+1))){
                    cnt++;
                    if(j==list.size()-2){//뒤에서 2번째면 더해주고 마무리
                        sb.append(cnt).append(list.get(j));
                    }
                }
                
                else if(cnt==1 && !list.get(j).equals(list.get(j+1))){
                    //바로 같은게 안나오면 바로 append
                    sb.append(list.get(j));
                    if(j==list.size()-2){
                        sb.append(list.get(j+1));
                    }
                }
                else if(cnt>=2 && !list.get(j).equals(list.get(j+1))){
                    //같은 거 나오다가(cnt>=2)이다 안나오면 cnt붙이고 append
                    sb.append(cnt).append(list.get(j));
                    cnt=1;//다시 cnt초기화
                    if(j==list.size()-2){
                        sb.append(list.get(j+1));//cnt랑 상관없는 뒤에 꼬랑지
                    }
                    
                }
            }
            tempans=sb.length();
            if(answer>tempans) answer=tempans;
        }
        return answer==Integer.MAX_VALUE?1:answer;//그냥 1개 단위 압축이면 1 반환
    }
}
