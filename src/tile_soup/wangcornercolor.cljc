(ns tile-soup.wangcornercolor
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]))

(s/def ::name string?)
(s/def ::color string?)
(s/def ::tile u/str->int)
(s/def ::probability u/str->float)

(s/def ::attrs (s/keys
                 :req-un [::tile]
                 :opt-un [::name
                          ::color
                          ::probability]))

(s/def ::wangcornercolor (s/keys :req-un [::attrs]))

