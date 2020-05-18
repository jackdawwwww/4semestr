import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {

            while (true)
                try (
                        Socket socket = serverSocket.accept();
                        BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(
                                        socket.getOutputStream()
                                )
                        );
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(
                                        socket.getInputStream()
                                )
                        );

                ) {
                    System.out.println("Client connected");
                    String request = reader.readLine();
                    String response = "aaaaaaa " + request.length();
                    writer.write(response);
                    System.out.print(response + " request: " + request);
                    writer.newLine();
                    writer.flush();
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
