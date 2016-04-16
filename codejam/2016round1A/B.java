import java.io.*;
import java.util.*;
public class B{
    class CC implements Comparator<int[]>{
        int idx;
        CC(int x){
            idx=x;
        }
        public int compare(int[] a, int[] b){
            return a[idx]-b[idx];
        }
    }
    static java.io.PrintStream out=System.out;
    void solver(int N, int[][] lists){
        int miss=0;
        while(true){
            Arrays.sort(lists,new CC(miss));
            if(miss==N-1 || lists[miss*2][miss]!=lists[miss*2+1][miss])
                break;
            else
                miss++;
        }
        int i=0, j=0, k=0;
        int[] ret=new int[N];
        ret[miss]=lists[miss*2][miss];
        //out.format("%d\n", miss);
        while(i<2*N-1){
            //out.format("%s, %d, %d\n",Arrays.toString(ret), j, k);
            if(j<N && lists[i][miss]==lists[2*miss][j]){
                j++;
            }else{
                if(k==miss) k++;
                ret[k++]=lists[i][miss];
            }
            i++;
        }
        for(int x:ret)
            out.format(" %d", x);
        out.println();
    }
    public static void main(String[] argin) {
        B inst = new B();
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int ii=1; ii<=T; ii++){
            int N=in.nextInt();
            int[][] lists=new int[2*N-1][N];
            for(int i=0; i<2*N-1; i++){
                for(int j=0; j<N; j++)
                    lists[i][j]=in.nextInt();
            }
            System.out.printf("Case #%d:", ii);
            inst.solver(N, lists);
        }
    }
}

