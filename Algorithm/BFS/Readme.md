Breadth-First Search  
쟁점1- bfs든 dfs든 두 연결요소 사이의 관계 주어지면 인접리스트 인접행렬 둘 중 하나 무조건 활용  
그 다음에 요소 하나씩 bfs dfs  각각 하나씩 돌려보기 

```
for(int i=0; i<n; i++) {
   for(int j=0; j<n; j++) {    //첫번째 행부터 갈 수 있는 길들을 큐에 추가 
      if(matrix[i][j]==1) {   //갈수 있는 길 
        queue.add(j);       //큐에 추가 
      }
   }
   while(!queue.isEmpty()) {   //큐가 비지 않았으면 
     tmp=queue.poll();       //큐에 있는 데이터를 꺼낸 뒤 잠시 저장 
       BFS(i,tmp);     //BFS메소드 호출 
     }
}
```

쟁점2- 케빈 베이컨의 법칙처럼 몇단계에 걸쳐 연결되어있는지 알아내기 위해선  
connect배열 따로 만들어서 +1씩 해주기 bfs while문 안에서
```
 while(!q.isEmpty()) {
            int curr=q.poll();

            for(int i=1;i<=n;++i) {
                if(adj[curr][i]==1 && !v[i]) {
                    v[i]=true;
                    q.offer(i);
                    connect[i]=connect[curr]+1;// 몇단계에 걸쳐 연결되어있는지 알아내기 위해선  
//connect배열 따로 만들어서 +1씩 해주기 bfs while문 안에서 이러면 bfs(x)로 들어왔을때
//connect[1]~connect[n]까지 x부터 몇단계만에 갈수있는지 
                }
            }
        }
```
쟁점3
```
//쟁점 .. 밑으로 내려오게하는거 그냥무조건 전체배열 돌리면서
//하기 list로 하면 위에서부터 하게되고, 위에서 내려온 밑에걸 못보게 된다. 그냥 단순리스트안에있는 것만
//보게됨
			
for(int i=0;i<list.size();++i) {
				
			
				
/*
* #        .  이때 list로하게되면 마지막것뺴고 다지워짐  .   이래됨
* #  --    #   					      .
* #	   #					      .
* .	   #					      #
* 
 * 
 */
if(list.get(i)[0]+1>=8) {  -->잘못구현한 코드
    map[list.get(i)[0]][list.get(i)[1]]='.';
    list.remove(i);
    i--;
}
else {
    map[list.get(i)[0]][list.get(i)[1]]='.';
    map[list.get(i)[0]+1][list.get(i)[1]]='#';
    list.set(i, new int[] {list.get(i)[0]+1,
    list.get(i)[1]});
}
}
```


쟁점 4. 단계별 bfs(int temp=q.size()) 인접행렬 인접리스트에도 유용  

쟁점 5. 인접행렬보다는 인접리스트쓰기(메모리초과 가능성) -ex.백준1707 이분그래프  

쟁점 6. 어떤 객체(ArrayList나 Class객체) 처음엔 null 들어있다
```
for(int i=0;i<v;++i) adj[i]=new ArrayList<>();
  System.out.println(Arrays.toString(adj));
if(adj[0]==null) System.out.println("gkdl");
//gkdl이 출력된다. 여기서 adj[0].size()등의 함수쓰면 코드전체가 에러생긴다
```  

쟁점 7. 인접리스트 전부 new ArrayList해줘야하는 경우는 해줘야겠지만 최적화차원에서 들어갈때마다 new ArrayList해주고 
원소 삽입시 null인 index에서만 new ArrayList해주고
안 만들어진거는 null조건으로 빼버리는게 시간 조금이라도 아낄수있다.
```
for(int i=0;i<v;++i) adj[i]=new ArrayList<>();
  for(int i=0;i<e;++i) {
	st=new StringTokenizer(br.readLine());
	a=Integer.parseInt(st.nextToken())-1;
	b=Integer.parseInt(st.nextToken())-1;
	if(adj[a]==null) adj[a]=new ArrayList<>();
	if(adj[b]==null) adj[b]=new ArrayList<>();
	adj[a].add(b);
	adj[b].add(a);
  }
  
  //그 후엔 문제조건에 따라 null이 아닌지점만 탐색하는 식으로... 다 미리 만들어줘야하는 경우도 있긴하다
for(int u=0;u<v;++u) {
	if(adj[u]!=null) {
```

쟁점 8 .출발점과 도착점이 같을때 항상 신경써줘야한다. 인덱스 양 끝부분신경쓰듯. 옛날에 쟁점정리해놓은것 참고  
