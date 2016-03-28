import java.io.*;
import java.util.*;


public class B{
    long solver(long n){
        return n*(n+1)*3+1;
    }
    public static void main(String[] argin) {
        B inst = new B();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n=in.nextInt(), k=in.nextInt(), q=in.nextInt();
            int[] t = new int[n];
            for(int i=0; i<n; i++){
                t[i]=in.nextInt();
            }
            TreeSet<Integer> set = new TreeSet<>();
            for(int ii=0; ii<q; ii++){
                int type = in.nextInt(), id = in.nextInt()-1;
                if(type==1){
                    set.add(t[id]);
                    if(set.size()>k)
                        set.pollFirst();
                }else{
                    if(set.contains(t[id]))
                        System.out.println("YES");
                    else
                        System.out.println("NO");
                }
            }
        }
    }
}

