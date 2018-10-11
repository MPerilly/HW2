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
    public TextFileGenerator(int num, int max, int seed) {
        numberOfStudents = num;
        maxNumTimeSlots = max;
        randSeed = seed;
    }
    public TextFileGenerator() {
        this(10, 6, 5);
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
            poolIndex = r.nextInt((nameMax + 1));
            nameList[i] = namePool[poolIndex];
        }
        return nameList;
    }
    //Generating free days for students:
    public int[][] genFreeDays() {
        Random r = new Random((randSeed +1));
        int[][] studentAvailDays = new int[this.numberOfStudents][7];
        for (int i = 0; i < this.numberOfStudents; i++) {
            int numOfDaysFree = r.nextInt(7);
            for (int k = 0; k < numOfDaysFree; k++) {
                int freeDay = r.nextInt(7);
                studentAvailDays[i][freeDay] = 1;
            }
        }
        return studentAvailDays;
    }
    public int[] stripFreeDays(int[][] freeDays, int studentIndex) {
        int[] studentSpecificDays = new int[7];
        for (int i = 0; i < 7; i++) {
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
        for (int i = 0; i < 7; i++) {
            if(strippedFreeDays[i] == 1) {
                freeCount++;
            }
        }
        daysIndex = new int[freeCount];
        letterDays = new char[freeCount];
        for (int i = 0; i < 7; i++) {
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
}
