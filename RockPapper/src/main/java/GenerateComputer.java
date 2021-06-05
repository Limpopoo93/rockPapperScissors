public class GenerateComputer {
    public int randomComputer(int forCount, byte[] bytes) {
        int key = 0;
        for (byte resultByte : bytes) {
            key += (128 + resultByte);
        }
        int number = key;
        key = key / forCount;
        key = key * forCount;
        number = number - key;
        if (number != 3) {
            number++;
        }
        return number;
    }
}
