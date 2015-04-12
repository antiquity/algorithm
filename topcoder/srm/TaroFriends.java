import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;
import java.util.*;

public class TaroFriends {
    public int getNumber_1(int[] co, int X) {
        Arrays.sort(co);
        int N = co.length;
        int md = co[N-1]-co[0];
        if(X>=md) return md;
        int l=Math.min(co[0]+X, co[N-1]-X),
            r=Math.max(co[0]+X, co[N-1]-X);
        int tl,tr;
        ArrayList<Integer> rem = new ArrayList<Integer>();
        int oS=Integer.MAX_VALUE;
        while(N!=oS && N>0){
            for(int i=0; i<N; i++){
                tl = co[i]-X;
                tr = co[i]+X;
                if(tr<l){
                    l=tr;
                    System.out.print("1r");
                }else if(tr<=r){
                    System.out.print("2r");
                    continue;
                }else{
                    if(tl<l){
                        if(l-tl<tr-r){
                            System.out.print("1l");
                            l=tl;
                        }else if(l-tl==tr-r){
                            System.out.print("o");
                            rem.add(co[i]);
                        }else{
                            r=tr;
                            System.out.print("3r");
                        }
                    }else if(tl<=r){
                        System.out.print("2l");
                        continue;
                    }else{
                        System.out.print("3l");
                        r=tl;
                    }
                }
            }
            oS=N;
            N=rem.size();
            System.out.println(N);
            if(N>0){
                co=new int[N];
                for(int i=0; i<N; i++) co[i]=rem.get(i);
                rem.clear();
            }
        }
        if(N>0) r=co[0]+X;
        return r-l;
    }
    public int getNumber(int[] co, int X) {
        Arrays.sort(co);
        System.out.println(Arrays.toString(co));
        int res = co[co.length - 1] - co[0];
        int ans=0;
        for (int toRight = 0; toRight + 1 < co.length; ++toRight) {
            int left = Math.min(co[0] + X, co[toRight + 1] - X);
            int right = Math.max(co[toRight] + X, co[co.length - 1] - X);
            if(right-left<=res){
                res=right-left;
                ans=toRight;
            }
            //res = Math.min(res, right - left);
        }
        System.out.println(ans);
        return res;
    }
// BEGIN CUT HERE
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new TaroFriends().runTestCase(6);
                //new TaroFriends().runTestCase(0);
		//new TaroFriends().runTestCase(1);
		//new TaroFriends().runTestCase(2);
		//new TaroFriends().runTestCase(3);
		//new TaroFriends().runTestCase(4);
		//new TaroFriends().runTestCase(5);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(getNumber_1(new int[] {-3, 0, 1}, 3), 3, 0); break;
			}
			case 1 : {
				checkOutput(getNumber(new int[] {4, 7, -7}, 5), 4, 1); break;
			}
			case 2 : {
				checkOutput(getNumber(new int[] {-100000000, 100000000}, 100000000), 0, 2); break;
			}
			case 3 : {
				checkOutput(getNumber(new int[] {3, 7, 4, 6, -10, 7, 10, 9, -5}, 7), 7, 3); break;
			}
			case 4 : {
				checkOutput(getNumber(new int[] {-4, 0, 4, 0}, 4), 4, 4); break;
			}
			case 5 : {
				checkOutput(getNumber(new int[] {7}, 0), 0, 5); break;
                        }
			case 6 : {
				checkOutput(getNumber(new int[] {523, 740, -995, -723, 249, 309, 395, -385, 779, -842, 177, -150, 389, 409, 33, -98, 740, -452, 765, -650, 449, -219, -356, 513, 133, -761, 607, 560, -923, 810, -956, -92, -485, 211, 923, -124}, 818), 1471, 6);
				checkOutput(getNumber_1(new int[] {523, 740, -995, -723, 249, 309, 395, -385, 779, -842, 177, -150, 389, 409, 33, -98, 740, -452, 765, -650, 449, -219, -356, 513, 133, -761, 607, 560, -923, 810, -956, -92, -485, 211, 923, -124}, 818), 1471, 6);
                                break;
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
