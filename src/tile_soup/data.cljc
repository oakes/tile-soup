(ns tile-soup.data
  (:require [clojure.spec.alpha :as s]
            [tile-soup.tile :as tile]
            [tile-soup.chunk :as chunk]))

(s/def ::encoding #{"base64" "csv"})
(s/def ::compression #{"gzip" "zlib"})

(s/def ::attrs (s/keys :opt-un [::encoding
                                ::compression]))

(defmulti child :tag)
(defmethod child nil [_] any?)
(defmethod child :tile [_] ::tile/tile)
(defmethod child :chunk [_] ::chunk/chunk)
(s/def ::content (s/coll-of (s/multi-spec child :tag)))

(s/def ::data (s/keys :req-un [::attrs ::content]))

