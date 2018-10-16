public class Student {
    String name;
    String[][] freeTimes;
    int[][] convertedFreeTimes;
    public Student(String n, String[] freeDays, String[][] freeTimeSlots) {
        name = n;
        String[][] freeTimes = new String[freeDays.length][];
        for (int i = 0; i < freeDays.length; i++) {
            freeTimes[i] = new String[freeTimeSlots[i].length + 1];
            for (int j = 0; j < freeTimes[i].length; j++) {
                if (j == 0) {
                    freeTimes[i][j] = freeDays[i];
                }
                else {
                    freeTimes[i][j] = freeTimeSlots[i][j];
                }
            }
        }
    }
    //Accessor Methods:
    public String getName() {
        return this.name;
    }
    public int[][] getConvertedFreeTimes() {
        this.convertedFreeTimes = new int[freeTimes.length][];
        for (int i = 0; i < freeTimes.length; i++) {
            this.convertedFreeTimes[i] = new int[freeTimes[i].length + 1];
            for (int j = 0; j < freeTimes[i].length; j++) {
                if (j == 0) {
                    switch (freeTimes[i][j]) {
                        case "M":
                            this.convertedFreeTimes[i][j] = 0;
                            break;
                        case "T":
                            this.convertedFreeTimes[i][j] = 1;
                            break;
                        case "W":
                            this.convertedFreeTimes[i][j] = 2;
                            break;
                        case "H":
                            this.convertedFreeTimes[i][j] = 3;
                            break;
                        case "F":
                            this.convertedFreeTimes[i][j] = 4;
                            break;
                    }
                }
                else {
                    int checkTime = 9;
                    if (checkTime == 9) {
                        String checkTimeString = "0" + checkTime + "000";

                    }
                    else {
                        String checkTimeString = checkTime + "00";
                    }
                }
            }
        }
        return this.convertedFreeTimes;
    }
}
