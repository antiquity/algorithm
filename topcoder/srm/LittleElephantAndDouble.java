// BEGIN CUT HERE

// END CUT HERE
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;
import java.util.*;

public class LittleElephantAndDouble {
	public String getAnswer(int[] A) {
            int max=A[0];
            for(int i=1; i<A.length; i++)
                if(A[i]>max) max=A[i];
            int tt;
            for(int i=0; i<A.length; i++){
                if(max%A[i]==0){
                    tt=max/A[i];
                    while(tt%2==0)
                        tt=tt/2;
                    if(tt!=1) return "NO";
                }else
                    return "NO";
            }
            return "YES";
	}
	public static void main(String[] args) {
		LittleElephantAndDouble temp = new LittleElephantAndDouble();
		//System.out.println(temp.getAnswer(int[] A));
	}
}
