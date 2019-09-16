
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

public class ListViewSearchLabel extends Application {
	ObservableList<Label>	entries	= FXCollections.observableArrayList();
	JFXListView<Label>				list	= new JFXListView<Label>();

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
			entries.add(new Label("Item " + Math.random()));
		}
		entries.add(new Label("A"));
		entries.add(new Label("B"));
		entries.add(new Label("C"));
		entries.add(new Label("D"));
		list.setItems(entries);

		VBox root = new VBox();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setSpacing(2);
		root.getChildren().addAll(txt, list);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}

	public void search(String oldVal, String newVal) {
		if (oldVal != null && (newVal.length() < oldVal.length()))
		{
			list.setItems(entries);
		}
		String value = newVal.toLowerCase();
		ObservableList<Label> subentries = FXCollections.observableArrayList();
		for (Object entry : list.getItems())
		{
			boolean match = true;
			String entryText = ((Label) entry).getText();
			if (entryText.toLowerCase().contains(value))
			{
				match = false;
				subentries.add(new Label(entryText));
//				break;
			}
			
		}
		list.setItems(subentries);
	}
}
