import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class receiverCommand extends JFrame {
	JTextArea t;
	JFrame f;
	
	public void setTextArea(JTextArea t) {
		this.t = t;
	}
	public void setJFrame(JFrame f) {
		this.f = f;
	}
	public void Cut(){
		t.cut();
	}
	public void Copy(){
		t.copy();
	}
	public void Paste() {
		t.paste();
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
				w.write(t.getText());

				w.flush();
				w.close();
			}
			catch (Exception evt) {
				JOptionPane.showMessageDialog(f, evt.getMessage());
			}
		}
		// If the user cancelled the operation
		else
		JOptionPane.showMessageDialog(f, "the user cancelled the operation");
	}
	
	public void Print() {
		try {
			// print the file
			t.print();
		}
		catch (Exception evt) {
			JOptionPane.showMessageDialog(f, evt.getMessage());
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
					File fi = new File(j.getSelectedFile().getAbsolutePath());

					try {
						// String
						String s1 = "", sl = "";

						// File reader
						FileReader fr = new FileReader(fi);

						// Buffered reader
						BufferedReader br = new BufferedReader(fr);

						// Initialize sl
						sl = br.readLine();

						// Take the input from the file
						while ((s1 = br.readLine()) != null) {
							sl = sl + "\n" + s1;
						}

						// Set the text
						t.setText(sl);
					}
					catch (Exception evt) {
						JOptionPane.showMessageDialog(f, evt.getMessage());
					}
				}
				// If the user cancelled the operation
				else
					JOptionPane.showMessageDialog(f, "the user cancelled the operation");
	}
	public void New() {
		t.setText("");
	}
	public void Close() {
		f.setVisible(false);
	}
	public void ScrollBar() {
		JScrollPane pane = new JScrollPane(t, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		f.add(pane);
	}
	public void ZoomIn() {
		
	}
}
