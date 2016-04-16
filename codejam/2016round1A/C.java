import java.io.*;
import java.util.*;
public class C{
    static java.io.PrintStream out=System.out;
    class Kid{
        Kid bff;
        int id;
        boolean visited;
        List<Kid> likedby;
        Kid(int x){
            id=x;
            visited=false;
            likedby=new ArrayList<>();
        }
        public String toString(){
            return String.valueOf(id);
        }
        int deep(){
            int ret=0;
            visited=true;
            for(Kid x:likedby) if(bff!=x){
                ret=Math.max(ret, x.deep());
            }
            return ret+1;
        }
        int dfs(){
            return dfs(new ArrayDeque<Kid>());
        }
        int dfs(Deque<Kid> q){
            //out.format("%s from %s\n", this, likedby);
            visited=true;
            if(bff.visited){
                q.offerLast(this);
                while(!q.isEmpty() && q.peekFirst()!=bff) q.pollFirst();
                return q.size();
            }else{
                q.offerLast(this);
                return bff.dfs(q);
            }
        }
    }
    private int solver(int N, int[] F){
        Kid[] kids=new Kid[N];
        for(int i=0; i<N; i++)
            kids[i]=new Kid(i);
        for(int i=0; i<N; i++){
            kids[i].bff=kids[F[i]];
            kids[F[i]].likedby.add(kids[i]);
        }
        int ret=0;
        for(int i=0; i<N; i++){
            if(F[F[i]]==i){
                ret+=kids[i].deep();
                //out.format("B %d, ret=%d\n", i, ret);
            }
        }
        for(Kid x:kids) if(!x.visited){
            ret=Math.max(ret,x.dfs());
            //out.format("A ret=%d\n", ret);
        }
        return ret;
    }
    public static void main(String[] argin) {
        C inst = new C();
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int ii=1; ii<=T; ii++){
            int N=in.nextInt();
            int[] F=new int[N];
            for(int i=0; i<N; i++)
                F[i]=in.nextInt()-1;
            System.out.printf("Case #%d: %d\n", ii, inst.solver(N,F));
        }
    }
}

