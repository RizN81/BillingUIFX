import java.net.URL;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import com.sun.javafx.fxml.builder.JavaFXImageBuilder;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginUI extends Application {
	String headerColor = "#2681E6";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		//Get the root pane
		Pane root = (Pane) FXMLLoader.load(new URL(this.getClass().getResource("login.fxml").toExternalForm()));
		initAdminCard(root);

		StackPane sp2 = (StackPane) root.lookup("#sp2");
		JFXDepthManager.setDepth(sp2, 2);
		StackPane sp3 = (StackPane) root.lookup("#sp3");
		JFXDepthManager.setDepth(sp3, 2);

		//Setup scene and set it in stage
		Scene scene = new Scene(root);
		scene.getStylesheets().add(this.getClass().getResource("jfoenix-components.css").toExternalForm());
		scene.getStylesheets().add(this.getClass().getResource("bootstrapfx.css").toExternalForm());
		primaryStage.setResizable(false);
		primaryStage.setMaxHeight(700);
		primaryStage.setMaxWidth(700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**Initialize Card UI For Admin Login
	 * @param root
	 */
	private void initAdminCard(Pane root) {
		//Get stack pane
		final StackPane sp1 = (StackPane) root.lookup("#sp1");

		//set depth
		JFXDepthManager.setDepth(sp1, 2);
		Font font = Font.font("Verdana", FontWeight.BOLD, 15);
		// initialize Header
		StackPane header = new StackPane();
		Label headerText = new Label("Administration");
		headerText.setFont(font);
		headerText.setTextFill(Paint.valueOf("#FFF"));
		header.getChildren().add(headerText);
		header.setStyle("-fx-background-radius: 5 5 0 0; -fx-background-color: " + headerColor);
		VBox.setVgrow(header, Priority.ALWAYS);

		//Initialize body
		StackPane body = new StackPane();
		body.setMinHeight(80);
		//Create Image For Icon
		ImageView icon = new ImageView(new Image(this.getClass().getResourceAsStream("boss.png")));
		StackPane.setAlignment(icon, Pos.CENTER);
		StackPane.setMargin(icon, new Insets(5, 5, 5, 5));
		//Add icon in body
		body.getChildren().add(icon);

		//Create vbox for header and body
		VBox content = new VBox();
		JFXRippler ripper = new JFXRippler(content);
		content.getChildren().addAll(header, body);
		body.setStyle("-fx-background-radius: 0 0 5 5; -fx-background-color: rgb(255,255,255,0.87);");

		//Initialize Button
		JFXButton button = new JFXButton("");
		button.setTooltip(new Tooltip("Login As Administrator"));
		button.setButtonType(ButtonType.RAISED);
		button.setStyle("-fx-background-radius: 40;-fx-background-color: #01A05E");
		button.setPrefSize(40, 40);
		button.setRipplerFill(Color.valueOf(headerColor));
		button.setScaleX(0);
		button.setScaleY(0);

		//Glyph icon
		SVGGlyph glyph = new SVGGlyph(-1, "Login",
				"M1008 6.286q18.857 13.714 15.429 36.571l-146.286 877.714q-2.857 16.571-18.286 25.714-8 4.571-17.714 4.571-6.286 "
						+ "0-13.714-2.857l-258.857-105.714-138.286 168.571q-10.286 13.143-28 13.143-7.429 "
						+ "0-12.571-2.286-10.857-4-17.429-13.429t-6.571-20.857v-199.429l493.714-605.143-610.857 "
						+ "528.571-225.714-92.571q-21.143-8-22.857-31.429-1.143-22.857 18.286-33.714l950.857-548.571q8.571-5.143 18.286-5.143" + " 11.429 0 20.571 6.286z",
				Color.WHITE);
		glyph.setSize(20, 20);

		button.setGraphic(glyph);
		button.translateYProperty().bind(Bindings.createDoubleBinding(() -> {
			return header.getBoundsInParent().getHeight() - button.getHeight() / 2;
		}, header.boundsInParentProperty(), button.heightProperty()));
		StackPane.setMargin(button, new Insets(0, 12, 0, 0));
		StackPane.setAlignment(button, Pos.BOTTOM_RIGHT);

		//Create timeline for animation
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(240), new KeyValue(button.scaleXProperty(), 1, javafx.animation.Interpolator.EASE_IN),
				new KeyValue(button.scaleYProperty(), 1, javafx.animation.Interpolator.EASE_IN)));
		animation.setDelay(Duration.millis(1000));
		animation.play();
		sp1.getChildren().addAll(content, button, ripper);

		///Event on Button
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
			}
		});
	}
}
