import java.io.*;
import java.util.*;


public class C{
    static java.io.PrintStream out=System.out;
    void solve(int n, int[] t){
        int[] count=new int[n];
        for(int i=0; i<n; i++){
            int[] sub=new int[n];
            int max=1,c=t[i];
            for(int j=i; j<n; j++){
                sub[t[j]]++;
                if(sub[t[j]]>max || (sub[t[j]]==max && t[j]<c)){
                    max=sub[t[j]];
                    c=t[j];
                }
                count[c]++;
                //out.format("%d, %d, %s\n",i,j,Arrays.toString(count));
            }
        }
        for(int x:count)
            out.print(x+" ");
        out.println();
    }
    public static void main(String[] argin) {
        C inst = new C();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt();
            int[] t = new int[n];
            for(int i=0; i<n; i++){
                t[i]=in.nextInt()-1;
            }
            inst.solve(n,t);
        }
    }
}

