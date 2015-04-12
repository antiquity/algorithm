// BEGIN CUT HERE

// END CUT HERE
import java.util.*;
public class FoxAndSightseeing {
	public int getMin(int[] position) {
            int[] dif = new int[position.length];
            int max=0;
            int reduced=Integer.MIN_VALUE;
            int temp;
            for(int i=1; i< position.length; i++){
                dif[i] = Math.abs(position[i]-position[i-1]);
                max += dif[i];
                if(i>1){
                    temp=dif[i]+dif[i-1]-Math.abs(position[i]-position[i-2]);
                    if(temp>reduced) reduced=temp;
                }
            }
            return max-reduced;
	}
	public static void main(String[] args) {
		FoxAndSightseeing temp = new FoxAndSightseeing();
                //System.out.println(temp.getMin(new {-2, 4, 3}));
	}
}
