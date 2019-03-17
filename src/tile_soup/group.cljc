(ns tile-soup.group
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.properties :as properties]
            [tile-soup.layer :as layer]
            [tile-soup.objectgroup :as objectgroup]
            [tile-soup.imagelayer :as imagelayer]))

(s/def ::id u/str->int)
(s/def ::name string?)
(s/def ::offsetx u/str->int)
(s/def ::offsety u/str->int)
(s/def ::opacity u/str->float)
(s/def ::visible u/str->boolean)

(s/def ::attrs (s/keys :opt-un [::id
                                ::name
                                ::offsetx
                                ::offsety
                                ::opacity
                                ::visible]))

(defmulti spec :tag)
(defmethod spec :properties [_] ::properties/properties)
(defmethod spec :layer [_] ::layer/layer)
(defmethod spec :objectgroup [_] ::objectgroup/objectgroup)
(defmethod spec :imagelayer [_] ::imagelayer/imagelayer)
(defmethod spec :group [_] ::group)
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in group tags") {})))
(s/def ::content (u/conformer spec))

(s/def ::group (s/keys :req-un [::attrs ::content]))

