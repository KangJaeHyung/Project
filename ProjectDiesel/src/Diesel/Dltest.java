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
      System.out.println("�������� �Ϸ�");

      Scanner s = new Scanner(System.in);

      // �����͸� ����� ���ؼ� ������ ��Ʈ�� ���.

      InputStream in = socket.getInputStream();
      DataInputStream dis = new DataInputStream(in);
      OutputStream out = socket.getOutputStream();
      DataOutputStream dos = new DataOutputStream(out);


      String fileNameMsg = "G_HOS_ICON.PNG";
       file = new File("C:\\KTH\\"+fileNameMsg);
      dos.writeUTF(fileNameMsg);//�����ڵ� �������� ���ϸ��� ������.
      System.out.println("������ ���� ��û�� �߽��ϴ�.");
      System.out.println("�������� ���� �����͸� �޾ƿɴϴ�.");
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

      System.out.println("���� �۾��� �Ϸ�Ǿ����ϴ�.");

      // ��Ʈ�� , ���� �ݱ�

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