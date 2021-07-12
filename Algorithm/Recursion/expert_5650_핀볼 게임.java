//스택 메모리 1MB-- 왠만하면 전역변수쓰는 것 생활화하기
// "1 "과 같은 입력이 들어올 때 인티저 형변환이 불가합니다
//br.readLine().trim()으로 사용하시는걸 습관화

import java.util.*;
import java.io.*;
 
public class Solution {
    static int N, map[][], max, count, x, y;
    static int dir[][] = {{-1,0}, {0,1}, {1,0}, {0,-1}};
    static int block[][] = {{2,3,1,0},{1,3,0,2},{3,2,0,1},{2,0,3,1},{2,3,0,1}};
    static int hole[][];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
         
        for(int t = 1; t <= T; t++) {
            N = sc.nextInt();
            map = new int[N+2][N+2];
            max = 0;
            hole = new int [10][2];
            int k = 0;
            for(int i=0;i<=N+1;i++)
                map[0][i]=map[N+1][i]=map[i][0]=map[i][N+1]=5;
             
            for(int i = 1; i <= N; i++)
                for(int j = 1; j <= N; j++) {
                    map[i][j] = sc.nextInt();
                    if(map[i][j] > 5) {
                        hole[k][0] = i;
                        hole[k][1] = j;
                        k++;
                    }
                }
            for(int i = 1; i <= N; i++)
                for(int j = 1; j <= N; j++)
                    if(map[i][j] == 0) {
                        x = i; y = j;
                        for(int d = 0; d < 4; d++) {
                            count = 0;
                            way(i, j, d);
                            max = max > count? max : count;
                        }
                    }
            System.out.println("#" + t + " " + max);
        }
    }
 
    static void way(int i, int j, int k) {
        i += dir[k][0]; j += dir[k][1];
        if((i == x && j == y) || map[i][j] == -1)
            return;
        else if(map[i][j] > 5)
            for(int m = 0; m < 10; m++) {
                int a = hole[m][0];
                int b = hole[m][1];
                if((a != i || b != j) && map[a][b] == map[i][j])
                    way(a,b,k);
            }
        else if(map[i][j] > 0) {
            way(i,j,block[map[i][j]-1][k]);
            count++;
        }
        else
            way(i, j, k);
    }
}
