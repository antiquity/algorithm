import java.io.*;
import java.util.*;
public class C{
    static java.io.PrintStream out=System.out;
    private int solver(String[][] edges){
        HashMap<String,Integer> first=new HashMap<>(), second=new HashMap<>();
        int id1=0, id2=0;
        for(String[] pair:edges){
            if(!first.containsKey(pair[0]))
                first.put(pair[0],id1++);
            if(!second.containsKey(pair[1]))
                second.put(pair[1],id2++);
        }
        int N=id1+id2+2;
        int[][] map=new int[N][N];
        String[] name1=new String[id1], name2=new String[id2];
        for(String[] pair:edges){
            int x=first.get(pair[0]), y=second.get(pair[1])+id1;
            name1[x]=pair[0]; name2[y-id1]=pair[1];
            map[x][y]=1;
        }
        for(int i=0; i<id1; i++) map[N-2][i]=1;
        for(int i=id1; i<N-2; i++) map[i][N-1]=1;
        int[][] newMap=maxflow(map);
        //out.println();
        //for(int[] x:map) out.println(Arrays.toString(x));
        //out.println();
        //for(int[] x:newMap) out.println(Arrays.toString(x));
        boolean[] firstV=new boolean[id1];
        boolean[] secndV=new boolean[id2];
        int cnt=0;
        for(int i=0; i<id1; i++)
            for(int j=id1; j<N-2; j++)
                if(newMap[i][j]!=map[i][j]){
                    firstV[i]=true;
                    secndV[j-id1]=true;
                    cnt++;
                    //out.format("%s %s\n", name1[i], name2[j-id1]);
                }
        for(boolean x:firstV) if(!x) cnt++;
        for(boolean x:secndV) if(!x) cnt++;
        return edges.length-cnt;
    }
    private boolean bfs(int source, int sink, int[] prev, int[][] map){
        int N=map.length;
        Deque<Integer> q=new ArrayDeque<Integer>();
        boolean[] visited=new boolean[N];
        visited[source]=true;
        q.offerLast(source);
        while(!q.isEmpty()){
            int cur=q.pollFirst();
            for(int j=0; j<N; j++) if(map[cur][j]>0 && !visited[j]){
                prev[j]=cur;
                visited[j]=true;
                if(j==sink){
                    return true;
                }
                q.offerLast(j);
            }
        }
        return false;
    }
    private int[][] maxflow(int[][] origMap){
        int N=origMap.length;
        int[][] map=new int[N][];
        {
            int i=0;
            for(int[] x:origMap)
                map[i++]=x.clone();
        }
        int[] prev=new int[N];
        Arrays.fill(prev,-1);
        while(true){
            if(bfs(N-2,N-1,prev,map)){
                int target=N-1,p;
                while((p=prev[target])!=-1){
                    map[p][target]--;
                    map[target][p]++;
                    target=p;
                }
            }else
                break;
        }
        return map;
    }
    public static void main(String[] argin) {
        C inst = new C();
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int ii=1; ii<=T; ii++){
            int N=in.nextInt();
            String[][] edges=new String[N][2];
            for(int i=0; i<N; i++){
                edges[i][0]=in.next();
                edges[i][1]=in.next();
            }
            System.out.printf("Case #%d: %d\n", ii, inst.solver(edges));
        }
    }
}

