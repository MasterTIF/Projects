import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
            // date format: dd/mm/yyyy
            System.out.println("Enter a start date:");
            String startDate = sc.nextLine();
            System.out.println("Enter a end date:");
            String endDate = sc.nextLine();

            int counter = 0;
            while (!startDate.equals(endDate)) {
                startDate = incrementDate(startDate);
                counter++;
            }
            System.out.println("\nTotal days: " + counter);
        

    }

    public static String incrementDate(String date) {
        String[] d = date.split("/");
        int day = Integer.valueOf(d[0]);
        int month = Integer.valueOf(d[1]);
        int year = Integer.valueOf(d[2]);
        int daysInMonth = getDaysInMonth(month, year);

        day++;
        if (day > daysInMonth) {
            day = 1;
            month++;
        }

        if (month > 12) {
            day = 1;
            month = 1;
            year++;
        }
 
        return new String(String.format("%d/%d/%d", day, month, year));
    }

    public static int getDaysInMonth(int month, int year) {
        
        if (month == 2 && checkForLeapYear(year)) {
            return 29;
        } else if (month == 2) {
            return 28;
        }

        if (month > 7) {
            month--;
        }
        if (month % 2 != 0) {
            return 31;
        }
        return 30;
    }

    public static boolean checkForLeapYear(int year) {
        boolean isLeap = false;
        if (year % 4 == 0) {
            isLeap = true;
            if (year % 100 == 0) {
                isLeap = false;
                if (year % 400 == 0) {
                    isLeap = true;
                }
            }
        }
        return isLeap;
    }
}