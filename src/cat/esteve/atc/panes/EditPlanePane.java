package cat.esteve.atc.panes;

import cat.esteve.atc.planes.Plane;
import cat.esteve.atc.scene.Scene;
import cat.esteve.atc.utils.Vector3f;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditPlanePane extends JFrame {
    private JPanel contentPane;

    private Scene scene;

    JTextField field_plate, field_brand, field_model, field_pass, field_kerosene;
    JButton btn_apply;

    public EditPlanePane(Scene scene) {
        this.scene = scene;
        this.scene.setPaused(true);

        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 335);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        field_plate = this.addInput("Plate", 25, 28, 30);
        field_brand = this.addInput("Brand", 25, 68, 30);
        field_model = this.addInput("Model", 25, 108, 30);
        field_pass = this.addInput("Max passangers", 25, 148, 30);
        field_kerosene = this.addInput("Kerosene", 25, 188, 30);

        btn_apply = this.addButton("Add plane", 25, 230, 100, 21);
        btn_apply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                scene.addPlane(new Plane(new Vector3f(700, 100, 0), field_plate.getText(), field_brand.getText(), field_model.getText(), Integer.parseInt(field_pass.getText()), Integer.parseInt(field_kerosene.getText())));
                scene.setPaused(false);
                dispose();
            }
        });

        setVisible(true);
    }

    private JTextField addInput(String label, int x, int y, int cols) {
        JLabel lblTime = new JLabel(label);
        lblTime.setBounds(x, y, 200, 14);
        this.contentPane.add(lblTime);

        JTextField res = new JTextField();
        res.setBounds(x, y + 15, 230, 20);
        this.contentPane.add(res);
        res.setColumns(cols);
        return res;
    }

    private JComboBox<String> addComboBox(String label, int x, int y, String[] options) {
        JLabel lblTime = new JLabel(label);
        lblTime.setBounds(x, y, 200, 14);
        this.contentPane.add(lblTime);

        JComboBox<String> res = new JComboBox<>(options);
        res.setBounds(x, y + 15, 100, 21);
        this.contentPane.add(res);
        return res;
    }

    private JButton addButton(String label, int x, int y, int w, int h) {
        JButton res = new JButton(label);
        res.setBounds(x, y, w, h);
        contentPane.add(res);
        return res;
    }
}
