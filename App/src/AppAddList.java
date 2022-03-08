//this class is for Add list frame

//libraries required
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JButton;
//import javax.swing.JTextField;
//import javax.swing.JOptionPane;
//import javax.swing.SwingConstants;
//import java.awt.GridLayout;
//import java.awt.FlowLayout;
//import java.awt.Font;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AppAddList {
    //class variables
    private JFrame addFrame;
    private JLabel headingLabel;
    private JTextField addTodoTextField;
    private JPanel buttonTextPanel;
    private JLabel resultLabel;
    private JButton addButton;
    private JButton goBackButton;

    AppAddList(){
        //calling methods
        AddListGUILayout();
        buttonController();
        headingDesign();
    }

    public void AddListGUILayout(){

        //add frame
        addFrame= new JFrame("The List");
        addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addFrame.setSize(350,350);
        addFrame.setLayout(new GridLayout(4, 1));

        //initializing components
        headingLabel= new JLabel("", SwingConstants.CENTER);
        resultLabel=new JLabel("",SwingConstants.CENTER); // To show if to-do was added or not
        addTodoTextField= new JTextField(15);
        buttonTextPanel= new JPanel();

        buttonTextPanel.setLayout(new FlowLayout());
        buttonTextPanel.add(addTodoTextField);

        //adding components to frame
        addFrame.add(headingLabel);
        addFrame.add(buttonTextPanel);
        addFrame.add(resultLabel);
        addFrame.setLocationRelativeTo(null);
        addFrame.setVisible(true);
    }

    private void headingDesign(){
        headingLabel.setText("What's your plan today?");
        headingLabel.setFont(new Font("TimesRoman",Font.ITALIC,16));
    }

    private void buttonController(){

        //buttons
        addButton = new JButton("Add to-do");
        goBackButton= new JButton("Go to Home");

        //calling method to perform an action when button is clicked
        addButton.addActionListener(new ActionOnButton());
        goBackButton.addActionListener(new ActionOnButton());

        //adding buttons to panel
        buttonTextPanel.add(addButton);
        buttonTextPanel.add(goBackButton);
    }


    private class ActionOnButton implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(addButton)){
                try {
                    if(!addTodoTextField.getText().equals("")){
                        Database.insertInTable(addTodoTextField.getText());
                        resultLabel.setText("Added");
                    }else{
                        JOptionPane.showMessageDialog(addFrame,"Please enter the text.");
                        resultLabel.setText("");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }else if (e.getSource().equals(goBackButton)){
                addFrame.dispose();
                new AppHome();
            }
        }
    }
}




