import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class FoxAirline2 {
    class City{
        List<int[]> next = new ArrayList<int[]>();
    }
    public String isPossible(int n, int[] a, int[] b) {
        City[] cities = new City[n];
        for(int i=0; i<n; i++){
            cities[i]=new City();
        }
        for(int i=0; i<a.length; i++){
            cities[a[i]].next.add(new int[]{b[i],i});
            cities[b[i]].next.add(new int[]{a[i],i});
        }

        boolean[] taken = new boolean[a.length];
        int[] visited = new int[n];
        taken[0]=true;
        List<Integer> c1Flights = new ArrayList<Integer>();
        c1Flights.add(0);
        visited[a[0]]=1;
        visited[b[0]]=1;
        int next=1, c1Count=2;
        while(!c1Flights.isEmpty()){
            //System.out.println(c1Flights + ": " + c1Count);
            if(c1Count==n){
                List<Integer> ret = new ArrayList<Integer>();
                boolean[] vi = new boolean[n];
                check(ret,cities,0,vi,taken);
                if(ret.size()==n){
                    //System.out.println(c1Flights+": "+ret +": "+ "sucess");
                    return "Possible";
                }else{
                    //System.out.println(c1Flights+": "+ret +": "+ "fail");
                }
                next=a.length;
            }
            while(next<a.length && (taken[next] || 
                        (visited[a[next]]>0 && visited[b[next]]>0)
                        ||
                        (visited[a[next]]==0 && visited[b[next]]==0)
                        ))
                next++;
            if(next<a.length){
                c1Flights.add(next);
                taken[next]=true;
                visited[a[next]]++;
                visited[b[next]]++;
                if(visited[a[next]]==1) c1Count++;
                if(visited[b[next]]==1) c1Count++;
                next=1;
            }else{
                next = c1Flights.remove(c1Flights.size()-1);
                taken[next]=false;
                visited[a[next]]--;
                visited[b[next]]--;
                if(visited[a[next]]==0) c1Count--;
                if(visited[b[next]]==0) c1Count--;
                next++;
            }
        }
        return "Impossible";
    }

    private void check(List<Integer> ret, City[] cities, int i, boolean[] visited, boolean[] taken){
        visited[i]=true;
        ret.add(i);
        for(int[] f : cities[i].next){
            if(!visited[f[0]] && !taken[f[1]])
                check(ret,cities,f[0],visited,taken);
        }
    }


// BEGIN CUT HERE
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new FoxAirline2().runTestCase(0);
		new FoxAirline2().runTestCase(1);
		new FoxAirline2().runTestCase(2);
		new FoxAirline2().runTestCase(3);
		new FoxAirline2().runTestCase(4);
		new FoxAirline2().runTestCase(5);
		new FoxAirline2().runTestCase(6);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(isPossible(4, new int[] {0,0,0,1,1,2}, new int[] {1,2,3,2,3,3}), "Possible", 0); break;
			}
			case 1 : {
				checkOutput(isPossible(6, new int[] {0,0,0,0,1,1,1,2,2,3,0}, new int[] {1,2,3,4,2,3,4,3,4,4,5}), "Impossible", 1); break;
			}
			case 2 : {
				checkOutput(isPossible(5, new int[] {0,0,0,1,1,2,2,3}, new int[] {1,2,4,2,4,3,4,4}), "Possible", 2); break;
			}
			case 3 : {
				checkOutput(isPossible(2, new int[] {0,1,1}, new int[] {1,0,0}), "Possible", 3); break;
			}
			case 4 : {
				checkOutput(isPossible(10, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 7, 7, 8}, new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5, 6, 7, 8, 9, 3, 4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9, 5, 6, 7, 8, 9, 6, 7, 8, 9, 7, 8, 9, 8, 9, 9}), "Possible", 4); break;
			}
			case 5 : {
				checkOutput(isPossible(2, new int[] {0}, new int[] {1}), "Impossible", 5); break;
			}
                        case 6: {
				checkOutput(isPossible(10, new int[]
                                            {0, 0, 3, 4, 5, 2, 7, 8, 6, 8, 8, 1, 6, 3, 7, 7, 7, 3, 8, 0, 7, 8, 5, 5, 4, 2, 4, 7, 0, 5, 3, 8, 1, 2, 7, 2, 1, 4, 4, 6, 0, 8, 6, 4, 4, 3, 0, 3, 7, 8},
                                           new int[] {8, 2, 0, 6, 1, 1, 4, 4, 5, 2, 4, 7, 0, 5, 6, 5, 4, 5, 4, 7, 3, 1, 8, 2, 0, 0, 8, 4, 7, 3, 7, 6, 0, 0, 3, 6, 4, 5, 2, 0, 8, 2, 1, 7, 6, 5, 6, 4, 2, 4}),"Impossible",6); break;
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
