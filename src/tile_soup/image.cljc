(ns tile-soup.image
  (:require [clojure.spec.alpha :as s]))

(s/def ::format string?)

(s/def ::attrs (s/keys :opt-un [::format]))

(s/def ::image (s/keys :req-un [::attrs]))

