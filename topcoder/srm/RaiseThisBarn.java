// BEGIN CUT HERE

// END CUT HERE
import java.util.*;
public class RaiseThisBarn {
	public int calc(String str) {
            int nc=0;
            for(int i=0; i<str.length(); i++){
                if(str.charAt(i)=='c') nc++;
            }
            if(nc==0) return str.length()-1;
            if(nc%2==1) 
                return 0;
            int t=nc/2, i;
            for(i=0; t>0; i++){
                if(str.charAt(i)=='c') t--;
            }
            int res=1;
            while(str.charAt(i)=='.'){
                res++;
                i++;
            }
            return res;
	}
	public static void main(String[] args) {
		RaiseThisBarn temp = new RaiseThisBarn();
		System.out.println(temp.calc("c...c...c..c"));
	}
}
