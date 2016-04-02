import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class SegmentsAndPoints {
    class Pair{
        int l, r;
        Pair(int l, int r){
            this.l=l; this.r=r;
        }
        public String toString(){
            return String.format("(%d, %d)",l,r);
        }
    }
    public String isPossible(int[] p, int[] l, int[] r) {
        int n=p.length;
        Pair[] pair=new Pair[n];
        for(int i=0; i<n; i++){
            pair[i]=new Pair(l[i], r[i]);
        }
        Arrays.sort(p);
        Arrays.sort(pair, new Comparator<Pair>(){
            public int compare(Pair a, Pair b){
                if(a.r==b.r)
                    return a.l-b.l;
                else
                    return a.r-b.r;
            }
        });
        //System.out.println(Arrays.toString(p));
        //System.out.println(Arrays.toString(pair));

        boolean[] visited=new boolean[n];
        for(int pp:p){
            int j=0;
            while(j<n && (visited[j] || pp<pair[j].l || pp>pair[j].r))
                j++;
            if(j==n){
                return "Impossible";
            }else{
                visited[j]=true;
            }
        }
        return "Possible";
    }
// BEGIN CUT HERE
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new SegmentsAndPoints().runTestCase(0);
		new SegmentsAndPoints().runTestCase(1);
		new SegmentsAndPoints().runTestCase(2);
		new SegmentsAndPoints().runTestCase(3);
		new SegmentsAndPoints().runTestCase(4);
		new SegmentsAndPoints().runTestCase(5);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(isPossible(new int[] {1, 2}, new int[] {0, 0}, new int[] {1, 3}), "Possible", 0); break;
			}
			case 1 : {
				checkOutput(isPossible(new int[] {0}, new int[] {2}, new int[] {3}), "Impossible", 1); break;
			}
			case 2 : {
				checkOutput(isPossible(new int[] {0, 1, 2}, new int[] {0, 0, 1}, new int[] {1, 2, 1}), "Possible", 2); break;
			}
			case 3 : {
				checkOutput(isPossible(new int[] {0, 1}, new int[] {-1, 0}, new int[] {0, 0}), "Impossible", 3); break;
			}
			case 4 : {
				checkOutput(isPossible(new int[] {434, 63, 241, 418, -380, -46, 397, -205, -262, -282, 260, -106, -389, -286, 422, -75, 127, 382, 52, -383}, new int[] {-447, -226, -411, 287, -83, -228, -390, 358, 422, 395, -461, -112, 49, 75, -160, -152, 372, -447, -337, -362}, new int[] {-102, 348, -70, 466, 168, -61, -389, 469, 433, 471, -75, -41, 52, 236, 299, -48, 383, -353, 346, -217}), "Possible", 4); break;
			}

                        case 5: {
                                    checkOutput(isPossible(
                                                new int[]{163, -229, -158, 494, -24, 232, 445, 471, 58, 217, 192, 294, -272, 40, -91, 217, 417, -69, -81, -116, -27, 174, 114, 382, 137, 115, -39, -493, -338, -103, -323, 186, -476, -226, -36, 314, -397, 95, -238, 212, -225, 106, 96, 353, -473, -454, 197, -292, -380, 148, -273, 154, -395, 395, 281, -170, -143, -306, -247, -3, -234, 354, 305, -405, 6, 345, 341, -251, -76, 290, 78, -390, 315, 434, 0, -327, -245, -319, -462, 407, -248, 380, 451, 383, 182, 166, -306, -83, 136, 329, -79, 108, 78, 183, -24, 186, -409, -304, 113, 90},
                                                new int[]{-372, 295, -43, 286, 402, 455, -219, 180, -52, -485, -189, -29, -182, 6, 101, 192, -424, 403, -497, 196, 245, 235, 29, 399, 203, -327, 181, 147, -391, -472, 494, -276, -129, -412, -186, -90, -271, -280, 467, -93, -401, -380, -47, -171, -387, -62, 459, -300, -48, 157, 213, -294, -332, 392, 18, -335, -342, 120, -399, -109, -478, 293, 427, 70, 213, 285, 401, -261, 327, -419, 291, 235, -235, 485, 458, -462, -382, -371, 147, 392, -202, 185, 436, -277, -273, 429, -187, 55, -194, 57, 438, 428, 418, -199, -495, 179, 416, -335, 446, 467},
                                                new int[]{154, 437, 327, 343, 479, 468, 68, 392, -14, -9, 361, 288, 388, 39, 449, 202, 170, 445, -241, 394, 255, 269, 89, 483, 347, 116, 222, 241, 298, -82, 495, 358, -101, 499, -165, -37, 236, -158, 484, 181, -385, 89, -28, 314, 66, 353, 483, -285, 390, 494, 312, 430, -240, 499, 235, -46, -185, 380, -73, 223, 261, 353, 468, 241, 366, 487, 419, 141, 411, 453, 326, 257, -162, 485, 484, 284, -148, 210, 456, 393, 356, 235, 487, -253, 147, 450, 360, 253, -137, 297, 445, 456, 420, -81, -109, 186, 445, -90, 462, 492}
                                                ),"Impossible",5);
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
