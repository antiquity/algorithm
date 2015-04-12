
import java.util.*;
public class EllysRoomAssignmentsDiv2 {
    public double getProbability(String[] ratings) {
        String large=new String(ratings[0]);
        for(int j=1; j<ratings.length; j++)
            large=large.concat("" + ratings[j]);
        //System.out.println(large);
        
        StringTokenizer st = new StringTokenizer(large);
        int N=st.countTokens();
        int[] r=new int[N];
        int R=(N+19)/20;
        int i=0;
        while(st.hasMoreTokens())
            r[i++]=Integer.parseInt(st.nextToken());
        //System.out.println(i); 
        //System.out.println(Arrays.toString(r));
        int elly=r[0];
        Arrays.sort(r);
        int pos=Arrays.binarySearch(r,elly);
        //System.out.println( N+ " " + R + " " + pos);
        if(R==1) return 1;
        if(pos>=N-R)
            return 0;
        else
            return 1/((double) R);
    }
    public static void main(String[] args) {
        //{"42 911 666 17 13 1 1155 1094 815 5 1000 540"};
        //System.out.println(temp.getProbability(ratings));
    }
}
