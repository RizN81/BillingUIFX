import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;

/**A Sample Invoice UI For Inventory
 * @author RizwanKhan
 *@Date: 29 May 2019
 */
public class DashboarUI extends Application {
	TableView<String>	particularTable;
	JFXListView<String>	productItemList;
	JFXListView<String>	productItemTypeList;
	JFXTextField		txtItemSearch, txtItemTypeSearch, txtCustomerName, txtInvoiceNo, txtTotalAmount, txtBalance, txtAmountPaid;
	JFXDatePicker		invoiceDate;
	JFXButton			btnInvoice, btnBillGenerate, btnPayments, btnCustomers, btnInventory, btnPreferenses, btnBalances, btnBank, btnReports, btnTax, btnQR, btnSuppliers,
			btnExpenses, btnStatistcis, btnPrint, btnNew, btnAddCustomer;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Get the root pane
		BorderPane root = (BorderPane) FXMLLoader.load(new URL(this.getClass().getResource("dashboard.fxml").toExternalForm()));
		//Set top layout
		setupTopLayout(root);
		setupLeftLayout(root);
		setupCenterLayout(root);
		setupBottomLayout(root);
		setupRightLayout(root);
		//Setup scene and set it in stage
		Scene scene = new Scene(root);
		scene.getStylesheets().add(this.getClass().getResource("jfoenix-components.css").toExternalForm());
		scene.getStylesheets().add(this.getClass().getResource("scrollpane.css").toExternalForm());
		primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.show();

		//add width property listener
		primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				root.setPrefWidth(newValue.doubleValue());
				particularTable.setPrefWidth(newValue.doubleValue());
			}
		});

		//add height property listener
		primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				particularTable.setPrefHeight(newValue.doubleValue());
			}
		});
	}

	/**Setup Table view
	 * @param root
	 */
	@SuppressWarnings("unchecked")
	private void setupCenterLayout(BorderPane root) {
		particularTable = (TableView<String>) root.lookup("#particularTable");
		particularTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	/**Initialize scroll pane and flow pane. 
	 * Set the view port property to adjust the width of scroll pane on size change for flow pane
	 * @param root
	 */
	private void setupTopLayout(BorderPane root) {
		//Get the scroll pane and flow pane and set the size change listeners
		ScrollPane scrollPane = (ScrollPane) root.lookup("#scrollpane");
		FlowPane flow = (FlowPane) scrollPane.getContent();
		flow.setPadding(new Insets(5, 5, 5, 5));
		flow.setVgap(5);
		flow.setHgap(5);
		flow.setAlignment(Pos.CENTER);
		scrollPane.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
			@Override
			public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
				flow.setPrefWidth(bounds.getWidth());
				flow.setPrefHeight(bounds.getHeight());
			}
		});
		JFXScrollPane.smoothScrolling(scrollPane);
		//Initialize All Buttons , tool tips and short cuts
		btnInvoice = (JFXButton) flow.lookup("#btnInvoice");
		btnBillGenerate = (JFXButton) flow.lookup("#btnBillGenerate");
		btnBillGenerate.setDisable(true);
		btnPayments = (JFXButton) flow.lookup("#btnPayments");
		btnCustomers = (JFXButton) flow.lookup("#btnCustomers");
		btnInventory = (JFXButton) flow.lookup("#btnInventory");
		btnPreferenses = (JFXButton) flow.lookup("#btnPreferenses");
		btnBalances = (JFXButton) flow.lookup("#btnBalances");
		btnBank = (JFXButton) flow.lookup("#btnBank");
		btnReports = (JFXButton) flow.lookup("#btnReports");
		btnTax = (JFXButton) flow.lookup("#btnTax");
		btnQR = (JFXButton) flow.lookup("#btnQR");
		btnSuppliers = (JFXButton) flow.lookup("#btnSuppliers");
		btnExpenses = (JFXButton) flow.lookup("#btnExpenses");
		btnStatistcis = (JFXButton) flow.lookup("#btnStatistcis");

		btnBillGenerate.setTooltip(new Tooltip("Open Invoice (ALT + Q)"));
		btnInvoice.setTooltip(new Tooltip("Open Invoice Records (ALT + 1)"));
		btnPayments.setTooltip(new Tooltip("Manage Payments (ALT + 2)"));
		btnCustomers.setTooltip(new Tooltip("Manage Customers (ALT + 3)"));
		btnInventory.setTooltip(new Tooltip("Manage Stocks (ALT + 4)"));
		btnPreferenses.setTooltip(new Tooltip("Settings (ALT + HOME)"));
		btnBalances.setTooltip(new Tooltip("Manage Balances (ALT + 5)"));
		btnBank.setTooltip(new Tooltip("Manage Bank Details (ALT + 6)"));
		btnReports.setTooltip(new Tooltip("Generate Reports (ALT + 7)"));
		btnTax.setTooltip(new Tooltip("Tax Settings (ALT + 8)"));
		btnQR.setTooltip(new Tooltip("Scan QR/Barcode (ALT + 9)"));
		btnSuppliers.setTooltip(new Tooltip("Manage Suppliers (ALT + 0)"));
		btnExpenses.setTooltip(new Tooltip("Manage Expenses (ALT + -)"));
		btnStatistcis.setTooltip(new Tooltip("View Statistics (ALT + =)"));
		
	}

	/**Initialize List View and set up the height change listeners
	 * @param root
	 */
	@SuppressWarnings("unchecked")
	private void setupLeftLayout(BorderPane root) {
		//get the list object
		VBox vbox = (VBox) root.lookup("#vbox");
		txtItemSearch = (JFXTextField) vbox.lookup("#txtItemSearch");
		txtItemTypeSearch = (JFXTextField) vbox.lookup("#txtItemTypeSearch");
		productItemList = (JFXListView<String>) vbox.lookup("#productItemList");
		productItemTypeList = (JFXListView<String>) vbox.lookup("#productItemTypeList");
		//set tool tip
		productItemList.setTooltip(new Tooltip("List Of Your Stocks Items"));
		productItemTypeList.setTooltip(new Tooltip("List Of Your Stocks Types"));
		txtItemSearch.setTooltip(new Tooltip("Search For Particular Item"));
		txtItemTypeSearch.setTooltip(new Tooltip("Search For Particular Item Type"));
		//set the expand and depth property
		productItemList.depthProperty().set(1);
		productItemList.setExpanded(true);
		productItemTypeList.depthProperty().set(1);
		productItemTypeList.setExpanded(true);

		//set add fake data
		ObservableList<String> items = FXCollections.observableArrayList();
		ObservableList<String> types = FXCollections.observableArrayList();
		for (int i = 0; i < 500; i++)
		{
			items.add("Products " + i);
			types.add("Types " + i);
		}
		productItemList.setItems(items);
		productItemTypeList.setItems(types);

	}

	/**Setup Bottom Scroll Pane and UI
	 * @param root
	 */
	private void setupBottomLayout(BorderPane root) {
		//Get the scroll pane and flow pane and set the size change listeners
		ScrollPane scrollPane = (ScrollPane) root.lookup("#bottomSP");
		FlowPane flow = (FlowPane) scrollPane.getContent();
		scrollPane.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
			@Override
			public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
				flow.setPrefWidth(bounds.getWidth());
				flow.setPrefHeight(bounds.getHeight());
			}
		});
		JFXScrollPane.smoothScrolling(scrollPane);

		//Get other fields
		txtCustomerName = (JFXTextField) flow.lookup("#txtCustomerName");
		txtInvoiceNo = (JFXTextField) flow.lookup("#txtInvoiceNo");
		invoiceDate = (JFXDatePicker) flow.lookup("#invoiceDate");
		invoiceDate.setConverter(new StringConverter<LocalDate>() {
			private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			@Override
			public String toString(LocalDate localDate) {
				if (localDate == null)
					return "";
				return dateTimeFormatter.format(localDate);
			}

			@Override
			public LocalDate fromString(String dateString) {
				if (dateString == null || dateString.trim().isEmpty())
				{
					return null;
				}
				return LocalDate.parse(dateString, dateTimeFormatter);
			}
		});
		invoiceDate.setValue(LocalDate.now());
		btnPrint = (JFXButton) flow.lookup("#btnPrint");
		btnNew = (JFXButton) flow.lookup("#btnNew");
		btnAddCustomer = (JFXButton) flow.lookup("#btnAddCustomer");

		//set tool tips
		txtCustomerName.setTooltip(new Tooltip("Customer name for invoice"));
		txtInvoiceNo.setTooltip(new Tooltip("Current Invoice Number"));
		invoiceDate.setTooltip(new Tooltip("Invoice Date"));
		btnPrint.setTooltip(new Tooltip("Print Invoice (ALT + P)"));
		btnNew.setTooltip(new Tooltip("New Invoice (ALT + N)"));
		btnAddCustomer.setTooltip(new Tooltip("New Customer (ALT + C)"));
	}

	/**Initialize Right Layout UI
	 * @param root
	 */
	private void setupRightLayout(BorderPane root) {
		VBox rightVbox = (VBox) root.lookup("#rightVbox");
		txtTotalAmount = (JFXTextField) rightVbox.lookup("#txtTotalAmount");
		txtBalance = (JFXTextField) rightVbox.lookup("#txtBalance");
		txtAmountPaid = (JFXTextField) rightVbox.lookup("#txtAmountPaid");

		//set tool tips
		txtTotalAmount.setTooltip(new Tooltip("Total Bill Amount"));
		txtBalance.setTooltip(new Tooltip("Remaining Balance"));
		txtAmountPaid.setTooltip(new Tooltip("Amount Paid"));
	}
}
