(ns tile-soup.properties
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]))

(s/def ::name string?)
(s/def ::type #{"string" "int" "float" "bool" "color" "file"})
(s/def ::value any?)

(s/def ::attrs (s/keys :req-un [::name
                                ::type
                                ::value]))

(s/def ::properties (s/keys :req-un [::attrs]))

