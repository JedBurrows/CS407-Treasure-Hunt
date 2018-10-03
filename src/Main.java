import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            ImageDecoder id = new ImageDecoder();
            id.DecodeLSB();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
