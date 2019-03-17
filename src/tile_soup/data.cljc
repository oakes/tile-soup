(ns tile-soup.data
  (:require [clojure.spec.alpha :as s]
            [tile-soup.data-tag :as data-tag]
            [tile-soup.data-base64 :as data-base64]
            [tile-soup.data-csv :as data-csv]))

(s/def ::encoding #{"base64" "csv"})
(s/def ::compression ;#{"gzip" "zlib"}
  (s/conformer (fn [_]
                 (throw (ex-info "Compression is not currently supported" {})))))

(s/def ::attrs (s/keys
                 :opt-un [::encoding
                          ::compression]))

(defn- get-encoding [x] (-> x :attrs :encoding))

(defmulti spec get-encoding)
(defmethod spec nil [_] (s/keys :req-un [::attrs ::data-tag/content]))
(defmethod spec "base64" [_] (s/keys :req-un [::attrs ::data-base64/content]))
(defmethod spec "csv" [_] (s/keys :req-un [::attrs ::data-csv/content]))
(defmethod spec :default [x]
  (throw (ex-info (str (get-encoding x) " not a supported encoding in data tags") {})))
(s/def ::data (s/conformer (fn [x]
                             (s/conform (spec x) x))))

