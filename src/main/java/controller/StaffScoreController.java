package controller;

import com.jfoenix.controls.JFXTabPane;
import dao.Staff;
import emp.EmpRankLook;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.CsvUtil;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class StaffScoreController {

    String path = "/csv/score.csv";

    CsvUtil csvUtil = new CsvUtil();

//    @FXML
//    Button listStaffBtn;

    @FXML
    TextField staffId;

    @FXML
    TextField date;

    @FXML
    TextField hygiene;

    @FXML
    TextField attendance;

    @FXML
    TextField politics;

    @FXML
    TextField contribution;

    @FXML
    TextField cooperation;

    @FXML
    Label wLabelMsg;

    @FXML
    TableView<EmpRankLook> weekRankLook;

    @FXML
    TableColumn weekRank;

    @FXML
    TableColumn id1;

    @FXML
    TableColumn name1;

    @FXML
    TableColumn sex1;

    @FXML
    TableColumn createDate1;

    @FXML
    TableColumn weekScore1;

    @FXML
    TableColumn monthScore1;

    @FXML
    TableView monthRankLook;

    @FXML
    TableColumn monthRank;

    @FXML
    TableColumn id2;

    @FXML
    TableColumn name2;

    @FXML
    TableColumn sex2;

    @FXML
    TableColumn createDate2;

    @FXML
    TableColumn weekScore2;

    @FXML
    TableColumn monthScore2;

    @FXML
    private JFXTabPane jfxTabPane;

    @FXML
    AnchorPane anChorPane;

    ObservableList<EmpRankLook> data = FXCollections.observableArrayList();

    public Boolean makeSure() {
        Boolean jdg = csvUtil.FindId(staffId.getText(),path);
        if(!jdg) {
            System.out.println("员工不存在");
            wLabelMsg.setText("员工不存在!");
            return false;
        }

        int Hygiene = Integer.parseInt(hygiene.getText());
        int Attendance = Integer.parseInt(attendance.getText());
        int Politics = Integer.parseInt(politics.getText());
        int Contribution = Integer.parseInt(contribution.getText());
        int Cooperation = Integer.parseInt(cooperation.getText());
        if(Hygiene > 20 || Hygiene < 0 || Attendance > 20 || Attendance < 0 || Politics > 20 || Politics < 0 || Contribution > 20 || Contribution < 0 || Cooperation > 20 || Cooperation < 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("添加员工评分状态");
            alert.setContentText("添加失败，每项评分必须在0~20之间");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    @FXML
    public void wSet() {
        wLabelMsg.setText("");
        if(!makeSure()) {
            return;
        }
//        System.out.println("添加评分");
        Boolean flag = csvUtil.SetScore(staffId.getText(),date.getText(),hygiene.getText(),attendance.getText(),politics.getText(),contribution.getText(),cooperation.getText());
        if(flag) {
            System.out.println("添加评分成功");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("添加员工评分状态");
            alert.setContentText("添加成功");
            alert.showAndWait();
            return;
        }
    }

    @FXML
    public void weekRankList() {
        data.clear();
        List<List<String>> list = csvUtil.getCsvData(path);

        String sid = list.get(0).get(0);
//        System.out.println(sid);
        int weekScore = 0,monthScore = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<list.size();i++) {
            try {
                Calendar c = Calendar.getInstance();

                //过去一周
                c.setTime(new Date());

                c.add(Calendar.DAY_OF_MONTH, - 7);
                String d = sdf.format(c.getTime());

                //过去一月
                c.setTime(new Date());
                c.add(Calendar.MONTH, - 1);
                String m = sdf.format(c.getTime());

//                System.out.println("d:" + d);
//                System.out.println("m:" + m);
//                System.out.println(list.get(i).get(0) + "," + sid);
                if(list.get(i).get(0).equals(sid)) {
                    if (sdf.parse(list.get(i).get(1)).after(sdf.parse(d))) {
                        weekScore += Integer.parseInt(list.get(i).get(2)) + Integer.parseInt(list.get(i).get(3)) + Integer.parseInt(list.get(i).get(4)) + Integer.parseInt(list.get(i).get(5)) + Integer.parseInt(list.get(i).get(6));
                    }
                    if (sdf.parse(list.get(i).get(1)).after(sdf.parse(m))) {
                        monthScore += Integer.parseInt(list.get(i).get(2)) + Integer.parseInt(list.get(i).get(3)) + Integer.parseInt(list.get(i).get(4)) + Integer.parseInt(list.get(i).get(5)) + Integer.parseInt(list.get(i).get(6));
                    }
                }
                else {
                    Staff staff = csvUtil.FindStaff(sid,"/csv/staff.csv");
    //                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                    EmpRankLook empRankLook = new EmpRankLook(sid,staff.getStaffName(),staff.getSex(),sdf.format(staff.getCreateDate()),weekScore/7,monthScore/30);
//                    System.out.println(empRankLook);
                    weekScore=0;monthScore=0;
                    data.add(empRankLook);
//                    System.out.println(data.get(data.size()-1).getName());
                    sid = list.get(i).get(0);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Staff staff = null;
        try {
            staff = csvUtil.FindStaff(sid,"/csv/staff.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        EmpRankLook empRankLook = new EmpRankLook(sid,staff.getStaffName(),staff.getSex(),sdf.format(staff.getCreateDate()),weekScore/7,monthScore/30);
//                    System.out.println(empRankLook);
        weekScore=0;monthScore=0;
        data.add(empRankLook);
//                    System.out.println(data.get(data.size()-1).getName());
        sid = list.get(list.size()-1).get(0);

        sortByWeekScore(data);
//        TableView weekRankLook为空指针!!!!!!!!!!!
//        System.out.println(weekRankLook);
        weekRankLook.setItems(data);
//        weekRank.setCellValueFactory(new PropertyValueFactory<EmpRankLook, String>("rank"));
        id1.setCellValueFactory(new PropertyValueFactory<EmpRankLook, String>("id"));
        name1.setCellValueFactory(new PropertyValueFactory<EmpRankLook, String>("name"));
        sex1.setCellValueFactory(new PropertyValueFactory<EmpRankLook, String>("sex"));
        createDate1.setCellValueFactory(new PropertyValueFactory<EmpRankLook, String>("date"));
        weekScore1.setCellValueFactory(new PropertyValueFactory<EmpRankLook, Integer>("weekScore"));
        monthScore1.setCellValueFactory(new PropertyValueFactory<EmpRankLook, Integer>("monthScore"));
    }

    public void monthRankList() {
        sortBymonthScore(data);

        monthRankLook.setItems(data);
        id2.setCellValueFactory(new PropertyValueFactory<EmpRankLook, String>("id"));
        name2.setCellValueFactory(new PropertyValueFactory<EmpRankLook, String>("name"));
        sex2.setCellValueFactory(new PropertyValueFactory<EmpRankLook, String>("sex"));
        createDate2.setCellValueFactory(new PropertyValueFactory<EmpRankLook, String>("date"));
        weekScore2.setCellValueFactory(new PropertyValueFactory<EmpRankLook, Integer>("weekScore"));
        monthScore2.setCellValueFactory(new PropertyValueFactory<EmpRankLook, Integer>("monthScore"));
    }

    public void tabClick() {
        if(jfxTabPane.getSelectionModel().isSelected(0)) {
            System.out.println("点击周排行");
            weekRankList();
        }
        else if(jfxTabPane.getSelectionModel().isSelected(1)) {
            System.out.println("点击月排行");
            monthRankList();
        }
    }
    public void sortByWeekScore(ObservableList<EmpRankLook> list) {
        EmpRankLook temp;//定义一个临时变量
        for(int i=0;i<list.size()-1;i++) {//冒泡趟数
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j + 1).getWeekScore() > list.get(j).getWeekScore()) {
                    temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    public void sortBymonthScore(ObservableList<EmpRankLook> list) {
        EmpRankLook temp;//定义一个临时变量
        for(int i=0;i<list.size()-1;i++) {//冒泡趟数
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j + 1).getMonthScore() > list.get(j).getMonthScore()) {
                    temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}