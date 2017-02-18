import java.util.*;

public class DoLunch {
    List<String> address;  // list of addresses indexed from 0 to N-1
    List<List<Integer>> map,newMap; // map[i] contains all nodes reachable from i
    List<Integer> peggy,start; // index (peggy) and group (start) for Peggy
    Set<Integer> sam,end;  // index (sam) and group (end) for Sam
    // grpTable[i] maps index i to group;
    // indices share the same group if they are strongly connected
    int[] grpTable;
    int N;

    public DoLunch(List<String> address, List<List<Integer>> map, Set<Integer> avoid,
            List<Integer> peggy, Set<Integer> sam){
        this.address=address;
        // isolate the avoided points by changing the map
        isolateAvoid(map, avoid);  
        this.map=map;
        this.peggy=peggy;
        this.sam=sam;
        N = map.size();
    }

    public List<String> solve(){
        // group the strongly connected components by grpNum and generate newMap
        // Tarjan's Algorithm is used here
        reduceCircles();
        // System.out.println(address);
        // System.out.println(Arrays.toString(grpTable));
        // find the path by DFS and collection all the group numbers
        Set<Integer> lunchLocationGrp=solve(newMap, start, end);

        //map the result indices to the corresponding addresses and sort the result
        List<String> result= new ArrayList<String>();
        for(int i=0; i<N; i++)
            if(lunchLocationGrp.contains(grpTable[i]))
                result.add(address.get(i));
        Collections.sort(result);
        return result;
    }

    private Set<Integer> solve(List<List<Integer>>map, List<Integer>start, Set<Integer>end){
        Set<Integer> sol=new HashSet<Integer>();
        Deque<Integer> stack= new ArrayDeque<Integer>();
        boolean[] visited = new boolean[map.size()];
        // "canReach" records whether addresses are possible meeting location or not 
        boolean[] canReach = new boolean[map.size()];
        //"path" records the father nodes of each nodes
        int[] path = new int[map.size()];
        Arrays.fill(path, -1);
        for(int cur:start) stack.push(cur);
        while(!stack.isEmpty()){
            int cur=stack.pop();
            // if "cur" is possible location or "cur"s children are possible location,"good" is true
            boolean good=false;
            visited[cur]=true;
            if(end.contains(cur))
                good=true;
            for(int it:map.get(cur))
                if(!visited[it]){//
                    stack.push(it);
                    path[it]=cur;
                }else if(!good && canReach[it])
                    good=true;
            // add possible location to solution set
            if(good){
                while(cur!=-1 && !sol.contains(cur)){
                    sol.add(cur);
                    canReach[cur]=true;
                    cur=path[cur];
                }
            }
        }
        return sol;
    }

    private void isolateAvoid(List<List<Integer>> map, Set<Integer>avoid){
        int i=0;
        for(List<Integer> next:map){
            if(avoid.contains(i))
                next.clear();
            for(int j=next.size()-1;j>=0; j--){
                if(avoid.contains(next.get(j)))
                    next.remove(j);
            }
            i++;
        }
    }

    private int idx,grpNum;
    private int[] index, lowlink;
    private boolean[] onTrack;
    private Deque<Integer> stack;

    private void reduceCircles(){
        grpTable = new int[N];
        newMap = new ArrayList<List<Integer>>();
        index = new int[N];
        lowlink = new int[N];
        onTrack = new boolean[N];

        idx=0;
        stack = new ArrayDeque<Integer>();
        Arrays.fill(index,-1);
        grpNum=0;
        Arrays.fill(grpTable,-1);
        for(int i=0; i<N; i++) if(index[i]==-1){
            strongConnect(i);
        }

        for(int i=0; i<grpNum; i++)
            newMap.add(new ArrayList<Integer>());

        for(int i=0; i<map.size(); i++){
            List<Integer> next = map.get(i);
            for(int j : next)
                newMap.get(grpTable[i]).add(grpTable[j]);
        }

        start = new ArrayList<Integer>();
        end = new HashSet<Integer>();
        for(int i : peggy) start.add(grpTable[i]);
        for(int i : sam) end.add(grpTable[i]);
    }

    private void strongConnect(int i){
        //System.out.format("i=%d, %s\n",i,Arrays.toString(index));
        index[i]=idx;
        lowlink[i]=idx;
        onTrack[i]=true;
        stack.push(i);
        idx++;
        for(int j:map.get(i))
            if(index[j]==-1){
                strongConnect(j);
                lowlink[i]=Math.min(lowlink[i],lowlink[j]);
            }else if(onTrack[j])
                lowlink[i]=Math.min(lowlink[i],index[j]);

        if(lowlink[i]==index[i]){
            int temp;
            while(true){
                temp=stack.pop();
                onTrack[temp]=false;
                grpTable[temp]=grpNum;
                if(temp==i) break;
            }
            grpNum++;
        }
    }

    static void addAddress(List<String> address, HashMap<String,Integer>
            hash, List<List<Integer>> map, String port){
        if(!hash.containsKey(port)){
            address.add(port);
            hash.put(port,hash.size());
            map.add(new ArrayList<Integer>());
        }					
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //"address" is a list of all addresses,indexed from 0 to n-1
        List<String> address=new ArrayList<String>();
        //"hash" maps the addresses to their corresponding indices in "address"
        HashMap<String, Integer> hash= new HashMap<String,Integer>();
        //"map" is used to store the graph
        List<List<Integer>> map= new ArrayList<List<Integer>>();
        String first,second;
        // read the graph, and construct "address", "hash"
        while(sc.hasNext() && !sc.next().equals("Map:"));
        while(sc.hasNext() && !(first=sc.next()).equals("Avoid:")){
            second=sc.next();
            addAddress(address, hash, map, first);
            addAddress(address, hash, map, second);
            map.get(hash.get(first)).add(hash.get(second));
        }
        Set<Integer> avoid= new HashSet<Integer>();
        List<Integer> peggy= new ArrayList<Integer>();
        Set<Integer> sam= new HashSet<Integer>();
        // read avoid locations
        while(sc.hasNext()&& !(first=sc.next()).equals("Peggy:")){
            addAddress(address, hash, map, first);
            avoid.add(hash.get(first));
        }
        // read peggy's starting locations
        while(sc.hasNext() && !(first=sc.next()).equals("Sam:")){
            addAddress(address, hash, map, first);
            peggy.add(hash.get(first));
        }
        // read sam's starting locations
        while(sc.hasNext()){
            first=sc.next();
            addAddress(address, hash, map, first);
            sam.add(hash.get(first));
        }
        sc.close();

        List<String> result=(new DoLunch(address,map,avoid,peggy,sam)).solve();
        for(String res:result)
            System.out.println(res);

    }
}
