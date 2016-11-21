package sample01;

import java.io.*;
import java.net.*;
/**
 *	Title:TCP/IP sample(2)
 *	@author Masahiko Narita
 *	@version 0.9	2004/9/23
 */
public class RcvSample {
  private static int portNumber = 2048;
  private static int op;
  private static final int COMMAND_START=1;
  private static final int COMMAND_STOP=2;
  private static final int COMMAND_ACK=3;

  public static void main(String [] args) throws IOException {
    System.out.println("RcvSample:main");
//    InetAddress myIPAddr = InetAddress.getByName("masahiko");
//    InetAddress myIPAddr = InetAddress.getByName(null);
//    System.out.println("RcvSample:myIPAddr="+myIPAddr);
    ServerSocket listenSocket = new ServerSocket(portNumber);
    Socket port = listenSocket.accept();
    System.out.println("RcvSample:port="+port.getPort());
    listenSocket.close();
    DataInputStream in = new DataInputStream(port.getInputStream());
    DataOutputStream out = new DataOutputStream(port.getOutputStream());
    port.setSoTimeout(30*1000);
    for(;;) {
     try{
	if(in.available()>0){
        op = in.readByte();
           if(op == COMMAND_STOP){
              System.out.println("RcvSample:STOPED");
              out.writeByte(COMMAND_ACK);
              do{
                op = in.readByte();   
                System.out.println("RcvSample:"+op+" accepted");
              }while(op != COMMAND_START);
              System.out.println("RcvSample:RESTARTED");
           }
        }
        try{Thread.sleep(300);}catch(InterruptedException e){};
        System.out.print("*");
      }
      catch(IOException e){
	 System.out.println("RcvSample:IO Exception "+e);
	 System.exit(0);
      }
    }
  }
}

