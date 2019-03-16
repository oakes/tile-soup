(ns tile-soup.chunk
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.tile :as tile]))

(s/def ::x u/str->int)
(s/def ::y u/str->int)
(s/def ::width u/str->int)
(s/def ::height u/str->int)

(s/def ::attrs (s/keys :req-un [::x
                                ::y
                                ::width
                                ::height]))

(defmulti content :tag)
(defmethod content nil [_] any?)
(defmethod content :tile [_] ::tile/tile)
(s/def ::content (s/coll-of (s/multi-spec content (fn [gen-v _] gen-v))))

(s/def ::grid (s/keys :req-un [::attrs ::content]))

