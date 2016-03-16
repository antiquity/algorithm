import java.io.*;
import java.util.*;

public class B{
    public static void main(String[] argin) {
        B inst = new B();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt(), b=in.nextInt();
            Deque<int[]> q = new ArrayDeque<int[]>();
            int[] ret = new int[n];
            int t=0;
            for(int i=0; i<n; i++){
                int ti=in.nextInt(), di=in.nextInt();
                while(t<=ti && !q.isEmpty()){
                    int[] qx = q.pollFirst();
                    if(t<qx[0])
                        t=qx[0]+qx[1];
                    else
                        t+=qx[1];
                    ret[qx[2]]=t;
                }
                if(q.size()<b)
                    q.offerLast(new int[]{ti,di,i});
                else
                    ret[i]=-1;
            }
            while(!q.isEmpty()){
                int[] qx = q.pollFirst();
                if(t<qx[0])
                    t=qx[0]+qx[1];
                else
                    t+=qx[1];
                ret[qx[2]]=t;
            }
            for(int y : ret)
                System.out.print(y+" ");
            System.out.println();
        }
    }
}

