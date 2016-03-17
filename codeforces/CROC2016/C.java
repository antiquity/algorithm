import java.io.*;
import java.util.*;

public class C{
    public static void main(String[] argin) {
        C inst = new C();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt();
            Map<String,Collection<String>> map = new HashMap<String,Collection<String>>();
            for(int i=0; i<n; i++){
                String url = in.next();
                int idx = url.indexOf('/',7);
                if(idx==-1) continue;
                String host = url.substring(7,idx),
                       path = url.substring(idx+1);
                Collection<String> pathes = map.get(host);
                if(pathes==null){
                    pathes = new HashSet<String>();
                    map.put(host,pathes);
                }
                pathes.add(path);
            }
            Map<Collection<String>,Collection<String>> remap
                = new HashMap<Collection<String>,Collection<String>>();
            for(Map.Entry<String,Collection<String>> entry : map.entrySet()){
                Collection<String> hosts = remap.get(entry.value());
                if(hosts=null){
                    hosts = new HashSet<String>();
                    remap.put(entry.value(),hosts);
                }
                hosts.add(entry.key());
            }
            List<Collection<String>> ret = new ArrayList<Collection<String>>();
            for(Collection<String> x : remap.values())
                if(x.size()>1)
                    ret.add(x);
            System.out.println(ret.size());
            for(Collection<String> x : ret){
                for(String str : x)
                    System.out.print(str+" ");
                System.out.println();
            }
        }
    }
}

