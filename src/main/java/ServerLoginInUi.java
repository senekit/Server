import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @program: Server
 * @description: 服务器登录Ui
 * @author: yps
 * @create: 2020-09-20 21:10
 **/
public class ServerLoginInUi extends Application {
    public void start(final Stage primaryStage) throws Exception {

        Pane pane = new Pane();                         //总体框架

        Text emailText = new Text("邮箱:");
        Text passwordText = new Text("密码:");
        TextField emailTextField = new TextField();
        emailTextField.setPromptText("请在此输入您的邮箱");
        TextField passwordTextField = new TextField();
        passwordTextField.setPromptText("请在此输入您的密码");
        Button loginButton = new Button("登录");
        Button registerButton = new Button("注册");
        Button forgetPasswordButton = new Button("忘记密码");
        Button closeButton = new Button("X");

        pane.getChildren().add(emailText);
        pane.getChildren().add(passwordText);
        pane.getChildren().add(loginButton);
        pane.getChildren().add(registerButton);
        pane.getChildren().add(emailTextField);
        pane.getChildren().add(passwordTextField);
        pane.getChildren().add(closeButton);
        pane.getChildren().add(forgetPasswordButton);

        emailText.setLayoutX(23);
        emailText.setLayoutY(124);
        emailTextField.setLayoutX(83);
        emailTextField.setLayoutY(100);
        emailTextField.setPrefWidth(188);
        emailTextField.setPrefHeight(36);

        passwordText.setLayoutX(23);
        passwordText.setLayoutY(204);
        passwordTextField.setLayoutX(83);
        passwordTextField.setLayoutY(180);
        passwordTextField.setPrefHeight(36);
        passwordTextField.setPrefWidth(188);

        loginButton.setLayoutX(60);
        loginButton.setLayoutY(286);
        loginButton.setPrefHeight(36);
        loginButton.setPrefWidth(186);

        registerButton.setLayoutX(60);
        registerButton.setLayoutY(356);
        registerButton.setPrefHeight(36);
        registerButton.setPrefWidth(186);

        closeButton.setLayoutX(260);
        closeButton.setLayoutY(15);
        closeButton.setPrefWidth(30);
        closeButton.setPrefHeight(20);

        forgetPasswordButton.setPrefWidth(80);
        forgetPasswordButton.setPrefHeight(20);
        forgetPasswordButton.setLayoutX(190);
        forgetPasswordButton.setLayoutY(230);

/** 样式部分 **/
        emailText.setFont(Font.font(18));
        emailText.setFill(Paint.valueOf("lightgrey"));
        passwordText.setFont(Font.font(18));
        passwordText.setFill(Paint.valueOf("lightgrey"));

        /** 注册和登录和关闭按钮样式 **/
        loginButton.setStyle("" +
                "-fx-background-color: #dfebff;\n" +
                "-fx-background-radius: 25;\n" +
                "-fx-border-radius: 25;" +
                "-fx-font-size:17px;");
        loginButton.setOnMouseEntered(
                e -> {
                    loginButton.setStyle("-fx-background-color: #bec9dd;\n" +
                            "-fx-background-radius: 25;\n" +
                            "-fx-border-radius: 25;" +
                            "-fx-font-size:17px;");
                });
        loginButton.setOnMouseExited(
                e -> {
                    loginButton.setStyle("-fx-background-color: #dfebff;\n" +
                            "-fx-background-radius: 25;\n" +
                            "-fx-border-radius: 25;" +
                            "-fx-font-size:17px;");
                });
        loginButton.setOnMousePressed(
                e -> {
                    loginButton.setStyle("-fx-background-color: #8893a7;\n" +
                            "-fx-background-radius: 25;\n" +
                            "-fx-border-radius: 25;" +
                            "-fx-font-size:17px;");
                });
        loginButton.setOnMouseReleased(e -> {
            loginButton.setStyle("" +
                    "-fx-background-color: #dfebff;\n" +
                    "-fx-background-radius: 25;\n" +
                    "-fx-border-radius: 25;" +
                    "-fx-font-size:17px;");
        });

        registerButton.setStyle("" +
                "-fx-background-color: #dfebff;\n" +
                "-fx-background-radius: 25;\n" +
                "-fx-border-radius: 25;" +
                "-fx-font-size:17px;");
        registerButton.setOnMouseEntered(
                e -> {
                    registerButton.setStyle("-fx-background-color: #bec9dd;\n" +
                            "-fx-background-radius: 25;\n" +
                            "-fx-border-radius: 25;" +
                            "-fx-font-size:17px;");
                });
        registerButton.setOnMouseExited(
                e -> {
                    registerButton.setStyle("-fx-background-color: #dfebff;\n" +
                            "-fx-background-radius: 25;\n" +
                            "-fx-border-radius: 25;" +
                            "-fx-font-size:17px;");
                });
        registerButton.setOnMousePressed(
                e -> {
                    registerButton.setStyle("-fx-background-color: #8893a7;\n" +
                            "-fx-background-radius: 25;\n" +
                            "-fx-border-radius: 25;" +
                            "-fx-font-size:17px;");
                });
        registerButton.setOnMouseReleased(e -> {
            registerButton.setStyle("" +
                    "-fx-background-color: #dfebff;\n" +
                    "-fx-background-radius: 25;\n" +
                    "-fx-border-radius: 25;" +
                    "-fx-font-size:17px;");
        });

        closeButton.setStyle("-fx-background-color: #dfebff;" +
                "-fx-border-radius: 5px; -fx-font-size: 9pt ; -fx-font-family: STHeiti");
        closeButton.setOnMouseEntered(e -> {
            closeButton.setStyle("-fx-text-fill: white; -fx-background-color: rgb(218, 95, 71)");
        });
        closeButton.setOnMouseExited(e -> {
            closeButton.setStyle("-fx-background-color: #dfebff; -fx-border-radius: 5px; -fx-font-size: 9pt ;" +
                    " -fx-font-family: STHeiti");
        });
        closeButton.setOnMousePressed(e -> {
            closeButton.setStyle("-fx-text-fill: white; -fx-background-color: rgb(218, 95, 71)");
        });
        closeButton.setOnMouseReleased(e -> {
            closeButton.setStyle("-fx-background-color: #dfebff; -fx-border-radius: 5px; -fx-font-size: 9pt ;" +
                    " -fx-font-family: STHeiti");
        });

        pane.setStyle("-fx-background-color: #36383c");

        closeButton.setOnAction(e->{
            primaryStage.close();
        });

        primaryStage.setScene(new Scene(pane, 300, 500));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
