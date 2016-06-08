import java.io.*;
import java.util.*;

public class A{
    static java.io.PrintStream out=System.out;
    public static void main(String[] argin) {
        A inst = new A();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int sum=0, max=0;
            HashMap<Integer, Integer> map=new HashMap<>();
            for(int i=0; i<5; i++){
                int x=in.nextInt();
                Integer c=map.get(x);
                if(c==null) map.put(x,1);
                else map.put(x,c+1);
                sum+=x;
            }
            for(Map.Entry<Integer, Integer> p:map.entrySet()){
                if(p.getValue()>1){
                    max=Math.max(max,p.getKey()*Math.min(p.getValue(),3));
                }
            }
            out.println(sum-max);
        }
    }
}

