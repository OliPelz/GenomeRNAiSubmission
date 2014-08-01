package genomernaisubmission

class DataRow {
    int plate
    int position
    String well
    Double score
    String wellAnno
    String finalWellAnno
    int rawR1Ch1
    int rawR2Ch1
    int rawR1Ch2
    int rawR2Ch2
    Double activityR1Ch1
    Double activityR2Ch1
    Double activityR1Ch2
    Double activityR2Ch2
    Double normalizedR1
    Double normalizedR2
    String reagentName
    String image
    String primaryGeneID
    String phenotype
    BigInteger reagentId
    String library
    String libraryProvider
    String bioModel

    static hasMany = [additionalColumns : AdditionalColumn]
    static belongsTo = [contributorExperiment : ContributorExperiment]

    static constraints = {
//        plate maxSize: 11, nullable: true
//        position maxSize: 11, nullable: true
//        well maxSize: 255, nullable: true
//        score nullable: true
//        wellAnno maxSize: 255, nullable: true
//        finalWellAnno maxSize: 255, nullable: true
//        rawR1Ch1 maxSize: 11, nullable: true
//        rawR2Ch1 maxSize: 11, nullable: true
//        rawR1Ch2 maxSize: 11, nullable: true
//        rawR2Ch2 maxSize: 11, nullable: true
//        activityR1Ch1 nullable: true
//        activityR2Ch1 nullable: true
//        activityR1Ch2 nullable: true
//        activityR2Ch2 nullable: true
//        normalizedR1 nullable: true
//        normalizedR2 nullable: true
//        reagentName maxSize: 255, nullable: true
//        image maxSize: 255, nullable: true
//        primaryGeneID maxSize: 255, nullable: true
//        phenotype maxSize: 255, nullable: true
//        reagentId maxSize: 20, nullable: true
//        library maxSize: 255, nullable: true
//        libraryProvider maxSize: 255
//        bioModel maxSize: 255, nullable: true
    }
}
