package util;

import java.security.SecureRandom;

/**
 * Utility class to generate a 12-digit account number for use in the account
 * creation process.
 */
public class AccountNumberGenerator {
    private static final SecureRandom rand = new SecureRandom();
    private static final int ACCOUNT_NUMBER_LENGTH = 12;

    /**
     * Generates a 12-digit account number using {@link SecureRandom} and {@link StringBuilder}
     * @return 12-digit account number
     */
    public static String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        for(int i = 0; i < ACCOUNT_NUMBER_LENGTH; i++) {
            accountNumber.append(rand.nextInt(10));
        }
        return accountNumber.toString();
    }
}
