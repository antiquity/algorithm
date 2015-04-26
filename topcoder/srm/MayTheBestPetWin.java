// BEGIN CUT HERE

// END CUT HERE
import java.util.*;
public class MayTheBestPetWin {
    public int calc(int[] A, int[] B) {
        int N = A.length;
        boolean[] w=new boolean[N];
        long t;
        long Smax,Smin,Tmax,Tmin;
        long d1,d2,newDif,res=Long.MAX_VALUE;
        //System.out.println(N+"  "+res);
        for(t=1; t<(((long)1)<<N-1); t++){
            System.out.println(Long.toBinaryString(t));

            for (int i = N-1; i >= 0; i--) {
                w[i] = (t & (1 << i)) != 0;
            }
            Smin=0; Smax=0; Tmin=0; Tmax=0;
            d1=0; d2=0;
            for(int i=0; i<N; i++){
                if(w[i]){
                    d1-=A[i]; d2+=B[i];
                    //Smin+=A[i]; Smax+=B[i];
                }else{
                    d1+=B[i]; d2-=A[i];
                    //Tmin+=A[i]; Tmax+=B[i];
                }
            }
            //d1=Tmax-Smin; d2=Smax-Tmin;
            newDif=((d1>d2) ? d1 : d2);
            //System.out.println(d1 + " " + d2+ " " + newDif);
            if(newDif<res) res=newDif;
        }
        System.out.println(res);

        return (int)res;
    }
    public static void main(String[] args) {
        MayTheBestPetWin temp = new MayTheBestPetWin();
        int[] A={2907,949,1674,6092,8608,5186,2630,970,1050,2415,1923,2697,5571,6941,8065,4710,716,756,5185,1341,993,5092,248,1895,4223,1783,3844,3531,2431,1755,2837,4015};
        int[] B= {7296,6954,4407,9724,8645,8065,9323,8433,1352,9618,6487,7309,9297,8999,9960,5653,4721,7623,6017,7320,3513,6642,6359,3145,7233,5077,6457,3605,2911,4679,5381,6574};

        System.out.println(temp.calc(A,B));
    }
}
