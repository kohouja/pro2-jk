package ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.ToDoItem;


public class ProDialog extends JDialog {

    private ToDoItem item;

    public ProDialog() {
        setModal(true);

        setLayout(new GridLayout(2, 2));

        JLabel jLabel = new JLabel("Obsah");
        add(jLabel);

        JTextField jTextField = new JTextField();
        add(jTextField);

        JButton btnOk = new JButton("OK");
        add(btnOk);

        btnOk.addActionListener(action -> {
            // plnit ToDoItem
            item = new ToDoItem(jTextField.getText());
            setVisible(false);
        });

        pack(); //shluk dle vnitřních prvků
        setLocationRelativeTo(null); //center
    }

    public ToDoItem getItem() {
        setVisible(true); //zmrazí původní vlákno
        return item;
    }
}
