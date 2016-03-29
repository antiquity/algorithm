import java.io.*;
import java.util.*;

public class D{
    long solver(int[] a, int n, int k){
        Deque<Integer> error = new ArrayDeque<>();
        long sum=0;
        for(int i=0; i<=n || sum!=0; i++){
            if(sum>=0)
                sum>>=1;
            else
                sum=-((-sum)>>1);
            if(i<=n) sum+=a[i];
            if(sum%2!=0)
                if(sum>0)
                    error.offerFirst(-(i+1));
                else
                    error.offerFirst((i+1));
            //System.out.println(i+": "+sum);
        }
        int base=Math.abs(error.peekLast())-1;
        //System.out.println(error);
        long cor=0;
        int x=Math.abs(error.peekFirst())-1;
        while(!error.isEmpty()){
            cor<<=1;
            if(x==Math.abs(error.peekFirst())-1)
                cor+=Integer.signum(error.pollFirst());
            x--;
            if(Math.abs(cor)>2*k)
                return 0;
        }
        //System.out.println(cor);
        int ret=0;
        while(base>=0){
            if(base<=n){
                if(Math.abs(cor+a[base])<=k){
                    if(base!=n || cor+a[base]!=0)
                        ret++;
                }
            }
            base--;
            cor<<=1;
            if(Math.abs(cor)>2*k)
                break;
        }
        return ret;
    }
    public static void main(String[] argin) {
        D inst = new D();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n=in.nextInt(), k=in.nextInt();
            int[] a = new int[n+1];
            for(int i=0; i<=n; i++)
                a[i]=in.nextInt();
            System.out.println(inst.solver(a,n,k));
        }
    }
}

