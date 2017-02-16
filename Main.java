package pkg365assignnment2;
//Brian Dorsey 2016

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Loader loading = new Loader();
        loading.show();
        loading.firstLoad();
        List<dataSource> ss = new ArrayList<>();
        //compareUI frameGUI = new compareUI(ss);
        compareUI frameGUI = new compareUI(loading.getSet());
        loading.setVisible(false);
        frameGUI.show();
    }

    //TEST URLs - some URL's to use for testing. 
    //https://en.wikipedia.org/wiki/Population_growth
    //https://en.wikipedia.org/wiki/China
    //http://www.webopedia.com/TERM/C/CPU.html
    //http://www.history.com/
    //http://www.senate.gov/index.htm
    //https://en.wikipedia.org/wiki/Rembrandt
    
}
