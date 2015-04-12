// BEGIN CUT HERE

// END CUT HERE
import java.util.*;
public class IDNumberVerification {
    public String verify(String id, String[] regionCodes) {
        String reg=id.substring(0,6);
        System.out.println(reg+" vs");
        boolean match=false;
        for(int i=0; i<regionCodes.length; i++){
            System.out.println(regionCodes[i]);
            if(reg.equals(regionCodes[i]))
                match=true;
        }
        if(match)
            System.out.println("region good");
        else
            return "Invalid";

        int year=Integer.parseInt(id.substring(6,10));
        int mon=Integer.parseInt(id.substring(10,12));
        int day=Integer.parseInt(id.substring(12,14));

        if( ! isGoodBirth(year, mon, day))
            return "Invalid";
        System.out.println("birthday good");

        int rand=Integer.parseInt(id.substring(14,17));
        String str;
        if(rand==0)
            return "Invalid";
        if(rand % 2==1)
            str="Male";
        else
            str="Female";
        System.out.println("is "+str);

        if( checksumGood(id) )
            return str;
        else
            return "Invalid";

    }
    boolean checksumGood(String num){
        char[] n=num.substring(0,17).toCharArray();
        int res=0,m;
        for(int i=0; i< n.length; i++)
            res= (res*2+n[i]-'0')%11;
        if(num.charAt(17)=='X')
            m=10;
        else
            m=Integer.parseInt(num.substring(17,18));

        if((res*2+m)%11==1)
            return true;
        else
            return false;
    }
    boolean isGoodBirth(int year,int mon, int day){
        if(year>2011 || year < 1900)
            return false;
        switch (mon) {
            case 1: case 3: case 5:
            case 7: case 8: case 10:
            case 12:
                if(day<1 || day>31)
                    return false;
                break;
            case 4: case 6:
            case 9: case 11:
                if(day<1 || day>30)
                    return false;
                break;
            case 2:
                if (((year % 4 == 0) && !(year % 100 == 0))
                        || (year % 400 == 0)){
                    if(day<1 || day>29)
                        return false;
                }else
                    if(day<1 || day>28)
                        return false;
                break;
            default:
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] str={"320683"};
        IDNumberVerification temp = new IDNumberVerification();
        System.out.println(temp.verify("320683198709100231",str));
        System.out.println(temp.verify("320683198701020448",str));
    }
}
