import java.util.Scanner;

public class AffineCipher {
    static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1;
    }

    static String encrypt(String text, int a, int b) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) (((a * (c - base) + b) % 26) + base));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    static String decrypt(String text, int a, int b) {
        int aInverse = modInverse(a, 26);
        if (aInverse == -1) {
            throw new IllegalArgumentException("No modular inverse exists for the given 'a'");
        }

        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) (((aInverse * ((c - base - b + 26)) % 26) + base)));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nAffine Cipher Menu");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            if (choice == 3) {
                System.out.println("Exiting...");
                break;
            }
            
            System.out.print("Enter text: ");
            String text = scanner.nextLine();
            System.out.print("Enter key 'a' (must be coprime with 26): ");
            int a = scanner.nextInt();
            System.out.print("Enter key 'b': ");
            int b = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Encrypted Text: " + encrypt(text, a, b));
            } else if (choice == 2) {
                System.out.println("Decrypted Text: " + decrypt(text, a, b));
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}
