(ns tile-soup.data-csv
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.chunk :as chunk]))

(defmulti spec :tag)
(defmethod spec nil [_] u/comma-str->vector)
(defmethod spec :chunk [_] (s/keys :req-un [::chunk/attrs ::content]))
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in CSV-encoded data tags") {})))
(s/def ::content (s/conformer
                   (fn [x]
                     (->> x
                          (map #(u/parse (spec %) %))
                          (filter seq)))))

