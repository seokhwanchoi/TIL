Arrays.sort안에서도 Comparator가 사용가능

        int[][] dat=new int[n][3];
        Arrays.sort(dat,new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                if(o1[0]>o2[0] && o1[1]>o2[1]){
                    return -1;
                }
                else if(o1[0]<o2[0] && o1[1]<o2[1]){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        });
        
        -> 이런식으로 2차원배열이면 그 이름을 넣어준다
