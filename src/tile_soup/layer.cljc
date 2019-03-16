(ns tile-soup.layer
  (:require [clojure.spec.alpha :as s]
            [tile-soup.attrs :as attrs]))

(s/def ::tag #{:layer})
(s/def ::attrs (s/keys :req-un [::attrs/name]))
(s/def ::layer (s/keys :req-un [::tag ::attrs]))

