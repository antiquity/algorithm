import java.util.*;

public class TheTreeAndMan {
    List<Integer>[] map;
    long[] head, leg, arm, feet;
    int N;
    int M=1000000007;
    long dp(int i){
        int ret=0;
        if(feet[i]>0) return head[i];
        for(int child : map[i]) dp(child);
        feet[i]=1;
        long sumSquar=0,sum=0,temp;
        for(int child : map[i]){
            head[i]=(head[i]+head[child])%M;
            arm [i]=(arm [i]+arm[child] )%M;
            leg [i]=(leg [i]+leg[child] )%M;
            feet[i]=feet[i]+feet[child];
            head[i]=(head[i]+arm[child] )%M;
            sumSquar= (sumSquar+feet[child]*feet[child]);
            sum = (sum+feet[child]);
        }
        temp=(sum*sum-sumSquar)/2;
        for(int child : map[i]) if(leg[child]>0){
            arm[i] = ((arm[i] + leg[child]*( (temp+feet[child]*feet[child]-feet[child]*sum)%M  )%M)%M);
        }
        leg[i] = (leg[i]+temp)%M;
        return head[i];
    }
    public int solve(int[] parent) {
        N = parent.length+1;
        map = new ArrayList[N];
        for(int i=0; i<N; i++) map[i]=new ArrayList<Integer>();
        for(int i=0; i<N-1; i++) map[parent[i]].add(i+1);

        head = new long[N];  Arrays.fill(head,0);
        arm  = new long[N];  Arrays.fill(arm ,0);
        leg  = new long[N];  Arrays.fill(leg ,0);
        feet = new long[N];  Arrays.fill(feet,0);
        return (int)dp(0);
    }
    // BEGIN CUT HERE
    int[] totalC;
    int[] parent;
    boolean direct(int i, int j){
        if(i==0) return true;
        int p=parent[j-1];
        while(p!=i && p!=0)
            p=parent[p-1];
        return p==i;
    }
    int np(int i){
        if(i==0) return 0;
        int cnt=1,p=parent[i-1];
        while(p!=0){
            p=parent[p-1];
            cnt++;
        }
        return cnt;
    }
    int rnc(int k){
        if(totalC[k]!=0) return totalC[k];
        int ret=1;
        for(int i=1; i<N; i++)
            if(parent[i-1]==k) ret+=rnc(i);
        totalC[k]=ret;
        return ret;
    }
    public int solve1(int[] parent) {
        this.parent=parent;
        N = parent.length+1;
        map = new ArrayList[N];
        totalC = new int[N];
        int[] nc = new int[N];
        Arrays.fill(nc,0);
        for(int i = 0; i<N-1; i++) nc[parent[i]]++;
        Arrays.fill(totalC,0);
        rnc(0);

        for(int i= 0; i<N; i++) map[i]=new ArrayList<Integer>();
        for(int i = 0; i<N-1; i++) map[parent[i]].add(i+1);

        System.out.println(N);
        //System.out.println(Arrays.toString(parent));
        //System.out.println(Arrays.toString(nc));
        //System.out.println(Arrays.toString(totalC));
        //for(int i=0; i<N; i++){
        //    for(int j=1; j<N; j++)
        //        print(direct(i,j)?'T':'.');
        //    println();
        //}

        long ret=0,head,arm,leg;
        for(int j=1; j<N; j++) if(nc[j]>=3)
            for(int k=j+1; k<N; k++) if(nc[k]>=2 && direct(j,k)){
                head=np(j);
                arm=0;
                for(int l:map[j]) if(l!=k && !direct(l,k))
                    for(int m:map[j])if(l<m && m!=k && !direct(m,k)){
                        arm+=totalC[l]*totalC[m];
                        arm%=M;
                    }
                leg=0;
                for(int l:map[k]) for(int m:map[k])
                    if(l<m){
                        leg+=totalC[l]*totalC[m];
                        leg%=M;
                    }
                ret = (ret+head*arm*leg)%M;

                System.out.format("j=%d, k=%d, head=%d, arm=%d, leg=%d, tot=%d\n",
                        j,k,head,arm,leg,ret);
            }
        return (int)ret;
    }
    void println(Object o) { System.out.println(o); }
    void print (Object o) {System.out.print(o); }
    void println() {System.out.println(); }
    /** begin cut - don't modify this line*/
    public static void main(String[] a) {
        new TheTreeAndMan().runTestCase(0);
        new TheTreeAndMan().runTestCase(1);
        new TheTreeAndMan().runTestCase(2);
        new TheTreeAndMan().runTestCase(3);
        new TheTreeAndMan().runTestCase(4);
        new TheTreeAndMan().runTestCase(5);
        new TheTreeAndMan().runTestCase(7);
        new TheTreeAndMan().runTestCase(8);
        new TheTreeAndMan().runTestCase(6);
    }

    public void runTestCase(int nbr) {
        switch(nbr) {
            case 0 : {
                         checkOutput(solve(new int[] {0,1,1,1,4,4}), 1, 0); break;
            }
            case 1 : {
                         checkOutput(solve(new int[] {0,0,2,2,2,4,0,4,7,2,10,11,9,0,1,0}), 7, 1); break;
            }
            case 2 : {
                         checkOutput(solve(new int[] {0,0,2,2,3,1,0,1,5}), 0, 2); break;
            }
            case 3 : {
                         checkOutput(solve(new int[] {0,0,0,2,3,5,2,5,3,7,7,7,2}), 3, 3); break;
            }
            case 4 : {
                         checkOutput(solve(new int[] {0,0,1,2,2,2,1,4,2,9,0,1,5}), 0, 4); break;
            }
            case 5 : {
                         checkOutput(solve(new int[] {0,0,0,2,4,2,5,6,5,9,5,0,3,8,8,15,0,17,9}), 3, 5); break;
            }
            case 6 : {
                         checkOutput(solve(new int[] {0,0,0,3,0,1,3,5,6,8,2,7,1,9,1,5,13,6,3,7,8,4,2,18,12,11,17,13,10,25,11,26,22,32,32,18,35,33,25,31,24,38,41,34,43,42,32, 43,31,35,47,49,36,35,39,42,36,39,47,53,40,61,60,43,47,61,51,56,67,69,53,53,63,56,71,55,76,73,70,74,67,76,64,80,68,85,78, 69,79,73,70,75,77,81,80,94,85,88,88,99,83,99,93,90,85,100,89,96,96,103,107,96,105,95,100,97,97} ), 9864, 6); break;
            }
            case 7 : {
                         checkOutput(solve(new int[] {0,0,2,2,3,1,0,1,5,4,7,11,11,10,10,13,13,13,5,11,2,8,1,8}), 15, 7); break;
            }
            case 8 : {
                         checkOutput(solve(new int[] {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 16, 16, 17, 17, 18, 18, 19, 19, 20, 20, 21, 21, 22, 22, 23, 23, 24, 24, 25, 25, 26, 26, 27, 27, 28, 28, 29, 29, 30, 30, 31, 31, 32, 32, 33, 33, 34, 34, 35, 35, 36, 36, 37, 37, 38, 38, 39, 39, 40, 40, 41, 41, 42, 42, 43, 43, 44, 44, 45, 45, 46, 46, 47, 47, 48, 48, 49, 49, 50, 50, 51, 51, 52, 52, 53, 53, 54, 54, 55, 55, 56, 56, 57, 57, 58, 58, 59, 59, 60, 60, 61, 61, 62, 62, 63, 63, 64, 64, 65, 65, 66, 66, 67, 67, 68, 68, 69, 69, 70, 70, 71, 71, 72, 72, 73, 73, 74, 74, 75, 75, 76, 76, 77, 77, 78, 78, 79, 79, 80, 80, 81, 81, 82, 82, 83, 83, 84, 84, 85, 85, 86, 86, 87, 87, 88, 88, 89, 89, 90, 90, 91, 91, 92, 92, 93, 93, 94, 94, 95, 95, 96, 96, 97, 97, 98, 98, 99, 99, 100, 100, 101, 101, 102, 102, 103, 103, 104, 104, 105, 105, 106, 106, 107, 107, 108, 108, 109, 109, 110, 110, 111, 111, 112, 112, 113, 113, 114, 114, 115, 115, 116, 116, 117, 117, 118, 118, 119, 119, 120, 120, 121, 121, 122, 122, 123, 123, 124, 124, 125, 125, 126, 126, 127, 127, 128, 128, 129, 129, 130, 130, 131, 131, 132, 132, 133, 133, 134, 134, 135, 135, 136, 136, 137, 137, 138, 138, 139, 139, 140, 140, 141, 141, 142, 142, 143, 143, 144, 144, 145, 145, 146, 146, 147, 147, 148, 148, 149, 149, 150, 150, 151, 151, 152, 152, 153, 153, 154, 154, 155, 155, 156, 156, 157, 157, 158, 158, 159, 159, 160, 160, 161, 161, 162, 162, 163, 163, 164, 164, 165, 165, 166, 166, 167, 167, 168, 168, 169, 169, 170, 170, 171, 171, 172, 172, 173, 173, 174, 174, 175, 175, 176, 176, 177, 177, 178, 178, 179, 179, 180, 180, 181, 181, 182, 182, 183, 183, 184, 184, 185, 185, 186, 186, 187, 187, 188, 188, 189, 189, 190, 190, 191, 191, 192, 192, 193, 193, 194, 194, 195, 195, 196, 196, 197, 197, 198, 198, 199, 199, 200, 200, 201, 201, 202, 202, 203, 203, 204, 204, 205, 205, 206, 206, 207, 207, 208, 208, 209, 209, 210, 210, 211, 211, 212, 212, 213, 213, 214, 214, 215, 215, 216, 216, 217, 217, 218, 218, 219, 219, 220, 220, 221, 221, 222, 222, 223, 223, 224, 224, 225, 225, 226, 226, 227, 227, 228, 228, 229, 229, 230, 230, 231, 231, 232, 232, 233, 233, 234, 234, 235, 235, 236, 236, 237, 237, 238, 238, 239, 239, 240, 240, 241, 241, 242, 242, 243, 243, 244, 244, 245, 245, 246, 246, 247, 247, 248, 248, 249, 249, 250, 250, 251, 251, 252, 252, 253, 253, 254, 254, 255, 255, 256, 256, 257, 257, 258, 258, 259, 259, 260, 260, 261, 261, 262, 262, 263, 263, 264, 264, 265, 265, 266, 266, 267, 267, 268, 268, 269, 269, 270, 270, 271, 271, 272, 272, 273, 273, 274, 274, 275, 275, 276, 276, 277, 277, 278, 278, 279, 279, 280, 280, 281, 281, 282, 282, 283, 283, 284, 284, 285, 285, 286, 286, 287, 287, 288, 288, 289, 289, 290, 290, 291, 291, 292, 292, 293, 293, 294, 294, 295, 295, 296, 296, 297, 297, 298, 298, 299, 299, 300, 300, 301, 301, 302, 302, 303, 303, 304, 304, 305, 305, 306, 306, 307, 307, 308, 308, 309, 309, 310, 310, 311, 311, 312, 312, 313, 313, 314, 314, 315, 315, 316, 316, 317, 317, 318, 318, 319, 319, 320, 320, 321, 321, 322, 322}), 638165655, 8); break;
            }
        }
    }
    final void checkOutput(int mine, int them, int nbr) {
        boolean success = (mine==them);
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append(mine);
            out.append(", Expected: ");
            out.append(them);
        }
        System.out.println(out);
    }
    final void checkOutput(long mine, long them, int nbr) {
        boolean success = (mine==them);
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append(mine);
            out.append(", Expected: ");
            out.append(them);
        }
        System.out.println(out);
    }
    final void checkOutput(double mine, double them, int nbr) {
        boolean success = doubleCompare(mine, them);
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append(mine);
            out.append(", Expected: ");
            out.append(them);
        }
        System.out.println(out);
    }
    private static boolean doubleCompare(double expected, double result){
        double MAX_DOUBLE_ERROR = 1E-9;
        if(Double.isNaN(expected)){
            return Double.isNaN(result);
        }else if(Double.isInfinite(expected)){
            if(expected > 0){
                return result > 0 && Double.isInfinite(result);
            }else{
                return result < 0 && Double.isInfinite(result);
            }
        }else if(Double.isNaN(result) || Double.isInfinite(result)){
            return false;
        }else if(Math.abs(result - expected) < MAX_DOUBLE_ERROR){
            return true;
        }else{
            double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR),
                    expected * (1.0 + MAX_DOUBLE_ERROR));
            double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR),
                    expected * (1.0 + MAX_DOUBLE_ERROR));
            return result > min && result < max;
        }
    }
    final void checkOutput(char mine, char them, int nbr) {
        boolean success = (mine==them);
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append("'");
            out.append(mine);
            out.append("'");
            out.append(", Expected: ");
            out.append("'");
            out.append(them);
            out.append("'");
        }
        System.out.println(out);
    }
    final void checkOutput(String mine, String them, int nbr) {
        boolean success = (mine.equals(them));
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append("\"");
            out.append(mine);
            out.append("\"");
            out.append(", Expected: ");
            out.append("\"");
            out.append(them);
            out.append("\"");
        }
        System.out.println(out);
    }
    final void checkOutput(long[] mine, long[] them, int nbr) {
        boolean success = (Arrays.equals(mine, them));
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append("{");
            for(int x=0;x<mine.length;x++) {
                out.append(mine[x]);
                if(x<mine.length-1) out.append(", ");
            }
            out.append("}");
            out.append(", Expected: ");
            out.append("{");
            for(int x=0;x<them.length;x++) {
                out.append(them[x]);
                if(x<them.length-1) out.append(", ");
            }
            out.append("}");
        }
        System.out.println(out);
    }
    final void checkOutput(char[] mine, char[] them, int nbr) {
        boolean success = (Arrays.equals(mine, them));
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append("{");
            for(int x=0;x<mine.length;x++) {
                out.append(mine[x]);
                if(x<mine.length-1) out.append(", ");
            }
            out.append("}");
            out.append(", Expected: ");
            out.append("{");
            for(int x=0;x<them.length;x++) {
                out.append(them[x]);
                if(x<them.length-1) out.append(", ");
            }
            out.append("}");
        }
        System.out.println(out);
    }
    final void checkOutput(double[] mine, double[] them, int nbr) {
        boolean success = (Arrays.equals(mine, them));
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append("{");
            for(int x=0;x<mine.length;x++) {
                out.append(mine[x]);
                if(x<mine.length-1) out.append(", ");
            }
            out.append("}");
            out.append(", Expected: ");
            out.append("{");
            for(int x=0;x<them.length;x++) {
                out.append(them[x]);
                if(x<them.length-1) out.append(", ");
            }
            out.append("}");
        }
        System.out.println(out);
    }
    final void checkOutput(int[] mine, int[] them, int nbr) {
        boolean success = (Arrays.equals(mine, them));
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append("{");
            for(int x=0;x<mine.length;x++) {
                out.append(mine[x]);
                if(x<mine.length-1) out.append(", ");
            }
            out.append("}");
            out.append(", Expected: ");
            out.append("{");
            for(int x=0;x<them.length;x++) {
                out.append(them[x]);
                if(x<them.length-1) out.append(", ");
            }
            out.append("}");
        }
        System.out.println(out);
    }
    final void checkOutput(String[] mine, String[] them, int nbr) {
        boolean success = (Arrays.equals(mine, them));
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr+1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if(!success) {
            out.append("Got: ");
            out.append("{");
            for(int x=0;x<mine.length;x++) {
                out.append(mine[x]);
                if(x<mine.length-1) out.append(", ");
            }
            out.append("}");
            out.append(", Expected: ");
            out.append("{");
            for(int x=0;x<them.length;x++) {
                out.append(them[x]);
                if(x<them.length-1) out.append(", ");
            }
            out.append("}");
        }
        System.out.println(out);
    }

    /** end cut - don't modify this line*/
    // END CUT HERE
}
