(ns tile-soup.data-base64
  (:require [clojure.spec.alpha :as s]
            [clojure.string :as str]
            [tile-soup.utils :as u]
            [tile-soup.chunk :as chunk]
            #?(:clj [clojure.data.codec.base64 :as base64]))
  #?(:clj (:import [java.nio ByteBuffer ByteOrder])))

(defn decode [s]
  (if-not (string? s)
    ::s/invalid
    (let [s (str/trim s)]
      #?(:clj  (try
                 (let [arr (base64/decode (.getBytes s "UTF-8"))
                       len (alength arr)
                       buffer (doto (ByteBuffer/wrap arr)
                                (.order ByteOrder/LITTLE_ENDIAN))]
                   (reduce
                     (fn [v i]
                       (conj v (.getInt buffer i)))
                     []
                     (range 0 len 4)))
                 (catch Exception _ ::s/invalid))
         :cljs (try (let [s (js/atob s)
                          len (.-length s)
                          arr (js/Uint8Array. len)
                          _ (dotimes [i len]
                              (aset arr i (.charCodeAt s i)))
                          view (js/DataView. (.-buffer arr))]
                      (reduce
                        (fn [v i]
                          (conj v (.getUint32 view i true)))
                        []
                        (range 0 len 4)))
                 (catch js/DOMException _ ::s/invalid))))))

(defmulti spec :tag)
(defmethod spec nil [_] (s/conformer decode))
(defmethod spec :chunk [_] (s/keys :req-un [::chunk/attrs ::content]))
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in base64-encoded data tags") {})))
(s/def ::content (s/conformer
                   (fn [x]
                     (->> x
                          (map #(u/parse (spec %) %))
                          (filter seq)))))

