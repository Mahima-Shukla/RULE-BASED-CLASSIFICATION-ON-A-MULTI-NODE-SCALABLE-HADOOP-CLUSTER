TITLE: RULE  BASED  CLASSIFICATION  ON A MULTI NODE SCALABLE HADOOP  CLUSTER 

There are total 10 files
This two files run on netbeans.
1. SerialClassifier-in_data file are run with in_data(Breast Cancer Data Set) excel file.
2. SerialClassifier-CTG file are run with CTG(Cardiotocographic Data Set) excel file.

This 4 files run on Eclips in VMware(Virtual Machine)

1. RipperD file are run on both data set(Breast Cancer AND Cardiotocographic) and it also display the delay time.
2. RipperDrviver,RipperMapper and RipperReducer- this is one code, run the RipperDriver class.
3.  SVMDriver,SVMMapper and SVMReducer- this id also one code, run the SVMDriver class.
4. WebServer- This is the server class which makes the client server connection. For running this class we need to install the WampServer software.
 Steps (How to run WebServer class)
i. Open WebServer file and run this code with in_data dataset file.
ii. Start the WampServer from start menu.
iii. Type cmd on start menu and write the “ipconfig” command which will show the ip address of computer/host machine/server machine.
iv. Open the browser on server machine with the help of wampserver and also open the browser on client machine and type the ip address host machine/server machine.
v. Click on process task button. It will show the output of data set file.

Important Links
1. How to Install Virtual Machine.
https://kevinblackston.com/2017/05/31/how-to-set-up-a-windows-7-virtual-machine-on-windows-10/

2. How to download Hadoop.
               https://ebiquity.umbc.edu/blogger/2009/04/09/hadoop-on-windows-with-eclipse/

3. How to download WampServer.
http://www.wampserver.com/en/download-wampserver-64bits/#download-wrapper
