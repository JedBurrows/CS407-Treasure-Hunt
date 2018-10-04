import javax.imageio.ImageIO;
import javax.xml.crypto.Data;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.Arrays;

public class ImageDecoder {
    private BufferedImage cipherImg;
    public ImageDecoder() throws IOException {
        cipherImg = ImageIO.read(new File("res/stego_cover14.bmp"));
    }

    public void DecodeLSB(){
        StringBuilder res = new StringBuilder();
        outer:
        for (int y=cipherImg.getHeight()-1; y>=0; y--) {
            for (int x=cipherImg.getWidth()-1; x>=0; x--) {
                String bp = Integer.toBinaryString(cipherImg.getRGB(x, y)).substring(8);
                String red = bp.substring(0,8);
                String green = bp.substring(8,16);
                String blue = bp.substring(16,24);

                // add LSB to result
                res.append(red.charAt(red.length()-1));
                res.append(green.charAt(green.length()-1));
                res.append(blue.charAt(blue.length()-1));
            }
        }

        BigInteger bInt = new BigInteger(res.toString(),2);
        final byte[] output = bInt.toByteArray();

        String fname = "output.txt";
        try{
            FileOutputStream fos = new FileOutputStream(fname);
            fos.write(output);
            fos.close();
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }


    }
}

