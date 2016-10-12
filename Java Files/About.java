import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class About{
  
JDialog d;
public About()
{ 
 d=new JDialog();
  String aboutMe="\n"+"@ @ @ @ @ @ @ @ @ @ @ @ @ @"+"\n"+"\n"+"----------------------------------------"+"\n"+"  This Software is Licensed"+"\n"+"  under Vishal Varshney (@Vv)."+"\n"+"  It is a open source Software ,"+"\n"+ "  you can Download the source code"+"\n"+"  from AbbeyJaana PC."+"\n"+"  This is a Beta Version 1.01."+"\n"+"  There is no Microsoft, no Linux,"+"\n"+"     ----->Sab Pe Chalaoo<-----     "+"\n"+"  Please Accept the Terms and Conditions"+"\n"+"  Of Mr. Varshney while trying to"+"\n"+"  access his Software :-D"+"\n"+"----------------------------------------";
 
   JTextArea area=new JTextArea();
  area.setText(aboutMe);
  area.setEditable(false);
  JButton btnOk=new JButton("OK");
  btnOk.setBounds(280,300,50,30);
  btnOk.addActionListener(new ActionListener(){
                           public void actionPerformed(ActionEvent ae){
                           d.dispose();
                           }
                           });

d.add(btnOk);
d.add(area);
d.setSize(350,400);
d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);  // take int value ...Nothing 1 Dispose 2 and Close 3
d.setTitle("About");
d.setModal(true);
d.setLocation(600,180);
d.setVisible(true);

}

}