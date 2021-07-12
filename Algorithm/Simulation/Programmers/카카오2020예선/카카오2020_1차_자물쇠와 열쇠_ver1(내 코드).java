//@@@굳이 rotate그때그때해줄필요가 없음..
//최적화 요소 많은 코드 일단은 pass했으나(실전에선 pass에 초점을 맞춰야하므로)

import java.io.*;
import java.util.*;

class Solution {
    static int n,m,map[][],tempmap[][],len,emp,key2[][], key3[][], key4[][];
    static boolean answer;
    public boolean solution(int[][] key, int[][] lock) {
        emp=0;
        answer = false;
        m=key[0].length;
        n=lock[0].length;
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                if(lock[i][j]==0) emp++;
            }
        }
        
        //원래것 복사(static으로 편히쓰기 위해서)
        key2=new int[m][m];
        key3=new int[m][m];
        key4=new int[m][m];
 
        key2=rotate(key);//미리 돌려놓기 굳이 rotate그때그때해줄필요가 없음.. 
        key3=rotate(key2);
        key4=rotate(key3);
        
        map=new int[2*m+n-2][2*m+n-2];//크게크게 다 넣어봐야하니까..
        
        for(int i=m-1;i<m-1+n;++i){
            for(int j=m-1;j<m-1+n;++j){
                map[i][j]=lock[i-m+1][j-m+1];
            }
        }//인덱스에 맞게 넣어주기.. 꼼꼼함 요함... 약간의 암기 있으면 편함
       
        len=map[0].length;
        
        loop:for(int i=0;i<=len-m;++i){
            for(int j=0;j<=len-m;++j){
                check(i,j,key);
                if(answer==true) break loop;
            }
        }
       
        
        return answer;
    }
    static void check(int x, int y,int[][] key){
    
                solve(x,y,key);
                if(answer==true) return;
           
                solve(x,y,key2);
                if(answer==true) return;
             
                solve(x,y,key3);
                if(answer==true) return;
              
                solve(x,y,key4);
                if(answer==true) return;
              
       
    }
    static int[][] rotate(int[][] key){//90도씩 회전시켜주기
        int tempkey[][]=new int[m][m];
        for(int i=0;i<m;++i){
            for(int j=m-1;j>=0;--j){
                tempkey[i][m-1-j]=key[j][i];
            }
        }

        return tempkey;
    }
    
    static void solve(int x, int y,int[][] key){//핵심 구현
        int cnt=0;
        for(int i=x;i<x+m;++i){
            for(int j=y;j<y+m;++j){
               
               if(map[i][j]==1 && i>=m-1 && j>=m-1 && i<m-1+n && j<m-1+n  && key[i-x][j-y]==1) return;
                //이부분빼먹으면 안됨. 열쇠의 돌기와 자물쇠의 돌기가 만나면 안됨(key와 map이 둘다 1이면 return)
                
                if(map[i][j]==0&& 
                  i>=m-1 && j>=m-1 && i<m-1+n && j<m-1+n  && key[i-x][j-y]==1 ){
                    cnt++;
                }
            }
        }
       
        if(cnt==emp)
            answer=true;
    }
}
