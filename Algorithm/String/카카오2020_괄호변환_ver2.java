import java.io.*;
import java.util.*;

class Solution {
    static boolean isRight(String p){
        Stack<Character> stack=new Stack<>();
     
        for(int i=0;i<p.length();++i){
            if(p.charAt(i)=='('){
                stack.push('(');
            }
            else{
                if(stack.isEmpty()) return false;
                char temp=stack.pop();
                if(temp==p.charAt(i))
                    return false;
            }
        }
        return true;
    }
    static boolean isBalanced(String p){
        int left=0; int right=0;
        for(int i=0;i<p.length();++i){
            if(p.charAt(i)=='(') left++;
            else right++;
        }
        if(left==right) return true;
        else return false;
    }
        
    static String solve(String p){
        if(p=="")
            return p;
        String u=""; String v="";
        for(int i=2;i<=p.length();i+=2){
            //u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 한다.
            //즉 u는 최소의 균형잡힌 괄호 문자열이 나오면
            //더 탐색할 필요가 없다
            u=p.substring(0,i);
            v=p.substring(i,p.length());
            if(isBalanced(u) && isBalanced(v))
                break;
                
        }
            if(isRight(u)){
                return u+solve(v);
            }
            else{
                StringBuilder sb=new StringBuilder();
                sb.append('(');
                sb.append(solve(v));
                sb.append(')');
                
               
                for(int j=1;j<u.length()-1;++j){
                    if(u.charAt(j)=='(')
                        sb.append(')');
                    else
                        sb.append('(');
                }
                return sb.toString();
            }
 
    }
    public String solution(String p) {
        //char[] dat=new char[p.length()];
       // dat=p.toCharArray();
        if(isRight(p)){
            return p;
        }

        String answer=solve(p);
        return answer.toString();
        
    }
}
