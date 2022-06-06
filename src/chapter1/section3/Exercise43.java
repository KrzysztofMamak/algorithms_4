package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.io.File;

public class Exercise43 {
    public static class Explorer {
        Queue<String> queue = new Queue<>();

        public void read(File file) {
            read(file, 0);
        }

        private void read(File file, int depth) {
            if (file == null || !file.exists()) {
                return;
            }

            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    read(f, depth + 1);
                }
            } else {
                addFileToQueue(file, depth);
            }
        }

        private void addFileToQueue(File file, int depth) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < depth; i++) {
                stringBuilder.append("  ");
            }

            stringBuilder.append(file.getName());

            queue.enqueue(stringBuilder.toString());
        }

        public void printFiles() {
            for (String fileName : queue) {
                StdOut.println(fileName);
            }
        }
    }

    public static void main(String[] args) {
        String path = args[0];
        File folder = new File(path);

        Explorer explorer = new Explorer();

        explorer.read(folder);

        explorer.printFiles();
    }
}
