/*2020-01-15
쟁점1 . 1번과 2번은 각각 3,4,5번 컴퓨터를 해킹할 수 있고

3번은 4,5번 컴퓨터를

4,5번은 자기 자신 이외에는 해킹할 수 없다.

쟁점2. 각각의 컴퓨터를 몇번 호출했냐로 해킹할 수 있는 컴퓨터의 개수를 구할 수 있다.

1번은 (1,3,4,5)

2번은 (2,3,4,5)

3번은 (3,4,5)

4번은 (4)

5번은 (5)

쟁점3. 체크해줘야 할부분이 2개 - 방문유무와 각각의 컴퓨터 번호마다 해킹할 수 있는 컴퓨터 개수
인접리스트를 만들어줘서 어떤 노드가 무슨 노드 신뢰하는지에 따라 push하기(위상정렬 비슷하게)
선후관계 헷갈릴수있는데 손으로 살짝 수식 그려보면서 하면 더 이해하기 쉽다. 심플하고 좋은 코드


쟁점 4 dfs 한번 돌고 난후  visit초기화해주는 것도 핵심
*/
#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int N,M;
vector<int> graph[10001];
vector<int> visited; //방문 유무
vector<int> hacking; //각각의 컴퓨터 번호마다 해킹할 수 있는 컴퓨터 개수

int ans=0; //가장 많이 해킹할 수 있는 컴퓨터 개수

void dfs(int node){

    hacking[node]++;
    ans=max(ans,hacking[node]);

    for(int i=0;i<graph[node].size();i++){

        int next_node=graph[node][i];
        if(!visited[next_node]){
            visited[next_node]++;
            dfs(next_node);
        }
    }
}
int main(){

    scanf("%d %d",&N,&M);

    int s,e;
    for(int i=0;i<M;i++){
        scanf("%d %d",&s,&e);
        graph[s].push_back(e);
    }

    hacking=vector<int> (N+1,0);

    for(int i=1;i<=N;i++){
        visited=vector<int> (N+1,0); //방문 초기화

        visited[i]++;
        dfs(i);
    }

    for(int i=1;i<=N;i++){
        if(hacking[i]==ans){
            printf("%d ",i);
        }
    }
    return 0;
}
