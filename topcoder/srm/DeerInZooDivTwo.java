// BEGIN CUT HERE

// END CUT HERE
import java.util.*;
public class DeerInZooDivTwo {
	public int[] getminmax(int N, int K) {
            int[] res=new int[2];
            if(K>=N) res[0]=0;
            else res[0]=N-K;
            res[1]=N-(K+1)/2;
            return res;
	}
}
