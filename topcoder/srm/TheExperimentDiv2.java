
import java.util.*;

public class TheExperimentDiv2{
    public int[] determineHumidity(int[] inten, int L, int[] leftEnd){
        int[] res = new int[leftEnd.length];
        int[] collected = new int[inten.length];
        int N=inten.length;
        //for(int i=0; i<inten.length; i++)
            //collected[i]=0;
        for(int i=0; i<leftEnd.length; i++){
            for(int j=leftEnd[i]; j< leftEnd[i]+L && j<N ; j++)
                if(collected[j]==0){
                    collected[j]=1;
                    res[i]+=inten[j];
                }
        }
        return res;
    }

    static void print(String str){
        System.out.print(str);
    }

    static void println(String str){
        System.out.println(str);
    }

    static void main(String args[]){
    }
}
