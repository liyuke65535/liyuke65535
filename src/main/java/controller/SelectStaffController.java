package controller;

import com.jfoenix.controls.JFXTextField;
import dao.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utils.CsvUtil;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class SelectStaffController {
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

    @FXML
    public void SelectById() throws FileNotFoundException {
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

    @FXML
    public void SelectByName() throws FileNotFoundException {
        String staffName = inputWM.getText();
//        System.out.println(staffId);
        Staff staff = csvUtil.FindStaffByName(staffName,path);
        if(staff == null) {
            wLabelMsg.setText("输入的员工姓名有误!");
        }
        else {
            System.out.println("显示员工" + staff.getStaffId() + " " + staff.getStaffId());
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
}
