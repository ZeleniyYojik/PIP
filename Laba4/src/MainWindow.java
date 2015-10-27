import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by panikun on 26.10.15.
 */
public class MainWindow {
    private JPanel panel1;
    private JPanel Graph;
    private JPanel Tools;
    private JSpinner RadiusSpinner;
    private JButton addPointBtn;
    private JComboBox comboBox1;

    private JCheckBox a1CheckBox;
    private JCheckBox a10CheckBox;
    private JCheckBox a5CheckBox;
    private JCheckBox a01CheckBox;
    private JCheckBox a10CheckBox1;
    private JCheckBox a32CheckBox;
    private JCheckBox a22CheckBox;
    private JCheckBox a01CheckBox1;
    private JCheckBox a0CheckBox;

    //Меняем значение Y при смене checkBox'a и отключаем прошлый checkBox
    ItemListener checkBoxListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            lastSelectedCheckBox.setSelected(false);
            lastSelectedCheckBox.setEnabled(true);
            lastSelectedCheckBox = (JCheckBox)e.getItem();
            lastSelectedCheckBox.setEnabled(false);
            JCheckBox tmp = (JCheckBox)e.getItem();
            Ycoord = Double.parseDouble(tmp.getText());
        }
    };
    //Меняем значение X при смене выбранного элемента в comboBox
    ItemListener comboBoxListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            //JComboBox tmp = (JComboBox)itemEvent.getItem();
            Xcoord = Double.parseDouble(itemEvent.getItem().toString());
        }
    };

    ActionListener addPointBtnClicked = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
        }
    };


    JCheckBox lastSelectedCheckBox;
    double Ycoord;
    double Xcoord;
    int Radius;

    public MainWindow() {
        a1CheckBox.addItemListener(checkBoxListener);     //
        a10CheckBox.addItemListener(checkBoxListener);    //
        a5CheckBox.addItemListener(checkBoxListener);     //
        a01CheckBox.addItemListener(checkBoxListener);    //Добавляем CheckBox'ам слушателя
        a10CheckBox1.addItemListener(checkBoxListener);   //
        a32CheckBox.addItemListener(checkBoxListener);    //
        a22CheckBox.addItemListener(checkBoxListener);    //
        a01CheckBox1.addItemListener(checkBoxListener);   //
        a0CheckBox.addItemListener(checkBoxListener);     //
        addPointBtn.addActionListener(addPointBtnClicked);//Добавляем слушателя для кнопки
        comboBox1.addItemListener(comboBoxListener);      //Добавляем слушателя для comboBox

        RadiusSpinner.setModel(new SpinnerNumberModel(1, 0, 20, 0.5));

        lastSelectedCheckBox = a0CheckBox;
        Ycoord = Double.parseDouble(lastSelectedCheckBox.getText());
        Xcoord = Double.parseDouble(comboBox1.getSelectedItem().toString());
       // Radius = (int)RadiusSpinner.getValue(); //commitEdit()
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        //frame.setSize(1024, 728);
        frame.setMinimumSize(new Dimension(1024,768));
        frame.setResizable(false);
        frame.setContentPane(new MainWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
