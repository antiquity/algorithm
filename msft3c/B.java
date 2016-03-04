import java.io.*;
import java.util.*;

class Primes{
    static List<Integer> p=new ArrayList<Integer>();
    Primes(){
        if(p.size()==0){
            int[] s = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};
            for(int a:s) p.add(a);
        }
    }
    private void getNextPrime(){
        int x=p.get(p.size()-1), sqrtX;
nextInt:
        while(true){
            x+=2;
            sqrtX=(int)Math.sqrt(x);
            for(int j:p)
                if(x%j==0)
                    continue nextInt;
                else if(j>=sqrtX)
                    break;
            p.add(x);
            break;
        }
    }
    int getIdx(int n){
        while(p.get(p.size()-1)<n){
            getNextPrime();
        }
        int h=p.size()-1,l=0,m;
        while(h-l>1){
            m=(h+l)/2;
            if(p.get(m)<n)
                l=m;
            else if(p.get(m)>n)
                h=m;
            else
                return m;
        }
        return l;
    }
    int getPrime(int i){
        while(i>=p.size())
            getNextPrime();
        return p.get(i);
    }
}
public class B{
    void solve(String str){
        int N =str.length();
        int[] count = new int[26];
        for(int i=0; i<N; i++)
            count[str.charAt(i)-'a']++;
        int odd=0,even=0;
        for(int i=0; i<26; i++){
            if(count[i]%2==1){
                odd++;
            }else even++;
            count[i]/=2;
        }

        int remove=0;
        if(odd>1) remove=odd-1;
        int tofill = (N-remove)/2;
        long ret=1;
        //System.out.println(tofill+": "+Arrays.toString(count));
        for(int x:count) if(x>0){
            //System.out.format("%d, %d, %d, %d\n",tofill,x,binomial(tofill,x),nchoosek(tofill,x));
            ret*=binomial(tofill,x);
            tofill-=x;
        }
        System.out.format("%d,%d\n",remove,ret);
    }
    public static void main(String[] argin) {
        B inst = new B();
        Scanner in = new Scanner(System.in);

        while(in.hasNext()){
            inst.solve(in.next().trim().toLowerCase());
        }
    }
    static int[] toArray(List<Integer> aa){
        int[] bb= new int[aa.size()];
        for(int i=0; i< aa.size(); i++)
            bb[i]=aa.get(i);
        return bb;
    }
    long nchoosek(int a, int b){
        if(b>a/2) b=(a-b);
        Primes prime = new Primes();
        int M = prime.getIdx(a);
        int[] res = new int[M+1];
        int s=a,p;
        while(s>(a-b)){
            for(int j=0,t=s; t>1; j++){
                p=prime.getPrime(j);
                while(t%p==0){
                    res[j]++;
                    t=t/p;
                }
            }
            s--;
        }
        s=2;
        while(s<=b){
            for(int j=0,t=s; t>1; j++){
                p=prime.getPrime(j);
                while(t%p==0){
                    res[j]--;
                    t=t/p;
                }
            }
            s++;
        }
        long tt=1;
        for(int j=0; j<=M; j++){
            while(res[j]>0){
                p=prime.getPrime(j);
                //tt=tt*p%N;
                tt=tt*p;
                res[j]--;
            }
        }
        return tt;
    }
    // binomial or nchoosek
    long binomial(int n, int k) { 
        if(k>n/2) k=n-k;
        long[] bin=new long[k+1];
        bin[0]=1;
        for(int i=1; i<=n; i++)
            for(int j=Math.min(k,i); j>0; j--)
                bin[j]=bin[j]+bin[j-1];
        return bin[k];
    }
    int binomial(int n, int k) { 
        if(k>n/2) k=n-k;
        int m=n-k;
        int[] dp=new int[k+1];
        Arrays.fill(dp,1);
        for(int i=1; i<=m; i++)
            for(int j=1;j<=k; j++)
                dp[j]=dp[j]+dp[j-1];
        return dp[k];
    }
}

