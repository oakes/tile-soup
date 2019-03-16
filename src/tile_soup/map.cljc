(ns tile-soup.map
  (:require [clojure.spec.alpha :as s]
            [tile-soup.attrs :as attrs]
            [tile-soup.tileset :as tileset]
            [tile-soup.layer :as layer]))

(s/def ::tag #{:map})
(s/def ::attrs (s/keys :req-un [::attrs/version]))
(s/def ::content (s/coll-of (s/or
                              :tileset ::tileset/tileset
                              :layer ::layer/layer
                              :string string?)))
(s/def ::map (s/keys :req-un [::tag ::attrs ::content]))

