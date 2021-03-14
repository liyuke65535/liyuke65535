package controller;

import com.jfoenix.controls.JFXTextField;
import dao.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utils.CsvUtil;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;


public class DeleteStaffController {
    @FXML
    Label wLabelMsg;

    @FXML
    JFXTextField wname;

    @FXML
    JFXTextField wdate;

    @FXML
    JFXTextField wid;

    @FXML
    JFXTextField wsex;

    @FXML
    TextField inputWM;

    CsvUtil csvUtil = new CsvUtil();

    String path = "/csv/staff.csv";

    public void wSelect() throws FileNotFoundException {
        String staffId = inputWM.getText();
//        System.out.println(staffId);
        Staff staff = csvUtil.FindStaff(staffId,path);
        if(staff == null) {
            wLabelMsg.setText("输入的员工号有误!");
        }
        else {
            System.out.println("显示员工" + staff.getStaffId() + " " + staff.getStaffName());
            wLabelMsg.setText("");
            wname.setText(staff.getStaffName());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            wdate.setText(sdf.format(staff.getCreateDate()));
            wid.setText(staff.getStaffId());
            if(staff.getSex() == "man") {
                wsex.setText("男");
            }
            else {
                wsex.setText("女");
            }
        }
    }

    public void wDelete() {
        String staffId = wid.getText();
//        System.out.println(staffId);
        Boolean flag = csvUtil.DeleteStaff(staffId,path);
        if(flag) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("删除工作人员状态");
            alert.setContentText("成功删除员工号为" + staffId + "的员工");
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("删除工作人员状态");
            alert.setContentText("删除失败，请勿重复删除操作!");
            alert.showAndWait();
        }
    }
}
