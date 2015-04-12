import java.io.*;

public class AStanding{
    int solver(int Sm, char[] data){
        int d=data[0]-'0';
        int res=0;
        int num=d;
        for(int i=1; i<=Sm; i++){
            d=data[i]-'0';
            if(num<i){
                res+=i-num;
                num+=(d+i-num);
            }else{
                num+=d;
            }
        }
        return res;
    }
    public static void main(String[] argin) {
        AStanding inst = new AStanding();
        try{
            BufferedReader in = new BufferedReader(new FileReader(argin[0]));
            //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("out.txt")));
            PrintStream out = System.out;
            String inputLine = in.readLine();
            String[] strArr;
            char[] data;
            int Sm;
            int T = Integer.parseInt(inputLine.trim());
            for(int ii=1; ii<=T; ii++){
                inputLine=in.readLine();
                strArr=inputLine.trim().split("[\\s]+");
                Sm = Integer.parseInt(strArr[0]);
                data = strArr[1].toCharArray();
                out.printf("Case #%d: %d\n", ii, inst.solver(Sm,data));
            }
            in.close();
            out.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }
}


/*
String[] strArr;
int[] data;
int T = Integer.parseInt(inputLine.trim());
for(int ii=0; ii<T; ii++){
    inputLine=in.readLine();
    strArr=inputLine.trim().split("[\\s]+");
    data = new int[strArr.length];
    for(int i=0; i<strArr.length; i++)
        data[i]=Integer.parseInt(strArr[i]);
    out.printf("Case #%d: %d\n", ii, inst.solver());
}
*/

