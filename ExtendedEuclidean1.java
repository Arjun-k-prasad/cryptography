import java.util.Scanner;

public class ExtendedEuclidean1 {
    static int gcdExtended(int a, int b, int[] x, int[] y) {
        if (a == 0) {
            x[0] = 0;
            y[0] = 1;
            return b;
        }

        int[] x1 = {0}, y1 = {0};
        int gcd = gcdExtended(b % a, a, x1, y1);

        x[0] = y1[0] - (b / a) * x1[0];
        y[0] = x1[0];

        return gcd;
    }

    static int modInverse(int a, int m) {
        int[] x = {0}, y = {0};
        int gcd = gcdExtended(a, m, x, y);
        if (gcd != 1) {
            throw new IllegalArgumentException("Inverse does not exist");
        }
        return (x[0] % m + m) % m;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number: ");
        int a = scanner.nextInt();
        System.out.print("Enter modulus: ");
        int m = scanner.nextInt();
        
        try {
            int inverse = modInverse(a, m);
            System.out.println("Modular Inverse: " + inverse);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
        scanner.close();
    }
}
