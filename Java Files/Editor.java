import java.awt.*;
import java.awt.print.PrinterJob;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File; 
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Date;
import javax.swing.undo.*;


public class Editor extends MouseAdapter implements ActionListener
 {    
           
    JFrame f;
    JSplitPane split;
    JPanel panel1,panel2;
    static JTextArea tf;
    JTextField jtf;
    JLabel labelPro;
    JPanel panelMain;
    JTextArea consoleArea;
    JFileChooser jfile;
    JPopupMenu pop;
    JScrollPane sp1,sp2;
    JMenuBar menuBar;
    JMenu New,menu1,menu2,menu3,menu4,menu5;
    JMenuItem menuItem[]=new JMenuItem[26];
    JCheckBoxMenuItem cbWordWrap;
    JRadioButton normalB,javaB;
    int check=0;
    static int z=0;
    static int caretPos;
    Runtime r=Runtime.getRuntime();
    String fname="";
    String str321="";
    UndoManager manager;
  
    Editor()
    {

                       

             try 
		{
     			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        
		}
		catch(ClassNotFoundException|InstantiationException|IllegalAccessException|UnsupportedLookAndFeelException e) 
		{
		}

    f=new JFrame();
    f.setTitle("My Editor");
    f.setLayout(new BorderLayout());
    jfile=new JFileChooser();
 
    labelPro=new JLabel("Project Name: ",JLabel.LEFT);
    labelPro.setFont(new Font("Cooper",2,12));
    jtf=new JTextField(100);
    jtf.addFocusListener(new MyFocusListener(this));

    panelMain=new JPanel();
    panelMain.add(labelPro);
    panelMain.add(jtf);
    panelMain.setBackground(Color.WHITE);

    Font Areafont=new Font("Arial",0,14);
   
    tf=new JTextArea();
    tf.setFont(Areafont); 
    tf.setBorder(BorderFactory.createTitledBorder("Text Area"));
    
    consoleArea=new JTextArea();
    consoleArea.setBorder(BorderFactory.createTitledBorder("Console"));

    sp1=new JScrollPane(tf);    
    sp1.setPreferredSize(new Dimension(1280,422));
    sp2=new JScrollPane(consoleArea);
    sp2.setPreferredSize(new Dimension(1280,200));
    sp1.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
    sp2.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
    sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    consoleArea.setLineWrap(true);
    consoleArea.setWrapStyleWord(true);
    consoleArea.setEditable(false); 
         
   
    
   f.add(panelMain,BorderLayout.NORTH);
   // f.add(sp2,BorderLayout.PAGE_END);
     tf.requestFocusInWindow();

    split=new JSplitPane(JSplitPane.VERTICAL_SPLIT,sp1,sp2);
   // split.setTopComponent(sp1);
   // split.setBottomComponent(sp2);
    split.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
    f.add(split);

    manager=new UndoManager();
    tf.getDocument().addUndoableEditListener(new UndoableEditListener(){
                                            public void undoableEditHappened(UndoableEditEvent ue){
                                             manager.addEdit(ue.getEdit());
                                             }
                                          });

   
    MyMenu();
    JPopUPMenu();
    new FontChooser();
    new Find();

    f.setSize(1280,720); 
    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    f.setVisible(true); 
   }
   
 
 public void MyMenu()
 {
  
 
    menuBar=new JMenuBar();  
  
    menu1=new JMenu("File  ");
    menu1.setMnemonic(KeyEvent.VK_F);
  
    New=new JMenu("New");
    normalB=new JRadioButton("   Normal Project");
    
    javaB=new JRadioButton("   Java Project");
    javaB.setSelected(true);
    ButtonGroup bg=new ButtonGroup();
    bg.add(normalB);
    bg.add(javaB);
   
    New.add(normalB);
    New.add(javaB);
    menu1.add(New);
    New.addSeparator();
   
    menuItem[0]=new JMenuItem("Open");
    menuItem[1]=new JMenuItem("Save");
    menuItem[2]=new JMenuItem("Save as");
    menuItem[3]=new JMenuItem("Print");
    menuItem[4]=new JMenuItem("Exit");
    
    menuItem[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
    menuItem[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
    menuItem[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
    menuItem[4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
   

    menu1.add(menuItem[0]);
    menu1.addSeparator();

    menu1.add(menuItem[1]);
     
    menu1.add(menuItem[2]);
    menu1.addSeparator();
   
    menu1.add(menuItem[3]);
    menu1.addSeparator();
    
    menu1.add(menuItem[4]);
   
    menuBar.add(menu1);                        //Adding Whole File Menu1 in Menu Bar
   
    menu2=new JMenu("Edit  ");
    menu2.setMnemonic(KeyEvent.VK_E);
    
    menuItem[5]=new JMenuItem("Undo");
    menuItem[6]=new JMenuItem("Cut");
    menuItem[7]=new JMenuItem("Copy"); 
    menuItem[8]=new JMenuItem("Paste");
    menuItem[9]=new JMenuItem("SelectAll");
    menuItem[10]=new JMenuItem("Delete");
    menuItem[11]=new JMenuItem("Find");
    menuItem[12]=new JMenuItem("Time/Date");
    
   
    menu2.add(menuItem[5]);
    menu2.addSeparator();
    
    menu2.add(menuItem[6]);
    menu2.add(menuItem[7]);
    menu2.add(menuItem[8]);
    menu2.addSeparator();
 
    menu2.add(menuItem[9]);
    menu2.add(menuItem[10]);
    menu2.addSeparator();

    menu2.add(menuItem[11]);
    menu2.add(menuItem[12]);

    menuItem[5].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
    menuItem[6].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
    menuItem[7].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
    menuItem[8].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
    menuItem[9].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
    menuItem[11].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
    menuItem[12].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.SHIFT_MASK+ActionEvent.ALT_MASK));

    menuBar.add(menu2);                                    // Adding Whole Edit Menu2 in Menu Bar 

    menu3=new JMenu("Format  ");
    menu3.setMnemonic(KeyEvent.VK_R);
  
    cbWordWrap=new JCheckBoxMenuItem("Word Wrap");
    menuItem[13]=new JMenuItem("Font");

    menu3.add(cbWordWrap);
    menu3.add(menuItem[13]);
   
    menuBar.add(menu3);          
                   
    menu4=new JMenu("Run  ");
    menuItem[14]=new JMenuItem("Run");
    menuItem[15]=new JMenuItem("Compile");
    menuItem[16]=new JMenuItem("Clear Console");
    menu4.add(menuItem[14]);
    menu4.addSeparator();
    menu4.add(menuItem[15]);
    menu4.addSeparator();
    menu4.add(menuItem[16]);
   
    menuItem[14].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));  
    menuItem[15].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK+ActionEvent.SHIFT_MASK));
  
    menuBar.add(menu4);
 
    menu5=new JMenu("About");
  
    menuItem[17]=new JMenuItem("About");
    menu5.add(menuItem[17]);
     
    menuBar.add(menu5);


    menuItem[18]=new JMenuItem("New Project");
    New.add(menuItem[18]);

    menuItem[19]=new JMenuItem("Redo");
    menuItem[19].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,ActionEvent.CTRL_MASK));
    menu2.insert(menuItem[19],1);
    menu2.addSeparator();


    tf.setMargin(new Insets(5,5,5,5));                 // TextArea Margin From All Side
    f.setJMenuBar(menuBar);
   

    for(int i=0;i<20;i++)                            // Action Listener on all menuItem
     {
       menuItem[i].addActionListener(this);
      }
    normalB.addActionListener(this);
    javaB.addActionListener(this);
    cbWordWrap.addActionListener(this);                // CheckBox Action Listener 
  
   }

 public void JPopUPMenu()
   {
    menuItem[20]=new JMenuItem("Undo");
    menuItem[21]=new JMenuItem("Redo");
    menuItem[22]=new JMenuItem("Cut");
    menuItem[23]=new JMenuItem("Copy"); 
    menuItem[24]=new JMenuItem("Paste");
    menuItem[25]=new JMenuItem("SelectAll");

    pop=new JPopupMenu();
    pop.add(menuItem[20]);
    pop.add(menuItem[21]);
    pop.addSeparator();
    pop.add(menuItem[22]);
    pop.add(menuItem[23]);
    pop.add(menuItem[24]);
    pop.add(menuItem[25]);

   
     for(int i=20;i<26;i++)                            // Action Listener on all menuItem
     {
       menuItem[i].addActionListener(this);
      }
   
    pop.setPopupSize(new Dimension(220,150));
    tf.add(pop);
    jtf.add(pop);
 
    tf.setComponentPopupMenu(pop);
    jtf.setComponentPopupMenu(pop);
    f.addMouseListener(this);
    }


public void actionPerformed(ActionEvent e)
{
 
 if(normalB.isSelected())
  { 
   f.remove(panelMain);
   f.remove(split);
   f.add(sp1,BorderLayout.CENTER);
   f.revalidate();
   f.repaint();
   }

  if(javaB.isSelected())
    {  
      f.remove(sp1);
      f.add(panelMain,BorderLayout.NORTH);
      split.setTopComponent(sp1);
      f.add(split);
      tf.requestFocusInWindow();
      f.revalidate();
      f.repaint();
    }
      
  if(e.getActionCommand().equals("New Project"))
    new Editor();
 
 if(e.getActionCommand().equals("Open"))
  openFile();

 if(e.getActionCommand().equals("Save"))
 save();
       
 if(e.getActionCommand().equals("Save as"))
   saveAs();

 if(e.getActionCommand().equals("Print"))
  printText();     

 if(e.getActionCommand().equals("Exit"))
  System.exit(0);
 
 if(e.getActionCommand().equals("Undo"))
    {  try {
                    if (manager.canUndo()) {
                        manager.undo();
                    }
                } catch (CannotUndoException ce) {
                }
     }

if(e.getActionCommand().equals("Redo"))
  {
    try{
        if(manager.canRedo()) {
           manager.redo();
             }
      }
     catch(CannotRedoException re){
    }
  }


 
 if(e.getActionCommand().equals("Cut"))
  cutText();

 if(e.getActionCommand().equals("Copy"))
   copyText();
 
 if(e.getActionCommand().equals("Paste"))
   pasteText();

 
 if(e.getActionCommand().equals("SelectAll"))
 tf.selectAll();

if(e.getActionCommand().equals("Time/Date"))
  {
    Date date=new Date();
     tf.append(date.toString());
  
 }

 if(e.getActionCommand().equals("Find"))
  Find.frame.setVisible(true);

 if(e.getSource()==cbWordWrap)
   wordWrap();

if(e.getActionCommand().equals("Font"))
    FontChooser.f.setVisible(true);

if(e.getActionCommand().equals("Run"))
  debugRun();
 
if(e.getActionCommand().equals("Compile"))
  debugCompile();

if(e.getActionCommand().equals("Clear Console"))
 consoleArea.setText("");
 
if(e.getActionCommand().equals("About"))
  new About();
  
  }

public void mousePressed(MouseEvent me)
     { 
       int x=me.getButton();
        if(x==MouseEvent.BUTTON3)
          { 
          pop.show(me.getComponent(),me.getXOnScreen(),me.getYOnScreen());}
     }

   


   public void openFile()                          // Open File Function
     {
  
   jfile.setDialogTitle("Open");
   int x=jfile.showOpenDialog(f);
   
   if(x==JFileChooser.APPROVE_OPTION)
     { 
     tf.setText(null);
      try{
     File fo=jfile.getSelectedFile();
     String fileName=fo.getName();    //getting name of file;
      f.setTitle(fileName+"- My Editor");
      File newFile=jfile.getCurrentDirectory();
      String abc=newFile.getPath();
  
      Path p=Paths.get(abc);
      String path=p.toString(); 
    
      String pass=path+File.separator+fileName; 
   
      FileInputStream fin=new FileInputStream(pass);
      Scanner scan=new Scanner(fin);
      while(scan.hasNextLine())
     {
         tf.append(scan.nextLine()+"\n");
     }    
     
    fin.close();  
    check++;
  tf.setCaretPosition(0);
 }
catch(Exception rrr){}
 } 

}




       public void save()                             // Save Only Function
     {   
           if(check==0)
               {
                  saveAs();
                  check++;
                }
     else {
          try{
          File data=jfile.getSelectedFile();
          String fileName=data.getName(); 
          f.setTitle(fileName+"- My Editor");
          String[] str=tf.getText().split("\n");     //reading from textFiled
          File newFile=jfile.getCurrentDirectory();
          String abc=newFile.getPath();
  
          Path p=Paths.get(abc);
          String path=p.toString(); 
    
          String pass=path+File.separator+fileName; 
   
         FileOutputStream fout=new FileOutputStream(pass);
         PrintStream ps=new PrintStream(fout);
      
       for(int i=0;i<str.length;i++)
       {
       ps.println(str[i]);
       }
        check++;
       ps.close();
        }   
        catch(Exception rr){}
         }   
     } 



 public void saveAs()                                        //Save As Function
{
   jfile.setDialogTitle("Save as");
    int x=jfile.showSaveDialog(f);
    if(x==JFileChooser.APPROVE_OPTION)
     { 
     try{
     File fo=jfile.getSelectedFile();
     String fileName=fo.getName();    //getting name of file;
     f.setTitle(fileName+"- My Editor");
    if(fo.exists())
     {
      int select=JOptionPane.showConfirmDialog(null,"Do you Want To Replace?","File already Exists",JOptionPane.YES_NO_OPTION);
      switch(select)
       {
        case JOptionPane.YES_OPTION:
                break;
        case JOptionPane.NO_OPTION:
             return;
     }
    }
      String[] str=tf.getText().split("\n");    //reading from textFiled
      File newFile=jfile.getCurrentDirectory();
      String abc=newFile.getPath();
  
      Path p=Paths.get(abc);
      String path=p.toString(); 
    
      String pass=path+File.separator+fileName; 
  //  System.out.println(fileName+"\n"+newFile+"\n"+path+"\n"+pass);

     FileOutputStream fout=new FileOutputStream(pass);
      PrintStream ps=new PrintStream(fout);
      
     for(int i=0;i<str.length;i++)
      {
       ps.println(str[i]);
       }
        check++;
       ps.close();
   }
catch(Exception rr){}
 }
}

   public void printText()
     {
       PrinterJob pj=PrinterJob.getPrinterJob();
       pj.printDialog();
        }


   public void cutText()
         {
           tf.cut();                             //a method of Technology  or JTextComponent
        }

   public void copyText()
   {
    tf.copy();                                     //a method of Technology  or JTextComponent
   }
 
  public void pasteText()
   {
   
   tf.paste();                                    //a method of Technology  or JTextComponent
   }

 public static void findWord(String textStr,JRadioButton rb1,JRadioButton rb2,JCheckBox box1)
   {
     
     tf.getHighlighter().removeAllHighlights();
     try{
     caretPos=tf.getCaretPosition();
     JRadioButton Up=rb1;
     JRadioButton Down=rb2;
     JCheckBox cb=box1;
     String textStr1=textStr;
     String textAreaString=tf.getText();
     int len=textStr1.length();
   
     
     if(Down.isSelected())
     {
     int x=textAreaString.indexOf(textStr1,caretPos);
     Highlighter.HighlightPainter painter1 = new DefaultHighlighter.DefaultHighlightPainter(Color.green);
     tf.getHighlighter().addHighlight(x,x+(len),painter1);
     tf.setSelectionColor(Color.YELLOW); 
    tf.setCaretPosition(x+len);
          }    
else if(Up.isSelected())
     {
      int y=textAreaString.lastIndexOf(textStr1,caretPos);
      Highlighter.HighlightPainter painter2=new DefaultHighlighter.DefaultHighlightPainter(Color.green);
      tf.getHighlighter().addHighlight(y,y+len,painter2);
      tf.setCaretPosition(y-len);
      }
       
     }
    catch(Exception eee)
       {
       ImageIcon icon=new ImageIcon("G:\\Programming\\Projects\\Editor\\Icon\\tuzki2.gif");
       JOptionPane.showMessageDialog(null,"\t"+"There Is No More To Find!!","Message",JOptionPane.PLAIN_MESSAGE,icon);
       z=0;
       }
   }
     
   
   public void wordWrap()
    { 
      if(cbWordWrap.isSelected())
       {
       sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       tf.setWrapStyleWord(true);
       tf.setLineWrap(true);
      }
    else 
       {
        sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tf.setWrapStyleWord(false);
        tf.setLineWrap(false);
       }
    }
   
  
  public void debugCompile()
    {
    
     

        if(!jtf.getText().equals(""))
          {
           try{
              String result1="" ;
             fname=jtf.getText().trim()+".java";
      
    
    File data1=jfile.getCurrentDirectory();
    String abc1=data1.getPath();
    String mainp="\""+abc1+File.separator+fname+"\"";
      
        Process error=r.exec("C:\\Program Files\\Java\\jdk1.8.0_71\\bin\\javac.exe "+mainp);
        BufferedReader err=new BufferedReader(new InputStreamReader(error.getErrorStream()));
 

          while(true)
             {
              String temp=err.readLine();
              if(temp!=null)
               {
                 result1+=temp;
                 result1+="\n";
               }
          else break;
             }
              if(result1.equals(""))
                {
                 consoleArea.setText("Compiled Successfully:  "+fname+"\n");
                 err.close();
                }
             else
                consoleArea.setText(result1);
              }
             
          catch(Exception e1)
            { System.out.println(e1);}
 
      } 
      
 }
         public void debugRun()
           {
             
              try{
                 
                 String result2="";
                 String result3="";
                File file1=jfile.getCurrentDirectory();
                String strPath=file1.getPath();
               
               for(int i=0;i<strPath.length();i++)
                 {
                   if(strPath.charAt(i)==' ')
                      { strPath="\""+strPath+"\"";
                        break;}
                    }

                String fn=jtf.getText().trim();
                Process p=r.exec("C:\\Program Files\\Java\\jdk1.8.0_71\\bin\\java -classpath "+strPath+" "+fn);
                BufferedReader output=new BufferedReader(new InputStreamReader(p.getInputStream()));
                BufferedReader error=new BufferedReader(new InputStreamReader(p.getErrorStream()));
                        
                            while(true)
             {
              String temp=output.readLine();
                 if(temp!=null)
                    {
                      result2+=temp;
                      result2+="\n";
                     }
              else  break;
            }
   
          while(true)
            { 
             String temp=error.readLine();
             if(temp!=null)
             { 
              result3+=temp;
              result3+="\n"; 
             }
      else break;
             }
      
        output.close();
        error.close();
        
     if(!result2.equals("")&&result3.equals(""))
      consoleArea.append(result2);
    if(result2.equals("")){
   consoleArea.append(result3); System.out.print(result3);}
    if(!result2.equals("")&&!result3.equals(""))
     consoleArea.append(result2+"\n"+result3);

  }
  catch(Exception e2)
 { 
  System.out.println(e2);
  }
  
} 



}