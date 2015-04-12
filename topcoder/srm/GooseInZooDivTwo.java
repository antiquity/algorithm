// BEGIN CUT HERE

// END CUT HERE
import java.util.*;
public class GooseInZooDivTwo {
    char[][] f;
    int nr, nc;
    int xx=1000000007;
    public int count(String[] field, int dist) {
        nr=field.length;
        nc=field[0].length();
        f=new char[nr][nc];
        int N=1;
        for(int i=0; i<nr; i++){
            f[i]=field[i].toCharArray();
        }

        for(int i=0; i<nr; i++)
            for(int j=0; j<nc; j++){
                if(f[i][j]=='v'){
                    kill(i,j,dist);
                    N=(N*2) % xx;
                }
            }
        return N-1;
    }
    void kill(int a, int b, int d){
        f[a][b]='.';
        for(int i=a-d; i<=a+d; i++)
            for(int j=b-d+Math.abs(i-a); j<=b+d-Math.abs(i-a); j++)
                if(i>=0 && i<nr && j>=0 && j<nc){
                    if(f[i][j]=='v')
                        kill(i,j,d);
            }
    }
    public static void main(String[] args) {
        GooseInZooDivTwo temp = new GooseInZooDivTwo();
    }
}
