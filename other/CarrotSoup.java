import java.util.*;
class CarrotSoup{
    static final int[][] d=new int[][]{{-4,0},{-3,-1},{-2,-2},{-1,-3}};
    public static void main(String[] s){
        int n=50*4;
        double[][] state=new double[n+1][n+1];
        state[n][n]=1;
        for(int t=0; t<n/2; t++){
            int sum=2*n-t*4, carrot;
            for(int soup=Math.max(1,sum-n); soup<=n && (carrot=sum-soup)>=0; soup++){
                // when soup=0 or carrot<0 stop transfer the state
                int count=0;
                for(int i=0; i<4; i++)
                    if(soup+d[i][0]>=0 && carrot+d[i][1]>=0)
                        count++;
                for(int i=0; i<4; i++)
                    if(soup+d[i][0]>=0 && carrot+d[i][1]>=0)
                        state[soup+d[i][0]][carrot+d[i][1]]+=state[soup][carrot]/count;
                state[soup][carrot]=0;
            }
        }
        double soupEmpty=0, soupEmptyWithCarrot=0;
        for(int carrot=1; carrot<=n; carrot++)
            soupEmptyWithCarrot+=state[0][carrot];
        soupEmpty=soupEmptyWithCarrot+state[0][0];
        System.out.println(soupEmptyWithCarrot+" / "+soupEmpty);
        System.out.println(soupEmptyWithCarrot/soupEmpty);
    }
}

