import java.awt.*;;
import javax.swing.*;
import java.awt.event.*;

public class MyFocusListener extends FocusAdapter
  {
    
   Editor e;
   MyFocusListener(Editor e)
   {
     this.e=e;
   }
 

 public void focusLost(FocusEvent fg)
   {
  
     String mystr=e.jtf.getText().trim();
     Editor.tf.setText("public class "+mystr+"\n"+"{"+"\n"+"public static void main(String[] args)"+"\n"+"{"+"\n"+"\n"+"}");
     e.jtf.removeFocusListener(this);
         }
    

  }




