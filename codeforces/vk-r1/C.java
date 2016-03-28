import java.io.*;
import java.util.*;


public class C{
    void solver(int n, int d, int h){
        if(d>2*h){
            System.out.println(-1);
            return;
        }
        if(h==1 && d==1 && n>2){
            System.out.println(-1);
            return;
        }
        for(int i=2; i<=h+1; i++)
            System.out.format("%d  %d\n",i-1,i);
        int j=1;
        if(d>h){
            for(int i=h+2; i<=n; i++){
                System.out.format("%d  %d\n",j,i);
                j=i;
                if((i-h-1)%(d-h)==0)
                    j=1;
            }
        }else{
            for(int i=h+2; i<=n; i++){
                System.out.format("%d  %d\n",h,i);
            }
        }
    }

    public static void main(String[] argin) {
        C inst = new C();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt(), d = in.nextInt(), h=in.nextInt();
            inst.solver(n,d,h);
        }
    }
}

