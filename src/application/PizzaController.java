package application;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PizzaController {
	private String size;
	private boolean vegetarian=false;
	private String cheese;
	private String pineapple="None";
	private String greenPepper="None";
	private String ham="None";
	private String savedOrders="";
	private int quantity=-1;
	private LineItem currentLine;
	private float savedCost=0;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text txt_COST;
    
    @FXML
    private Button btnSmall;

    @FXML
    private Button btnMedium;

    @FXML
    private Button btnLarge;

    @FXML
    private Button btnSingle;

    @FXML
    private Button btnDouble;

    @FXML
    private Button btnTriple;
    
    @FXML
    private Button btnPineapple;

    @FXML
    private Button btnPepper;

    @FXML
    private Button btnHam;

    @FXML
    private Button btnVegetarian;

    @FXML
    private TextField txtfQuant;

    @FXML
    private Button btnOrder;

    @FXML
    private Button btnSaveOrder;

    @FXML
    private Button btnReset;

    @FXML
    private TextArea txtaOrderList;
    
    @FXML
    private Text txt_COSTQ;
    
    @FXML
    private Button btnShowOrder;
    
    @FXML
    void initialize(){
    	/*
    	 * The Order Button
    	 * Once pressed, it will try and create and Pizza and LineItem object as well as change the Cost
    	 * and CostQ texts to the Pizza and LineItem's .getCost()s
    	 * If creation of Pizza and LineItem objects are successful, it will print a message in the TextArea
    	 * confirming the order
    	 * If creation of Pizza and LineItem objects were unsuccessful, it will prompt the user in the TextArea
    	 * with missing parameters
    	 */
    	btnOrder.setOnAction(event->{
    		try {
    			Pizza pizza = new Pizza(size, vegetarian, cheese, pineapple, greenPepper, ham);
    			LineItem linePizza = new LineItem(quantity, pizza);
    			txt_COST.setText("" + pizza.getCost());
    			txt_COSTQ.setText("" + linePizza.getCost());
    			txtaOrderList.setText("Your Current Order:\n");
    			txtaOrderList.setText(txtaOrderList.getText() + linePizza.toString() + "\n");
    			txtaOrderList.setText(txtaOrderList.getText() + "Press 'Save Order' to save the order");
    			btnSaveOrder.setOpacity(1.0);
    			btnSaveOrder.setDisable(false);
    			currentLine = linePizza;
    		}catch(IllegalPizza e) {
    			txtaOrderList.setText("Finish your order!\n");
    			if(size==null) {
    				txtaOrderList.setText(txtaOrderList.getText() + "- size not specified\n");
    			}
    			if(cheese==null) {
    				txtaOrderList.setText(txtaOrderList.getText() + "- cheese not specified\n");
    			}
    			if(quantity==-1) {
    				txtaOrderList.setText(txtaOrderList.getText() + "- quantity not specified\n");
    			}
    		}
    	});
    	/*
    	 * The Save Order Button
    	 * Disabled until the Order button is pressed and an order is made successfully
    	 * Once pressed, it will save the current order into a string as well as add the cost to the
    	 * total cost
    	 * Will be disabled if user touches any of the buttons that customize the pizza, as that will be
    	 * considered a new order
    	 */
    	btnSaveOrder.setOnAction(click->{
    		btnShowOrder.setOpacity(1.0);
			btnShowOrder.setDisable(false);
    		savedCost+=currentLine.getCost();
    		savedOrders+=currentLine.toString() + "\n";
    		txtaOrderList.setText("Order Saved! Your Orders:\n" + savedOrders + "Total Cost: $" + savedCost);
    	});
    	/*
    	 * Choice 'Small' for pizza size
    	 * Once pressed, will set the string size to 'Small'
    	 * Sets the font bold to let user know current pizza size 
    	 */
        btnSmall.setOnAction(click -> {
        	size = "Small";
        	btnSaveOrder.setDisable(true);
        	btnSaveOrder.setOpacity(0.3);
        	btnSmall.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 12));
        	btnMedium.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnLarge.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	
        });
        /*
         * Choice 'Medium' for pizza size
         * Once pressed, will set the string size to 'Medium'
         * Sets the font bold to let user know current pizza size
         */
        btnMedium.setOnAction(click -> {
        	size = "Medium";
        	btnSaveOrder.setDisable(true);
        	btnSaveOrder.setOpacity(0.3);
        	btnSmall.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnMedium.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 12));
        	btnLarge.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        });
        /*
         * Choice 'Large' for pizza size
         * Once pressed, will set the string size to 'Large'
         * Sets the font bold to let user know current pizza size
         */
        btnLarge.setOnAction(click ->{
        	size = "Large";
        	btnSaveOrder.setDisable(true);
        	btnSaveOrder.setOpacity(0.3);
        	btnSmall.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnMedium.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnLarge.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 12));
        });
        /*
         * Choice 'Single' for pizza cheese
         * Once pressed, will set the string cheese to 'Single'
         * Sets the font bold to let user know current pizza cheese
         */
        btnSingle.setOnAction(click ->{
        	cheese = "Single";
        	btnSaveOrder.setDisable(true);
        	btnSaveOrder.setOpacity(0.3);
        	btnSingle.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 12));
        	btnDouble.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnTriple.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        });
        /*
         * Choice 'Double' for pizza cheese
         * Once pressed, will set the string cheese to 'Double'
         * Sets the font bold to let user know current pizza cheese
         */
        btnDouble.setOnAction(click->{
        	cheese = "Double";
        	btnSaveOrder.setDisable(true);
        	btnSaveOrder.setOpacity(0.3);
        	btnSingle.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnDouble.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 12));
        	btnTriple.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        });
        /*
         * Choice 'Triple' for pizza cheese
         * Once pressed, will set the string cheese to 'Triple'
         * Sets the font bold to let user know current pizza size
         */
        btnTriple.setOnAction(click->{
        	cheese = "Triple";
        	btnSaveOrder.setDisable(true);
        	btnSaveOrder.setOpacity(0.3);
        	btnSingle.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnDouble.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnTriple.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 12));
        });
        /*
         * Choice 'Pineapple' for pizza topping
         * Once pressed, will alternate the string pineapple between 'None' and 'Single'
         * Switches between bold and regular to show pineapple status
         */
        btnPineapple.setOnAction(click->{
        	btnSaveOrder.setDisable(true);
        	btnSaveOrder.setOpacity(0.3);
        	if(pineapple.equals("Single")) {
        		pineapple="None";
        		btnPineapple.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	}
        	else{
        		pineapple="Single";
        		btnPineapple.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 12));
        	}
        });
        /*
         * Choice 'Green Pepper' for pizza topping
         * Once pressed, will alternate the string greenPepper between 'None' and 'Single'
         * Switches between bold and regular to show greenPepper status
         */
        btnPepper.setOnAction(click->{
        	btnSaveOrder.setDisable(true);
        	btnSaveOrder.setOpacity(0.3);
        	if(greenPepper.equals("Single")) {
        		greenPepper="None";
        		btnPepper.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	}
        	else {
        		greenPepper = "Single";
        		btnPepper.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 12));
        	}
        });
        /*
         * Choice 'Vegetarian' for making the pizza vegetarian
         * Once pressed, will toggle the pizza between vegetarian and non vegetarian
         * Switches between bold and regular to show vegetarian status
         * 'Ham' cannot be chosen as a topping when the pizza is vegetarian
         */
        btnVegetarian.setOnAction(click->{
        	btnSaveOrder.setDisable(true);
        	btnSaveOrder.setOpacity(0.3);
        	if(vegetarian) {
        		vegetarian = false;
        		btnHam.setDisable(false);
        		btnHam.setOpacity(1.0);
        		btnVegetarian.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	}
        	else {
        		vegetarian = true;
        		btnHam.setDisable(true);
        		btnHam.setOpacity(0.3);
        		ham = "None";
        		btnHam.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        		btnVegetarian.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 12));
        	}
        });
        /*
         * Choice 'Ham' for pizza topping
         * Once pressed, will alternate the string ham between 'None' and 'Single'
         * Switches between bold and regular to show ham status
         * If vegetarian is on, the button is disabled
         */
        btnHam.setOnAction(click->{
        	btnSaveOrder.setDisable(true);
        	btnSaveOrder.setOpacity(0.3);
        	if(ham.equals("Single")) {
        		ham="None";
        		btnHam.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	}
        	else {
        		ham = "Single";
        		btnHam.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 12));
        	}
        });
        /*
         * Quantity textfield
         * User can type in the textfield and press 'Enter' to input the value in the textfield
         * If the input is not an integer between 1 and 100 inclusive, quantity is not changed
         * If the input is an integer between 1 and 100 inclusive, quantity becomes the inputted value
         */
        txtfQuant.setOnKeyPressed(event -> {
        	if(event.getCode()==KeyCode.ENTER) {
        		try{
        			int content = Integer.parseInt(txtfQuant.getText()); 
        			if(content <= 100 && content >= 1) {
        				btnSaveOrder.setDisable(true);
        	        	btnSaveOrder.setOpacity(0.3);
            			quantity = content;
            			txtaOrderList.setText("Quantity set to " + quantity + ".");
            		}
        			else if(content<1) {
        				txtaOrderList.setText("Quantity cannot be lower than 1");
        			}
        			else if(content>100) {
        				txtaOrderList.setText("Quantity cannot be over 100");
        			}
        		}catch(Exception e) {
        			txtaOrderList.setText("Quantity must be an integer.");
        		}
        	}
        });
        /*
         * The Show Order Button
         * Once pressed, shows current saved orders
         */
        btnShowOrder.setOnAction(click->{
        	txtaOrderList.setText("Order List:\n" + savedOrders + "Total Cost: $" + savedCost + "\n");
        });
        /*
         * The Reset Button
         * Once pressed, resets the whole program by setting all variables to default starting values
         */
        btnReset.setOnAction(click->{
        	size = null;
        	vegetarian = false;
        	txtaOrderList.setText("");
        	txtfQuant.setText("");
        	cheese = null;
        	pineapple="None";
        	greenPepper="None";
        	ham="None";
        	savedOrders="";
        	quantity=-1;
        	savedCost=0;
        	currentLine = null;
        	btnSaveOrder.setDisable(true);
        	btnSaveOrder.setOpacity(0.3);
        	btnHam.setDisable(false);
        	btnHam.setOpacity(1);
        	txt_COST.setText("--");
        	txt_COSTQ.setText("--");
        	btnSmall.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnMedium.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnLarge.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnSingle.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnDouble.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnTriple.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnPineapple.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnPepper.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnHam.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        	btnVegetarian.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 12));
        });
    }
}
