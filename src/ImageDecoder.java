import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ImageDecoder {
    private BufferedImage cipherImg;
    private BufferedImage originalImg
            ;    public ImageDecoder() throws IOException {
        cipherImg = ImageIO.read(new File("res/stego_cover14.bmp"));
        //System.out.println(Integer.toBinaryString(cipherImg.getRGB(0,0)));
    }

    public void DecodeBPCS(){
        for (int y = 0; y < cipherImg.getHeight(); y++) {
            for (int x = 0; x < cipherImg.getWidth(); x++) {
                String rgb = Integer.toBinaryString(cipherImg.getRGB(x, y)).substring(8);

            }
        }
        System.out.println();
    }

    public void DecodeLSB(){
        String res = "";
        outer:
        for (int y = 0; y < cipherImg.getHeight(); y++) {
            for (int x = 0; x < cipherImg.getWidth(); x++) {
                if(res.length() > 83 * 8){
                    break outer;
                }
                String bp = Integer.toBinaryString(cipherImg.getRGB(x, y)).substring(8);
                String red = bp.substring(0,8);
                String green = bp.substring(8,16);
                String blue = bp.substring(16,24);

                res += red.charAt(red.length()-1);
                res += green.charAt(green.length()-1);
                res += blue.charAt(blue.length()-1);

            }
        }
        char[] characters = new char[res.length()/8];
        res = new StringBuilder(res).reverse().toString();
        for(int i=0, j=0; i<res.length(); i++){
            if(i % 8 == 0 && i>0){
                characters[j] = (char) Integer.parseInt(res.substring(i - 8, i));
                j++;
            }
        }



        System.out.println("Result: " + String.valueOf(characters));

    }
}

