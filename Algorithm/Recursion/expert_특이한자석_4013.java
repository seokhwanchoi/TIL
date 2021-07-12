//재귀의 정수같은 문제
//반드시 익히기

import java.io.*;
import java.util.*;

public class Solution {
	static int K,score;
	static boolean chk[];
	static ArrayList<Boolean> Mag[];
	
	public static void turn(int index,int dir) {
		boolean left=Mag[index].get(6);
		boolean right=Mag[index].get(2);
		chk[index]=true;
		if(dir==1) {
			Mag[index].add(0, Mag[index].remove(7));
		}else {
			Mag[index].add(Mag[index].remove(0));
		}
		
		if(index>0 && !chk[index-1] && left!=Mag[index-1].get(2)) {
			turn(index-1,-1*dir);
		}
		if(index<3 && !chk[index+1] && right!=Mag[index+1].get(6)) {
			turn(index+1,-1*dir);
		}
	}

	public static void main(String[] args)throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine().trim());
		for(int tc=1;tc<=T;++tc) {
			K=Integer.parseInt(br.readLine().trim());
			Mag=new ArrayList[4];
			for(int i=0;i<4;++i) {
				Mag[i]=new ArrayList<>();
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<8;++j) {
					if(Integer.parseInt(st.nextToken())==1) {
						Mag[i].add(true);
					}else {
						Mag[i].add(false);
					}
				}
			}
			int index; int dir;
			for(int i=0;i<K;++i) {
				st=new StringTokenizer(br.readLine());
				index=Integer.parseInt(st.nextToken())-1;
				dir=Integer.parseInt(st.nextToken());
				chk=new boolean[4];
				turn(index,dir);
			}
			score=0;
			if(Mag[0].get(0))score++;
			if(Mag[1].get(0))score+=2;
			if(Mag[2].get(0))score+=4;
			if(Mag[3].get(0))score+=8;
			System.out.println("#"+tc+" "+score);
		}
	}
}
