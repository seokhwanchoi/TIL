//

import java.io.*;
import java.util.*;
public class Main {
static int n,m,temp,a[],left,right,mid;
static boolean flag;
public static void main(String[] args)throws Exception {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
    n=Integer.parseInt(br.readLine().trim());
    a=new int[n];
    StringTokenizer st=new StringTokenizer(br.readLine());
    for(int i=0;i<n;++i) {
        a[i]=Integer.parseInt(st.nextToken());
    }
    m=Integer.parseInt(br.readLine().trim());
    Arrays.sort(a);//정렬 필수
    st=new StringTokenizer(br.readLine());
    for(int i=0;i<m;++i) {
        temp=Integer.parseInt(st.nextToken());
        flag=false;
        left=0; right=a.length-1;
        while(left<=right) {
            mid=(left+right)/2;
            if(a[mid]==temp) {
                flag=true;
                break;
            }
            else {
                if(a[mid]>temp) {
                    right=mid-1;
                }
                else
                    left=mid+1;
            }

        }
        if(flag)
            bw.write("1"+'\n');
        else
            bw.write("0"+'\n');
    }

    bw.flush();
}
}
