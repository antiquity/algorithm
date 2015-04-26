import java.util.*;

public class TheTips {
    public double solve(String[] clues, int[] probability) {
        int N = clues.length;
        boolean[][] map = new boolean[N][N];
        boolean test=true;
        double[] prob = new double[N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++)
                map[i][j]=(clues[i].charAt(j)=='Y');
            map[i][i]=true;
            prob[i] = 1f-(double)probability[i]/100;
        }

        for(int k=0; k<N; k++) for(int i=0; i<N; i++) for(int j=0; j<N; j++)
            map[i][j]|=map[i][k]&map[k][j];

        double exp=0,pp;
        for(int i=0; i<N; i++){
            pp=1;
            for(int j=0; j<N; j++) if(map[j][i])
                pp*=prob[j];
            exp+= (1-pp);
        }
        return exp;
    }
    public double notgoodsolve(String[] clues, int[] probability) {
        int N = clues.length;
        boolean[][] map = new boolean[N][N];
        boolean test=true;
        for(int i=0; i<N; i++) for(int j=0; j<N; j++){
            map[i][j]=(clues[i].charAt(j)=='Y');
            if(i==j) map[i][j]=true;
        }

        for(int k=0; k<N-1 && test; k++){
            test=false;
            for(int i=0; i<N; i++) for(int j=0; j<N; j++) if(i!=j && map[i][j])
                for(int l=0; l<N; l++) if(!map[i][l] && map[j][l]){
                        test=true;
                        map[i][l] = true;
                }
        }

        List<Integer> cliques = new ArrayList<Integer>();
        List<Integer> num = new ArrayList<Integer>();
        List<Double> prob = new ArrayList<Double>();
        boolean[] visited = new boolean[N];
        Arrays.fill(visited,false);
        double pp=1;
        int cnt=0;
        for(int i=0; i<N; i++) if(!visited[i]){
            pp=1;
            cnt=0;
            for(int j=0; j<N; j++) if(map[i][j] && map[j][i] && !visited[j]){
                visited[j]=true;
                pp*=(1f-(double)probability[j]/100);
                cnt++;
            }
            prob.add(1-pp);
            cliques.add(i);
            num.add(cnt);
        }

        //for(int i=0; i<N; i++){
        //    for(int j=0; j<N; j++)
        //        print((map[i][j]?'T':'.'));
        //    println();
        //}
        //println(cliques); println(num); println(prob);

        Arrays.fill(visited,false);
        double exp=0;
        cnt=0;
        for(int i=0; i<cliques.size(); i++){
            pp=(1-prob.get(i));
            for(int j=0; j<cliques.size(); j++)
                if(i!=j && map[cliques.get(j)][cliques.get(i)])
                    pp*=(1-prob.get(j));
            exp+= (1-pp)*num.get(i);
        }
        return exp;
    }
// BEGIN CUT HERE
    void println(Object o) { System.out.println(o); }
    void print (Object o) {System.out.print(o); }
    void println() {System.out.println(); }
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new TheTips().runTestCase(0);
		new TheTips().runTestCase(1);
		new TheTips().runTestCase(2);
		new TheTips().runTestCase(3);
		new TheTips().runTestCase(4);
		new TheTips().runTestCase(5);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(solve(new String[] {"Y"}, new int[] {50}), 0.5, 0); break;
			}
			case 1 : {
				checkOutput(solve(new String[] {"YN","NY"}, new int[] {100,50}), 1.5, 1); break;
			}
			case 2 : {
				checkOutput(solve(new String[] {"YYY","NYY","NNY"}, new int[] {100,0,0}), 3.0, 2); break;
			}
			case 3 : {
				checkOutput(solve(new String[] {"NNN","NNN","NNN"}, new int[] {0,0,0}), 0.0, 3); break;
			}
			case 4 : {
				checkOutput(solve(new String[] {"NYYNYYNNNN","NNNNYNNNYN","YNNYYYYYNN","YYNYNNNNYN","NYNNNNNNNY","YNYYNNYNNY","NYNNYYYYYY","NYYYYNNNNN","YYNYNNYYYN","NNYYNYNYYY"} , new int[] {11,66,99,37,64,45,21,67,71,62} ), 9.999891558057332, 4); break;
			}
			case 5 : {
				checkOutput(solve(new String[] {"NNY","NNY","NNN"}, new int[] {50, 50, 1}), 1.7525, 5); break;
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
