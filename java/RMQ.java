//m[i][j] is from i to i+2^j-1
//
//                i..i+2^(j-1)-1    i+2^(j-1) ... i+2^(j-1)+2^(j-1)-1
//                     i+2^(j-1) + 2^(j-1)
//
//so m[i][j]=min(m[i][j-1], m[i+2^(j-1)][j-1])
//
//
//    2^j<=M

public class RMQ {
    int[][] m;
    int[] nums;
    RMQ(int[] nums){
        this.nums=nums;
        int I=nums.length, J=32-Integer.numberOfLeadingZeros(I);
        m = int[I][J];
        for(int i=0; i<I; i++)
            m[i][0]=i;
        for(int j=1; j<J; j++){
            for(int i=0; i<=I-(1<<j); i++){
                m[i][j]=m[i][j-1];
                int t;
                if(nums[t=m[i+(1<<(j-1))][j-1]]<nums[m[i][j]])
                    m[i][j]=t;
            }
        }
    }
    int query(int i, int j){
        int k=0;
        while(i+(1<<k)-1<j) k++;
        k--;
        return Math.min(nums[m[i][k]],nums[j-(1<<k)+1][k]);
    }
}

