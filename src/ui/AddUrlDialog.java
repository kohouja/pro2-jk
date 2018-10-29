package ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class AddUrlDialog extends JDialog {
    private String url;
    public AddUrlDialog(){
        setModal(true);

        setLayout(new GridLayout(2, 2));

        JLabel jLabel = new JLabel("URL");
        add(jLabel);

        JTextField jTextField = new JTextField();
        add(jTextField);

        JButton btnOk = new JButton("OK");
        add(btnOk);

        btnOk.addActionListener(action -> {
            this.url = jTextField.getText();
            setVisible(false);
        });

        pack(); //shluk dle vnitřních prvků
        setLocationRelativeTo(null); //center
    }

    public String getUrl(){
        setVisible(true);
        return url;

    }
}
