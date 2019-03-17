(ns tile-soup.terraintypes
  (:require [clojure.spec.alpha :as s]
            [tile-soup.terrain :as terrain]))

(defmulti spec :tag)
(defmethod spec :terrain [_] ::terrain/terrain)
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in terraintypes tags") {})))
(s/def ::content (s/conformer (fn [x]
                                (->> x
                                     (remove string?)
                                     (mapv #(s/conform (spec %) %))))))

(s/def ::terraintypes (s/keys :req-un [::content]))

