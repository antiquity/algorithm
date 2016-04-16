import java.io.*;
import java.util.*;
public class D{
    private List<Long> solver(int K, int C, int S){
        List<Long> ret=new ArrayList<Long>();
        int i=0;
        while(i<K && ret.size()<S){
            long pos=0;
            int c=0;
            while(i<K && c<C){
                pos=pos*K+i;
                i++;
                c++;
            }
            ret.add(pos+1);
        }
        if(i<K) ret.clear();
        return ret;
    }
    public static void main(String[] argin) {
        D inst = new D();
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int ii=1; ii<=T; ii++){
            int K=in.nextInt(),
                C=in.nextInt(),
                S=in.nextInt();
            System.out.printf("Case #%d:", ii);
            //System.out.printf("%d %d %d:", K, C, S);
            List<Long> ret=inst.solver(K,C,S);
            for(long x:ret)
                System.out.format(" %d", x);
            if(ret.isEmpty())
                System.out.print(" IMPOSSIBLE");
            System.out.println();
        }
    }
}

