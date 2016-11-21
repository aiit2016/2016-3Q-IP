package sample01;

import java.io.*;
import java.net.*;
/**
 *	Title:TCP/IP sample
 *	@author Masahiko Narita
 *	@version 0.9	2004/9/23
 */
public class SndSample {
  private static int portNumber = 2048;
  private static final int COMMAND_START=1;
  private static final int COMMAND_STOP=2;
  private static final int COMMAND_ACK=3;
  private static int op;

  public static void main(String [] args) throws IOException {
    System.out.println("SndSample:main");
//    InetAddress myIPAddr = InetAddress.getByName("masahiko");
    InetAddress myIPAddr = InetAddress.getByName(null);
    System.out.println("SndSample:myIPAddr"+myIPAddr);
    Socket port = new Socket(myIPAddr, portNumber);
    DataInputStream in = new DataInputStream(port.getInputStream());
    DataOutputStream out = new DataOutputStream(port.getOutputStream());
    port.setSoTimeout(30*1000);
    for(;;) {
     try{
        out.writeByte(COMMAND_STOP);
        System.out.println("SndSample:STOP");
        op = in.readByte();
        if(op == COMMAND_ACK){
           System.out.println("SndSample:ack accepted");
        }
        else  System.out.println("SndSample:"+op+" accepted");
        out.writeByte(COMMAND_START);
        System.out.println("SndSample:START");
      }
      catch(IOException e){
	 System.out.println("SndSample:IO Exception "+e);
      }
      try{Thread.sleep(5000);}catch(InterruptedException e){};
    }
  }
}

