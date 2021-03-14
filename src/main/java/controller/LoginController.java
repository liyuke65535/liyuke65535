package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;


public class LoginController {

    Parent saRoot;

    @FXML
    JFXTextField userID;

    @FXML
    JFXPasswordField userPwd;

    @FXML
    private Label loginMsg;

    @FXML
    AnchorPane anChorPane;

    public void clearContent() {
        //清空一些登录消息
        loginMsg.setText("登录信息已失效");
        //同时清空用户的密码
        userPwd.setText("");
        //idGroup.selectToggle(superAdmin);
    }

    public void loginBtnClick(ActionEvent actionEvent) throws IOException {

//    去除首尾空字符
        String Id = userID.getText().trim(), pwd = userPwd.getText();
        if(Id.isEmpty()) {
            loginMsg.setText("用户名不能为空！");
            System.out.println("用户名不能为空！");
            return;
        }
        if(pwd.isEmpty()) {
            loginMsg.setText("密码不能为空！");
            System.out.println("密码不能为空！");
            return;
        }
        if(Id.equals("admin") && pwd.equals("123"))
        {
            Stage stage = (Stage)anChorPane.getScene().getWindow();
            stage.setTitle("员工考评系统");
            FXMLLoader saLoader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"));
            saRoot = (Parent) saLoader.load();
            Scene sacScene = new Scene(saRoot,900,600);
            stage.setScene(sacScene);

//            设置窗口居中
            stage.centerOnScreen();

            System.out.println("登录成功");

//            stage.show();
        }
        else
        {
            System.out.println("登录失败");
            loginMsg.setText("用户名或密码错误");
            return;
        }

    }
}
