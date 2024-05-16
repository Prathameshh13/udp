import java.io.*;
import java.net.*;

public class fileshareClient {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 5500;
    private static final int PACKET_SIZE = 1024;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();

            // Send text file
            sendFile(socket, "script.txt");

            // Send audio file
            // sendFile(socket, "audio_cn.mp3");

            // // Send video file
            // sendFile(socket, "Video_cn.mp4");

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendFile(DatagramSocket socket, String fileName) throws IOException {
        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[PACKET_SIZE];

        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            DatagramPacket packet = new DatagramPacket(buffer, bytesRead, InetAddress.getByName(SERVER_IP), SERVER_PORT);
            socket.send(packet);
        }

        System.out.println("File " + fileName + " sent successfully.");
        fileInputStream.close();
    }
}
