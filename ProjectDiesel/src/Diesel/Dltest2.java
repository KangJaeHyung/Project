package Diesel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Dltest2 {
	public void fileclient() throws IOException {

		Socket socket = new Socket("127.0.0.1", 9999);
		// Socket socket = new Socket("172.16.20.2", 9999);
		System.out.println("�������� �Ϸ�");

		Scanner s = new Scanner(System.in);

		// �����͸� ����� ���ؼ� ������ ��Ʈ�� ���.

		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);

		System.out.println("�ް� ���� ������ �̸��� �Է����ּ���!");

		String fileNameMsg = s.nextLine();
		dos.writeUTF(fileNameMsg);
		System.out.println("������ ���� ��û�� �߽��ϴ�.");
		System.out.println("�������� ���� �����͸� �޾ƿɴϴ�.");
		FileOutputStream fos = new FileOutputStream(fileNameMsg);
		// BufferedOutputStream bos = new BufferedOutputStream(fos);
		// byte[] buffer = new byte[1024];

		while (true) {
			int data = dis.read(/* buffer */);
			if (data == -1)
				break;
			fos.write(data);
			// bos.write(buffer,0,data);

		}

		System.out.println("���� �۾��� �Ϸ�Ǿ����ϴ�.");

		// ��Ʈ�� , ���� �ݱ�

		// fos.close();

		dos.close();
		dis.close();
		out.close();
		in.close();
		socket.close();

	}// main()-----------

}
