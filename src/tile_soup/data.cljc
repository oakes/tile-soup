(ns tile-soup.data
  (:require [clojure.spec.alpha :as s]))

(s/def ::encoding #{"base64" "csv"})
(s/def ::compression #{"gzip" "zlib"})

(s/def ::attrs (s/keys :opt-un [::encoding
                                ::compression]))

(s/def ::data (s/keys :req-un [::attrs]))

