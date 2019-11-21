package Diesel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Dltest2 {
	public void fileserver() throws IOException {
	      ServerSocket serverSocket = new ServerSocket(9999);
	      System.out.println("서버준비완료");

	      Socket socket = serverSocket.accept(); // 클라이언트접속 대기
	      System.out.println("소켓준비 완료");

	      InputStream in = socket.getInputStream();
	      DataInputStream dis = new DataInputStream(in);
	      OutputStream out = socket.getOutputStream();
	      DataOutputStream dos = new DataOutputStream(out);

	      String fileNameStr = dis.readUTF(); // 클라이언트로부터 파일명(유니코드형)얻기
	      System.out.println("사용자가 요청한 파일 :" + fileNameStr);

	      // 서버프로그램이 실행되는 컴퓨터에 해당 위치에 있는 파일읽기 시작
	      FileInputStream fin = new FileInputStream("C:\\KTH\\" + fileNameStr);

	      while (true) { // FileInputStream을 통해 파일을 읽어들여서 소켓의 출력스트림을 통해 출력.

	         int data = fin.read();

	         if (data == -1)
	            break;

	         dos.write(data);

	      }

	      // 스트림 , 소켓 닫기

	      fin.close();

	      dos.close();

	      dis.close();

	      socket.close();

	      serverSocket.close();

	   }
	

	public static void main(String[] args) {
		Dltest2 dl2 = new Dltest2();
		try {
			dl2.fileserver();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   
}