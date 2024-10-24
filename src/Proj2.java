import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Proj2 {
    public static void main(String[] args) throws IOException {
        // Check if correct number of command-line arguments is provided
        if (args.length != 2) {
            System.err.println("Usage: java Proj2 <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // Open the input file
        FileInputStream inputFileNameStream = new FileInputStream(inputFileName);
        Scanner inputFileNameScanner = new Scanner(inputFileNameStream);

        // Read data and store in ArrayList
        ArrayList<Integer> dataset = new ArrayList<>();
        for (int i = 0; i < numLines && inputFileNameScanner.hasNextLine(); i++) {
            dataset.add(Integer.parseInt(inputFileNameScanner.nextLine().trim()));
        }
        inputFileNameScanner.close();

        // Create BST and AVL trees
        BST<Integer> bst = new BST<>();
        AvlTree<Integer> avl = new AvlTree<>();

        // Sort and shuffle dataset for testing both sorted and randomized cases
        ArrayList<Integer> sortedDataset = new ArrayList<>(dataset);
        Collections.sort(sortedDataset);

        ArrayList<Integer> randomizedDataset = new ArrayList<>(dataset);
        Collections.shuffle(randomizedDataset);

        // Time and insert elements into the BST and AVL tree for sorted dataset
        long startTime, endTime, bstInsertTime, avlInsertTime, bstSearchTime, avlSearchTime;

        // Insert sorted dataset into BST
        startTime = System.nanoTime();
        for (int value : sortedDataset) {
            bst.insert(value);
        }
        endTime = System.nanoTime();
        bstInsertTime = endTime - startTime;

        // Insert sorted dataset into AVL
        startTime = System.nanoTime();
        for (int value : sortedDataset) {
            avl.insert(value);
        }
        endTime = System.nanoTime();
        avlInsertTime = endTime - startTime;

        // Search sorted dataset in BST
        startTime = System.nanoTime();
        for (int value : sortedDataset) {
            bst.contains(value);
        }
        endTime = System.nanoTime();
        bstSearchTime = endTime - startTime;

        // Search sorted dataset in AVL
        startTime = System.nanoTime();
        for (int value : sortedDataset) {
            avl.contains(value);
        }
        endTime = System.nanoTime();
        avlSearchTime = endTime - startTime;

        // Print timing results
        System.out.println("Sorted Dataset Insertion Times:");
        System.out.println("BST Insert Time: " + bstInsertTime + " ns");
        System.out.println("AVL Insert Time: " + avlInsertTime + " ns");
        System.out.println("Sorted Dataset Search Times:");
        System.out.println("BST Search Time: " + bstSearchTime + " ns");
        System.out.println("AVL Search Time: " + avlSearchTime + " ns");

        // Save results to output.txt
        FileOutputStream outputStream = new FileOutputStream("output.txt", true);
        String result = numLines + "," + bstInsertTime + "," + avlInsertTime + "," + bstSearchTime + "," + avlSearchTime + "\n";
        outputStream.write(result.getBytes());
        outputStream.close();

        // Repeat for randomized dataset

        // Time and insert elements into BST and AVL tree for randomized dataset
        // Insert randomized dataset into BST
        startTime = System.nanoTime();
        for (int value : randomizedDataset) {
            bst.insert(value);
        }
        endTime = System.nanoTime();
        bstInsertTime = endTime - startTime;

        // Insert randomized dataset into AVL
        startTime = System.nanoTime();
        for (int value : randomizedDataset) {
            avl.insert(value);
        }
        endTime = System.nanoTime();
        avlInsertTime = endTime - startTime;

        // Search randomized dataset in BST
        startTime = System.nanoTime();
        for (int value : randomizedDataset) {
            bst.contains(value);
        }
        endTime = System.nanoTime();
        bstSearchTime = endTime - startTime;

        // Search randomized dataset in AVL
        startTime = System.nanoTime();
        for (int value : randomizedDataset) {
            avl.contains(value);
        }
        endTime = System.nanoTime();
        avlSearchTime = endTime - startTime;

        // Print timing results
        System.out.println("Randomized Dataset Insertion Times:");
        System.out.println("BST Insert Time: " + bstInsertTime + " ns");
        System.out.println("AVL Insert Time: " + avlInsertTime + " ns");
        System.out.println("Randomized Dataset Search Times:");
        System.out.println("BST Search Time: " + bstSearchTime + " ns");
        System.out.println("AVL Search Time: " + avlSearchTime + " ns");

        // Save results to output.txt
        result = numLines + "," + bstInsertTime + "," + avlInsertTime + "," + bstSearchTime + "," + avlSearchTime + "\n";
        outputStream = new FileOutputStream("output.txt", true);
        outputStream.write(result.getBytes());
        outputStream.close();
    }
}

// BST class implementation
class BST<T extends Comparable<T>> {
    private class Node {
        T data;
        Node left, right;

        Node(T data) {
            this.data = data;
            left = right = null;
        }
    }

    private Node root;

    public BST() {
        root = null;
    }

    public void insert(T value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node root, T value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }
        if (value.compareTo(root.data) < 0)
            root.left = insertRec(root.left, value);
        else if (value.compareTo(root.data) > 0)
            root.right = insertRec(root.right, value);
        return root;
    }

    public boolean contains(T value) {
        return containsRec(root, value);
    }

    private boolean containsRec(Node root, T value) {
        if (root == null) {
            return false;
        }
        if (value.compareTo(root.data) < 0)
            return containsRec(root.left, value);
        else if (value.compareTo(root.data) > 0)
            return containsRec(root.right, value);
        else
            return true;
    }
}
