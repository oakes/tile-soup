(ns tile-soup.data-xml
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]))

(defn- tile [tile]
  (or (some->> tile :attrs :gid (u/parse u/str->long))
      0))

(defmulti spec :tag)
(defmethod spec :tile [_] (s/conformer tile))
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in XML-encoded data tags") {})))
(s/def ::content (s/conformer
                   (fn [x]
                     (list (->> x
                                (remove string?)
                                (mapv #(u/parse (spec %) %)))))))

