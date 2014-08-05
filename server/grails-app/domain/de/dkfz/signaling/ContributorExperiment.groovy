package de.dkfz.signaling

class ContributorExperiment {
    String assay
    String assayMethod
    String authors
    String bioSource
    String notes
    String publicationTitle
    Integer publicationYear
    BigInteger pubmedId
    String reagentType
    String scope
    String scoreCutoff
    String scoreType
    String screenTitle
    String screenType
    String stableId
    BigInteger orgId
    BigInteger submittedFileId
    String notesToTheCurator

    static hasMany = [dataRows : DataRow]

    static constraints = {
        assay maxSize: 255, nullable: true
        assayMethod maxSize: 255, nullable: true
        authors maxSize: 255, nullable: true
        bioSource maxSize: 255, nullable: true
        notes maxSize: 1000, nullable: true
        publicationTitle maxSize: 255, nullable: true
        publicationYear nullable: true
        pubmedId maxSize: 20, nullable: true
        reagentType maxSize: 255, nullable: true
        scope maxSize: 255, nullable: true
        scoreCutoff maxSize: 255, nullable: true
        scoreType maxSize: 255, nullable: true
        screenTitle maxSize: 255, nullable: true
        screenType maxSize: 255, nullable: true
        stableId maxSize: 255, nullable: true
        orgId maxSize: 20, nullable: true
        submittedFileId maxSize: 20, nullable: true
        notesToTheCurator maxSize: 1000, nullable: true
    }
}
