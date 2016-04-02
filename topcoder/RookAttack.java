import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class RookAttack {
    int rows, cols;
    private int bfs(int[][] map, int[] prev, int source, int sink){
        boolean[] visited=new boolean[map.length];
        Arrays.fill(prev,-1);
        Deque<Integer> q=new ArrayDeque<>();
        q.offerLast(source);
        visited[source]=true;
        while(!q.isEmpty()){
            int node=q.pollFirst();
            for(int i=0; i<map.length; i++)
                if(!visited[i] && map[node][i]>0){
                    prev[i]=node;
                    if(i==sink) return 1;
                    q.offerLast(i);
                    visited[i]=true;
                }
            //System.out.println(q);
        }
        return 0;
    }
    private int maxFlow(int[][] map, int source, int sink){
        int ret=0;
        int[] prev=new int[map.length];
        while(true){
            int flow=bfs(map, prev, source, sink);
            //System.out.println(prev);
            if(flow==0)
                break;
            ret+=flow;
            int p=sink;
            while(prev[p]!=-1){
                int preNode=prev[p];
                map[preNode][p]--;
                map[p][preNode]++;
                p=preNode;
            }
            //for(int i=0; i<map.length; i++) System.out.println(Arrays.toString(map[i])); System.out.println();
            //System.out.println(ret);
        }
        return ret;
    }
    public int howMany(int rows, int cols, String[] cutouts) {
        this.rows=rows; this.cols=cols;
        int N=rows+cols+2;
        int[][] map=new int[N][N];
        for(int i=0; i<rows; i++)
            Arrays.fill(map[i],rows,N-2,1);
        for(int i=rows; i<N-2; i++)
            map[i][N-1]=1;
        Arrays.fill(map[N-2],0,rows,1);
        for(String s:cutouts){
            String[] lists=s.split(",");
            for(String coord:lists){
                String[] rc=coord.split(" ");
                int r=Integer.parseInt(rc[0]), c=Integer.parseInt(rc[1]);
                map[r][rows+c]=0;
            }
        }
        //int[][] mapClone=(int[][])map.clone();

        //for(int i=0; i<N; i++) System.out.println(Arrays.toString(map[i])); System.out.println();
        return maxFlow(map,N-2,N-1);
    }
// BEGIN CUT HERE
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new RookAttack().runTestCase(0);
		new RookAttack().runTestCase(1);
		new RookAttack().runTestCase(2);
		new RookAttack().runTestCase(3);
		new RookAttack().runTestCase(4);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(howMany(8, 8, new String[] {}), 8, 0); break;
			}
			case 1 : {
				checkOutput(howMany(2, 2, new String[] {"0 0","0 1","1 1","1 0"}), 0, 1); break;
			}
			case 2 : {
				checkOutput(howMany(3, 3, new String[] {"0 0","1 0","1 1","2 0","2 1","2 2"}), 2, 2); break;
			}
			case 3 : {
				checkOutput(howMany(3, 3, new String[] {"0 0","1 2","2 2"}), 3, 3); break;
			}
			case 4 : {
				checkOutput(howMany(200, 200, new String[] {}), 200, 4); break;
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
