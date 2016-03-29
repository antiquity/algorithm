import java.io.*;
import java.util.*;


public class E{
    class Pair{
        long val;
        int id;
        Pair(int id, long val){
            this.id=id;
            this.val=val;
        }
        public String toString(){
            return String.valueOf(this.val);
        }
    }
    long solver(int n, int k, int b, int c, int[] t){
        Arrays.sort(t);
        final int base=t[n-1];
        //final int base=12;
        Long ret = Long.MAX_VALUE;
        for(int j=base; j<base+5; j++){
            PriorityQueue<Long> q = new PriorityQueue<>(new Comparator<Long>(){
                public int compare(Long a, Long b){
                    return Long.signum(b-a);
                }
            });
            long sum=0;
            //System.out.println(Arrays.toString(t));
            for(int i=0; i<n; i++){
                long dif = j-t[i];
                long time;
                if(b>=5*c)
                    time=dif*c;
                else
                    time=dif/5*b + dif%5*c;
                q.offer(time);
                sum+=time;
                if(q.size()>k){
                    sum-=q.poll();
                }
                //System.out.println(q);
                if(q.size()==k){
                    long test;
                    if(b>=5*c)
                        test=sum-((long)j-t[i])*c*k;
                    else
                        test=sum-((long)j-t[i])/5*b*k;
                    //System.out.format("idx=%d, t[idx]=%d, %d->%d\n",i,t[i],sum,test);
                    ret = Math.min(test,ret);
                }
            }
            if(b>=5*c) break;
        }
        return ret;
    }
    public static void main(String[] argin) {
        E inst = new E();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n=in.nextInt(), k=in.nextInt(), b=in.nextInt(), c=in.nextInt();
            int[] t = new int[n];
            for(int i=0; i<n; i++)
                t[i]=in.nextInt();
            System.out.println(inst.solver(n,k,b,c,t));
        }
    }
}

