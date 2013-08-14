(ns slp.models.formschema)

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
      :title "First Name"
			:type "string"
;			:required true
		}
    :last-name {
      :title "Last Name"
			:type "string"
;			:required true
		}
		:date-of-birth {
      :title "Date of Birth"
			:type "text"
			:format "date"
;			:required true
		}
		:social-security {
      :title "Social Security Number"
			:type "number"
;			:required true
		}
    :federal-pin {
      :title "Federal Pin"
      :type "number"
;      :required false
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
    :first-name {
      :label "First Name"
		}
    :last-name {
      :label "Last Name"
;			:hideInitValidationError true
		}
		:date-of-birth {
      :label "Date of Birth"
;			:hideInitValidationError true
		}
		:social-security {
      :label "Social Security Number"
;			:hideInitValidationError true
		}
    :federal-pin {
      :label "Federal Pin"
;      :hideInitValidationError true
    }}})


(def full-form {:schema schema :options options})
