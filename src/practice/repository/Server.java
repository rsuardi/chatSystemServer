package practice.repository;

import practice.connection.Connection;

import java.io.*;

public class Server extends Connection {

    public Server(String tipo, int port, String host) throws IOException {
        super(tipo, port, host);
    }

    public void startServer(){

        try
        {
            System.out.println("Esperando..."); //Esperando conexión

            cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente

            System.out.println("Cliente en línea");

            //Se obtiene el flujo de salida del cliente para enviarle mensajes
            clientOutput = new DataOutputStream(cs.getOutputStream());

            //Se le envía un mensaje al cliente usando su flujo de salida
            clientOutput.writeUTF("Petición recibida y aceptada\n");

            //Se obtiene el flujo entrante desde el cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cs.getInputStream()));

            while((serverMessage = entrada.readLine()) != null) //Mientras haya mensajes desde el cliente
            {
                //Se muestra por pantalla el mensaje recibido
                System.out.println(serverMessage);
            }

            System.out.println("Fin de la conexión");

            //ss.close();//Se finaliza la conexión con el cliente
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void startServer2(){
        try
        {


            System.out.println("Server Started and listening to the port 1234");

            //Server is running always. This is done using this while(true) loop

            //Reading the message from the client
            cs = ss.accept();
            InputStream is = cs.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String number = br.readLine();
            System.out.println("From Client: "+number);

            //Multiplying the number by 2 and forming the return message
            String returnMessage;
            try
            {
                int numberInIntFormat = Integer.parseInt(number);
                int returnValue = numberInIntFormat*2;
                returnMessage = String.valueOf(returnValue) + "\n";
            }
            catch(NumberFormatException e)
            {
                //Input was not a number. Sending proper message back to client.
                returnMessage = "Please send a proper number\n";
            }

            //Sending the response back to the client.
            OutputStream os = cs.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(returnMessage);
            System.out.println("To Client: "+returnMessage);
            bw.flush();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                cs.close();
            }
            catch(Exception e){}
        }
    }
}
