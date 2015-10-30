import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Enumeration;

/**
 * Created by panikun on 29.10.15.
 */
public class Tools extends JPanel {
    public Tools(final Graphic graph) {
        this.setLayout(new GridLayout(1, 10));
        this.add(new JLabel("R:"));

        final JSpinner rSpinner = new JSpinner(new SpinnerNumberModel(1, 0, 20, 1));
        rSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
            graph.setRadius(new Double((int)rSpinner.getValue()));
            }
        });


        this.add(rSpinner);


        this.add((new JLabel("Выберете Y:")));
        final JCheckBox checkBox1 = new JCheckBox("10");
        checkBox1.setSelected(true);
        final JCheckBox checkBox2 =new JCheckBox("3.2");
        final JCheckBox checkBox3 =new JCheckBox("1");

        JPanel container = new JPanel(new GridLayout(1,3));
        this.add(container);
        container.add(checkBox1);
        container.add(checkBox2);
        container.add(checkBox3);

        final ButtonGroup checkBtns = new ButtonGroup();
        checkBtns.add(checkBox1 );
        checkBtns.add(checkBox2 );
        checkBtns.add(checkBox3 );


        this.add(new JLabel("Выберете X:"));
        final JComboBox<Double> xComboBox = new JComboBox<Double>();
        xComboBox.addItem(new Double(10));
        xComboBox.addItem(new Double(3.2));
        xComboBox.addItem(new Double(1));
        this.add(xComboBox);

        JButton addPointBtn = new JButton("Добавить точку");
        addPointBtn.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Double x = xComboBox.getItemAt(xComboBox.getSelectedIndex());
                double y=0;
                for (Enumeration<AbstractButton> buttons = checkBtns.getElements(); buttons.hasMoreElements();) {
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
}
