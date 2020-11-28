package cat.esteve.atc.panes;

import cat.esteve.atc.airports.Airport;
import cat.esteve.atc.airports.GermanyAirport;
import cat.esteve.atc.airports.SpainAirport;
import cat.esteve.atc.airports.USAirport;
import cat.esteve.atc.planes.Plane;
import cat.esteve.atc.scene.Scene;
import cat.esteve.atc.utils.Vector3f;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class CreatePlanePane extends JFrame {
    private JPanel contentPane;

    private Scene scene;

    JTextField field_plate, field_brand, field_model, field_pass, field_kerosene;
    JComboBox<String> cb_airport;
    JButton btn_apply;

    public CreatePlanePane(Scene scene) {
        this.scene = scene;
        this.scene.setPaused(true);

        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");//UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(300, 355);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        field_plate = this.addInput("Plate", 25, 28, 30);
        field_brand = this.addInput("Brand", 25, 68, 30);
        field_model = this.addInput("Model", 25, 108, 30);
        field_pass = this.addInput("Max passengers", 25, 148, 30);
        field_kerosene = this.addInput("Kerosene", 25, 188, 30);

        String[] options = new String[] {"---", new SpainAirport().getName(), new GermanyAirport().getName(),
                new USAirport().getName()};
        cb_airport = this.addComboBox("Airport", 25, 228, options);

        btn_apply = this.addButton("Add plane", 25, 280, 100, 21);
        btn_apply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                apply();
            }
        });

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                scene.setPaused(false);
                dispose();
            }
        });
    }

    private void apply() {
        Plane newPlane = new Plane(new Vector3f(700, 100, 0), field_plate.getText(), field_brand.getText(), field_model.getText(), Integer.parseInt(field_pass.getText()), Integer.parseInt(field_kerosene.getText()));

        System.out.println(this.cb_airport.getSelectedItem());
        if(this.cb_airport.getSelectedItem().equals("Spain")) {
            newPlane.setPath(SpainAirport.getPath());
        } else if(this.cb_airport.getSelectedItem().equals("Germany")) {
            newPlane.setPath(GermanyAirport.getPath());
        } else if(this.cb_airport.getSelectedItem().equals("United State")) {
            newPlane.setPath(USAirport.getPath());
        } else {
            newPlane.setPath(Airport.getPath());
        }

        this.scene.addPlane(newPlane);
        scene.setPaused(false);
        dispose();
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
