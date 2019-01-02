import java.util.Random;

public class Content {
    StringBuilder content;
    public StringBuilder contentCreator() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder(4000);
        Random random = new Random();
        for (int i = 0; i < 4000; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb;

    }
}

