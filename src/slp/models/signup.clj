(ns slp.models.signup)

(def name-schema {
	:first-name {
		:type "string"
		:required true
	}
	:last-name {
		:type "string"
		:required true
	}
 })

(def schema {
	:type "object"
	:properties {
		:first-name {
			:type "string"
			:required true
		}
    :last-name {
			:type "string"
			:required true
		}
		:date-of-birth {
			:type "text"
			:format "date"
			:required true
		}
		:social-security {
			:type "number"
			:required true
		}
    :federal-pin {
      :type "number"
      :required false
    }
  }
 })

(def address-schema {
  :address {:type "string"}
  :zip-code {:type "number"}
})

(def prime-student-loan-advanced-schema {
  :currently-enrolled   {:type "boolean"}
  :last-school-attended {:type "string"}
  :graduation-date {:type "date"}
  :major {:type "string"}
  :current-employment {:type "string"}
  :household-income {:type "number"}
  :family-size {:type "number"}
  :claiming-unemployment {:type "boolean"}
  :make-all-monthly-payments {:type "boolean"}
  :afford-to-increase-monthly-payments {:type "boolean"}
  :if-cannot-afford-to-increase-monthly-payments {:type "string"}
  :has-loan-co-signers {:type "boolean"}
})

(def options {
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
		}}})


(def full-form {:schema schema :options options})
