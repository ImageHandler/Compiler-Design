/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first_follow;

/**
 *
 * @author Yov
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

/**
 *
 * @author placements2016
 */
public class First_Follow {
    /**
     * @param args the command line arguments
     */
    static String lhs[] = new String[1000];   
    static String rhs[] = new String[1000];
    static int no_of_productions=0;
    static Hashtable first = new Hashtable();
    static Hashtable follow = new Hashtable();
    static Hashtable non_terminals = new Hashtable();
    
    
    static void calfirstof(String nont) throws Exception {
        if(!non_terminals.containsKey(nont)) {
            first.put(nont, nont.toString());
            return;
        }
        
        if(first.containsKey(nont)) return;
        
        String temp;
        for(int i=0; i < no_of_productions; i++) {
            System.out.println( nont + " ->>> " + lhs[i] );
            if(lhs[i].equals(nont)){
                if(rhs[i].length() == 1) {
                    calfirstof(rhs[i]);
                    temp="";
                    if(first.containsKey(nont)) {
                        temp = first.get(nont).toString();
                        first.remove(nont);
                    }
                    first.put(nont, temp+first.get(rhs[i]));
                    //System.out.println(first);
                } else {
                    System.out.println("calling first " + i);
                    calfirst(i);
                    System.out.println("calling done first " + i);
                    temp="";
                    if(first.containsKey(nont)) {
                        temp = first.get(nont).toString();
                        first.remove(nont);
                    }
                    first.put(nont, temp+first.get(rhs[i]));
                }
            }
        }
        return;
    }
    
    static void calfirst (int i) throws Exception {
        System.out.println(rhs[i]);
        if(first.containsKey(rhs[i])) return;
        
        if(rhs[i].length() == 1) {
            calfirstof(rhs[i]);
        } else {
            String prev="";
            for(int j=0;j< rhs[0].length();j++){
                System.out.println("here " + rhs[0].charAt(j));
                String temp = "";
                System.out.println("here " + temp + rhs[0].charAt(j));
                System.out.println("callZing firstof " + temp + rhs[0].charAt(j) );
                calfirstof(temp + rhs[0].charAt(j));
                System.out.println(first);
                System.out.println("calling done firstof " + temp + rhs[0].charAt(j));
                prev+= first.get(temp + rhs[0].charAt(j)).toString();
                if(!first.get(temp + rhs[0].charAt(j)).toString().contains("_")) {
                    break;
                }
            }
            first.put(rhs[i], prev);
        }
    }
    public static void main(String[] args) throws FileNotFoundException, Exception {
        File f = new File ("input.txt");
        Scanner sc = new Scanner(f);
        while(sc.hasNext()) {
            String line = sc.nextLine();
            String parts[] = line.split("->");
            
            String rhss[] = parts[1].trim().split("/");
            for(int i=0;i<rhss.length;i++) {
                lhs[no_of_productions] = parts[0].trim();
                rhs[no_of_productions] = rhss[i].trim();
                no_of_productions++; 
            }
           
        }
        for(int i=0;i<no_of_productions;i++) {
            non_terminals.put(lhs[i], "bfruf");
        }
        System.out.println("This is our grammer :");
        
        for(int i=0;i<no_of_productions;i++) {
            System.out.println(lhs[i] + " -> " + rhs[i]);
        }
        calfirst(0);
        System.out.println(first);
        System.out.println("now here");
        for(int i=0;i<no_of_productions;i++) {
            if(!first.containsKey(lhs[i])) {
                calfirstof(lhs[i]);
            }
            System.out.println(first);
        }
        System.out.println(first);
    }
    
}

//First and follow