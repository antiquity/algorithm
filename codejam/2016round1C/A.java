import java.io.*;
import java.util.*;
public class A{
    static java.io.PrintStream out=System.out;
    class Pair{
        int n;
        char c;
        Pair(int a, char b){
            n=a; c=b;
        }
    }
    void solver(int N, int[] P){
        PriorityQueue<Pair> pq=new PriorityQueue<>(new Comparator<Pair>(){
            public int compare(Pair a, Pair b){
                return b.n-a.n;
            }
        });
        int count=0, i=0;
        for(int x:P){
            count+=x;
            pq.offer(new Pair(x,(char)(i+'A')));
            i++;
        }
        while(count>0){
            int max1=(count-1)/2, max2=(count-2)/2;
            Pair a=pq.poll(), b=pq.poll();
            if(Math.max(0,a.n-max2)+Math.max(0,b.n-max2)<=2 && (pq.isEmpty() || pq.peek().n<=max2)){
                if(Math.max(0,a.n-max2)==2){
                    out.format("%c%c",a.c,a.c);
                    a.n-=2;
                    count-=2;
                }else{
                    if(a.n>0){
                        out.print(a.c);
                        a.n--;
                        count--;
                    }
                    if(b.n>0){
                        out.print(b.c);
                        b.n--;
                        count--;
                    }
                }
            }else if(Math.max(0,a.n-max1)+Math.max(0,b.n-max1)<=1){
                out.format("%c",a.c);
                a.n--;
                count--;
            }
            pq.offer(a); pq.offer(b);

            out.print(" ");

        }
    }

    public static void main(String[] argin) {
        A inst = new A();
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int ii=1; ii<=T; ii++){
            int N=in.nextInt();
            int[] P=new int[N];
            for(int i=0; i<N; i++)
                P[i]=in.nextInt();
            System.out.printf("Case #%d: ", ii);
            inst.solver(N,P);
            out.println();
        }

    }
}

