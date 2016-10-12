import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.*;
public class Find extends WindowAdapter implements ActionListener
{
	static JFrame frame;
	JPanel p1,p2,p3,p4;
	JButton b1,b2;
	JTextField text;
	JLabel label;
	JRadioButton r1,r2;
	ButtonGroup bg=new ButtonGroup();
	JCheckBox box;
        String str;
	Find()
	{
		try 
		{
     			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(ClassNotFoundException|InstantiationException|IllegalAccessException|UnsupportedLookAndFeelException e) 
		{
		}
		frame=new JFrame("Find");
		frame.setSize(400,150);
		p1=new JPanel();
		p1.setBorder(new EmptyBorder(10,0,0,0));
		label=new JLabel("Find what   ");
		p1.add(label);
		text=new JTextField(26);
		p1.add(text);
		frame.add(p1,BorderLayout.WEST);
		p2=new JPanel();
		p2.setBorder(new EmptyBorder(10,0,0,10));
		p2.setLayout(new GridLayout(2,1,0,5));
		b1=new JButton("Find Next");
		p2.add(b1);
		b2=new JButton("Cancel");
		p2.add(b2);
                b1.addActionListener(this);
		b2.addActionListener(this);
                frame.add(p2,BorderLayout.EAST);
		p3=new JPanel();
		p3.setLayout(new BorderLayout());
		p4=new JPanel();
		//p4.setBorder(BorderFactory.createTitledBorder("Direction"));
		p4.setBorder(new CompoundBorder(new EmptyBorder(0,0,10,0), BorderFactory.createTitledBorder("Direction")));
		r1=new JRadioButton("up");
		r2=new JRadioButton("down");
                r2.setSelected(true);
		bg.add(r1);
		bg.add(r2);
		p4.add(r1);
		p4.add(r2);
		p3.add(p4,BorderLayout.EAST);
		box=new JCheckBox("Ignore Case");
		p3.add(box,BorderLayout.WEST);
		p3.setBorder(new EmptyBorder(0,0,0,80));
		frame.add(p3,BorderLayout.SOUTH);       
                frame.setLocation(800,100);
                frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.addWindowListener(new WindowAdapter(){
                                         public void windowClosed(WindowEvent we){
                                         // Editor.tf.getHighlighter().removeAllHighlights();
                                         
                                            }
                                         });
                frame.setAlwaysOnTop(true);
               
	}
        
      public void actionPerformed(ActionEvent e)
     {   if(e.getSource()==b1)
         {
          str=text.getText();
          Editor.findWord(str,r1,r2,box);
       }
    
        if(e.getSource()==b2)
           frame.dispose();

   }


}
		