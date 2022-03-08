//this class is for the Home frame of the app
// and contains the main method to run the app

//libraries required
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JButton;
//import javax.swing.SwingConstants;
//import java.awt.GridLayout;
//import java.awt.FlowLayout;
//import java.awt.Font;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AppHome{
    //class variables
    private JFrame homeFrame;
    private JLabel headingLabel;
    private JPanel ButtonPanel;
    private JButton addButton;
    private JButton viewButton;


    AppHome(){
        //calling methods
        homeGUILayout();
        buttonsController();
        headingDesign();
    }

    public void homeGUILayout(){

        //home Frame
        homeFrame= new JFrame("The List");
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(350,350);
        homeFrame.setLayout(new GridLayout(2, 1));

        // initializing components and setting layout
        headingLabel= new JLabel("", SwingConstants.CENTER);
        ButtonPanel= new JPanel();
        ButtonPanel.setLayout(new FlowLayout());

        //adding components to frame
        homeFrame.add(headingLabel);
        homeFrame.add(ButtonPanel);
        homeFrame.setLocationRelativeTo(null);
        homeFrame.setVisible(true);
        homeFrame.setResizable(false);
    }

    private void headingDesign(){
        headingLabel.setText("To-do List");
        Font f = new Font("TimesRoman",Font.ITALIC,30);
        headingLabel.setFont(f);
    }

    private void buttonsController(){

        //Buttons
        addButton = new JButton("Add to list");
        viewButton = new JButton("View List");

        //calling method to perform an action when button is clicked
        addButton.addActionListener(new ActionOnButton());
        viewButton.addActionListener(new ActionOnButton());

        ButtonPanel.add(addButton);
        ButtonPanel.add(viewButton);
    }

    private class ActionOnButton implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(addButton)){
                homeFrame.dispose();
                new AppAddList();
            }else if (e.getSource().equals(viewButton)){
                homeFrame.dispose();
                new AppViewList();
            }
        }
    }

    public static void main(String args[]) {
        new AppHome();
    }
}
