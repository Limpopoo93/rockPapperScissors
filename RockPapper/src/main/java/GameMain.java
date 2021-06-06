import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameMain {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
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
                    return;
                }
                if (s % 2 == 0) {
                    System.out.println("the number is even you must enter an odd number");
                    return;
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
            byte[] byteKeyGeneration = securityRandomNumber.rand();
            int numberComputers = generateComputer.randomComputer(list.size(), byteKeyGeneration);
            byte[] digest = generateHmacKey.generate(byteKeyGeneration, numberComputers);
            System.out.println("HMAC: " + BytesToHex.bytesToHex(digest));
            int numberComputer = generateComputer.randomComputer(list.size(), byteKeyGeneration);
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
            System.out.println("HMAC key: " + BytesToHex.bytesToHex(byteKeyGeneration));

        } catch (NullPointerException | NumberFormatException | InputMismatchException e) {
            System.out.println("you didn't enter a number or an empty string");
            return;
        }
    }
}