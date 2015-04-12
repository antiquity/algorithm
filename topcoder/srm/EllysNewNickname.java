
import java.util.*;

public class EllysNewNickname {
	public int getLength(String nickname){
            char[] set={'a','e','i','o','u','y'};
            int len=nickname.length();
            int test=0;
            char temp;
            boolean pre, now=false;

            for(int i=0; i<nickname.length(); i++){
                pre=now;
                temp=nickname.charAt(i);

                test=(Arrays.binarySearch(set,temp));
                now=test>=0;
                //System.out.println(i+" " + test + " "  + now);
                if(pre && now) len--;
            }
            return len;
	}
	public static void main(String[] args) {
		EllysNewNickname temp = new EllysNewNickname();
                //System.out.println(temp.getLength(String nickname));
	}
}
