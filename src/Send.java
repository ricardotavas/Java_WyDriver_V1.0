
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import br.com.wyma.wydriver.*;

import static javax.swing.GroupLayout.Alignment.*;

@SuppressWarnings("serial")

public class Send extends JFrame implements ActionListener {

	static JButton btn1;
	static JButton btn2;
	static JTextField txt1;

	public Send() {
		super("Enviar comando");

		btn1 = new JButton("Próximo");
		btn2 = new JButton("Repete");
		txt1 = new JTextField("");

		btn1.addActionListener(this);
		btn2.addActionListener(this);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(LEADING)
										.addComponent(btn1))
								.addGroup(layout.createParallelGroup(LEADING)
										.addComponent(btn2)))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(LEADING)
										.addComponent(txt1))))
				);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(BASELINE)
						.addComponent(btn1)
						.addComponent(btn2))
				.addGroup(layout.createParallelGroup(BASELINE)
						.addComponent(txt1))
				);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Socket sock = new Socket();
		Packet pack;
		switch (e.getActionCommand()) {
		case "Próximo":
			pack = new Packet((byte) 255, (byte) 255, 'S', (byte) 51, (byte) 10, 5, 4, new byte[] { 4,1,1 });
			sock.SEND("192.168.0.113", pack.getPacket());
			txt1.setText(sock.getStatus());
			break;
		case "Repete":
			pack = new Packet((byte) 255, (byte) 255, 'S', (byte) 51, (byte) 10, 6, 3, new byte[] { 4,1,2 });
			sock.SEND("192.168.0.113", pack.getPacket());
			txt1.setText(sock.getStatus());
			break;
		}
	}
}