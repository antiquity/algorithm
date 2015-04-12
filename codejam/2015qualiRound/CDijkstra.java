
import java.io.*;
import java.util.*;

public class Dijkstra{
    byte[][] matrix={
        {0, 0, 0, 0, 0},
        {0, 1, 2, 3, 4},
        {0, 2,-1, 4,-3},
        {0, 3,-4,-1, 2},
        {0, 4, 3,-2,-1}};
    byte[][] gi = {
        {1,2},
        {2,1},
        {3,4},
        {4,-3}};
    byte[][] gk={
        {1,4},
        {2,3},
        {4,1},
        {3,-2}};
    byte[] p4 = new byte[4];
    
    int LL=10000;
    byte[] b = new byte[LL];
    byte[] p0x = new byte[LL+1];
    byte[] pxL = new byte[LL+1];

    public static void main(String[] argin) {
        Dijkstra inst = new Dijkstra();
        try{
            BufferedReader in = new BufferedReader(new FileReader(argin[0]));
            //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("out.txt")));
            PrintStream out = System.out;
            int T = Integer.parseInt(in.readLine().trim());
            String[] strArr;
            int L;
            long X;
            char[] a;
            for(int ii=1; ii<=T; ii++){
                strArr=in.readLine().trim().split("[\\s]+");
                L = Integer.parseInt(strArr[0]);
                X = Long.parseLong(strArr[1]);
                a = in.readLine().trim().toCharArray();
                for(int i=0; i<L; i++) inst.b[i] = (byte)(a[i]-'i'+2);
                out.printf("Case #%d: %s\n", ii, inst.solver(L,X,inst.b));
            }
            in.close();
            out.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    byte times(byte a, byte b){
        byte t = matrix[Math.abs(a)][Math.abs(b)];
        return  (byte)((a*b)>0 ? t:-t);
    }
    boolean inp4(int a){
        for(int i=0; i<4; i++)
            if(p4[i]==a) return true;
        return false;
    }
    String solver(int L,long X, byte[] a){
        int temp;
        p0x[0]=1; pxL[L]=1;
        for(int i=1; i<=L; i++){
            int j=L-i;
            p0x[i]= times(p0x[i-1],a[i-1]);
            pxL[j]= times(a[j],pxL[j+1]);
        }
        p4[0]=1; for(int i=1; i<4; i++) p4[i]= times(p4[i-1],p0x[L]);
        if(p4[(int)(X%4)]!=-1) return "NO";

        byte il,ir,kl,kr,  jl,jm,jr;
        for(int i=0; i<4; i++) if(inp4(gi[i][0])) for(int k=0; k<4; k++) if(inp4(gk[k][1]))
            for(byte l=-1; l<=1; l+=2) for(byte j=-1; j<=1; j+=2){
                il=(byte)(j*gi[i][0]); ir=(byte)(j*gi[i][1]);
                kl=(byte)(l*gk[k][0]); kr=(byte)(l*gk[k][1]);

                boolean keepgo=true;
                for(int iri=0; iri<L && keepgo; iri++) if(p0x[iri]==ir)
                    for(int kli=L; kli>0 && keepgo; kli--) if(pxL[kli]==kl){
                        for(int jmi=-1; jmi<4; jmi++){
                            if((jmi==-1 && kli>iri &&  times(times(ir,(byte)3),kl)==p4[1])
                                    ||
                                    (jmi!=-1 && times(times(pxL[iri],p4[jmi]),p0x[kli])==3))
                                for(int ili=0; ili<4; ili++) if(p4[ili]==il)
                                    for(int kri=0; kri<4; kri++) if(p4[kri]==kr){
                                        if(iri==0 && ili==0) X-=4;
                                        //if((X-2-ili-kri-jmi)%4==0){
                                        if(X-2-ili-kri-jmi>=0 && (X-2-ili-kri-jmi)%4==0){
                                           // System.out.println(Arrays.toString(p0x));
                                           // System.out.println(Arrays.toString(pxL));
                                           // System.out.println(Arrays.toString(p4));

                                           // System.out.printf("%d %d %d %d %d\n",ili,iri,jmi,kli,kri);
                                            return "YES";
                                        }
                                    }
                        }
                        if(iri!=0 && kli!=L) keepgo=false;
                    }
            }

        return "NO";
    }
}


