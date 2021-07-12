import java.io.*;
import java.util.*;

class Solution {
    static StringBuilder answer;

    static StringBuilder solve(StringBuilder input){
         if(input.length()==0){
            //입력이 빈 문자열이면 반환
             return input;
         }


        StringBuilder u=new StringBuilder();//균형잡힌 최소의 문자열
        StringBuilder v=new StringBuilder();//빈문자열 가능
        int left=0; int right=0; boolean flag=false;
        for(int i=0;i<input.length();++i){
            if(!flag){
                if(input.charAt(i)=='(')
                    left++;
            else
                right++;
            u.append(input.charAt(i));
            }
            else
                v.append(input.charAt(i));

            if(left!=0 && right!=0 && left==right)
                flag=true;
        }

        left=0; right=0; flag=true;
        for(int i=0;i<u.length();++i){
            if(u.charAt(i)=='(')
                left++;
            else
                right++;
            if(right>left){
                flag=false;
                break;
            }

        }
        StringBuilder temp=new StringBuilder();
        if(flag){

            temp.append(u);
            return temp.append(solve(v));
        }
        else{

            temp.append('(');

            temp.append(solve(v));

            temp.append(')');

            u.deleteCharAt(0);
            u.deleteCharAt(u.length()-1);

            for(int i=0;i<u.length();++i){
                if(u.charAt(i)=='('){
                    u.setCharAt(i,')');
                }
                else
                    u.setCharAt(i,'(');
            }
            temp.append(u);
            return temp;
        }

    }
    public String solution(String p) {

        answer = new StringBuilder();
        StringBuilder input=new StringBuilder();

        input.append(p);

        answer=solve(input);


        return answer.toString();

    }
}
