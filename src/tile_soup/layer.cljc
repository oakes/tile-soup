(ns tile-soup.layer
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.properties :as properties]
            [tile-soup.data :as data]))

(s/def ::id u/str->int)
(s/def ::name string?)
(s/def ::x u/str->int)
(s/def ::y u/str->int)
(s/def ::width u/str->int)
(s/def ::height u/str->int)
(s/def ::opacity u/str->float)
(s/def ::visible u/str->boolean)
(s/def ::offsetx u/str->int)
(s/def ::offsety u/str->int)

(s/def ::attrs (s/keys
                 :opt-un [::id
                          ::name
                          ::x
                          ::y
                          ::width
                          ::height
                          ::opacity
                          ::visible
                          ::offsetx
                          ::offsety]))

(defmulti spec :tag)
(defmethod spec :properties [_] ::properties/properties)
(defmethod spec :data [_] ::data/data)
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in layer tags") {})))
(s/def ::content (u/conformer spec))

(s/def ::layer (s/keys :req-un [::attrs ::content]))

