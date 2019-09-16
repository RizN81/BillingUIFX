
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListViewSearchText extends Application {
	ObservableList<String>	entries	= FXCollections.observableArrayList();
	JFXListView<String>		list	= new JFXListView<String>();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Simple Search");
		TextField txt = new TextField();
		txt.setPromptText("Search");
		txt.textProperty().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> observable, Object oldVal, Object newVal) {

				search((String) oldVal, (String) newVal);
			}
		});

		for (int i = 0; i < 500; i++)
		{
			entries.add("Item " + Math.random());
		}
		entries.add("A");
		entries.add("B");
		entries.add("C");
		entries.add("D");

		list.setItems(entries);
		list.depthProperty().set(1);
		list.setExpanded(true);
		VBox root = new VBox();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setSpacing(2);
		root.getChildren().addAll(txt, list);
		root.getStylesheets().add(ListViewDemo.class.getResource("jfoenix-components.css").toExternalForm());
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}

	public void search(String oldVal, String newVal) {
		if (oldVal != null && (newVal.length() < oldVal.length()))
		{
			list.setItems(entries);
		}
		String value = newVal.toLowerCase();
		ObservableList<String> subentries = FXCollections.observableArrayList();
		for (Object entry : list.getItems())
		{
			
			String entryText = (String) entry;
			if (entryText.toLowerCase().contains(value))
			{
			
				subentries.add(entryText);
				//				break;
			}

		}
		list.setItems(subentries);
	}
}
