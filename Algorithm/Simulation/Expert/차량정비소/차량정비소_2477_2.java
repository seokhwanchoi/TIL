//접수데스크 -> pq -> 정비데스크 -> arrive순으로 구현해야하는 걸찾는게 핵심
//(문제에서는 arrive -> 접수데스크 -> pq -> 정비데스크 순으로 진행되는 것처럼 주어지지만
//그 순서로 구현하면 접수->pq로 빠지는 즉시 arrive에서 접수로 채워넣는 구현을 추가해줘야함 -- > 즉 복잡해짐)

// (추가)
//먼저 내보낼거 다 내보내고 채워넣는게 핵심
//내보내는 건 순서대로 끝까지 도달후 채워넣는 건 역순으로
import java.io.*;
import java.util.*;
 
public class Solution {
    static int N,M,K,A,B,queueSize,sum,turn,leave,idx,arrive[];
    static int rct[],rpt[];
    static customer rcd[],rpd[];
    static PriorityQueue<customer> pq;//정비데스크 들어가기 전 대기
    static class customer{
        int cnum;//고객 번호
        int reception;//접수데스크 이용한 창구번호
        int repair;//정비데스크 이용한 창구 번호
        int time;//각각의 데스크에서 끝날 때까지 남은시간
        int endTime;//접수데스크까지 끝난 시각 기록
         
        customer(int cnum){
            this.cnum=cnum;
            this.reception=-1;
            this.repair=-1;
            this.time=0;
        }
    }

    public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream("res/expert_2477.txt"));
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T=Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;++tc) {
            st=new StringTokenizer(br.readLine());
            N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());
            K=Integer.parseInt(st.nextToken());
            A=Integer.parseInt(st.nextToken())-1;
            B=Integer.parseInt(st.nextToken())-1;
            rct=new int[N]; rpt=new int[M];
            arrive=new int[K];
            rcd=new customer[N]; rpd=new customer[M];
            pq=new PriorityQueue<>(new Comparator<customer>() {
            	public int compare(customer o1, customer o2) {
            		if(o1.endTime>o2.endTime)
				return 1;
			else if(o1.endTime==o2.endTime) {
				if(o1.reception>o2.reception) {
					return 1;
				}
				else if(o1.reception==o2.reception) {
					return 0;
				}
				else
					return -1;
			}
			else
				return -1;
            	  }
            	
            }) ;
            sum=0;
            st=new StringTokenizer(br.readLine());
            for(int i=0;i<N;++i) {
                rct[i]=Integer.parseInt(st.nextToken());
            }
            st=new StringTokenizer(br.readLine());
            for(int i=0;i<M;++i) {
                rpt[i]=Integer.parseInt(st.nextToken());
            }
            st=new StringTokenizer(br.readLine());
            for(int i=0;i<K;++i) {
                arrive[i]=Integer.parseInt(st.nextToken());
            }
            turn=0;
            leave=0;
            idx=0;
            while(leave!=K) {
                 
                for(int i=0;i<N;++i) {
                    if(rcd[i]!=null && --rcd[i].time==0) {
                        rcd[i].endTime=turn;
                        pq.offer(rcd[i]);
                        rcd[i]=null;
                    }
                }
                 
                for(int i=0;i<M;++i) {
                    if(rpd[i]!=null && --rpd[i].time==0) {
                        if(rpd[i].reception==A && rpd[i].repair==B)sum+=rpd[i].cnum;
                        rpd[i]=null;
                        ++leave;
                    }
                    if(rpd[i]==null && !pq.isEmpty()) {
                        rpd[i]=pq.poll();
                        rpd[i].repair=i;
                        rpd[i].time=rpt[i];
                    }
                }
                 
                for(int i=0;i<N;++i) {
                    if(idx==K || arrive[idx]>turn)break;
                    if(rcd[i]==null) {
                        rcd[i]=new customer(++idx);
                        rcd[i].time=rct[i];
                        rcd[i].reception=i;
                    }
                }
                ++turn;
            }
            bw.write(sum==0?"#"+tc+" "+(-1)+'\n':"#"+tc+" "+sum+'\n');
        }
        bw.flush();
    }
}
