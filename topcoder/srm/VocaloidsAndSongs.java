import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;
import java.util.*;

public class VocaloidsAndSongs {
    final int N = 1000000007;
    int[] primes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47};
    long nchoosek(int a, int b){
        if(b>a/2) b=(a-b);
        int M = primes.length;
        int[] res = new int[M];
        int i=0;
        int temp;
        for(i=0; i<M; i++) res[i]=0;
        i=0;
        while(i<b){
            temp = a-i;
            for(int j=0; j<M; j++){
                while(temp%primes[j]==0){
                    res[j]++;
                    temp=temp/primes[j];
                }
            }
            i++;
        }
        i=2;
        while(i<=b){
            temp = i;
            for(int j=0; j<M; j++){
                while(temp%primes[j]==0){
                    res[j]--;
                    temp=temp/primes[j];
                }
            }
            i++;
        }
        long tt=1;
        for(int j=0; j<M; j++){
            while(res[j]>0){
                tt=(tt*primes[j])%N;
                res[j]--;
            }
        }
        return tt;
    }
    public int count(int s, int a, int b, int c) {
        //System.out.println(nchoosek(49,3));
        int max = Math.max(Math.max(a,b),c);
        //System.out.println(max);
        long res, t;
        res=0; 
        int temp=1;
        for(int i=0; i<=s-max; i++){
            t=1;
            t = (t*nchoosek(s-i,a))%N;
            t = (t*nchoosek(s-i,b))%N;
            t = (t*nchoosek(s-i,c))%N;
            t = (t*nchoosek(s,s-i))%N;
            res=(res+temp*t+N)%N;
            temp=-temp;
            //System.out.println(res);
        }
        //System.out.println(res);
        return (int)res;
    }
    // BEGIN CUT HERE
    /** begin cut - don't modify this line*/
    public static void main(String[] a) {
        new VocaloidsAndSongs().runTestCase(0);
        new VocaloidsAndSongs().runTestCase(1);
        new VocaloidsAndSongs().runTestCase(2);
        new VocaloidsAndSongs().runTestCase(3);
        new VocaloidsAndSongs().runTestCase(4);
    }

    public void runTestCase(int nbr) {
        switch(nbr) {
            case 0 : {
                         checkOutput(count(3, 1, 1, 1), 6, 0); break;
            }
            case 1 : {
                         checkOutput(count(3, 3, 1, 1), 9, 1); break;
            }
            case 2 : {
                         checkOutput(count(50, 10, 10, 10), 0, 2); break;
            }
            case 3 : {
                         checkOutput(count(18, 12, 8, 9), 81451692, 3); break;
            }
            case 4 : {
                         checkOutput(count(50, 25, 25, 25), 198591037, 4); break;
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
