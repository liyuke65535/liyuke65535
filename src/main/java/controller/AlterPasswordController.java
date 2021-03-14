package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.CsvUtil;

public class AlterPasswordController {

    @FXML
    PasswordField inputWM;

    @FXML
    PasswordField inputWM1;

    @FXML
    Label labelMsgError;

    @FXML
    Label labelMsgError1;

    @FXML
    Label labelMsgSuccess;

    @FXML
    Label labelMsgSuccess1;

    CsvUtil csvUtil = new CsvUtil();

    String path = "/csv/admin.csv";

    Boolean judge = false;

    public void CheckPassword() {
        labelMsgError.setText("");
        labelMsgSuccess.setText("");
        Boolean flag = csvUtil.checkPassword(inputWM.getText(),path);
        if(flag) {
            labelMsgSuccess.setText("密码正确!");
            judge = true;
        }
        else {
            inputWM.setText("");
            inputWM1.setText("");
            labelMsgError.setText("密码错误!");
            judge = false;
        }
    }

    public void SetPassword(ActionEvent actionEvent) {
        labelMsgSuccess1.setText("");
        labelMsgError1.setText("");
        if(!judge) {
            inputWM.setText("");
            inputWM1.setText("");
            labelMsgError1.setText("原密码错误!");
            return;
        }
        Boolean flag = csvUtil.
                SetAdminPassword(inputWM1.getText(),path);
        if(flag) {
            labelMsgError.setText("");
            labelMsgSuccess.setText("");
            inputWM.setText("");
            inputWM1.setText("");
            labelMsgSuccess1.setText("密码修改成功!");
        }
        else {
            labelMsgError.setText("");
            labelMsgSuccess.setText("");
            inputWM.setText("");
            inputWM1.setText("");
            labelMsgSuccess1.setText("密码修改失败!");
        }
        judge = false;
    }
}
