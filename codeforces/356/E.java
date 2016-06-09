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
    void inc(int p){
        Integer c=count.get(p);
        if(c==null){
            count.put(p,1);
            sum+=uf.size[p];
        }else count.put(p,c+1);
    }
    void dec(int p){
        Integer c=count.get(p);
        if(c>1) count.put(p,c-1);
        else{
            count.remove(p);
            sum-=uf.size[p];
        }
    }
    int n, k;
    char[][] map;
    int sum;
    UnionFind uf;
    Map<Integer,Integer> count;
    int[][] p;
    int solve(){
        uf=new UnionFind(n*n);
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++) if(map[i][j]=='.'){
                int idx=i*n+j;
                if(i>0 && map[i-1][j]=='.')
                    uf.union(idx,idx-n);
                if(j>0 && map[i][j-1]=='.')
                    uf.union(idx,idx-1);
            }

        p=new int[n][n];
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                p[i][j]=uf.find(i*n+j);

        count=new HashMap<>();
        sum=0;
        for(int i=0; i<k; i++)
            for(int j=0; j<k; j++)
                inc(p[i][j]);
        int max=getSum(0,0);

        for(int i=0; i<=n-k;i++){
            for(int j=i%2==1?(n-k-1):1, jj=0;
                    jj<n-k; j+=(i%2==1?-1:1), jj++){
                for(int l=i; l<i+k; l++){
                    if(i%2==0){
                        inc(p[l][j+k-1]);
                        dec(p[l][j-1]);
                    }else{
                        inc(p[l][j]);
                        dec(p[l][j+k]);
                    }
                }
                max=Math.max(max,getSum(i,j));
            }
            if(i>=n-k) break;

            int j=i%2==0?n-k:0;
            for(int l=j; l<j+k; l++){
                inc(p[i+k][l]);
                dec(p[i][l]);
            }
            max=Math.max(max,getSum(i,j));
        }
        return max;
    }
    private int getSum(int i, int j){
        int s=sum, pp=0;
        Set<Integer> set=new HashSet<>();
        if(i>0) for(int l=j; l<j+k; l++)
            if(map[i-1][l]=='.' && !count.containsKey((pp=p[i-1][l])) && !set.contains(pp)){
                //out.println(pp+" "+uf.size[pp]);
                s+=uf.size[pp];
                set.add(pp);
            }
        if(i+k<n) for(int l=j; l<j+k; l++)
            if(map[i+k][l]=='.' && !count.containsKey((pp=p[i+k][l])) && !set.contains(pp)){
                s+=uf.size[pp];
                set.add(pp);
            }
        if(j>0) for(int l=i; l<i+k; l++)
            if(map[l][j-1]=='.' && !count.containsKey((pp=p[l][j-1])) && !set.contains(pp)){
                s+=uf.size[pp];
                set.add(pp);
            }
        if(j+k<n) for(int l=i; l<i+k; l++)
            if(map[l][j+k]=='.' && !count.containsKey((pp=p[l][j+k])) && !set.contains(pp)){
                s+=uf.size[pp];
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

