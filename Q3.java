
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class Q3 extends Application {
 
    private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
        FXCollections.observableArrayList(
            new Person("farhatabjani" ,3),
            new Person("hass" ,1),
            new Person("aah" ,12),
            new Person("chiuling" ,2),
            new Person("hongvin" ,9),
            new Person("lobbeytan",3),
            new Person("lin0618" , 4),
            new Person("janvik" ,4),
            new Person("xinpeng" ,1),
            new Person("kurk" ,1),
            new Person("mk_98" ,24),
            new Person("hva170037" ,5),
            new Person("htt_felicia" ,21),
            new Person("fairus" ,10),
            new Person("han" ,11),
            new Person("f4ww4z" ,4),
            new Person("manoj" ,4),
            new Person("fahmi8" ,1),
            new Person("ongkuanhung" ,1),
            new Person("tinweijing" ,2),
            new Person("aznul" ,2),
            new Person("roland" ,4 ),
            new Person("shahreeza" ,6),
            new Person("noraini" ,4)
        );
   
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table for number of jobs causing error");
        stage.setWidth(450);
        stage.setHeight(550);
 
        final Label label = new Label("Number of jobs causing error");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn userCol = new TableColumn("User");
        userCol.setMinWidth(100);
        userCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("User"));
 
        TableColumn errorCol = new TableColumn("Number of jobs causing error");
        errorCol.setMinWidth(200);
        errorCol.setCellValueFactory(
                new PropertyValueFactory<Person, Integer>("Error"));
 
        table.setItems(data);
        table.getColumns().addAll(userCol, errorCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
 
    public static class Person {
 
        private final SimpleStringProperty user;
        private final SimpleIntegerProperty error;
      
 
        private Person(String User, int Error) {
            this.user = new SimpleStringProperty(User);
            this.error = new SimpleIntegerProperty(Error);
        }
 
        public String getUser() {
            return user.get();
        }
 
        public void setUser(String usern) {
            user.set(usern);
        }
 
        public int getError() {
            return error.get();
        }
 
        public void setError(int errorn) {
            error.set(errorn);
        }
    }
} 