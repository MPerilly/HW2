import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TextFileReader {
    String directory;
    String fileName;
    String p;
    Path path;
    Scanner reader = null;
    File readFile;
    char[] delimiterList = {'(', '<', '[', ']', '>', ')'};
    char[] delimiterListO = {'(', '<', '[' };
    char[] delimiterListC = {')', '>', ']'};
    public TextFileReader(String dir, String fName) {
        directory = dir;
        fileName = fName;
        p = directory + fileName + ".txt";
        readFile = new File(p);
        path = Paths.get(p);
    }
    //Methods:
    public StackList<String> readLines() {
        String s = null;
        StackList<String> students = new StackList<>();
        try {
            reader = new Scanner(readFile);
            reader.useDelimiter("\\)");
            while (reader.hasNext()) {
                s = reader.next();
                s = s +")";
                if (checkData(s)) {
                    students.push(s);
                }
            }
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return students;
    }
    private boolean checkData(String s) {
        StackList<Character> dataCheck = new StackList<>();
        char [] presentDelimiters = checkStringForDelimiters(s);
        if (presentDelimiters.length < 6) {
            return false;
        }
        for (int i = 0; i < presentDelimiters.length; i++) {
            if (checkArray(presentDelimiters[i], delimiterListO)) {
                dataCheck.push(presentDelimiters[i]);
            }
            else if (checkArray(presentDelimiters[i], delimiterListC)) {
                if (dataCheck.isEmpty()) {
                    return false;
                }
                else {
                    switch(dataCheck.pop()) {
                        case '(':
                            if (presentDelimiters[i] != delimiterListC[0]) {
                            return false;
                            }
                            else {
                                break;
                            }
                        case '<':
                            if (presentDelimiters[i] != delimiterListC[1]) {
                            return false;
                            }
                            else {
                                break;
                            }
                        case '[':
                            if (presentDelimiters[i] != delimiterListC[2]) {
                            return false;
                            }
                            else {
                                break;
                            }
                    }
                }
            }
        }
        if (dataCheck.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }
    private char[] checkStringForDelimiters(String s) {
        int delimiterCount = 0;
        for (int i = 0; i < s.length(); i++){
            if (checkArray(s.charAt(i), delimiterList)){
                delimiterCount++;
            }
        }
        char[] presentDelimiters = new char[delimiterCount];
        int delimiterIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            if (checkArray(s.charAt(i), delimiterList)) {
                presentDelimiters[delimiterIndex] = s.charAt(i);
                delimiterIndex++;
            }
        }
        return presentDelimiters;
    }
    private boolean checkArray(char e, char[] check) {
        boolean contains = false;
        for (int i = 0; i < check.length; i++) {
            if (check[i] == e) {
                contains = true;
                break;
            }
        }
        return contains;
    }
}
