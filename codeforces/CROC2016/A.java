import java.io.*;
import java.util.*;


public class A{
    int[][] solver(int n, int a, int b){
        if(a*b<n)
            return new int[][]{{-1}};
        int[][] ret = new int[a][b];
        int odd=1, even=2;
        for(int i=0; i<a; i++)
            for(int j=0; j<b; j++){
                if((i+j)%2==0){
                    if(odd<=n){
                        ret[i][j]=odd;
                        odd+=2;
                    }
                }else{
                    if(even<=n){
                        ret[i][j]=even;
                        even+=2;
                    }
                }
                if(odd>n && even>n)
                    return ret;
            }
        return ret;
    }
    public static void main(String[] argin) {
        A inst = new A();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt(), a=in.nextInt(), b=in.nextInt();
            int[][] ret = inst.solver(n,a,b);
            for(int[] x : ret){
                for(int y : x)
                    System.out.print(y+" ");
                System.out.println();
            }
        }
    }
}

