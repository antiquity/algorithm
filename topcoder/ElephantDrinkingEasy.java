import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;
import java.util.*;

public class ElephantDrinkingEasy {
    public int maxElephants(String[] map) {
        int n = map.length;
        char[][] m = new char[n][n]
        int[][] t = new int[n*4][n*4];
        int p;
        boolean test;
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                m[i][j]=map[i].charAt(j);

        for(int i=0; i<4*n; i++){
            switch(i/4){
                case 0:
                    p=i%n;
                    test=false;
                    for(int j=0; j<n; j++){
                        if(m[j][p]=='N') m[j][p]='S'; else{
                            m[j][p]='T';
                            test=true;
                            break;
                        }
                    }
                    if(test){
                        get[i]=true;
                        for(int j=i+1; j<4*n; j++){
                            if(test && hasConf(i,j,m)){ t[i][j]=1; }else t[i][j]=0;
                        }
                    }else get[i]=false;
                    for(int j=0; j<n; j++){
                        if(m[j][p]=='S') m[j][p]='N';
                        else if(m[j][p]=='T')
                            m[j][p]='T';
                            test=true;
                            break;
                        }
                    }

                    for(int
                    if(hasConf(i,j



                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        }
        int cnt =0;
        boolean good, b1,b2,b3,b4;

        for(int i=0; i<R; i++){
            for(int j =0; j<C; j++){
                if(map[i].charAt(j)=='Y'){
                    b1 = false;
                    for(int k=i-1; k>=0; k--){ if(map[k].charAt(j)=='Y'){ b1 = true; break; } }
                    b2 = false;
                    for(int k=i+1; k<R ; k++){ if(map[k].charAt(j)=='Y'){ b2 = true; break; } }
                    b3 = false;
                    for(int k=j-1; k>=0; k--){ if(map[i].charAt(k)=='Y'){ b3 = true; break; } }
                    b4 = false;
                    for(int k=j+1; k<C ; k++){ if(map[i].charAt(k)=='Y'){ b4 = true; break; } }
                    if(!(b1 && b2 && b3 && b4)){
                        cnt++;
                        t[i][j]=1;
                    }
                }
            }
            //System.out.println(Arrays.toString(t[i]));
        }
        return cnt;


    }
    // BEGIN CUT HERE
    /** begin cut - don't modify this line*/
    public static void main(String[] a) {
        new ElephantDrinkingEasy().runTestCase(0);
        new ElephantDrinkingEasy().runTestCase(1);
        new ElephantDrinkingEasy().runTestCase(2);
        new ElephantDrinkingEasy().runTestCase(3);
        new ElephantDrinkingEasy().runTestCase(4);
        new ElephantDrinkingEasy().runTestCase(5);
        new ElephantDrinkingEasy().runTestCase(6);
    }

    public void runTestCase(int nbr) {
        switch(nbr) {
            case 0 : {
                         checkOutput(maxElephants(new String[] {"NNNNN",  "NNYYN",  "NYNNN",  "NNYNN",  "NNNNN"}), 4, 0); break;
            }
            case 1 : {
                         checkOutput(maxElephants(new String[] {"YYY",  "YYY",  "YYY"} ), 8, 1); break;
            }
            case 2 : {
                         checkOutput(maxElephants(new String[]  {"YNYN",   "NNYY",   "YYNN",   "YYYY"}), 10, 2); break;
            }
            case 3 : {
                         checkOutput(maxElephants(new String[]  {"YNYN",   "YNYY",   "YYNN",   "YYYY"}), 10, 3); break;
            }
            case 4 : {
                         checkOutput(maxElephants(new String[] {"YYNYN",  "NYNNY",  "YNYNN",  "YYNYY",  "YYNNN"}), 12, 4); break;
            }
            case 5 : {
                         checkOutput(maxElephants(new String[] {"YYNYN",  "NYNYY",  "YNYYN",  "YYNYY",  "YYNNN"}), 13, 5); break;
            }
            case 6 : {
                         checkOutput(maxElephants(new String[] {"NN",  "NN"}), 0, 6); break;
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
