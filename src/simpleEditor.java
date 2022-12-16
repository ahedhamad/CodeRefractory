import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class Editor extends JFrame implements ActionListener, DocumentListener {

    public static  void main(String[] args) {
        new editor();
    }

    public JEditorPane textPanel;
    private JMenuBar menu;
    private JMenuItem copy, paste, cut;
     private boolean isChanged = false;
    private File file;
    static final int WINDOW_WIDTH = 500;
    static final int WINDOW_HEIGHT = 500;
    public Editor() {
        //Editor the name of our application
        super("Editor");
        textPanel = new JEditorPane();
        // center means middle of container.
        add(new JScrollPane(textPanel), "Center");
        textPanel.getDocument().addDocumentListener(this);

        menu = new JMenuBar();
        setJMenuBar(menu);
        BuildMenu();
        //The size of window
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void BuildMenu() {
        buildFileMenu();
        buildEditMenu();
    }
    private void buildFileMenu() {
        buildFile();
        addItemNewFileInMenu();
        addItemOpenFileInMenu();
        addItemSaveFileInMenu();
        addItemSaveAsFileInMenu();
        addItemQuitFileInMenu();
    }
    private JMenu buildFile(){
        JMenu file = new JMenu("File");
        file.setMnemonic('F');
        menu.add(file);
        return file;
    }
    private void addItemNewFileInMenu(){
        JMenuItem newFile = new JMenuItem("New");
        newFile.setMnemonic('N');
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        newFile.addActionListener(this);
        buildFile().add(newFile);
    }
    private void addItemOpenFileInMenu() {
        JMenuItem open = new JMenuItem("Open");
        buildFile().add(open);
        open.addActionListener(this);
        open.setMnemonic('O');
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

    }
    private void addItemSaveFileInMenu() {
        JMenuItem save = new JMenuItem("Save");
        buildFile().add(save);
        save.setMnemonic('S');
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
    }
    private void addItemSaveAsFileInMenu() {
        JMenuItem saveAs = new JMenuItem("Save as...");
        saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        buildFile().add(saveAs);
        saveAs.addActionListener(this);
    }
    private void addItemQuitFileInMenu() {
        JMenuItem quit = new JMenuItem("Quit");
        buildFile().add(quit);
        quit.addActionListener(this);
        quit.setMnemonic('Q');
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
    }
    private void buildEditMenu() {
        buildEdit();
        addItemCutInMenu();
        addItemCopyInMenu();
        addItemPastInMenu();
        addItemFindInMenu();
        addItemSelectAllInMenu();
    }
    private JMenu buildEdit(){
        JMenu edit = new JMenu("Edit");
        menu.add(edit);
        edit.setMnemonic('E');
        return edit;
    }
    private void addItemCutInMenu(){
        cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        cut.setMnemonic('T');
        buildEdit().add(cut);
    }
    private void addItemCopyInMenu(){
        copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setMnemonic('C');
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        buildEdit().add(copy);
    }
    private void addItemPastInMenu(){
        paste = new JMenuItem("Paste");
        paste.setMnemonic('P');
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        buildEdit().add(paste);
        paste.addActionListener(this);
    }
    private void addItemFindInMenu(){
        JMenuItem find = new JMenuItem("Find");
        find.setMnemonic('F');
        find.addActionListener(this);
        buildEdit().add(find);
        find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));

    }
    private void addItemSelectAllInMenu(){
        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.setMnemonic('A');
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        selectAll.addActionListener(this);
        buildEdit().add(selectAll);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "Quit":
                System.exit(0);
                break;
            case "Open":
                loadFile();
                break;
            case "Save":
                saveFile();
                break;
            case "New":
                createNewFile();
                break;
            case "Save as...":
                saveAs("Save as...");
                break;
            case "Select All":
                textPanel.selectAll();
                break;
            case "Copy":
                textPanel.copy();
                break;
            case "Cut":
                textPanel.cut();
                break;
            case "Paste":
                textPanel.paste();
                break;
            case "Find":
                FindDialog find = new FindDialog(this, true);
                find.showDialog();
                break;
            default:

                break;
        }
    }
    static final int WARNING_MESSAGE = 2;
    static final int YES_NO_OPTION = 0;
    static final int CANCEL_OR_NO_OPTION = 1;
    static final int APPROVE_OR_YES_OPTION = 1;
        private void saveFile() {

            // Check if the file has changed and show a confirmation dialog if it has
            if (isChanged && !confirmSave()) {
                return;
            }
            // Save the file if it exists, or show a "Save As" dialog if it doesn't
            if (file != null) {
                save(file);
            } else {
                saveAs("Save");
            }
        }
    private boolean confirmSave() {
        int ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file", YES_NO_OPTION, WARNING_MESSAGE);
        return ans != CANCEL_OR_NO_OPTION;
    }

    private void save(File file) {
        String text = textPanel.getText();
        try (PrintWriter writer = new PrintWriter(file)) {
            if (!file.canWrite()) {
                throw new Exception("Cannot write file!");
            }
            writer.write(text);
            isChanged = false;
        } catch (Exception ex) {
            log.error("Error saving file", ex);
        }
    }
        private void createNewFile() {
            if (isChanged) {
                saveFile();
            }

            file = null;
            textPanel.setText("");
            isChanged = false;
            setTitle("Editor");
        }

    private void loadFile() {
        JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
        dialog.setMultiSelectionEnabled(false);
        try {
            int result = dialog.showOpenDialog(this);

            if (result == CANCEL_OR_NO_OPTION)
                return;
            if (result == APPROVE_OR_YES_OPTION) {
                if (isChanged && !promptToSaveFile())
                    return;
                File selectedFile = dialog.getSelectedFile();
                String fileContent = readFile(selectedFile);
                textPanel.setText(fileContent);
                isChanged = false;
                setTitle("Editor - " + selectedFile.getName());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private boolean promptToSaveFile() {
        int ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    if (ans == APPROVE_OR_YES_OPTION){
            if (file == null)
                saveAs("Save");
            else
                saveFile();
            return true;
        }
        return false;
    }
    private String readFile(File file){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot read file !", "Error !", 0);//0 means show Error Dialog
        }
        return stringBuilder.toString();
    }

    private void saveAs(String dialogTitle) {
        JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
        dialog.setDialogTitle(dialogTitle);
        int result = dialog.showSaveDialog(this);
        if (result != APPROVE_OR_YES_OPTION)
            return;
        file = dialog.getSelectedFile();
        try (PrintWriter writer = new PrintWriter(file)){
            writer.write(textPanel.getText());
            isChanged = false;
            setTitle("Editor - " + file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        isChanged = true;
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        isChanged = true;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        isChanged = true;
    }


}
