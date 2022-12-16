# codeRefractoryAssignment_6

1.	import java.awt.BorderLayout ; // delete this import statement because Unused.
2.	TP;  //  Rename it because the name is not clear.to textPanel
3.	private JMenuItem move; // Delete this variable because it is not used
4.	public boolean changed;// Change the variable of Boolean type by adding is isChanged
5.	setSize(500,500); // The Magic Number is converted into a const value(WINDOW_WIDTH, WINDOW_HEIGHT).
6.	JMenuItem n = new JMenuItem("New"); // variable (n) it is not clear converted into a new name 
7.	private void buildFileMenu() {} // This function is very long and can be divided into several (6) functions. 
buildFile() 
addItemNewFileInMenu();
addItemOpenFileInMenu();
addItemSaveFileInMenu();
addItemSaveAsFileInMenu();
addItemQuitFileInMenu();

 and call this functions in buildFileMenu() function

8.	JMenuItem saveas // rename to saveAs
9.	private void buildEditMenu() {} // This function is very long and can be divided into several (6) functions.
buildEdit();
       addItemCutInMenu();
        addItemCopyInMenu();
        addItemPastInMenu();
        addItemFindInMenu();
        addItemSelectAllInMenu();
and call this functions in buildEditMenu() function

10.	// Delete the move comment because it is not used,  This is in function buildEditMenu()
11.	JMenuItem sall // Rename it because the name is not clear to selectAll naming.
12.	public void actionPerformed(ActionEvent e); // in this function uses a switch statement to handle the different actions, Instead of many if Statements. 
	Use separate method for handling the "Save" action called saveFile();
•	saveFile(); //Dividing this function into more than one method because it is long .
         = confirmSave() // this function to show Confirm Dialog
         = save() // this function to Save the file if it exists.
         =  ex.printStackTrace(); // convert this statement print Exception to logger statement.
•	Use separate method for handling the "New file" action called createNewFile();
•	The all Magic Number is converted into a const value ( 
WARNING_MESSAGE
YES_NO_OPTION
 CANCEL_OR_NO_OPTION)

13.	private void loadFile() {} //  this function must be divided into several functions because it is long. ( promptToSaveFile(),,readFile(File file))
•	private boolean promptToSaveFile() {} // this function to save file it follow functions Load File.
•	private String readFile(File file){} // this function to read file it follow functions load file. == rs,fr //rename this variable because it is not clear 
•	The all magic number is converted into a const value ( 
                    WARNING_MESSAGE
                  YES_NO_OPTION
       CANCEL_OR_NO_OPTION
               APPROVE_OR_YES_OPTION)
•	the lines below the comment // save file , are deleted because they are duplicates, and there is a function indicating a save file.

14.	In the last function, saveAs Text(), it was deleted because it is not used, but if we consider that it can be used in the future, it can be combined with the function of saveAs(), because they have similarities, so it becomes as shown here.

![2022-12-16 (2)](https://user-images.githubusercontent.com/99614732/208093980-50a12ce8-8169-4bc3-ae8f-7bbfd5b626ab.png)

•	A parameter named windowTitle is added to send the function a title suitable for the Editor, such as Save As Text Editor or Editor
