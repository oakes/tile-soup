(ns tile-soup.tile
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.properties :as properties]
            [tile-soup.image :as image]
            [tile-soup.objectgroup :as objectgroup]
            [tile-soup.animation :as animation]))

(s/def ::id u/str->int)
(s/def ::type string?)
(s/def ::terrain u/comma-str->vector)
(s/def ::probability u/str->float)

(s/def ::attrs (s/keys
                 :opt-un [::id
                          ::type
                          ::terrain
                          ::probability]))

(defmulti spec :tag)
(defmethod spec :properties [_] ::properties/properties)
(defmethod spec :image [_] ::image/image)
(defmethod spec :objectgroup [_] ::objectgroup/objectgroup)
(defmethod spec :animation [_] ::animation/animation)
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in tile tags") {})))
(s/def ::content (u/conformer spec))

(s/def ::tile (s/keys :req-un [::attrs ::content]))

