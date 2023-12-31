package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class FarmdashboardController {
	
	@FXML private Pane bottomPane;
	@FXML private TreeView<String> treeView;
	@FXML private ListView<String> listCtrl;
	private TreeItem<String> rootItem = new TreeItem<String>("Root");
	@FXML private ImageView myDroneImage;
	private ArrayList<FarmObjectsContainer> itemsContainerCollection = new ArrayList<FarmObjectsContainer>();
	private static FarmdashboardController INSTANCE;
	
	@FXML
	private void initialize() {
		treeView.setRoot(rootItem);
	}
	
	@FXML
	public TreeItem<String> selectItem() {
		return (treeView.getSelectionModel().getSelectedItem());
	}
	
	public static FarmdashboardController getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FarmdashboardController();
		}
		return INSTANCE;
	}
	

	
	public void addToTree(FarmObjects item,FarmObjectsContainer container,String parentTitle) {
		String title;
		if(container != null) {
			title = container.getTitle();
		}else {
			title = item.getTitle();
		}
		if((rootItem.getChildren().size() == 0 && container != null)|| parentTitle.equals("Root")) {
			TreeItem<String> containerTitle = new TreeItem<>(title);
			rootItem.getChildren().add(containerTitle);
		}else {
			boolean isFound = false;
			for (TreeItem<String> depNode : rootItem.getChildren()) {
				if (depNode.getValue().contentEquals(parentTitle)){
					TreeItem<String> containerTitle = new TreeItem<>(title);
					depNode.getChildren().add(containerTitle);
					isFound = true;
	                break;
				}
			}
			if(!isFound) {
				for (TreeItem<String> depNode : rootItem.getChildren()) {
					iterateAndAdd(depNode,title, parentTitle);
				}
			}
		}
		
	}
	
	public void setValuesInitialize() {
		MultipleSelectionModel<TreeItem<String>> msm = treeView.getSelectionModel();

		addDefaultItemsToRootTree();
		rootItem.setExpanded(true);
		int row = treeView.getRow( rootItem );
		treeView.setOnMouseClicked(new EventHandler<MouseEvent>(){
			
			public void handle(MouseEvent arg0) {
				showSelectedItemCommands();
			}
		});
		listCtrl.setOnMouseClicked(new EventHandler<MouseEvent>(){
			
			public void handle(MouseEvent event) {
				String selectedCommand = listCtrl.getSelectionModel().getSelectedItem();
				if(selectedCommand != null) {
					if(!selectedCommand.contains("Commands")) {
						checkCommandsAndDoFn(selectedCommand);
					}
				}	
			}
		});
		msm.select(row);
		showSelectedItemCommands();
		listCtrl.setVisible(true);
	}
	private void iterateAndAdd(TreeItem<String> node, String title, String parentTitle) {
		boolean isFound = false;
		for (TreeItem<String> depNodeChild :node.getChildren()) {
			if (depNodeChild.getValue().contentEquals(parentTitle)){
				TreeItem<String> containerTitle = new TreeItem<>(title);
				depNodeChild.getChildren().add(containerTitle);
				isFound = true;
                break;
			}
		}
		if(!isFound) {
			for (TreeItem<String> depNodeChild :node.getChildren()) {
				iterateAndAdd(depNodeChild,title, parentTitle);
			}
		}
	}
	
	public void showSelectedItemCommands() {
		listCtrl.setVisible(false);
		listCtrl.getItems().clear();
		boolean isParent =false;
		for (FarmObjectsContainer itemsCont : getItemsContainerCollection()) {
			if(selectItem().getValue().equals(itemsCont.getTitle())) {
				isParent = true;
			}
		}
		if(selectItem() != null && (selectItem().getValue().equals("Root") || isParent)) {
			if(!selectItem().getValue().equals("Root")) {
				listCtrl.getItems().add("Item Container Commands");
				if(!selectItem().getValue().equals("Command Center")) {
					listCtrl.getItems().add("Retitle");
				}
				listCtrl.getItems().add("Change Location X");
				listCtrl.getItems().add("Change Location Y");
				listCtrl.getItems().add("Change Price");
				listCtrl.getItems().add("Change Width");
				listCtrl.getItems().add("Change Height");
				listCtrl.getItems().add("Add Item");
			}else {;
				listCtrl.getItems().add("Item Root Commands");
			}
			listCtrl.getItems().add("Add Item Container");
			if(!selectItem().getValue().equals("Root") && !selectItem().getValue().equals("Command Center")) {
				listCtrl.getItems().add("Delete");
			}
		} else {
			listCtrl.getItems().add("Item Commands");
			if(!selectItem().getValue().equals("Drone")) {
				listCtrl.getItems().add("Retitle");
			}
			
			listCtrl.getItems().add("Change Location X");
			listCtrl.getItems().add("Change Location Y");
			listCtrl.getItems().add("Change Price");
			listCtrl.getItems().add("Change Market Value");
			listCtrl.getItems().add("Change Width");
			listCtrl.getItems().add("Change Height");
			if(!selectItem().getValue().equals("Drone")) {
				listCtrl.getItems().add("Delete");
			}
		}
		listCtrl.setVisible(true);
	}
	

	
	private void renderTheCharts() {
		bottomPane.getChildren().removeAll(bottomPane.getChildren());
		ArrayList<Rectangle> rect = new ArrayList<Rectangle>();
		ArrayList<Text> Tex = new ArrayList<Text>();
		for (FarmObjectsContainer itemsCont : itemsContainerCollection) {
			Rectangle rect2 = new Rectangle(itemsCont.getx_axis(), itemsCont.gety_axis(), itemsCont.getWidth(), itemsCont.getHeight());
	        rect2.setFill(Color.TRANSPARENT);
	        rect2.setStroke(Color.RED);
	        rect2.setStrokeWidth(1);
	        rect.add(rect2);
	        Text reqText = new Text(itemsCont.getx_axis()+10,itemsCont.gety_axis()+20,itemsCont.getTitle() );
	        Tex.add(reqText);
	        if(itemsCont.getItemsCollection().size() ==0 ) {

	        } else {
	        	for (FarmObjects itemEach : itemsCont.getItemsCollection()) {
	        		Rectangle rect22 = new Rectangle(itemEach.getx_axis(), itemEach.gety_axis(), itemEach.getWidth(), itemEach.getHeight());
			        rect22.setFill(Color.TRANSPARENT);
			        rect22.setStroke(Color.RED);
			        rect22.setStrokeWidth(1);
			        rect.add(rect22);
			        Text reqText2 = new Text(itemEach.getx_axis()+10,itemEach.gety_axis()+20,itemEach.getTitle() );
			        Tex.add(reqText2);
					if(itemEach.getTitle().equals("Drone")) {
			        	Image imageObject = new Image(getClass().getResourceAsStream("drone.png"));
			        	myDroneImage = new ImageView(imageObject);
			        	myDroneImage.setX(itemEach.getx_axis()+20);
			        	myDroneImage.setY(itemEach.gety_axis()+30);
			        	myDroneImage.setFitWidth(50);
			        	myDroneImage.setPreserveRatio(true);
			        	bottomPane.getChildren().add(myDroneImage);
			        }

		        }
	        }

			
		}
		for(Rectangle r: rect) {
			bottomPane.getChildren().add(r);
		}
		for(Text rTex: Tex) {
			bottomPane.getChildren().add(rTex);
		}
		
	}
	
	public FarmObjectsContainer createItemContainerObject(String title,double price,int x_axis,int y_axis,int length, int width,int height,String parentTitle) {
		FarmObjectsContainer eachItemsContainer = new FarmObjectsContainer(title,x_axis,y_axis,y_axis,width,height,parentTitle);
		getItemsContainerCollection().add(eachItemsContainer);
		return eachItemsContainer;
	}

	public FarmObjects createItemObject(String title,int x_axis,int y_axis,int length, int width,int height,FarmObjectsContainer container, double marketVal) {
		FarmObjects eachItem = new FarmObjects(title,x_axis,y_axis,y_axis,width,height,marketVal,container.getTitle());
		container.addItem(eachItem);
		return eachItem;
	}
	
	public void addDefaultItemsToRootTree() {
		FarmObjectsContainer container = createItemContainerObject("Command Center",1000.00, 350,20,10,200,150,"Root");
		addSubNode(container,false);
		FarmObjects eachItem = createItemObject("Drone",400,60,10,100,100, container, 500);
		addChildNode(container, eachItem);
		renderTheCharts();
	}
		
	public TreeItem<String> addSubNode(FarmObjectsContainer container,boolean notInital) {
		TreeItem<String> containerTitle = null;
		boolean isFound = false;
		if(notInital && !selectItem().getValue().equals("Root") ) {
			for (TreeItem<String> depNode : rootItem.getChildren()) {
				if (depNode.getValue().contentEquals(selectItem().getValue())){
					isFound = true;
					containerTitle = new TreeItem<>(container.getTitle());
					depNode.getChildren().add(containerTitle);
	                break;
				}
			}
			if(!isFound && selectItem().getParent() != null) {
				for (TreeItem<String> depNode : selectItem().getParent().getChildren()) {
					if (depNode.getValue().contentEquals(selectItem().getValue())){
						isFound = true;
						containerTitle = new TreeItem<>(container.getTitle());
						depNode.getChildren().add(containerTitle);
		                break;
					}
				}	
			}
		}else {
			containerTitle = new TreeItem<>(container.getTitle());
			rootItem.getChildren().add(containerTitle);
		}
		
//		
		return containerTitle;
	}
	
	
	public TreeItem<String> addChildNode(FarmObjectsContainer container, FarmObjects ReqItemNode) {
		TreeItem<String> childNodeTitle =  null;
		if(selectItem() !=null && selectItem().getParent() != null) {
			for (TreeItem<String> depNode : selectItem().getParent().getChildren()) {
				if (depNode.getValue().contentEquals(container.getTitle())){
					childNodeTitle = new TreeItem<>(ReqItemNode.getTitle());
	                depNode.getChildren().add(childNodeTitle);
	                break;
	            }
			}
		}else {
			for (TreeItem<String> depNode : rootItem.getChildren()) {
				if (depNode.getValue().contentEquals(container.getTitle())){
					childNodeTitle = new TreeItem<>(ReqItemNode.getTitle());
	                depNode.getChildren().add(childNodeTitle);
	                break;
	            }
			}
		}
		
		return childNodeTitle;
	}
	
	private void addChildItem() {
		MultipleSelectionModel<TreeItem<String>> msm = treeView.getSelectionModel();
		TextInputDialog popup1 = new TextInputDialog("");
		TextInputDialog popup2 = new TextInputDialog("0");
		TextInputDialog popup3 = new TextInputDialog("0");
		TextInputDialog popup4 = new TextInputDialog("0");
		TextInputDialog popup5 = new TextInputDialog("0");
		

		popup1.setHeaderText("Enter container title");
		popup2.setHeaderText("Enter X Co-oridnate");
		popup3.setHeaderText("Enter Y Co-oridnate");
		popup4.setHeaderText("Enter Width");
		popup5.setHeaderText("Enter Height");		

		popup1.showAndWait();
		popup2.showAndWait();
		popup3.showAndWait();
		popup4.showAndWait();
		popup5.showAndWait();
		
		String title = popup1.getEditor().getText();
		int xCord = Integer.valueOf(popup2.getEditor().getText());
		int yCord = Integer.valueOf(popup3.getEditor().getText());
		int width = Integer.valueOf(popup4.getEditor().getText());
		int height = Integer.valueOf(popup5.getEditor().getText());
		FarmObjectsContainer container = null;
		for (FarmObjectsContainer itemsCont : getItemsContainerCollection()) {
			if(selectItem().getValue().equals(itemsCont.getTitle())) {
				container = itemsCont;
			}
		}
		if(container != null) {
			FarmObjects eachItem = createItemObject(title, xCord,yCord,0,width,height, container,0);
			TreeItem<String>addedItem =addChildNode(container, eachItem);
			selectItem().setExpanded(true);
			
			int row = treeView.getRow(addedItem);
			
			
			msm.select(row);
			showSelectedItemCommands();
		}
		renderTheCharts();
	}
	
	private void retitleTheNode() {
		boolean isParent =false;
		FarmObjectsContainer itemContainer = null;
		FarmObjects selectedItem = null;
		for (FarmObjectsContainer itemsCont : getItemsContainerCollection()) {
			if(selectItem().getValue().equals(itemsCont.getTitle())) {
				isParent = true;
				itemContainer = itemsCont;
			}
		}
		if(!isParent) {
			String parentVal = selectItem().getParent().getValue();
			for (FarmObjectsContainer itemsConter : getItemsContainerCollection()) {
				if(parentVal.equals(itemsConter.getTitle())) {
					itemContainer = itemsConter;
				}
			}
			if(itemContainer != null) {
				for (FarmObjects itemEach : itemContainer.getItemsCollection()) {
					if(selectItem().getValue().equals(itemEach.getTitle())) {
						selectedItem = itemEach;
					}
				}
			}	
			
		}
		TextInputDialog popup = new TextInputDialog(selectItem().getValue());
		popup.setHeaderText("Enter the updated title");
		popup.showAndWait();
		String updatedTitle = popup.getEditor().getText();
		if(updatedTitle != null) {
			if(selectedItem !=null || itemContainer !=null) {
				if(isParent) {
					itemContainer.setTitle(updatedTitle);
					selectItem().setValue(itemContainer.getTitle());
				} else {
					selectedItem.setTitle(updatedTitle);
					selectItem().setValue(selectedItem.getTitle());
				}
			}
		}
		renderTheCharts();
	}

	public void checkCommandsAndDoFn(String command) {
		if(command.contains("Retitle")) {
			retitleTheNode();
		}else if (command.contains("Location X")) {
			changeCoordinatesX();
		}else if (command.contains("Location Y")) {
			changeCoordinatesY();

		}else if(command.contains("Width")) {
			changeWidth();

		}else if(command.contains("Height")) {
			changeHeight();

		} else if(command.equals("Add Item")) {
			addChildItem();

		} else if(command.equals("Add Item Container")) {
			addContainerItem();

		} else if(command.contains("Delete")) {
			deleteItem();

		}else if(command.contains("Market Value")) {

		}
	}
	
	private void changeCoordinatesX() {
		boolean isParent =false;
		FarmObjectsContainer itemContainer = null;
		FarmObjects selectedItem = null;
		for (FarmObjectsContainer itemsCont : getItemsContainerCollection()) {
			if(selectItem().getValue().equals(itemsCont.getTitle())) {
				isParent = true;
				itemContainer = itemsCont;
			}
		}
		
		if(!isParent) {
			String parentVal = selectItem().getParent().getValue();
			for (FarmObjectsContainer itemsConter : getItemsContainerCollection()) {
				if(parentVal.equals(itemsConter.getTitle())) {
					itemContainer = itemsConter;
				}
			}
			if(itemContainer != null) {
				for (FarmObjects itemEach : itemContainer.getItemsCollection()) {
					if(selectItem().getValue().equals(itemEach.getTitle())) {
						selectedItem = itemEach;
					}
				}
			}	
			
		}
		if(selectedItem !=null || itemContainer !=null) {
			if(isParent) {
				TextInputDialog popup2 = new TextInputDialog(String.valueOf(itemContainer.getx_axis()));
				popup2.setHeaderText("Enter X Coordinate");
				popup2.showAndWait();
				int xCordiante = Integer.valueOf(popup2.getEditor().getText());
				itemContainer.setx_axis(xCordiante);
				selectItem().setValue(itemContainer.getTitle());
			} else {
				TextInputDialog popup2 = new TextInputDialog(String.valueOf(selectedItem.getx_axis()));
				popup2.setHeaderText("Enter X Coordinate");
				popup2.showAndWait();
				int xCordiante = Integer.valueOf(popup2.getEditor().getText());
				selectedItem.setx_axis(xCordiante);
				selectItem().setValue(selectedItem.getTitle());
			}
		}
		renderTheCharts();
		
	}
	private void deleteItem() {
		boolean isParent =false;
		int counter =0;
		for (FarmObjectsContainer itemsCont : getItemsContainerCollection()) {
			if(selectItem().getValue().equals(itemsCont.getTitle())) {
				isParent = true;
				this.itemsContainerCollection.remove(counter);
			}
			counter++;
		}
		if(!isParent) {
			int increment = 0;
			for (FarmObjectsContainer itemsConter : getItemsContainerCollection()) {
				for (FarmObjects itemEach : itemsConter.getItemsCollection()) {
					if(selectItem().getValue().equals(itemEach.getTitle())) {
//						selectedItem = itemEach;
						itemsConter.getItemsCollection().remove(increment);
					}
					increment++;
				}
				
			}
			
		}
		
		for (FarmObjectsContainer itemsCont : getItemsContainerCollection()) {
			System.out.println("Delete parents size: "+getItemsContainerCollection().size() );
			System.out.println("Delete childern size: "+itemsCont.getItemsCollection().size() );
		}
		
		selectItem().getParent().getChildren().remove(selectItem());
		listCtrl.setVisible(false);
		renderTheCharts();
	}


	private void changeWidth() {
		boolean isParent =false;
		FarmObjectsContainer itemContainer = null;
		FarmObjects selectedItem = null;
		for (FarmObjectsContainer itemsCont : getItemsContainerCollection()) {
			if(selectItem().getValue().equals(itemsCont.getTitle())) {
				isParent = true;
				itemContainer = itemsCont;
			}
		}
		
		if(!isParent) {
			String parentVal = selectItem().getParent().getValue();
			for (FarmObjectsContainer itemsConter : getItemsContainerCollection()) {
				if(parentVal.equals(itemsConter.getTitle())) {
					itemContainer = itemsConter;
				}
			}
			if(itemContainer != null) {
				for (FarmObjects itemEach : itemContainer.getItemsCollection()) {
					if(selectItem().getValue().equals(itemEach.getTitle())) {
						selectedItem = itemEach;
					}
				}
			}	
			
		}
		if(selectedItem !=null || itemContainer !=null) {
			if(isParent) {
				TextInputDialog popup2 = new TextInputDialog(String.valueOf(itemContainer.getWidth()));
				popup2.setHeaderText("Enter Width");
				popup2.showAndWait();
				int width = Integer.valueOf(popup2.getEditor().getText());
				itemContainer.setWidth(width);
				selectItem().setValue(itemContainer.getTitle());
			} else {
				TextInputDialog popup2 = new TextInputDialog(String.valueOf(selectedItem.getWidth()));
				popup2.setHeaderText("Enter Width");
				popup2.showAndWait();
				int width = Integer.valueOf(popup2.getEditor().getText());
				selectedItem.setWidth(width);
				selectItem().setValue(selectedItem.getTitle());
			}
		}
		renderTheCharts();
	}
	private void changeHeight() {
		boolean isParent =false;
		FarmObjectsContainer itemContainer = null;
		FarmObjects selectedItem = null;
		for (FarmObjectsContainer itemsCont : getItemsContainerCollection()) {
			if(selectItem().getValue().equals(itemsCont.getTitle())) {
				isParent = true;
				itemContainer = itemsCont;
			}
		}
		
		if(!isParent) {
			String parentVal = selectItem().getParent().getValue();
			for (FarmObjectsContainer itemsConter : getItemsContainerCollection()) {
				if(parentVal.equals(itemsConter.getTitle())) {
					itemContainer = itemsConter;
				}
			}
			if(itemContainer != null) {
				for (FarmObjects itemEach : itemContainer.getItemsCollection()) {
					if(selectItem().getValue().equals(itemEach.getTitle())) {
						selectedItem = itemEach;
					}
				}
			}	
			
		}
		if(selectedItem !=null || itemContainer !=null) {
			if(isParent) {
				TextInputDialog popup2 = new TextInputDialog(String.valueOf(itemContainer.getHeight()));
				popup2.setHeaderText("Enter Height");
				popup2.showAndWait();
				int height = Integer.valueOf(popup2.getEditor().getText());
				itemContainer.setHeight(height);
				selectItem().setValue(itemContainer.getTitle());
			} else {
				TextInputDialog popup2 = new TextInputDialog(String.valueOf(selectedItem.getHeight()));
				popup2.setHeaderText("Enter Height");
				popup2.showAndWait();
				int height = Integer.valueOf(popup2.getEditor().getText());
				selectedItem.setHeight(height);
				selectItem().setValue(selectedItem.getTitle());
			}
		}
		renderTheCharts();
		
	}
	
	

	
	private void changeCoordinatesY() {
		boolean isParent =false;
		FarmObjectsContainer itemContainer = null;
		FarmObjects selectedItem = null;
		for (FarmObjectsContainer itemsCont : getItemsContainerCollection()) {
			if(selectItem().getValue().equals(itemsCont.getTitle())) {
				isParent = true;
				itemContainer = itemsCont;
			}
		}
		
		if(!isParent) {
			String parentVal = selectItem().getParent().getValue();
			for (FarmObjectsContainer itemsConter : getItemsContainerCollection()) {
				if(parentVal.equals(itemsConter.getTitle())) {
					itemContainer = itemsConter;
				}
			}
			if(itemContainer != null) {
				for (FarmObjects itemEach : itemContainer.getItemsCollection()) {
					if(selectItem().getValue().equals(itemEach.getTitle())) {
						selectedItem = itemEach;
					}
				}
			}	
			
		}
		if(selectedItem !=null || itemContainer !=null) {
			if(isParent) {
				TextInputDialog popup2 = new TextInputDialog(String.valueOf(itemContainer.gety_axis()));
				popup2.setHeaderText("Enter Y Coordinate");
				popup2.showAndWait();
				int yCordiante = Integer.valueOf(popup2.getEditor().getText());
				itemContainer.sety_axis(yCordiante);
				selectItem().setValue(itemContainer.getTitle());
			} else {
				TextInputDialog popup2 = new TextInputDialog(String.valueOf(selectedItem.gety_axis()));
				popup2.setHeaderText("Enter Y Coordinate");
				popup2.showAndWait();
				int yCordiante = Integer.valueOf(popup2.getEditor().getText());
				selectedItem.sety_axis(yCordiante);
				selectItem().setValue(selectedItem.getTitle());
			}
		}
		renderTheCharts();
		
	}
	private void addContainerItem() {
		MultipleSelectionModel<TreeItem<String>> msm = treeView.getSelectionModel();
		TextInputDialog popup1 = new TextInputDialog("");
		TextInputDialog popup2 = new TextInputDialog("0");
		TextInputDialog popup3 = new TextInputDialog("0");
		TextInputDialog popup4 = new TextInputDialog("0");
		TextInputDialog popup5 = new TextInputDialog("0");
		popup1.setHeaderText("Enter container title");
		popup2.setHeaderText("Enter X Co-oridnate");
		popup3.setHeaderText("Enter Y Co-oridnate");
		popup4.setHeaderText("Enter Width");
		popup5.setHeaderText("Enter Height");
		popup1.showAndWait();
		popup2.showAndWait();
		popup3.showAndWait();
		popup4.showAndWait();
		popup5.showAndWait();
		String title = popup1.getEditor().getText();
		int xCord = Integer.valueOf(popup2.getEditor().getText());
		int yCord = Integer.valueOf(popup3.getEditor().getText());
		int width = Integer.valueOf(popup4.getEditor().getText());
		int height = Integer.valueOf(popup5.getEditor().getText());
		FarmObjectsContainer container = createItemContainerObject(title,0, xCord,yCord,0,width,height,selectItem().getValue());
		TreeItem<String> addedItem = addSubNode(container,true);
		selectItem().setExpanded(true);
		int row = treeView.getRow(addedItem);
		msm.select(row);
		showSelectedItemCommands();
		renderTheCharts();
	}
	
	public void visitItemDrone(ActionEvent e) throws IOException {
		boolean isParent =false;
		FarmObjectsContainer itemContainer = null;
		FarmObjects selectedItem = null;
		int droneX =0;
		int droneY =0;
		for (FarmObjectsContainer itemsCont : getItemsContainerCollection()) {
			if(selectItem().getValue().equals(itemsCont.getTitle())) {
				isParent = true;
				itemContainer = itemsCont;
			}
			for (FarmObjects itemEach : itemsCont.getItemsCollection()) {
				if(itemEach.getTitle().equals("Drone")) {
					droneX = itemEach.getx_axis();
					droneY = itemEach.gety_axis();
				}
			}
		}
		
		if(!isParent) {
			String parentVal = selectItem().getParent().getValue();
			for (FarmObjectsContainer itemsConter : getItemsContainerCollection()) {
				if(parentVal.equals(itemsConter.getTitle())) {
					itemContainer = itemsConter;
				}
			}
			if(itemContainer != null) {
				for (FarmObjects itemEach : itemContainer.getItemsCollection()) {
					if(selectItem().getValue().equals(itemEach.getTitle())) {
						selectedItem = itemEach;
					}
				}
			}	
			
		}
		if(selectedItem !=null || itemContainer !=null) {
			if(isParent) {
				int xCord = itemContainer.getx_axis();
				int yCord = itemContainer.gety_axis();
				DroneAnimation visitDroneObj = new DroneAnimation(myDroneImage); 
				visitDroneObj.visitDroneAnimation(xCord, yCord, droneX, droneY);
				
			} else {
				int xCord = selectedItem.getx_axis();
				int yCord = selectedItem.gety_axis();
				DroneAnimation visitDroneObj = new DroneAnimation(myDroneImage); 
				visitDroneObj.visitDroneAnimation(xCord, yCord, droneX, droneY);
			}
		}
	}
	

	
	public void scanFarmDrone(ActionEvent e) throws IOException {
		goHomeDrone(e);
		DroneAnimation droneObj = new DroneAnimation(myDroneImage);
		droneObj.scanWholeFarm();
	}
	
	public void goHomeDrone(ActionEvent e) {
		FarmObjectsContainer itemsCont = getItemsContainerCollection().get(0);
		FarmObjects itemEach = itemsCont.getItemsCollection().get(0);
		if(itemEach != null && itemEach.getTitle().equals("Drone")) {
			int xCord = itemEach.getx_axis();
			int yCord = itemEach.gety_axis();
			myDroneImage.setX(xCord+10);
			myDroneImage.setY(yCord+10);
		}    
	}
	public ArrayList<FarmObjectsContainer> getItemsContainerCollection() {
		return itemsContainerCollection;
	}

	public void setItemsContainerCollection(ArrayList<FarmObjectsContainer> itemsContainerCollection) {
		this.itemsContainerCollection = itemsContainerCollection;
	}
	

}
