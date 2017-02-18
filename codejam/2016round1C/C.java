import java.io.*;
import java.util.*;
public class C{
    static java.io.PrintStream out=System.out;
    class Pair{
        int a,b;
        Pair(int a, int b){
            this.a=a; this.b=b;
        }
        public int hashCode(){
            return (String.valueOf(a)+","+String.valueOf(b)).hashCode();
        }
        public boolean equals(Object o){
            Pair p=(Pair)o;
            return a==p.a && b==p.b;
        }
    }
    int J,P,S,K;
    HashMap<Pair, Integer> map01;
    HashMap<Pair, Integer> map02;
    HashMap<Pair, Integer> map12;
    int[] sol;
    List<int[]> list;
    private void solver(){
        sol=new int[3];
        list=new ArrayList<>();
        map01=new HashMap<>();
        map02=new HashMap<>();
        map12=new HashMap<>();
        for(int i=1; i<=J; i++)
            for(int j=1; j<=S; j++)
                map02.put(new Pair(i,j),0);
        for(int i=1; i<=P; i++)
            for(int j=1; j<=S; j++)
                map12.put(new Pair(i,j),0);
        doit(0);
        out.println(list.size());
        for(int[] ll:list){
            for(int x:ll) out.print(x+" ");
            out.println();
        }
    }
    private void doit(int i){
        if(i==3){
            list.add(sol.clone());
            return;
        }
        if(i==0){
            for(int j=1; j<=J; j++){
                sol[i]=j;
                doit(1);
            }
        }else if(i==1){
            for(int p=1; p<=P; p++){
                Pair pair=new Pair(sol[0],p);
                Integer count=map01.get(pair);
                count=(count==null?0:count);
                if(count>=K) continue;
                sol[i]=p;
                doit(2);
            }
        }else if(i==2){
            Pair pair01=new Pair(sol[0],sol[1]);
            Set<Integer> tried=new HashSet<>();
            for(int s=1; s<=S; s++){
                Integer c01=map01.get(pair01);
                c01=(c01==null?0:c01);
                if(c01>=K) return;

                int min=K;
                Pair pair=new Pair(sol[0],s);
                for(Map.Entry<Pair,Integer> entry:map02.entrySet()){
                    if(entry.getKey().a==sol[0] &&
                            !tried.contains(entry.getKey().b)
                            && entry.getValue()<min){
                        min=entry.getValue();
                        pair=entry.getKey();
                    }
                }

                Integer count=map02.get(pair);

                Pair pair1=new Pair(sol[1],pair.b);
                Integer count1=map12.get(pair1);
                if(count1>=K){
                    tried.add(pair.b);
                    continue;
                }

                map01.put(pair01,c01+1);
                map02.put(pair,count+1);
                map12.put(pair1,count1+1);
                sol[i]=pair.b;
                doit(3);
            }
        }
    }
    public static void main(String[] argin) {
        C inst = new C();
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int ii=1; ii<=T; ii++){
            inst.J=in.nextInt();
            inst.P=in.nextInt();
            inst.S=in.nextInt();
            inst.K=in.nextInt();
            System.out.printf("Case #%d: ", ii);
            inst.solver();
        }
    }
}

