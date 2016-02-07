import java.util.*;

public class FleetFunding {
    class FF implements Comparable<FF>{
        int k,a,b;
        FF(int k, int a, int b){
            this.k=k;
            this.a=a;
            this.b=b;
        }
        public int compareTo(FF f){
            if(this.a!=f.a)
                return this.a-f.a;
            else return this.b-f.b;
        }
        public String toString(){
            return String.format("(%d, %d, %d)",k,a,b);
        }
    }
    List<List<Integer>> belongsTo;
    List<Integer> len;
    FF[] f;
    public int maxShips(int m, int[] k, int[] a, int[] b) {
        //println(Arrays.toString(k)); println(Arrays.toString(a)); println(Arrays.toString(b));
        int i,j;
        f = new FF[k.length];
        for(i=0; i<k.length; i++) f[i]=new FF(k[i],a[i],b[i]);
        Arrays.sort(f);
        for(i=0; i<k.length; i++) k[i]=f[i].k;

        //println(Arrays.toString(f));

        belongsTo = new ArrayList<List<Integer>>();
        len = new ArrayList<Integer>();

        j=0;
        int s=0,t;
        while(j<m){
            i=j+1;
            while(s<f.length){
                if(f[s].a<=i && f[s].b>=i)
                    break;
                s++;
            }
            if(s>=f.length) return 0;
            j=f[s].b;
            t=s+1;
            while(t<f.length){
                if(f[t].a>i){
                    j=Math.min(j,f[t].a-1);
                    break;
                }
                t++;
            }

            List<Integer> father = new ArrayList<Integer>();
            t=s;
            while(t<f.length && f[t].a<=i){
                if(f[t].b>=i){
                    father.add(t);
                    j=Math.min(j,f[t].b);
                }
                t++;
            }
            len.add(j-i+1);
            Collections.sort(father, new Comparator<Integer>(){
                public int compare(Integer sss, Integer ttt){
                    return f[sss].b-f[ttt].b;
                }
            });
            belongsTo.add(father);
        //    System.out.format("(%d,%d) : %s\n",i,j,father);
        }

        i=0; j=0;
        for(int kk:k) j+=kk;
        j/=m;
        while(i<j){
            //System.out.format("i=%d, j=%d\n", i,j);
            int ij=(i+j+1)/2;
            if(cando(ij,m,k))
                i=ij;
            else
                j=ij-1;
        }
        return i;
    }
    boolean cando(int ij, int m, int[] k){
        k = Arrays.copyOf(k,k.length);
        for(int i=0; i<belongsTo.size(); i++){
            int to=ij*len.get(i);
            for(int tt : belongsTo.get(i)){
                if(to<=0) break;
                if(k[tt]>=to){
                    k[tt]-=to;
                    to=0;
                }else{
                    to-=k[tt];
                    k[tt]=0;
                }
            }
            if(to>0) return false;
        }
        return true;
    }

// BEGIN CUT HERE
    void println(Object o) { System.out.println(o); }
    void print (Object o) {System.out.print(o); }
    void println() {System.out.println(); }
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
            for(int i=0; i<=5; i++)
		new FleetFunding().runTestCase(i);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(maxShips(3, new int[] {2,2,2}, new int[] {1,2,1}, new int[] {3,3,2}), 2, 0); break;
			}
			case 1 : {
				checkOutput(maxShips(1, new int[] {10,9,8,7,6,5,4,3,2,1}, new int[] {1,1,1,1,1,1,1,1,1,1}, new int[] {1,1,1,1,1,1,1,1,1,1}), 55, 1); break;
			}
			case 2 : {
				checkOutput(maxShips(10, new int[] {2,4,6,8,10,1,3,5,7,9}, new int[] {1,2,3,4,5,6,7,8,9,10}, new int[] {1,2,3,4,5,6,7,8,9,10}), 1, 2); break;
			}
			case 3 : {
		  		checkOutput(maxShips(84457, new int[] {374778,169109,529984,498638,29462,465769,29285,394948,307944,459189, 349316,766627,686851,404285,850199,359378,3014,248257,558856,27393,32370, 574862,337134,965567,32223,935678,389898,973497,990575,483190,122899, 857994,991217,149619,415870}, new int[] {92,4997,11,28545,933,210,2,124,114,4513,32959,1,57,17,13,133,1843,41851, 3,9296,9757,28,3,769,10,11102,683,6173,11821,3982,214,2,4304,439,1998}, new int[] {81034,54474,70239,79597,47317,82938,83883,74652,84033,84422,84456,84457, 81095,83743,79210,84255,84455,45596,84456,82959,67062,80330,44090,84445, 84454,84450,45931,77190,83025,83567,83639,84333,83355,70982,84456}), 186, 3); break;
			}
			case 4 : {
				checkOutput(maxShips(2, new int[] {1000000,1000000,1000000,1000000,1000000}, new int[] {1,1,1,2,2}, new int[] {1,1,2,2,2}), 2500000, 4); break;
			}
			case 5 : {
				checkOutput(maxShips(100000, new int[] {1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000, 1000000}, new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, new int[]{100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000, 100000}), 500,5);
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
