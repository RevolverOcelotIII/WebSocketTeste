
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTCP {
    public ClienteTCP() {
    }

    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 1234;

        try {
            Throwable var3 = null;
            Object var4 = null;

            try {
                Socket socket = new Socket(hostName, portNumber);

                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        try {
                            Scanner scanner = new Scanner(System.in);

                            try {
                                System.out.println(in.readLine());
                                System.out.println(in.readLine());

                                while (true) {
                                    String inputLine = in.readLine();
                                    if (inputLine == null) {
                                        break;
                                    }

                                    System.out.println(inputLine);
                                    System.out.print("> ");
                                    String userInput = scanner.nextLine();
                                    out.println(userInput);
                                    System.out.println(in.readLine());
                                    in.readLine();
                                    inputLine = in.readLine();
                                    if (inputLine == null) {
                                        break;
                                    }

                                    System.out.println(inputLine);
                                }
                            } finally {
                                if (scanner != null) {
                                    scanner.close();
                                }

                            }
                        } catch (Throwable var47) {
                            if (var3 == null) {
                                var3 = var47;
                            } else if (var3 != var47) {
                                var3.addSuppressed(var47);
                            }

                            if (in != null) {
                                in.close();
                            }

                            throw var3;
                        }

                        if (in != null) {
                            in.close();
                        }
                    } catch (Throwable var48) {
                        if (var3 == null) {
                            var3 = var48;
                        } else if (var3 != var48) {
                            var3.addSuppressed(var48);
                        }

                        if (out != null) {
                            out.close();
                        }

                        throw var3;
                    }

                    if (out != null) {
                        out.close();
                    }
                } catch (Throwable var49) {
                    if (var3 == null) {
                        var3 = var49;
                    } else if (var3 != var49) {
                        var3.addSuppressed(var49);
                    }

                    if (socket != null) {
                        socket.close();
                    }

                    throw var3;
                }

                if (socket != null) {
                    socket.close();
                }
            } catch (Throwable var50) {
                if (var3 == null) {
                    var3 = var50;
                } else if (var3 != var50) {
                    var3.addSuppressed(var50);
                }

                throw var3;
            }
        } catch (UnknownHostException var51) {
            System.err.println("Host desconhecido: " + hostName);
            System.exit(1);
        } catch (Throwable var52) {
            System.err.println("Não foi possível obter I/O para a conexão com " + hostName);
            System.exit(1);
        }

    }
}
