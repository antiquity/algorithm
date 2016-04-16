import java.io.*;
import java.util.*;
public class A{
    static java.io.PrintStream out=System.out;
    String solver(String S){
        StringBuilder ret=new StringBuilder();
        for(char x:S.toCharArray()){
            if(ret.length()==0 || x>=ret.charAt(0))
                ret.insert(0,x);
            else
                ret.append(x);
        }
        return ret.toString();
    }
    public static void main(String[] argin) {
        A inst = new A();
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        long max=0;
        for(int ii=1; ii<=T; ii++){
            String S=in.next();
            System.out.printf("Case #%d: %s\n", ii, inst.solver(S));
        }
        
    }
}

