package 第十六次作业;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class User extends Application {
	DataOutputStream toServer;
	DataInputStream fromServer;
	
	public void start(Stage primaryStage) {
		GridPane gpane = new GridPane();
		TextField EnterName = new TextField();
		TextField EnterText = new TextField();
		EnterName.setPrefColumnCount(20);
		EnterText.setPrefColumnCount(20);
		gpane.add(new Label("Name"), 0, 0);
		gpane.add(EnterName, 1, 0);
		gpane.add(new Label("Enter text  "), 0, 1);
		gpane.add(EnterText, 1, 1);
		gpane.setVgap(10);
		gpane.setHgap(5);
		gpane.setAlignment(Pos.CENTER);

		Button button = new Button("Send");
		
		TextArea tx = new TextArea();
		ScrollPane spane = new ScrollPane(tx);
		
		VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(gpane, button, spane);

		Scene scene = new Scene(vbox, 450, 300);
		primaryStage.setTitle("Client");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		button.setOnAction(e -> {
			String message = EnterName.getText() + ":" + EnterText.getText();
			try {
			toServer.writeUTF(message);
			toServer.flush();
			}catch(Exception ex) {
				System.out.println("Error in User-try1.");
				System.exit(1);
			}
			
		});
		
		try {
			Socket socket = new Socket("127.0.0.1", 8000);
			toServer = new DataOutputStream(socket.getOutputStream());
			fromServer = new DataInputStream(socket.getInputStream());
		}catch(Exception ex) {
		}
		new Thread(() -> {
			try {
				while(true) {
					String mFromServer = fromServer.readUTF();
					tx.appendText(mFromServer + "\n");
				}
			}catch(Exception ex) {
				System.out.println("Error in User-try2.");
				System.exit(2);
			}
		}).start();;
	}
	public static void main(String[] args) {
		launch();
	}
}
