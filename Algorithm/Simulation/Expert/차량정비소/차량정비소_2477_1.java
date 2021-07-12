https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV6c6bgaIuoDFAXy

import java.util.*;
import java.io.*;
class Solution {
    static int Answer;
    public static void main(String args[]) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out)); 
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
        for(int tc = 1; tc<= T; ++tc) {
            Answer=0;
            st=new StringTokenizer(br.readLine());
            int N=Integer.parseInt(st.nextToken());
            int M=Integer.parseInt(st.nextToken());
            int K=Integer.parseInt(st.nextToken());
            int A=Integer.parseInt(st.nextToken());
            int B=Integer.parseInt(st.nextToken());
            int custom[][] = new int[K][5];
            int a[][] = new int [N][2];
            int b[][] = new int [M][2];
            boolean chk=true;
            Queue<Integer> line = new LinkedList<Integer>();
            st=new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) a[i][0]=Integer.parseInt(st.nextToken());
            st=new StringTokenizer(br.readLine());
            for(int i=0;i<M;i++) b[i][0]=Integer.parseInt(st.nextToken());
            st=new StringTokenizer(br.readLine());
            for(int i=0;i<K;i++) custom[i][0]=Integer.parseInt(st.nextToken());
               
            int time=0;
            int g;
            while(true) {
                chk=true;
                for(int i=0;i<N;i++) {
                    for(int j=0;j<K;j++) {
                        if(a[i][1]<=time&&custom[j][1]==0&&custom[j][2]==0&&custom[j][0]<=time) {
                            a[i][1]=time+a[i][0];//접수데스크까지 오는데 총 걸리는시간(대기시간 포함해서)
                            custom[j][2]=i+1;
                            custom[j][1]=a[i][1];
                        }
                        if(custom[j][2]==i+1&&custom[j][1]==time&&custom[j][1]!=0) {
                            line.offer(j);
                        }
                    }
                }
                   
                for(int j=0;j<M;j++) {
                    if(b[j][1]<=time&&line.peek()!=null) {
                        g=line.poll();
                        b[j][1]=time+b[j][0];//정비데스크까지 오는데 총 걸린 시간
                        custom[g][4]=j+1;
                        custom[g][3]=b[j][1];
                    }
                }
                       
                   
                for(int i=0;i<K;i++) 
                    if(custom[i][3]==0)
                        chk=false;
                 
                if(chk==true)break;
                time++;
            }
               
               
            for(int i=0;i<K;i++) 
                if(custom[i][2]==A&&custom[i][4]==B) 
                    Answer+=(i+1);
               
            if(Answer==0)Answer=-1;
            bw.write("#"+tc+" "+Answer+'\n');
        }
        bw.flush();
    }
}
