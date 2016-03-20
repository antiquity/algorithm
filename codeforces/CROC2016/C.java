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
                String url = in.next().trim();
                int idx = url.indexOf('/',8);
                if(idx==-1) continue;
                String host = url.substring(7,idx),
                       path = url.substring(idx);
                if(path.length()>1 && path.charAt(path.length()-1)=='/')
                    path=path.substring(0,path.length()-1);
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
                Collection<String> pathes = entry.getValue();
                if(pathes.size()==0) continue;
                if(pathes.size()==1){
                    boolean toContinue=false;
                    for(String str : pathes)
                        if(str.equals("/"))
                            toContinue=true;
                    if(toContinue) continue;
                }
                Collection<String> hosts = remap.get(pathes);
                if(hosts==null){
                    hosts = new HashSet<String>();
                    remap.put(pathes,hosts);
                }
                hosts.add(entry.getKey());
            }
            List<Collection<String>> ret = new ArrayList<Collection<String>>();
            for(Collection<String> x : remap.values())
                if(x.size()>1)
                    ret.add(x);
            System.out.println(ret.size());
            for(Collection<String> x : ret){
                for(String str : x)
                    System.out.print("http://"+str+" ");
                System.out.println();
            }
        }
    }
}

