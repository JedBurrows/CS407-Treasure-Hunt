import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageDecoder {
    private BufferedImage cipherImg;
    private BufferedImage originalImg
;    public ImageDecoder() throws IOException {
        cipherImg = ImageIO.read(new File("src/stego_cover14.bmp"));
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
}

