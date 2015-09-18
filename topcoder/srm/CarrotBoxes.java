import java.util.*;
 
public class CarrotBoxes {
    public double theProbability(String[] information) {
        int n = information.length;
        int res = n - 1;
        for (int skipped = 0; skipped < n; ++skipped) {
            boolean[][] see = new boolean[n][n];
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < n; ++j) {
                    if (i != skipped && j != skipped)
                        see[i][j] = information[i].charAt(j) == 'Y';
                }
            see[skipped][skipped] = true;
            for (int k = 0; k < n; ++k)
                for (int i = 0; i < n; ++i)
                    for (int j = 0; j < n; ++j)
                        see[i][j] |= see[i][k] && see[k][j];
            
            for (int k = 0; k < n; ++k){
                for (int i = 0; i < n; ++i)
                    System.out.print((see[k][i]?1:0) +" ");
                System.out.println();
            }

            System.out.println();

            int cnt = 0;
            boolean[] mark = new boolean[n];
            for (int i = 0; i < n; ++i) {
                if (mark[i]) continue;
                boolean ok = true;
                for (int j = 0; j < n; ++j)
                    if (see[j][i] && !see[i][j])
                        ok = false;
                if (!ok)
                    continue;
                ++cnt;
                System.out.println(skipped+"\t"+i+"\t"+cnt);
                mark[i] = true;
                for (int j = 0; j < n; ++j)
                    if (see[i][j] && see[j][i])
                        mark[j] = true;
            }
            res = Math.min(res, cnt - 1);
        }
        return (n - res) / (double) n;
    }
    public static void main(String[] args){
        CarrotBoxes test = new CarrotBoxes();
        String[] str={"YYYYY",
            "NYNNN",
            "NNYNN",
            "NNNYN",
            "NNNNY"};
        System.out.println(test.theProbability(str));
    }
 
}
 
 
// Powered by FileEdit
// Powered by RETester [based on ExampleBuilder]
// Powered by CodeProcessor
