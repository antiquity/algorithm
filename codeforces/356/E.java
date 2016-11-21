import java.io.*;
import java.util.*;


public class E{
    static java.io.PrintStream out=System.out;
    void inc(int p){
        if(count[p]++==0)
            sum+=num.get(p);
    }
    void dec(int p){
        if(count[p]--==1)
            sum-=num.get(p);
    }
    int n, k;
    char[][] map;
    int sum;
    int[] count;
    int[][] ctgry;
    List<Integer> num;

    int[][] dir=new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    int dfs(int i, int j, boolean[][] visited, int N){
        visited[i][j]=true;
        ctgry[i][j]=N;
        int num=1;
        for(int k=0; k<4; k++){
            int x=i+dir[k][0], y=j+dir[k][1];
            if(x>=0 && x<n && y>=0 && y<n && map[x][y]=='.' && !visited[x][y])
                num+=dfs(x,y,visited,N);
        }
        return num;
    }
    int solve(){
        boolean[][] visited=new boolean[n][n];
        int N=0;

        ctgry=new int[n][n];
        num=new ArrayList<>();

        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(map[i][j]=='.' && !visited[i][j]){
                    num.add(dfs(i,j,visited,N));
                    N++;
                }else if(map[i][j]=='X'){
                    ctgry[i][j]=N;
                    num.add(1);
                    N++;
                }

        count=new int[N];
        sum=0;
        for(int i=0; i<k; i++)
            for(int j=0; j<k; j++)
                inc(ctgry[i][j]);
        int max=getSum(0,0);

        for(int i=0; i<=n-k;i++){
            for(int j=i%2==1?(n-k-1):1, jj=0;
                    jj<n-k; j+=(i%2==1?-1:1), jj++){
                for(int l=i; l<i+k; l++){
                    if(i%2==0){
                        inc(ctgry[l][j+k-1]);
                        dec(ctgry[l][j-1]);
                    }else{
                        inc(ctgry[l][j]);
                        dec(ctgry[l][j+k]);
                    }
                }
                max=Math.max(max,getSum(i,j));
            }
            if(i>=n-k) break;

            int j=i%2==0?n-k:0;
            for(int l=j; l<j+k; l++){
                inc(ctgry[i+k][l]);
                dec(ctgry[i][l]);
            }
            max=Math.max(max,getSum(i,j));
        }
        return max;
    }
    private int getSum(int i, int j){
        Set<Integer> set=new HashSet<>();
        int s=sum, pp;
        for(int l=j; l<j+k; l++)
            for(int m=i-1, t=0; t<2; m+=k+1, t++)
                if(m>=0 && m<n && !set.contains(pp=ctgry[m][l]) && map[m][l]=='.' && count[(pp)]==0){
                    //out.println(pp+" "+uf.size[pp]);
                    s+=num.get(pp);
                    set.add(pp);
                }

        for(int l=i; l<i+k; l++)
            for(int m=j-1, t=0; t<2; m+=k+1, t++)
                if(m>=0 && m<n && !set.contains(pp=ctgry[l][m]) && map[l][m]=='.' && count[(pp)]==0){
                    s+=num.get(pp);
                    set.add(pp);
                }
        //out.format("i=%d, j=%d, sum=%d, s=%d\n", i,j,sum,s);
        return s;
    }
    public static void main(String[] argin) {
        E inst = new E();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt(), k=in.nextInt();
            char[][] map = new char[n][];
            for(int i=0; i<n; i++)
                map[i]=in.next().toCharArray();
            inst.map=map;
            inst.n=n;
            inst.k=k;
            out.println(inst.solve());
        }
    }
}

