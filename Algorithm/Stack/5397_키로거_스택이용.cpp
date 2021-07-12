//이중 연결리스트로도 풀수있다. 반드시 해보기 https://www.crocus.co.kr/473
//deque를 써서도 풀수있다.
//stringbuilder를 쓰면 시간 초과난다. 단순히 append는 상관없지만
//커서부분에 insert delete할때 O(N)이 걸린다.
// (인덱스까지 접근은 O(1)이지만 그 후 배열값을 다시 만드는데 O(N)이 걸린다)


#include <iostream>
#include <string>
#include <stack>
using namespace std;

stack<char> s1, s2;//커서를 기준으로 왼쪽 오른쪽 구분
int T;
int main() {

	ios::sync_with_stdio(0);
	cin.tie(0);
  //이 두줄 속도 향상
  
	cin >> T;
	while (T--) {
		string s;
		cin >> s;
		for (int i = 0; i<s.size(); ++i) {
    
      //c++에서 switch() 괄호안에는 정수형 변수만 들어올수있다.
      //그냥 if else로 해주기
      
			 char c = s.at(i);
		
			 if (c == '<' && !s1.empty()) {
				 s2.push(s1.top());
				 s1.pop();
			 }

			 else if (c == '>' && !s2.empty()) {
				 s1.push(s2.top());
				 s2.pop();
			 }

			 else if (c == '-' && !s1.empty()) {
				 s1.pop();
			 }
			 //이거 그냥 else로 처리하면 <로 들어오는데
			 //s1이 빈상태인것도 else로 들어가게 
			 //하나하나 꼼꼼히
			 else if ((c >= 'a' && c <= 'z') ||
				 (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))
				 s1.push(c);
				
			}
		while (!s1.empty()) {
			s2.push(s1.top());
			s1.pop();
		}
		while (!s2.empty()) {
			cout << s2.top();
			s2.pop();
		}

		cout << '\n';
	}		
}
