import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.Scanner;

public class ScheduleOpimizer {
    StackList<Student> students;
    TextFileReader reader;
    int[][] studentScheduleCheck;
    String  dir;
    String fname;
    Scanner scanner;
    boolean end;
    public ScheduleOpimizer() {
        scanner = new Scanner(System.in);
        do {
            System.out.println("Type 'C' to close. Please input the directory in which your files are located (ensure to end \n " +
                    "the string with a '/' character):");
            this.end = scanner.hasNext("C");
            if (!end) {
                this.dir = scanner.nextLine();
                System.out.println("Please input the name of the file (do not put the extension .txt, just enter the file name):");
                this.end = scanner.hasNext("C");
                if (!end) {
                    this.fname = scanner.nextLine();
                    try {
                        this.reader = new TextFileReader(this.dir, this.fname);
                        this.students = reader.generateStudents();
                        int [][] optTimes = optimize();
                        for (int i = 0; i < optTimes.length; i++) {
                            String day = null;
                            switch (i) {
                                case 0: day = "Monday";
                                    break;
                                case 1: day = "Tuesday";
                                    break;
                                case 2: day = "Wednesday";
                                    break;
                                case 3: day = "Thursday";
                                    break;
                                case 4: day = "Friday";
                                    break;
                            }
                            System.out.println(day + " , students available in slot:");
                            for (int j = 0; j < optTimes[i].length; j++) {
                                String time = null;
                                if (j == 0) {
                                    time = "0900";
                                }
                                else {
                                    time = (j + 9) +"00";
                                }
                                System.out.print(time + ": " + optTimes[i][j] + ", ");
                            }
                            System.out.println();
                        }
                        System.out.println(getOutput());
                    } catch (FileNotFoundException ex) {
                        System.out.println("Invalid file name or directory. Please try again.");
                    }
                }
            }
        } while (!end);
        System.out.println("Goodbye!");
    }
    //Methods:
    private int[][] optimize() {
        this.studentScheduleCheck = new int[5][13];
        int numOfStudents = this.students.getSize();
        for (int i = 0; i < numOfStudents; i ++) {
            Student sCheck = this.students.pop();
            int[][] freeTimes = sCheck.getConvertedFreeTimes();
            for (int j = 0; j < freeTimes.length; j++) {
                for (int k = 1; k < freeTimes[j].length; k++) {
                    this.studentScheduleCheck[freeTimes[j][0]][(freeTimes[j][k])]++;
                }
            }
        }
        return this.studentScheduleCheck;
    }
    private int[] getOptimal() {
        int bestDay = 0;
        int bestTime = 0;
        int max = this.studentScheduleCheck[0][0];
        for (int i = 0; i < this.studentScheduleCheck.length; i++) {
            for (int j = 0; j < this.studentScheduleCheck[i].length; j++) {
                if (this.studentScheduleCheck[i][j] > max) {
                    max = this.studentScheduleCheck[i][j];
                    bestDay = i;
                    bestTime = j;
                }
            }
        }
        int[] optimalSlot = {bestDay, bestTime};
        return optimalSlot;
    }
    private String getOutput() {
        int[] primeTime = getOptimal();
        String day = null;
        String time = null;
        switch (primeTime[0]) {
            case 0: day = "Monday";
                    break;
            case 1: day = "Tuesday";
                    break;
            case 2: day = "Wednesday";
                    break;
            case 3: day = "Thursday";
                    break;
            case 4: day = "Friday";
                    break;
        }
        if (primeTime[1] == 0) {
            time = "0900";
        }
        else {
            time = (primeTime[1] + 9) +"00";
        }
        String ret = "The optimal time for most the most students based on this data set is " + time + " on " + day + ".";
        return ret;
    }
}
