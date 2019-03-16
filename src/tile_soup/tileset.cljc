(ns tile-soup.tileset
  (:require [clojure.spec.alpha :as s]))

(s/def ::name string?)

(s/def ::attrs (s/keys :opt-un [::name]))

(s/def ::tileset (s/keys :req-un [::attrs]))

