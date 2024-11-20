public class HammingCode {
    
    // Method to calculate the parity bits for the 7-bit encoded data
    public static int[] encode(int[] data) {
        int[] encoded = new int[7];

        // Assigning the data bits to the appropriate positions
        encoded[2] = data[0];
        encoded[4] = data[1];
        encoded[5] = data[2];
        encoded[6] = data[3];

        // Calculate parity bits
        encoded[0] = encoded[2] ^ encoded[4] ^ encoded[6]; // p1
        encoded[1] = encoded[2] ^ encoded[5] ^ encoded[6]; // p2
        encoded[3] = encoded[4] ^ encoded[5] ^ encoded[6]; // p3

        return encoded;
    }

    // Method to detect and correct a single-bit error
    public static int[] decode(int[] encoded) {
        // Calculate the parity bits from the received data
        int p1 = encoded[0] ^ encoded[2] ^ encoded[4] ^ encoded[6];
        int p2 = encoded[1] ^ encoded[2] ^ encoded[5] ^ encoded[6];
        int p3 = encoded[3] ^ encoded[4] ^ encoded[5] ^ encoded[6];

        // Determine the error position
        int errorPosition = p1 * 1 + p2 * 2 + p3 * 4;

        // If errorPosition is non-zero, there's a single-bit error
        if (errorPosition != 0) {
            System.out.println("Error detected at position: " + errorPosition);
            // Correct the error
            encoded[errorPosition - 1] = encoded[errorPosition - 1] == 0 ? 1 : 0;
            System.out.println("Error corrected.");
        } else {
            System.out.println("No error detected.");
        }

        // Extract the original data bits
        int[] data = {encoded[2], encoded[4], encoded[5], encoded[6]};
        return data;
    }

    public static void main(String[] args) {
        // Example data: 4 bits of data
        int[] data = {1, 0, 1, 1};

        // Encode the data using Hamming Code
        int[] encodedData = encode(data);
        System.out.print("Encoded data: ");
        for (int bit : encodedData) {
            System.out.print(bit + " ");
        }
        System.out.println();

        // Simulate an error by flipping one bit (e.g., bit 3)
        encodedData[2] = encodedData[2] == 0 ? 1 : 0;

        // Decode the data and correct the error if necessary
        int[] decodedData = decode(encodedData);
        System.out.print("Decoded data: ");
        for (int bit : decodedData) {
            System.out.print(bit + " ");
        }
        System.out.println();
    }
}
