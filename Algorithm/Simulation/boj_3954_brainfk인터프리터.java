/*
charAt 내부함수호출횟수(이것도 실제 배열 바로 인덱스접근하는 거와 비슷하긴 하지만 호출을 몇번 거친다..
(단 0부터 그 인덱스까지 찾는 방식은 아님))
때문에 바로 배열a[1]이렇게 접근하는 것보다 느리다(데이터 크기가 많으면 많을수록)<br>
따라서 charArray로 배열화 시킨 후 접근하는 것이 더 빠를수도(boj 3954 brainfk인터프리터 참고 제출내역) 그냥 알아두면 좋을수도...쓸일은 별로없겠지만<br>
switch case문안에서 break주의해서 쓰기- 잘못해선 이중break가 되어 답이 아예 안나올 수도 있다.<br>
```
case ']':
  if(dat[datPointerIndex]!=0) {
	if(maxPointerIndex<programcodeIndex) {
	  maxPointerIndex=programcodeIndex;
	}
	programcodeIndex=pairDat[programcodeIndex];
					
   }
   if(cnt>=50000000) {
	flag=false;
	bw.write("Loops "+pairDat[maxPointerIndex]
	       +" "+maxPointerIndex+'\n');
	break;//------------->전체 감싸고 있는 for문이나 while문을 빠져나오려쓴 것이나 그냥 case만 빠져나온 형국이 되었다.
   }
    break;//얘는 사실상 의미가 없어진다??
```
stack은 만드나 갔다쓰나 별차이없고...<br><br>

*/
import java.io.*;
import java.util.*;
public class Main {
	static int datPointerIndex,programcodeIndex,inputIndex,dat[],sm,sc,si,pairDat[];
	static Stack<Integer> stack;
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		int t=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=t;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			sm=Integer.parseInt(st.nextToken());
			sc=Integer.parseInt(st.nextToken());
			si=Integer.parseInt(st.nextToken());
			dat=new int[sm];
			pairDat=new int[sc];
			
			String programCode=br.readLine();
			String programInput=br.readLine();

			
			stack=new Stack<>();
			int Left=0;
			for(int i=0;i<sc;++i) {
				if(programCode.charAt(i)=='[') {
					stack.push(i);
				}
				else if(programCode.charAt(i)==']') {
					Left=stack.pop();
					pairDat[Left]=i;
					pairDat[i]=Left;
				}
			}
			
			//System.out.println(Arrays.toString(pairDat));
			
			datPointerIndex=0;
			programcodeIndex=0;
			inputIndex=0;
			
			//출력을 위한 것들
			int maxPointerIndex=0;
			int cnt=0;
			boolean flag=true;
			
			while(programcodeIndex<sc) {
				switch (programCode.charAt(programcodeIndex)) {
				case '-':
					dat[datPointerIndex]--;
					if(dat[datPointerIndex]<0)
						dat[datPointerIndex]=255;
					break;
				case '+':
					dat[datPointerIndex]++;
					if(dat[datPointerIndex]==256)
						dat[datPointerIndex]=0;
					break;
				case '<':
					datPointerIndex--;
					if(datPointerIndex<0)
						datPointerIndex=sm-1;
					break;
				case '>':
					datPointerIndex++;
					if(datPointerIndex==sm)
						datPointerIndex=0;
					break;
				case '[':
					if(dat[datPointerIndex]==0) {
						programcodeIndex=pairDat[programcodeIndex];
					}
					break;
				case ']':
					if(dat[datPointerIndex]!=0) {
						if(maxPointerIndex<programcodeIndex) {
							maxPointerIndex=programcodeIndex;
						}
						programcodeIndex=pairDat[programcodeIndex];
					
					}
					break;
				case '.':
					break;
				case ',':
					if(inputIndex==si) {
						dat[datPointerIndex]=255;
					}
					else
						dat[datPointerIndex]=programInput.charAt(inputIndex++);
					break;
				}
				
				cnt++;
				programcodeIndex++;
				if(cnt>=50000000) {
					flag=false;
					bw.write("Loops "+pairDat[maxPointerIndex]
							+" "+maxPointerIndex+'\n');
					break;
				}
			
			}
			if(flag)
				bw.write("Terminates"+'\n');
		}
		
		bw.flush();
	}

}
