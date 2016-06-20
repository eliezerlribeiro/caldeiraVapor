package caldeiraVapor.clientGui;

import caldeiraVapor.ClienteThread;

import caldeiraVapor.*;
import caldeiraVapor.ClienteThread;

import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class TestClient {

    public static void main(String[] args) throws IOException {
       ClienteThread cliente = new ClienteThread(31313);
       cliente.run();
    }
}
