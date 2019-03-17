(ns tile-soup.terraintypes
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.terrain :as terrain]))

(defmulti spec :tag)
(defmethod spec :terrain [_] ::terrain/terrain)
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in terraintypes tags") {})))
(s/def ::content (u/conformer spec))

(s/def ::terraintypes (s/keys :req-un [::content]))

