import java.io.*;
import java.util.*;


public class E{
    class UnionFind{
        int[] p, size;
        int comps;
        UnionFind(int n){
            p = new int[n];
            size = new int[n];
            reset();
        }
        UnionFind(UnionFind old){
            p = old.p.clone();
            size = old.size.clone();
            comps=old.comps;
        }
        void reset(){
            for(int i=0; i<p.length; i++){
                p[i]=i;
                size[i]=1;
            }
            comps = p.length;
        }
        int find(int x){
            if(x!=p[x]) p[x]=find(p[x]);
            return p[x];
        }
        void union(int x, int y){
            x = find(x);
            y = find(y);
            if(x!=y)
                if(size[x]>size[y]){
                    p[y]=x;
                    size[x]+=size[y];
                }else{
                    p[x]=y;
                    size[y]+=size[x];
                }
            comps--;
        }
    }
    static java.io.PrintStream out=System.out;
    void inc(Map<Integer,Integer> map, int p){
        Integer c=map.get(p);
        if(c==null){
            map.put(p,1);
            sum+=uf.size[p];
        }else map.put(p,c+1);
    }
    void dec(Map<Integer,Integer> map, int p){
        Integer c=map.get(p);
        if(c==null) return;
        if(c>1) map.put(p,c-1);
        else{
            map.remove(p);
            sum-=uf.size[p];
        }
    }
    int sum;
    UnionFind uf;
    int solve(int n, int k, char[][] map){
        uf=new UnionFind(n*n);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i>0 && map[i][j]=='.' && map[i-1][j]=='.')
                    uf.union(i*n+j,(i-1)*n+j);
                if(j>0 && map[i][j]=='.' && map[i][j-1]=='.')
                    uf.union(i*n+j,i*n+j-1);
            }
        }
        int[][] p=new int[n][n];
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                p[i][j]=uf.find(i*n+j);

        Map<Integer,Integer> count=new HashMap<>();
        sum=0;
        for(int i=0; i<=k; i++)
            for(int j=0; j<=k; j++)
                inc(count,p[i][j]);
        dec(count,p[k][k]);
        int max=sum;
        out.print(max);

        for(int i=0; i<=n-k;){
            int j=1;
            for(j=1; j<=n-k; j++){
                if(i>0){
                    inc(count,p[i-1][j+k-1]);
                    dec(count,p[i-1][j-1]);
                }
                if(i+k<n){
                    inc(count,p[i+k][j+k-1]);
                    dec(count,p[i+k][j-1]);
                }
                if(j<n-k) for(int l=i; l<i+k; l++)
                    inc(count,p[l][j+k]);
                if(j-2>=0) for(int l=i; l<i+k; l++)
                    dec(count,p[l][j-2]);

                max=Math.max(max,sum);
                out.print(max);
            }
            out.println();
            i++; j--;
            if(i>n-k) break;

            inc(count,p[i+k-1][j-1]);
            dec(count,p[i-1][j-1]);
            if(i+k<n) for(int l=j; l<j+k; l++) inc(count,p[i+k][l]);
            if(i-2>=0) for(int l=j; l<j+k; l++) dec(count,p[i-2][l]);
            max=Math.max(max,sum);
                out.print(max);

            for(j=n-k-1; j>=0; j--){
                if(i>0){
                    inc(count,p[i-1][j]);
                    dec(count,p[i-1][j+k]);
                }
                if(i+k<n){
                    inc(count,p[i+k][j]);
                    dec(count,p[i+k][j+k]);
                }
                if(j-1>=0) for(int l=i; l<i+k; l++)
                    inc(count,p[l][j-1]);
                if(j<n-k-1) for(int l=i; l<i+k; l++)
                    dec(count,p[l][j+k+1]);

                max=Math.max(max,sum);
                out.print(max);
            }
            i++; j++;
            out.println();

            if(i>n-k) break;
            inc(count,p[i+k-1][j+k]);
            dec(count,p[i-1][j+k]);
            if(i+k<n) for(int l=j; l<j+k; l++) inc(count,p[i+k][l]);
            for(int l=j; l<j+k; l++) dec(count,p[i-2][l]);
            max=Math.max(max,sum);
            out.print(max);
        }
        return max;
    }
    public static void main(String[] argin) {
        E inst = new E();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt(), k=in.nextInt();
            char[][] map = new char[n][];
            for(int i=0; i<n; i++)
                map[i]=in.next().toCharArray();
            out.println(inst.solve(n,k,map));
        }
    }
}

