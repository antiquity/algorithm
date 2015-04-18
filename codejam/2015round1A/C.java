import java.io.*;
import java.util.*;

public class C{
    int[][] map= new int[3000][3000];
    void solver(int N, Point[] p){
        Line l;
        int a,b,c;
        int min,temp;
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
                map[i][j]=temp;
                map[j][i]=temp;
            }
            min=N-1;
            for(int j=0; j<N; j++) if(j!=i && map[i][j]<min)
                min=map[i][j];
            System.out.println(min);
        }
    }
    void betterSolver(int N, Point[] p){
        List<Double> lst = new ArrayList<Double>();
        double theta;
        int res=0,temp,cnt;
        for(int i=0; i<N; i++){
            lst.clear();
            for(int j=0; j<N; j++) if(j!=i){
                theta=Math.atan2(p[j].y-p[i].y, p[j].x-p[i].x)/Math.PI;
                lst.add(theta);
                lst.add(theta+2);
            }
            res=N-1;
            Collections.sort(lst);
            //System.out.println(lst);
            for(int j=0; j<lst.size() && (theta=lst.get(j))<1; j++){
                temp=j+1;
                while(temp<lst.size()
                        && lst.get(temp)>theta+Math.ulp(theta)
                        && lst.get(temp)<theta+1-Math.ulp(theta))
                    temp++;
                res=Math.min(res,Math.min(temp-j-1, N-2+j+1-temp));
                //System.out.format("theta=%g, j=%d, temp=%d, res=%d\n", theta,j,temp,res);
            }
            System.out.println(res);
        }
    }
    class Line{
        Point p1,p2;
        long a,b,c;
        Line(Point pp1,Point pp2){
            p1=pp1; p2=pp2;
            a = (p2.y-p1.y);
            b=-(p2.x-p1.x);
            c=p1.x*p2.y-p2.x*p1.y;
            if(a<0){ a=-a; b=-b; c=-c;}
            if(a==0 && b<0){ b=-b; c=-c;}
        }
        int onRight(Point p3){
            long d=a*p3.x+b*p3.y-c;
            if(d>0) d=1;
            if(d<0) d=-1;
            return (int)d;
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

