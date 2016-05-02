import java.io.*;
import java.util.*;
public class C{
    List<Integer> primes;
    private void generatePrimes(int n){
        primes=new ArrayList<>();
        boolean[] notPrime=new boolean[n+1];
        for(int i=2; i<=n; i++) if(!notPrime[i]){
            for(long j=(long)i*i; j<=n; j+=i)
                notPrime[(int)j]=true;
            primes.add(i);
        }
    }
    private int getDivider(String x, int radix){
        long a=0, b=0, c=((long)1<<57);
        for(char d:x.toCharArray()){
            a*=radix;
            b*=radix;
            b+=(d=='1'?1:0);
            a+=(b/c);
            b%=c;
        }
        for(int p:primes){
            if(((a%p)*(c%p)+(b%p))%p==0){
                //System.out.format("%s=%d*%d+%d, by %d\n", x, a, c, b, p);
                return p;
            }
        }
        return -1;
    }
    private void solver(int N, int J){
        long x=((long)1<<(N-1))+1-2;
        List<Integer> divider=new ArrayList<>();
outer:
        while(J>0){
            x+=2;
            String xx=Long.toString(x,2);
            divider.clear();
            for(int i=2; i<=10; i++){
                int y;
                if((y=getDivider(xx,i))==-1)
                    continue outer;
                else
                    divider.add(y);
            }
            J--;
            System.out.print(xx);
            for(int d:divider)
                System.out.format(" %d", d);
            System.out.println();
        }
    }
    public static void main(String[] argin) {
        C inst = new C();
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        inst.generatePrimes(1<<10);
        //inst.generatePrimes(1<<18);
        //for(int i=0; i<100; i++) System.out.format("%d ", inst.primes.get(i));
        //System.out.println("size:"+inst.primes.size());
        for(int ii=1; ii<=T; ii++){
            int N=in.nextInt(),
                J=in.nextInt();
            System.out.printf("Case #%d:\n", ii);
            inst.solver(N,J);
        }
    }
}

