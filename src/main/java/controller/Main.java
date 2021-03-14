package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static LoginController lc;

    public static AdminController sac;

    public static Scene lcScene;

    public  static Scene sacScene;

    public final static Double width = 350.0;
    public final static Double height = 420.0;
    public final static String ICON_MAIN_LOC = "/images/analysis.png";

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader lcLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));

//        csv test
//        CsvUtil csvUtil = new CsvUtil();
//        csvUtil.getCsvData("/csv/staff.csv");

        Parent lcRoot = (Parent) lcLoader.load();

        lc = (LoginController) lcLoader.getController();

        lcScene = new Scene(lcRoot, width, height);

        primaryStage.setTitle("登录");
        primaryStage.getIcons().add(new Image(ICON_MAIN_LOC));

        //设置场景
        primaryStage.setScene(lcScene);

//        primaryStage.initStyle(StageStyle.TRANSPARENT);

//        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.centerOnScreen();
        //设置窗口不可调节
        primaryStage.setResizable(false);
        //展示场景
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}