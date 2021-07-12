<2019.12.20> 커서를 이동하며 문자를 삽입하거나 삭제하는 문제는 왠만하면 스택이나 큐로 풀 수있다.
알아두기

????: 우선순위큐 Integer Character String Double 같은 기본자료형은 compartor구현 안해도 상관없지만 그외에 class 따로 구현한 거라든지 배열을 generic으로 하면 comparator 구현해야한다...-->이거 정확한지 확인 검증

https://kin.naver.com/profile/index.nhn?u=jCFqsm0aZFt14b%2Fu5%2BS8G5G1TN9ynI2BXEAcnv1Z9KU%3D

???? : arraylist 위에서부터 빼는 거는 i--안해줘도 된다 --->이거 정확한지 확인 검증

<백준 17837 새로운게임2>
```java
for(int i=map[x][y].size()-1;i>=idx;--i) {
			
	map[targetx][targety].add(map[x][y].get(i));
				
	checkFour(curr);
				
	list.set(map[x][y].get(i)[0]-1,new int[] {map[x][y].get(i)[0],targetx,targety,i==idx?curr[3]:map[x][y].get(i)[1]});
	   
  	map[x][y].remove(i);
		
}
```
    
