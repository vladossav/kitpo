import types.UserType;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Gui extends JFrame {
    BinaryTreeAsArray tree = new BinaryTreeAsArray();

    Gui() {
        super("BinaryTreeAsArray");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //окно вывода
        JTextArea out = new JTextArea();
        out.setEditable(false);
        out.setFont(new Font("Consolas", Font.PLAIN, 22));

        JPanel menu = new JPanel();
        Box box = Box.createVerticalBox();

        JComboBox userType = new JComboBox(UserFactory.getTypeNameList().toArray());
        userType.addActionListener(view -> {
            tree.init();
            System.out.println(userType.getSelectedItem());
        });

        //loadsave
        JPanel workFiles = new JPanel(new FlowLayout());
        workFiles.setBorder(BorderFactory.createEmptyBorder(10,0,30,0));
        JButton load = new JButton("Load");
        load.addActionListener(v -> {
            try {
                tree = Serialization.loadFile("file.txt");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String str = out.getText() + "\nTree was loaded!";
            out.setText(str);
        });
        JButton save = new JButton("Save");
        save.addActionListener(v -> {
            try {
                Serialization.saveToFile(tree, "file.txt", userType.getSelectedItem().toString());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            String str = out.getText() + "\nTree was saved!";
            out.setText(str);
        });
        workFiles.add(load);
        workFiles.add(save);

        //insert
        JPanel insertIndexPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel insertValuePanel = new JPanel();
        JLabel insertIndexLabel = new JLabel("index: ");
        JTextField insertIndexField = new JTextField(4);
        JLabel insertValueLabel = new JLabel("value: ");
        JTextField insertValueField = new JTextField(10);
        insertValueField.setToolTipText("Int: int    ProperFraction: int'int/int");

        insertIndexPanel.add(insertIndexLabel);
        insertIndexPanel.add(insertIndexField);
        insertValuePanel.add(insertValueLabel);
        insertValuePanel.add(insertValueField);
        JPanel insertCont = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton insertBtn = new JButton("Insert");
        insertBtn.addActionListener(view -> {
            UserType obj = (UserType) UserFactory
                    .getBuilderByName(userType.getSelectedItem().toString())
                    .parseValue(insertValueField.getText());
            int index = Integer.parseInt(insertIndexField.getText());
            insertIndexField.setText("");
            insertValueField.setText("");
            tree.insertByIndex(index,obj);
            String str = out.getText() + "\nInsert " + obj.toString() + " at " + index;
            out.setText(str);
        });
        insertCont.add(insertBtn);

        JPanel remove = new JPanel(new FlowLayout(FlowLayout.LEFT));
        remove.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        JLabel removeIndexLabel = new JLabel("index: ");
        JTextField removeIndexField = new JTextField(4);
        remove.add(removeIndexLabel);
        remove.add(removeIndexField);
        JPanel removeCont = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton removeBtn = new JButton("Remove");
        removeBtn.addActionListener(view -> {
            int index = Integer.parseInt(removeIndexField.getText());
            removeIndexField.setText("");
            tree.deleteByIndex(index);
            String str = out.getText() + "\nRemove " + index;
            out.setText(str);
        });
        removeCont.add(removeBtn);

        JPanel btnCont = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnCont.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        JButton balanceBtn = new JButton("Balance");
        balanceBtn.addActionListener(view -> {
            tree.balance();
        });
        btnCont.add(balanceBtn);

        JPanel showCont = new JPanel(new FlowLayout(FlowLayout.LEFT));
        showCont.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        JButton showBtn = new JButton("Show Tree");
        showBtn.addActionListener(view -> {
            String str = out.getText() + "\n" + tree.toString();
            out.setText(str);
        });
        showCont.add(showBtn);

        box.add(userType);
        box.add(workFiles);
        box.add(insertIndexPanel);
        box.add(insertValuePanel);
        box.add(insertCont);
        box.add(remove);
        box.add(removeCont);
        box.add(btnCont);
        box.add(showCont);
        menu.add(box);

        JPanel frame = new JPanel();
        frame.setLayout(new BorderLayout());
        frame.add(menu, BorderLayout.WEST);
        frame.add(new JScrollPane(out),BorderLayout.CENTER);

        setContentPane(frame);
        setSize(800,500);
        setResizable(false);
        setVisible(true);
    }

}
