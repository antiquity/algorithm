public class FoxStones{
public int getCount(int N, int M, int[] sx, int[] sy){
    int[][] mtx=new int[M][N];
    int lens=sx.length;
    int[] sz=new int[lens];
    long count=1;
    int temp,test;
    for(int i=0; i< M; i++){
        for(int j=0; j<N; j++){
            if(mtx[i][j]!=1){
                for(int k=0; k< lens; k++)
                    sz[k]=dist(i,j,sx[k]-1,sy[k]-1);
                temp=0;
                for(int ii=i; ii<M; ii++){
                    for(int jj=0; jj<N; jj++){
                        test=1;
                        for(int k=0; k< lens; k++)
                            if(sz[k]!=dist(ii,jj,sx[k]-1,sy[k]-1)){
                                test=0;
                                break;
                            }
                        if(test==1){
                            temp++;
                            mtx[ii][jj]=1;
                        }
                    }
                }
                count=count*fact(temp)%(1000000009L);  
                System.out.println(i+" "+j+" "+temp+" "+fact(temp)+" "+count);
            }
        }
    }
    return (int)count;
}

int dist(int a, int b, int c, int d){
    int x=Math.abs(a-d);
    int y=Math.abs(b-c);
    return ((x>y)? x:y);
}

long fact(int x){
    long re=1;
    for(int i=2; i<=x; i++)
        re=re*i%(1000000009);
    return re;
}

public static void main(String[] args){
    FoxStones test=new FoxStones();
    int[] sx={5,6,7,8,9,10};
    int[] sy={11,12,13,14,15,16};
    test.getCount(12, 34,sx,sy);
}
}
