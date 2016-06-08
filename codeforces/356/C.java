import java.io.*;
import java.util.*;


public class C{
    static java.io.PrintStream out=System.out;
    static int[] primes=new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};
    public static void main(String[] argin) {
        C inst = new C();
        int count=0;
        Scanner in = new Scanner(System.in);
        for(int x:primes){
            out.println(x);
            out.flush();
            String ans=in.next();
            if(ans.equals("yes")){
                count++;
                if(x<=10 && count<2){
                    out.println(x*x);
                    out.flush();
                    if(in.next().equals("yes"))
                        count++;
                }
            }
            if(count==2){
                out.println("composite");
                out.flush();
                return;
            }
        }
        out.println("prime");
        out.flush();
    }
}

