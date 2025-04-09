import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class Check{

    public static void main(String[] args) {
        String data = "This is some data that needs a checksum";
        

        long checksumValue = computeChecksum(data.getBytes());
        System.out.println("Checksum: " + checksumValue);
        
        String alteredData = "This is some altered data that needs a checksum";
        long newChecksumValue = computeChecksum(alteredData.getBytes());
        System.out.println("New Checksum: " + newChecksumValue);
        
        // Compare checksums
        if (checksumValue != newChecksumValue) {
            System.out.println("Data has been altered or corrupted.");
        } else {
            System.out.println("Data is intact.");
        }
    }

    private static long computeChecksum(byte[] data) {
        Checksum checksum = new CRC32();
        checksum.update(data, 0, data.length);
        return checksum.getValue();
    }
}
