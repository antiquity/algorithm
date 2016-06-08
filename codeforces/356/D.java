import java.io.*;
import java.util.*;


public class D{
    static java.io.PrintStream out=System.out;
    long[] solve(long X){
        if(X==0) return new long[]{0,0};
        long[] res=map.get(X);
        if(res!=null) return res;
        long a=(long)Math.cbrt(X);
        long A=a*a*a;
        long[] f1=solve(X%A), f2=solve(A-1);
        if(f1[0]>=f2[0]-1){
            res=new long[]{f1[0]+X/A, f1[1]+(X/A)*A};
        }else
            res=new long[]{X/A-1+f2[0], (X/A-1)*A+f2[1]};
        map.put(X,res);
        return res;
    }
    HashMap<Long,long[]> map;
    public static void main(String[] argin) {
        D inst = new D();
        Scanner in = new Scanner(System.in);
        while(in.hasNextLong()){
            long m = in.nextLong();
            inst.map=new HashMap<>();
            long[] res=inst.solve(m);
            System.out.println(res[0]+" "+res[1]);
        }
    }
}

