(ns tile-soup.terrain
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.properties :as properties]))

(s/def ::name string?)
(s/def ::tile u/str->int)

(s/def ::attrs (s/keys :req-un [::name
                                ::tile]))

(defmulti child :tag)
(defmethod child nil [_] any?)
(defmethod child :properties [_] ::properties/properties)
(s/def ::content (s/coll-of (s/multi-spec child :tag)))

(s/def ::terrain (s/keys :req-un [::attrs ::content]))

