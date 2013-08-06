(ns slp.models.partners)

(def full-form 
  {
		:view "VIEW_JQUERYUI_CREATE"
		:schema {
			:type "object"
			:properties {
				:name {
					:type "string"
					:required true
				}
				:birthday {
					:type "text"
					:format "date"
					:required true
				}
				:preference {
					:type "text"
					:enum ["orlando" "tokyo" "amsterdam"]
					:default "orlando"
					:required true
				}
			}
		}
		:options {
			:renderForm true
			:form {
				:buttons {
					:submit {}
				}
			}
			:fields {
				:name {
					:label "Your Name"
				}
				:birthday {
					:label "Your Birthday"
				}
				:preference {
					:label "Your Destination"
					:type "select"
					:optionLabels ["Orlando USA" "Tokyo Japan" "Amsterdam Netherlands"]
				}
			}
		}
	}
)