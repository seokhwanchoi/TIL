/*
		return parent[x]=find(parent[x]);아니고 return find(parent[x])해주면 틀림--여기서 parent[x]를 바꿔줘야하기에..
    
    if(a!=b)해주고 안해주고도 시간상 차이남
    
    	if(a!=b)
			parent[a]=b;
      이것도
      
      parent[b]=a;하면 틀림
      
      기준을 잡고 무엇을 누구의 부모로 해주냐에 따라 달라짐. union find는 이런 거하나하나가 엄청 큼
    
      참고 : https://mygumi.tistory.com/245
*/

import java.io.*;
import java.util.*;
public class Main {
	static int G,P,ans,parent[],gi,docking;
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		G=Integer.parseInt(br.readLine().trim());
		P=Integer.parseInt(br.readLine().trim());
		parent=new int[G+1];
		ans=0;
		for(int i=1;i<=G;++i) parent[i]=i;
		
		for(int i=1;i<=P;++i) {
			gi=Integer.parseInt(br.readLine().trim());
			docking=find(gi);
			if(docking!=0) {
				union(docking, docking-1);
				ans++;
			}
			else
				break;
		}
		System.out.println(ans);
	}
	static int find(int x) {
		if(x==parent[x])
			return x;
		else
			return parent[x]=find(parent[x]);
	}
	static void union(int x, int y) {
		int a=find(x);
		int b=find(y);
		
		if(a!=b)
			parent[a]=b;
	}
}
