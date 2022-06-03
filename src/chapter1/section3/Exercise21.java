package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

public class Exercise21 {
    static public boolean find(LinkedList<String> list, String key) {
        for (String item : list) {
            if (item.equals(key)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.insert("A");
        list.insert("B");
        list.insert("C");
        list.insert("D");

        StdOut.println("Find B result: " + find(list, "B") + " Expected: true");
        StdOut.println("Find Z result: " + find(list, "Z") + " Expected: false");
    }
}
