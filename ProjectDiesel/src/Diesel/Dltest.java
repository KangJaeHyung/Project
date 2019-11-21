package Diesel;


import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Dltest {
   File file=null;
   public void fileclient() throws IOException {

      Socket socket = new Socket("127.0.0.1", 9999);
      // Socket socket = new Socket("192.168.0.15", 9999);
      System.out.println("서버접속 완료");

      Scanner s = new Scanner(System.in);

      // 데이터를 통신을 위해서 소켓의 스트림 얻기.

      InputStream in = socket.getInputStream();
      DataInputStream dis = new DataInputStream(in);
      OutputStream out = socket.getOutputStream();
      DataOutputStream dos = new DataOutputStream(out);


      String fileNameMsg = "G_HOS_ICON.PNG";
       file = new File("C:\\KTH\\"+fileNameMsg);
      dos.writeUTF(fileNameMsg);//유니코드 유형으로 파일명을 보낸다.
      System.out.println("서버에 파일 요청을 했습니다.");
      System.out.println("서버에서 파일 데이터를 받아옵니다.");
      FileOutputStream fos = new FileOutputStream(fileNameMsg);
      BufferedOutputStream bos = new BufferedOutputStream(fos);
      byte[] buffer = new byte[1024];

      while (true) {
         int data = dis.read(buffer);
         System.out.println(data);
         if (data == -1)
            break;
  //      fos.write(data);
         bos.write(buffer,0,data);
         bos.flush();
         try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      }

      System.out.println("전송 작업이 완료되었습니다.");

      // 스트림 , 소켓 닫기

      fos.close();

      dos.close();
      dis.close();
      out.close();
      in.close();
      socket.close();

   }// main()-----------
   public static void main(String[] args) throws IOException {
      Dltest dl2 =new Dltest();
      dl2.fileclient();
   }
   
}