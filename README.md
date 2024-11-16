# Hospital Management System
NTU Hospital Management System project SC2006

## Table of Contents
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Usage](#usage)
- [Instructions / Pre-configurations](#instructions--pre-configurations)

## Getting Started


## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/seanlengwx/SC2006---Project---Hostpital-Management-System.git
   ```

2. Navigate to the project directory
    ```bash
    cd SC2006---Project---Hostpital-Management-System
    ```

## Usage
Compile and run the Main.java file. Follow all instructions / pre-requisites below.

## Instructions / Pre-requisites
To ensure the project can access the CSV files in Main.java, set the correct filepaths by doing the following:
1. Locate CSV files in the project.
    Ensure that Staff.csv, Patient.csv, Medicine.csv are available in the project directory.

2. Set correct filepath in Main.java
    Update filepath for each CSV file within Main.java based on the following example:
    ```bash
        String staffFilePath = "./Staff.csv";  
        String patientFilePath = "./Patient.csv";  
        String medicineFilePath = "./Medicine.csv";
    ```