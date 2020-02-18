
//@author: Anamol Acharya
//CS2365-002-Spring-2020
//Project 1

package html2text;

import java.io.*;                                               //This will import all the classes of Input/output package and use in i/o stresm
import javax.swing.text.html.*;                                 //This provides class HTMLEditorKit and support class for creating HTML text editors
import javax.swing.text.html.parser.*;                          //include classes and packages for parsing HTML text
import java.lang.*;                                             //creates an applet tocommunicatw with applet context
import java.util.*;                                             //it imports the collections framework
import java.io.Writer.*;
import java.io.BufferedWriter.*;
import java.io.FileWriter.*;

import java.util.Arrays;
import javax.swing.text.MutableAttributeSet;
  

public class Html2Text extends HTMLEditorKit.ParserCallback  /*Constructor*/ {   //Used HTMLEditorKit to call HTML2Text to have a parent child relationship using inheritence.

    boolean inTr = false;
    
  public static void main (String[] args) {  
    
   //handleText(char[] text, int pos);
   final Formatter x;                                                               
   try{                                                                  //using try-catch to create the output file and read the given html file 
       FileWriter writer = new FileWriter("output.txt", false);           //this creates an output.txt file in the project folder.
       try {  
       Html2Text parser;
       try ( // the HTML to convert
           FileReader in = new FileReader("C:\\Users\\anamo\\Desktop\\matador.html")) {     //this reads the matador.html file in the  desktop
           parser = new Html2Text();                                     //calling the html2text method in main
           parser.parse(in);                                             //calling the parse method in main
           System.out.println("\n\nJust a Try string-anamol");
          // writer.write(parser.getText());
       }
    writer.write(parser.getText());                                   //This prints the string read from the html file
   
   }  
   catch (IOException e) {  
   }
            
            writer.close();                                           //this closes the writer   
       
       System.out.println("You have just created a text file");
   }
   catch(Exception e){
       System.out.println("You have an error");
       
   }
  
 }                                                           
 StringBuffer s;  
  
 public Html2Text() {}  
  
 public void parse(Reader in) throws IOException {                //parse method to donvert data into string datatype
   s = new StringBuffer();  
   ParserDelegator delegator = new ParserDelegator();  
   // the third parameter is TRUE to ignore charset directive  
   delegator.parse(in, this, Boolean.TRUE);  
 }  
  
    /**
     *
     * @param text
     * @param pos
     */
    @Override    
     public void handleText(char[] text, int pos) {                 //handle text method to print the letters in text format in the output

       //s.append(text);
       System.out.print(text);

       String CSV = "| ";                                           //this give a new line  in each new line present in html file
       StringTokenizer tokenizer = new StringTokenizer(CSV, "|");       
       while (tokenizer.hasMoreTokens()){
           System.out.println(tokenizer.nextToken());
       }
       s.append(text);
       if(!inTr) {
           s.append("\n");
       } else {
           s.append(", ");
       }


       } 

       public String getText() {                                   //gettext method to read  from the html file
     //  String getText = Html2Text.replaceAll("        ", "\n");

       return s.toString();  
     }  
       
    @Override                                                           //this over ride is for changing the start tag that is in html
    public void handleStartTag(HTML.Tag tag, MutableAttributeSet mas, int i) {
        if(tag == HTML.Tag.TR) {                                        //check f the start tag is TR or not
            inTr = true;
        }
    }

    @Override
    public void handleEndTag(HTML.Tag tag, int i) {
        if(tag == HTML.Tag.TR) {
            inTr = false;
            s.delete(s.length() - 2, s.length());                        //this will decrease the string length by 2 in each line
            s.append("\n");                                              //this will create the new line in each of the end tag
        }
    }
 
 }  
  



  