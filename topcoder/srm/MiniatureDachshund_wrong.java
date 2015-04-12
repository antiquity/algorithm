


import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;
import java.util.*;

public class MiniatureDachshund {
    public int maxMikan(int[] mikan, int weight) {
        int remain = 5000-weight;
        HashMap<Integer,Integer> temp = new HashMap<Integer,Integer>();

        int a,b;
        temp.put(0,0);
        Iterator<Integer> it;
        Iterator<Integer> res;

        Set<Integer> setInt= temp.keySet();
        Integer[] aa = setInt.toArray(new Integer[0]);
           
        for(int i=0; i< mikan.length; i++){
            it = ((HashMap<Integer,Integer>)temp.clone()).keySet().iterator();
            res =((HashMap<Integer,Integer>)temp.clone()).values().iterator();
            while(it.hasNext()){
                a = it.next()+mikan[i];
                b = res.next()+1;
                if( a<=remain )
                   if(!temp.containsKey(a))
                       temp.put(a,b);
                   else{
                       if(b>temp.get(a))
                           temp.put(a,b);
                   }

            }
        }
        int ta=0;
        int tb=0;
        it = (Iterator<Integer>)temp.keySet().iterator();
        res = (Iterator<Integer>)temp.values().iterator();
        while(it.hasNext()){
            a=it.next();
            b=res.next();
            if(ta<a){
                ta = a;
                tb = b;
            }
        }
        return tb;
    }
}
