this is the official repositiory for the GenomeRNAi GenomeRNAi REST submission tool

#the folder structure

Folder | Description
------------ | -------------
client | a web client which reads our REST sources and displays results in webbrowser (based on bootstrap and angularjs)
server | grails REST web application
test   | some test scripts etc.
 

for the server setup one need to set up following mysql database:
```bash
CREATE DATABASE GenomeRNAiSubmission_DEV DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
grant all on GenomeRNAiSubmission_DEV.* to GenomeRNAiSub@localhost identified by 'GenomeRNAiSub';
flush privileges
```


#tests on the commandline

Just to quickly test the service to import data you can use curl (http://blogs.plexibus.com/2009/01/15/rest-esting-with-curl/)

to see whats in the ContributorExperiment table:
```bash
$ curl -i -H "Content-Type:application/json" -X GET  http://genomernai-test.dkfz.de/GenomeRNAiSubmission/contributorExperiment/rest
$ curl -i -H "Content-Type:application/json" -X POST -d '{"publicationTitle": "my first screen","authors": "Oliver Pelz", orgId : 1}' http://genomernai-test.dkfz.de/GenomeRNAiSubmission/contributorExperiment/rest
```

in the JSON response from the server there is a property id, take it for inserting belonging dataRows and connect them to the experiment using the property
contributorExperiment

e.g. JSON response is : { ......, "id":2, ....}, than set dataRow's contributorExperiment property to {"contributorExperiment" : 2}

e.g. insert some datarows:
```bash
$ curl -i -H "Content-Type:application/json" -X POST -d '{"contExpId": 2,"score":123, "plate": 1, "well": "A2"}' http://genomernai-test.dkfz.de/GenomeRNAiSubmission/dataRow/rest
$ curl -i -H "Content-Type:application/json" -X POST -d '{"contExpId": 2,"score":234, "plate": 1, "well": "B2"}' http://genomernai-test.dkfz.de/GenomeRNAiSubmission/dataRow/rest
$ curl -i -H "Content-Type:application/json" -X POST -d '{"contExpId": 2,"score":345, "plate": 1, "well": "C2"}' http://genomernai-test.dkfz.de/GenomeRNAiSubmission/dataRow/rest
$ curl -i -H "Content-Type:application/json" -X POST -d '{"contExpId": 2,"score":456, "plate": 1, "well": "D2"}' http://genomernai-test.dkfz.de/GenomeRNAiSubmission/dataRow/rest
$ curl -i -H "Content-Type:application/json" -X POST -d '{"contExpId": 2,"score":567, "plate": 1, "well": "E2"}' http://genomernai-test.dkfz.de/GenomeRNAiSubmission/dataRow/rest
```
