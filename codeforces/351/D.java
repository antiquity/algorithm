import java.io.*;
import java.util.*;


public class D{
    static java.io.PrintStream out=System.out;
    void solve(int n, int k, int[] t){
        if(n<=4 || k<=n){
            out.println(-1);
            return;
        }
        Set<Integer> set=new HashSet<>();
        set.add(t[1]);
        set.add(t[2]);
        set.add(t[4]);
        set.add(t[n]);
        int i=1;
        while(set.contains(i)) i++;
        t[3]=i;
        for(int j=5; j<n; j++){
            i++;
            while(set.contains(i)) i++;
            t[j]=i;
        }
        for(i=1; i<=n; i++)
            out.print(t[i]+" ");
        out.println();
        out.print(t[2]+" ");
        out.print(t[1]+" ");
        out.print(t[3]+" ");
        for(i=n; i>3; i--)
            out.print(t[i]+" ");
        out.println();
    }
    public static void main(String[] argin) {
        D inst = new D();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt(), k=in.nextInt();
            int[] t = new int[n+1];
            t[1]=in.nextInt();
            t[n]=in.nextInt();
            t[2]=in.nextInt();
            t[4]=in.nextInt();
            inst.solve(n,k,t);
        }
    }
}

