import java.io.*;
import java.util.*;


public class C{
    long solver(long C, int D, long V, int[] de){
        Arrays.sort(de);
        long value=0;
        int i=0;
        long ret=0;

        //System.out.println(Arrays.toString(de));

        while (value<V){
            if(i<D && value+1>=de[i]){
                //System.out.format("value=%d -> %d, i=%d, de[i]=%d, D=%d, V=%d\n",value,value+C*de[i],i,de[i],D,V);
                value+=C*de[i];
                i++;
            }else{
                ret++;
                //if(ret<10) System.out.format("value=%d -> %d, ret=%d, V=%d\n",value,value+(value+1)*C,ret,V);
                value+=(value+1)*C;
            }
        }
        return ret;
    }
    public static void main(String[] argin) {
        C inst = new C();
        Scanner in = new Scanner(System.in);
        int C,D,V;
        int[] de;
        String kb,target;
        int T = in.nextInt();
        for(int ii=1; ii<=T; ii++){
            C = in.nextInt();
            D = in.nextInt();
            V = in.nextInt();
            de=new int[D];
            for(int i=0; i<D; i++)
                de[i]=in.nextInt();
            System.out.printf("Case #%d: %d\n", ii, inst.solver(C,D,V,de));
        }
    }
}

