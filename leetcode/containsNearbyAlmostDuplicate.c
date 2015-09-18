
#include <stdlib.h>
#include <stdio.h>
#define MIN(x,y) ((x)>(y)?(y):(x))
#define MAX(x,y) ((x)>(y)?(x):(y))

typedef struct treeNode {
    int data;
    struct treeNode *left;
    struct treeNode *right;
}treeNode;

treeNode* FindMin(treeNode *node){
    if(node==NULL) return NULL;
    if(node->left) /* Go to the left sub tree to find the min element */
        return FindMin(node->left);
    else 
        return node;
}

treeNode * Insert(treeNode *node,int data) {
    if(node==NULL) {
        treeNode *temp;
        temp = (treeNode *)malloc(sizeof(treeNode));
        temp -> data = data;
        temp -> left = temp -> right = NULL;
        return temp;
    }

    if(data >(node->data))
        node->right = Insert(node->right,data);
    else if(data < (node->data))
        node->left = Insert(node->left,data);
    return node;
}

treeNode * Delete(treeNode *node, int data) {
    treeNode *temp;
    if(node==NULL)
        printf("Element Not Found");
    else if(data < node->data)
        node->left = Delete(node->left, data);
    else if(data > node->data)
        node->right = Delete(node->right, data);
    else{
        if(node->right && node->left){
            temp = FindMin(node->right);
            node -> data = temp->data; 
            node -> right = Delete(node->right,temp->data);
        }else{
            temp = node;
            if(node->left == NULL)
                node = node->right;
            else if(node->right == NULL)
                node = node->left;
            free(temp); /* temp is longer required */ 
        }
    }
    return node;
}

bool Find(treeNode *node, int data, int t) {
    if(node==NULL)  return false;
    if(data > node->data+t)
        return Find(node->right,data,t);
    else if(data < node->data-t)
        return Find(node->left,data,t);
    else
        return true;
}
void DelTree(treeNode* node){
    if(node){
        DelTree(node->left);
        DelTree(node->right);
        free(node);
    }
}

void PrintInorder(treeNode *node) {
    if(node==NULL) return;
    PrintInorder(node->left);
    printf("%d ",node->data);
    PrintInorder(node->right);
}

bool containsNearbyAlmostDuplicate(int* nums, int numsSize, int k, int t) {
    treeNode *root = NULL;
    root = Insert(root, nums[0]);
    bool ret=false;
    for(int i=1; i<numsSize; i++){
        if(i>k) root=Delete(root,nums[i-k-1]);
        if(Find(root,nums[i],t)){
            ret=true;
            break;
        }
        root = Insert(root, nums[i]);
    }
    DelTree(root);
    return ret;
}

int main(){
    int nums[]={-1,2147483647};
    printf("return %d\n",containsNearbyAlmostDuplicate(nums,2,1,2147483647));
}

