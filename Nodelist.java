
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
            new Person("cpu01" ,984),
            new Person("cpu03" ,717),
            new Person("cpu04" ,543),
            new Person("cpu05" ,362),
            new Person("cpu07" ,327),
            new Person("cpu08",235),
            new Person("cpu09" ,403),
            new Person("cpu10" ,418),
            new Person("cpu11" ,632),
            new Person("cpu12" ,1059),
            new Person("cpu13" ,841),
            new Person("cpu14" ,445),
            new Person("cpu15" ,697),
            new Person("gpu01" ,222),
            new Person("gpu02" ,640),
            new Person("gpu03" ,206),
            new Person("gpu04" ,295),
            new Person("gpu05" ,588)
        );
   
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table for Node List");
        stage.setWidth(450);
        stage.setHeight(550);
 
        final Label label = new Label("Node List");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn userCol = new TableColumn("Node List");
        userCol.setMinWidth(100);
        userCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("User"));
 
        TableColumn errorCol = new TableColumn("Number of node");
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