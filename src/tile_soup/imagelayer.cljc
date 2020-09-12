(ns tile-soup.imagelayer
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.properties :as properties]
            [tile-soup.image :as image]))

(s/def ::id u/str->long)
(s/def ::name string?)
(s/def ::offsetx u/str->long)
(s/def ::offsety u/str->long)
(s/def ::opacity u/str->float)
(s/def ::visible u/str->boolean)

(s/def ::attrs (s/keys
                 :opt-un [::id
                          ::name
                          ::offsetx
                          ::offsety
                          ::opacity
                          ::visible]))

(defmulti spec :tag)
(defmethod spec :properties [_] ::properties/properties)
(defmethod spec :image [_] ::image/image)
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in imagelayer tags") {})))
(s/def ::content (u/conformer spec))

(s/def ::imagelayer (s/keys :req-un [::attrs ::content]))

