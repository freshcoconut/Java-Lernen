package 第十六次作业;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashSet;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Ex31_10 extends Application {
	TextArea text = new TextArea();
	int num = 0;
	HashSet<DataInputStream> ins = new HashSet<>();
	HashSet<DataOutputStream> outs = new HashSet<>();
	String message;
	public void start(Stage primaryStage) {
		ScrollPane spane = new ScrollPane(text);
		Scene scene = new Scene(spane, 500, 260);
		primaryStage.setTitle("Ex31_10Server");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new Thread( () ->   {
			try {
			ServerSocket server = new ServerSocket(8000);
			text.appendText("The server is ready at " + new Date() + "\n");
			
			while(true) {
				//����socket
				Socket client = server.accept();
				ins.add(new DataInputStream(client.getInputStream()));
				outs.add(new DataOutputStream(client.getOutputStream()));
				Platform.runLater(() -> {
					text.appendText("Connection from " + client.getLocalPort() + "--" + client.getInetAddress() + new Date() + "\n");
				
				new Thread(new Send(client, outs)).start();

				});
			}
			}catch(Exception e) {
				System.out.println("Error in server.");
				System.exit(3);
			}
		}).start();
	}

	public static void main(String[] args) {
		launch();
	}
}
