import java.util.*;

class Date {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String date = scn.nextLine();
        String dateRegex = "((19\\d{2}|20\\d{2})[.-/](0\\d|1[0-2])[.-/]([0-2]\\d|3[0-1]))|" +
                "(([0-2]\\d|3[0-1])[.-/](0\\d|1[0-2])[.-/](19\\d{2}|20\\d{2}))";
        System.out.println(date.matches(dateRegex) ? "Yes" : "No");
    }
}