vector <[data type]> [변수이름] --> vector \<int\> v

vector \<int\> v(5);
기본값(0)으로 초기화 된 5개의 원소를 가지는 vector v를 생성합니다.

[c++에서 발생할 수 있는 치명적일 수 있는 에러 모음]  
1. 인덱스 벗어난 것 - 비주얼스튜디오에서 잡아내지 못한다 
배열의 요소에 접근할 때 인덱스로 음수를 지정하거나, 배열의 크기를 벗어난 인덱스를 지정해도 컴파일 에러가 발생하지 않습니다.   
하지만 실행을 해보면 쓰레기값이 출력됩니다. 즉, 배열의 범위를 벗어난 인덱스에 접근하면 배열이  
아닌 다른 메모리 공간에 접근하게 됩니다.  
https://dojang.io/mod/page/view.php?id=295  


2. tuple 초기화시 make_tuple()을 쓰지 않고 중괄호로 묶어서 초기화하는 방법 사용->더 알아보기 - make_tuple을 쓰지 않아도  
비주얼 스튜디오에서 잡아내지 못한다  
tuple<int,int,int> t1=make_tuple(1,2,3);  ->이렇게 쓰도록 

3. bits/stdc++.h - 비주얼 스튜디오에서 못 쓴다(쓸 순 있는데 다운받아야 해서 실제 시험환경에서 무의미)  
gcc에서만 쓸 수 있다