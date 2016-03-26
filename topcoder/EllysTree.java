import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class EllysTree {
    class TreeNode implements Comparable{
        int id;
        int needs;
        int level;
        TreeSet<TreeNode> children;
        TreeNode parent;
        TreeNode(int id){
            this.id= id;
            children = new TreeSet<TreeNode>();
        }
        void setLevel(int l){
            level=l;
            for(TreeNode child : children)
                child.setLevel(l+1);
        }
        void sort(){
            for(TreeNode child : children){
                child.sort();
            }
        }
        public int compareTo(Object o){
            TreeNode c = (TreeNode) o;
            return id-c.id;
        }
        public boolean equals(Object o){
            return id==((TreeNode)o).id;
        }
        boolean getNeeds(){
            needs=0;
            for(TreeNode child : children){
                if(!child.getNeeds())
                    return false;
                needs+=Math.max(1,(child.needs-1));
            }
            needs=Math.max(needs,1);
            if(needs>level)
                return false;
            else
                return true;
        }
        boolean canRemove(){
            //System.out.println("testing "+id);
            remove();
            TreeNode r = this;
            while(r.parent!=null) r=r.parent;
            boolean ret = r.getNeeds();
            //System.out.println(r);
            if(!ret) reinstall();
            //System.out.println(r);
            return ret;
        }
        void remove(){
            parent.children.remove(this);
            parent.children.addAll(children);
            for(TreeNode child : children){
                child.parent = parent;
                child.setLevel(level);
            }
        }
        void reinstall(){
            parent.children.removeAll(children);
            parent.children.add(this);
            for(TreeNode child : children){
                child.parent = this;
                child.setLevel(level+1);
            }
        }
        public String toString(){
            Deque<TreeNode> q = new ArrayDeque<>();
            StringBuilder ret = new StringBuilder();
            for(TreeNode child : children)
                ret.append(child.toString()+" ");
            ret.append(String.format("(%d,%d)", id, needs));
            return ret.toString();
        }
    }
    public int[] getMoves(int[] parent) {
        int N = parent.length;
        TreeNode[] nodes = new TreeNode[N+1];
        for(int i=0; i<=N; i++)
            nodes[i]=new TreeNode(i);
        for(int i=0; i<parent.length; i++){
            nodes[i+1].parent = nodes[parent[i]];
            nodes[parent[i]].children.add(nodes[i+1]);
        }
        TreeNode root = nodes[0];
        root.sort();
        root.setLevel(1);
        if(!root.getNeeds())
            return new int[0];
        int[] ret = new int[N];
        boolean[] visited = new boolean[N+1];
        int pos=0, c=1;
        //System.out.println(root);
        while(pos>=0){
            if(pos==N) break;
            while(c<=N && (visited[c] || (pos>0 && !connected(parent, c,ret[pos-1])) || !nodes[c].canRemove())) c++;
            if(c>N){
                if(pos==0) break;
                c = ret[--pos];
                visited[c]=false;
                nodes[c].reinstall();
                c++;
            }else{
                ret[pos]=c;
                visited[c]=true;
                pos++;
                c=1;
            }
        }
        return ret;
    }
    
    private boolean connected(int[] parent, int a, int b){
        int c=a;
        while(c!=0){
            c=parent[c-1];
            if(c==b)
                return true;
        }
        c=b;
        while(c!=0){
            c=parent[c-1];
            if(c==a)
                return true;
        }
        return false;
    }


// BEGIN CUT HERE
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new EllysTree().runTestCase(0);
		new EllysTree().runTestCase(1);
		new EllysTree().runTestCase(2);
		new EllysTree().runTestCase(3);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(getMoves(new int[] {9, 13, 7, 9, 8, 14, 14, 0, 6, 9, 2, 2, 5, 5, 7}), new int[] {1, 5, 2, 11, 13, 12, 8, 3, 7, 15, 14, 4, 6, 9, 10 }, 0); break;
			}
			case 1 : {
				checkOutput(getMoves(new int[] {3, 4, 5, 0, 2}), new int[] {1, 2, 3, 4, 5 }, 1); break;
			}
			case 2 : {
				checkOutput(getMoves(new int[] {0, 0}), new int[] { }, 2); break;
			}
			case 3 : {
				checkOutput(getMoves(new int[] {0, 6, 6, 2, 6, 1, 3, 5}), new int[] {2, 4, 1, 3, 7, 6, 5, 8 }, 3); break;
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
