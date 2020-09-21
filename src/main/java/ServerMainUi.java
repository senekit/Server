import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * @program: Server
 * @description: 服务器主页面Ui
 * @author: yps
 * @create: 2020-09-20 16:26
 **/
public class ServerMainUi extends Application {

    public static TextArea mainInformationTextArea = null;
    public static  Pane   serverLoginPane = null;


    public void start(Stage primaryStage) throws Exception {

        serverLoginPane = new Pane();
        mainInformationTextArea = new TextArea();

        mainInformationTextArea.setPrefWidth(350);
        mainInformationTextArea.setPrefHeight(300);
        mainInformationTextArea.setEditable(false);//不可编辑
        mainInformationTextArea.setLayoutX(50);
        mainInformationTextArea.setLayoutY(30);

        serverLoginPane.getChildren().addAll(mainInformationTextArea);

        primaryStage.setScene(new Scene(serverLoginPane,450,500));
        primaryStage.show();
    }

    public void addText(String information){
        mainInformationTextArea.appendText("/n"+information);
    }

}
