package genomernaisubmission

class AdditionalColumn {

    String type
    String value

    static belongsTo = [dataRow : DataRow]

    static constraints = {
        type maxSize: 1000, nullable: true
        value maxSize: 1000, nullable: true
    }
}
