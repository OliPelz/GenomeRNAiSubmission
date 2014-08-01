class UrlMappings {

	static mappings = {
        "/additionalColumn/rest"(resources: "additionalColumnREST")
        "/contributorExperiment/rest"(resources: "contributorExperimentREST")
        "/dataRow/rest"(resources: "dataRowREST")
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/"(view:"/index")
        "500"(view:'/error')
	}
}
