this is the official repositiory for the GenomeRNAi GenomeRNAi REST submission tool

#the folder structure

Folder | Description
------------ | -------------
client | a web client which reads our REST sources and displays results in webbrowser
server | grails REST web application
test   | some test scripts etc.
 

for the server setup one need to set up following mysql database:
```
CREATE DATABASE GenomeRNAiSubmission_DEV DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
grant all on GenomeRNAiSubmission_DEV.* to GenomeRNAiSub@localhost identified by 'GenomeRNAiSub';
flush privileges
```


#tests on the commandline

