import java.io.*;
import java.util.*;


public class E{
    static java.io.PrintStream out=System.out;
    double solve(int n, int k, int[] t){
        int[] T=new int[n+1];
        double[] S=new double[n+1], tau=new double[n+1];
        for(int i=1; i<=n; i++){
            int tt=t[i];
            T[i]=T[i-1]+tt;
            tau[i]=tau[i-1]+(double)1/tt;
            S[i]=S[i-1]+(double)T[i]/tt;
        }
        double[] dp=new double[n+1];
        Arrays.fill(dp,Double.MAX_VALUE);
        dp[0]=0;
        for(int l=1; l<=k; l++){
            double[] temp=new double[n+1];
            int ll=l;
            for(int j=l; j<=l+n-k; j++){
                int m=(ll=test(ll,j,dp,S,T,tau));
                temp[j]=S[j]+dp[m-1]-S[m-1]+T[m-1]*tau[m-1]-T[m-1]*tau[j];
            }
            dp=temp;
            //out.println(Arrays.toString(dp));
        }
        return dp[n];
    }
    int test(int ll, int j, double[] dp, double[] S, int[] T, double[] tau){
        double min=Double.MAX_VALUE;
        for(int m=ll; m<=j; m++){
            double tt=dp[m-1]-S[m-1]+T[m-1]*tau[m-1]-T[m-1]*tau[j];
            if(tt<min){
                min=tt;
                ll=m;
            }
        }
        return ll;
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

