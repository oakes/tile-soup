(ns tile-soup.grid
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]))

(s/def ::orientation #{"orthogonal" "isometric"})
(s/def ::width u/str->int)
(s/def ::height u/str->int)

(s/def ::attrs (s/keys
                 :req-un [::orientation
                          ::width
                          ::height]))

(s/def ::grid (s/keys :req-un [::attrs]))

