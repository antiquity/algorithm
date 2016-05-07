import java.io.*;
import java.util.*;


public class B{
    static java.io.PrintStream out=System.out;
    int solve(int n, int[][] pairs){
        int[] div=new int[n];
        int d1min=n-1, d2max=0;
        for(int[] pair:pairs){
            int a=Math.min(pair[0],pair[1]);
            int b=Math.max(pair[0],pair[1]);
            if(div[a]==1)
                return 0;
            if(div[a]==0){
                if(a>=d1min)
                    return 0;
                div[a]=2;
                d2max=Math.max(d2max,a);
            }
            if(div[b]==2)
                return 0;
            if(div[b]==0){
                if(b<=d2max)
                    return 0;
                div[b]=1;
                d1min=Math.min(b,d1min);
            }
        }
        return d1min-d2max;
    }
    public static void main(String[] argin) {
        B inst = new B();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt(), m=in.nextInt();
            int[][] pairs = new int[m][2];
            for(int i=0; i<m; i++){
                pairs[i][0]=in.nextInt()-1;
                pairs[i][1]=in.nextInt()-1;
            }
            out.print(inst.solve(n,pairs));
        }
    }
}

