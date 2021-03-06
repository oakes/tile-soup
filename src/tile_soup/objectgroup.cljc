(ns tile-soup.objectgroup
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.properties :as properties]
            [tile-soup.object :as object]))

(s/def ::id u/str->long)
(s/def ::name string?)
(s/def ::color string?)
(s/def ::x u/str->long)
(s/def ::y u/str->long)
(s/def ::width u/str->long)
(s/def ::height u/str->long)
(s/def ::opacity u/str->float)
(s/def ::visible u/str->boolean)
(s/def ::offsetx u/str->long)
(s/def ::offsety u/str->long)
(s/def ::draworder #{"index" "topdown"})

(s/def ::attrs (s/keys
                 :opt-un [::id
                          ::name
                          ::color
                          ::x
                          ::y
                          ::width
                          ::height
                          ::opacity
                          ::visible
                          ::offsetx
                          ::offsety
                          ::draworder]))

(defmulti spec :tag)
(defmethod spec :properties [_] ::properties/properties)
(defmethod spec :object [_] ::object/object)
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in objectgroup tags") {})))
(s/def ::content (u/conformer spec))

(s/def ::objectgroup (s/keys :req-un [::attrs ::content]))

