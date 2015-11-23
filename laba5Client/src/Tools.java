import sun.misc.Resource;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * Created by panikun on 29.10.15.
 */
public class Tools extends JPanel {
    Vector<Locale> locales = new Vector();
    JLabel yLabel;
    JLabel xLabel;
    JButton addPointBtn;
    ResourceBundle strings;
    public Tools(final Graphic graph) {

        locales.add(new Locale("ru","RU"));
        locales.add(new Locale("fi", "FI"));
        strings=ResourceBundle.getBundle("client");

        this.setLayout(new GridLayout(1, 10));

        final JSpinner rSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        rSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                graph.setRadius(new Double((int) rSpinner.getValue()));
            }
        });
        final JComboBox<String> languages = new JComboBox<>();
        languages.addItem("Русский");
        languages.addItem("Suomi");
        this.add(languages);

        this.add(new JLabel("R:"));

        languages.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setLanguage((String)languages.getSelectedItem());
            }
        });

        this.add(rSpinner);
        yLabel=(new JLabel(strings.getString("chooseY")));
        this.add(yLabel);

        final JCheckBox checkBox1 = new JCheckBox("10");
        checkBox1.setSelected(true);
        final JCheckBox checkBox2 = new JCheckBox("3.2");
        final JCheckBox checkBox3 = new JCheckBox("1");

        JPanel container = new JPanel(new GridLayout(1, 3));
        this.add(container);
        container.add(checkBox1);
        container.add(checkBox2);
        container.add(checkBox3);

        final ButtonGroup checkBtns = new ButtonGroup();
        checkBtns.add(checkBox1);
        checkBtns.add(checkBox2);
        checkBtns.add(checkBox3);


        xLabel=(new JLabel(strings.getString("chooseX")));
        this.add(xLabel);

        final JComboBox<Double> xComboBox = new JComboBox<Double>();
        xComboBox.addItem(new Double(10));
        xComboBox.addItem(new Double(3.2));
        xComboBox.addItem(new Double(1));
        this.add(xComboBox);

        addPointBtn = new JButton(strings.getString("add"));
        addPointBtn.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Double x = xComboBox.getItemAt(xComboBox.getSelectedIndex());
                double y = 0;
                for (Enumeration<AbstractButton> buttons = checkBtns.getElements(); buttons.hasMoreElements(); ) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        y = Double.parseDouble(button.getText());
                    }
                }
                graph.addPoint(x, y);
            }
        });
        this.add(addPointBtn);
    }
        private void setLanguage(String lang){
            switch (lang){
                case "Русский":{
                    strings= ResourceBundle.getBundle("client", locales.elementAt(0));
                    yLabel.setText(strings.getString("chooseY"));
                    xLabel.setText(strings.getString("chooseX"));
                    addPointBtn.setText(strings.getString("add"));
                }break;
                case "Suomi":{
                    ResourceBundle strings= ResourceBundle.getBundle("client", locales.elementAt(1));
                    yLabel.setText(strings.getString("chooseY"));
                    xLabel.setText(strings.getString("chooseX"));
                    addPointBtn.setText(strings.getString("add"));
                }break;
                default:{}break;
            }
    }
}
