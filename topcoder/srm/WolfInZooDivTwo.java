// BEGIN CUT HERE

// END CUT HERE
import java.util.*;
public class WolfInZooDivTwo {
	public int count(int N, String[] L, String[] R) {
            String ll=new String(L[0]);
            for(int j=1; j<L.length; j++)
                ll=ll.concat("" + L[j]);

            String rr=new String(R[0]);
            for(int j=1; j<R.length; j++)
                rr=rr.concat("" + R[j]);

            StringTokenizer st = new StringTokenizer(ll);
            int M=st.countTokens();
            int[] l=new int[M];
            int i=0;
            while(st.hasMoreTokens())
                l[i++]=Integer.parseInt(st.nextToken());

            st = new StringTokenizer(ll);
            if(st.countTokens() != M) System.out.println("error");
            int[] r=new int[M];
            int i=0;
            while(st.hasMoreTokens())
                r[i++]=Integer.parseInt(st.nextToken());

            while(1){

            }
        }
        public static void main(String[] args) {
            WolfInZooDivTwo temp = new WolfInZooDivTwo();
            System.out.println(temp.count(int N, String[] L, String[] R));
        }
}
