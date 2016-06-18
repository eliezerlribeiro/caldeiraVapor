package caldeiraVapor.clientGui;

import caldeiraVapor.*;

import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class TestClient {

    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 31313);
        BufferedReader input =
            new BufferedReader(new InputStreamReader(s.getInputStream()));
		while (true) {
	        String answer = input.readLine();
			System.out.println(answer);
		}
    }
}
