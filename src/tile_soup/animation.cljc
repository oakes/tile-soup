(ns tile-soup.animation
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.frame :as frame]))

(defmulti spec :tag)
(defmethod spec :frame [_] ::frame/frame)
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in animation tags") {})))
(s/def ::content (u/conformer spec))

(s/def ::animation (s/keys :req-un [::content]))

