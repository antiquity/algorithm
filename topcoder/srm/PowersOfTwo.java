import java.util.*;

public class PowersOfTwo {
    int[] table = new int[52];
    public long count_1(long[] powers) {
        ArrayList<Long> sPoswers = new ArrayList<Long>(powers.length);
        for (long p : powers) sPoswers.add(p);
        Collections.sort(sPoswers);
        long max = 0;
        HashSet<Long> current = new HashSet<Long>();
        long total = 1;
        current.add(0l);
        Long last = null;
        for (Long p : sPoswers) {
            if (p != last && last != null && p > max) {
                total *= current.size();
                current = new HashSet<Long>();
                current.add(0l);
            }

            HashSet<Long> newVals = new HashSet<Long>();
            for (Long val : current)
                newVals.add(val + p);
            current.addAll(newVals);

            max += p;
            last = p;
        }
        total *= (current.size() != 0 ? current.size() : 1);
        return total;
    }

    public long count(long[] powers) {
        //println("powers: " + Arrays.toString(powers));
        Arrays.fill(table,0);
        int d=0;
        long max=0;
        for(int i=0; i<powers.length; i++){
            d=0;
            while(powers[i]!=1){
                powers[i]/=2;
                d++;
            }
            table[d]++;
        }
        //println("table:  " + Arrays.toString(Arrays.copyOf(table,d+1)));
        int i=0,j;
        long ans=1;
        HashSet<Long> set = new HashSet<Long>();
        while(i<table.length){
            max=table[i]; j=i;
            set.clear(); set.add(0l);
            while((1l<<(j-i))<=max && j<table.length){
                //System.out.format("(%d,%d): ",i,j);
                //System.out.print(set);
                HashSet<Long> temp=new HashSet<Long>();
                for(long l:set)
                    for(int k=1; k<=table[j]; k++)
                        temp.add(l+k*(1l<<(j-i)));
                set.addAll(temp);
                //System.out.println(" -> " + set);
                if(j>i) max=max+table[j]*(1l<<(j-i));
                j++;
            }
            if(table[i]==0) j++;
            ans*=set.size();
            i=j;
        }
        return ans;
    }

// BEGIN CUT HERE
    void println(Object o) { System.out.println(o); }
    void print (Object o) {System.out.print(o); }
    void println() {System.out.println(); }
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new PowersOfTwo().runTestCase(4);
		//System.out.println(new PowersOfTwo().count_1(new long[]{1,1,1,1,1,2,2,2,2,2}));
		//System.out.println(new PowersOfTwo().count(new long[]{1,1,1,1,1,2,2,2,2,2}));
		//new PowersOfTwo().runTestCase(0);
		//new PowersOfTwo().runTestCase(1);
		//new PowersOfTwo().runTestCase(2);
		//new PowersOfTwo().runTestCase(3);
		//new PowersOfTwo().runTestCase(6);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(count(new long[] {1,2}), 4, 0); break;
			}
			case 1 : {
				checkOutput(count(new long[] {1,1,1,1}), 5, 1); break;
			}
			case 2 : {
				checkOutput(count(new long[] {1,2,2,2,4,4,16}), 32, 2); break;
			}
			case 3 : {
				checkOutput(count(new long[] {1,32,1,16,32}), 18, 3); break;
			}
			case 4 : {
                                     long[] ttt={2l, 4l, 8l, 16l, 32l, 64l, 128l, 256l, 512l, 1024l, 2048l, 4096l, 8192l, 16384l, 32768l, 65536l, 131072l, 262144l, 524288l, 1048576l, 2097152l, 4194304l, 8388608l, 16777216l, 33554432l, 67108864l, 134217728l, 268435456l, 536870912l, 1073741824l, 2147483648l, 4294967296l, 8589934592l, 17179869184l, 34359738368l, 68719476736l, 137438953472l, 274877906944l, 549755813888l, 1099511627776l, 2199023255552l, 4398046511104l, 8796093022208l, 17592186044416l, 35184372088832l, 70368744177664l, 140737488355328l, 281474976710656l, 562949953421312l, 1125899906842624l};
				checkOutput(count(ttt), 1125899906842624l, 4); break;
			}
                        case 6: {
                                    long[] ttt={2l, 8l, 32l, 16384l, 262144l, 262144l, 524288l, 1048576l, 2097152l, 2097152l, 2097152l, 4194304l, 4194304l, 8388608l, 536870912l, 2147483648l, 2147483648l, 2147483648l, 2147483648l, 2147483648l, 4294967296l, 4294967296l, 4294967296l, 4294967296l, 4294967296l, 274877906944l, 274877906944l, 8796093022208l, 17592186044416l, 17592186044416l, 17592186044416l, 35184372088832l, 35184372088832l, 281474976710656l, 562949953421312l, 562949953421312l, 562949953421312l};
                                    checkOutput(count_1(ttt), 19070976, 5);
                                    checkOutput(count(ttt), 19070976, 5);
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
