import java.io.*;
import java.util.*;

public class Infinite{
    public static void main(String[] argin) {
        Infinite inst = new Infinite();
        try{
            BufferedReader in = new BufferedReader(new FileReader(argin[0]));
            //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("out.txt")));
            PrintStream out = System.out;
            String inputLine = in.readLine();
            int T = Integer.parseInt(inputLine.trim());
            String[] strArr;
            int[] p;
            int D;
            for(int ii=1; ii<=T; ii++){
                D=Integer.parseInt(in.readLine().trim());
                p = new int[D];
                strArr=in.readLine().trim().split("[\\s]+");
                for(int i=0; i<D; i++)
                    p[i] = Integer.parseInt(strArr[i]);
                out.printf("Case #%d: %d\n", ii, inst.solver(D,p));
            }
            in.close();
            out.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    int solver(int D, int[] p){
        int max=p[0];
        for(int i=0; i<D; i++) if(p[i]>max)
            max=p[i];
        int op;
        for(int i=1; i<max; i++){
            op=i;
            for(int j=0; j<D; j++)
                op+=(p[j]-1)/i;
            if(op<max) max=op;
        }
        return max;
    }
}


