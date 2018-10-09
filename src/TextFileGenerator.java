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
}
