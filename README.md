📌 Project Overview

This project is an end-to-end Selenium Automation Testing Framework developed for the OrangeHRM Web Application using Java.
It follows real-time automation best practices such as Page Object Model (POM), Data Driven Testing, Extent Reporting, and CI/CD execution using Jenkins.

This framework is designed for learning, practice, and SDET portfolio purposes.

🛠️ Tech Stack & Tools

Programming Language: Java

Automation Tool: Selenium WebDriver

Test Framework: TestNG

Build Tool: Maven

Framework Design: Page Object Model (POM)

Reporting: Extent HTML Reports

CI/CD: Jenkins

Version Control: Git & GitHub

IDE: Eclipse / IntelliJ IDEA

📂 Project Structure
OrangeHRM-Selenium-Automation
│
├── src/main/java
│   ├── base            → WebDriver & Base configuration
│   ├── utilities       → Config, Excel, Report utilities
│
├── src/test/java
│   ├── Admin           → Admin module test cases
│   ├── PIM             → PIM module test cases
│   ├── Recruitment     → Recruitment module test cases
│   ├── Buzz            → Buzz module test cases
│   ├── Claim           → Claim module test cases
│   ├── orangehrm       → Common flows (Login / Logout)
│
├── logs                → Execution logs
├── reports             → Extent HTML reports
├── test-output         → TestNG reports
├── pom.xml             → Maven dependencies
├── testng.xml          → TestNG suite file
└── README.md

🧩 Modules Automated

Admin Module

User Management

Role & permission validation

PIM Module

Add Employee

Search Employee

Employee details validation

Recruitment Module

Candidate management

Vacancy validation

Buzz Module

Post creation and validation

Claim Module

Claim creation

Claim status verification

Common Flows

Login

Logout

Dashboard validation

✅ Key Features

✔ Page Object Model (POM) framework
✔ Data Driven Testing using Excel
✔ TestNG-based execution
✔ Extent HTML Reports with screenshots on failure
✔ Module-wise test case organization
✔ Maven build management
✔ Jenkins CI/CD execution
✔ GitHub version control

📊 Reporting

Extent Reports are generated after every execution

Reports include:

Test pass/fail status

Execution steps

Screenshots on failure

(Extent report screenshots can be added for better presentation)

🔁 CI/CD – Jenkins Integration

This project is configured to run using Jenkins:

Jenkins pulls the latest code from GitHub

Maven build is executed

TestNG test suite runs

Extent Reports are generated

▶️ How to Run the Project Locally

Clone the repository:

git clone https://github.com/Hammadashfaq276/<repository-name>.git


Open the project in your IDE

Allow Maven dependencies to download

Run testng.xml or individual test classes

⚙️ Configuration

Browser and environment settings are managed via configuration files

Test data is maintained using Excel files (Data Driven approach)

👨‍💻 Author

Hammad Ashfaq
Role: SDET | Automation Test Engineer
Skills: Selenium | Java | TestNG | Jenkins | Git

<img width="1908" height="939" alt="image" src="https://github.com/user-attachments/assets/44cfc37a-3788-4a1f-a175-6396c275d84b" />
