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

import java.nio.file.StandardOpenOption;



public class HW4 {


  static void P1() throws Exception {
    byte[] iv = new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    byte[] key = Files.readAllBytes(Paths.get("P1_key"));
    byte[] cipherText = Files.readAllBytes(Paths.get("P1_cipher.txt"));
    
    // BEGIN SOLUTION

    // initialize key and IV
    SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

    // create and initialize cipher given the specifications
    Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding");
    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

    // decript cipherText using the created cipher
    byte[] plainText = cipher.doFinal(cipherText);

    // END SOLUTION
    
    System.out.println(new String(plainText, StandardCharsets.UTF_8));
  }
  
  static void P2() throws Exception {
    // BEGIN SOLUTION
    byte[] P1_plaintext = "The quick brown fox jumps over the lazy dog.".getBytes(StandardCharsets.UTF_8);
    // create digest instance
    MessageDigest md5 = MessageDigest.getInstance("MD5");
    // update with the plaintext
    md5.update(P1_plaintext);
    //hash
    byte[] key = md5.digest();
    // END SOLUTION
    
    System.out.println(Arrays.toString(key));
  }
  
  static void P3() throws Exception {
    byte[] cipherBMP = Files.readAllBytes(Paths.get("P3_cipher.bmp"));
    
    // BEGIN SOLUTION
    byte[] key = new byte[] {-28, -39, 9, -62, -112, -48, -5, 28, -96, 104, -1, -83, -33, 34, -53, -48};
    // create key spec
    SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
    // create and initialize cipher given the specifications, no IV needed for ECB
    Cipher cipher = Cipher.getInstance("AES/ECB/ISO10126Padding");
    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
    // decrypt
    byte[] plainBMP = cipher.doFinal(cipherBMP);
    // END SOLUTION

   // System.out.println(Arrays.toString(plainBMP));
  }



  
  // of course, you can solve P4 by writing some code
  // or, you can do it manually. Basically, it is your choice!
  static void P4() throws Exception {

    /**
     * Since the file is encrypted the file will not open since
     * the header is unreadble, not because of the data. The data does
     * not to be decrypted becasue it was encrypted using ECB which
     * can preserve patterns in the data. Since we know that the file
     * is the exact same size as P3 image, we can use the header that
     * was decrypted in P3. I took the 14 byte header and found the byte
     * offset (bytes 10-14) which is where the image data begins. I took
     * header from P3 wrote it into a new file, and then offset the encrypted
     * data by the amount specified and filled the file with the remaining
     * encrypted data. While the image is not exact, paterns reveal key
     * patterns about the file. In this case the image produced 5 numbers:
     *
     *                    -65 6 -39 -69 115
     *
     */

    // get images data
    byte[] plainP3 = Files.readAllBytes(Paths.get("P3_plain.bmp"));
    byte[] cipherP4 = Files.readAllBytes(Paths.get("P4_cipher.bmp"));

    // get size and convert to unsigned int
    byte offset = plainP3[10];
    int startOfPixels = Byte.toUnsignedInt(offset);

    // copy header from P3 and pixels from P4
    byte[] header = Arrays.copyOfRange(plainP3, 0, startOfPixels);
    byte[] pixels = Arrays.copyOfRange(cipherP4, startOfPixels, plainP3.length);

    // write header and append pixels
    Files.write(Paths.get("P4_plain.bmp"), header);
    Files.write(Paths.get("P4_plain.bmp"), pixels, StandardOpenOption.APPEND);

  }
  
  public static void main(String[] args) {
    try {  
      P1();
      P2();
      P3();
      P4();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}

