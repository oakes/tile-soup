(ns tile-soup.property
  (:require [clojure.spec.alpha :as s]))

(s/def ::name string?)
(s/def ::type #{"string" "int" "float" "bool" "color" "file"})
(s/def ::value any?)

(s/def ::attrs (s/keys
                 :req-un [::name
                          ::value]
                 :opt-un [::type]))

(s/def ::property (s/keys :req-un [::attrs]))

