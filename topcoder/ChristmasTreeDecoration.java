import java.util.*;

public class ChristmasTreeDecoration {
    public boolean connected(int[] input){
        int[] con=input.clone();
        int N = con.length;
        List<Integer> set = new ArrayList<Integer>();
        set.add(0);
        int i=0, x;
        while(true){
            if(i>=set.size()) break;
            x=set.get(i);
            if(con[x]>-1){
               if(!set.contains(con[x])) set.add(con[x]);
                con[x]=-1;
            }
            for(int j=0; j<N-1; j++) if(con[j]==x){
                if(!set.contains(j)) set.add(j);
                con[j]=-1;
            }
            i++;
        }
        return set.size()==N;
    }
    public int solve(int[] col, int[] x, int[] y) {
        int N = col.length;
        int[][] map = new int[N][N];
        for(int i=0; i<x.length; i++){
            map[x[i]-1][y[i]-1]=(col[x[i]-1]==col[y[i]-1]? 2 : 1);
            map[y[i]-1][x[i]-1]=map[x[i]-1][y[i]-1];
        }
        //for(int i=0; i<N; i++) println(Arrays.toString(map[i]));
        int[] con= new int[N];
        Arrays.fill(con,-1);
        int sum=0;
        int res=Integer.MAX_VALUE;
        int i=0;
        boolean hasmore;
        while(i>=0){
            hasmore=false;
            if(i==N-1){
                if(connected(con)){
                    res=Math.min(res,sum);
                    //println("res="+(sum-N+1)+": "+Arrays.toString(con));
                }
            }else
                for(int j=con[i]+1; j<N; j++){
                    if(map[i][j]>0){
                        sum+=map[i][j];
                        if(con[i]>=0) sum-=map[i][con[i]];
                        hasmore=true;
                        con[i]=j;
                        break;
                    }
                }
            if(hasmore) i++;
            else{ 
                if(con[i]>=0){
                    sum-=map[i][con[i]];
                    con[i]=-1;
                }
                i--;
            }
        }
        return res-N+1;
    }
// BEGIN CUT HERE
    void println(Object o) { System.out.println(o); }
    void print (Object o) {System.out.print(o); }
    void println() {System.out.println(); }
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new ChristmasTreeDecoration().runTestCase(0);
		new ChristmasTreeDecoration().runTestCase(1);
		new ChristmasTreeDecoration().runTestCase(2);
		new ChristmasTreeDecoration().runTestCase(3);
		new ChristmasTreeDecoration().runTestCase(4);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(solve(new int[] {1,1,2,2}, new int[] {1,2,3,4}, new int[] {2,3,4,1}), 1, 0); break;
			}
			case 1 : {
				checkOutput(solve(new int[] {1,1,2,2}, new int[] {1,2,3,4,1,2}, new int[] {2,3,4,1,3,4}), 0, 1); break;
			}
			case 2 : {
				checkOutput(solve(new int[] {50,50,50,50}, new int[] {1,2,3,1,1,2}, new int[] {2,3,4,4,3,4}), 3, 2); break;
			}
			case 3 : {
				checkOutput(solve(new int[] {1,4,2,3}, new int[] {1,2,3}, new int[] {2,3,4}), 0, 3); break;
			}
			case 4 : {
				checkOutput(solve(new int[] {1,1,1,2,2,2,3,3,3,4,4,4}, new int[] {1,2,3,4,5,6,7,8,9,10,11,12,1,1,1,1,1,1}, new int[] {2,3,1,5,6,4,8,9,7,11,12,10,5,7,12,11,6,4}), 5, 4); break;
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
