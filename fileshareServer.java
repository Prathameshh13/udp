import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;

public class fileshareServer {
    private static final int PORT = 5500;
    private static final int BUFFER_SIZE = 1024000000;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            byte[] buffer = new byte[BUFFER_SIZE];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // Save received data to a file
                String fileName = "received_" + System.currentTimeMillis() + ".dat";
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                fileOutputStream.write(packet.getData(), 0, packet.getLength());
                fileOutputStream.close();

                System.out.println("File received and saved as: " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
