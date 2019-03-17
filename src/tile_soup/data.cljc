(ns tile-soup.data
  (:require [clojure.spec.alpha :as s]
            [tile-soup.data-base64 :as data-base64]
            [tile-soup.utils :as u]
            [tile-soup.data-tile :as data-tile]))

(s/def ::encoding #{"base64" "csv"})
(s/def ::compression #{"gzip" "zlib"})

(s/def ::attrs (s/keys :opt-un [::encoding
                                ::compression]))

(s/def ::tile (s/conformer (fn [tile]
                             (if (not= :tile (:tag tile))
                               ::s/invalid
                               (or (-> tile :attrs :gid u/str->int*)
                                   ::s/invalid)))))
(s/def ::content (s/coll-of ::tile))

(defmulti data #(-> % :attrs :encoding))
(defmethod data nil [_] (s/keys :req-un [::attrs ::content]))
(defmethod data "base64" [_] (s/keys :req-un [::attrs ::data-base64/content]))
(s/def ::data (s/multi-spec data (fn [gen-v _] gen-v)))

