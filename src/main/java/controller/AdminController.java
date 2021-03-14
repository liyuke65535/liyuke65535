package controller;

import com.jfoenix.controls.JFXTabPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Accordion accordion;

    @FXML
    private Button addreader;

    @FXML
    private Button addworker;

    @FXML
    private Button selectReader;

    @FXML
    private Button selectWorker;

    @FXML
    private Button cancellation;

    @FXML
    private Button alterRpw;

    @FXML
    private Button alterWpw;

    @FXML
    private Button alterSpw;

    @FXML
    private AnchorPane anchorpaneR;

    @FXML
    private TitledPane titleFirstPane;

    @FXML
    private TitledPane titleSecondPane;

    @FXML
    private TitledPane titleThirdPane;

    @FXML
    private TitledPane titleFourthPane;

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void initData() {
        //默认选择第一个标题，并且清空右边的内容，同时使得每个标题处于关闭状态
        //清空右边的面板
        anchorpaneR.getChildren().clear();
        //设置4个选项卡都为关闭状态
        titleFirstPane.setExpanded(false);
        titleSecondPane.setExpanded(false);
        titleThirdPane.setExpanded(false);
        titleFourthPane.setExpanded(false);
    }

    //跳转到添加员工页面
    @FXML
    public void addStaffClick() {
        try {
            switchView("add_staff.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //跳转到删除员工页面
    @FXML
    public void deleteStaffClick() {
        try {
            switchView("delete_staff.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //默认界面
    @FXML
    public void listDefault() {

    }

    //退出登录
    @FXML
    public void returnToLoginBtnClick() {
        System.out.println("返回到登录界面~");
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.setTitle("登录");
        stage.setScene(Main.lcScene);
//        设置窗口居中
        stage.centerOnScreen();
        Main.lc.clearContent();
    }

//    员工评分
    @FXML
    public void setStaffScoreClick() {
        try {
            switchView("setStaffScore.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //选择显示页面
    private void switchView(String fileName) throws Exception {

        anchorpaneR.getChildren().clear();
//        System.out.println(1);
        AnchorPane anchorPane = new FXMLLoader(getClass().getResource("/fxml/" + fileName)).load();
//        System.out.println(2);
        anchorpaneR.getChildren().add(anchorPane);
    }

    @FXML
    public void titlePaneClick1() {
        accordion.setPrefHeight(360);
        System.out.println("点击增删用户");
    }

    @FXML
    public void titlePaneClick2() {
        accordion.setPrefHeight(360);
        System.out.println("点击查询员工");
    }

    @FXML
    public void titlePaneClick3() {
        accordion.setPrefHeight(360);
        System.out.println("点击员工评分及排名");
    }

    @FXML
    public void titlePaneClick4() {
        accordion.setPrefHeight(300);
        System.out.println("点击管理员中心");
    }

//    周排行
    @FXML
    public void weekRankClick() {
        try {
            switchView("staff_score_rank.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    按员工号查找
    @FXML
    public void selectById() {
        try {
            switchView("select_by_id.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    按姓名查找
    @FXML
    public void selectByName() {
        try {
            switchView("select_by_name.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AlterPassword() {
        try {
            switchView("alter_password.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
