import java.io.*;
import java.util.*;

public class A{
    void solve(int T, int[] id, int[] score, int[] time){
        int N = id.length;
        double[][] density= new double[N][3];
        for(int i=0; i<N; i++){
            density[i][0]=((double)score[i])/time[i];
            density[i][1]=i;
            density[i][2]=score[i];
        }
        Arrays.sort(density, new Comparator<double[]>(){
            public int compare(double[] a, double[] b){
                if(a[0]<b[0])
                    return 1;
                else if(a[0]>b[0])
                    return -1;
                else
                    return (int)(a[2]-b[2]);
            }
        });
        //for(double[] xx : density) System.out.format("id=%d, dens=%g;\n",id[(int)xx[1]],xx[0]);
        int ret=0, t=0,i=0,j,parti=-1;
        List<Integer> ids = new ArrayList<Integer>();
        while(t<T && ids.size()<N){
            j=(int)density[i][1];
            if(t+time[j]<=T){
                ret+=score[j];
                ids.add(id[j]);
                t+=time[j];
            }else{
                ret+=Math.round(density[j][0]*(T-t));
                ids.add(id[j]);
                t=T;
                parti=id[j];
            }
            i++;
        }
        System.out.println(ret);
        Collections.sort(ids);
        for(i=0; i<ids.size()-1; i++)
            if(parti==ids.get(i))
                System.out.format("%s*,",ids.get(i));
            else
                System.out.format("%s,",ids.get(i));
        if(parti==ids.get(ids.size()-1))
            System.out.format("%s*\n",ids.get(ids.size()-1));
        else
            System.out.format("%s\n",ids.get(ids.size()-1));
    }
    public static void main(String[] argin) {
        A inst = new A();
        Scanner in = new Scanner(System.in);
        List<Integer> id=new ArrayList<Integer>();
        List<Integer> score=new ArrayList<Integer>();
        List<Integer> time=new ArrayList<Integer>();

        String[] lin;
        int T = in.nextInt();
        while(in.hasNext()){
            lin = in.next().trim().split(",");
            //System.out.println(Arrays.toString(lin));
            id.add(Integer.parseInt(lin[0]));
            score.add(Integer.parseInt(lin[1]));
            time.add(Integer.parseInt(lin[2]));
        }
        inst.solve(T, toArray(id),toArray(score),toArray(time));
    }
    static int[] toArray(List<Integer> aa){
        int[] bb= new int[aa.size()];
        for(int i=0; i< aa.size(); i++)
            bb[i]=aa.get(i);
        return bb;
    }
}

