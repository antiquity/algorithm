#include <stdlib.h>

int findKthLargestSub(int* nums, int k, int l, int r){
    int s = nums[rand()%(r-l)+l];
    int i=l,kk=l,j=r,temp;
    while(kk<j){
        if(nums[kk]<s){
            j--; temp=nums[kk]; nums[kk]=nums[j]; nums[j]=temp;
        }else if(nums[kk]>s){
            if(i==kk){ i++; kk++;}
            else{
                temp=nums[kk]; nums[kk]=nums[i]; nums[i]=temp; i++;
            }
        }else kk++;
    }
    if(i>=k) return findKthLargestSub(nums,k,l,i);
    else if(j<k) return findKthLargestSub(nums,k,j,r);
    else return s;
}

int findKthLargest(int* nums, int numsSize, int k) {
    return findKthLargestSub(nums, k, 0, numsSize);
}

