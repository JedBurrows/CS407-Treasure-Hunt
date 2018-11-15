import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.io.FileInputStream;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

public class Decrypter {
    private FileInputStream fiFile;
    private FileInputStream fiKey;
    private Cipher cipher;
    public Decrypter() throws Exception {
        cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        fiKey = new FileInputStream("/Users/jed/Documents/Uni Work/CS407/assingments/treasurehunt/CS407-Treasure-Hunt/Stage3/rosepublic");
        fiFile = new FileInputStream("/Users/jed/Documents/Uni Work/CS407/assingments/treasurehunt/CS407-Treasure-Hunt/Stage3/encrypted");

    }

    public void Decrypt() throws Exception{

        byte[] keyBytes = fiKey.readAllBytes();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPublicKey pubKey = (RSAPublicKey)kf.generatePublic(spec);
        cipher.init(Cipher.DECRYPT_MODE,pubKey);

        byte[] data = fiFile.readAllBytes();
        byte[] original = cipher.doFinal(data);

        String fname = "output.txt";
        try{
            FileOutputStream fos = new FileOutputStream(fname);
            fos.write(original);
            fos.close();
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        Decrypter d = new Decrypter();
        d.Decrypt();

    }
}
