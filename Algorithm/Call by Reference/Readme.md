정확한지 계속 수정  

내가 아는 한 객체는 총 3가지 : 함수, 배열, 그 외(ex.클래스 객체)  

즉 어떤 속성들의 집합이면 객체  

예를 들어 int a=2이면 a의 값은 2다, char c='a'면 c의 값은 'a'다 String s="abc"면 s의 값은 abc다라 표현가능(어떤 집합이 아니다)   

여기서 string은 equals로 비교해야하는 등 객체의 느낌을 갖고있긴 하다... 하지만 call by reference는 일어나지 않는듯하다..  

하지만 int[] a={1,2}면 a의 값은? -->뭐..a는 1과 2의 집합이다  

함수도 한번 호출될때마다 스택에 각기 다른 주소로 호출되서 그런가(그 안의 표현들의 집합??)?  

이들을 함수의 인자로 넘기거나 같은 변수명으로 무언가 작업할때(arraylist에 넣어준다든지.. 값을 수정한다든지) 주의할 사항이 있다.  

이들은 C++의 call by reference와 비슷하게 주소를 참조하는?식으로 작동한다.

따라서 ArrayList나 Queue같은 곳에 넣어주면 분명히 다른 객체를 넣어준 것같은데 온통 같은 객체로 변해있는 괴현상이 일어난다.  

또 이런 현상을 이용하여 비교적 쉽게 문제를 풀수있는 경우도 존재한다.
ex)https://www.acmicpc.net/problem/17837   ㅁㅇㄹ 및 내소스 참고-   
```
       /// node란 객체를 선언한 상태에서 
Node node = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
		Integer.parseInt(st.nextToken()) - 1);

list.add(node);
map[node.row][node.col].add(node);
```
위처럼 ArrayList의 list와 2차원 배열의 map의 같은 node(그때 그때 new해주어서 변하는 node빼놓고 for문이나 재귀에서)를 넣어주면  
같은 시점에 같은 주소로 생성된 node들은 한곳에서 바뀌어도 다른 곳도 자동적으로 바뀌어  
이중으로 수정해야하는 번거로움을 줄여준다.(이 편리함은 생각보다 매우 강력하다. 희비를 엇가를정도로.. 인덱스꼬여서 틀리는 경우가 있으므로..)

이를테면 list의 node.row를 set함수를 이용해 2에서 1로 바뀌었다면 그에 해당하는 map의 node도 자동적으로 node.row가 1로바뀌어있다.    
따라서 문제가 복잡한 시뮬레이션 문제일수록 class 객체를 생성해 이 기능을 적극활용하는 것이 매우매우 중요하다  

이번엔 재귀함수에서의 경우(혹은 인자로 넘겨주는 경우)에 대해 살펴보자.  

예를 들어보자  카카오 2019 후보키 문제 중 조합을 만드는 과정이다.
```
import java.io.*;
import java.util.*;

class practice2{
    static int garo,sero;
    static ArrayList<boolean[]> list;
    static boolean[] v;
    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
    	String[][] input=new String[6][4];
    	solution(input);
    }
    static int solution(String[][] relation){
        garo=relation[0].length;
        sero=relation.length;
        
        //조합 미리 만들어두기
        list=new ArrayList<>();
        //boolean[] v=new boolean[garo];
        
        v=new boolean[garo];
        
        dfs(0,0);
        for(int i=0;i<list.size();++i){
           System.out.println(Arrays.toString(list.get(i)));
        }
        return 1;
    }
    static void dfs(int cnt, int idx){
        if(cnt==garo){
            System.out.println(Arrays.toString(v));//여기선 다르게 출력되도
            //넣는건 v로 같으므로 list에서 v 넣어주기도 전에 밑에서 v가 바뀌면 list안에 
            //v도 몽땅 바뀐 v로 바뀐다.
            list.add(v);
            return;
        }
        for(int i=idx;i<garo;++i){
           
           v[i]=true;//여기서 v바뀌는 순간 list에 있는 v들도 전부 똑같이 바뀐다
           dfs(cnt+1,i+1);
           v[i]=false;
           dfs(cnt+1,i+1);
          
        }
    }
}
```

여기서 첫번째 방법으로 V배열을 static변수로 취하고 조합을 돌려보았다.  
주소가 참조된건지 list에 계속 v라는 동일한 배열만 넣어주므로 v가 for문에서 true/false로 바뀔때마다  
list에 있는 v들도 전부 똑같이 바뀐다  

<br>
<br>
<br>
<br>
<br>
<br>



```
import java.io.*;
import java.util.*;

class practice2{
    static int garo,sero;
    static ArrayList<boolean[]> list;
    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
    	String[][] input=new String[6][4];
    	solution(input);
    }
    static int solution(String[][] relation){
        garo=relation[0].length;
        sero=relation.length;
        
        //조합 미리 만들어두기
        list=new ArrayList<>();
        boolean[] v=new boolean[garo];
  
        dfs(0,0,v);
        for(int i=0;i<list.size();++i){
           System.out.println(Arrays.toString(list.get(i)));
        }
        return 1;
    }
    static void dfs(int cnt, int idx, boolean[] v){
        if(cnt==garo){
            System.out.println(Arrays.toString(v));//여기선 다르게 출력되도
            //넣는건 v로 같으므로 list에서 v 넣어주기도 전에 밑에서 v가 바뀌면 list안에 
            //v도 몽땅 바뀐 v로 바뀐다.
            list.add(v);
            return;
        }
        for(int i=idx;i<garo;++i){
           
           v[i]=true;//여기서 v바뀌는 순간 list에 있는 v들도 전부 똑같이 바뀐다
           dfs(cnt+1,i+1);
           v[i]=false;
           dfs(cnt+1,i+1);
          
        }
    }
}
```
두 번째 static변수가 아닌 함수로 넣어줘도 마찬가지의 결과가 도출된다(이클립스 출력 및 디버깅 참고)  

<br>
<br>
<br>
<br>
<br>
<br>

해결을 위해선... a형 문제 풀때 2차원 맵 상태저장해주듯이 따로 복사배열을 만들어 고놈을 넣어주면된다  
복사배열은 매 깊이의 상태마다 새로 new해서 만들어주는 것이므로 설사 배열의 이름은 vistemp로 동일할지라도  
전부 다 다른 주소값으로 매핑되었기 때문에 vistemp끼리 서로 영향을 끼치지 않는다.  
[해결코드]  
```
import java.io.*;
import java.util.*;

class practice2{
    static int garo,sero;
    static ArrayList<boolean[]> list;
    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
    	String[][] input=new String[6][4];
    	solution(input);
    }
    static int solution(String[][] relation){
        garo=relation[0].length;
        sero=relation.length;
        
        //조합 미리 만들어두기
        list=new ArrayList<>();
        boolean[] v=new boolean[garo];
        
        
        dfs(0,0,v);
        for(int i=0;i<list.size();++i){
           System.out.println(Arrays.toString(list.get(i)));
        }
        return 1;
    }
    static void dfs(int cnt, int idx, boolean[] v){
        if(cnt==garo){
            //System.out.println(Arrays.toString(v));
            boolean[] vistemp=new boolean[garo];
            vistemp=v.clone();//배열 인자로 넘길땐 callby reference 되서 무조건 이렇게 해줘야
            list.add(vistemp);//전역 변수로 해도 call by reference일어난다.
            //무조건 따로 빼줘야 매우매우매우매우중요@@
            return;
        }
        for(int i=idx;i<garo;++i){
           
           v[i]=true;
           dfs(cnt+1,i+1,v);
           v[i]=false;
           dfs(cnt+1,i+1,v);
          
        }
    }
}
```
<br>
<br>

알고리즘을 자바로 풀때 어떻게 보면 가장 중요한 쟁점이라고 할 수 있을 것이다. 반드시 숙지하고 살을 붙이자  
