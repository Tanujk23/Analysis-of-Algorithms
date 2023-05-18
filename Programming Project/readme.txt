This project consists of .java files in src folder.

Step 1: make
        ---> On entering this command, all the class files for the java files
            will be generated including META-INF folder which consists of main 
            class configuration file
        ----> This will also generate Stocks.jar file 
        
Step 2: java -jar Stocks.jar $(argument)
        ----> argument is where we give task number like 1,2,3a,3b ......
        ----> This will begin the program execution.

Inorder to generate jar file again run make clean and then make. This will remove existing jar and create new jar file.