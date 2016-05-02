import java.io.*;
import java.util.*;
public class B{
    static java.io.PrintStream out=System.out;
    Res solver(String C, String J){
        char[] c=C.toCharArray(), j=J.toCharArray();
        int compare=0;
        boolean swap=false;
        for(int i=0; i<c.length; i++){
            if(c[i]<j[i]){
                char[] t=c; c=j; j=t;
                String T=C; C=J; J=T;
                compare*=-1;
                swap=!swap;
            }
            if(c[i]==j[i]){
                if(c[i]=='?'){
                    if(compare<0){
                        c[i]='9'; j[i]='0';
                    }else if(compare>0){
                        c[i]='0'; j[i]='9';
                    }else{
                        c[i]='5'; j[i]='5';
                    }
                }
            }else if(c[i]=='?'){
                if(compare<0){
                    c[i]='9';
                }else if(compare>0){
                    c[i]='0';
                }else
                    c[i]=j[i];
            }else{
                if(compare==0){
                    if(i>0 && swap && (minusOne(c,C,i) || addOne(j,J,i))){
                        compare=-1;
                    }else
                        compare=1;
                }
            }
        }
        if(swap){
            char[] t=c; c=j; j=t;
            String T=C; C=J; J=T;
        }
        //System.out.println();
        //System.out.println(C+" "+J);
        //System.out.println(new String(c)+" "+new String(j));
        for(int i=0; i<c.length; i++){
            if(C.charAt(i)=='?' && J.charAt(i)=='?'){
                int x=Math.min(c[i]-'0',j[i]-'0');
                c[i]=(char)(c[i]-x);
                j[i]=(char)(j[i]-x);
                if(c[i]=='1' && minusOne(j,J,i+1)){
                    c[i]='0';
                }
                if(j[i]=='1' && minusOne(c,C,i+1)){
                    j[i]='0';
                }
            }
        }
        //System.out.println(new String(c)+" "+new String(j));
        C=new String(c); J=new String(j);
        long cc=Long.parseLong(C), jj=Long.parseLong(J);
        if(cc>=jj){
            return new Res(cc-jj,C,J);
        }else
            return null;
    }
    private boolean minusOne(char[] b, String B, int i){
        //System.out.format("%s %s", A, B);
        if(i==0) return false;
        i--;
        if(B.charAt(i)=='?'){
            if(b[i]>'0')
                b[i]=(char)(b[i]-1);
            else
                if(minusOne(b,B,i))
                    b[i]='9';
                else
                    return false;
        }else
            return false;
        return true;
    }
    private boolean addOne(char[] a, String A, int i){
        if(i==0) return false;
        i--;
        if(A.charAt(i)=='?'){
            if(a[i]<'9')
                a[i]=(char)(a[i]+1);
            else
                if(addOne(a,A,i)){
                    a[i]='0';
                }else
                    return false;
        }else
            return false;
        return true;
    }
    class Res{
        long diff;
        String a, b;
        Res(long x, String c, String d){
            diff=x; a=c; b=d;
            //out.format("\n%d=%s-%s\n",diff,c,d);
        }
    }
    public static void main(String[] argin) {
        B inst = new B();
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int ii=1; ii<=T; ii++){
            String C=in.next(), J=in.next();
            System.out.printf("Case #%d: ", ii);
            Res a=inst.solver(C, J);
            Res b=inst.solver(J, C);
            if(a==null || (b!=null && b.diff<a.diff))
                out.format("%s %s\n",b.b, b.a);
            else if(a==null || (b!=null && b.diff==a.diff && b.b.compareTo(a.a)<0))
                out.format("%s %s\n",b.b, b.a);
            else if(a==null || (b!=null && b.diff==a.diff && b.b.equals(a.a) && b.a.compareTo(a.b)<=0))
                out.format("%s %s\n",b.b, b.a);
            else
                out.format("%s %s\n",a.a, a.b);
        }
    }
}

