import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class Graduation {
    class Course{
        char id;
        Collection<Integer> requiredBy;
        Course(char c){
            id=c;
            requiredBy=new HashSet<Integer>();
        }
        public String toString(){
            return String.valueOf(id)+requiredBy;
        }
    }
    List<Course> classes;
    boolean[] check2;
    public String moreClasses(String classesTaken, String[] requirements) {
        int[] check=new int[126-33+1];
        check2=new boolean[126-33+1];
        Arrays.fill(check,-1);
        int nclass=0;
        classes=new ArrayList<>();
        for(char c:classesTaken.toCharArray())
            if(check[c-33]<0){
                check[c-33]=nclass++;
                classes.add(new Course(c));
                check2[c-33]=true;
            }
        
        List<Course> class2=new ArrayList<>();
        for(int i=0; i<requirements.length; i++){
            for(char c:requirements[i].toCharArray()){
                if(c<'0' || c>'9'){
                    Course toadd=null;
                    if(check[c-33]<0){
                        check[c-33]=nclass++;
                        toadd=new Course(c);
                        class2.add(toadd);
                    }else{
                        if(check[c-33]>=classes.size())
                            toadd=class2.get(check[c-33]-classes.size());
                        else
                            toadd=classes.get(check[c-33]);
                    }
                    toadd.requiredBy.add(i);
                }
            }
        }
        Collections.sort(class2, new Comparator<Course>(){
            public int compare(Course a, Course b){
                return a.id-b.id;
            }
        });
        classes.addAll(class2);
        int N=classes.size()+requirements.length+2;
        int[][] map=new int[N][N];
        for(int i=0; i<classes.size(); i++){
            for(int j:classes.get(i).requiredBy)
                map[i][classes.size()+j]=1;
        }
        int expect=0;
        for(int i=0; i<requirements.length; i++){
            int j=0;
            String req=requirements[i];
            while(j<req.length() && Character.isDigit(req.charAt(j))) j++;
            int need=Integer.parseInt(req.substring(0,j));
            if(need>req.length()-j) return "0";
            expect+=need;
            map[classes.size()+i][N-1]=need;
        }
        Arrays.fill(map[N-2],0,classes.size()-class2.size(),1);
        int max=maxFlow(map,N-2,N-1);

        for(int i=0; i<classes.size(); i++){
            map[i][N-2]=0;
        }
        for(int i=0; i<requirements.length; i++){
            Arrays.fill(map[classes.size()+i],0,classes.size(),0);
        }
        Arrays.fill(map[N-2],classes.size()-class2.size(),classes.size(),1);
        Arrays.fill(map[N-1],0);

        //for(int[] x:map) out.println(Arrays.toString(x)); out.println();

        max+=maxFlow(map,N-2,N-1);
        //if(max<expect) return "0";
        out.println(classesTaken);
        StringBuilder ret=new StringBuilder();
        out.println(classes);
        for(int j=0; j<classes.size(); j++){
            if(map[N-2][j]==0 && !check2[classes.get(j).id-33])
                ret.append(classes.get(j).id);
            if(map[N-2][j]==0)
                out.print(classes.get(j).id);
        }
                out.println();

        return ret.toString();
    }
    private int maxFlow(int[][] map, int source, int sink){
        int ret=0;
        int[] prev=new int[map.length];
        while(true){
            boolean res=bfs(map,prev,source,sink);
            //out.format("ret=%d, res=%s\n", ret, res);
            //out.println(Arrays.toString(prev));
            if(res){
                int curr=sink;
                while(prev[curr]!=-1){
                    //out.println(curr);
                    int p=prev[curr];
                    map[p][curr]--;
                    map[curr][p]++;
                    curr=p;
                }
                ret++;
            }else{
                break;
            }
            for(int j=0; j<classes.size(); j++){
                if(map[map.length-2][j]==0)
                    out.print(classes.get(j).id);
            }
            out.println();
            //for(int[] x:map) out.println(Arrays.toString(x)); out.println();
            //out.format("ret=%d\n", ret);
        }
        return ret;
    }
    private boolean bfs(int[][] map, int[] prev, int source, int sink){
        Arrays.fill(prev, -1);
        Deque<Integer> q=new ArrayDeque<Integer>();
        boolean[] visited=new boolean[prev.length];
        q.offerLast(source);
        visited[source]=true;
        while(!q.isEmpty()){
            int curr=q.pollFirst();
            for(int j=0; j<map[curr].length; j++)
                if(!visited[j] && map[curr][j]>0){
                    visited[j]=true;
                    q.offerLast(j);
                    prev[j]=curr;
                    if(j==sink){
                        return true;
                    }
                }
        }
        return false;
    }


// BEGIN CUT HERE
java.io.PrintStream out=System.out;
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new Graduation().runTestCase(0);
		new Graduation().runTestCase(1);
		new Graduation().runTestCase(2);
		new Graduation().runTestCase(3);
		new Graduation().runTestCase(4);
		new Graduation().runTestCase(5);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(moreClasses("A", new String[] {"2ABC","2CDE"}), "BCD", 0); break;
			}
			case 1 : {
				checkOutput(moreClasses("+/NAMT", new String[] {"3NAMT","2+/","1M"}), "", 1); break;
			}
			case 2 : {
				checkOutput(moreClasses("A", new String[] {"100%*Klju"}), "0", 2); break;
			}
			case 3 : {
				checkOutput(moreClasses("", new String[] {"5ABCDE","1BCDE,"}), ",ABCDE", 3); break;
			}
			case 4 : {
				checkOutput(moreClasses("CDH", new String[] {"2AP", "3CDEF", "1CDEFH"}), "AEP", 4); break;
			}
                        case 5:{
                                   checkOutput(moreClasses("tSl#Uw-{pg/DWZ+NKr?Gf>RAOVYj^E,JP\"B",
                                              new String[] {"2L~Y`eH<GO}Xv", "4TDjeH$poumJ\".Xw_%OyQ{PM^g`GR]/UYN", "4/wK?.'J", "7|<OXRgZi;qw,tm$e\"AYlU[].E%@_u>{", "3|(,u\\v%'AcBY!s-fz`rq;ToO?bW", "6SV>QE.tw%]*D'", "3nB/a|v'}rSf\\cWAOX{^.&?\"HjV:[q>~", "7x|-][S=I+\\pQC'HuyAteq;c.<sd^oBM:k@fUlTi_\"Lh!N~J"}),
                        "!$",5);
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
