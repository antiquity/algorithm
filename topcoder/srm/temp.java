// BEGIN CUT HERE

// END CUT HERE
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;
import java.util.*;

public class LittleElephantAndString {
    public int getNumber(String A, String B) {
        int n=A.length();
        int[] s=new int[26];

        for(int i=0; i<26; i++) s[i]=0;
        for(int i=0; i<n; i++){
            s[A.charAt(i)-'A']++; s[B.charAt(i)-'A']--;
        }
        for(int i=0; i<26; i++) if(s[i]!=0) return -1;

        int a,b=n-1;
        for(a=n-1; a>=0; a--)
            if(A.charAt(a)==B.charAt(b)) b--;
        return n-1-b;
    }
    public static void main(String[] args) {
        LittleElephantAndString temp = new LittleElephantAndString();
        //System.out.println(temp.getNumber(String A, String B));
    }
}
