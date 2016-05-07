import java.io.*;
import java.util.*;


public class A{
    static java.io.PrintStream out=System.out;
    private int solve(int[] nums){
        int pre=0;
        for(int x:nums){
            if(x<=pre+15)
                pre=x;
            else
                return pre+15;
        }
        return Math.min(pre+15, 90);
    }
    public static void main(String[] argin) {
        A inst = new A();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n=in.nextInt();
            int[] min=new int[n];
            for(int i=0; i<n; i++){
                min[i]=in.nextInt();
            }
            out.print(inst.solve(min));
        }
    }
}

