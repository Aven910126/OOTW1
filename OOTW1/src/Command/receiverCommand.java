package Command;

import Observer.Attribute;
import Observer.Title;
import Observer.WindowAttribute;
import Observer.WindowTitle;

import javax.swing.*;
import java.io.*;


public class receiverCommand extends JFrame {
	JTextPane textArea;
	JFrame frame;
	String fileName;
	String[] fileNameTemp;
	String Origin;
	File fi;
	public void getOrigin(String Origin){this.Origin = Origin;}
	public void getFi(File fi){this.fi = fi;}
	public File setFi(){return fi;}
	public String setOrigin(){return Origin;}
	public void setTextArea(JTextPane textArea) {
		this.textArea = textArea;
	}
	public void setJFrame(JFrame frame) {
		this.frame = frame;
	}
	public void Cut(){
		textArea.cut();
	}
	public void Copy(){
		textArea.copy();
	}
	public void Paste() {
		textArea.paste();
	}
	public void Save() {
		// Create an object of JFileChooser class
		JFileChooser j = new JFileChooser("f:");

		// Invoke the showsSaveDialog function to show the save dialog
		int r = j.showSaveDialog(null);

		if (r == JFileChooser.APPROVE_OPTION) {

		// Set the label to the path of the selected directory
		File fi = new File(j.getSelectedFile().getAbsolutePath());

		try {
				// Create a file writer
				FileWriter wr = new FileWriter(fi, false);

				// Create buffered writer to write
				BufferedWriter w = new BufferedWriter(wr);

				// Write
				w.write(textArea.getText());

				w.flush();
				w.close();
			}
			catch (Exception evt) {
				JOptionPane.showMessageDialog(frame, evt.getMessage());
			}
		}
		// If the user cancelled the operation
		else
		JOptionPane.showMessageDialog(frame, "the user cancelled the operation");
	}

	public void Print() {
		try {
			// print the file
			textArea.print();
		}
		catch (Exception evt) {
			JOptionPane.showMessageDialog(frame, evt.getMessage());
		}
	}
	public void Open() {
		// Create an object of JFileChooser class
		JFileChooser j = new JFileChooser("f:");

		// Invoke the showsOpenDialog function to show the save dialog
		int r = j.showOpenDialog(null);

		// If the user selects a file
		if (r == JFileChooser.APPROVE_OPTION) {
			// Set the label to the path of the selected directory
			fi = new File(j.getSelectedFile().getAbsolutePath());
			//To get the fileName
			fileName = fi.toString();
			fileNameTemp = fileName.split("\\\\");
			System.out.println("Open file: " + fileNameTemp[fileNameTemp.length - 1]);

			// Observer Pattern
			WindowAttribute windowTitle = new WindowTitle();
			Attribute title = new Title();
			windowTitle.AddObserver(title);
			windowTitle.Notify(fileNameTemp[fileNameTemp.length - 1]);

			try {
				// String
				String s1 = "", sl = "";

				// File reader
				FileReader fr = new FileReader(fi);

				// Buffered reader
				BufferedReader br = new BufferedReader(fr);

				// Initialize sl
				sl = br.readLine();
				Origin = sl;
				// Take the input from the file
				while ((s1 = br.readLine()) != null) {
					sl = sl + "\n" + s1;
				}

				// Set the text
				textArea.setText(sl);
			} catch (Exception evt) {
				JOptionPane.showMessageDialog(frame, evt.getMessage());
			}
		}
		// If the user cancelled the operation
		else
			JOptionPane.showMessageDialog(frame, "the user cancelled the operation");


	}
	public void New() {
		textArea.setText("");
	}

	public void ScrollBar() {
		JScrollPane pane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.add(pane);
	}

}
