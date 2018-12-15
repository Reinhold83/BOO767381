package ie.ulster.exam;

import javax.servlet.annotation.WebServlet;
import java.util.*;
import java.sql.*;
import com.vaadin.ui.*;
import com.vaadin.*;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        String connectionString ="jdbc:sqlserver://boo767381sumplementardbserver.database.windows.net:1433;" +
        "database=BOO767381DBsumpl;" + "user=Reinhold@boo767381sumplementardbserver;" + "password = Star_wars;"
        + "encrypt=true;" + "trustServerCertificate=false;" + "hostNameInCertificate=*.database.windows.net;"
        + "loginTimeout=30;";

        Connection connection = null;
        final VerticalLayout vLayout = new VerticalLayout();
        final HorizontalLayout hLayout = new HorizontalLayout();
        final TextField name = new TextField("Type your name here");
        final Slider ammountSlider = new Slider("Ammount of seats", 1, 200);
        ammountSlider.setWidth("500px");
        final ComboBox<String> cbAcess = new ComboBox<>("Accesible");
        cbAcess.setItems("Yes", "No");
        Label status = new Label("You have not booked a seat yet");
        status.setContentMode(ContentMode.HTML);
        Button book = new Button("Book");
    



        try  {
  // Connect with JDBC driver to a database
  connection = DriverManager.getConnection(connectionString);
    ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Bookings1;");
    // Convert the resultset that comes back into a List
    List<Bookings1> lBookings = new ArrayList<Bookings1>();
    // While there are more records in the resultset
    while(rs.next())
    {   
         // Add a new Bookings1 instantiated with the fields
         lBookings.add(new Bookings1(rs.getString("destination"), 
         rs.getInt("capacity"), 
         rs.getString("feature"), 
         rs.getBoolean("accesibility")));
    }
    // Add my component, grid is templated with Bookings1
    Grid<Bookings1> myGrid = new Grid<>();
    // Set the items (List)
     myGrid.setItems(lBookings);
    // Configure the order and the caption of the grid
     myGrid.addColumn(Bookings1::getDestination).setCaption("Destination");
     myGrid.addColumn(Bookings1::getAccesibility).setCaption("Capacity");
     myGrid.addColumn(Bookings1::getFeature).setCaption("Feature");
     myGrid.addColumn(Bookings1::getAccesibility).setCaption("Accesibility");
     myGrid.setSelectionMode(SelectionMode.MULTI);
     myGrid.setSizeFull();


     book.addClickListener(e ->{
        if(name.getValue().isEmpty()){
            status.setValue("");
        }



     });


    } catch (Exception e) {
        // This will show an error message if something went wrong
        vLayout.addComponent(new Label(e.getMessage())); 
    }

       /* final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        
        layout.addComponents(name, button);*/
        
        setContent(vLayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
