import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameMain {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        try {
            EnterDateConsole enterDateConsole = new EnterDateConsole();
            System.out.println("enter odd non-repeating numbers");
            List<Integer> list = enterDateConsole.enterDateForConsole();
            if (list.size() < 3) {
                System.out.println("enter more than 3 numbers");
                return;
            }
            for (Integer s : list) {
                if (s < 0) {
                    System.out.println("the number must be greater than 0");
                    list = enterDateConsole.enterDateForConsole();
                }
                if (s % 2 == 0) {
                    System.out.println("the number is even you must enter an odd number");
                    list = enterDateConsole.enterDateForConsole();
                }
                for (int i = 0; i < list.size(); i++) {
                    for (int j = i + 1; j < list.size(); j++) {
                        if (list.get(i).equals(list.get(j))) {
                            System.out.println("the numbers must not be repeated");
                            return;
                        }
                    }
                }
            }
            SecurityRandomNumber securityRandomNumber = new SecurityRandomNumber();
            GenerateComputer generateComputer = new GenerateComputer();
            GenerateHmacKey generateHmacKey = new GenerateHmacKey();
            Charset ch = Charset.forName("windows-1251");
            byte[] bytes = new byte[16];
            byte[] byteKeyHmac = generateHmacKey.generate(bytes);
            byte[] byteHmac = generateHmacKey.generate(securityRandomNumber.rand(bytes));
            String strHmac = new String(byteHmac, ch);
            System.out.println("HMAC: " + strHmac);
            int numberComputer = generateComputer.randomComputer(list.size(), byteHmac);
            System.out.println("0 - Exit");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + " - " + list.get(i));
            }
            System.out.println("enter your move:");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            if (input < 0 || input > list.size()) {
                System.out.println("the number entered is incorrect");
                return;
            }
            System.out.println("Computer move:");
            System.out.println(numberComputer);
            if (input == numberComputer) {
                System.out.println("Draw");
            } else {
                int resultGame = 1;
                int sum;
                for (int i = 1; i <= (list.size() / 2); i++) {
                    sum = input + 1;
                    if (sum > list.size()) {
                        sum -= list.size();
                    }
                    if (sum == numberComputer) {
                        resultGame = 0;
                        return;
                    }
                }
                if (resultGame == 1) {
                    System.out.println("You win");
                } else {
                    System.out.println("You lose");
                }
            }
            String strKeyHmac = new String(byteKeyHmac, ch);
            System.out.println("HMAC key: " + strKeyHmac);

        } catch (NullPointerException | NumberFormatException | InputMismatchException e) {
            System.out.println("you didn't enter a number or an empty string");
            return;
        }
    }
}
