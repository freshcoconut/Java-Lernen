package 第十六次作业;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashSet;

public class Send implements Runnable {
	private HashSet<DataOutputStream> toOther;
	Socket socket;
	public Send(){
	}
	public Send(Socket socket, HashSet<DataOutputStream> toOther){
		this.toOther = toOther;
		this.socket = socket;
	}
	public void run(){
		try {
			while(true) {
				DataInputStream input =  new DataInputStream(socket.getInputStream());
				String message = input.readUTF();
				for(DataOutputStream output: toOther) {
					output.writeUTF(message);
				}
			}
		}catch(Exception ex) {
			System.out.println("Error in send.");	
			System.exit(0);
		}

	}
}
