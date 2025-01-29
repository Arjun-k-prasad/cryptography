import java.math.BigInteger;
import java.util.Scanner;
import java.security.SecureRandom;

public class ElGamal {

    private static final SecureRandom random = new SecureRandom();

    
    public static BigInteger[] generateKeys(BigInteger p, BigInteger g, BigInteger d) {
       
        BigInteger e1 = g.modPow(d, p);
        return new BigInteger[]{p, g, e1, d}; 
    }

    public static BigInteger[] encrypt(BigInteger m, BigInteger e1, BigInteger p, BigInteger g, BigInteger r) {
       
        BigInteger c1 = g.modPow(r, p);

      
        BigInteger c2 = m.multiply(e1.modPow(r, p)).mod(p);

        return new BigInteger[]{c1, c2};
    }

    
    public static BigInteger decrypt(BigInteger[] ciphertext, BigInteger d, BigInteger p) {
        BigInteger c1 = ciphertext[0];
        BigInteger c2 = ciphertext[1];

  
        BigInteger c1d = c1.modPow(d, p);
        BigInteger inverse = c1d.modInverse(p);

 
        return c2.multiply(inverse).mod(p);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

      
        System.out.print("Enter prime modulus p  ");
        BigInteger p = new BigInteger(scanner.nextLine());

        System.out.print("Enter generator e1 ");
        BigInteger e1 = new BigInteger(scanner.nextLine());

        System.out.print("Enter private key d  ");
        BigInteger d = new BigInteger(scanner.nextLine());

        
        System.out.print("Enter  r  ");
        BigInteger r = new BigInteger(scanner.nextLine());

       
        BigInteger[] keys = generateKeys(p, e1, d);
        BigInteger e2 = keys[2]; 

        
        System.out.println("Public key: (e2 = " + e2+ ", p = " + p + ", e1 = " + e1 + ")");
        System.out.println("Private key: d = " + d);


        System.out.print("Enter a message to encrypt (numeric value): ");
        BigInteger message = new BigInteger(scanner.nextLine());

    
        BigInteger[] ciphertext = encrypt(message, e2, p, e1, r);
        System.out.println("Encrypted message: c1 = " + ciphertext[0] + ", c2 = " + ciphertext[1]);

       
        BigInteger decryptedMessage = decrypt(ciphertext, d, p);
        System.out.println("Decrypted message: " + decryptedMessage);

        scanner.close();
    }
}
