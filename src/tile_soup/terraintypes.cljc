(ns tile-soup.terraintypes
  (:require [clojure.spec.alpha :as s]
            [tile-soup.terrain :as terrain]))

(defmulti child :tag)
(defmethod child nil [_] any?)
(defmethod child :terrain [_] ::terrain/terrain)
(s/def ::content (s/coll-of (s/multi-spec child (fn [gen-v _] gen-v))))

(s/def ::terraintypes (s/keys :req-un [::content]))

