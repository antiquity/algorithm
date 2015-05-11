import java.io.*;
import java.util.*;


public class C{
    class Hiker implements Comparable<Hiker>{
        int id;
        long t;
        long speed;
        Hiker(int id, long t, long speed){
            this.id=id;
            this.t=t;
            this.speed=speed;
        }
        public int compareTo(Hiker aa){
            return this.t>aa.t? 1 : (this.t<aa.t? -1:0);
        }
        public String toString(){
            return String.format("[%d,%d,%d]",id,speed,t);
        }
    }
    public static void main(String[] argin) {
        C inst = new C();
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        long N, D, H, M;
        for(int ii=1; ii<=T; ii++){
            N = in.nextInt();
            Queue<Hiker> q = new PriorityQueue<Hiker>();
            int id=0;
            for(int i=0; i<N; i++){
                D = in.nextInt();
                H = in.nextInt();
                M = in.nextInt();
                for(int j=0; j<H; j++)
                    q.offer(inst.new Hiker(id++,(360-D)*M,M++));
            }
            System.out.printf("Case #%d: %d\n", ii, inst.solver(q));
        }
    }
    int solver(Queue<Hiker> q){
        int N=q.size();
        boolean[] status = new boolean[N];
        Arrays.fill(status,true);
        Hiker h;
        int ret=N,res=N;
        while(ret<2*N){
            //System.out.println(q + "\t" + ret);
            while(true){
                h = q.poll();
                q.offer(new Hiker(h.id,h.t+360*h.speed, h.speed));
                if(status[h.id]){
                    ret--;
                    status[h.id]=false;
                }else
                    ret++;
                if(h.t!=q.peek().t) break;
            }
            res=Math.min(res,ret);
        }
        return res;
    }
}

