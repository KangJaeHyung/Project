package Diesel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Dltest {
	public void fileserver() throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("�����غ�Ϸ�");

		Socket socket = serverSocket.accept(); // Ŭ���̾�Ʈ���� ���
		System.out.println("�����غ� �Ϸ�");

		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);

		String fileNameStr = dis.readUTF(); // Ŭ���̾�Ʈ�κ��� ���ϸ���
		System.out.println("����ڰ� ��û�� ���� :" + fileNameStr);

		// �������α׷��� ����Ǵ� ��ǻ�Ϳ� ���������� ����� ���� ����.
		FileInputStream fin = new FileInputStream("D:\\Dev\\Java\\Save\\" + fileNameStr);

		while (true) { // FileInputStream�� ���� ������ �о�鿩�� ������ ��½�Ʈ���� ���� ���.

			int data = fin.read();

			if (data == -1)
				break;

			dos.write(data);

		}

		// ��Ʈ�� , ���� �ݱ�

		fin.close();

		dos.close();

		dis.close();

		socket.close();

		serverSocket.close();

	}
}// main()-----------