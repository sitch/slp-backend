(ns slp.ws.models.aggregate)

(defn aggregate-accounts
  [accounts]
  
  (merge (apply merge-with + (map #(select-keys % [:loans
																									:minimum-payment
																									:current-balance
																									:original-balance])))
         (:accounts (count accounts))
  ))
  
  
;  (select-keys (apply merge-with + accounts))
;	:percent_paid
;	:weighted_interest_rate 0
;	:remaining_lifetime_interest 0
;	:remaining_lifetime_payment 35240
;	:incomplete_repayment_options 0
;	:payment_too_low true