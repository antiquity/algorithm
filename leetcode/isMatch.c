/*
 * =====================================================================================
 *
 *       Filename:  isMatch.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  06/14/2015 03:26:36 PM
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  YOUR NAME (), 
 *   Organization:  
 *
 * =====================================================================================
 */
#include <stdlib.h>
#include <stdio.h>
bool isMatch(const char *s, const char *p) {
    const char* star=NULL;
    const char* ss=s;
    while (*s){
        if(star)
            printf("%c, %c, %c, %c\n",*s,*p,*star,*ss);
        else
            printf("%c, %c,  0, %c\n",*s,*p,*ss);
        if ((*p=='?')||(*p==*s)){s++;p++;continue;} 
        if (*p=='*'){star=p++; ss=s;continue;} 
        if (star){ p = star+1; s=++ss;continue;} 
        return false;
    }
    while (*p=='*'){p++;}
    return !*p;  
}

int main(){
    char* a = "aaaaaba";
    char* b = "*b*";
    printf("result of %s, %s is %d\n",a,b,isMatch(a,b));
    a="bbbbbbbabbaabbabbbbaaabbabbabaaabbababbbabbbabaaabaab";
    b="b*b*ab**ba*b**b***baab";
    printf("result of %s, %s is %d\n",a,b,isMatch(a,b));
    a="ab*cdec"; b = "ab*c";
    printf("result of %s, %s is %d\n",a,b,isMatch(a,b));
    return 0;
}
