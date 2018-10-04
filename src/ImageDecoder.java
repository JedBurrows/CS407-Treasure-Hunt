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
    }

    public void DecodeLSB(){
        StringBuilder res = new StringBuilder();
        outer:
        for (int y = cipherImg.getHeight() -1; y >= 0; y--) {
            for (int x = cipherImg.getWidth()-1; x >= 0; x--) {
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
        char[] characters = new char[res.length()];
        for(int i=0, j=0; i<res.length(); i++){
            if(i % 8 == 0 && i>0){
                characters[j] = (char) Integer.parseInt(res.substring(i-8,i));
                System.out.print(characters[j]);
                j++;
            }
        }


        String fname = "output.txt";
        try{
            FileOutputStream fos = new FileOutputStream(fname);
            DataOutputStream dos = new DataOutputStream(fos);

            for(char c : characters){
                dos.write(c);
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }


    }

    public void EncodeMsg(String message) throws IOException {
        byte[] bytes = message.getBytes();
        BufferedImage img = ImageIO.read(new File("res/meandchloe.bmp"));
        int i=0;
        for(int y=0; y<img.getHeight(); y++){
            for(int x=0; x<img.getWidth(); x++){
                int lsb = bytes[i] & 0x01;
                String bp = Integer.toBinaryString(img.getRGB(x, y)).substring(8);
                String red = bp.substring(0,8);
                String green = bp.substring(8,16);
                String blue = bp.substring(16,24);



            }
        }
    }
}

