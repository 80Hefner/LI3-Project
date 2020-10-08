# Projeto LI3 - JAVA (2019/2020)  
**Version 1.0.0**

Instructions on how to use the project!

---
# Students  
- Bruno Filipe de Sousa Dias ([Tourette137](https://github.com/Tourette137))
- Luís Enes Sousa ([80Hefner](https://github.com/80Hefner))

---
# Run the program  
In order to run the program, you must be in the folder where the application's .jar is located and run the following command:  

    java -jar GestVendasAppMVC.jar

### Change the files you want to read  
The default settings will always run the application with the "**Vendas_1M.txt**", "**Produtos.txt**" and "**Clientes.txt**" files. In order to change the files you want to read, you have to go to the file **config.cfg** and change the DEFAULT options to the path of the files you want to read.  

For example, if you want to read another file of Sales, you have to go to the config.cfg file, and change the DEFAULT option of VENDAS to the path of the file of Sales you want to read. 


---  
# Run the tests  
In order to run the tests, you must be in the folder where the test's .jar are located and run each of the following commands, for each test:   

    java -jar gestVendasTest_List.jar
    
    java -jar gestVendasTest_Set.jar  
    
    java -jar gestVendasTest_Map.jar  
    
    java -jar StreamReaderTest.jar
    
Once you run a test, a **"TEST_NAME_result.txt"** file is created where all the results are stored.
