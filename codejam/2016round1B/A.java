import java.io.*;
import java.util.*;
public class A{
    static java.io.PrintStream out=System.out;
    String[] dict=new String[]{"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
    int[] order=new int[]{0,2,4,8,5,7,1,3,6,9};
    int len;
    int[] ret;
    int[] count;
    String solver(char[] S){
        len=S.length;
        count=new int[26];
        for(char x:S)
            count[x-'A']++;
        int[] c=new int[10];
        for(int num:order){
            int n=Integer.MAX_VALUE;
            for(char l:dict[num].toCharArray())
                n=Math.min(n,count[l-'A']);
            c[num]=n;
            for(char l:dict[num].toCharArray())
                count[l-'A']-=n;
        }
        StringBuilder ret=new StringBuilder();
        char l='0';
        for(int i=0; i<10; i++, l++){
            for(int j=0; j<c[i]; j++)
                ret.append(l);
        }
        return ret.toString();
    }

    public static void main(String[] argin) {
        A inst = new A();
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int ii=1; ii<=T; ii++){
            char[] S=in.next().toCharArray();
            System.out.printf("Case #%d: %s\n", ii, inst.solver(S));
        }
        
    }
}

