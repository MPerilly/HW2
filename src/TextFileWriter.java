import java.util.ArrayList;
public class TextFileWriter {
    public static void main(String[] args) {
        TextFileGenerator generator = new TextFileGenerator();
        String[] names = generator.pickNames();
        int[][] availableDays = generator.genFreeDays();
        int[][][] availableSlots = generator.genFreeTimes();
        generator.createNewWriteFile();
        for (int i = 0; i < names.length; i++) {
            int[] studentSpecificDays = generator.stripFreeDays(availableDays, i);
            char[] specificFreeLetterDays = generator.convertFreeDays(studentSpecificDays);
            ArrayList<String[]> cvsTimesList = new ArrayList<>();
            for (int j = 0; j < specificFreeLetterDays.length; j++) {
                int[] specificAvailableSlots = generator.stripFreeTimes(availableSlots, i, j);
                String[] convertedSpecificAvailableTimes = generator.convertFreeTimes(specificAvailableSlots);
                cvsTimesList.add(convertedSpecificAvailableTimes);
            }
            generator.writeToFile(names[i], specificFreeLetterDays, cvsTimesList);
        }
    }
}
