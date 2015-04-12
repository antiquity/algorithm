import java.util.*;

public class TwoLLogo {
    public long count(int i,int j,String[] grid,int uu, int rr){
        int u,r;
        u=i;
        while( u-1>=uu && grid[u-1].charAt(j)=='.') u--;
        r=j;
        while( r+1<rr && grid[i].charAt(r+1)=='.') r++;
        return (i-u)*(r-j);
    }
    public long countWays(String[] grid) {
        int N=grid.length, M=grid[0].length();
        long res=0, t1,t2;
        int u,r;
        //   .
        // .
        //System.out.println(" .\n. \n");
        for(int i=2; i<=N-1; i++)
            for(int j=0; j<=M-3; j++){
                if(grid[i].charAt(j)!='.') continue;
                t1=count(i,j,grid,0,M);
                if(t1==0) continue;

                for(int ii=1; ii<=i-1; ii++)
                    for(int jj=j+1; jj<M-1; jj++){
                        if(grid[ii].charAt(jj)!='.') continue;
                        t2=count(ii,jj,grid,0,M);
                        res+=t1*t2;
                        //if(t1*t2>0) System.out.println(res);
                    }
            }
        // . .
        //System.out.println("..\n");
        for(int i=1; i<N; i++)
            for(int j=2; j<M-1; j++){
                if(grid[i].charAt(j)!='.') continue;
                t1=count(i,j,grid,0,M);
                if(t1==0) continue;

                for(int jj=0; jj<=j-2; jj++){
                    if(grid[i].charAt(jj)!='.') continue;
                    t2=count(i,jj,grid,0,j);
                    res+=t1*t2;
                        //if(t1*t2>0) System.out.println(res);
                }
            }
        // .
        // .
        //System.out.println(".\n.\n");
        for(int i=1; i<N-2; i++)
            for(int j=0; j<M-1; j++){
                if(grid[i].charAt(j)!='.') continue;
                t1=count(i,j,grid,0,M);
                if(t1==0) continue;

                for(int ii=i+2; ii<=N-1; ii++){
                    if(grid[ii].charAt(j)!='.') continue;
                    t2=count(ii,j,grid,i+1,M);
                    res+=t1*t2;
                        //if(t1*t2>0) System.out.println(res);
                }
            }
        //    .
        //       .
        //System.out.println(". \n .\n");
        for(int i=1; i<=N-2; i++)
            for(int j=0; j<M-2; j++){
                if(grid[i].charAt(j)!='.') continue;
                u=i;
                while( u-1>=0 && grid[u-1].charAt(j)=='.') u--;
                t1=i-u;
                if(t1==0) continue;

                for(int ii=i+1; ii<N; ii++)
                    for(int jj=j+1; jj<M-1; jj++){
                        if(grid[ii].charAt(jj)!='.') continue;
                        r=jj;
                        while( r+1<M && grid[ii].charAt(r+1)=='.') r++;
                        t2=r-jj;
                        if(t2==0) continue;

                        u=ii;
                        while(u-1>=0 && grid[u-1].charAt(jj)=='.') u--;
                        r=j;
                        while( r+1<M && grid[i].charAt(r+1)=='.') r++;
                        if(u<=i && r>=jj)
                            res+=t1*t2*((r-j)*(ii-u)-(i-u+1)*(r-jj+1));
                        else
                            res+=t1*t2*(ii-u)*(r-j);
                        //if(t1*t2>0) System.out.println(res);
                    }
            }

        return res;


    }
    // BEGIN CUT HERE
    void println(Object o) { System.out.println(o); }
    void print (Object o) {System.out.print(o); }
    void println() {System.out.println(); }
    /** begin cut - don't modify this line*/
    public static void main(String[] a) {
        new TwoLLogo().runTestCase(0);
        new TwoLLogo().runTestCase(1);
        new TwoLLogo().runTestCase(2);
        new TwoLLogo().runTestCase(3);
        new TwoLLogo().runTestCase(4);
        new TwoLLogo().runTestCase(5);
        new TwoLLogo().runTestCase(6);
        new TwoLLogo().runTestCase(7);
    }

    public void runTestCase(int nbr) {
        switch(nbr) {
            case 0 : {
                         checkOutput(countWays(new String[] {"....",  "...."}), 1, 0); break;
            }
            case 1 : {
                         checkOutput(countWays(new String[] {".##..",  "...#.",  ".#.#.",  "#...#"}), 3, 1); break;
            }
            case 2 : {
                         checkOutput(countWays(new String[] {"..#.",  "#.#.",  "....",  "..#."}), 4, 2); break;
            }
            case 3 : {
                         checkOutput(countWays(new String[] {"..",  ".."}), 0, 3); break;
            }
            case 4 : {
                         checkOutput(countWays(new String[] {".#.#",  "....",  ".#.#",  "...."}), 34, 4); break;
            }
            case 5 : {
                         checkOutput(countWays(new String[] {"##############",  "##############",  "#.############",  "#.############",  "#.############",  "#.############",  "#.############",  "#.############",  "#.#####.######",  "#.#####.######",  "#.#####.######",  "#....##.######",  "#######.######",  "#######.######",  "#######.######",  "#######.######",  "#######.######",  "#######.######",  "#######......#",  "##############"}), 1350, 5); break;
            }
            case 6 : {
                         checkOutput(countWays(new String[] {"#......",  ".#....#",  ".#.#...",  "#....#.",  ".##..#.",  ".#.....",  ".....#.",  ".#.#...",  ".#...#.",  "..##..."}), 2386, 6); break;
            }
            case 7 : {
                         checkOutput(countWays(new String[] {"...#..........................",  "..............................",  "..............................",  "..................#...#.......",  "..................#...........",  "..............................",  "...........#..................",  "..............................",  ".....#..#.....................",  ".......................#......",  "..................#.....#.....",  "..............................",  "..............................",  "..............................",  "..............................",  "..#...........................",  "..............................",  "..............................",  "..............................",  "#............................#",  "..............................",  ".....#.........#............#.",  "..............................",  ".........................#....",  ".#............................",  ".............#................",  "..............................",  "..............................",  ".......................#......",  ".............#................"}), 5020791386L, 7); break;
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
