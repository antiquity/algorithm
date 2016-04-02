import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class Parking {
    class Car{
        int r, c;
        int[] dist;
        Car(int a, int b){
            r=a;
            c=b;
        }
    }
    public int minTime(String[] park) {
        int r=park.length, c=park[0].length();
        char[][] map=new char[r][c];
        for(int i=0; i<r; i++){
            map[i]=park[i].toCharArray();
            //System.out.println(park[i]);
        }
        int ncar=0, nspot=0;
        List<Car> car=new ArrayList<>();
        List<Integer> spot=new ArrayList<>();
        Map<Integer,Integer> spotMap=new HashMap<>();
        for(int i=0; i<r; i++)
            for(int j=0; j<c; j++){
                if(map[i][j]=='C')
                    car.add(new Car(i,j));
                else if(map[i][j]=='P'){
                    spot.add(i*c+j);
                    spotMap.put(i*c+j,nspot++);
                }
            }
        ncar=car.size();
        for(Car x:car){
            x.dist=new int[nspot];
            bfs(x,spotMap,map);
        }
        //out.format("ncar=%d, nspot=%d\n", ncar, nspot);

        if(ncar>nspot) return -1;

        int N=ncar+nspot+2;
        int[][] distMap=new int[N][N];
        int l=0, h=0;
        for(int i=0; i<ncar; i++)
            for(int j=0; j<nspot; j++){
                distMap[i][j+ncar]=car.get(i).dist[j];
                h=Math.max(h,distMap[i][j+ncar]);
            }
        for(int i=ncar; i<ncar+nspot; i++)
            distMap[i][N-1]=1;
        for(int i=0; i<ncar; i++)
            distMap[N-2][i]=1;
        //for(int[] xx:distMap) System.out.println(Arrays.toString(xx)); System.out.println();

        if(maxFlow(h,distMap,N-2,N-1)<ncar){
            return -1;
        }
        while(l<h){
            int D=(l+h)/2;
            int res=maxFlow(D,distMap,N-2,N-1);
            //System.out.format("l=%d, h=%d, D=%d, res=%s\n", l,h,D, res);
            if(res==ncar){
                h=D;
            }else
                l=D+1;
        }
        return l;
    }
    private boolean toParkBFS(int D, int[][] map, int[] prev, int source, int sink){
        boolean[] visited=new boolean[map.length];
        visited[source]=true;
        Deque<Integer> q=new ArrayDeque<>();
        q.offerLast(source);
        Arrays.fill(prev,-1);
        while(!q.isEmpty()){
            int cur=q.pollFirst();
            for(int j=0; j<map[cur].length; j++){
                if(map[cur][j]>0 && map[cur][j]<=D && !visited[j]){
                    visited[j]=true;
                    prev[j]=cur;
                    q.offerLast(j);
                    if(j==sink) return true;
                }
            }
        }
        return false;
    }
    private int maxFlow(int D, int[][] map, int source, int sink){
        int[][] distMap=new int[map.length][];
        for(int i=0; i<map.length; i++)
            distMap[i]=map[i].clone();
        int ret=0;
        int[] prev=new int[distMap.length];
        while(true){
            boolean res=toParkBFS(D, distMap,prev,source,sink);
            //System.out.format("D=%d, res=%s, ret=%d\n", D, res, ret);
            if(res){
                int where=sink;
                while(prev[where]!=-1){
                    int pre=prev[where];
                    distMap[where][pre]=distMap[pre][where];
                    distMap[pre][where]=0;
                    where=pre;
                }
                ret++;
            }else
                break;

        }
        return ret;
    }

    class Unit{
        int x, y, dist;
        Unit(int a, int b, int d){ x=a; y=b; dist=d; };
    }
    int[][] d=new int[][]{{1,0},{-1,0},{0,-1},{0,1}};
    private void bfs(Car car, Map<Integer, Integer> spotMap, char[][] map){
        int r=map.length, c=map[0].length;
        boolean[][] visited=new boolean[r][c];
        visited[car.r][car.c]=true;
        Deque<Unit> q=new ArrayDeque<>();
        q.offerLast(new Unit(car.r,car.c,0));
        while(!q.isEmpty()){
            Unit pos=q.pollFirst();
            if(map[pos.x][pos.y]=='P'){
                car.dist[spotMap.get(pos.x*c+pos.y)]=pos.dist;
            }
            for(int i=0; i<4; i++){
                int ii=pos.x+d[i][0], jj=pos.y+d[i][1];
                if(ii>=0 && ii<r && jj>=0 && jj<c && !visited[ii][jj] && map[ii][jj]!='X'){
                    visited[ii][jj]=true;
                    q.offerLast(new Unit(ii,jj,pos.dist+1));
                }
            }
        }
    }
// BEGIN CUT HERE
/** begin cut - don't modify this line*/
    java.io.PrintStream out=System.out;
	public static void main(String[] a) {
  		new Parking().runTestCase(0);
        	new Parking().runTestCase(2);
  		new Parking().runTestCase(3);
  		new Parking().runTestCase(4);
  		new Parking().runTestCase(5);
  		new Parking().runTestCase(1);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(minTime(new String[] {"C.....P",  "C.....P",  "C.....P"}), 6, 0); break;
			}
			case 1 : {
				checkOutput(minTime(new String[] {"C.X.....",  "..X..X..",  "..X..X..",  ".....X.P"}), 16, 1); break;
			}
			case 2 : {
				checkOutput(minTime(new String[] {"XXXXXXXXXXX",  "X......XPPX",  "XC...P.XPPX",  "X......X..X",  "X....C....X",  "XXXXXXXXXXX"} ), 5, 2); break;
			}
			case 3 : {
				checkOutput(minTime(new String[] {".C.",  "...",  "C.C",  "X.X",  "PPP"}), 4, 3); break;
			}
			case 4 : {
				checkOutput(minTime(new String[] {"CCCCC",  ".....",  "PXPXP"} ), -1, 4); break;
			}
			case 5 : {
				checkOutput(minTime(new String[] {"..X..",  "C.X.P",  "..X.."}), -1, 5); break;
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
