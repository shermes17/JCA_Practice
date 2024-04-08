import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.BadPaddingException;
import java.security.MessageDigest;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class HW4 {


  static void P1() throws Exception {
    byte[] iv = new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    byte[] key = Files.readAllBytes(Paths.get("P1_key"));
    byte[] cipherText = Files.readAllBytes(Paths.get("P1_cipher.txt"));
    
    // BEGIN SOLUTION
    // byte[] plainText = decrypted cipherText;
    // END SOLUTION
    
    //System.out.println(new String(plainText, StandardCharsets.UTF_8));
  }
  
  static void P2() throws Exception {
    // BEGIN SOLUTION
    // byte[] P1_plaintext = "plaintext from P1".getBytes(StandardCharsets.UTF_8);
    // byte[] key = generated digest
    // END SOLUTION
    
    //System.out.println(Arrays.toString(key));
  }
  
  static void P3() throws Exception {
    byte[] cipherBMP = Files.readAllBytes(Paths.get("P3_cipher.bmp"));
    
    // BEGIN SOLUTION
    // byte[] key = bytes from P2 (the MD5 digest);
    // byte[] plainBMP = decrypted cipherBMP;
    // END SOLUTION
    
   // Files.write(Paths.get("P3_plain.bmp"), plainBMP);
  }
  
  // of course, you can solve P4 by writing some code
  // or, you can do it manually. Basically, it is your choice!
  //static void P4() throws Exception {
  //}
  
  public static void main(String[] args) {
    try {  
      P1();
      //P2();
      //P3();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}

