# Hospital Management System
NTU Hospital Management System project SC2002

## Table of Contents
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Usage](#usage)
- [Instructions / Pre-configurations](#instructions--pre-configurations)

## Getting Started


## Installation
1. Clone the repository:
   ```bash
   [git clone https://github.com/seanlengwx/SC2006---Project---Hostpital-Management-System.git](https://github.com/seanlengwx/SC2002-Hospital-Management-System.git)
   ```

2. Navigate to the project directory
    ```bash
    cd SC2002-Hospital-Management-System.git
    ```

## Usage
Compile and run the Main.java file. Follow all instructions / pre-requisites below.

## Instructions / Pre-requisites
To ensure the project can access the txt files in Main.java, set the correct filepaths by doing the following:
1. Locate txt files in the project.
    Ensure that Staff.txt, Patient.txt, Medicine.txt are available in the project directory under datafiles.

2. Set correct filepath in Main.java
    Update filepath for each txt file within Main.java based on the following example:
    ```bash
        String staffFilePath = "./datafiles/Staff.txt";  
        String patientFilePath = "./datafiles/Patient.txt";  
        String medicineFilePath = "./datafiles/Medicine.txt";
    ```
