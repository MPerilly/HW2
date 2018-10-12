import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Random;
public class TextFileGenerator {
    int numberOfStudents;
    int maxNumTimeSlots;
    int randSeed;
    String[] namePool = {"Addison", "Adrian" , "Ainsley", "Alex"   , "Andy"   , "Angel"  , "Ashley" , "Ashton" ,
                         "Aubrey" , "Avery"  , "Bailey" , "Bevan"  , "Blair"  , "Bobby"  , "Brett"  , "Brooke" ,
                         "Bronwyn", "Cameron", "Carson" , "Casey"  , "Cassidy", "Charlie", "Chris"  , "Dakota" ,
                         "Dallas" , "Dana"   , "Darby"  , "Dawson" , "Devon"  , "Drew"   , "Eden"   , "Ellis"  ,
                         "Emery"  , "Emerson", "Erin"   , "Finley" , "Francis", "Jean"   , "Jillian", "Greer"  ,
                         "Hayden" , "Harlow" , "Harper" , "Holland", "Hunter" , "Indigo" , "Jayden" , "Jackie" ,
                         "Jamie"  , "Jan"    , "Jesse"  , "Joe"    , "Jody"   , "Jordan" , "Journey", "Julian" ,
                         "Justice", "Kai"    , "Keegan" , "Keely"  , "Keelan" , "Kei"    , "Keith"  , "Kelly"  ,
                         "Kelsey" , "Kendall", "Kennedy", "Kensley", "Carrie" , "Kevin"  , "Kieran" , "Kiley"  ,
                         "Kim"    , "Kyle"   , "Lane"   , "Leigh"  , "Leslie" , "Logan"  , "Loren"  , "Macy"   ,
                         "Madison", "Marley" , "Marlow" , "Merritt", "Michael", "Micky"  , "Montana", "Morgan" ,
                         "Nevada" , "Nico"   , "Owen"   , "Paris"  , "Parker" , "Pat"    , "Peyton" , "Phoenix",
                         "Piper"  , "Quinn"  , "Rayne"  , "Regan"  , "Renee"  , "Reese"  , "Riley"  , "Robin"  ,
                         "Rory"   , "Rowan"  , "Ryan"   , "Sage"   , "Sasha"  , "Scout"  , "Shae"   , "Shannon",
                         "Sloan"  , "Skylar" , "Sydney" , "Shawn"  , "Storm"  , "Tate"   , "Tatum"  , "Taylor" ,
                         "Toni"   , "Tory"   , "Tracy"  , "Trinity", "Tristan", "Vick"   , "Wesley" , "Whitney"};
    int nameMax = namePool.length;
    String writeDirectory;
    String fileName;
    String path;
    Path p;
    public TextFileGenerator(int num, int max, int seed, String dir, String fname) {
        numberOfStudents = num;
        maxNumTimeSlots = max;
        randSeed = seed;
        writeDirectory = dir;
        fileName = fname;
        path = writeDirectory + fileName + ".txt";
        p = Paths.get(this.path);
    }
    public TextFileGenerator() {
        this(50, 6, 5,
                "/Users/mperilly/Google Drive/College/Junior/Data Structures/Homework/Project 2/",
                "test1");
    }
    //Accessor Methods:
    public int getNumberOfStudents() {return this.numberOfStudents;}
    public int getMaxNumTimeSlots() {return this.maxNumTimeSlots;}
    public int getSeed() {return this.randSeed;}
    //Functions:
    //Picking Names:
    public String[] pickNames() {
        Random r = new Random(randSeed);
        int poolIndex;
        String[] nameList = new String[numberOfStudents];
        for (int i = 0; i < numberOfStudents; i++){
            poolIndex = r.nextInt((nameMax));
            nameList[i] = namePool[poolIndex];
        }
        return nameList;
    }
    //Generating free days for students:
    public int[][] genFreeDays() {
        Random r = new Random((this.randSeed +1));
        int[][] studentAvailDays = new int[this.numberOfStudents][5];
        for (int i = 0; i < this.numberOfStudents; i++) {
            int numOfDaysFree = r.nextInt(5);
            for (int k = 0; k < numOfDaysFree; k++) {
                int freeDay = r.nextInt(5);
                studentAvailDays[i][freeDay] = 1;
            }
        }
        return studentAvailDays;
    }
    public int[] stripFreeDays(int[][] freeDays, int studentIndex) {
        int[] studentSpecificDays = new int[5];
        for (int i = 0; i < 5; i++) {
            studentSpecificDays[i] = freeDays[studentIndex][i];
        }
        return studentSpecificDays;
    }
    public char[] convertFreeDays(int[] strippedFreeDays) {
        char[] letterDays;
        int[] daysIndex;
        int daysIndexIndex = 0;
        int freeDayIndex = 0;
        int freeCount = 0;
        for (int i = 0; i < 5; i++) {
            if(strippedFreeDays[i] == 1) {
                freeCount++;
            }
        }
        daysIndex = new int[freeCount];
        letterDays = new char[freeCount];
        for (int i = 0; i < 5; i++) {
            if(strippedFreeDays[i] == 1) {
                daysIndex[daysIndexIndex] = freeDayIndex;
                daysIndexIndex++;
            }
            freeDayIndex++;
        }
        for (int i = 0; i < daysIndex.length; i++){
            switch(daysIndex[i]) {
                case 0: letterDays[i] = 'M';
                        break;
                case 1: letterDays[i] = 'T';
                        break;
                case 2: letterDays[i] = 'W';
                        break;
                case 3: letterDays[i] = 'H';
                        break;
                case 4: letterDays[i] = 'F';
                        break;
            }
        }
        return letterDays;
    }
    //Generating free times for students:
    public int[][][] genFreeTimes() {
        Random r = new Random((this.randSeed + 2));
        int[][][] freeTimes = new int[this.numberOfStudents][5][15];
        for (int i = 0; i < this.numberOfStudents; i++) {
            for (int j = 0; j < 5; j++){
                int numberFreeTimes = (r.nextInt(this.maxNumTimeSlots) + 1);
                for (int k = 0; k < numberFreeTimes; k++) {
                    int freeSlot = (r.nextInt(11) + 1);
                    freeTimes[i][j][freeSlot] = 1;
                }
            }
        }
        return freeTimes;
    }
    public int[] stripFreeTimes(int[][][] freeTimes, int studentIndex, int dayIndex) {
        int[] specificFreeTimes = new int[15];
        for (int i = 0; i < 15; i++) {
            specificFreeTimes[i] = freeTimes[studentIndex][dayIndex][i];
        }
        return specificFreeTimes;
    }
    public String[] convertFreeTimes(int[] specificFreeTimes) {
        String[] convertedTimes;
        int freeTimes = 0;
        int freeTimesIndex = 0;
        for (int i = 0; i < 15; i++) {
            if (specificFreeTimes[i] == 1) {
                freeTimes++;
            }
        }
        convertedTimes = new String[freeTimes];
        for (int i = 0; i < 15; i++) {
            if (specificFreeTimes[i] == 1) {
                if (i == 0) {
                    convertedTimes[freeTimesIndex] = "0" + (i + 9) + "000";
                    freeTimesIndex++;
                }
                else {
                    convertedTimes[freeTimesIndex] = (i + 9) + "00";
                    freeTimesIndex++;
                }
            }
        }
        return convertedTimes;
    }
    public boolean createNewWriteFile() {
        try {
            File writeFile = new File(this.path);
            boolean fileCheck = writeFile.createNewFile();
            return fileCheck;
        }
        catch (IOException ex) {
            System.out.println("Exception:");
            ex.printStackTrace();
            return false;
        }
    }
    public boolean writeToFile(String name, char[] days, ArrayList<String[]> times) {
        StandardOpenOption ops = StandardOpenOption.APPEND;
        try (BufferedWriter writer = Files.newBufferedWriter(this.p, StandardCharsets.UTF_8, ops)) {
            writer.write("(" + name);
            for (int j = 0; j < days.length; j ++) {
                writer.write("<");
                writer.write(days, j, 1);
                String[] timesArray = times.get(j);
                for (int k = 0; k < timesArray.length; k++) {
                    writer.write("[" + timesArray[k] + "]");
                }
                writer.write(">");
            }
            writer.write(")");
            return true;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
