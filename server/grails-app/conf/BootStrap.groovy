import genomernaisubmission.AdditionalColumn
import genomernaisubmission.ContributorExperiment
import genomernaisubmission.DataRow

class BootStrap {

    def init = { servletContext ->

    ContributorExperiment contributorExperiment = new ContributorExperiment(
            assay: "sfsdf",
            assayMethod:"saddf" ,
            authors:"sfsdf" ,
            bioSource:"sfsdf" ,
            notes: "sfsdf",
            publicationTitle: "sfsdf",
            publicationYear: 2014 ,
            pubmedId: 2434235,
            reagentType: "sfsdf",
            scope: "sfsdf",
            scoreCutoff: "sfsdf",
            scoreType: "sfsdf",
            screenTitle: "sfsdf",
            screenType: "sfsdf",
            stableId: "sfsdf",
            orgId: 324,
            notesToTheCurator: "Hello World"
    ).addToDataRows(new DataRow(
            plate: 235423,
            position: 234,
            well: "sfsdf",
            score: 323.34,
            wellAnno: "424",
            finalWellAnno: "sfsdf",
            rawR1Ch1: 213,
            rawR2Ch1: 4534,
            rawR1Ch2: 43242,
            rawR2Ch2: 242,
            activityR1Ch1: 34.5,
            activityR2Ch1: 534.5,
            activityR1Ch2: 342.7,
            activityR2Ch2: 42.5,
            normalizedR1: 6434.6,
            normalizedR2: 645323.66,
            reagentName: "sfsdf",
            image: "sfsdf",
            primaryGeneID: "sfsdf",
            phenotype: "sfsdfsfsdf",
            reagentId: 34523523,
            library: "sfsdf",
            libraryProvider: "sfsdf",
            bioModel: "sfsdf"
    )).save(failOnError : true)


    }
    def destroy = {
    }
}