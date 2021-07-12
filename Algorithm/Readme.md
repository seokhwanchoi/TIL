if문하고 while문 안에 최소화해서 넣기@@@@@@(범위도 딱 인덱스 벗어나는 부분만 넣기)
if문 while문 안에껀 최소화하고 if while문이 여러단계로 중첩되는 것이 훨씬 올바르고 세부적인 구현
ex)낚시왕 가로세로 벗어나는 부분 다 다른데 한꺼번에 때려밖으면 오류남


변수 제대로 썼는지확인

변수 클릭해보면 for문안에서 회색으로 뜬다


if if , if else if, if else if else 등 써야할때도 명확히 구분!

이클립스 내부구현 보려면 f3

boolean 배열과 class객체의 위대함..
뭔가 껄끄러운 처리 boolean배열로 처리하면 편할때가 많고(int형 방문배열도 유용)
백준 gaaaaaaaaaaarden문제

특히 이번 턴에 들어온건지 표시해주는 것에는 클래스객체만한게 없다진짜


직사각형 90도 오른쪽 회전 외우기(반드시 알아야함)
```
	static void rotate() {
		if(tmp[0].length==c) {
			int nxt[][]=new int[c][r];
			for(int i=0;i<r;++i) {
				for(int j=0;j<c;++j) {
					nxt[j][r-1-i]=tmp[i][j];
				}
			}
			tmp=new int[c][r];
			for(int i=0;i<c;++i) {
				for(int j=0;j<r;++j) {
					tmp[i][j]=nxt[i][j];
				}
			}
		}
		else if(tmp[0].length==r) {
			int nxt[][]=new int[r][c];
			for(int i=0;i<c;++i) {
				for(int j=0;j<r;++j) {
					nxt[j][c-1-i]=tmp[i][j];
				}
			}
			tmp=new int[r][c];
			for(int i=0;i<r;++i) {
				for(int j=0;j<c;++j) {
					tmp[i][j]=nxt[i][j];
				}
			}
		}
		
	}
```

xor 외우는 팁 연산자모양 연상 1두개있으면 0, 즉 1이 짝수면(0도 포함) 0, 홀수면 1이라 생각


@@@@@@어떻게 푸는게 좋을지 연습장에 설계부터 하기  
최대 총 시험시간의 1/3  


연습할때 아주쉬운문제(바로 설계가 나오는문제) 제외하고는
최소 한문제당 10분은 설계하는 습관  
어떻게 풀것인가  
ex)2018 [3차] 파일명 정렬 ->comparator쓰는 방법쓰면 편할걸  
설계 안하고 그냥 마구자비로 직접 문자열 나누면 엄청 복잡해진다  
설계의 중요성 너무너무중요  
@@@@@@@@@@@@@@@@@@@@@@@  




<h3>맨밑에 중요개념 및 코드들</h3>

공통쟁점(Issue) - simplemind, doc, 기타 여러가지에서 계속 분류작업<br>

프로그래머스 return 값 바꾸면 틀린다(ex- string[] 가 return값인데 원래의, ArrayList<String>으로 바꾸면 틀린다)  

java에서 NULL이 아니라 null이다! 아주 틀리기 쉽다 명심!(특히 ide없는 환경에서)

############<막판까지 코드와 함께 볼 자료 쟁점들>###########  
index별 쟁점  

<c언어에서의>  
정럴- qsort   
출력-  
입력-  
(문자열다루기-strcmp,strcpy,memcpy,memmove,strlen,strcpy 등)   
+문자열자르기(strtok)

char ans[50];
printf("%s", ans);

문자열출력의 기본 허나 이것은뒤에 꼬랑지도 출력되는 단점이있다..

단일문자 입력,출력 : %c
문자열 입력, 출력 : %s

문자열길이 strlen()으로 할수있는것은 배열크기 최대로 선언해놓고
scanf로 받을때만 가능

그냥 scanf받지 않은 char배열은 문자열길이 나타낼수없다.
char a[50];한후 0-10까지 넣어줘도
strlen(a)해도 50나온다

for (int i = 0; arr[0][i] != '\0'; i++) 그래서 이런식으로 하거나

for(int i = 0; dir[0][i]; i++)
이런식으로도 할수있다


sizeof-  
goto문-  
라이브러리-  
while루프-  
switch및 if문  
<br><br><br>
(배열 및 포인터) <br>


배열크기 : sizeof(배열이름)/sizeof(데이터형)
2차원배열 한 가로의 길이 구하는건없는듯..

c언어 배열도 초기화가 필요하다! <br>
단순히 int arr[10]; 이런식으로 선언만 하면<br>
-858993460 이런식으로 되어있다<br>
int arr[10]={0,}; ->요런식으로 초기화해주기<br>
https://blog.naver.com/bluemoonkjh/35902044<br>
https://cafe.naver.com/pplus/65<br>


int arr[strlen(dat)]; 이렇게하면 상수식에 함수호출을 사용할 수 없다고한다.<br>
그렇다고 int temp=strlen(dat);하고<br>
int arr[temp]해도 식에 상수값이 있어야한다고 한다...<br>
여러모로 java와 비교했을 때 매우불편한 것임만은 틀림없다<br>

물론 처음에 배열선언만 상수로 해야하는 것이고 그 이후에
문제를 풀면서 이후에는 for문안에서나 배열인덱스 접근할때
안에 함수나 변수 넣는 것 가능하다
```
	char dat[100];
	scanf("%s", dat);
	for (int i = 0; i < strlen(dat) / 2; ++i) {
		if (dat[i] != dat[strlen(dat) - 1 - i])
		{	
			printf("%d", 0);
			return 0;
		}
	}
```
요런식으로...

--2차원 배열 입력받기--
바로 2차원그래프 입력값이 붙어있다는것!

보통 인풋(input)은 띄워서 주는데 붙어있는 경우도 있다

띄워서 입력값이 주어지는경우는 scanf("%d",&map[i][j]) 로 쓰는데

붙어서 입력값이 주어지는 경우는

scanf("%1d",&map[i][j]) 로 쓰면 된다 -- >정수 1자리씩 입력받기

원래 문자열을 읽어 문자배열 안에 넣으면 &을 쓰진 않지만 
이경우엔 &을 써준다... 왜???인지는 더 찾아보기

간단

<br><br><br>

malloc, calloc(동적배열이 이거같다)-
(atoi, sprintf) <br>
큐-  
스택-  
링크드리스트-  
<br><br><br>
(그밖의 잡기술)  <br>
---정수를 문자열로---<br>
sprintf(문자열, "%d", 정수);
sprintf(문자열, "%x", 정수);

```
#define _CRT_SECURE_NO_WARNINGS    // sprintf 보안 경고로 인한 컴파일 에러 방지
#include <stdio.h>     // sprintf 함수가 선언된 헤더 파일

int main()
{
    char s1[10];       // 변환한 문자열을 저장할 배열
    int num1 = 283;    // 283은 정수

    sprintf(s1, "%d", num1);    // %d를 지정하여 정수를 문자열로 저장

    printf("%s\n", s1);         // 283

    return 0;
}
```

실행결과 283  


---문자열을 정수로---<br>
atoi(문자열);  
int atoi(char const *_String);  
성공하면 변환된 정수를 반환, 실패하면 0을 반환  

```
#include <stdio.h>
#include <stdlib.h>    // atoi 함수가 선언된 헤더 파일

int main()
{
    char *s1 = "283";   // "283"은 문자열
    int num1;

    num1 = atoi(s1);        // 문자열을 정수로 변환하여 num1에 할당

    printf("%d\n", num1);   // 283

    return 0;
}
```
실행결과 283
처음부터 숫자가 아니면 0으로 변환

283a → 283
283a30 → 283
283! → 283
283!30 → 283
a30 → 0
!30 → 0
abc → 0
!@# → 0


--c언어에서의 퀵정렬(효율성)--<br>
순서대로 배열, 배열의 크기, sizeof(데이터형), comparator이름  
qsort(a,n,sizeof(int),comp);  



오름차순 정렬
```
int comp(const void *a, const void *b){
	const int *a1 = (const int*)a;
	const int *a2 = (const int*)b;
	if (*a1 > *a2) {
		return 1;
	}
	else if (*a1 == *a2) {
		return 0;
	}
	else
		return -1;
}
```

<br>
--이진탐색--
```
int* find;//찾았는지 여부
int key;//찾고싶은 원소
int arr[] = {0,1,2,3,4,5};
if((find=bsearch(&key,arr,원소개수,sizeof(데이터형),구현한 comparator이름))!=NULL);
//NULL(java에서는 소문자 c언어에서는 대문자)이 아니면 찾은것
```

getchar는 나중에 일단은<br>
printf scanf로 하자<br>

모비스는 printf scanf필요도 없다 심지어<br>

#include<stdio.h><br>
#include<stdlib.h><br>
#include<string.h><br>
#include<math.h><br>
#include<stdbool.h><br>

이 5개는 일단 기본<br>
<br><br><br>
<br><br><br>
<br><br><br>














<JAVA언어><br>
문자열(ㅁㅂㅅ 가장 중요)-  
우선순위큐 -  
위상정렬 -   

<br><br><br>
(트라이)  <br>
쟁점1. 객체 : 자손노드들을 가리키는 배열, 노드가 종료 노드인지 나타내는 부울변수<br>
쟁점2. 언제쓰느냐? 자손노드 배열이 동적배열이 아니라 입력에 등장할 수 있는 모든 문자의 개수를 길이로 갖는 배열<br>
쟁점3. terminal변수를 boolean 말고 int로 쓰기 - 그러면 문자열을 정수로 대응하는<br>
사전자료구조로도 사용 가능<br>

쟁점4.트라이 객체에 지나간 단어가 있는지 여부, 먼저 삽입된 문자열의 번호 등 여러 변수등을
추가로 응용해 넣어줄수있다.

<br>
<br><br><br>

해시맵,해시셋 -  

<br><br><br>
(DP)<br>

dp자체가 재귀호출에서 적용하는게 가장 직관적
그래서 dfs+memoization(dp)를 많이 쓰는 것
expert 보급로문제 sol1.dp+메모이제이션, sol2. 

<br><br><br>

<br><br><br>
(Union-find)<br>
여러개의 부분집합 들 하면 바로 union find 떠올리기<br>


<br><br><br>
인접리스트 및 인접행렬  
최소 스패닝 트리(MST)  
시뮬레이션  
사이클찾기  


(덜중요한것)  
이진탐색, KMP, 펜윅트리, 다익스트라, 세그먼트트리,  
비트마스크,bfs,dfs(뭐 재귀는 너무기본이니까),  


(기타잡기술)
call by reference 배열객체는 잘 안따라가는 이유
```
 ArrayList<int[]> temp=new ArrayList<>();
LinkedList<int[]> q=new LinkedList<>();
        
 int[] curr=new int[] {1,2,3};
  int[] curr2=new int[] {4,5,6};
 temp.add(curr);
 q.add(curr);
 temp.add(curr2);
q.add(curr2);
        
 temp.set(0,new int[]{7,8,9});//아래와 같이 바꿔주면 new를 하게 되어 주소값이 새로 배당된다
 System.out.println(Arrays.toString(temp.get(0)));//[7, 8, 9]
System.out.println(Arrays.toString(q.get(0)));//[1, 2, 3]   -->따라서 출력 이렇게
```




################################################################  

10배빠른 해쉬 구현 : https://na982.tistory.com/103?category=92742  

<h3>트리표현은 크게  배열표현과  연결리스트 표현으로 나뉠 수 있다.</h3><br>



<차량정비소 저거못타면 지각이야>-문제들 처음에 부분 입력말고  
시간지나가는거 표현 안하기 위해 2-3-4-5-1순으로 하는 것!  

가중치 있으면(음의 가중치는 없어야) 다익스트라 없으면 bfs dfs  


boolean타입 if(v[i]==false)인데
if(v[i]=false)로 써도 못거르니까 주의<br><br>

bw.write할 시에  int ans=2이면 bw.write(ans+'\n')이렇게하면 안된다.<br>
반드시 앞에꺼 string으로 바꾸고 뒤에 '\n'붙여줘야<br>
모범답안 : bw.write(ans+""+'\n')<br><br>

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


쟁점 -문자열 비교시 equals로 비교(JAVA)  
그냥 객체 비교는 다 이걸로 가끔 까먹는다<br>

inline 의미, 포인터 참조반환-  c언어<br>

팩토리얼 이항계수<br>
피보나치 dp를 이용한 구현 반드시 알아두기<br>

c에는 참조변수 없다. c++에만 있다<br>

static 안붙여주는 변수는 static인곳에 바로 쓸수없다<br>
https://xxxelppa.tistory.com/29   참고해서 정리

<h1>중요 코드 및 개념:</h1><br>
사이클찾기 - 백준 2668번 숫자고르기<br>
단방향 그래프 및 dfs의 정수 - 백준 1325 효율적인 
