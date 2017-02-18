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
outter:
        while(in.hasNext()){
            String a = in.next() + new StringBuilder(in.next()).reverse().toString();
            String b = in.next() + new StringBuilder(in.next()).reverse().toString();
            int i=0, j=0;
            while(a.charAt(i)!='A') i++;
            while(b.charAt(j)!='A') j++;
            
            for(int k=1; k<4; k++){
                if(a.charAt(i)!=b.charAt(j)){
                    System.out.println("NO");
                    continue outter;
                }
                i=(i+1)%4; j=(j+1)%4;
                if(a.charAt(i)=='X') i=(i+1)%4;
                if(b.charAt(j)=='X') j=(j+1)%4;
            }
            System.out.println("YES");
        }
    }
}

