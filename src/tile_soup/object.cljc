(ns tile-soup.object
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.properties :as properties]
            [tile-soup.polygon :as polygon]
            [tile-soup.polyline :as polyline]
            [tile-soup.text :as text]))

(s/def ::id u/str->long)
(s/def ::name string?)
(s/def ::type string?)
(s/def ::x u/str->long)
(s/def ::y u/str->long)
(s/def ::width u/str->long)
(s/def ::height u/str->long)
(s/def ::rotation u/str->float)
(s/def ::gid u/str->long)
(s/def ::visible u/str->boolean)
(s/def ::template string?)

(s/def ::attrs (s/keys
                 :opt-un [::id
                          ::x
                          ::y
                          ::name
                          ::type
                          ::width
                          ::height
                          ::rotation
                          ::gid
                          ::visible
                          ::template]))

(defmulti spec :tag)
(defmethod spec :properties [_] ::properties/properties)
(defmethod spec :ellipse [_] map?)
(defmethod spec :point [_] map?)
(defmethod spec :polygon [_] ::polygon/polygon)
(defmethod spec :polyline [_] ::polyline/polyline)
(defmethod spec :text [_] ::text/text)
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in object tags") {})))
(s/def ::content (u/conformer spec))

(s/def ::object (s/keys :req-un [::attrs ::content]))

