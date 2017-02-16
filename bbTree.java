package pkg365assignnment2;
//Brian Dorsey 2016

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class bbTree {

    private Node root;
    private int T; //2T is the maximum number of childen a node can have
    //BTree constructor, sets the root as a blank node.
    //
    public bbTree(int t) {
        root = new Node(t);
        T = t;
    }

    public boolean containsKey(String skey) {
        if (get(skey) == -1) {
            return false;
        }
        return true;
    }

    public void insert(String newKey, int freq) {
        if (root.isFull()) {//Split root if full;
            split();
        }
        root.put(newKey, freq);//then use put to insert value/freq directly into a node
    }

    public void writeFile(String dir) throws FileNotFoundException, IOException {
        RandomAccessFile file = new RandomAccessFile(dir, "rw");

        this.buildSeek(root, 0);
        writeFile(root, 0, file);
        file.close();
    }

    private void writeFile(Node rt, int p, RandomAccessFile file) throws IOException {
        Node temp = rt;
        file.seek(p);
        //System.out.print("Node: s:" + temp.seekPos + " n:" + temp.n + " ");//debug
        file.writeInt(temp.seekPos);
        file.writeInt(temp.n);
        file.writeBoolean(rt.isLeaf);
        for (int i = 0; i < temp.n; i++) {
            //System.out.print(temp.key[i].word + ", ");//debug

            file.writeUTF(temp.key[i].word);
            file.writeInt(temp.key[i].freq);
        }
        if (!temp.isLeaf) {
            for (int l = 0; l <= temp.n; l++) {
                //System.out.print(temp.childs[l].seekPos + ", ");//debug
                file.writeInt(temp.childs[l].seekPos);
            }

            for (int i = 0; i <= temp.n; i++) {
                p = p + 1000;

                writeFile(temp.childs[i], p, file);
            }
        }
    }

    public void readFile(String dir) throws FileNotFoundException, IOException {
        RandomAccessFile file = new RandomAccessFile(dir, "rw");
        
        this.root = readFile(this.root, 0, file);
    }

    private Node readFile(Node rt, int seek, RandomAccessFile file) throws IOException {
        
        //System.out.println(seek);
        file.seek(seek);
        Node temp = rt;

        int seekP = file.readInt();
        temp.seekPos = seekP;
        //System.out.println("\tseek: " + seekP);//debug

        int vals = file.readInt();
        //System.out.println("\tvals: " + vals);
        temp.n = vals;
        temp.isLeaf = file.readBoolean();
        //System.out.println(temp.isLeaf);
        for (int x = 0; x < vals; x++) {
            String word = file.readUTF();
            //System.out.print("\t" + word + ", ");
            int freq = file.readInt();
            //System.out.println(freq);

            temp.key[x] = new Data(word, freq);
        }
        if (!temp.isLeaf) {
            for (int s = 0; s <= vals ; s++) {
                temp.childs[s] = new Node(T);
                temp.childs[s].seekPos = file.readInt();
                //System.out.println("found: " + temp.childs[s].seekPos);
            }
//            for (int c = 0; c < vals; c++) {
//                //System.out.println("Next child: " + temp.childs[c].seekPos);
//                temp.childs[c] = readFile(temp.childs[c], temp.childs[c].seekPos, file);
//            }
        }
        
        return temp;

    }

    private void buildSeek(Node rt, int p) {
        Node temp = rt;
        temp.seekPos = p;
        //System.out.println(p);
        if (!temp.isLeaf) {
            for (int i = 0; i <= temp.n; i++) {
                p = p + 1000;
                buildSeek(temp.childs[i], p);
            }
        }
    }

    //get method to be used externally.
    public int get(String Key) {
        searchReturn = -1;
        return get(this.root, Key);
    }
    int searchReturn = -1;

    //get method used for traversing the tree.
    private int get(Node rt, String key) {
//        System.out.println("finding: " + key);
        try {
//            System.out.println("inside");
            for (int x = 0; x < rt.n; x++) {
//                System.out.println("here: " + rt.key[x].word);
                if (rt.key[x].word.equals(key)) {
//                    System.out.println("found: " + rt.key[x].word + ", " + rt.key[x].freq);
                    searchReturn = rt.key[x].freq;
                    return rt.key[x].freq;
                }

            }
            for (int y = 0; y <= rt.n; y++) {
//                System.out.println("here2: " + rt.key[y].word);
                if (AlessB(key, rt.key[y].word)) {
                    rt = rt.childs[y];

                    get(rt, key);

                }
                if (searchReturn != -1) {
                    return searchReturn;
                    //break;
                }
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException | java.lang.NullPointerException er) {
//            System.out.println("Jerry Rig Fix");
        }
        return searchReturn;
    }

    public void split() {
        //setup new nodes
        Node left = new Node(T); //make 2 new nodes to split into.
        Node right = new Node(T);
        left.isLeaf = root.isLeaf;
        right.isLeaf = root.isLeaf;
        left.n = T - 1;//set the number of items in each node
        right.n = T - 1;

        //split and reposition
        int med = T - 1;
        for (int i = 0; i < T - 1; i++) {
            left.childs[i] = root.childs[i];
            left.key[i] = root.key[i];
        }
        left.childs[med] = root.childs[med];
        for (int i = med + 1; i < root.n; i++) {
            right.childs[i - med - 1] = root.childs[i];
            right.key[i - med - 1] = root.key[i];
        }
        right.childs[med] = root.childs[root.n];
        root.key[0] = root.key[med];
        root.n = 1;
        root.childs[0] = left;
        root.childs[1] = right;
        root.isLeaf = false;

    }

    //object to hold each val's information, word/frequency.
    private class Data {

        String word;
        int freq;

        public Data(String w, int f) {//constructor assigns values.
            this.word = w;
            this.freq = f;
        }
    }

    //Node object that 
    private class Node {

        public int seekPos;
        public Data[] key;//list of data points in this Node
        public Node[] childs;//list of this Nodes children
        boolean isLeaf;//if this Node is a leaf Node or not.
        public int n; //number of items in node.
        private int T; //order or the Node

        public Node(int t) {
            T = t;//set order
            isLeaf = true;
            key = new Data[2 * T - 1];//initialize the list of data points.
            childs = new Node[2 * T];//initialize the list of children.
            n = 0;
        }

        public boolean isFull() {//if this Node is full
            return n == (2 * T - 1);
        }

        //Node put method, called by the tree.
        public void put(String newKey, int freq) {
            int t = n - 1;
            if (isLeaf) {
                while ((t >= 0) && (AlessB(newKey, key[t].word))) {
                    key[t + 1] = key[t];
                    t--;
                }
                n++;//increase number of items
                Data d = new Data(newKey, freq);//create new data point with new values
                key[t + 1] = d;
            } else {
                while ((t >= 0) && (AlessB(newKey, key[t].word))) {//see below of AlessB 
                    t--;
                }
                int in = t + 1;
                if (childs[in].isFull()) {

                    n++;//increase number of items
                    childs[n] = childs[n - 1];
                    for (int j = n - 1; j > in; j--) {
                        childs[j] = childs[j - 1];
                        key[j] = key[j - 1];
                    }
                    key[in] = childs[in].key[T - 1];
                    childs[in].n = T - 1;

                    Node temp = new Node(T);//create new temp node
                    for (int l = 0; l < T - 1; l++) {
                        temp.childs[l] = childs[in].childs[l + T];
                        temp.key[l] = childs[in].key[l + T];
                    }

                    temp.childs[T - 1] = childs[in].childs[2 * T - 1];
                    temp.n = T - 1;
                    temp.isLeaf = childs[in].isLeaf;
                    childs[in + 1] = temp;
                    if (AlessB(newKey, key[in].word)) {
                        childs[in].put(newKey, freq);
                    } else {
                        childs[in + 1].put(newKey, freq);
                    }
                } else {
                    childs[in].put(newKey, freq);
                }
            }
        }

    }//class Node

    //I wrote this to simplify the comparing of 2 strings.
    private static boolean AlessB(String A, String B) {
        return A.compareTo(B) < 0;
    }

}//class bbTree
