# **Contributing Guidelines**

Thank you for considering contributing to the E-Commerce Furniture Store Website project. Your help is greatly appreciated in making this project better for everyone!

## Contributing Guidelines

## **1. Repository Setup Guide**

This guide offers detailed instructions for setting up a repository, installing dependencies, configuring a server, and starting the server for a Java application.

### **1.1 Clone the Repository**

To clone a repository using GitHub Desktop, follow these steps:

* Download and Install GitHub Desktop: [https://desktop.github.com/](https://desktop.github.com/)
* Open GitHub Desktop
* Sign in to GitHub (if you don't have an account, create one for free)
* Clone the Repository:
    * In GitHub Desktop, go to "File" -> "Clone Repository"
    * Enter the repository URL: [https://github.com/Kasun-Abeywickrama/DEA-Project.git](https://github.com/Kasun-Abeywickrama/DEA-Project.git)
    * Choose a local path for the repository on your computer
    * Click "Clone"
* Open the Cloned Repository:
    * Once cloned, you'll see the repository in GitHub Desktop. Click on it to open its contents.

## **2. Create your Branch**

### **2.1 Switch to Development Branch**

In GitHub Desktop, locate the "Current Branch" dropdown menu and select the `development` branch.

### **2.2 Create New Branch**

1. Click on "Current Branch" -> "New Branch".
2. Name your branch following the format `feature/<suitable_name_for_your_work>`.
3. Choose "development" as the base branch and click "Create Branch".

### **2.3 Switch to your branch**

Select your newly created branch from the "Current Branch" dropdown menu.

### **2.4 View Branches**

The "Current Branch" dropdown menu displays the currently checked out branch.

### **2.5 Choose Your Branch**

Select the desired branch from the dropdown menu to switch to it locally.

## **3. Database Setup**

### **3.1 Install MySQL Workbench**

Please follow the instructions provided on this website to install the software.
[https://www.freecodecamp.org/news/how-to-install-mysql-workbench-on-windows/](https://www.freecodecamp.org/news/how-to-install-mysql-workbench-on-windows/)

Download MySQL Workbench from the official website: [https://dev.mysql.com/downloads/workbench/](https://dev.mysql.com/downloads/workbench/)

### **3.2 Import Database**

1. Use your PC's file explorer to locate the `SQL File.sql` in the `Database Management` folder within the project folder.
   <br>Because you need to know where the file is located before you can do anything with it.
2. Open MySQL Workbench and connect to your local MySQL server.<br>
   Open MySQL Workbench -> click on the connection tile in the My SQL connections section -> give credentials -> hit the ok button
4. Import the `SQL File.sql` into your MySQL server to create the database schema.<br>
   file -> Open SQL script -> Execute the query by clicking the yellow lightning button. 

### **3.3 Update Connection Details**

1. Navigate to the `DBConnectionManager` file within the `DatabaseConnection` package of the project.
2. Update the database connection details in this file with your specific credentials (hostname, username, password).

### **3.4 Start the Database Server**

Ensure your MySQL server is running and accessible.

## **4. Create WEB-INF Folder**

1. Navigate to the project folder path: `Project_folder\E-Commerce-Website\web`.
2. If the `WEB-INF` folder doesn't exist, create a new empty folder named `WEB-INF`.

## **5. Open this project folder in Netbeans IDE.**

## **6. Start Working ...**

## **7. Making Changes**

* Code Style: Follow consistent coding style and conventions used in the existing codebase.
* Write Clear Code: Strive to write clean, well-commented code that is easy to understand and maintain.
* Unit Tests: If applicable, consider adding unit tests for your changes to ensure functionality.
* Commit Messages: Use descriptive commit messages that explain the purpose of your changes.

## **8. Submitting Changes**

* Push your branch: Once your changes are complete and tested, push your branch to your repository.

<!-- ## **9. Additional Notes**

* Communication: Feel free to communicate through pull request comments or discussions for any questions or clarifications.
* License: Ensure your code contributions adhere to the project's license (refer to LICENSE.md file). -->

We appreciate your contributions! By following these guidelines, you can help us maintain a high-quality codebase for this project.
