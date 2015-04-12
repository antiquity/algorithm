// BEGIN CUT HERE

// END CUT HERE
import java.util.*;
public class SwappingDigits {
    int findSmall(String num, int a){
        char t;
        int idx=a;
        t=(char)(num.charAt(a)-1);
        for(int i=a+1; i< num.length(); i++){
            if(a==0){
                if((num.charAt(i)<=t) && num.charAt(i)!='0'){
                    t=num.charAt(i);
                    idx=i;
                }
            }else
                if((num.charAt(i)<=t)){
                    t=num.charAt(i);
                    idx=i;
                }
        }
        return idx;
    }
	public String minNumber(String num) {
            int s=0,t;
            t=findSmall(num,s);
            if( t!=s && num.charAt(t)!='0')
                return swap(num,s,t);
            s++;
            while(s<num.length()){
                t=findSmall(num,s);
                if(t!=s)
                    return swap(num,s,t);
                else
                    s++;
            }
            return num;
	}
        String swap(String num, int s, int t){
            return num.substring(0,s)+num.charAt(t)+num.substring(s+1,t)+
                num.charAt(s)+ num.substring(t+1,num.length());
        }

	public static void main(String[] args) {
		SwappingDigits temp = new SwappingDigits();
		System.out.println(temp.minNumber("596"));
	}
}
