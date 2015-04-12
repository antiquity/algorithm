// BEGIN CUT HERE

// END CUT HERE
import java.util.*;
public class SparseFactorialDiv2 {
	public long getCount(long lo, long hi, long divisor) {
            long count =0;
            for(long i=lo; i<=hi; i++){
                if(dividable(i,divisor)){
                    System.out.println(i);
                    count ++;
                }
            }
            return count;
	}

        public boolean dividable(long n, long dividable){
            long K = (long)(Math.sqrt(n-1));
            for(long k=0; k<=K; k++)
                if((n-k*k)%dividable==0) return true;
            return false;
        }
		
	public static void main(String[] args) {
		SparseFactorialDiv2 temp = new SparseFactorialDiv2();
		System.out.println(temp.getCount(4, 8, 3));
	}
}
