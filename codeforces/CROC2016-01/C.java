import java.io.*;
import java.util.*;

public class C{
    private long solver(int n, int k, char[] room){
        k++;
        int j=0, count=0;
        for(; j<n; j++){
            if(room[j]=='0'){
                count++;
                if(count==k)
                    break;
            }
        }
        int i=0;
        while(room[i]=='1') i++;
        int min = getMin(i,j,room);
        while(j<n){
            j++;
            while(j<n && room[j]=='1') j++;
            if(j==n) break;
            i++;
            while(room[i]=='1') i++;
            min = Math.min(min,getMin(i,j,room));
        }
        return min;
    }
    private int getMin(int i, int j, char[] room){
        int m = (i+j)/2;
        while(room[m]=='1') m--;
        int ret = (j-m);
        m = (i+j+1)/2;
        while(room[m]=='1') m++;
        ret = Math.min(ret,(m-i));
        return ret;
    }
    public static void main(String[] argin) {
        C inst = new C();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n=in.nextInt(), k = in.nextInt();
            char[] room = in.next().toCharArray();

            System.out.println(inst.solver(n,k,room));
        }
    }
}

