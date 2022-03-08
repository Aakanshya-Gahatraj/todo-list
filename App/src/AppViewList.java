//this class is for View list frame

//libraries required
//import javax.swing.JFrame;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.JPanel;
//import javax.swing.JLabel;
//import javax.swing.JButton;
//import javax.swing.JScrollPane;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.JTableHeader;
//import javax.swing.table.TableModel;
//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class AppViewList {
    //class variables
    private JFrame viewFrame;
    private JTable table;
    private JTextField toDo;
    private JTextField completed;
    private JPanel tablePanel;
    private JPanel toDoPanel;
    private JPanel compPanel;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private JPanel bottomPanel;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton goToHomeButton;
    ArrayList<Task> tasks;


    public AppViewList() {
        //calling methods
        viewListGUILayout();
        displayInTable();
        textFields();
        buttons();
    }

    public void viewListGUILayout() {

        //view frame
        viewFrame = new JFrame("The List");
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.setSize(450, 450);
        viewFrame.setLocationRelativeTo(null);
        viewFrame.setVisible(true);
        viewFrame.setResizable(false);
        viewFrame.setLayout(new BorderLayout());

        //components initialization
        tablePanel = new JPanel();
        toDoPanel = new JPanel();
        compPanel = new JPanel();
        textPanel = new JPanel();
        buttonPanel= new JPanel();
        bottomPanel= new JPanel();

        //components layout
        buttonPanel.setLayout(new FlowLayout());
        toDoPanel.setLayout(new FlowLayout());
        compPanel.setLayout(new FlowLayout());
        textPanel.setLayout(new FlowLayout());
        bottomPanel.setLayout(new BorderLayout());
    }


    //making the table that will display tasks from database
    public void displayInTable() {
        try {
            tasks = Database.readFromTable();
            String[] column = {"Task-to-do","Completed"};
            String[][] data = new String[tasks.size()][2];

            for (int i = 0; i < data.length; i++) {
                Task t = tasks.get(i);
                data[i][0] = t.getTaskDesc();
                data[i][1]= String.valueOf(t.getComp());
            }

            table = new JTable(data, column);
            table.setSize(300,290);

            //adding listener to know the row clicked
            table.addMouseListener(new TableMouseListener());
            tableDesign();
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(400, 300));
            tablePanel.add(scrollPane);
            viewFrame.add(tablePanel,BorderLayout.CENTER);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //designing the table
    public void tableDesign() {

        JTableHeader columnTitle = table.getTableHeader();
        columnTitle.setFont(new Font("Ariel", Font.ITALIC, 15));

        table.setRowHeight(table.getRowHeight() + 20);
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(0);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
    }


    //positioning textfield
    public void textFields(){
        toDo= new JTextField(15);
        completed=new JTextField(6);

        JLabel toDoLabel= new JLabel("To-Do: ");
        JLabel completedLabel= new JLabel("Completed: ");

        //arranging the layout
        toDoPanel.add(toDoLabel);
        toDoPanel.add(toDo);
        compPanel.add(completedLabel);
        compPanel.add(completed);
        textPanel.add(toDoPanel);
        textPanel.add(compPanel);
        bottomPanel.add(textPanel,BorderLayout.NORTH);
    }

    public void buttons(){
        //buttons
        updateButton= new JButton("Update");
        deleteButton= new JButton("Delete");
        goToHomeButton=new JButton("Go To Home");

        //calling method to perform an action when button is clicked
        updateButton.addActionListener(new ActionOnButton());
        deleteButton.addActionListener(new ActionOnButton());
        goToHomeButton.addActionListener(new ActionOnButton());

        //adding components to frame
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(goToHomeButton);
        bottomPanel.add(buttonPanel,BorderLayout.SOUTH);
        viewFrame.add(bottomPanel,BorderLayout.SOUTH);
    }

    private class ActionOnButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(goToHomeButton)) {
                viewFrame.dispose();
                new AppHome();
            } else if (e.getSource().equals(deleteButton)){
                Database.deleteFromTable(tasks.get(table.getSelectedRow()).getID());
                viewFrame.dispose();
                new AppHome();
            }else if (e.getSource().equals(updateButton)){
                //sending string and boolean from the textfields
                // and using arraylist to get taskID of that row
                Database.updateInTable(toDo.getText(),Boolean.parseBoolean(
                        completed.getText()),tasks.get(table.getSelectedRow()).getID());
                viewFrame.dispose();
                new AppHome();
            }
        }
    }

    private void tableRowClicked(MouseEvent evt){
        //helps find the selected row and keep it in the textfields
        int selectedRow= table.getSelectedRow();
        TableModel model=table.getModel();
        toDo.setText(model.getValueAt(selectedRow,0).toString());
        completed.setText(model.getValueAt(selectedRow,1).toString());
    }

    //for calling tableRowClicked
    class TableMouseListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            tableRowClicked(e);
            super.mouseClicked(e);
        }
    }
}







