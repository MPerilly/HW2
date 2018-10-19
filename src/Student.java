public class Student {
    String name;
    String[][] freeTimes;
    int[][] convertedFreeTimes;
    public Student(String n, String[] freeDays, String[][] freeTimeSlots) {
        name = n;
        this.freeTimes = new String[freeDays.length][];
        for (int i = 0; i < freeDays.length; i++) {
            freeTimes[i] = new String[freeTimeSlots[i].length + 1];
            for (int j = 0; j < freeTimes[i].length; j++) {
                if (j == 0) {
                    this.freeTimes[i][j] = freeDays[i];
                }
                else {
                    this.freeTimes[i][j] = freeTimeSlots[i][j - 1];
                }
            }
        }
        this.convertedFreeTimes = convertFreeTimes();
    }
    //Accessor Methods:
    public String getName() {
        return this.name;
    }
    public int[][] getConvertedFreeTimes() {
        return this.convertedFreeTimes;
    }
    //Modifiers:
    private int[][] convertFreeTimes() {
        this.convertedFreeTimes = new int[freeTimes.length][];
        for (int i = 0; i < freeTimes.length; i++) {
            this.convertedFreeTimes[i] = new int[freeTimes[i].length];
            for (int j = 0; j < freeTimes[i].length; j++) {
                int checkTime = 9;
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
                    for (int k = 0; k < 13; k++) {
                        if (checkTime == 9) {
                            String checkTimeString = "0" + checkTime + "000";
                            if (checkTimeString.equals(this.freeTimes[i][j])) {
                                this.convertedFreeTimes[i][j] = checkTime - 9;
                                break;
                            }
                            else {
                                checkTime++;
                            }
                        }
                        else {
                            String checkTimeString = checkTime + "00";
                            if (checkTimeString.equals(this.freeTimes[i][j])) {
                                this.convertedFreeTimes[i][j] = checkTime - 9;
                                break;
                            }
                            else {
                                checkTime++;
                            }
                        }
                    }
                }
            }
        }
        return this.convertedFreeTimes;
    }
}
