import java.io.*;
import java.util.*;
import java.lang.Math;

public class B{
    int[] sol=new int[1000];
    int[] m=new int[1000];

    int betterSolver(int B, long N, int[] m){
        long l=0,r=1,mm,cnt;
        for(int i=0; i<B; i++)
            r=Math.max(r,m[i]);
        r=r*(N-1);
        while(r>l+1){
            mm=(r+l)/2;
            cnt=0;
            for(int i=0; i<B; i++)
                cnt+=(mm-1)/m[i]+1;
            if(cnt>=N) r=mm;
            else l=mm;
        }
        cnt=0;
        for(int i=0; i<B; i++)
            cnt+=(l-1)/m[i]+1;
        for(int i=0; i<B; i++) if(l%m[i]==0){
            cnt++;
            if(cnt==N) return i+1;
        }
        return -1;
    }
    long lcm(long x, long y){
        return x/gcd(x,y)*y;
    }
    long gcd(long l, long s){
        return s==0 ? l : gcd(s,l%s);
    }
    int solver(int B, long N, int[] m){
        if(B>=N) return (int)N;
        int[] time=Arrays.copyOf(m,B);
        int serv=0;
        int min,in; 
        boolean test;

        long mt=m[0];
        long p=0;
        for(int i=1; i< B; i++) mt=lcm(mt,m[i]);
        for(int i=0; i< B; i++) p+=(mt/m[i]);
        System.out.println(N+" "+B+" "+p+" "+mt);
        p = B+(N-B)/p*p;

        for(long i=p+1; i<=N;){
            //System.out.println(Arrays.toString(time) + " " + i);
            min=time[0];
            for(int j=1; j<B; j++) if(min>time[j]){
                    min=time[j];
                }
            for(int j=0; j<B; j++) if(time[j]==min){
                if(i==N) return j+1;
                time[j]=m[j];
                i++;
            }else
                time[j]-=min;
        }
        return B;
    }
    public static void main(String[] argin) {
        B inst = new B();
        try{
            BufferedReader in = new BufferedReader(new FileReader(argin[0]));
            PrintStream out = System.out;
            String inputLine = in.readLine();
            String[] strArr;
            int B;
            long N;
            int T = Integer.parseInt(inputLine.trim());
            for(int ii=1; ii<=T; ii++){
                strArr=in.readLine().trim().split("[\\s]+");
                B=Integer.parseInt(strArr[0]); 
                N=Integer.parseInt(strArr[1]);
                strArr=in.readLine().trim().split("[\\s]+");
                for(int i=0; i<B; i++)
                    inst.m[i]=Integer.parseInt(strArr[i]);
                out.printf("Case #%d: %d\n", ii, inst.betterSolver(B,N,inst.m));
            }
            in.close();
            out.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }
}

