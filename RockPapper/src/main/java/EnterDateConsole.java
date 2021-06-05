import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnterDateConsole {
    public List<Integer> enterDateForConsole() {
            Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                String[] inputNumbers = input.split(" ");
                List<Integer> listNumbersForInt = new ArrayList<>();
                for (String s : inputNumbers) {
                    listNumbersForInt.add(Integer.valueOf(s));
                }
                return listNumbersForInt;
            }

}
