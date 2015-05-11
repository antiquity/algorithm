import java.io.*;
import java.util.*;


public class A{
    int solver(int R, int C, int W){
        int[] dp = new int[20];
        dp[0]=0;
        int temp;
        for(int i=1; i<W; i++){
            temp=2*W;
            for(int j=1; j<=i; j++)
                temp=Math.min(temp,Math.max(dp[j-1],dp[i-j]+1));
            dp[i]=temp;
        }
        //System.out.println(Arrays.toString(dp));
        temp=(C-W)/W+dp[C%W]+W;
        if(C>=2*W) 
            temp=Math.max(temp, (C-2*W)/W+dp[W-1]+W);
        return temp+(R-1)*(C/W);

    }
    public static void main(String[] argin) {
        A inst = new A();
        Scanner in = new Scanner(System.in);
        int R,C,W;
        int T = in.nextInt();
        for(int ii=1; ii<=T; ii++){
            R = in.nextInt();
            C = in.nextInt();
            W = in.nextInt();
            System.out.printf("Case #%d: %d\n", ii, inst.solver(R,C,W));
        }
    }
}

