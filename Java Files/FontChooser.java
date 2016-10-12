import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.ArrayList;

public class FontChooser implements ActionListener,ListSelectionListener
{
 
   static  JFrame f=new JFrame("Font");
     JButton btn1,btn2;
     JTextArea ta;
     JList list1,list2,list3;
     JTextField tf1,tf2,tf3;
     JLabel label,label1,label2,label3;
     int[] str1=new int[]{Font.PLAIN,Font.BOLD,Font.ITALIC};
    Canvas c=new Canvas();
    String name="I am Lazy ,But Love to Code";
    int count=0;
    ListSelectionModel listSelectionModel;
    Font Myfont;

    public FontChooser()
   {
          try 
		{
     			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(ClassNotFoundException|InstantiationException|IllegalAccessException|UnsupportedLookAndFeelException e) 
		{
		}

      f.setLayout(null);
      f.setLocation(600,200);
     Box box1=Box.createVerticalBox();
     Box box2=Box.createVerticalBox();
     Box box3=Box.createVerticalBox();
      
   String[] color=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
      
    String[] myColor=color;
    
     
     String[] str2={"PLAIN","BOLD","ITALIC"};     
     String[] str3={"8","10","12","14","16","18","20","22","24","26","28","30","32","34","36","40","44","48","50","60","72"};
     Font f1=new Font("TIMES NEW ROMAN",Font.BOLD,12);
     

     f.setSize(390,460);
     list1=new JList(color);
     list2=new JList(str2);
     list3=new JList(str3);

     label1=new JLabel("Font:");
     label2=new JLabel("Font Style:");
     label3=new JLabel("Size:");
     label1.setBounds(5,12,40,10);
     label2.setBounds(222,12,90,13);
     label3.setBounds(315,12,40,10);
     label1.setFont(f1);
     label2.setFont(f1);
     label3.setFont(f1);

     JScrollPane pane1=new JScrollPane(list1);
     pane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
     list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
     box1.add(pane1);
     box1.setBounds(5,50,212,180);
     box1.setBorder(BorderFactory.createLineBorder(Color.black));
     f.add(box1);
   

     JScrollPane pane2=new JScrollPane(list2);
     pane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
     list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     box2.add(pane2);
     box2.setBounds(222,50,90,180);
     box2.setBorder(BorderFactory.createLineBorder(Color.black));
     f.add(box2);
     
     JScrollPane pane3=new JScrollPane(list3);
     pane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
     list3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     box3.add(pane3);
     box3.setBounds(315,50,50,140);
     box3.setBorder(BorderFactory.createLineBorder(Color.black));
     f.add(box3);


     btn1=new JButton("OK");
     btn2=new JButton("Cancel");
     btn1.setFont(btn1.getFont().deriveFont(Font.ITALIC));
     btn2.setFont(btn2.getFont().deriveFont(Font.ITALIC));
     btn1.setBounds(190,370,80,25);
     btn2.setBounds(280,370,80,25);
     btn1.setBorder(BorderFactory.createLineBorder(Color.blue));
     btn2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
     btn1.addActionListener(this);
     btn2.addActionListener(this);
                             
     f.add(btn1);
     f.add(btn2);

     tf1=new JTextField();
     tf2=new JTextField();
     tf3=new JTextField();
     tf1.setEditable(false);
     tf2.setEditable(false);
     tf3.setEditable(false);
     tf1.setBounds(5,30,212,20);
     tf2.setBounds(222,30,90,20);
     tf3.setBounds(315,30,50,20);

     list1.setSelectedIndex(2);
     list2.setSelectedIndex(0);
     list3.setSelectedIndex(5);
   
     listSelectionModel=list1.getSelectionModel();
     listSelectionModel.addListSelectionListener(this);
    
     listSelectionModel=list2.getSelectionModel();
     listSelectionModel.addListSelectionListener(this);

     listSelectionModel=list3.getSelectionModel();
     listSelectionModel.addListSelectionListener(this);

    label=new JLabel(name);
    label.setPreferredSize(new Dimension(200,100));
    label.setBounds(30,260,300,105);
    Border border=BorderFactory.createTitledBorder("Preview");
    label.setBorder(border);

     f.add(label1);
     f.add(label2);
     f.add(label3);
     f.add(tf1);
     f.add(tf2);
     f.add(tf3);
     f.add(label);
     f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     f.setResizable(false);
     f.setAlwaysOnTop(true);
   }


public void actionPerformed(ActionEvent e)
 {
     if(e.getSource()==btn2)
       f.dispose();

    if(e.getSource()==btn1)
      {    
        Editor.tf.setFont(Myfont);
        f.setVisible(false);
     }
}
    public void valueChanged(ListSelectionEvent lse)
       {
        String strr1=list1.getSelectedValue().toString();
       int index=list2.getSelectedIndex();
       String strr2=list2.getSelectedValue().toString();
       String strr3=list3.getSelectedValue().toString();
       int Mysize=Integer.parseInt(strr3);
       String mainstr=Editor.tf.getText();
       Myfont=new Font(strr1,str1[index],Mysize);
       label.setFont(Myfont);
       tf1.setText(strr1);
       tf2.setText(strr2);
       tf3.setText(strr3);
        }

}