//문자열이고 뭔가 커서를 중심으로 act되는 문제는 stack을 사용하면 편리할 때가 많다


import java.io.*;
import java.util.*;
public class Solution {
	static Stack<Character> stack;
	static int ans;
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		int T=Integer.parseInt(br.readLine().trim());
		for(int tc=1;tc<=T;++tc) {
			ans=0; stack=new Stack<>();
			String s=br.readLine().trim();
			for(int i=0;i<s.length();++i) {
				switch (s.charAt(i)) {
				case '(':
					stack.push(s.charAt(i));
					break;
				case ')':
					if(s.charAt(i)!=s.charAt(i-1)) {
						stack.pop();
						ans+=stack.size();
					}
					else {
						stack.pop();
						ans++;
					}
					break;
				}
			}
			bw.write("#"+tc+" "+ans+'\n');
		}
		bw.flush();
		
	}
}
