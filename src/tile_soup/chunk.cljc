(ns tile-soup.chunk
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]))

(s/def ::x u/str->int)
(s/def ::y u/str->int)
(s/def ::width u/str->int)
(s/def ::height u/str->int)

(s/def ::attrs (s/keys
                 :req-un [::x
                          ::y
                          ::width
                          ::height]))

