package runner;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DumbPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	JCheckBox c = new JCheckBox("Do I really need a checkbox here?");
	JLabel l = new JLabel("");
	boolean state=false;
	
	public static DumbPanel create() {
		DumbPanel d = new DumbPanel();
		d.c.addActionListener(d);
		return d;
	}
	
	public DumbPanel() {
		this.setLayout(new GridLayout(2,1));
		this.add(c);
		this.add(l);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		state = !state;
		
		if(state)
			l.setText("No, no I do not");
		else
			l.setText("");
	}
}
