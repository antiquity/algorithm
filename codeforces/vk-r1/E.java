import java.io.*;
import java.util.*;


public class E{
    long solver(long n){
        return n*(n+1)*3+1;
    }
    public static void main(String[] argin) {
        E inst = new E();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n=in.nextInt(), k=in.nextInt(), b=in.nextInt(), c=in.nextInt();
            int[] t = new int[n];
            for(int i=0; i<n; i++)
                t[i]=in.nextInt();
            Arrays.sort(t);
            int[] count = new int[5];
            int sum=0, goal=t[k-1];
            for(int i=0; i<(n-k+1); i++){
                if(i<k){
                    dif = goal-t[i];
                    count[dif%5]++;
                    sum+=dif/5;
                    if(i==k-1){
                        ret = Math.min(ret, sum+0*count[0]+1*count[1]+2*count[2]+3*count[3]+4*count[4]);
                        ret = Math.min(ret, sum+1*count[0]+2*count[1]+3*count[2]+4*count[3]+1*count[4]);
                        ret = Math.min(ret, sum+2*count[0]+3*count[1]+4*count[2]+1*count[3]+2*count[4]);
                        ret = Math.min(ret, sum+3*count[0]+4*count[1]+1*count[2]+2*count[3]+3*count[4]);
                    }
                }else{
                    dif=goal-t[i-k];
                    count[dif%5]--;
                    sum-=dif/5;
                    int x=t[i]-goal;
                    goal=t[i];
                    sum+=(k-1)*(x/5);



                }
                for(int goal = t[i+k-1]; goal<=t[i+k-1]+3; goal++){
                    int sum=0;
                    for(int j=i; j<i+k; j++)
                }
            }

            System.out.println(inst.solver(in.nextInt()));
        }
    }
}

