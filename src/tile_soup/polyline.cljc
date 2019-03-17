(ns tile-soup.polyline
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]))

(s/def ::points u/comma-str->vector)

(s/def ::attrs (s/keys :req-un [::points]))

(s/def ::polyline (s/keys :req-un [::attrs]))

