(ns tile-soup.frame
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]))

(s/def ::tileid u/str->long)
(s/def ::duration u/str->long)

(s/def ::attrs (s/keys
                 :req-un [::tileid
                          ::duration]))

(s/def ::frame (s/keys :req-un [::attrs]))

