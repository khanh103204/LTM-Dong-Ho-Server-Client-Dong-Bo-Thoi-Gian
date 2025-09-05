package tcpsocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		Socket s = null;
		// TODO Auto-generated method stub
		//1. khoi tao doi tuong Socket de ket noi toi server
		try {
			s = new Socket("localhost" , 2000);
			// thiet lap luong gui cho server
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			//gui du lieu
			
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
			BufferedReader br =new BufferedReader(new InputStreamReader(s.getInputStream()));
			new Thread( new Runnable() {
				
				@Override
				public void run() {
					try {
						
						// nhan du lieu
						while(true) {
							String line = br.readLine();
							System.out.println("Server: " +line);
						}}catch (Exception e) {
						// TODO: handle exception
					}}}
				
			).start();
			
			
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// dong socket
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
