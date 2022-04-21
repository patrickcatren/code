import java.util.*;
/**
* This class takes a file as input, then replaces specified places with randomly
* selected nouns, verbs, or adjectives to create a MadLibs style result.
* @author MIDN Patrick Catren
*/
public class MidLibs {
/**
* This function replaces words from the user given file and outputs
* the results.
* @param args The default arg, replaced by the file name.
*/
public static void main(String[] args) {
    if(args.length == 0) {
        System.out.println("usage: java MidLibs <filename>");
        System.exit(1);
    }

    String[] stringarray = WordRead.get(args[0]);
    String[] nouns = WordRead.get("nouns.txt");
    String[] verbs = WordRead.get("verbs.txt");
    String[] adjectives = WordRead.get("adjectives.txt");
    Random rand = new Random(890);
    for(int i = 0; i < stringarray.length; i++) {
        if(stringarray[i].equals("@nounp")) {
            stringarray[i] = nouns[rand.nextInt(nouns.length + 1)]+"s";
        }
        if(stringarray[i].equals("@noun")) {
            stringarray[i] = nouns[rand.nextInt(nouns.length + 1)];
        }
        if(stringarray[i].equals("@verb")) {
            stringarray[i] = verbs[rand.nextInt(verbs.length + 1)];
        }
        if(stringarray[i].equals("@adjective")) {
            stringarray[i] = adjectives[rand.nextInt(adjectives.length + 1)];
        }
    }
    Formatter.writeInColumns(stringarray, 35);
}
}