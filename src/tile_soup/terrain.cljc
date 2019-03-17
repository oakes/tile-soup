(ns tile-soup.terrain
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.properties :as properties]))

(s/def ::name string?)
(s/def ::tile u/str->int)

(s/def ::attrs (s/keys :req-un [::name
                                ::tile]))

(defmulti spec :tag)
(defmethod spec :properties [_] ::properties/properties)
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in terrain tags") {})))
(s/def ::content (u/conformer spec))

(s/def ::terrain (s/keys :req-un [::attrs ::content]))

