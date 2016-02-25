/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Yov
 */
public class First {
    /**
     * @param args the command line arguments
     */
    
    public static boolean isInt(String str)  
    {  
      try  
      {  
        int d = Integer.parseInt(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;
    }
    
    public static boolean isReal(String str)  
    { 
      try  
      {  
        int d = Integer.parseInt(str); 
      }
      catch(NumberFormatException nfe)  
      { 
        try {
            double d1 = Double.parseDouble(str);
        } 
        catch (NumberFormatException nf) {
            return false;
        }
      }  
      return true;
    }
    
    public static int isKeyword(String str, String keywords[], String reserved[]) {
        
        int n = keywords.length;
        
        for(int i=0; i < n; i++) {
            if(str.contains(keywords[i])) {
                for(int j = 0; j < reserved.length;j++) {
                    if(keywords[i].equals(reserved[j])) {
                        return -1;
                    }
                }
                return i;
            }
        }
        return -1;
    }
    public static int isReserved(String key, String[] reserved) {
        for(int i=0;i<reserved.length;i++) {
            if(key.equals(reserved[i])){
                return i;
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
       FileInputStream in = new FileInputStream("input.txt");
       
       String keywords[] = {"abstract","void","return","class", "assert", "boolean", "int","float", "double", "String", "short", "long","char","do","while","for","else", "if","throws"};
       
       String identifiers[] = new String[1000];
       String reserved[] = new String[1000];
       String integers[] = new String[1000];
       String real[] = new String[1000];
       String comments[] = new String[1000];
       String symbols[] = new String[1000];
       String parenthesis[] = new String[1000];
       String finall[] = new String[1000];
       //String comments[] = new String[100];
       for(int i=0;i<1000;i++) {
           identifiers[i]="";
           reserved[i]="";
           integers[i]="";
           real[i]="";
           comments[i]="";
           symbols[i]="";
           parenthesis[i]="";
           finall[i]="";
       }
       
       int plus=0;
       int minus=0;
       int mul=0;
       int div=0;
       int mod=0;
       
      
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
                    
                   if(sing_cmnt == 0 && flag_mul_cmt == 0) {
                       
                        for(int i=0; i<s1.length(); i++) {
                            if(s1.charAt(i) == '+') {
                                plus++;
                            }
                            if(s1.charAt(i) == '-') {
                                minus++;
                            }
                            if(s1.charAt(i) == '*') {
                                mul++;
                            }
                            if(s1.charAt(i) == '/') {
                                div++;
                            }
                            if(s1.charAt(i) == '%') {
                                mod++;
                            }
                            if(s1.charAt(i) == '=') {
                                eq++;
                            }
                        }
                   }
                   
                   String[] tokens = s1.split("=|\\*|\\+|\\ |\\-|\\,|\\;|\\[]|\\(|\\)");
                   
                   //System.out.println(tokens.length);
                   for(int k=0;k<tokens.length;k++) {
                       finall[fi++] = tokens[k].trim();
                   }
                   sing_cmnt = 0;
               }
           }
           
           for(int i=0;i<fi;i++) {
               System.out.println(finall[i]);
               if(isReserved(finall[i], keywords) != -1) {
                   reserved[rese++] = finall[i];
               } else if(isReal(finall[i]) == true) {
                   real[rea++] = finall[i];
                   if(isInt(finall[i]) == true) {
                       integers[inte++] = finall[i];
                   }
               } else if(!finall[i].equals("{") && !finall[i].equals("}")){
                   identifiers[iden++] = finall[i];
               }
           }
           //pw.close();
           //out.close();
           
           System.out.println("Comments are : ");
           
           for(int i=0;i<comm; i++) {
               System.out.print(comments[i]);
           }
           System.out.println();
           
           System.out.println("Reserved words are : ");
           
           for(int i=0;i<rese; i++) {
               System.out.println(reserved[i]);
           }
           
           System.out.println("Integers are : ");
           
           for(int i=0;i<integers.length;i++) {
               System.out.print(integers[i]+" ");
           }
           
           System.out.println();
           
           
           
           System.out.println("Real numbers are : ");
           
           for(int i=0;i<real.length;i++) {
               System.out.print(real[i]+" ");
           }
           
           
           
           for(int i=0;i<iden;i++) {
               System.out.println(identifiers[i]);
           }
           
           System.out.println();
           
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
    }
}