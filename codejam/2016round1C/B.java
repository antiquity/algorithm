import java.io.*;
import java.util.*;
public class B{
    static java.io.PrintStream out=System.out;
    Res solver(String C, String J){
            return null;
    }
    public static void main(String[] argin) {
        B inst = new B();
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int ii=1; ii<=T; ii++){
            String C=in.next(), J=in.next();
            System.out.printf("Case #%d: ", ii);
            Res a=inst.solver(C, J);
        }
    }
}

