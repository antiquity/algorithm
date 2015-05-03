import java.io.*;

public class A{
    long[] nine=new long[19];
    int length(long num){
        int n=0;
        while(num>0){ num/=10; n++; }
        return n;
    }
    long solver(long N){
        int n =length(N);
        long high = N;
        while(high>9) high/=10;
        long low = N%10;
        System.err.println(N + " " + n);

        if(low==0) return 1+solver(N-1);

        long ret=-1;
        int nnum=0;

        while(nnum<n-1){
            nnum++;
            ret+=(nine[(nnum+1)/2]+1);
            ret+=nine[nnum/2];
        }

        System.err.print(ret + " ");

        long temp=1;
        for(int i=0; i<(n+1)/2; i++){ temp*=10; }
        ret+=(revert(N/temp)+1);
        System.err.print(ret + " ");
        if(revert(N/temp)!=1) ret++;
        System.err.print(ret + " ");

        ret+=N%temp-1;
        System.err.println(ret + " ");

        return ret;
    }
    long revert(long N){
        long high = N;
        long low = 0;
        while(high>0){ low = (low*10 + high%10); high/=10; }
        return low;
    }
    long reduce(long N){
        long high = N/10;
        long low = 10;
        while(high%10==0){
            low*=10;
            high/=10;
        }
        long x;
        x = (N/(low))%10;
        //if(x==0) return N;
        return N-(x)*low;
    }
    long reduce1(long N){
        long high = N;
        long low = 1;
        while(high%10==1){
            low*=10;
            high/=10;
        }
        long x;
        x = (N/(low))%10;
        //if(x==0) return N;
        return N-(x)*low;
    }

    public static void main(String[] argin) {
        A inst = new A();
        try{
            inst.nine[0]=0;
            for(int i=1; i<inst.nine.length; i++) inst.nine[i]=inst.nine[i-1]*10+9;

            BufferedReader in = new BufferedReader(new FileReader(argin[0]));
            PrintStream out = System.out;
            //String inputLine = in.readLine();
            String[] strArr;
            int[] data;
            long N;
            int T = Integer.parseInt(in.readLine().trim());
            for(int ii=1; ii<=T; ii++){
                N=Long.parseLong(in.readLine().trim());
                out.printf("Case #%d: %d\n", ii, inst.solver(N));
            }
            in.close();
            out.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }
}

