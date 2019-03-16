(ns tile-soup.tile
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]))

(s/def ::id u/str->int)
(s/def ::type string?)
(s/def ::terrain u/comma-str->vector)
(s/def ::probability u/str->float)

(s/def ::attrs (s/keys :req-un [::id
                                ::type
                                ::terrain
                                ::probability]))

(s/def ::tile (s/keys :req-un [::attrs]))

