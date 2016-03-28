import java.io.*;
import java.util.*;


public class D{
    long solver(int[] a, int n, int k){
        int l=30;
        //while((1<<l)<=k) l++; l--;
        List<Integer> ret = new ArrayList<>();
        for(int i=0; i<=n; i++){
            int sum=0;
            int sum2=0;
            for(int j=Math.max(0,i-l); j<i; j++){
                if(a[j]>=0){
                    sum+=((a[j])>>(i-j));
                    sum2+=((a[j]&((1<<(i-j))-1))<<(l-(i-j)));
                }else{
                    sum-=((-a[j])>>(i-j));
                    sum2-=(((-a[j])&((1<<(i-j))-1))<<(l-(i-j)));
                }
            }
            for(int j=i+1; j<=Math.min(i+l,n); j++)
                if(a[j]>=0)
                    sum+=((a[j]&((1<<(l+1-(j-i)))-1))<<(j-i));
                else
                    sum-=(((-a[j])&((1<<(l+1-(j-i)))-1))<<(j-i));

            System.out.println(i+": "+ret+": "+sum+": "+sum2);
            if(sum+a[i]==0) continue;
            else{
                if(sum2!=0){
                   if(ret.size()==0) return 0;
                }else{
                    if(Math.abs(sum)<=k && (i!=n || sum!=0)){
                        for(int x : ret)
                            if(x+l<i)
                                return 0;
                        ret.add(i);
                    }
                }
            }
        }
        return ret.size();
    }
    public static void main(String[] argin) {
        D inst = new D();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n=in.nextInt(), k=in.nextInt();
            int[] a = new int[n+1];
            for(int i=0; i<=n; i++)
                a[i]=in.nextInt();
            System.out.println(inst.solver(a,n,k));
        }
    }
}

