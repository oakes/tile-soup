(ns tile-soup.object
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.properties :as properties]))

(s/def ::id u/str->int)
(s/def ::name string?)
(s/def ::type string?)
(s/def ::x u/str->int)
(s/def ::y u/str->int)
(s/def ::width u/str->int)
(s/def ::height u/str->int)
(s/def ::rotation u/str->float)
(s/def ::gid u/str->int)
(s/def ::visible u/str->boolean)
(s/def ::template string?)

(s/def ::attrs (s/keys :req-un [::id
                                ::name
                                ::type
                                ::x
                                ::y
                                ::width
                                ::height
                                ::rotation
                                ::gid
                                ::visible
                                ::template]))

(s/def ::points u/comma-str->vector)

(defmulti spec :tag)
(defmethod spec :properties [_] ::properties/properties)
(defmethod spec :ellipse [_] map?)
(defmethod spec :point [_] map?)
(defmethod spec :polygon [_] (s/keys :req-un [::points]))
(defmethod spec :polyline [_] (s/keys :req-un [::points]))
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in object tags") {})))
(s/def ::content (u/conformer spec))

(s/def ::object (s/keys :req-un [::attrs ::content]))

