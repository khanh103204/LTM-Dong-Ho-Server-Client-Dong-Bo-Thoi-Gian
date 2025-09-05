package tcpsocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) {
			//khoi tao doi tuong server socket de nghe ket noi
		ServerSocket ss = null;
		Socket s= null;
		
		try {
			 ss = new ServerSocket(2000);
			System.out.println("Waiting");
			// goi phuong thuc access() de chap nhap ket noi tu cilent
			 s= ss.accept();
			System.out.println("U in");
			// thiet lap luong nhan
			BufferedReader br =new BufferedReader(new InputStreamReader(s.getInputStream()));
			// nhan du lieu
			
			
			new Thread( new Runnable() {
				
				@Override
				public void run() {
					try {
						while(true) {
							String line = br.readLine();
							System.out.println("Client: " +line);
						}}catch (Exception e) {
						// TODO: handle exception
					}}}
				
			).start();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			new Thread( new Runnable() {
				
				@Override
				public void run() {
					try {
					while(true) {
						System.out.println("tao oach");
						String message = new Scanner(System.in).nextLine();
						bw.write(message);
						bw.newLine();
						bw.flush();
					}}catch (Exception e) {
						// TODO: handle exception
					}}}
				
			).start();
			
			
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				s.close();
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
