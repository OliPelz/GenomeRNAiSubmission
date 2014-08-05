package de.dkfz.signaling

class AdditionalColumn {
    String annotationType
    String annotationValue

    static belongsTo = [dataRow : DataRow]

    static constraints = {
        annotationType maxSize: 1000, nullable: true
        annotationValue maxSize: 1000, nullable: true
    }
}
