(ns tile-soup.data
  (:require [clojure.spec.alpha :as s]
            [tile-soup.data-base64 :as data-base64]
            [tile-soup.utils :as u]))

(s/def ::encoding #{"base64" "csv"})

(s/def ::compression ;#{"gzip" "zlib"}
  (s/conformer (fn [_]
                 (throw (ex-info "Compression is not currently supported" {})))))

(s/def ::attrs (s/keys :opt-un [::encoding
                                ::compression]))

(defn- tile [tile]
  (if (not= :tile (:tag tile))
    ::s/invalid
    (or (some-> tile :attrs :gid u/str->int*)
        0)))

(defmulti content :tag)
(defmethod content nil [_] nil)
(defmethod content :tile [x] (tile x))
(s/def ::content (s/conformer (fn [x]
                                (vec (keep content x)))))

(defmulti data #(-> % :attrs :encoding))
(defmethod data nil [_] (s/keys :req-un [::attrs ::content]))
(defmethod data "base64" [_] (s/keys :req-un [::attrs ::data-base64/content]))
(s/def ::data (s/multi-spec data (fn [gen-v _] gen-v)))

