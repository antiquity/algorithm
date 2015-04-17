import java.io.*;

public class DOminous{
    String solver(int x, int r, int c){
        if(r*c%x!=0) return "RICHARD";
        if(x>Math.max(r,c)) return "RICHARD";
        if((x+1)/2 > Math.min(r,c)) return "RICHARD";
        if(x>=7) return "RICHARD";
        if(x==4 && r*c/x<=2) return "RICHARD";
        if(x==5 && r*c/x<=3) return "RICHARD";
        if(x==6 && Math.min(r,c)<=3) return "RICHARD";
        return "GABRIEL";
    }
    public static void main(String[] argin) {
        DOminous inst = new DOminous();
        try{
            BufferedReader in = new BufferedReader(new FileReader(argin[0]));
            //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("out.txt")));
            PrintStream out = System.out;
            String inputLine = in.readLine();
            String[] strArr;
            int X,R,C;
            int T = Integer.parseInt(inputLine.trim());
            for(int ii=1; ii<=T; ii++){
                inputLine=in.readLine();
                strArr=inputLine.trim().split("[\\s]+");
                X = Integer.parseInt(strArr[0]);
                R = Integer.parseInt(strArr[1]);
                C = Integer.parseInt(strArr[2]);
                out.printf("Case #%d: %s\n", ii, inst.solver(X,R,C));
            }
            in.close();
            out.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }
}

