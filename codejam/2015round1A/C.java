import java.io.*;
import java.util.*;

public class C{
    void betterSolver(int N, Point[] p){
        Line l;
        int a,b,c;
        int min,temp;
        int[] map= new int[N];
        Arrays.fill(map,N-1);
        for(int i=0; i<N; i++){
            for(int j=i+1; j<N; j++){
                l=new Line(p[i],p[j]);
                a=0; b=0;
                for(int k=0; k<N; k++) if(k!=i && k!=j){
                    c=l.onRight(p[k]);
                    if(c>0) a++;
                    if(c<0) b++;
                }
                temp=Math.min(a,b);
                map[i]=Math.min(map[i],temp);
                map[j]=Math.min(map[j],temp);
            }
        }
        for(int i=0; i<N; i++){
            System.out.println(map[i]);
        }
    }
    void solver(int N, Point[] p){
        double[] lst=new double[N-1];
        double theta,temp;
        int res=0,ll,ss,k;
        for(int i=0; i<N; i++){
            k=0;
            for(int j=0; j<N; j++) if(j!=i)
                lst[k++]=Math.atan2(p[j].y-p[i].y, p[j].x-p[i].x)/Math.PI;
            res=N-1;
            Arrays.sort(lst);
            //System.out.println(Arrays.toString(lst));
            for(int j=0; j<N-1; j++){
                theta=lst[j];
                ll=0; ss=0;
                for(k=1; k<N-1; k++){
                    temp=lst[(j+k)%(N-1)];
                    if(!((temp>theta && temp-theta-1<-1e-12)
                        || (temp< theta && temp+1-theta<-1e-12)))
                        break;
                    temp=lst[(j-k+N-1)%(N-1)];
                    if(!((temp<theta && theta-temp-1<-1e-12)
                        || (temp>theta && theta-temp+1<-1e-12)))
                        break;
                    ll++;
                }
                res=Math.min(res,ll);
                //System.out.format("theta=%g, j=%d, temp=%d, res=%d\n", theta,j,temp,res);
            }
            System.out.println(res);
        }
    }
    class Line{
        long a,b,c;
        Line(Point p1,Point p2){
            a = (p2.y-p1.y);
            b=-(p2.x-p1.x);
            c=p1.x*p2.y-p2.x*p1.y;
        }
        int onRight(Point p3){
            long d=a*p3.x+b*p3.y-c;
            if(d>0) return 1;
            if(d<0) return -1;
            return 0;
        }
    }
    class Point{
        long x, y;
        Point(long xx, long yy){
            x=xx;
            y=yy;
        }
    }
    public static void main(String[] argin) {
        C inst = new C();
        try{
            BufferedReader in = new BufferedReader(new FileReader(argin[0]));
            PrintStream out = System.out;
            String inputLine = in.readLine();
            String[] strArr;
            Point[] data;
            int N;
            int T = Integer.parseInt(inputLine.trim());
            for(int ii=1; ii<=T; ii++){
                N=Integer.parseInt(in.readLine());
                data=new Point[N];
                for(int i=0; i<N; i++){
                    strArr=in.readLine().trim().split("[\\s]+");
                    data[i]=inst.new Point(Integer.parseInt(strArr[0]),
                            Integer.parseInt(strArr[1]));
                }
                out.printf("Case #%d:\n", ii);
                inst.betterSolver(N,data);
            }
            in.close();
            out.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }
}

