import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class Coversta {
    int r,c;
    private int getNumber(String[] a, int x, int y){
        if(x<0 || x>=r || y<0 || y>=c) return 0;
        return a[x].charAt(y)-'0';
    }
    public int place(String[] aa, int[] x, int[] y) {
        r=aa.length; c=aa[0].length();
        int n=x.length;
        Set<Point> neighbor=new HashSet<Point>();
        Set<Point> xy = new HashSet<Point>();
        neighbor.add(new Point(0,0));
        for(int i=0; i<n; i++) xy.add(new Point(x[i],y[i]));
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++){
                Point a=new Point(x[i]-x[j],y[i]-y[j]);
                if(!neighbor.contains(a)) neighbor.add(a);
            }
        int[][] score = new int[r*c][3];
        for(int i=0; i<r; i++)
            for(int j=0; j<c; j++){
                int map=0;
                for(int l=0; l<n; l++)
                    map+=getNumber(aa,i+x[l],j+y[l]);
                score[i*c+j][0]=map;
                score[i*c+j][1]=i;
                score[i*c+j][2]=j;
            }
        Arrays.sort(score,new Comparator<int[]>(){
            public int compare(int[] aaa, int[] bbb){
                return bbb[0]-aaa[0];
            }
        });
        //for(int[] kk:score) System.out.print(Arrays.toString(kk));
        int ret=0;
        for(int i=0; i<score.length; i++)
            for(int j=i+1; j<score.length; j++){
                if(score[i][0]+score[j][0]<=ret)
                    break;
                if(neighbor.contains(new Point(score[j][1]-score[i][1],score[j][2]-score[i][2]))){
                    int temp=0;
                    for(int k=0; k<n; k++)
                        if(!xy.contains(new Point(score[j][1]+x[k]-score[i][1],score[j][2]+y[k]-score[i][2])))
                            temp+=getNumber(aa,score[j][1]+x[k],score[j][2]+y[k]);
                    ret=Math.max(ret,score[i][0]+temp);
                }else
                    ret=Math.max(ret,score[i][0]+score[j][0]);
            }
        return ret;
    }
    class Point{
        int x,y;
        Point(int b, int c){
            x=b; y=c;
        }
        public int hashCode(){
            return x*100+y;
        }
        public boolean equals(Object zz){
            Point kk = (Point)zz;
            return (x==kk.x && y==kk.y);
        }
    }
// BEGIN CUT HERE
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new Coversta().runTestCase(0);
		new Coversta().runTestCase(1);
		new Coversta().runTestCase(2);
		new Coversta().runTestCase(3);
		new Coversta().runTestCase(4);
		new Coversta().runTestCase(5);
		new Coversta().runTestCase(6);
		new Coversta().runTestCase(7);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(place(new String[] {"11",  "11"}, new int[] {0,0}, new int[] {0,1}), 4, 0); break;
			}
			case 1 : {
				checkOutput(place(new String[] {"11",  "11"}, new int[] {0,1}, new int[] {0,1}), 3, 1); break;
			}
			case 2 : {
				checkOutput(place(new String[] {"15",  "61"}, new int[] {0}, new int[] {0}), 11, 2); break;
			}
			case 3 : {
				checkOutput(place(new String[] {"151",  "655",  "661"}, new int[] {0,0,-1}, new int[] {0,1,0}), 33, 3); break;
			}
			case 4 : {
				checkOutput(place(new String[] {"303",  "333",  "000"}, new int[] {-1,-1}, new int[] {-1,1}), 12, 4); break;
			}
			case 5 : {
				checkOutput(place(new String[] {"0000000",  "1010101"} , new int[] {-1,-1}, new int[] {-1,1}), 0, 5); break;
			}
			case 6 : {
				checkOutput(place(new String[] {"34113", "87427", "79319", "86502"}, new int[]{3, 0, 0, 2, -1, 3, -3, 3, -1}, new int[]{0, 0, 1, 1, 3, -1, -1, -4, -4}),46,6); break;
                        }
			case 7 : {
				checkOutput(place(new String[] {"8324115", "9510645", "3089134", "7463652", "8103155", "2217482", "3630352", "8072259", "1085079", "0534104"}, new int[] {3, -8, 4, -6, -3, 4, 3, -3, -2}, new int[] {4, -6, -6, 1, -3, -2, 1, 5, -1}),54,7); break;
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
