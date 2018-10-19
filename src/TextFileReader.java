import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

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
    public StackList<Student> generateStudents() {
        StackList<Student> studentStackList = new StackList<>();
        StackList<String> rawData = readLines();
        String[] studentDayData = null;
        int numOfStudents = rawData.getSize();
        for (int i = 0; i < numOfStudents; i++) {
            String name = null;
            String studentData = rawData.pop();
            String[] studentDayDataWithName = studentData.split("<");
            studentDayData = new String[studentDayDataWithName.length - 1];
            for (int d = 0; d < studentDayDataWithName.length; d++) {
                if (d == 0) {
                    StringBuilder sb = new StringBuilder(studentDayDataWithName[d]);
                    if (sb.lastIndexOf("(") != 0) {
                        sb.delete(0, 3);
                    }
                    else{
                        sb.deleteCharAt(0);
                    }
                    name = sb.toString();
                }
                else {
                    studentDayData[d - 1] = studentDayDataWithName[d];
                }
            }
            String[] freeDays = new String[studentDayData.length];
            String[][] freeTimeSlots = new String[freeDays.length][];
            for (int j = 0; j < studentDayData.length; j++) {
                boolean lastCheck = false;
                if (j == studentDayData.length-1) {
                    lastCheck = true;
                }
                StringBuilder sb = new StringBuilder(studentDayData[j]);
                sb.delete(1, studentDayData[j].length());
                freeDays[j] = sb.toString();
                String[] studentTimeDataWithDay = studentDayData[j].split("\\[");
                freeTimeSlots[j] = new String[studentTimeDataWithDay.length - 1];
                for (int k = 0; k < studentTimeDataWithDay.length - 1; k++) {
                    StringBuilder sb2 = new StringBuilder(studentTimeDataWithDay[k + 1]);
                    if (k + 2 == studentTimeDataWithDay.length && lastCheck) {
                        sb2.delete(studentTimeDataWithDay[k + 1].length() - 3, studentTimeDataWithDay[k + 1].length());
                    }
                    else if (k + 2 == studentTimeDataWithDay.length) {
                        sb2.delete(studentTimeDataWithDay[k + 1].length() - 2, studentTimeDataWithDay[k + 1].length());
                    }
                    else {
                        sb2.deleteCharAt(studentTimeDataWithDay[k + 1].length() - 1);
                    }
                    freeTimeSlots[j][k] = sb2.toString();
                }
            }
            studentStackList.push(new Student(name, freeDays, freeTimeSlots));
        }
        return studentStackList;
    }
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
