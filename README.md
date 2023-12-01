# Code Refactoring Assignment 6

This repository contains refactoring suggestions for improving the code quality of a Java application. Below are the identified issues and proposed improvements.

## Identified Issues and Refactoring Suggestions

1. **Unused Import Statement**
   - Deleted the unused import statement `java.awt.BorderLayout`.

2. **Unclear Variable Name**
   - Renamed `TP` to `textPanel` for clarity.

3. **Unused Variable**
   - Removed the unused variable `move`.

4. **Boolean Variable Naming Convention**
   - Renamed `changed` to `isChanged` for better naming convention.

5. **Magic Number Refactoring**
   - Converted magic numbers in `setSize(500,500)` to constants (`WINDOW_WIDTH`, `WINDOW_HEIGHT`).

6. **Refactoring File Menu Building**
   - Divided the `buildFileMenu()` function into multiple functions for better readability and maintenance:
     - `buildFile()`
     - `addItemNewFileInMenu()`
     - `addItemOpenFileInMenu()`
     - `addItemSaveFileInMenu()`
     - `addItemSaveAsFileInMenu()`
     - `addItemQuitFileInMenu()`
   
7. **Refactoring Edit Menu Building**
   - Similar to File Menu, refactored the `buildEditMenu()` function:
     - `buildEdit()`
     - `addItemCutInMenu()`
     - `addItemCopyInMenu()`
     - `addItemPasteInMenu()`
     - `addItemFindInMenu()`
     - `addItemSelectAllInMenu()`

8. **Variable Renaming**
   - Renamed `saveas` to `saveAs`.

9. **Action Handling and Exception Handling**
   - Used switch statements instead of numerous if statements in `actionPerformed(ActionEvent e)`.
   - Segregated handling actions into separate methods:
     - `saveFile()` for handling the "Save" action.
     - `createNewFile()` for handling the "New file" action.
   - Replaced `ex.printStackTrace();` with logging statements.

10. **Function Length and Magic Numbers**
   - Divided the long `loadFile()` function into smaller functions:
     - `promptToSaveFile()` to save the file.
     - `readFile(File file)` to read the file.
   - Converted magic numbers to constants for readability.

11. **Code Cleanup**
   - Deleted duplicate lines and unused functions (e.g., `saveAsText()`).

12. **Consolidation of Functions**
   - Suggested combining `saveAsText()` with `saveAs()` due to similarities for potential future use.

## Usage
- To implement these refactoring suggestions, review the identified issues and apply the recommended changes in the respective code sections.

## Additional Notes
- These refactoring suggestions aim to enhance code readability, maintainability, and adherence to coding best practices.
- For a detailed implementation, refer to the specific code sections marked in the suggestions.


![2022-12-16 (2)](https://user-images.githubusercontent.com/99614732/208093980-50a12ce8-8169-4bc3-ae8f-7bbfd5b626ab.png)

•	A parameter named windowTitle is added to send the function a title suitable for the Editor, such as Save As Text Editor or Editor
