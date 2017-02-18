import java.io.*;
import java.util.*;

public class D{
    private int pos(int i){
        if(dp[i]>0) return dp[i];
        dp[i]=1;
        Collection<int[]> priors = map[i];
        for(int[] x : priors){
            int prePos;
            if(1+pos(x[0])>dp[i]){
                dp[i]=1+pos(x[0]);
                ret = Math.max(ret,x[1]);
            }
        }
        return dp[i];
    }
    private long solver(int n, int m){
        for(int i=0; i<n; i++){
            if(pos(i)==n){
                return ret;
            }
        }
        //System.out.println(Arrays.toString(dp));
        return -1;
    }

    static Collection<int[]>[] map;
    static int[] dp;
    static int ret;
    public static void main(String[] argin) {
        D inst = new D();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n=in.nextInt(), m = in.nextInt();
            map = new Collection[n];
            for(int i=0; i<n; i++){
                map[i]=new ArrayList<int[]>();
            }
            for(int i=0; i<m; i++){
                map[in.nextInt()-1].add(new int[]{in.nextInt()-1,i+1});
            }
            dp = new int[n];
            System.out.println(inst.solver(n,m));
        }
    }
}

