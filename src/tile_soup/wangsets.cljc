(ns tile-soup.wangsets
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.wangset :as wangset]))

(defmulti spec :tag)
(defmethod spec :wangset [_] ::wangset/wangset)
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in wangsets tags") {})))
(s/def ::content (u/conformer spec))

(s/def ::wangsets (s/keys :req-un [::content]))

