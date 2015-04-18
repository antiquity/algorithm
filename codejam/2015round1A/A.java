import java.io.*;

public class A{
    int solver1(int N, int[] mi){
        int res=0;
        for(int i=0; i<N-1; i++)
            res+=Math.max(mi[i]-mi[i+1],0);
        return res;
    }
    int solver2(int N, int[] mi){
        int x=0;
        for(int i=0; i<N-1; i++)
            x=Math.max(mi[i]-mi[i+1],x);
        int res=0;
        for(int i=0; i<N-1; i++)
            if(mi[i]>=x) res+=x;
            else res+=mi[i];
        return res;
    }
    public static void main(String[] argin) {
        A inst = new A();
        try{
            BufferedReader in = new BufferedReader(new FileReader(argin[0]));
            PrintStream out = System.out;
            String inputLine = in.readLine();
            String[] strArr;
            int[] data;
            int N;
            int T = Integer.parseInt(inputLine.trim());
            for(int ii=1; ii<=T; ii++){
                N=Integer.parseInt(in.readLine());
                inputLine=in.readLine();
                strArr=inputLine.trim().split("[\\s]+");
                data = new int[N];
                for(int i=0; i<N; i++)
                    data[i]=Integer.parseInt(strArr[i]);
                out.printf("Case #%d: %d %d\n", ii, inst.solver1(N,data), inst.solver2(N,data));
            }
            in.close();
            out.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }
}

