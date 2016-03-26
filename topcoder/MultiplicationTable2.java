import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class MultiplicationTable2 {
    int n;
    boolean[] v;
    public int minimalGoodSet(int[] table) {
        n = (int)Math.sqrt(table.length);
        List<Integer> group = new ArrayList<Integer>();
        v =new boolean[n];
        int ret = n;
        for(int i=0; i<n; i++){
            group.clear();
            Arrays.fill(v,false);
            if(doit(group,table,i,i))
                ret = Math.min(ret,group.size());
            //System.out.println(group);
        }
        return ret;
    }

    private boolean doit(List<Integer> group, int[] table, int i, int lowerbound){
        if(i<lowerbound) return false;
        v[i]=true;
        group.add(i);
        List<Integer> set = new ArrayList<Integer>(group);
        for(int x : set){
            int t;
            boolean test;
            if(!v[t=table[x*n+i]])
                if(!doit(group,table,t,lowerbound))
                    return false;
            if(!v[t=table[i*n+x]])
                if(!doit(group,table,t,lowerbound))
                    return false;
        }
        return true;
    }

// BEGIN CUT HERE
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new MultiplicationTable2().runTestCase(0);
		new MultiplicationTable2().runTestCase(1);
		new MultiplicationTable2().runTestCase(2);
		new MultiplicationTable2().runTestCase(3);
		new MultiplicationTable2().runTestCase(4);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(minimalGoodSet(new int[] {1,1,2,3,  1,0,3,2,  2,0,1,3,  1,2,3,0}), 2, 0); break;
			}
			case 1 : {
				checkOutput(minimalGoodSet(new int[] {0,1,2,3,  1,2,3,0,  2,3,0,1,  3,0,1,2}), 1, 1); break;
			}
			case 2 : {
				checkOutput(minimalGoodSet(new int[] {1,1,1,1,  2,2,2,2,  3,3,3,3,  0,0,0,0}), 4, 2); break;
			}
			case 3 : {
				checkOutput(minimalGoodSet(new int[] {0}), 1, 3); break;
			}
                        case 4 : {
                                checkOutput(minimalGoodSet(new int[]{41, 26, 16, 19, 42, 0, 11, 11, 2, 5, 32, 7, 5, 19, 20, 6, 29, 23, 32, 43, 18, 38, 4, 39, 23, 1, 10, 38, 4, 28, 11, 32, 33, 21, 18, 42, 22, 8, 2, 37, 12, 11, 11, 14, 26, 41, 24, 30, 12, 9, 9, 11, 4, 43, 44, 25, 27, 27, 0, 37, 42, 24, 35, 26, 22, 29, 25, 38, 20, 40, 12, 46, 7, 46, 33, 25, 21, 37, 48, 10, 40, 33, 44, 19, 42, 15, 34, 8, 28, 0, 49, 39, 8, 5, 43, 4, 29, 16, 17, 15, 17, 24, 42, 35, 18, 35, 19, 23, 42, 42, 33, 35, 7, 43, 20, 8, 19, 9, 3, 16, 21, 15, 34, 37, 23, 26, 18, 21, 38, 8, 17, 25, 34, 27, 14, 2, 16, 19, 28, 0, 23, 16, 12, 34, 15, 36, 18, 42, 46, 2, 42, 38, 6, 7, 21, 20, 2, 12, 35, 5, 41, 24, 19, 20, 39, 26, 16, 24, 5, 7, 21, 18, 33, 12, 40, 44, 30, 32, 38, 29, 42, 9, 48, 15, 49, 41, 39, 35, 2, 43, 24, 12, 7, 33, 8, 20, 37, 32, 39, 5, 40, 1, 1, 27, 27, 30, 36, 38, 47, 45, 28, 26, 15, 17, 15, 27, 34, 31, 34, 12, 40, 45, 28, 14, 28, 26, 5, 28, 37, 1, 0, 0, 10, 40, 20, 46, 31, 47, 30, 28, 39, 34, 9, 18, 45, 1, 16, 47, 40, 3, 15, 2, 9, 32, 4, 7, 12, 13, 15, 5, 23, 25, 39, 41, 35, 16, 16, 10, 32, 30, 13, 34, 4, 17, 9, 41, 31, 12, 19, 17, 29, 1, 29, 3, 22, 15, 24, 31, 5, 12, 21, 23, 5, 46, 26, 35, 23, 35, 15, 7, 43, 4, 41, 19, 49, 0, 35, 37, 48, 38, 19, 30, 16, 22, 11, 20, 28, 46, 9, 42, 27, 43, 0, 23, 2, 23, 45, 15, 7, 22, 28, 30, 18, 17, 7, 6, 35, 28, 3, 32, 48, 6, 35, 41, 2, 36, 41, 27, 27, 14, 43, 28, 41, 37, 44, 33, 38, 19, 35, 49, 14, 0, 7, 24, 13, 10, 6, 36, 21, 16, 23, 19, 29, 2, 10, 22, 29, 22, 23, 34, 31, 21, 1, 20, 16, 12, 38, 28, 37, 37, 8, 37, 35, 34, 27, 10, 10, 47, 43, 24, 21, 21, 0, 9, 7, 32, 47, 28, 17, 18, 33, 15, 41, 45, 21, 39, 24, 16, 46, 29, 24, 6, 34, 14, 2, 7, 48, 14, 18, 38, 25, 13, 37, 48, 49, 9, 15, 44, 35, 45, 16, 24, 11, 45, 44, 13, 31, 37, 17, 5, 40, 36, 16, 38, 18, 41, 39, 14, 6, 23, 26, 36, 28, 18, 28, 25, 26, 15, 31, 31, 39, 10, 10, 23, 48, 26, 29, 16, 14, 16, 11, 19, 11, 1, 10, 15, 49, 40, 48, 0, 4, 29, 44, 6, 16, 5, 22, 21, 44, 38, 8, 13, 16, 34, 23, 10, 26, 7, 14, 5, 3, 41, 19, 0, 47, 17, 38, 7, 0, 20, 8, 8, 37, 31, 15, 17, 30, 25, 47, 32, 25, 21, 23, 20, 29, 8, 8, 23, 1, 5, 10, 45, 27, 11, 31, 40, 48, 35, 38, 17, 36, 18, 20, 17, 15, 16, 35, 15, 22, 38, 11, 7, 39, 8, 39, 23, 6, 8, 45, 16, 27, 32, 45, 20, 21, 25, 4, 17, 8, 1, 33, 47, 11, 18, 13, 7, 33, 38, 39, 0, 23, 42, 20, 29, 28, 13, 11, 25, 22, 37, 16, 5, 12, 42, 23, 17, 19, 2, 41, 41, 45, 30, 23, 20, 21, 18, 16, 5, 41, 6, 40, 40, 46, 38, 40, 17, 45, 36, 23, 17, 47, 9, 17, 1, 39, 6, 48, 41, 41, 8, 15, 42, 7, 13, 23, 5, 3, 48, 29, 40, 20, 22, 36, 37, 30, 30, 30, 41, 31, 45, 26, 30, 32, 17, 0, 25, 21, 31, 37, 23, 27, 30, 14, 15, 11, 8, 0, 29, 29, 0, 6, 25, 11, 28, 44, 49, 27, 19, 9, 16, 27, 14, 5, 43, 34, 43, 42, 32, 17, 18, 8, 43, 8, 8, 48, 31, 49, 39, 28, 14, 26, 23, 3, 11, 26, 22, 35, 11, 13, 4, 45, 6, 17, 12, 47, 11, 36, 1, 5, 24, 22, 26, 48, 26, 21, 1, 49, 10, 14, 14, 14, 5, 23, 29, 20, 45, 35, 48, 6, 6, 35, 35, 44, 21, 7, 46, 13, 33, 2, 23, 31, 18, 37, 25, 47, 6, 20, 9, 13, 35, 23, 5, 5, 44, 13, 17, 34, 3, 17, 48, 19, 6, 8, 19, 36, 11, 36, 2, 27, 7, 8, 22, 15, 28, 30, 21, 41, 17, 21, 17, 41, 30, 7, 7, 27, 37, 16, 2, 21, 35, 49, 12, 38, 30, 39, 20, 2, 21, 37, 28, 20, 3, 21, 28, 2, 48, 33, 44, 28, 9, 3, 26, 25, 44, 32, 42, 48, 37, 38, 46, 36, 41, 42, 9, 28, 40, 0, 28, 21, 48, 18, 23, 37, 10, 10, 0, 14, 11, 4, 34, 14, 43, 3, 16, 19, 45, 43, 14, 14, 1, 43, 35, 5, 46, 17, 0, 45, 42, 4, 2, 30, 23, 13, 31, 13, 26, 24, 30, 31, 34, 22, 32, 4, 45, 41, 9, 13, 37, 49, 8, 10, 28, 24, 34, 29, 32, 3, 30, 14, 13, 17, 6, 40, 12, 33, 25, 15, 38, 34, 49, 22, 33, 12, 43, 3, 13, 44, 31, 43, 0, 11, 35, 15, 13, 15, 27, 34, 16, 37, 13, 25, 6, 48, 7, 35, 44, 14, 25, 14, 8, 24, 26, 2, 19, 21, 35, 42, 42, 48, 36, 29, 36, 41, 49, 18, 33, 3, 18, 43, 16, 7, 31, 39, 38, 27, 40, 42, 49, 7, 13, 31, 37, 13, 14, 10, 28, 36, 38, 2, 1, 5, 28, 35, 29, 39, 27, 24, 24, 2, 22, 37, 23, 40, 2, 19, 46, 3, 40, 40, 32, 14, 2, 30, 8, 15, 19, 48, 12, 44, 45, 17, 43, 49, 20, 47, 16, 9, 22, 24, 38, 23, 34, 40, 28, 8, 48, 31, 9, 18, 48, 2, 25, 45, 24, 25, 8, 25, 11, 25, 11, 27, 21, 12, 4, 33, 43, 41, 40, 24, 10, 8, 42, 17, 46, 44, 27, 27, 9, 29, 6, 4, 13, 46, 13, 41, 6, 21, 11, 49, 26, 7, 14, 46, 20, 23, 18, 20, 38, 45, 15, 20, 16, 22, 44, 12, 34, 13, 24, 49, 23, 4, 6, 20, 47, 23, 40, 39, 44, 10, 20, 12, 49, 5, 19, 2, 26, 47, 37, 48, 2, 38, 38, 21, 44, 5, 11, 14, 14, 46, 20, 21, 10, 6, 10, 42, 44, 19, 34, 24, 28, 6, 15, 22, 29, 34, 42, 9, 25, 8, 9, 28, 29, 6, 42, 6, 33, 16, 19, 43, 14, 28, 1, 6, 14, 5, 18, 2, 35, 42, 16, 27, 5, 9, 28, 47, 49, 11, 43, 2, 38, 34, 34, 11, 4, 0, 19, 32, 35, 6, 40, 45, 7, 28, 49, 49, 23, 34, 43, 10, 0, 47, 37, 46, 10, 41, 13, 3, 25, 45, 5, 44, 8, 15, 48, 45, 20, 28, 18, 24, 26, 39, 48, 12, 47, 29, 44, 17, 16, 14, 29, 49, 29, 9, 8, 47, 29, 31, 4, 41, 34, 28, 7, 10, 18, 11, 3, 7, 40, 42, 2, 37, 39, 27, 29, 25, 46, 36, 29, 41, 30, 49, 7, 22, 28, 25, 18, 22, 1, 21, 22, 10, 40, 16, 23, 36, 12, 49, 33, 31, 5, 22, 14, 20, 7, 21, 4, 17, 42, 29, 26, 13, 29, 31, 24, 21, 44, 19, 40, 20, 18, 31, 14, 23, 36, 36, 9, 17, 30, 46, 34, 27, 10, 6, 34, 2, 3, 19, 9, 2, 40, 33, 29, 23, 48, 20, 39, 46, 6, 14, 35, 45, 34, 36, 6, 46, 0, 12, 30, 31, 39, 12, 38, 34, 0, 4, 34, 47, 6, 47, 2, 24, 9, 19, 11, 43, 20, 22, 37, 36, 40, 11, 16, 5, 21, 16, 34, 48, 30, 49, 20, 5, 43, 16, 43, 49, 37, 25, 33, 28, 7, 11, 48, 11, 23, 16, 40, 16, 40, 38, 6, 13, 1, 6, 37, 38, 11, 32, 43, 29, 7, 16, 21, 42, 19, 26, 32, 23, 38, 21, 11, 14, 44, 6, 31, 43, 24, 28, 28, 42, 38, 20, 23, 30, 16, 11, 34, 36, 25, 2, 16, 6, 13, 27, 41, 44, 6, 47, 16, 28, 12, 30, 28, 16, 30, 2, 45, 25, 40, 48, 14, 9, 27, 5, 0, 21, 40, 38, 32, 36, 30, 22, 22, 16, 47, 10, 37, 41, 10, 1, 36, 33, 18, 2, 43, 17, 35, 31, 24, 11, 19, 48, 45, 41, 9, 29, 40, 2, 35, 24, 0, 16, 15, 18, 44, 8, 20, 1, 32, 3, 3, 24, 22, 5, 10, 18, 28, 32, 28, 22, 41, 20, 44, 0, 19, 37, 7, 7, 1, 35, 9, 25, 17, 16, 41, 17, 28, 30, 44, 7, 16, 13, 10, 19, 42, 49, 35, 38, 32, 0, 23, 29, 14, 31, 46, 21, 42, 40, 14, 31, 43, 42, 23, 46, 29, 1, 28, 39, 18, 34, 12, 20, 9, 18, 37, 13, 9, 18, 6, 10, 22, 33, 46, 15, 36, 26, 9, 13, 15, 33, 44, 3, 25, 12, 39, 33, 35, 48, 0, 9, 47, 1, 3, 7, 45, 9, 8, 27, 9, 42, 12, 20, 25, 7, 24, 30, 6, 43, 39, 6, 20, 1, 9, 23, 48, 25, 45, 47, 21, 19, 28, 34, 3, 13, 14, 21, 32, 7, 21, 4, 1, 39, 43, 26, 36, 43, 29, 0, 34, 5, 40, 8, 24, 15, 5, 14, 27, 21, 4, 33, 45, 25, 18, 11, 16, 43, 24, 39, 49, 39, 43, 19, 44, 47, 25, 4, 37, 41, 5, 14, 8, 14, 31, 8, 3, 26, 14, 42, 17, 9, 40, 22, 42, 2, 2, 43, 45, 6, 30, 22, 46, 33, 10, 2, 31, 1, 17, 21, 44, 24, 44, 22, 41, 34, 21, 27, 19, 33, 17, 3, 2, 19, 1, 36, 0, 8, 48, 36, 43, 41, 29, 31, 40, 2, 11, 8, 32, 45, 32, 37, 29, 49, 42, 23, 10, 32, 8, 46, 3, 40, 22, 5, 1, 35, 46, 42, 28, 36, 37, 29, 26, 8, 45, 6, 37, 21, 40, 6, 38, 26, 42, 13, 2, 37, 27, 19, 34, 12, 5, 0, 2, 38, 7, 13, 19, 8, 16, 47, 38, 28, 9, 12, 26, 42, 27, 25, 28, 8, 16, 2, 25, 4, 35, 3, 12, 3, 10, 17, 49, 40, 36, 27, 48, 27, 43, 20, 3, 1, 14, 44, 12, 48, 37, 41, 47, 11, 16, 41, 18, 47, 26, 26, 49, 47, 6, 25, 8, 18, 27, 45, 20, 7, 21, 49, 43, 18, 47, 46, 27, 24, 23, 38, 49, 41, 5, 16, 11, 33, 22, 13, 16, 48, 47, 3, 42, 31, 6, 3, 19, 29, 28, 34, 20, 12, 14, 36, 37, 7, 49, 23, 37, 4, 27, 18, 28, 26, 37, 22, 14, 35, 46, 2, 23, 25, 22, 9, 7, 35, 35, 19, 11, 18, 3, 16, 23, 4, 32, 20, 11, 5, 41, 10, 40, 23, 41, 30, 32, 28, 42, 39, 4, 6, 39, 16, 2, 47, 1, 37, 34, 44, 38, 46, 31, 7, 23, 38, 22, 49, 18, 42, 40, 16, 23, 8, 49, 6, 37, 18, 28, 2, 48, 9, 42, 6, 42, 1, 30, 32, 49, 11, 19, 16, 2, 15, 41, 5, 30, 0, 43, 42, 18, 42, 12, 45, 40, 14, 49, 6, 9, 34, 0, 45, 15, 31, 2, 32, 40, 34, 39, 30, 4, 13, 0, 1, 32, 46, 0, 13, 24, 22, 31, 38, 36, 44, 36, 21, 14, 11, 15, 16, 26, 2, 19, 44, 36, 32, 7, 37, 6, 15, 24, 46, 33, 2, 2, 30, 46, 27, 23, 39, 22, 38, 38, 28, 6, 40, 31, 37, 42, 21, 17, 17, 13, 33, 30, 21, 7, 16, 49, 26, 21, 45, 42, 10, 33, 28, 16, 36, 25, 0, 35, 38, 33, 19, 3, 29, 47, 35, 16, 0, 3, 10, 48, 38, 7, 36, 5, 16, 2, 45, 28, 13, 18, 40, 3, 23, 20, 41, 1, 16, 6, 2, 31, 6, 20, 18, 35, 15, 3, 19, 45, 13, 12, 38, 18, 25, 5, 22, 27, 15, 35, 41, 12, 38, 35, 32, 37, 37, 2, 36, 4, 29, 40, 38, 1, 46, 17, 35, 5, 35, 7, 31, 8, 47, 16, 4, 23, 38, 19, 3, 24, 28, 27, 33, 13, 35, 38, 48, 16, 35, 26, 34, 19, 35, 6, 45, 19, 45, 20, 41, 49, 13, 49, 2, 9, 0, 17, 38, 35, 16, 34, 10, 2, 18, 29, 47, 11, 26, 10, 18, 44, 28, 23, 31, 41, 10, 7, 34, 19, 27, 0, 45, 12, 20, 21, 22, 1, 19, 10, 29, 23, 3, 3, 21, 36, 15, 30, 32, 33, 5, 8, 15, 29, 46, 47, 2, 36, 26, 15, 36, 35, 39, 30, 28, 10, 39, 23, 26, 3, 46, 47, 16, 24, 1, 4, 18, 0, 2, 49, 43, 2, 1, 36, 26, 13, 7, 41, 12, 5, 3, 28, 43, 48, 42, 21, 35, 15, 18, 8, 40, 16, 16, 5, 36, 17, 39, 29, 21, 25, 7, 22, 24, 13, 39, 44, 43, 26, 37, 49, 18, 2, 18, 23, 33, 20, 49, 15, 11, 6, 30, 9, 1, 7, 7, 42, 46, 38, 49, 21, 13, 34, 21, 45, 22, 17, 26, 38, 38, 38, 16, 36, 5, 36, 11, 31, 35, 40, 48, 27, 26, 24, 0, 38, 20, 48, 2, 40, 23, 34, 6, 3, 49, 31, 5, 37, 0, 12, 28, 40, 15, 32, 40, 39, 36, 22, 11, 20, 37, 18, 45, 5, 0, 47, 18, 13, 5, 13, 29, 18, 22, 37, 4, 7, 8, 22, 26, 3, 21, 23, 12, 11, 6, 3, 31, 33, 25, 44, 16, 8, 20, 5, 1, 37, 44, 31, 9, 48, 0, 44, 2, 37, 4, 11, 4, 42, 22, 4, 39, 17, 34, 20, 23, 8, 44, 26, 9, 41, 17, 6, 9, 23, 35, 21, 44, 14, 12, 4, 26, 18, 49, 43, 45, 48, 40, 18, 5, 18, 10, 0, 8, 0, 33, 13, 39, 45, 3, 9, 11, 32, 49, 44, 21, 9, 6, 4, 22, 28, 32, 5, 38, 3, 41, 3, 30, 49, 48, 1, 32, 32, 25, 15, 48, 17, 4, 26, 20, 39, 8, 39, 44, 37, 18, 40, 30, 20, 42, 9, 35, 10, 15, 22, 21, 31, 26, 10, 41, 45, 43, 44, 5, 24, 9, 24, 7, 10, 39, 18, 2, 40, 23, 42, 7, 41, 26, 4, 2, 35, 5, 15, 47, 10}),
                                        14,4);
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
