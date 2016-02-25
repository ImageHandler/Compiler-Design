/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package removecomments;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author Yov
 */

public class RemoveComments {
    
    public static void main(String[] args) throws FileNotFoundException {
        
       FileInputStream in = new FileInputStream("input.txt");
       
       
       String comments[] = new String[1000];
      
       for(int i=0;i<1000;i++) {
           comments[i]="";
       }
       //System.out.println();
       int inte=0,rese=0,rea=0,eq=0,comm=0,symb=0,pare=0,iden=0,flag_mul_cmt=0,sing_cmnt=0,fi=0;
       
       try {
           Scanner s = new Scanner(in);
           //PrintWriter pw = new PrintWriter(out);
           String s1="";
           int cm=0;
           
           while(s.hasNextLine()) {
               cm++;
               //System.out.println(cm);
               s1 = s.nextLine();
               //System.out.println(s1);
               char temp[] = s1.toCharArray();
               
               if(flag_mul_cmt == 1) {
                   for(int i=0;i<s1.length();i++) {
                       if(s1.charAt(i) == '*') {
                           comments[comm]+=s1.charAt(i);
                           i++;
                           if(s1.charAt(i) == '/') {
                               comments[comm]+=s1.charAt(i);
                                flag_mul_cmt = 0;
                                comm++;
                           }
                       } else {
                           comments[comm]+=s1.charAt(i);
                       }
                   }
               } else {
//_______________________________________________removingg comments ________________________________________________________________________________
                    for(int i=0; i < s1.length(); i++) {
                        if(s1.charAt(i) == '/') {
                            i++;
                            //comments[comm]+=s1.charAt(i);
                            if(s1.charAt(i) == '/') {
                                sing_cmnt = 1;
                                comments[comm]+="comments found at the line " + cm +" //";
                                for(int j=i+1;j < s1.length(); j++) {
                                    comments[comm]+=s1.charAt(j);
                                }
                                comm++;
                                continue;
                            }
                            if(s1.charAt(i) == '*') {
                                flag_mul_cmt = 1;
                                comments[comm]+="comments found at the line " + cm + " /*";
                                for(int j=i+1;j<s1.length(); j++) {
                                    if(s1.charAt(j) == '*') {
                                        comments[comm]+=s1.charAt(j);
                                        j++;
                                        if(s1.charAt(j) == '/') {
                                            comments[comm]+=s1.charAt(j);
                                            flag_mul_cmt = 0;
                                            comm++;
                                        }
                                    } else {
                                        comments[comm]+=s1.charAt(j);
                                    }
                                }
                            }
                        }
                   }
                   sing_cmnt = 0;
               }
           }
           
         
           
           System.out.println("Comments are : ");
           
           for(int i=0;i<comm; i++) {
               System.out.println(comments[i]);
           }
           System.out.println();
           
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
    }
}