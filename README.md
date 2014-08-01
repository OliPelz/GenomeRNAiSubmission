this is the official repositiory for the GenomeRNAi GenomeRNAi REST submission tool

for the server one need to set up following mysql database:
```
CREATE DATABASE GenomeRNAiSubmission_DEV DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
grant all on GenomeRNAiSubmission_DEV.* to GenomeRNAiSub@localhost identified by 'GenomeRNAiSub';
flush privileges
```
