package edu.sie.ws.components;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class MyTextPane extends JTextPane {

	private static final long serialVersionUID = 3;

	public void append(String str)

	    {

	       Document doc = getDocument();

	       if (doc != null)

	       {

	           try

	           {

	              doc.insertString(doc.getLength(), str, null);

	           }

	           catch (BadLocationException e)

	           {

	           }

	       }

	    }

}
