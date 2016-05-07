import java.io.*;
import java.util.*;


public class E{
    static java.io.PrintStream out=System.out;
    double solve(int n, int k, int[] t){
        //double[][] g=new double[n+1][n+1];
        //for(int i=1; i<=n; i++){
        //    int sum=0;
        //    double time=0;
        //    for(int j=i; j<=n; j++){
        //        sum+=t[j];
        //        time+=((double)sum)/t[j];
        //        g[i][j]=time;
        //    }
        //    out.println(Arrays.toString(g[i]));
        //}
        //out.println();
        double[] dp=new double[n+1];
        Arrays.fill(dp,Double.MAX_VALUE);
        dp[0]=0;
        for(int l=0; l<k; l++){
            for(int j=(l==0?0:l+n-k); j>=l; j--){
                int sum=0;
                double time=dp[j];
                for(int jj=j+1; jj<=n-k+l+1; jj++){
                    sum+=t[jj];
                    time+=((double)sum)/t[jj];
                    dp[jj]=Math.min(dp[jj], time);
                }
            }
            //out.println(Arrays.toString(dp));
        }
        return dp[n];
    }
    public static void main(String[] argin) {
        E inst = new E();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt(), k=in.nextInt();
            int[] t = new int[n+1];
            for(int i=1; i<=n; i++)
                t[i]=in.nextInt();
            out.println(inst.solve(n,k,t));
        }
    }
}

