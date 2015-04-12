import java.util.*;

public class ArcadeManao{
    public int shortestLadder(String[] level, int cr, int cc){
        int N=level.length;
        int M=level[0].length();
        int[][] L=new int[N][M];
        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                if(level[i].charAt(j)=='X')
                    L[i][j]=N-1-i;
        int temp, cal,head;
        boolean sig=true;


        while(sig){
            sig=false;
            for(int i=N-2; i>=0; i--)
                for(int j=0; j<M; j++){
                    if(level[i].charAt(j)=='X'){
                        head=j;
                        while(head>0 && level[i].charAt(head-1)=='X')
                            head--;

                        temp=L[i][j];
                        for(int k=0; k<N-1; k++)
                            if(level[k].charAt(j)=='X'){
                                cal= max(L[k][j],Math.abs(k-i));
                                if(cal<temp) temp=cal;
                            }
                        if(temp<L[i][j]){
                            while(head<M && level[i].charAt(head)=='X'){
                                L[i][head]=temp;
                                head++;
                            }
                            sig=true;
                        }
                    }
                }
        }
        return L[cr-1][cc-1];
    }
    
    static int max(int a, int b){
        return (a>=b ? a:b);
    }

    static void print(String str){
        System.out.print(str);
    }

    static void println(String str){
        System.out.println(str);
    }

    public static void main(String args[]){
        String[] test={"XXXX....", "...X.XXX", "XXX..X..", "......X.", "XXXXXXXX"};
        String[] test2={"XXXX", "...X", "XXXX"};
        String[] test3={"..X..", ".X.X.", "X...X", ".X.X.", "..X..", "XXXXX"};
        String[] test4={"X"};
        String[] test5={"XXXXXXXXXX", "...X......", "XXX.......", "X.....XXXX", "..XXXXX..X", ".........X", ".........X", "XXXXXXXXXX"};
        println("" + (new ArcadeManao()).shortestLadder(test4,1,1));
    }
}
