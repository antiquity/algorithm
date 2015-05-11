import java.io.*;
import java.util.*;


public class B{
    double solver(int K, int L, int S, String kb, String target){
outer:
        for(int i=0; i<L; i++){
            for(int j=0; j<K; j++)
                if(target.charAt(i)==kb.charAt(j))
                    continue outer;
            return 0f;
        }
        int[] cnt = new int[26];
        Arrays.fill(cnt,0);
        for(int i=0; i<K; i++) cnt[kb.charAt(i)-'A']++;

        int len;
        for(len=1; len<L; len++){
            //System.out.println(target.substring(0,L-len) + "=?" + (target.substring(len)));
            if(target.substring(0,L-len).equals(target.substring(len)))
                break;
        }
        int possible=(S-L)/len+1;

        double ret=1;
        for(int i=0; i<L; i++) ret*=(double)cnt[target.charAt(i)-'A']/K;
        //System.out.println(K+" "+L+" "+S);
        //System.out.println(kb);
        //System.out.println(target);
        //System.out.println(Arrays.toString(cnt));
        //System.out.println(len);
        //System.out.println(possible);
        //System.out.println(ret);

        return possible-ret*(S-L+1);
    }
    public static void main(String[] argin) {
        B inst = new B();
        Scanner in = new Scanner(System.in);
        int K,L,S;
        String kb,target;
        int T = in.nextInt();
        for(int ii=1; ii<=T; ii++){
            K = in.nextInt();
            L = in.nextInt();
            S = in.nextInt();
            kb = in.next();
            target = in.next();
            System.out.printf("Case #%d: %f\n", ii, inst.solver(K,L,S,kb,target));
        }
    }
}

