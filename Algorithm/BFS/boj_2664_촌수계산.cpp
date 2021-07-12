/*
이 문제는 전형적인 BFS문제이다. 또한, BFS의 특징을 살려 해당 정점에 
대해 갈 수 있는 최단경로를 구하는 문제이다.

이 문제의 핵심:	depth[j] = depth[temp] + 1;  temp의 정점에서 j(정점)
으로 갈 수 있을 때 +1 시켜준다.*/


using namespace std;

#include<queue>

int n,m; // n: 전체 사람의 수, m:부모 자식들 간의 관계 개수

int num1, num2; // 촌수 계산해야하는 두사람 번호

int x, y;//부머 자식간의 관계

int depth[101];

int family[101][101];

bool visit[101];

queue <int> q;



void bfs(int i)

{

	visit[i] = true;

	q.push(i);

	int temp;

	while (!q.empty())

	{

		temp = q.front(); q.pop();

		for (int j = 1; j <= n; j++)

		{

			if (family[temp][j] == 1 && !visit[j])

			{

				visit[j] = true;

				depth[j] = depth[temp] + 1;

				q.push(j);

			}

		}

	}

}

int main()

{

	cin >> n; //전체 사람의 수

	cin >> num1 >> num2; // 촌수 계산해야하는 두사람 번호

	cin >> m; // 부모 자식간의 관계 개수



	int a, b;

	for (int i = 1; i <= m; i++)

	{

		cin >> a >> b;

		family[a][b] = 1;

		family[b][a] = 1;

	}

	bfs(num1);



	if (depth[num2] != 0)

		cout << depth[num2] << endl;

	else

		cout << "-1" << endl;

	return 0;

}
