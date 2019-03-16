(ns tile-soup.layer
  (:require [clojure.spec.alpha :as s]))

(s/def ::name string?)

(s/def ::attrs (s/keys :opt-un [::name]))

(s/def ::layer (s/keys :req-un [::attrs]))

