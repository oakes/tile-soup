(ns tile-soup.properties
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.property :as property]))

(defmulti spec :tag)
(defmethod spec :property [_] ::property/property)
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in properties tags") {})))
(s/def ::content (u/conformer spec))

(s/def ::properties (s/keys :req-un [::content]))

