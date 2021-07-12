//이거 이해 후 암기 + 쟁점


import java.io.*;
import java.util.*;

class Solution {
    static class TrieNode{
        TrieNode children[];//?는 만들어주지않는다 당연하지만..
        int terminal;//0이면 false 1이면 true
        TrieNode(){
            terminal=0;
            children=new TrieNode[26];
        }
     
        
        void insert(String key, int idx, boolean isReverse){
            if(isReverse==false && idx==key.length()){
                terminal=1;
            }
            else if(isReverse==true && idx==-1){
                terminal=1;
            }
            else{
                int next=key.charAt(idx)-'a';
                
                if(children[next]==null)
                    children[next]=new TrieNode();
                
                if(isReverse)
                    children[next].insert(key,idx-1,true);
                
                else
                    children[next].insert(key,idx+1,false);
            }
        }
        
        int find(TrieNode node,String query, int idx){
            if(idx==query.length()) {
               if(node.terminal==1) return 1;
                else return 0;
            }
            char c=query.charAt(idx);
            int count=0;
            if(c=='?'){
                for(int i=0;i<26;++i){
                    if(node.children[i]!=null)
                        count+=find(node.children[i],query,idx+1);
                    }
                 return count;
            }
            else{
                if(node.children[c-'a']!=null){
                    count=find(node.children[c-'a'],query,idx+1);
                }
            }
            return count;
        }
        
    }
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        TrieNode root=new TrieNode();
        TrieNode reverseRoot=new TrieNode();
        
        for(int i=0;i<words.length;++i){
            root.insert(words[i],0,false);
            reverseRoot.insert(words[i],words[i].length()-1,true);
        }
        Map<String,Integer> memo=new HashMap<>();
        for(int i=0;i<queries.length;++i){
            if(memo.containsKey(queries[i])){//어차피 중복되어도 put에서 또 answer구하면 그걸로 수정되기때문에
                answer[i]=memo.get(queries[i]);//코드 단순화하려면 굳이 필요는 없는 코드처럼 보일수있지만
                //최악의 케이스 -- 중복된 쿼리만 계속들어오는 것 -- 그러면 시간초과가 나기에 반드시 필요한 부분
            }
            else{
                if(queries[i].charAt(0)=='?')
                    answer[i]=
                    reverseRoot.find(reverseRoot,new StringBuilder(queries[i]).reverse().toString(),0);
                else
                    answer[i]=root.find(root, queries[i],0);
                
                memo.put(queries[i],answer[i]);
            }
                
        }
        return answer;
    }
}
