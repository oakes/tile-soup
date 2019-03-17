(ns tile-soup.data-csv
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]))

(s/def ::content (s/conformer (fn [content]
                                (if (= 1 (count content))
                                  (u/comma-str->vector* (first content))
                                  (throw (ex-info "Expected only one child in csv-encoded tag" {}))))))

