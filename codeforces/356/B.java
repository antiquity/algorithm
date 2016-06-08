import java.io.*;
import java.util.*;

public class B{
    static java.io.PrintStream out=System.out;
    int solve(int n, int a, int[] t){
        int sum=t[a]==1?1:0;
        int i=a-1, j=a+1;
        while(i>=0 && j<n){
            if(t[i]==1 && t[j]==1) sum+=2;
            i--; j++;
        }
        if(i<0){
            while(j<n){
                if(t[j]==1) sum++;
                j++;
            }
        }else
            while(i>=0){
                if(t[i]==1) sum++;
                i--;
            }
        return sum;
    }
    public static void main(String[] argin) {
        B inst = new B();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt(), a=in.nextInt()-1;
            int[] t = new int[n];
            for(int i=0; i<n; i++){
                t[i]=in.nextInt();
            }
            out.println(inst.solve(n,a,t));
        }
    }
}

