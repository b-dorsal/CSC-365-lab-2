package pkg365assignnment2;
//Brian Dorsey 2016
//Class to keep track of each datasource's info, URL, and word frequency hashmap.

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pkg365assignnment2.bHashMap.bucket;

public class dataSource {

    private ArrayList<String> badWordList = new ArrayList<String>();
    private ArrayList<String> linkList = new ArrayList<String>();

    private String url;
    private URL sourceURL;

    public String name;

    private bHashMap wordMap = new bHashMap(10000); //custom map of String word, int frequency <key, value>.
    //private BTree groupTree = new BTree();
    private bbTree groupTree = new bbTree(15);

    private double score = 0.0;
    public double lastScore;

    public dataSource(String URLstring, String name) throws Exception { //Constuctor for the dataSource object.
        URL constructURL = new URL(URLstring);
        sourceURL = constructURL;
        url = URLstring;
        this.name = name;

        badWordList.add("the");
        badWordList.add("it");
        badWordList.add("was");
        badWordList.add("are");
        badWordList.add("in");
        badWordList.add("of");
        badWordList.add("i");
        badWordList.add("we");
        badWordList.add("is");
        badWordList.add("and");
        badWordList.add("to");
        badWordList.add("as");
        badWordList.add("too");
        badWordList.add("then");
        badWordList.add("there");
        badWordList.add("also");
        badWordList.add("this");
        badWordList.add("they");
        badWordList.add("when");
        badWordList.add("where");
        badWordList.add("what");
        badWordList.add("why");
        badWordList.add("how");
        badWordList.add("but");
        badWordList.add("or");
        badWordList.add("yet");
        badWordList.add("so");
        badWordList.add("nor");
        badWordList.add("a");
        badWordList.add("for");

        File check = new File("/Users/admin/Desktop/TEMP/" + name);
        System.out.println("File: " + "/Users/admin/Desktop/TEMP/" + name);

        if (check.exists()) {
            System.out.println("Reading from RAM File");
            this.groupTree.readFile("/Users/admin/Desktop/TEMP/" + name);
        } else {
            System.out.println("Reading from Internet");
            getLinks();
            loadSourceData();
            buildToTree();
            this.groupTree.writeFile("/Users/admin/Desktop/TEMP/" + name);
        }
        
    }

    public void writeTreetoFile(String dir) throws IOException {
        this.groupTree.writeFile(dir + this.name);
    }

    private void buildToTree() throws IOException {
        for (int x = 0; x < this.wordMap.table.length; x++) {
            if (this.wordMap.table[x] != null) {
                //this.groupTree.put(this.wordMap.table[x].key, this.wordMap.table[x].val, this.url);
                //System.out.println(this.wordMap.table[x].key + "-" +this.wordMap.table[x].val);
                this.groupTree.insert(this.wordMap.table[x].key, this.wordMap.table[x].val);
            }
        }

        for (String temp : linkList) {

            bHashMap tempMap = new bHashMap(10000);

            Document doc = Jsoup.connect(url).get();
            Elements paragraphs = doc.select("p");
            for (Element p : paragraphs) {
                String inputLine = p.text();
                String nextWord;
                while (inputLine.contains(" ")) {
                    nextWord = (inputLine.substring(0, inputLine.indexOf(' ')).trim()).toLowerCase();
                    inputLine = inputLine.substring(inputLine.indexOf(' ') + 1, inputLine.length()).trim();
                    nextWord = formatWord(nextWord);
                    if (!isBadWord(nextWord)) {
                        tempMap.put(nextWord);
                    }
                }
            }

            //Take the words/frequencies and adds them to the tree for the cooresponding cluster.
            for (int x = 0; x < tempMap.table.length; x++) {
                if (tempMap.table[x] != null) {
                    //this.groupTree.put(this.wordMap.table[x].key, this.wordMap.table[x].val, temp);
                    this.groupTree.insert(this.wordMap.table[x].key, this.wordMap.table[x].val);
                    for (bucket temps = tempMap.table[x]; temps.next != null; temps = temps.next) {
                        //this.groupTree.put(this.wordMap.table[x].key, this.wordMap.table[x].val, temp);
                        this.groupTree.insert(this.wordMap.table[x].key, this.wordMap.table[x].val);
                    }

                }
            }

        }

    }

    public double compareSource(String sURL) throws Exception { // Compares this objects source to the URL provided.
        int[] thisSource = new int[10000];
        int[] compareSource = new int[10000];
        bHashMap unionMap = new bHashMap(10000);

        //Load a new bHashMap that contains all words similar to both sources.
        Document doc = Jsoup.connect(sURL).get();
        Elements paragraphs = doc.select("p");
        for (Element p : paragraphs) {
            String inputLine = p.text();
            String nextWord;
            while (inputLine.contains(" ")) {
                nextWord = (inputLine.substring(0, inputLine.indexOf(' ')).trim()).toLowerCase();
                inputLine = inputLine.substring(inputLine.indexOf(' ') + 1, inputLine.length()).trim();
                nextWord = nextWord.trim();
                nextWord = formatWord(nextWord);
                if (!isBadWord(nextWord)) {
//                    if (this.groupTree.containsKey(nextWord)) {
                    unionMap.put(nextWord);
//
//                    }
                }
            }
        }

        int pos = 0;
        for (int x = 0; x < unionMap.table.length; x++) {
            if (unionMap.table[x] != null) {
                if (!this.groupTree.containsKey(unionMap.table[x].key)) {
                    thisSource[pos] = 0;
                    compareSource[pos] = unionMap.table[x].val;
                    pos++;
                } else {
                    thisSource[pos] = (int) this.groupTree.get(unionMap.table[x].key);
                    compareSource[pos] = unionMap.table[x].val;
                    pos++;
                }
                //System.out.println(unionMap.table[x].key + ":\t" + unionMap.table[x].val + ", " + this.groupTree.get(unionMap.table[x].key));
            }
        }

        double dot = 0.0;
        double denom1 = 0.0;
        double denom2 = 0.0;

        for (int c = 0; c < thisSource.length; c++) {
            dot += thisSource[c] * compareSource[c];
            denom1 += thisSource[c] * thisSource[c];
            denom2 += compareSource[c] * compareSource[c];
        }

        this.score = dot / (Math.sqrt(denom1) * Math.sqrt(denom2));
        return this.score;

    }

    private void loadSourceData() throws Exception { // Loads this sources data into a custom HashMap.
        Document doc = Jsoup.connect(url).get();
        Elements paragraphs = doc.select("p");
        for (Element p : paragraphs) {
            String inputLine = p.text();
            String nextWord;
            while (inputLine.contains(" ")) {
                nextWord = (inputLine.substring(0, inputLine.indexOf(' ')).trim()).toLowerCase();
                inputLine = inputLine.substring(inputLine.indexOf(' ') + 1, inputLine.length()).trim();
                nextWord = nextWord.trim();
                nextWord = formatWord(nextWord);
                if (!isBadWord(nextWord)) {
                    addToWordMap(nextWord);
                    //System.out.println("\t" + nextWord + ": \t" + this.url);
                }
            }
        }
        System.out.println("Done.");
    }

    private boolean isBadWord(String w) {
        //Function to help prevent standard or repetetive words from being added to a map.
        /*Bad Words
        the, it, was, are, in, of, i, we, is, and, to, as, too, then, there, also, this, they, when, where, what, why, how, but, or, yet, so, for, nor, 
         */
        return badWordList.contains(w);
    }

    private String formatWord(String w) { //Function to remove unwanted character from words.
        w = w.replace("(", "");
        w = w.replace(")", "");
        w = w.replace(".", "");
        w = w.replace(",", "");
        return w;
    }

    private void addToWordMap(String word) { // add the word to the map.
        if (wordMap.containsKey(word)) {
            wordMap.put(word);
        } else {
            wordMap.put(word);
        }
    }

    public void getLinks() throws IOException {
        Document doc = Jsoup.connect(this.url).get();
        Elements links = doc.select("a[href]");

        int pos = 0;
        for (Element link : links) {
            if (!linkList.contains(link.attr("abs:href"))) {
                if (isLinkGood(link.attr("abs:href"))) {
                    linkList.add(link.attr("abs:href"));
                    System.out.println("\tlink: " + link.attr("abs:href"));
                    pos++;
                    if (pos == 20) {
                        break;
                    }
                }
            }
        }
    }

    public boolean checkForUpdate() throws MalformedURLException, IOException {
        URL url = new URL(this.url);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

        long date = httpCon.getLastModified();
        if (date != 0) {
            System.out.println(new Date(date));
            //not implemented yet
            //write last updated Date to a file?
            //recall each URL's updated Date and compare to this pulled from site.
            //return true if site has been updated, false if not.
        }
        return false;
    }

    private boolean isLinkGood(String link) {
        if (!link.contains("#")) {
            if (!link.contains(".jpg")) {
                if (!link.contains(".JPG")) {
                    if (!link.contains("File:")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String getURL() {	// returns the source URL string
        return url;
    }

    public boolean hasWord(String word) { // returns boolean true if the source contains the word provided, else false.
        if (wordMap.containsKey(word)) {
            return true;
        } else {
            return false;
        }
    }

    public double getScore() {
        return score;
    }

    public bHashMap copyMap() {
        return this.wordMap;
    }

    public int getWordUsage(String word) {//returns the frequency of the inputed string.
        return wordMap.get(word);
    }
}
