import java.io.*;


public class B{
    int solver2(int R, int C, int N){
        int temp;
        if(R%2==0 && C%2==1){temp=R; R=C; C=temp; }
        int r = (R+1)/2, c=(C+1)/2;
        int rr = (R)/2, cc=(C)/2;
        int a = r*cc+rr*c;
        int ret=0;
        if(N<=a) return ret;
        N-=a;

        if(R%2==0 && C%2==0){
            a=2;
            if(N<=a) return ret+N*2;
            else ret+=a*2;
            N-=a;
            a=(r+c)*2-4;
            if(N<=a) return ret+3*N;
            else ret+=3*a;
            N-=a;
            return ret+4*N;
        }else if(R%2==1 && C%2==0){
            a=2;
            if(N<=a) return ret+N*2;
            else ret+=a*2;
            N-=a;
            a=(r+rr+c+c)-4;
            if(N<=a) return ret+3*N;
            else ret+=3*a;
            N-=a;
            return ret+4*N;
        }else{
            //System.out.println(a+" "+N);
            a=4;
            if(N<=a) return ret+N*2;
            else ret+=a*2;
            N-=a;
            a=2*(r+c)-8;
            if(N<=a) return ret+N*3;
            else ret+=3*a;
            N-=a;
            return ret+4*N;
        }
    }
    int solver(int R, int C, int N){
        int temp;
        if(R%2==0 && C%2==1){temp=R; R=C; C=temp; }
        int r = (R+1)/2, c=(C+1)/2;
        int rr = (R)/2, cc=(C)/2;
        int a = r*c+rr*cc;
        int ret=0;
        if(N<=a) return ret;
        N-=a;
        if(R==1 || C==1){
            if(R%2==0 || C%2==0){
                ret++; N--;
            }
            return ret+2*N;
        }

        if(R%2==0 && C%2==0){
            a=2;
            if(N<=a) return ret+N*2;
            else ret+=a*2;
            N-=a;
            a=(rr+cc)*2-4;
            if(N<=a) return ret+3*N;
            else ret+=3*a;
            N-=a;
            return ret+4*N;
        }else if(R%2==1 && C%2==0){
            a=2;
            if(N<=a) return ret+N*2;
            else ret+=a*2;
            N-=a;
            a=(rr+r+c+c)-4;
            if(N<=a) return ret+3*N;
            else ret+=3*a;
            N-=a;
            return ret+4*N;
        }else{
            //System.out.println(a+" "+N);
            a=2*(rr+cc);
            if(N<=a) return ret+N*3;
            else ret+=3*a;
            N-=a;
            return ret+4*N;
        }
    }
    public static void main(String[] argin) {
        B inst = new B();
        try{
            BufferedReader in = new BufferedReader(new FileReader(argin[0]));
            PrintStream out = System.out;
            String inputLine;
            String[] strArr;
            int[] data;
            int R,C,N;
            int T = Integer.parseInt(in.readLine().trim());
            for(int ii=1; ii<=T; ii++){
                strArr=in.readLine().trim().split("[\\s]+");
                R = Integer.parseInt(strArr[0]);
                C = Integer.parseInt(strArr[1]);
                N = Integer.parseInt(strArr[2]);
                out.printf("Case #%d: %d\n", ii, Math.min(inst.solver(R,C,N),inst.solver2(R,C,N)));
                //out.printf("Case #%d: %d %d\n", ii, inst.solver(R,C,N),inst.solver2(R,C,N));
            }
            in.close();
            out.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }
}

