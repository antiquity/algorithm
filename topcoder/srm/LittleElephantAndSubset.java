import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;
import java.util.*;

public class LittleElephantAndSubset {
    int[][] dp = new int[1<<10][10]; 
    int GN = 0; 

    int dpf(int b, int c, int f){ 
        long lans = 0; 
        int ans = 0; 
        if (c == 0) { 
            if (dp[b][f] != -1) return dp[b][f]; 
            ans = 1; 
            for (int i = 1; i < 10; i ++) { 
                if ((b & (1 << i))==0 && i > f) { 
                    lans = c * 10L + i; 
                    if (lans < GN) { 
                        ans += dpf(b | (1 << i), (int)lans, i); 
                        ans %= 1000000007; 
                        ans += dpf(b | (1 << i), 0, i); 
                        ans %= 1000000007; 
                    } 
                } 
            } 
            dp[b][f] = ans; 
            return dp[b][f]; 
        } 
        for (int i = 0; i < 10; i ++) { 
            if ((b & (1 << i))==0) { 
                lans = c * 10L + i; 
                if (lans < GN) { 
                    ans += dpf(b | (1 << i), (int)lans, f); 
                    ans %= 1000000007; 
                    ans += dpf(b | (1 << i), 0, f); 
                    ans %= 1000000007; 
                } 
            } 
        } 
        return ans; 
    } 

    public int getNumber(int N) {
        GN = N + 1; 
        System.out.format("N=%d: ",N);
        for(int i=0; i<1<<10; i++)
            Arrays.fill(dp[i],-1);
        int res=dpf(0, 0, 0);
        boolean z=true;
        int cnt=0;
        for(int i=0; i<1<<10; i++){
            System.out.format("\n%11s: ",Integer.toString(i,2));
            z=true;
            for(int j=0; j<10; j++){
                if(dp[i][j]!=-1){
                    System.out.format("%7d|",dp[i][j]);
                    z=false;
                }else
                    System.out.format("%7s|"," ");
            }
            if(z) cnt++;
            else cnt=0;
            if(cnt>5) break;
        }
        System.out.format("\n");
        return res - 1; 
    }

    // BEGIN CUT HERE
    /** begin cut - don't modify this line*/
    public static void main(String[] a) {
        new LittleElephantAndSubset().runTestCase(0);
        new LittleElephantAndSubset().runTestCase(1);
        new LittleElephantAndSubset().runTestCase(2);
        new LittleElephantAndSubset().runTestCase(3);
    }

    public void runTestCase(int nbr) {
        switch(nbr) {
            case 0 : {
                         checkOutput(getNumber(3), 7, 0); break;
            }
            case 1 : {
                         checkOutput(getNumber(10), 767, 1); break;
            }
            case 2 : {
                         checkOutput(getNumber(47), 25775, 2); break;
            }
            case 3 : {
                         checkOutput(getNumber(4777447), 66437071, 3); break;
            }
        }
    }
    final void checkOutput(int mine, int them, int nbr) {
        boolean success = (mine==them);
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append(mine);
            out.append(", Expected: ");
            out.append(them);
        }
        System.out.println(out);
    }
    final void checkOutput(long mine, long them, int nbr) {
        boolean success = (mine==them);
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append(mine);
            out.append(", Expected: ");
            out.append(them);
        }
        System.out.println(out);
    }
    final void checkOutput(double mine, double them, int nbr) {
        boolean success = doubleCompare(mine, them);
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append(mine);
            out.append(", Expected: ");
            out.append(them);
        }
        System.out.println(out);
    }
    private static boolean doubleCompare(double expected, double result){
        double MAX_DOUBLE_ERROR = 1E-9;
        if(Double.isNaN(expected)){
            return Double.isNaN(result);
        }else if(Double.isInfinite(expected)){
            if(expected > 0){
                return result > 0 && Double.isInfinite(result);
            }else{
                return result < 0 && Double.isInfinite(result);
            }
        }else if(Double.isNaN(result) || Double.isInfinite(result)){
            return false;
        }else if(Math.abs(result - expected) < MAX_DOUBLE_ERROR){
            return true;
        }else{
            double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR),
                    expected * (1.0 + MAX_DOUBLE_ERROR));
            double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR),
                    expected * (1.0 + MAX_DOUBLE_ERROR));
            return result > min && result < max;
        }
    }
    final void checkOutput(char mine, char them, int nbr) {
        boolean success = (mine==them);
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append("'");
            out.append(mine);
            out.append("'");
            out.append(", Expected: ");
            out.append("'");
            out.append(them);
            out.append("'");
        }
        System.out.println(out);
    }
    final void checkOutput(String mine, String them, int nbr) {
        boolean success = (mine.equals(them));
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append("\"");
            out.append(mine);
            out.append("\"");
            out.append(", Expected: ");
            out.append("\"");
            out.append(them);
            out.append("\"");
        }
        System.out.println(out);
    }
    final void checkOutput(long[] mine, long[] them, int nbr) {
        boolean success = (Arrays.equals(mine, them));
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append("{");
            for(int x=0;x<mine.length;x++) {
                out.append(mine[x]);
                if(x<mine.length-1) out.append(", ");
            }
            out.append("}");
            out.append(", Expected: ");
            out.append("{");
            for(int x=0;x<them.length;x++) {
                out.append(them[x]);
                if(x<them.length-1) out.append(", ");
            }
            out.append("}");
        }
        System.out.println(out);
    }
    final void checkOutput(char[] mine, char[] them, int nbr) {
        boolean success = (Arrays.equals(mine, them));
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append("{");
            for(int x=0;x<mine.length;x++) {
                out.append(mine[x]);
                if(x<mine.length-1) out.append(", ");
            }
            out.append("}");
            out.append(", Expected: ");
            out.append("{");
            for(int x=0;x<them.length;x++) {
                out.append(them[x]);
                if(x<them.length-1) out.append(", ");
            }
            out.append("}");
        }
        System.out.println(out);
    }
    final void checkOutput(double[] mine, double[] them, int nbr) {
        boolean success = (Arrays.equals(mine, them));
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append("{");
            for(int x=0;x<mine.length;x++) {
                out.append(mine[x]);
                if(x<mine.length-1) out.append(", ");
            }
            out.append("}");
            out.append(", Expected: ");
            out.append("{");
            for(int x=0;x<them.length;x++) {
                out.append(them[x]);
                if(x<them.length-1) out.append(", ");
            }
            out.append("}");
        }
        System.out.println(out);
    }
    final void checkOutput(int[] mine, int[] them, int nbr) {
        boolean success = (Arrays.equals(mine, them));
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append("{");
            for(int x=0;x<mine.length;x++) {
                out.append(mine[x]);
                if(x<mine.length-1) out.append(", ");
            }
            out.append("}");
            out.append(", Expected: ");
            out.append("{");
            for(int x=0;x<them.length;x++) {
                out.append(them[x]);
                if(x<them.length-1) out.append(", ");
            }
            out.append("}");
        }
        System.out.println(out);
    }
    final void checkOutput(String[] mine, String[] them, int nbr) {
        boolean success = (Arrays.equals(mine, them));
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append("{");
            for(int x=0;x<mine.length;x++) {
                out.append(mine[x]);
                if(x<mine.length-1) out.append(", ");
            }
            out.append("}");
            out.append(", Expected: ");
            out.append("{");
            for(int x=0;x<them.length;x++) {
                out.append(them[x]);
                if(x<them.length-1) out.append(", ");
            }
            out.append("}");
        }
        System.out.println(out);
    }

    /** end cut - don't modify this line*/
    // END CUT HERE
}
