import java.io.*;
import java.util.*;
public class A{
    String solver(int N){
        if(N==0) return "INSOMNIA";
        boolean[] test=new boolean[10];
        int i=0;
        boolean run=true;
        while(run){
            i++;
            long y=(long)N*i;
            while(y!=0){
                test[(int)(y%10)]=true;
                y/=10;
            }
            run=false;
            for(boolean x:test)
                if(!x){
                    run=true;
                    break;
                }
        }
        return String.valueOf((long)N*i);
    }
    public static void main(String[] argin) {
        A inst = new A();
        Scanner in = new Scanner(System.in);
        int N;
        int T = in.nextInt();
        long max=0;
        for(int ii=1; ii<=T; ii++){
            N = in.nextInt();
            System.out.printf("Case #%d: %s\n", ii, inst.solver(N));
        }
        
    }
}

