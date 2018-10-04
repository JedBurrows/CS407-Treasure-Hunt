import javax.imageio.ImageIO;
import javax.xml.crypto.Data;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Arrays;

public class ImageDecoder {
    private BufferedImage cipherImg;
    public ImageDecoder() throws IOException {
        cipherImg = ImageIO.read(new File("res/stego_cover14.bmp"));
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
                String bp = Integer.toBinaryString(cipherImg.getRGB(x, y)).substring(8);
                String red = bp.substring(0,8);
                String green = bp.substring(8,16);
                String blue = bp.substring(16,24);

                // add LSB to result
                if(res.length() > 83 * 8){
                    break outer;
                }
                res += red.charAt(red.length()-1);
                if(res.length() > 83 * 8){
                    break outer;
                }
                res += green.charAt(green.length()-1);
                if(res.length() > 83 * 8){
                    break outer;
                }
                res += blue.charAt(blue.length()-1);

            }
        }
        char[] characters = new char[res.length()/8];
        for(int i=0, j=0; i<res.length(); i++){
            if(i % 8 == 0 && i>0){
                characters[j] = (char) (Integer.parseInt(res.substring(i - 8, i)));
                j++;
            }
        }
        String fname = "output.txt";
        try{
            FileOutputStream fos = new FileOutputStream(fname);
            DataOutputStream dos = new DataOutputStream(fos);

            for(char c : characters){
                dos.writeChar(c);

            }
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }


    }
}

