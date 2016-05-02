import java.io.*;
import java.util.*;
public class B{
    String solver(String S){
        char[] str=S.toCharArray();
        int ret=0;
        for(int i=0; i<str.length-1; i++)
            if(str[i]!=str[i+1])
                ret++;
        if(str[str.length-1]=='-')
            ret++;
        return String.valueOf(ret);
    }
    public static void main(String[] argin) {
        B inst = new B();
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int ii=1; ii<=T; ii++){
            String S=in.next();
            System.out.printf("Case #%d: %s\n", ii, inst.solver(S));
        }
    }
}

