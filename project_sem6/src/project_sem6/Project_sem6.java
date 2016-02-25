/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project_sem6;

/**
 *
 * @author Yov
 */

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Math.ceil;
import java.util.Scanner;

public class Project_sem6 {

    public static int out[][][];

    public static int c, t;
    public static String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

    public static void main(String args[]) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));

        c = sc.nextInt();
        t = sc.nextInt();

        Pair mat[][] = new Pair[c][t];
        
        out = new int[c][t][20];

        int i, j, k;
        for (i = 0; i < c; i++) {
            for (j = 0; j < t; j++) {
                mat[i][j] = new Pair(sc.nextInt(), 0);

                for (k = 0; k < 20; k++) {
                    out[i][j][k] = 0;
                }
            }
        }
        compute(mat, 0);
        print();
    }

    static void print() {
        int i, j, k;

        for (i = 0; i < c; i++) {
            for (j = 0; j < t; j++) {
                for (k = 0; k < 20; k++) {
                    if (out[i][j][k] == 1) {
                        System.out.println("class: " + (i + 1) + " teacher: " + (j + 1) + " at day: " + days[(k / 4)] + " time: " + (k % 4 + 9));
                    }
                }
            }
        }
    }

    static void compute(Pair mat[][], int level) {
        if (level == 20) {
            return;
        }

        int i, j, k;
        boolean ta[] = new boolean[t];

        for (i = 0; i < t; i++) {
            ta[i] = true;
        }

        for (i = 0; i < c; i++) {
            for (j = 0; j < t; j++) {
                if (mat[i][j].first > 0 && ta[j]) {
                    if (ceil((double) mat[i][j].second / 4) * 4 >= level) {
                        continue;
                    }
                    mat[i][j].first--;
                    out[i][j][level] = 1;
                    ta[j] = false;
                    mat[i][j].second = level;
                    break;
                }
            }
        }
        compute(mat, level + 1);
    }
}

class Pair {
    int first, second;
    
    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Teacher {

    String id;
    String name;
    int load;

    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
        load = 0;
    }
}

class Classes {
    String room_no;
    String name;
    
    public Classes(String room_no, String name) {
        this.room_no = room_no;
        this.name = name;
    }
}