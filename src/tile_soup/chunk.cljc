(ns tile-soup.chunk
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]))

(s/def ::x u/str->long)
(s/def ::y u/str->long)
(s/def ::width u/str->long)
(s/def ::height u/str->long)

(s/def ::attrs (s/keys
                 :req-un [::x
                          ::y
                          ::width
                          ::height]))

