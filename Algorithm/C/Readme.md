비쥬얼스튜디오 콘솔 유지 - [프로젝트(Project)] – ['프로젝트 이름' 속성(Properties)]  
[구성 속성(Configuration Properties)] – [링커(Linker)]에서 [시스템(System)]을 선택하고,  
오른쪽의 [하위 시스템(Subsystem)]에서 콘솔 Console (/SUBSYSTEM:CONSOLE)  
그 후에 f5를 누르는게 아니라 ctrl f5를 누르기!!  
실행시키고싶은것만main으로나머지는함수이름바꿔주기main2라든지  

C언어 퀵소트 라이브러리

알고리즘 문제를 풀다가 문제가 생겼다.

바로 2차원그래프 입력값이 붙어있다는것!

보통 인풋(input)은 띄워서 주는데 붙어있는 경우도 있다

띄워서 입력값이 주어지는경우는 scanf("%d",&data)  로 쓰는데

붙어서 입력값이 주어지는 경우는

scanf("%1d",&data)    로 쓰면 된다 -- >정수 1자리씩 입력받기

간단
