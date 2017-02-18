import java.io.*;
import java.util.*;

public class B{
    private long solver(int n, int k){
        long ret = 0;
        int m = n-2*k+2;
        if(m<3) m=n%2+2;
        return ((long)(m+n-3))*((n-m)/2+1);
    }
    public static void main(String[] argin) {
        B inst = new B();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n=in.nextInt(), k=in.nextInt();
            System.out.println(inst.solver(n,k));
        }
    }
}

