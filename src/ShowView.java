import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ShowView implements FocusListener {

	JFrame f = new JFrame("Generate and Check MD5");
	JLabel labelString1 = new JLabel("Select a file to compute MD5 checksum");
	JLabel labelString = new JLabel("Current file MD5 checksum value");
	JLabel labelString2 = new JLabel("Original file MD5 checksum value (Optional)");
	JTextField directoryString = new JTextField("");
	JTextField md5StringText = new JTextField("");
	JTextField originalMd5StringText = new JTextField("Paste the original MD5 checksum...");
	JButton browseDirectoryButton = new JButton("Browse...");
	JButton checkMD5 = new JButton("Check");
	public String md5Sting = "";

	public static int GetScreenWorkingWidth() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds().width;
	}

	public static int GetScreenWorkingHeight() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds().height;
	}

	public ShowView() {

		int width = GetScreenWorkingWidth() / 2 - 150;
		int height = GetScreenWorkingHeight() / 2 - 85;

		f.setBounds(width, height, 340, 350);
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLayout(null);

		f.add(labelString1);
		labelString1.setBounds(20, 20, 300, 30);

		f.add(directoryString);
		directoryString.setBounds(20, 50, 180, 30);

		
		f.add(browseDirectoryButton);
		browseDirectoryButton.setBounds(200, 50, 90, 30);
		
		f.add(labelString);
		labelString.setBounds(20, 90, 300, 30);

		f.add(md5StringText);
		md5StringText.setEditable(false);
		md5StringText.setBounds(20, 130, 260, 30);
		
		f.add(labelString2);
		labelString2.setBounds(20, 170, 330, 30);
		
		f.add(originalMd5StringText);
		originalMd5StringText.setBounds(20, 210, 260, 30);
		originalMd5StringText.addFocusListener(this);
		
		f.add(checkMD5);
		checkMD5.setBounds(200, 250, 90, 30);
		checkMD5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(md5Sting != null) {
					String given = originalMd5StringText.getText();
					if(md5Sting.equals(given)) {
						JOptionPane.showMessageDialog(f, "Verified");
					} else {
						JOptionPane.showMessageDialog(f, "Not Equal");
					}
				}
				
			}
		});
		
		final JFileChooser fileDialog = new JFileChooser();
		browseDirectoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileDialog.showOpenDialog(f);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					java.io.File file = fileDialog.getSelectedFile();
					String path = file.getAbsolutePath();
					directoryString.setText(path);
					directoryString.setEditable(false);
					try {
						GetMD5String m = new GetMD5String();
						md5Sting = m.getMD5OfFile(path);
						md5StringText.setText(md5Sting);
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
				}
			}
		});

	}

	@Override
	public void focusGained(FocusEvent e) {
		if(originalMd5StringText.getText().equals("Paste the original MD5 checksum...") || originalMd5StringText.getText().length() <= 0)
			originalMd5StringText.setText("");
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if(originalMd5StringText.getText().length() <= 0)
			originalMd5StringText.setText("Paste the original MD5 checksum...");
	}

}
