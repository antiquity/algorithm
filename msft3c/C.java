import java.io.*;
import java.util.*;

public class C{
    void solve(String in, int from, int to){
        if(from<2 || from >36 || to<2 || to >36){
            System.out.println("Invalid Input");
            return;
        }
        int x=0,y;
        for(int i=0; i<in.length(); i++){
            y=toDec(in.charAt(i));
            if(y>=from || y<0){
                System.out.println("Invalid Input");
                return;
            }
            x=toDec(in.charAt(i))+x*from;
        }
        String ret="";
        while(x>0){
            ret=toChar(x%to)+ret;
            x/=to;
        }
        System.out.println(ret);
    }
    public static void main(String[] argin) {
        C inst = new C();
        Scanner in = new Scanner(System.in);

        String[] temp;
        while(in.hasNext()){
            temp=in.next().trim().toLowerCase().split(",");
            inst.solve(temp[0],Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        }
    }
    int toDec(char x){
        if(x<='9' && x>='0') return x-'0';
        return x-'a'+10;
    }
    char toChar(int x){
        if(x<10) return (char)(x+'0');
        return (char)(x-10+'a');
    }
}
