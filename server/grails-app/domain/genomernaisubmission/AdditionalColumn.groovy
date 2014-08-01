package genomernaisubmission

class AdditionalColumn {

    String type
    String value

    static belongsTo = [dataRow : DataRow]

    static constraints = {
//        type maxSize: 255, nullable: true
//        value maxSize: 255, nullable: true
    }
}
