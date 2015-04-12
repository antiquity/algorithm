// BEGIN CUT HERE

// END CUT HERE
import java.util.*;
public class ColorfulRoad {
	public int getMin(String road) {
            System.out.println("called " + road);
            int N=road.length();
            int i=N-2;
            ArrayList<Integer> c = new ArrayList<>();
            int temp;
            if(N==1) return 0;
            while(i>=0){
                switch(road.charAt(N-1)){
                    case 'R':
                        System.out.println(road + "i = " + i);
                        if(road.charAt(i)=='B'){
                            temp=getMin(road.substring(0,i+1));
                            System.out.println(road + "temp = " + temp);
                            if(temp!=-1)
                                c.add(temp+(N-1-i)*(N-1-i));
                        }
                        break;
                    case 'G':
                        System.out.println(road + "i = " + i);
                        if(road.charAt(i)=='R'){
                            temp=getMin(road.substring(0,i+1));
                            System.out.println(road + "temp = " + temp);
                            if(temp!=-1)
                                c.add(temp+(N-1-i)*(N-1-i));
                        }
                        break;
                    case 'B':
                        System.out.println(road + "i = " + i);
                        if(road.charAt(i)=='G'){
                            temp=getMin(road.substring(0,i+1));
                            System.out.println(road + "temp = " + temp);
                            if(temp!=-1)
                                c.add(temp+(N-1-i)*(N-1-i));
                        }
                        break;
                    default:
                        break;
                }
                i--;
            }
            int cost =Integer.MAX_VALUE;
            if(c.size()>0){
                Iterator<Integer> cc = c.iterator();
                while(cc.hasNext()){
                    temp=cc.next();
                    if(temp< cost)
                        cost = temp;
                }
            }else
                cost = -1;

            return cost;
	}
	public static void main(String[] args) {
		ColorfulRoad temp = new ColorfulRoad();
		System.out.println(temp.getMin("RBRRBGGGBBBBR"));
	}
}
