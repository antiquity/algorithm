import java.io.*;
import java.util.*;


public class A{
    public static void main(String[] argin) {
        A inst = new A();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt(), c = in.nextInt();
            int[] p= new int[n], t = new int[n];
            for(int i=0; i<n; i++)
                p[i] = in.nextInt();
            for(int i=0; i<n; i++)
                t[i] = in.nextInt();
            int a=0, b=0, x=0;
            for(int i=0; i<n; i++){
                x+=t[i];
                a+=Math.max(0,p[i]-c*x);
            }
            x=0;
            for(int i=n-1; i>=0; i--){
                x+=t[i];
                b+=Math.max(0,p[i]-c*x);
            }
            String ret = a>b?"Limak":(a<b?"Radewoosh":"Tie");
            System.out.println(ret);
        }
    }
}

