(ns tile-soup.tileset
  (:require [clojure.spec.alpha :as s]
            [tile-soup.attrs :as attrs]))

(s/def ::tag #{:tileset})
(s/def ::attrs (s/keys :req-un [::attrs/name]))
(s/def ::tileset (s/keys :req-un [::tag ::attrs]))

